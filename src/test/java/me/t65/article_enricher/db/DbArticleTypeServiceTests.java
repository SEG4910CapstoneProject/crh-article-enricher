package me.t65.article_enricher.db;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import me.t65.article_enricher.db.mongo.entities.ArticleContentEntity;
import me.t65.article_enricher.db.mongo.repository.ArticleContentRepository;
import me.t65.article_enricher.db.postgres.entities.ArticleTypeEntity;
import me.t65.article_enricher.db.postgres.repository.ArticleTypeRepository;
import me.t65.article_enricher.db.postgres.repository.ArticlesRepository;
import me.t65.article_enricher.db.service.DbArticleTypeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DbArticleTypeServiceTests {

    @Mock private ArticleTypeRepository articleTypeRepository;
    @Mock private ArticlesRepository articlesRepository;
    @Mock private ArticleContentRepository articleContentRepository;

    private DbArticleTypeService dbArticleTypeService;

    @BeforeEach
    private void beforeEach() {
        dbArticleTypeService =
                new DbArticleTypeService(
                        Schedulers.immediate(),
                        articleTypeRepository,
                        articlesRepository,
                        articleContentRepository);
    }

    @Test
    void testSaveArticleTypeReactive_whenArticleTypeDoesNotExist() {
        UUID articleId = UUID.randomUUID();
        String articleType = "Tech";

        when(articleTypeRepository.existsByArticleId(articleId)).thenReturn(false);

        when(articleTypeRepository.save(any(ArticleTypeEntity.class)))
                .thenReturn(new ArticleTypeEntity(articleId, articleType));

        Boolean result =
                dbArticleTypeService.saveArticleTypeReactive(articleId, articleType).block();

        assertTrue(result);

        verify(articleTypeRepository).existsByArticleId(articleId);
        verify(articleTypeRepository).save(any(ArticleTypeEntity.class));
    }

    @Test
    void testGetArticlesContentWithoutType() {

        List<UUID> articleIds = List.of(UUID.randomUUID(), UUID.randomUUID());

        when(articlesRepository.findArticlesWithoutType()).thenReturn(articleIds);

        when(articleContentRepository.findAllById(articleIds))
                .thenReturn(List.of(new ArticleContentEntity(), new ArticleContentEntity()));

        List<ArticleContentEntity> result = dbArticleTypeService.getArticlesContentWithoutType();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(articlesRepository).findArticlesWithoutType();
        verify(articleContentRepository).findAllById(articleIds);
    }
}
