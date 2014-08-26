package uz.micros.jstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.micros.jstore.entity.AppUser;
import uz.micros.jstore.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository rpstr;

    @Transactional(readOnly = true)
    public AppUser getUserByUserName(String username) {
        return rpstr.getUserByUserName(username);
    }
}