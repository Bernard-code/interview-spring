package com.bernard.interview.service;

import com.bernard.interview.payload.QuestionDTO;

import java.util.List;

public interface QuestionService {
    QuestionDTO createQuestion(QuestionDTO questionDTO);
    List<QuestionDTO> getAllQuestions();
    QuestionDTO getQuestionById(long id);
    QuestionDTO updateQuestion(long id, QuestionDTO QuestionDTO);
    void deleteQuestion(long id);
}
