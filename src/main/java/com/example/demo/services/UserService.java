package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService { // логика регистрации

    // инжект
    private final UserRepository userRepository;
    // шифрование пароля
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) { // создание юзера
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) return false;
        // если почта уже существует - фолс
        user.setActive(true); // установили активность
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER); // указали роль юзера
        log.info("Saving new user with email: {}", email); // логирование
        userRepository.save(user); // сохраняем в БД юзера
        return true;
    }


}
