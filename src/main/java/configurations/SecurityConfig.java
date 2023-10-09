package configurations;

import com.example.demo.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // конфигурация http
        http.authorizeRequests()
                // к каким страничкам возможен доступ для не зареганых пользователей, продукт, последующая инфа о нем..
                .antMatchers("/", "/product/**", "/images/**", "/registration")
                .permitAll()
                .anyRequest().authenticated() // полученный запрос аутентифицируется
                .and()
                .formLogin()// создает простую страницу входа
                .loginPage("/login")// страничка авторизации
                .permitAll()
                .and()
                .rememberMe()// файл cookie «запомнить меня»
                .and()
                .logout()// можно выйти из аккаунта
                .permitAll(); // любой пользователь, независимо от того, прошел ли он проверку подлинности
        // или нет, сможет получить доступ к этой конечной точке без каких-либо проверок авторизации
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) // для аутентификации, как подгружать юзеров
                .passwordEncoder(passwordEncoder());// хэширование паролей в БД от злоумышленников
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
