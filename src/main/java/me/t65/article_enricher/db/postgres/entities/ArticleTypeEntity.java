package me.t65.article_enricher.db.postgres.entities;

import jakarta.persistence.*;

import java.util.UUID;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.ToString
@lombok.EqualsAndHashCode
@Entity
@Table(name = "article_type")
public class ArticleTypeEntity {

    @Id
    @Column(name = "article_ID", columnDefinition = "uuid")
    private UUID articleId;

    @Column(name = "article_type")
    private String articleType;
}
