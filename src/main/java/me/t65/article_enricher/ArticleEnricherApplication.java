package me.t65.article_enricher;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
public class ArticleEnricherApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ArticleEnricherApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
