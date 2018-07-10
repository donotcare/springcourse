package ru.otus.quizapp.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ApplicationSettings {
    private String csvFileName;
    private String quizLang = "en";

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public String getQuizLang() {
        return quizLang;
    }

    public void setQuizLang(String quizLang) {
        this.quizLang = quizLang;
    }
}
