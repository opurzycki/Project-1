package com.example.taskapp.config;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//        security.httpBasic().disable();
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;




    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

        @Autowired
        private DataSource dataSource;

        @Autowired
        private PasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    // tutaj są URL wymagające autoryzacji - strefa chroniona
                    // .antMatchers(/url) -> wymaga autoryzacji
                    // .hasAnyAuthority("uprawnienie") -> dla określonego uprawnienia
                    .antMatchers("/admin").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                    // pozostałe URL udostępnij dla każdego
                    .anyRequest().permitAll()
                    .and()
                    .csrf().disable()
                    // formularz logowania
                    .formLogin()
                    // adres formularza logowania
                    .loginPage("/login")
                    // nazwa pola w formularzu dot. loginu
                    .usernameParameter("email")
                    // nazwa pola w formularzu dot. hasła
                    .passwordParameter("password")
                    // adres gdzie przekazywane są parametry formularza
                    .loginProcessingUrl("/login-process")
                    // domyśly URL po poprawnym zalogowaniu
                    .defaultSuccessUrl("/")
                    // domyśly URL po błędnym logowaniu
                    .failureUrl("/errorLogin")
                    .and()
                    // wylogowanie
                    .logout()
                    // adres URL przekierowyjący na wylogowanie
                    .logoutUrl("/logout")
                    // domyśly URL po poprawnym wylogowaniu
                    .logoutSuccessUrl("/");


        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .jdbcAuthentication()
                    // SQL dla logowania użytkownika po adresie email i haśle
                    .usersByUsernameQuery( "SELECT u.email, u.password, u.active FROM User u WHERE u.email = ?")
                    // SQL dla przypisania uprawnień dla zalogowanego użytkownika
                    .authoritiesByUsernameQuery("SELECT u.email, u.role FROM User u WHERE u.email = ?")
                    // wynik logowania
                    .dataSource(dataSource)
                    // szyfrowanie hasła
                    .passwordEncoder(bCryptPasswordEncoder);
        }
    }


