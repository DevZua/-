package dev.zua.TodolistApp.event;

public interface Event {
    void print();  // 출력하는 기능은 다 필요함

    boolean support(EventType type);
}
