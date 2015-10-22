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
        surveyService.save(survey);

        // Here we get all the Postit Notes (in a reverse order) and add them to the model
        model.addAttribute("surveys", surveyService.findAllReverseOrder());

        // Add a new Postit Note to the model for the form
        // If you look at the form in SurveyCreator.jsp, you can see that we
        // reference this attribute there by the name `survey`.
        model.addAttribute("survey", new Survey());
        //model.addAttribute("survey", new Survey());

        // Return the view
        //return "surveys/SurveyCreator";
        return "redirect:/survey/";
    }

    // Method that returns the correct view for the URL /postit/{name}
    // The {name} part is a Path Variable, and we can reference that in our method
    // parameters as a @PathVariable. This enables us to create dynamic URLs that are
    // based on the data that we have.
    // This method finds all Postit Notes posted by someone with the requested {name}
    // and returns a list with all those Postit Notes.
    @RequestMapping(value = "/survey/{name}", method = RequestMethod.GET)
    public String surveyGetAuthorFromName(@PathVariable String name,
                                             Model model){

        // Get all Postit Notes with this name and add them to the model
        model.addAttribute("survey", surveyService.findByName(name));
        model.addAttribute("questions", questionService.findByContainingSurvey(name));
        model.addAttribute("question", new Question());

        // Add a new Postit Note to the model for the form
        // If you look at the form in SurveyCreator.jsp, you can see that we
        // reference this attribute there by the name `survey`.
        //model.addAttribute("survey", new Survey());

        // Return the view
        return "surveys/SurveyEditor";
    }


    /*@RequestMapping(value = "/survey/{name}", method = RequestMethod.POST)
    public String surveyEditorViewPost(@ModelAttribute("survey") Survey survey,
                                 Model model){

        // Save the Postit Note that we received from the form
        surveyService.save(survey);

        // Here we get all the Postit Notes (in a reverse order) and add them to the model
        model.addAttribute("surveys", surveyService.findAllReverseOrder());

        // Add a new Postit Note to the model for the form
        // If you look at the form in SurveyCreator.jsp, you can see that we
        // reference this attribute there by the name `survey`.
        model.addAttribute("survey", new Survey());
        //model.addAttribute("survey", new Survey());

        // Return the view
        //return "surveys/SurveyCreator";
        return "redirect:/survey/";
    }*/

    @RequestMapping(value = "/survey/surveyedit/{name}", method = RequestMethod.POST)
    public String SurveyEditorPostQuestion(@PathVariable String name, @ModelAttribute("question")
                                           Question question, Model model) {
        question.setContainingSurvey(name);
        questionService.save(question);
        model.addAttribute("questions", questionService.findByContainingSurvey(name));
        model.addAttribute("question", new Question());
        model.addAttribute("survey", surveyService.findByName(name));

        //return "surveys/SurveyEditor";
        return "redirect:/survey/"+name;

    }

    @RequestMapping(value = "/survey/surveyedit/delete/{containingSurvey}/{id}", method = RequestMethod.POST)
    public String SurveyEditorDeleteQuestion(@PathVariable String containingSurvey, @PathVariable Long id,
                                             Model model){

        Question questionToDelete = questionService.findByContainingSurveyAndId(containingSurvey,id).get(0);
        questionService.delete(questionToDelete);
        model.addAttribute("questions", questionService.findByContainingSurvey(containingSurvey));
        model.addAttribute("question", new Question());
        model.addAttribute("options", optionService.findByContainingSurvey(containingSurvey));
        model.addAttribute("survey", surveyService.findByName(containingSurvey));

        //return "surveys/SurveyEditor";
        return "redirect:/survey/"+containingSurvey;
    }

    @RequestMapping(value = "/survey/surveyedit/{containingSurvey}/{questionId}", method = RequestMethod.GET)
    public String SurveyEditorViewQuestion(@PathVariable String containingSurvey, @PathVariable Long questionId,
                                           Model model) {
        model.addAttribute("question", questionService.findByContainingSurveyAndId(containingSurvey, questionId));
        model.addAttribute("option", new Option());
        model.addAttribute("options", optionService.findByContainingSurveyAndQuestionId(containingSurvey, questionId));
        return "surveys/QuestionEditor";
    }

    @RequestMapping(value = "/survey/surveyedit/{containingSurvey}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorViewQuestion(@PathVariable String containingSurvey, @PathVariable Long questionId,
                                           @ModelAttribute("option") Option option, Model model) {
        option.setContainingSurvey(containingSurvey);
        option.setQuestionId(questionId);
        optionService.save(option);
        model.addAttribute("question", questionService.findByContainingSurveyAndId(containingSurvey, questionId));
        model.addAttribute("option", new Option());
        model.addAttribute("options", optionService.findByContainingSurveyAndQuestionId(containingSurvey, questionId));
        //return "surveys/QuestionEditor";
        return "redirect:/survey/surveyedit/"+containingSurvey+"/"+questionId;
    }

    @RequestMapping(value = "/survey/surveyedit/delete/{containingSurvey}/{questionId}/{id}", method = RequestMethod.POST)
    public String SurveyEditorDeleteOption(@PathVariable String containingSurvey, @PathVariable Long questionId,
                                           @PathVariable Long id, Model model) {
        Option optionToDelete = optionService.findOne(id);
        optionService.delete(optionToDelete);
        model.addAttribute("question", questionService.findByContainingSurveyAndId(containingSurvey, questionId));
        model.addAttribute("option", new Option());
        model.addAttribute("options", optionService.findByContainingSurveyAndQuestionId(containingSurvey, questionId));
        //return "surveys/QuestionEditor";
        return "redirect:/survey/surveyedit/"+containingSurvey+"/"+questionId;
    }
}
