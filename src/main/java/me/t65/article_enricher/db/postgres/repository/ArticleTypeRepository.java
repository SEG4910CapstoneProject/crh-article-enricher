package me.t65.article_enricher.db.postgres.repository;

import me.t65.article_enricher.db.postgres.entities.ArticleTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity, UUID> {
    boolean existsByArticleId(UUID articleId);
}
