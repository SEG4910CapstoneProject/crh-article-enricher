package me.t65.article_enricher.stubs;

import me.t65.article_enricher.db.mongo.entities.ArticleContentEntity;
import me.t65.article_enricher.db.mongo.repository.ArticleContentRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
@Primary
public class ArticleContentRepositoryStub implements ArticleContentRepository {
    @Override
    public <S extends ArticleContentEntity> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends ArticleContentEntity> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends ArticleContentEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ArticleContentEntity> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ArticleContentEntity> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ArticleContentEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ArticleContentEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ArticleContentEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ArticleContentEntity, R> R findBy(
            Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ArticleContentEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ArticleContentEntity> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<ArticleContentEntity> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<ArticleContentEntity> findAll() {
        return List.of();
    }

    @Override
    public List<ArticleContentEntity> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {}

    @Override
    public void delete(ArticleContentEntity entity) {}

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {}

    @Override
    public void deleteAll(Iterable<? extends ArticleContentEntity> entities) {}

    @Override
    public void deleteAll() {}

    @Override
    public List<ArticleContentEntity> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ArticleContentEntity> findAll(Pageable pageable) {
        return null;
    }
}
