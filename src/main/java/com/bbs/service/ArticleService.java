package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll(){
        return articleRepository.findAll();
    };

    public void Insert (Article article){
        articleRepository.Insert(article);
    }

    public void DeleteById(int id){
        articleRepository.deleteById(id);
    }
}
