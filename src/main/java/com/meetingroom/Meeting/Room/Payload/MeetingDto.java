package com.meetingroom.Meeting.Room.Payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingDto {
    private String id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long hostId;
    private boolean waitingRoomEnabled;
}
