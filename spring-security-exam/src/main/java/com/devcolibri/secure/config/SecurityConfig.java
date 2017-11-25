package com.devcolibri.secure.config;

import com.devcolibri.secure.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   // @Autowired
    private UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth)  {
        try {
            auth
                    .userDetailsService(new UserDetailsServiceImpl())
                    .passwordEncoder(getShaPasswordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure(HttpSecurity http)  {
        try {


            http.csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/resources/**", "/**").permitAll()
                    .anyRequest().permitAll()
                    .and();

            http.formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/j_spring_security_check")
                    //  .failureUrl("/login?error")
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                    .permitAll();

            http.logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true);
        }
        catch (Exception e){
            e.printStackTrace();
        };

    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }

}