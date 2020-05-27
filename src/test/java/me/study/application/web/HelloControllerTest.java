package me.study.application.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다. (여기서는 SpringRunner.class를 실행)
// 여러 스프링 테스트 어노테이션 중, WEB에 집중할 수 있는 어노테이션
// @Controller, @ControllerAdvice 등을 사용할 수 있음. (@Service, @Component, @Repository 는 사용할 수 없음)
@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // 웹 API를 테스트할 때 사용. (HTTP, GET, POST 등에 대한 API 테스트를 할 수 있음)

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)) // 파라미터 요청할때 사용하므로 String만 허용됨
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))); //jsonPath는 JSON응답값을 필드별로 검증할 수 있는 메소드, $을 기준으로 필드명 명시
    }
}