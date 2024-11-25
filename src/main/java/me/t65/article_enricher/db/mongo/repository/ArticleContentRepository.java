package me.t65.article_enricher.db.mongo.repository;

import me.t65.article_enricher.db.mongo.entities.ArticleContentEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleContentRepository extends MongoRepository<ArticleContentEntity, UUID> {}
