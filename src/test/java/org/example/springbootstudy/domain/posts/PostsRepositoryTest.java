package org.example.springbootstudy.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "test title";
        String content = "test content";
        String author = "test author";

        postsRepository.save(Posts.builder()
                .author(author)
                .content(content)
                .title(title)
                .build());
        //when
        List<Posts> postList = postsRepository.findAll();

        //then
        Posts posts = postList.get(0);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getTitle()).isEqualTo(title);
    }

    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.now();
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> all = postsRepository.findAll();

        //then
        Posts posts = all.get(0);

        System.out.println("---------------------------------------------");
        System.out.println("nowDate : "+now);
        System.out.println("createdDate : "+savedPosts.getCreatedDate());
        System.out.println("modifiedDate : "+savedPosts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}