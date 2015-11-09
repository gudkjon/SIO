package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.Survey;
import project.persistence.entities.Question;
import project.persistence.entities.Option;
import project.service.StringManipulationService;
import project.service.SurveyService;
import project.service.QuestionService;
import project.service.OptionService;
import project.service.ResultService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class SurveyTakingController {

    // Instance Variables
    SurveyService surveyService;
    QuestionService questionService;
    OptionService optionService;
    ResultService resultService;
    UserService userService;
    StringManipulationService stringManipulationService = new StringManipulationService();

    // Dependency Injection
    @Autowired
    public SurveyTakingController(SurveyService surveyService, QuestionService questionService,
                                  OptionService optionService, ResultService resultService, UserService userService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
        this.optionService = optionService;
        this.resultService = resultService;
        this.userService = userService;
    }


    @RequestMapping(value = "/survey/take", method = RequestMethod.GET)
    public String surveyViewGet(Model model){
        model.addAttribute("surveys", surveyService.findAllReverseOrder());

        return "surveys/SurveyList";
    }

    // Method that returns the correct view for the URL /postit
    // This handles the GET request for this URL
    // Notice the `method = RequestMethod.GET` part
    @RequestMapping(value = "/survey/take/{surveyId}", method = RequestMethod.GET)
    public String surveyViewGet(@PathVariable Long surveyId, Model model){
        // Add a new Postit Note to the model for the form
        // If you look at the form in SurveyCreator.jsp, you can see that we
        // reference this attribute there by the name `survey`.
        // Here we get all the Postit Notes (in a reverse order) and add them to the model

        model.addAttribute("survey", surveyService.findOne(surveyId));

        // Return the view
        return "surveys/SurveyTaker";
    }
}
