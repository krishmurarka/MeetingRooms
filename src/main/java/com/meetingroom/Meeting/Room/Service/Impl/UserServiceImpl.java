package com.meetingroom.Meeting.Room.Service.Impl;

import com.meetingroom.Meeting.Room.Entitty.User;
import com.meetingroom.Meeting.Room.Repository.UserRepo;
import com.meetingroom.Meeting.Room.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean singup(User user) {

        try{

            try{

                userRepo.save(user);

            }catch (Exception e){
                log.info("unable to save User Info in Database");
                log.error("Exception: {}", e.getMessage());
                return false;
            }

        }catch (Exception e){
                log.error("UnExpected Error Occured while user Signup");
                log.error("Exception: {}", e.getMessage());
                return false;
        }
        log.info("User Created Successfully");
        return true;
    }

    @Override
    public boolean login(User user) {

//        try{
//            User user1 = userRepo.findByUserNamePass(user.getUsername(), user.getPassword());
//
//            if(user1.)
//        }catch (Exception e){
//
//        }

        return true;
    }
}
