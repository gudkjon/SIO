package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Option;
import project.persistence.repositories.OptionRepository;
import project.service.OptionService;

import java.util.Collections;
import java.util.List;

@Service
public class OptionServiceImplementation implements OptionService {

    // Instance Variables
    OptionRepository repository;

    // Dependency Injection
    @Autowired
    public OptionServiceImplementation(OptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Option save(Option option) {
        return repository.save(option);
    }

    @Override
    public void delete(Option option) {
        repository.delete(option);
    }

    @Override
    public List<Option> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Option> findAllReverseOrder() {
        // Get all the Postit notes
        List<Option> options = repository.findAll();

        // Reverse the list
        Collections.reverse(options);

        return options;
    }

    @Override
    public Option findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Option> findBySurveyId(Long surveyId) {
        return repository.findBySurveyId(surveyId);
    }

    @Override
    public List<Option> findBySurveyIdAndContainingQuestion(Long surveyId, String containingQuestion) {
        return repository.findBySurveyIdAndContainingQuestion(surveyId, containingQuestion);
    }

    @Override
    public List<Option> findBySurveyIdAndId(Long surveyId, Long id) {
        return repository.findBySurveyIdAndId(surveyId,id);
    }

    @Override
    public List<Option> findBySurveyIdAndQuestionId(Long surveyId, Long questionId) {
        return repository.findBySurveyIdAndQuestionId(surveyId, questionId);
    }
}
