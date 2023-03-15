package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommetRepository articleCommetRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommetRepository articleCommetRepository) {
        this.articleRepository = articleRepository;
        this.articleCommetRepository = articleCommetRepository;
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_theWorksFine(){
    //given
    Long previousCount = articleRepository.count();


    //when
    Article savedArticle = articleRepository.save(Article.of("new article", "new cotenent", "spring"));
    //then
    assertThat(articleRepository.count()).isEqualTo(previousCount + 1 );

    }
    @DisplayName("update 테스트")
    @Test
    void givenTestData_whennupdating_theWorksFine(){
        //given
        Article firstaricle = articleRepository.save(Article.of("new article", "new cotenent", "hashtag"));
        Article secondaricle = articleRepository.save(Article.of("new article", "new cotenent", "hashtag"));
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        //when
        Article savedArticle = articleRepository.saveAndFlush(article);
        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }
    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whennupdeleting_theWorksFine(){
        //given
        Article firstaricle = articleRepository.save(Article.of("new article", "new cotenent", "hashtag"));
        Article secondaricle = articleRepository.save(Article.of("new article", "new cotenent", "hashtag"));
        Article article = articleRepository.findById(1L).orElseThrow();

        Long previousAticleCount = articleRepository.count();



        //when
       articleRepository.delete(article);
        //then
        assertThat(articleRepository.count()).isEqualTo(previousAticleCount - 1);
    }
}