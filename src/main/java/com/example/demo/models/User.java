package com.example.demo.models;

import com.example.demo.models.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация уникального значения
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true) // валидация уникального имейла для БД
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "active")
    private boolean active; // пока пользователь не подтвердит аккаунт - он будет неактивным\кидать в бан

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // при удалении юзера удалится всё
    @JoinColumn(name = "image_id")
    @Column(name = "avatar")
    private Image avatar;
    @Column(name = "password", length = 1000) // будет добавлено шифрование пароля
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)  // преобразовать enum в тип String (конвертация)
    private Set<Role> roles = new HashSet<>(); // id юзеров и их роли таблица

    private LocalDateTime dateOfCreated; // дата создания аккаунта

    @CreatedDate
    private void init() { // инициализация даты создания
        dateOfCreated = LocalDateTime.now(); //
    }

    // SPRING SECURITY
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;  //  тип активности юзера
    }
}
