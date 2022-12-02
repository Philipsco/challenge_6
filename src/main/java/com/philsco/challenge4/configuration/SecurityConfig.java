package com.philsco.challenge4.configuration;

import com.philsco.challenge4.enumeration.ERole;
import com.philsco.challenge4.module.users.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/cinema/auth/**").permitAll()

                .antMatchers("/api/v1/cinema/films/admin/**").hasAuthority(ERole.ADMIN.name())
                .antMatchers("/api/v1/cinema/users/admin/**").hasAuthority(ERole.ADMIN.name())

                .antMatchers("/api/v1/cinema/films/customer/**").hasAuthority(ERole.CUSTOMER.name())
                .antMatchers("/api/v1/cinema/users/customer/**").hasAuthority(ERole.CUSTOMER.name())
                .antMatchers("/api/v1/cinema/invoice/**").hasAuthority(ERole.CUSTOMER.name())

                .antMatchers("/api/v1/cinema/films/public/**").hasAnyAuthority(ERole.ADMIN.name(), ERole.CUSTOMER.name())
                .antMatchers("/api/v1/cinema/users/public/**").hasAnyAuthority(ERole.ADMIN.name(), ERole.CUSTOMER.name())

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
