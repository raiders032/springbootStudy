package org.example.springbootstudy.web;

import lombok.RequiredArgsConstructor;
import org.example.springbootstudy.config.auth.LoginUser;
import org.example.springbootstudy.config.auth.dto.SessionUser;
import org.example.springbootstudy.service.PostsService;
import org.example.springbootstudy.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return"index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return"posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return"posts-update";
    }
}
