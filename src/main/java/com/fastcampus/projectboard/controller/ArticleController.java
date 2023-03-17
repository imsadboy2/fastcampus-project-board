package com.fastcampus.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(Model map){
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model map){
        map.addAttribute("article", "article"); //TODO: 구현 할 때 여기에 실제 데이터 기입
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }
}
