package org.example.springbootstudy.web;

import lombok.RequiredArgsConstructor;
import org.example.springbootstudy.domain.posts.Posts;
import org.example.springbootstudy.service.PostsService;
import org.example.springbootstudy.web.dto.PostsResponseDto;
import org.example.springbootstudy.web.dto.PostsSaveRequestDto;
import org.example.springbootstudy.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private  final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody  PostsSaveRequestDto requestDto){
        PostsSaveRequestDto requestDto1 = new PostsSaveRequestDto("sdasd","asd","asd");
        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }




}
