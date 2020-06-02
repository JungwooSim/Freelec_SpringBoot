package me.study.application.service.posts;

import lombok.RequiredArgsConstructor;
import me.study.application.domain.posts.Posts;
import me.study.application.domain.posts.PostsRepository;
import me.study.application.web.dto.PostsListResponseDto;
import me.study.application.web.dto.PostsResponseDto;
import me.study.application.web.dto.PostsSaveRequestsDto;
import me.study.application.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDto requestsDto) {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남기기 때문에 속도 개선에 도움이 됨
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllByDESC().stream().map(PostsListResponseDto::new).collect(Collectors.toList());

        // .map(PostsListReponseDto::new) = .map(posts -> new PostsListReponseDto(posts))
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        postsRepository.delete(posts);
    }
}
