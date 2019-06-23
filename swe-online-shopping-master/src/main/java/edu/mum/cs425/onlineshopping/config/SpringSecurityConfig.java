package edu.mum.cs425.onlineshopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@DependsOn("passwordEncoder")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService appUserDetailsService;
 
	private static final String MD5KEY = "tek.me";
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;


    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
        
        .headers()
        .frameOptions().sameOrigin()
        .and()
        .authorizeRequests()
               .antMatchers("/user/**","/logo/**","/css/**", "/js/**", "/fonts/**", "/index","/register").permitAll()
        .antMatchers("/", "/register","/login","/product/list","/uploads/**","/search").permitAll()
        .antMatchers("/admin/**").hasRole("MANAGER")
        .anyRequest().authenticated()
        .and()
 
        
        
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/product/list")
        .failureUrl("/login?error")
        .permitAll()
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll()
        .and()
        .exceptionHandling();
       
       
       
       
       
       
        
//                .antMatchers("/order/finish/**").access("hasAnyRole('EMPLOYEE', 'MANAGER')")
//                .antMatchers("/seller/product/new").access("hasAnyRole('MANAGER')")
//                .antMatchers("/seller/**/delete").access("hasAnyRole( 'MANAGER')")
//                .antMatchers("/seller/**").access("hasAnyRole('EMPLOYEE', 'MANAGER')")
//                // Customer
//                .antMatchers(HttpMethod.POST,"/cart/checkout/**").access("hasAnyRole('CUSTOMER')")
//                .antMatchers("/cart/**").access("!hasAnyRole('EMPLOYEE', 'MANAGER')")
//
//                .antMatchers("/order/**").authenticated()
//                .antMatchers("/profiles/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .loginProcessingUrl("/login") // Submit URL
//                .failureUrl("/login?error")//
//                .usernameParameter("email")//
//                .passwordParameter("password")
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
//                .and()
//                .rememberMe().key(MD5KEY)
//                .and()
//                .exceptionHandling().accessDeniedPage("/403");

    }


}
