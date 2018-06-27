package ru.otus.quizapp.question;

import java.util.List;

public class QuestionService  implements IQuestionService {
    private final QuestionDao dao;

    public QuestionService(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAll() {
        return dao.getAll();
    }
}
