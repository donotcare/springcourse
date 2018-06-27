import org.junit.jupiter.api.Test;
import ru.otus.quizapp.dao.CsvQuestionDao;
import ru.otus.quizapp.question.Question;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionTest {
    @Test
    void checkAnswer() {
        Question question = Question.of("Test question", Arrays.asList("A", "B"), 1);
        assertTrue(question.checkAnswer("A"), "Checking correct answer");
        assertFalse(question.checkAnswer("B"), "Checking wrong answer");
        assertFalse(question.checkAnswer("C"), "Checking answer not from question");
    }

    @Test
    void csvDaoGetAll(){
        CsvQuestionDao dao = new CsvQuestionDao("/questions.csv");
        List<Question> actual = dao.getAll();
        assertThat(actual).extracting("text").contains("What is the size of long variable?", "Can we have two main methods in a java class?");
    }
}
