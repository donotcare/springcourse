package ru.otus.quizapp.system.event;

import org.springframework.context.ApplicationEvent;

public class LanguageChangedEvent extends ApplicationEvent {

    public LanguageChangedEvent(Object source) {
        super(source);
    }

    public String getLanguage() {
        return (String) source;
    }
}
