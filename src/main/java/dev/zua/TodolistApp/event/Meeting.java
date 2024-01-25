package dev.zua.TodolistApp.event;

import dev.zua.TodolistApp.event.update.AbstractAuditableEvent;
import dev.zua.TodolistApp.event.update.UpdateMeeting;

import java.time.ZonedDateTime;
import java.util.Set;

public class Meeting extends AbstractEvent{

    private Set<String> participants;
    private String meetingRoom;
    private String agenda;

    public Meeting(int id, String title,
                   ZonedDateTime startAt, ZonedDateTime endAt,
                   Set<String> participants, String meetingRoom, String agenda) {
        super(id, title, startAt, endAt);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    @Override
    public void print() {
        System.out.printf("[회의] %s : %s%n", getTitle(), agenda);

    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.MEETING;  // 전달받은 타입이 MEETING과 동일한지 볼것.
    }

    public void update() {

    }

    @Override
    protected void update(AbstractAuditableEvent update) {
        UpdateMeeting meetingUpdate = (UpdateMeeting) update;

        this.participants = meetingUpdate.getParticipants();
        this.meetingRoom = meetingUpdate.getMeetingRoom();
        this.agenda = meetingUpdate.getAgenda();

    }
}
