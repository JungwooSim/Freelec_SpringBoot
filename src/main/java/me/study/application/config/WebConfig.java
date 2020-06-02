package me.study.application.config;

import lombok.RequiredArgsConstructor;
import me.study.application.config.auth.LoginUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
LoginUserArgumentResolver Class 가 스프링에서 인식 될 수 있도록 WebMvcConfigurer에 추가
HandlerMethodArgumentResolver 는 항상 WebMvcConfigurer 의  argumentResolvers()를 통해 추가 해주어야 한다. ( 다른 HandlerMethodArgumentResolver 가 필요하면 같은 방식으로 추가하면 된다)
 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
