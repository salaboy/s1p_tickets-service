package org.spring.cloud.k8s.ticketsservice;

import org.spring.cloud.k8s.ticketsservice.config.TicketsConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class TicketsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketsServiceApplication.class, args);
    }

    @Autowired
    private TicketsConfigurationProperties config;

    @GetMapping()
    public String hello() {
        return "Hello from Tickets";
    }

    @GetMapping(path = "/tickets")
    public int getAvailableTickets() {
        return 1010;
    }



    @GetMapping(path = "/ratings")
    public int getRatings() {
        return 5;
    }

    @Scheduled(fixedDelay = 5000)
    public void timerBasedMessage() {
        System.out.println("The first message is: " + config.getDecorate());

    }


    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }
}
