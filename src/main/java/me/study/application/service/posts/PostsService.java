package me.study.application.service.posts;

import lombok.RequiredArgsConstructor;
import me.study.application.domain.posts.PostsRepository;
import me.study.application.web.dto.PostsSaveRequestsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDto requestsDto) {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }
}
