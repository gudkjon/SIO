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
import project.persistence.entities.Survey;
import project.persistence.entities.Question;
import project.persistence.entities.Option;
import project.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ResultController {

// Instance variables
    ResultService resultService;
    UserService userService;

//  Dependency injections
    @Autowired
    public ResultController(ResultService resultService, UserService userService){
        this.resultService = resultService;
        this.userService = userService;
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String returnsResultView(Model model){

    //  Return the view
        return "surveys/ResultView";
    }

    @RequestMapping(value = "/allResults", method = RequestMethod.GET)
    public String returnsAllResultsView(Model model){

    //  Return the view
        return "surveys/AllResultsView";
    }


}
