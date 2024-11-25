package me.t65.article_enricher.stubs;

import me.t65.article_enricher.db.postgres.entities.ArticleTypeEntity;
import me.t65.article_enricher.db.postgres.repository.ArticleTypeRepository;

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
public class ArticleTypeRepositoryStub implements ArticleTypeRepository {
    @Override
    public boolean existsByArticleId(UUID articleId) {
        return false;
    }

    @Override
    public void flush() {}

    @Override
    public <S extends ArticleTypeEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ArticleTypeEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<ArticleTypeEntity> entities) {}

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {}

    @Override
    public void deleteAllInBatch() {}

    @Override
    public ArticleTypeEntity getOne(UUID uuid) {
        return null;
    }

    @Override
    public ArticleTypeEntity getById(UUID uuid) {
        return null;
    }

    @Override
    public ArticleTypeEntity getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends ArticleTypeEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ArticleTypeEntity> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ArticleTypeEntity> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ArticleTypeEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ArticleTypeEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ArticleTypeEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ArticleTypeEntity, R> R findBy(
            Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ArticleTypeEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ArticleTypeEntity> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<ArticleTypeEntity> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<ArticleTypeEntity> findAll() {
        return List.of();
    }

    @Override
    public List<ArticleTypeEntity> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {}

    @Override
    public void delete(ArticleTypeEntity entity) {}

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {}

    @Override
    public void deleteAll(Iterable<? extends ArticleTypeEntity> entities) {}

    @Override
    public void deleteAll() {}

    @Override
    public List<ArticleTypeEntity> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ArticleTypeEntity> findAll(Pageable pageable) {
        return null;
    }
}
