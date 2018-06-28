package ru.otus.quizapp.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.quizapp.question.Question;
import ru.otus.quizapp.question.QuestionDao;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private final String filename;

    public CsvQuestionDao(String filename) {
        this.filename = filename;
    }

    public List<Question> getAll() {
        try {
            return parseCsv();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            return Collections.emptyList();
        }
    }

    private List<Question> parseCsv() throws IOException {
        String path = CsvQuestionDao.class.getResource(filename).getFile();
        List<Question> questions = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(new FileReader(path));
        for (CSVRecord record : records) {
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
}
