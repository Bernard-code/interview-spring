package com.bernard.interview.payload;

import lombok.Data;

@Data
public class QuestionDTO {
    private long id;
    private String name;
    private String answer;
    private int position;
    private int category;
}
