package com.bernard.interview.service.serviceImpl;

import com.bernard.interview.entity.Question;
import com.bernard.interview.payload.QuestionDTO;
import com.bernard.interview.repository.QuestionRepository;
import com.bernard.interview.service.QuestionService;
import com.bernard.interview.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;
    private ModelMapper modelMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
//        Question question = mapDTOToEntity(questionDTO);
        Question question = new Question();
        question.setName(questionDTO.getName());
        question.setAnswer(questionDTO.getAnswer());
        question.setPosition(questionDTO.getPosition());
        question.setCategory(questionDTO.getCategory());

        Question newQuestion = questionRepository.save(question);
        return mapEntityToDTO(newQuestion);
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public QuestionDTO getQuestionById(long id) {
        return mapEntityToDTO(getRawQuestionById(id));
    }

    @Override
    public QuestionDTO updateQuestion(long id, QuestionDTO questionDTO) {
        Question question = getRawQuestionById(id);
        question.setName(questionDTO.getName());
        question.setAnswer(questionDTO.getAnswer());
        question.setPosition(questionDTO.getPosition());
        question.setCategory(questionDTO.getCategory());
        Question updateQuestion = questionRepository.save(question);
        return mapEntityToDTO(updateQuestion);
    }

    @Override
    public void deleteQuestion(long id) {
        questionRepository.delete(getRawQuestionById(id));
    }

    private QuestionDTO mapEntityToDTO(Question question) {
        return modelMapper.map(question, QuestionDTO.class);
    }

    private Question mapDTOToEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }

    private Question getRawQuestionById(long id) {
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }
}
