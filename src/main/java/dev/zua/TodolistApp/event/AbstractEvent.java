package dev.zua.TodolistApp.event;

import dev.zua.TodolistApp.event.update.AbstractAuditableEvent;
import dev.zua.TodolistApp.exception.InvalidEventException;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.ZonedDateTime;

@Getter
@Setter
public abstract class AbstractEvent implements Event {

    private final int id;
    private String title;

    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private Duration duration; // entAt - startAt

    private final ZonedDateTime createAt;
    private ZonedDateTime modifiedAt;

    private boolean deletedYn;

    protected AbstractEvent(int id, String title,
                            ZonedDateTime startAt, ZonedDateTime endAt) {

        // startAt, endAt 순서가 헷갈리지 않도록 미리 방지
        if (startAt.isAfter(endAt)) {
            throw new InvalidEventException(
                    String.format("시작일은 종료일보다 이전이어야 합니다. 시작일=%s, 종료일=%s", startAt, endAt)
            );
        }

        this.id = id;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = Duration.between(startAt, endAt);

        ZonedDateTime now = ZonedDateTime.now();
        this.createAt = now;
        this.modifiedAt = now;

        this.deletedYn = false;  // 처음 객체를 생성할 때는 삭제가 안됐으니까 false
    }

    public void validateAndUpdate(AbstractAuditableEvent update) {
        if (deletedYn == true) {  // 삭제됐으면 수정 못하게
            throw new RuntimeException("이미 삭제된 이벤트는 수정할 수 없습니다.");
        }

        defaultupdate(update);
        update(update);
    }

    private void defaultupdate(AbstractAuditableEvent update) {
        this.title = update.getTitle();
        this.startAt = update.getStartAt();
        this.endAt = update.getEndAt();
        this.duration = Duration.between(this.startAt, this.endAt);
        this.modifiedAt = ZonedDateTime.now();
    }

    protected abstract void update(AbstractAuditableEvent update);
}
