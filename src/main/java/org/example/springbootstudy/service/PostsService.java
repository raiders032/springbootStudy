package org.example.springbootstudy.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootstudy.domain.posts.PostsRepository;
import org.example.springbootstudy.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveReauestDto) {
        return postsRepository.save(postsSaveReauestDto.toEntity()).getId();
    }
}

