package org.example.springbootstudy.web;

import lombok.RequiredArgsConstructor;
import org.example.springbootstudy.service.PostsService;
import org.example.springbootstudy.web.dto.PostsResponseDto;
import org.example.springbootstudy.web.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private  final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody  PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }


}
