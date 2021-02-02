package org.progmatic.messenger.configurators;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                .authorizeRequests()
                .antMatchers("/messages", "/messages/{messageId}", "/searchInMessages", "/registrationPage", "/css/**").permitAll()
                .antMatchers("/createNewMessage", "/newMessage").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage").permitAll()
                .loginProcessingUrl("/loginPage")
                .and()
                .logout().permitAll();
    }
}
