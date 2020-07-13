package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.repository.IntermediateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntermediateService {

    @Autowired
    private IntermediateRepository intermediateRepository;

    public List<Article> intermediateFindAll(){
        Article article = new Article();
        List<Article> intermediateList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();

        List<Article> intermediateList2 = new ArrayList<>();// 新しく詰め直す用

        intermediateList = intermediateRepository.findAll();

        for (int i = 0; i < intermediateList.size(); i++) {

            if (i == 0 ) {
                article.setId(intermediateList.get(i).getId());
                article.setName(intermediateList.get(i).getName());
                article.setContent(intermediateList.get(i).getContent());

                commentList.addAll(intermediateList.get(i).getComments());

            } else if (intermediateList.get(i).getId() != intermediateList.get(i - 1).getId()) {

                article.setComments(commentList);
                intermediateList2.add(article);

                article = new Article();//初期化
                article.setId(intermediateList.get(i).getId());
                article.setName(intermediateList.get(i).getName());
                article.setContent(intermediateList.get(i).getContent());

                commentList = new ArrayList<>();//初期化
                commentList.addAll(intermediateList.get(i).getComments());
                article.setComments(commentList);

            }else {
                commentList.addAll(intermediateList.get(i).getComments());
                article.setComments(commentList);
            }

            if( i == intermediateList.size()-1 ) {
                article.setComments(commentList);
                intermediateList2.add(article);
            }

        }

        return intermediateList2;
    }
}
