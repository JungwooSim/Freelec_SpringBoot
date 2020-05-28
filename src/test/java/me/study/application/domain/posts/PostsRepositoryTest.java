package me.study.application.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없이 @SpringBootTest 를 사용할 경우 H2 데이터베이스를 자동으로 실행 해준다.
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // Junit에서 테스트 단위가 끝날 때 마다 수행하는 메소드 지
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(
                Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}
