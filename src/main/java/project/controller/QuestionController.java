package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Survey;
import project.service.SurveyService;
import project.service.QuestionService;

@Controller
public class QuestionController {

    // Instance Variables
    QuestionService questionService;

    // Dependency Injection
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }



}
