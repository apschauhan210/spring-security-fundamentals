package com.understanding.springsecurity6.config;

import com.understanding.springsecurity6.security.CsrfTokenLoggerFilter;
import com.understanding.springsecurity6.security.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.csrf().disable();
//        http.csrf().ignoringAntMatchers("/csrfdisabled/**");
        http.csrf(c->{
            c.ignoringAntMatchers("/csrfdisabled/**");
            c.csrfTokenRepository(new CustomCsrfTokenRepository());
        });

        http.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class);
    }

}
