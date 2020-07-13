package com.bbs.repository;

import com.bbs.domain.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class CommentRepository {

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {

        Comment comment = new Comment();

        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));

        return comment;

    };






