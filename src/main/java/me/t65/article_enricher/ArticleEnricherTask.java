package me.t65.article_enricher;

import me.t65.article_enricher.db.mongo.entities.ArticleContentEntity;
import me.t65.article_enricher.db.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Map;

@Component
public class ArticleEnricherTask implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleEnricherTask.class);
    private final Scheduler scheduler;
    private final WebClient webClient;
    private final DbArticleTypeService dbArticleTypeService;

    @Autowired
    public ArticleEnricherTask(
            Scheduler scheduler,
            WebClient.Builder webClientBuilder,
            DbArticleTypeService dbArticleTypeService,
            @Value("${enricher.url}") String enricherUrl) {
        this.scheduler = scheduler;
        this.webClient = webClientBuilder.baseUrl(enricherUrl).build();
        this.dbArticleTypeService = dbArticleTypeService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.fromIterable(dbArticleTypeService.getArticlesContentWithoutType())
                .flatMap(
                        articleContent ->
                                this.getEnrichmentApiType(articleContent)
                                        .map(
                                                articleType ->
                                                        Map.entry(
                                                                articleContent.getId(),
                                                                articleType)))
                .doOnNext(
                        entry ->
                                LOGGER.info(
                                        "Processed Article {} with type {}",
                                        entry.getKey(),
                                        entry.getValue()))
                .flatMap(
                        entry ->
                                dbArticleTypeService.saveArticleTypeReactive(
                                        entry.getKey(), entry.getValue()))
                .doOnError(e -> LOGGER.error("Error processing articles", e))
                .subscribeOn(scheduler)
                .blockLast();
    }

    public Mono<String> getEnrichmentApiType(ArticleContentEntity articleContent) {
        return Mono.justOrEmpty(articleContent)
                .filter(
                        content ->
                                !isNullOrEmpty(content.getName())
                                        && !isNullOrEmpty(content.getDescription()))
                .switchIfEmpty(
                        Mono.error(
                                new IllegalArgumentException(
                                        "ArticleContentEntity must have non-null title and body.")))
                .map(
                        content ->
                                Map.of(
                                        "title",
                                        content.getName(),
                                        "body",
                                        content.getDescription()))
                .flatMap(
                        payload ->
                                webClient
                                        .post()
                                        .uri("/models/v1/type")
                                        .headers(
                                                headers ->
                                                        headers.setContentType(
                                                                MediaType.APPLICATION_JSON))
                                        .bodyValue(payload)
                                        .retrieve()
                                        .bodyToMono(Map.class))
                .flatMap(
                        response -> {
                            if (response == null || !response.containsKey("type")) {
                                return Mono.error(
                                        new RuntimeException(
                                                "Invalid response: Missing 'type' field."));
                            }
                            return Mono.just(response.get("type").toString());
                        })
                .doOnError(err -> LOGGER.error("Error during FastAPI call", err))
                .onErrorResume(err -> Mono.empty())
                .subscribeOn(scheduler);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
