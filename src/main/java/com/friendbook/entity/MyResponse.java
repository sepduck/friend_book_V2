package com.friendbook.model;

import lombok.Data;

@Data
public class MyResponse {
    public int status;
    public String message;
    public Object data;
}
