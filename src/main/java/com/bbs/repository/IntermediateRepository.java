package com.bbs.repository;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * articles,commentsテーブルを操作するリポジトリ.
 *
 * @author ryukanjiro
 *
 */


@Repository
public class IntermediateRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Articleオブジェクトを生成するローマッパー
     */

    private static final RowMapper<Article> INTERMEDIATE_ROW_MAPPER = (rs, i)-> {

        Article article = new Article();

        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));

        List<Comment> commentList = new ArrayList<>();

        Comment comment = new Comment();

        comment.setId(rs.getInt("com_id"));
        comment.setName(rs.getString("com_name"));
        comment.setContent(rs.getString("com_content"));
        comment.setArticleId(rs.getInt("article_id"));

        commentList.add(comment);

        article.setComments(commentList);

        return article;
    };


    /**
     *
     * @return
     */

    public List<Article> findAll() {

        List<Article> intermediateList = new ArrayList<>();

        String findAllSql = "SELECT articles.id AS id , articles.name AS name,"
                + "articles.content AS content, comments.id AS com_id,"
                + "comments.name AS com_name, comments.content AS com_content,"
                + "comments.article_id FROM articles LEFT OUTER JOIN comments ON articles.id = comments.article_id "
                + " ORDER BY articles.id DESC , comments.id DESC ";

        intermediateList = template.query(findAllSql, INTERMEDIATE_ROW_MAPPER);

        return intermediateList;
    }

    /**
     * 記事内容をインサート
     * @param article 記事情報
     */

    public void ArticleInsert(Article article) {

        String insSql = "INSERT INTO articles (name, content) VALUES (:name , :content) ";

        SqlParameterSource param = new BeanPropertySqlParameterSource(article);

        template.update(insSql, param);

    }

    /**
     * 記事内容をデリート
     * @param id 記事情報のid
     */

    public void ArticleDeleteById(int id) {

        String delSql = "DELETE FROM articles WHERE id = :id ";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        template.update(delSql, param);

    }

    /**
     * コメント内容をインサート
     * @param comment
     */

    public void CommentsInsert(Comment comment) {

        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);

        String insSql = "INSERT INTO comments (name, content, article_id ) VALUES (:name , :content, :articleId) ";

        template.update(insSql, param);

    }


    /**
     * 記事内容をデリート
     * @param articleId
     */

    public void CommentsDeleteById(int articleId) {

        String delSql = "DELETE FROM comments WHERE article_id = :id ";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", articleId);

        template.update(delSql, param);

    }

}
