package dev.zua.TodolistApp.event.update;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public abstract class AbstractAuditableEvent {
    private final String title;
    private final ZonedDateTime startAt;
    private final ZonedDateTime endAt;

    protected AbstractAuditableEvent(String title, ZonedDateTime startAt, ZonedDateTime endAt) {
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
