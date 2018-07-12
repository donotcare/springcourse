package ru.otus.quizapp.system.shell;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.quizapp.system.event.LanguageChangedEvent;

@ShellComponent
public class ChangeLanguageCommand {
    private final ApplicationEventPublisher eventPublisher;

    public ChangeLanguageCommand(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @ShellMethod("changing language")
    public String change(String lang) {
        eventPublisher.publishEvent(new LanguageChangedEvent(lang));
        return "language changed";
    }
}
