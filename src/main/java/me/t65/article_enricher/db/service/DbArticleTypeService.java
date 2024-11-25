package me.t65.article_enricher.db.service;

import me.t65.article_enricher.db.mongo.entities.ArticleContentEntity;
import me.t65.article_enricher.db.mongo.repository.ArticleContentRepository;
import me.t65.article_enricher.db.postgres.entities.ArticleTypeEntity;
import me.t65.article_enricher.db.postgres.repository.ArticleTypeRepository;
import me.t65.article_enricher.db.postgres.repository.ArticlesRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;
import java.util.UUID;

@Service
public class DbArticleTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbArticleTypeService.class);

    private final Scheduler scheduler;
    private final ArticleTypeRepository articleTypeRepository;
    private final ArticlesRepository articlesRepository;
    private final ArticleContentRepository articleContentRepository;

    @Autowired
    public DbArticleTypeService(
            Scheduler scheduler,
            ArticleTypeRepository articleTypeRepository,
            ArticlesRepository articlesRepository,
            ArticleContentRepository articleContentRepository) {
        this.scheduler = scheduler;
        this.articleTypeRepository = articleTypeRepository;
        this.articlesRepository = articlesRepository;
        this.articleContentRepository = articleContentRepository;
    }

    public Mono<Boolean> saveArticleTypeReactive(UUID articleId, String articleType) {
        return Mono.defer(
                () -> {
                    return Mono.fromCallable(
                                    () -> articleTypeRepository.existsByArticleId(articleId))
                            .filter(exists -> !exists)
                            .flatMap(
                                    exists -> {
                                        if (exists) {
                                            LOGGER.info(
                                                    "Article type for article ID {} already"
                                                            + " exists.",
                                                    articleId);
                                            return Mono.just(false);
                                        }

                                        ArticleTypeEntity articleTypeEntity =
                                                new ArticleTypeEntity(articleId, articleType);
                                        return Mono.fromCallable(
                                                        () ->
                                                                articleTypeRepository.save(
                                                                        articleTypeEntity))
                                                .doOnSuccess(
                                                        saved ->
                                                                LOGGER.info(
                                                                        "Successfully saved article"
                                                                                + " type: {} for"
                                                                                + " article ID: {}",
                                                                        articleType,
                                                                        articleId))
                                                .thenReturn(true);
                                    })
                            .subscribeOn(scheduler);
                });
    }

    /** Retrieves a list of article content for articles that do not have a type assigned. */
    public List<ArticleContentEntity> getArticlesContentWithoutType() {

        List<UUID> articleIdsWithoutType = articlesRepository.findArticlesWithoutType();

        List<ArticleContentEntity> articleContents =
                articleContentRepository.findAllById(articleIdsWithoutType);

        return articleContents;
    }
}
