package me.t65.article_enricher;

import static org.mockito.Mockito.*;

import me.t65.article_enricher.db.service.DbArticleTypeService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ArticleEnricherTaskTests {

    @Mock private DbArticleTypeService dbArticleTypeService;

    @InjectMocks private ArticleEnricherTask articleEnricherTask;
}
