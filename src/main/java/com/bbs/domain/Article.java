package com.bbs.domain;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    private Integer id;
    private String name;
    private String content;
    private List<Comment> comments;
}
