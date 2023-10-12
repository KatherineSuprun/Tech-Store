package com.example.demo.services;
import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j // логирование
//@RequiredArgsConstructor

public class UserService { // логика регистрации

    // инжект
    private final UserRepository userRepository;

    // шифрование пароля
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
