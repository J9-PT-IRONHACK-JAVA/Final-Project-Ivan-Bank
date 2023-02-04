package com.ironhack.ivanbank.demo;

import com.ironhack.ivanbank.model.User;
import com.ironhack.ivanbank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("load-data")
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){

        var admin1 = new User("admin",passwordEncoder.encode("admin123"),"ROLE_USER,ROLE_ADMIN");
        userRepository.save(admin1);

        var user1 = new User("user", passwordEncoder.encode("user123"), "ROLE_USER");
        userRepository.save(user1);
    }
}
