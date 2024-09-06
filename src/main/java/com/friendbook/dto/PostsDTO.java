package com.friendbook.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PostsDTO {
    private String content;
    private String image;
    private String video;
}
