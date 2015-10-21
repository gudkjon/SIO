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
    public List<Option> findByContainingSurvey(String containingSurvey) {
        return repository.findByContainingSurvey(containingSurvey);
    }

    @Override
    public List<Option> findByContainingSurveyAndContainingQuestion(String containingSurvey, String containingQuestion) {
        return repository.findByContainingSurveyAndContainingQuestion(containingSurvey, containingQuestion);
    }

    @Override
    public List<Option> findByContainingSurveyAndId(String containingSurvey, Long id) {
        return repository.findByContainingSurveyAndId(containingSurvey,id);
    }

    @Override
    public List<Option> findByContainingSurveyAndQuestionId(String containingSurvey, Long questionId) {
        return repository.findByContainingSurveyAndQuestionId(containingSurvey, questionId);
    }
}
