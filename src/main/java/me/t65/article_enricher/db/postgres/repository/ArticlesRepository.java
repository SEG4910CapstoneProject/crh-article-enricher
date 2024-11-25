package me.t65.article_enricher.db.postgres.repository;

import me.t65.article_enricher.db.postgres.entities.ArticlesEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticlesRepository extends JpaRepository<ArticlesEntity, UUID> {

    @Query(
            "SELECT a.articleId FROM ArticlesEntity a "
                    + "LEFT JOIN ArticleTypeEntity at ON a.articleId = at.articleId "
                    + "WHERE at.articleId IS NULL")
    List<UUID> findArticlesWithoutType();
}
