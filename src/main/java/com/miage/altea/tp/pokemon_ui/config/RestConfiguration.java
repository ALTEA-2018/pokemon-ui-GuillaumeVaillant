package com.miage.altea.tp.pokemon_ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    RestTemplate trainerApiRestTemplate() {return new RestTemplate();
    }
}
