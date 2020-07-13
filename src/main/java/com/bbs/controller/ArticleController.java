package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.form.ArticleForm;
import com.bbs.form.CommentForm;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ModelAttribute
    public ArticleForm setUpArticleFrom(){
        return new ArticleForm();
    }

    @ModelAttribute
    public CommentForm setUpCommentFrom(){
        return new CommentForm();
    }

    @RequestMapping("")
    public String index(Model model){
        List<Article> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "bbs";
    }



    /**
     *
     * 記事を投稿するコントローラーのメソッド
     * @return
     */
    @RequestMapping("/insert-article")
    public String insertArticle(Model model ,ArticleForm articleForm){
        Article article = new Article();
        article.setName(articleForm.getName());
        article.setContent(articleForm.getContent());

        articleService.Insert(article);

        return index(model);
    }

    @RequestMapping("/delete-article/{id}")
    public String deleteArticle(
            @PathVariable ("id")
            String id,Model model){

        try {
            articleService.DeleteById(Integer.parseInt(id));
        }catch (Exception e) {

        }
        return index(model);
    }
}
