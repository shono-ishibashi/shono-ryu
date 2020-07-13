package com.bbs.form;

import lombok.Data;

@Data
public class CommentForm {
    private Integer id;
    private String name;
    private String content;
    private Integer articleId;
}
