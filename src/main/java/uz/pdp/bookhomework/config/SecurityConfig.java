package uz.pdp.bookhomework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import uz.pdp.bookhomework.entity.enums.RoleNames;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/","/api/books").hasAnyRole(RoleNames.CLIENT.name(),RoleNames.ADMIN.name(),RoleNames.MODERATOR.name())
                .antMatchers("/api/add","/api/edit","/api/edit/*").hasAnyRole(RoleNames.ADMIN.name(),RoleNames.MODERATOR.name())
                .antMatchers("/api/delete","/api/delete/*").hasRole(RoleNames.ADMIN.name())
                .antMatchers("/add","/add/*","/edit","/edit/*").hasAnyRole(RoleNames.ADMIN.name(),RoleNames.MODERATOR.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails adminUser = User
                .builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(RoleNames.ADMIN.name())
                .build();

        UserDetails moderatorUser = User
                .builder()
                .username("moderator")
                .password(passwordEncoder().encode("moderator"))
                .roles(RoleNames.MODERATOR.name())
                .build();

        UserDetails clientUser = User
                .builder()
                .username("client")
                .password(passwordEncoder().encode("client"))
                .roles(RoleNames.CLIENT.name())
                .build();


        return new InMemoryUserDetailsManager(adminUser,clientUser,moderatorUser);
    }


}
