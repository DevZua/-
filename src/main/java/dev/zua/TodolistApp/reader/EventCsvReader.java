package dev.zua.TodolistApp.reader;

import com.opencsv.CSVReader;
import dev.zua.TodolistApp.event.Meeting;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// CSV 파일을 읽어서 JAVA로 변환하여 반환하는 기능
public class EventCsvReader {

    private final RawCsvReader rawCsvReader;

    public EventCsvReader(RawCsvReader rawCsvReader) {
        this.rawCsvReader = rawCsvReader;
    }
    public List<Meeting> readMeetings(String path) throws IOException {
        List<Meeting> result = new ArrayList<>();

        // 데이터를 읽는 부분
        List<String[]> read = rawCsvReader.readAll(path);
        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) {
                continue;
            }

            String[] each = read.get(i);
            int id;
            try {
                id = Integer.parseInt(each[0]); // id
            } catch(NumberFormatException e) {
                System.out.println("Invalid input in CSV file: " + each[0]);
                continue;
            }
            result.add(
                    new Meeting(
                            id, // id
                            each[2], // title
                            ZonedDateTime.of(LocalDateTime.parse(each[6], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul")),
                            ZonedDateTime.of(LocalDateTime.parse(each[7], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul")),
                            new HashSet<>(Arrays.asList(each[3].split(","))),
                            each[4], // meetingRoom
                            each[5] // agenda
                    )
            );
        }

        return result;
    }

    private static boolean skipHeader(int i) {
        return i == 0;
    }

}
