package ru.otus.quizapp.system.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import ru.otus.quizapp.system.event.LanguageChangedEvent;

import java.util.Locale;

public class LocaleMessageSource {
    private final MessageSource messageSource;
    private Locale locale;

    public LocaleMessageSource(Locale locale, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMessage(String value) {
        return messageSource.getMessage(value, null, locale);
    }

    @EventListener
    public void changeLocale(LanguageChangedEvent event) {
        this.locale = Locale.forLanguageTag(event.getLanguage());
    }
}
