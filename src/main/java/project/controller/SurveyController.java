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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SurveyController {

    // Instance Variables
    SurveyService surveyService;
    QuestionService questionService;
    OptionService optionService;
    StringManipulationService stringManipulationService = new StringManipulationService();

    // Dependency Injection
    @Autowired
    public SurveyController(SurveyService surveyService, QuestionService questionService, OptionService optionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
        this.optionService = optionService;
    }

    // Method that returns the correct view for the URL /postit
    // This handles the GET request for this URL
    // Notice the `method = RequestMethod.GET` part
    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public String surveyViewGet(Model model){

        // Add a new Postit Note to the model for the form
        // If you look at the form in SurveyCreator.jsp, you can see that we
        // reference this attribute there by the name `survey`.
        model.addAttribute("survey",new Survey());

        // Here we get all the Postit Notes (in a reverse order) and add them to the model
        model.addAttribute("surveys", surveyService.findAllReverseOrder());

        // Return the view
        return "surveys/SurveyCreator";
    }

    // Method that receives the POST request on the URL /postit
    // and receives the ModelAttribute("survey")
    // That attribute is the attribute that is mapped to the form, so here
    // we can save the postit note because we get the data that was entered
    // into the form.
    // Notice the `method = RequestMethod.POST` part
    @RequestMapping(value = "/survey", method = RequestMethod.POST)
    public String surveyViewPost(@ModelAttribute("survey") Survey survey,
                                     Model model){

        // Save the Postit Note that we received from the form
        //survey.setLinkText(stringManipulationService.convertsSpecialCharactersToEncoding(survey.getName()));
        surveyService.save(survey);

        // Here we get all the Postit Notes (in a reverse order) and add them to the model
        model.addAttribute("surveys", surveyService.findAllReverseOrder());

        // Add a new Postit Note to the model for the form
        // If you look at the form in SurveyCreator.jsp, you can see that we
        // reference this attribute there by the name `survey`.
        model.addAttribute("survey", new Survey());
        return "redirect:/survey/";
    }

    @RequestMapping(value = "/survey/{surveyId}", method = RequestMethod.GET)
    public String surveyGetAuthorFromName(@PathVariable Long surveyId,
                                             Model model){
        model.addAttribute("survey", surveyService.findOne(surveyId));
        model.addAttribute("question", new Question());

        return "surveys/SurveyEditor";
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}", method = RequestMethod.POST)
    public String SurveyEditorPostQuestion(@PathVariable Long surveyId, @ModelAttribute("question")
                                           Question question, Model model) {
        Survey survey = surveyService.findOne(surveyId);
        survey.addQuestion(question);
        surveyService.save(survey);

        return "redirect:/survey/"+surveyId;

    }

    @RequestMapping(value = "/survey/surveyedit/delete/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorDeleteQuestion(@PathVariable Long surveyId, @PathVariable Long questionId,
                                             Model model){

        Question questionToDelete = questionService.findOne(questionId);
        Survey survey = surveyService.findOne(surveyId);
        survey.getQuestions().remove(questionToDelete);
        surveyService.save(survey);

        return "redirect:/survey/"+surveyId;
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}/{questionId}", method = RequestMethod.GET)
    public String SurveyEditorViewQuestion(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           Model model) {
        Question question = questionService.findOne(questionId);
        model.addAttribute("question", question);
        model.addAttribute("option", new Option());
        model.addAttribute("options", question.getOptions());
        return "surveys/QuestionEditor";
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorPostOption(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           @ModelAttribute("option") Option option, Model model) {
        Question question = questionService.findOne(questionId);
        question.addOption(option);
        optionService.save(option);
        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }

    @RequestMapping(value = "/survey/surveyedit/delete/{surveyId}/{questionId}/{optionId}", method = RequestMethod.POST)
    public String SurveyEditorDeleteOption(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           @PathVariable Long optionId, Model model) {
        Option optionToDelete = optionService.findOne(optionId);
        Question question = questionService.findOne(questionId);
        question.getOptions().remove(optionToDelete);
        questionService.save(question);
        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }
}
