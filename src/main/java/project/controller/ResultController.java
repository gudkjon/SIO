/**
 * Created by Stulli on 19/11/2015.
 */

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
import project.persistence.entities.*;
import project.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashMap;

@Controller
public class ResultController {

// Instance variables
    ResultService resultService;
    UserService userService;
    SurveyService surveyService;

//  Dependency injections
    @Autowired
    public ResultController(ResultService resultService, UserService userService, SurveyService surveyService){
        this.resultService = resultService;
        this.userService = userService;
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/results/view", method = RequestMethod.GET)
    public String returnsResultView(Model model){

    //  Return the view
        model.addAttribute("surveys", surveyService.findAll());

        return "surveys/SurveyResultList";
    }

    @RequestMapping(value = "/results/view/{surveyId}", method = RequestMethod.GET)
    public String returnsResultSurveyView(@PathVariable Long surveyId, Model model){
        //  Return the view
        ArrayList<Question> questions = new ArrayList<Question>(
                new LinkedHashSet<Question>(
                    surveyService.findOne(surveyId).getQuestions()
                )
        );
        System.out.println(questions.get(0).getQuestionText());
        System.out.println(surveyService.findOne(surveyId).getQuestions().get(0).getQuestionText());
        ArrayList<ArrayList<Result>> results = new ArrayList<ArrayList<Result>>();
        for(int i = 0; i < questions.size(); i++) {
            results.add(new ArrayList(questions.get(i).getResults()));
        }
        ArrayList<Long> totalAnswersPerQuestion = new ArrayList<Long>();
        ArrayList<HashMap<String, Integer>> optionPercentages = new ArrayList<HashMap<String, Integer>>();
        for(int i = 0; i < results.size(); i++) {
            ArrayList<Result> currentQuestionResults = results.get(i);
            totalAnswersPerQuestion.add((long)currentQuestionResults.size());
            if(questions.get(i).getType().equals("input")) {
                continue;
            }
            optionPercentages.add(new HashMap<String, Integer>());
            for(int j = 0; j < currentQuestionResults.size(); j++) {
                Result currentResult = currentQuestionResults.get(j);
                ArrayList<SelectedOption> selectedOptionsInCurrentResult = new ArrayList<SelectedOption>(currentResult.getSelectedOptions());
                for(int k = 0; k < selectedOptionsInCurrentResult.size(); k++) {
                    String optionText = selectedOptionsInCurrentResult.get(k).getSelectedOptionText();
                    int currentValue;
                    if (optionPercentages.get(i).containsKey(optionText)) {
                        currentValue = optionPercentages.get(i).get(optionText);
                        optionPercentages.get(i).put(optionText, currentValue + 1);
                    } else {
                        optionPercentages.get(i).put(optionText, 1);
                    }
                }
            }
        }
        model.addAttribute("questions", questions);
        model.addAttribute("optionPercentages", optionPercentages);
        model.addAttribute("totalAnswersPerQuestion", totalAnswersPerQuestion);
        return "surveys/ResultView";
        /*
        HashMap<String, Integer> optionPercentages.get(i).get(j) = new HashMap<String, Integer>();

        for (... record : records) {
            String optionText = record.getCountryCode();
            int curVal;
            if (optionPercentages.get(i).get(j).containsKey(optionText)) {
                curVal = optionPercentages.get(i).get(j).get(optionText);
                optionPercentages.get(i).get(j).put(optionText, curVal + 1);
            } else {
                optionPercentages.get(i).get(j).put(optionText, 1);
            }
        }
        */
    }

    //"/result/view/${survey.getId()}"

    @RequestMapping(value = "/allResults", method = RequestMethod.GET)
    public String returnsAllResultsView(Model model){

    //  Return the view
        return "surveys/AllResultsView";
    }


}
