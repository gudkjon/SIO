package project.service;

import project.persistence.entities.Option;

import java.util.List;

public interface OptionService {

    /**
     * Save a {@link Option}
     * @param option {@link Option} to be saved
     * @return {@link Option} that was saved
     */
    Option save(Option option);

    /**
     * Delete {@link Option}
     * @param option {@link Option} to be deleted
     */
    void delete(Option option);

    /**
     * Get all {@link Option}s
     * @return A list of {@link Option}s
     */
    List<Option> findAll();

    /**
     * Get all {@link Option}s in a reverse order
     * @return A reversed list of {@link Option}s
     */
    List<Option> findAllReverseOrder();

    /**
     * Find a {@link Option} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Option} with {@link Long id}
     */
    Option findOne(Long id);

    /**
     * Find all {@link Option}s with {@link String name}
     * @param containingSurvey {@link String}
     * @return All {@link Option}s with the {@link String containingSurvey} passed
     */
    List<Option> findByContainingSurvey(String containingSurvey);

    List<Option> findByContainingSurveyAndContainingQuestion(String containingSurvey, String containingQuestion);

    List<Option> findByContainingSurveyAndId(String containingSurvey, Long id);

    List<Option> findByContainingSurveyAndQuestionId(String containingSurvey, Long questionId);

}
