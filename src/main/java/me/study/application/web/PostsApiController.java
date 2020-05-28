package me.study.application.web;

import lombok.RequiredArgsConstructor;
import me.study.application.service.posts.PostsService;
import me.study.application.web.dto.PostsSaveRequestsDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 만들어 준다.
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestsDto requestsDto) {
        return postsService.save(requestsDto);
    }
}
