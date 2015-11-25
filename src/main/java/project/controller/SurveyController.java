package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
import java.util.HashSet;

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

    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public String surveyViewGet(Model model){
        model.addAttribute("survey",new Survey());
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
    public String surveyViewPost(@ModelAttribute("survey") Survey survey){
        surveyService.save(survey);
        return "redirect:/survey/" +survey.getId();
    }

    @RequestMapping(value = "/optionType", method = RequestMethod.GET)
    public String optionTypeChooser(){
        return "surveys/optionType";
    }

    @RequestMapping(value = "/optionType", method = RequestMethod.POST)
    public String optionTypeChooser2(){
        return "surveys/optionType";
    }

    @RequestMapping(value = "/survey/{surveyId}", method = RequestMethod.GET)
    public String surveyGetAuthorFromName(@PathVariable Long surveyId,
                                             Model model) {
        Survey survey = surveyService.findOne(surveyId);
        System.out.println(survey.getQuestions().size());

        model.addAttribute("survey", survey);
        model.addAttribute("questions", new HashSet<Question>(survey.getQuestions()));
        model.addAttribute("question", new Question());

        return "surveys/SurveyEditor";
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}", method = RequestMethod.POST)
    public String SurveyEditorPostQuestion(@PathVariable Long surveyId, @ModelAttribute("question")
                                           Question question) {
        Survey survey = surveyService.findOne(surveyId);
        survey.addQuestion(question);
        surveyService.save(survey);

        //return "redirect:/optionType";
        return "redirect:/survey/"+surveyId;
    }

    @RequestMapping(value = "/survey/surveyedit/delete/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorDeleteQuestion(@PathVariable Long surveyId, @PathVariable Long questionId){
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

        return "surveys/QuestionEditor";
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorPostOption(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           @ModelAttribute("option") Option option) {
        Question question = questionService.findOne(questionId);
        //questionService.delete(question);
        question.addOption(option);
        questionService.save(question);
        //optionService.save(option);
        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }

    @RequestMapping(value = "/survey/surveyedit/delete/{surveyId}/{questionId}/{optionId}", method = RequestMethod.POST)
    public String SurveyEditorDeleteOption(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           @PathVariable Long optionId) {
        Option optionToDelete = optionService.findOne(optionId);
        Question question = questionService.findOne(questionId);
        question.getOptions().remove(optionToDelete);
        questionService.save(question);

        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }

    @RequestMapping(value = "/survey/surveyedit/predoptions/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorPostChosenOptions(@PathVariable Long surveyId, @PathVariable Long questionId) {

        //Question question = questionService.findOne(questionId);
        //questionService.delete(question);
       // System.out.println("im here");
        //questionService.save(question);
        //optionService.save(option);
        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }




}
