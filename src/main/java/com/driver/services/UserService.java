package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public void createUser(User user){
        try{
            userRepository3.save(user);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public void deleteUser(int userId){
        if(userRepository3.findById(userId).get()!=null)
            userRepository3.deleteById(userId);
    }

    public void updateUser(User user){
        User user1=userRepository3.findById(user.getId()).get();
        if(user.getUsername()!=null) {
            user1.setUsername(user.getUsername());
        }
        if(user.getPassword()!= null) {
            user1.setPassword(user.getPassword());
        }
        if(user.getFirstName()!=null) {
            user1.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null) {
            user1.setLastName(user.getLastName());
        }
        if(user.getBlogList()!=null) {
            user1.setBlogList(user.getBlogList());
        }

        userRepository3.save(user1);
    }

    public User findUserByUsername(String username){
        return userRepository3.findByUsername(username);
    }
}
