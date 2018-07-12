package ru.otus.quizapp.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.event.EventListener;
import ru.otus.quizapp.question.Question;
import ru.otus.quizapp.question.QuestionDao;
import ru.otus.quizapp.system.event.LanguageChangedEvent;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private String filename;
    private String lang;

    public CsvQuestionDao(String filename, String lang) {
        this.filename = filename;
        this.lang = lang;
    }

    public List<Question> getAll() {
        try {
            return parseCsv();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<Question> parseCsv() throws IOException {
        String path = CsvQuestionDao.class.getResource(getFilePath()).getFile();
        List<Question> questions = new ArrayList<>();
        for (CSVRecord record : CSVFormat.RFC4180.withFirstRecordAsHeader().parse(new FileReader(path))) {
            String question = record.get("Question");
            int correct = Integer.parseInt(record.get("Correct"));
            List<String> answers = new ArrayList<>();
            int i = 0;
            while (record.isSet(String.valueOf(++i))) {
                answers.add(record.get(String.valueOf(i)));
            }
            questions.add(Question.of(question, answers, correct));
        }

        return questions;
    }

    private String getFilePath() {
        return String.format("/%s_%s.csv", filename, lang);
    }

    @EventListener
    public void changeLanguage(LanguageChangedEvent event) {
        lang = event.getLanguage();
    }
}
