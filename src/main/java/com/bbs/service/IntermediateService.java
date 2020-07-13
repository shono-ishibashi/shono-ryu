package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.repository.IntermediateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IntermediateService {

    @Autowired
    private IntermediateRepository intermediateRepository;

    /**
     *
     * @return
     */

    public List<Article> intermediateFindAll(){
        /**
         *  新しく詰め直すリストを作成
         */

        List<Article> intermediateList = intermediateRepository.findAll();



        List<Comment> commentList = new ArrayList<>();

        for(Article article : intermediateList){
            commentList.add(article.getComments().get(0));
        }

        Map<Integer,Article> articleMap = new HashMap<>();

        intermediateList.forEach(e->
                articleMap.put(e.getId(), e)
                );



        for(Integer articleId : articleMap.keySet()){
            articleMap.get(articleId).getComments().clear();
            for(Comment comment : commentList){
                if(Objects.equals(articleId, comment.getArticleId())){
                    articleMap.get(articleId).getComments().add(comment);
                }
            }
        }


        return new ArrayList<>(articleMap.values());
    }
}
