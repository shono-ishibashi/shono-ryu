package com.bbs.controller;

import com.bbs.form.ArticleFrom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ArticleController {

    @ModelAttribute
    public ArticleFrom setUpArticleFrom(){
        return new ArticleFrom();
    }

    @RequestMapping("show-list")
    public String index(){
        return "bbs";
    }

    /**
     *
     * 記事を投稿するコントローラーのメソッド
     * @return
     */
    @RequestMapping("insert-article")
    public String insertArticle(){
        return index();
    }
}
