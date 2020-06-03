package me.study.application.web;

import lombok.RequiredArgsConstructor;
import me.study.application.config.auth.LoginUser;
import me.study.application.config.auth.dto.SessionUser;
import me.study.application.service.posts.PostsService;
import me.study.application.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // // @LoginUser 을 통해 세션값을 간단하게 호출할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
