package com.sayhi.user.config;
import com.sayhi.user.filter.AuthenticationFilter;
import com.sayhi.user.filter.AuthorizationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            "/auth/signup"
    };


    // @Bean
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	// 	return super.authenticationManagerBean();
	// }

    public WebSecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }


    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(),getApplicationContext()))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}