package org.example.springbootstudy.web;

import lombok.RequiredArgsConstructor;
import org.example.springbootstudy.service.PostsService;
import org.example.springbootstudy.web.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private  final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody  PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
