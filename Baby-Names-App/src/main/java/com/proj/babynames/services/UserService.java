package com.proj.babynames.services;

import java.util.Optional;

import com.proj.babynames.models.LoginUser;
import com.proj.babynames.models.User;
import com.proj.babynames.repositories.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User register(User newUser, BindingResult result) {

        if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use, please use a different email");
        }

        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "Incorrect password");
        }

        if (result.hasErrors()) {
            return null;
        }

        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);

        return userRepo.save(newUser);
    }

    public User login(LoginUser newLogin, BindingResult result) {

        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if (!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Please enter a valid Email Adress");
            return null;
        }

        User user = potentialUser.get();
        if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "That password does not match the password for this email");
            return null;
        }

        if (result.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }

    public User findUserById(Long id) {

        Optional<User> optUser = userRepo.findById(id);

        if (optUser.isPresent()) {
            return optUser.get();

        } else {

            return null;
        }
    }
}