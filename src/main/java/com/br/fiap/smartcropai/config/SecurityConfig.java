package com.br.fiap.smartcropai.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {
   
    @Autowired
   AuthorizationFilter authorizationFilter;

   @Override
   public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
   }


   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       return http
               .authorizeHttpRequests()
                   .requestMatchers(HttpMethod.POST, "/api/cadastrar").permitAll()
                   .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                   .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                   .requestMatchers(HttpMethod.POST, "/api/clima").permitAll()
                   .requestMatchers(HttpMethod.POST, "/api/solo").permitAll()
                   .anyRequest().authenticated()
               .and()
               .csrf().disable()
               .formLogin().disable()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
       return config.getAuthenticationManager();
   }

   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
}