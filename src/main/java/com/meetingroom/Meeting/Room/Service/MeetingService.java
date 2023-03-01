package com.meetingroom.Meeting.Room.Service;


import com.meetingroom.Meeting.Room.Entitty.Attendee;
import com.meetingroom.Meeting.Room.Entitty.Meeting;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import com.meetingroom.Meeting.Room.Payload.MeetingDto;

import java.util.List;

public interface MeetingService {

    Meeting createMeeting(MeetingDto meetingDTO);
    Meeting getMeeting(String id);
    List<Attendee> getAttendees(String meetingId);
    void addAttendee(String meetingId, AttendeeDto attendeeDTO);
    void removeAttendee(String meetingId, Long attendeeId, Long hostId) throws Exception;
    void transferHost(String meetingId, Long newHostId, Long currentHostId) throws Exception;



}
