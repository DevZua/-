package dev.zua.TodolistApp;

import dev.zua.TodolistApp.event.*;
import dev.zua.TodolistApp.event.update.UpdateMeeting;
import dev.zua.TodolistApp.reader.EventCsvReader;
import dev.zua.TodolistApp.event.Schedule;
import dev.zua.TodolistApp.reader.RawCsvReader;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;


public class TodolistAppApplication {

	public static void main(String[] args) throws IOException {

/*		Schedule schedule = new Schedule();

		EventCsvReader csvReader = new EventCsvReader();
		String meetingCsvPath = "/data/meeting.csv";

		List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
		meetings.forEach(schedule::add);

		for (Meeting meeting : meetings) {

			meeting.print();

			System.out.println("수정 후 데이터");

			try {
				meeting.validateAndUpdate(
						new UpdateMeeting(
								"new title",
								ZonedDateTime.now(),
								ZonedDateTime.now().plusHours(1),
								null,
								"A",
								"new agenda"
						)
				);

				meeting.print();
			} catch (Exception e) {
				System.out.println("An exception occurred: " + e.getMessage());
			}*/


		Schedule schedule = new Schedule();

		EventCsvReader csvReader = new EventCsvReader(new RawCsvReader());
		String meetingCsvPath = "/data/meeting.csv";

		List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
		meetings.forEach(schedule::add);

		Meeting meeting = meetings.get(0);
		meeting.print();

		System.out.println("수정 후 데이터");
		meetings.get(0).validateAndUpdate(
				new UpdateMeeting(
						"new title",
						ZonedDateTime.now(),
						ZonedDateTime.now().plusHours(1),
						null,
						"A",
						"new agenda"
				)
		);
		meeting.print();

	}

		}
