package com.meetingroom.Meeting.Room.Service.Impl;

import com.meetingroom.Meeting.Room.Entitty.Attendee;
import com.meetingroom.Meeting.Room.Entitty.Meeting;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import com.meetingroom.Meeting.Room.Payload.MeetingDto;
import com.meetingroom.Meeting.Room.Repository.AttendeeRepo;
import com.meetingroom.Meeting.Room.Repository.MeetingRepo;
import com.meetingroom.Meeting.Room.Service.AttendeeService;
import com.meetingroom.Meeting.Room.Service.MeetingService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class MeetingServiceImpl implements MeetingService {

    @Autowired
    AttendeeService attendeeService;

    @Autowired
    MeetingRepo meetingRepo;
    @Autowired
     ModelMapper modelMapper;

    @Override
    public Meeting createMeeting(MeetingDto meetingDTO) {
        Meeting meeting = this.modelMapper.map(meetingDTO,Meeting.class);
        meeting.setId(UUID.randomUUID().toString().substring(0, 10));
        return meetingRepo.save(meeting);
    }
    @Override
    public Meeting getMeeting(String id) {

        Meeting meeting = this.meetingRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Meeting not found"));
        return meeting;
    }

    @Override
    public List<Attendee> getAttendees(String meetingId) {

        Meeting meeting = getMeeting(meetingId);
        return meeting.getAttendees();

    }

    @Override
    public void addAttendee(String meetingId, AttendeeDto attendeeDTO) {
        Meeting meeting = getMeeting(meetingId);

         Attendee attendee = this.modelMapper.map(attendeeDTO,Attendee.class);
         meeting.getAttendees().add(attendee);

         meetingRepo.save(meeting);
    }

    @Override
    public void removeAttendee(String meetingId, Long attendeeId, Long hostId) throws Exception {


        Meeting meeting = getMeeting(meetingId);

        if(meeting.getHostId().equals(hostId)){
            Attendee attendee = attendeeService.getAttendee(attendeeId);

            meeting.getAttendees().remove(attendee);
            attendeeService.deleteAttendee(attendeeId);
            meetingRepo.save(meeting);
        }else{

            throw new Exception("Only the host can remove attendees");
        }
    }

    @Override
    public void transferHost(String meetingId, Long newHostId, Long currentHostId) throws Exception {

            Meeting meeting = getMeeting(meetingId);
            if(meeting.getHostId().equals(currentHostId)){

                Attendee newHost =  meeting.getAttendees().stream()
                        .filter(attendee -> attendee.getId().equals(newHostId))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Attendee not found"));

                meeting.setHostId(newHostId);
                meetingRepo.save(meeting);
            }else{
                throw new Exception("Only the host can change Host");
            }
    }
}
