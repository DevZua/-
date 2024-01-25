package dev.zua.TodolistApp;

import dev.zua.TodolistApp.event.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class TodolistAppApplication {

	public static void main(String[] args) {

		Schedule schedule = new Schedule();
		HashSet<String> participants = new HashSet<>();
		participants.add("user1");

		Meeting meeting1 = new Meeting(
				1, "meeting1",
				ZonedDateTime.now(), ZonedDateTime.now().plusHours(1),
				participants, "meetingRoomA", "스터디"
		);

		schedule.add(meeting1);


		Todo todo1 = new Todo(
				2, "todo1",
				ZonedDateTime.now(), ZonedDateTime.now().plusHours(2),
				"할 일 적기"
		);
		schedule.add(todo1);



		schedule.printAll();

	}
}
