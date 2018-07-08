package ru.otus.quizapp.system.i18n;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class LocaleMessageSource {
    private final MessageSource messageSource;
    private final Locale locale;

    public LocaleMessageSource(Locale locale, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMessage(String value) {
        return messageSource.getMessage(value, null, locale);
    }
}
