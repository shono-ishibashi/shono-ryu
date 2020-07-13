package com.bbs.repository;

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


@Repository
public class CommentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {

        Comment comment = new Comment();

        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;

    };

    public List<Comment> findByArticleId(int articleId) {

        List<Comment> commentList = new ArrayList<>();

        String findIdSql = "SELECT * FROM comments WHERE article_id = :id ORDER BY id DESC";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", articleId);

        commentList = template.query(findIdSql, param, COMMENT_ROW_MAPPER);

        return commentList;
    }

    public void Insert(Comment comment) {

        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);

        String insSql = "INSERT INTO comments (name, content, article_id ) VALUES (:name , :content, :articleId) ";

        template.update(insSql, param);

    }

    public void deleteById(int articleId) {

        String delSql = "DELETE FROM comments WHERE article_id = :id ";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", articleId);

        template.update(delSql, param);

    }

}




