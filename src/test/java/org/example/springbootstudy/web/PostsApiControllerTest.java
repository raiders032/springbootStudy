package org.example.springbootstudy.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springbootstudy.domain.posts.Posts;
import org.example.springbootstudy.domain.posts.PostsRepository;
import org.example.springbootstudy.web.dto.PostsSaveRequestDto;
import org.example.springbootstudy.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.PostUpdate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @Autowired private WebApplicationContext context;

    private MockMvc mvc;

    @LocalServerPort
    private int port;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        mvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void posts_등록된다() throws Exception {

        //given
        String title = "spring-boot and AWS";
        String author = "nys";
        String content = "test content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .author(author)
                .content(content)
                .title(title)
                .build();
        String url = "http://localhost:"+port+"/api/v1/posts";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());


        //then
        List<Posts> posts = postsRepository.findAll();
        assertThat(posts.get(0).getTitle()).isEqualTo(title);

    }

    @Test
    @WithMockUser(roles="USER")
    public void posts_수정된다() throws Exception {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .author("nys")
                .content("content1")
                .title("title1")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity= new HttpEntity<>(requestDto);

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());


        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    }

}