package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonTypeServiceUrl;

    @Override
    public List<PokemonType> listPokemonsTypes() {

        Locale locale = LocaleContextHolder.getLocale();
        HttpHeaders headers = new HttpHeaders();
        headers.setAcceptLanguage(Locale.LanguageRange.parse(locale.toLanguageTag()));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        PokemonType[] listPokemons = restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types/", PokemonType[].class, entity);

        return Lists.newArrayList(listPokemons);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("http://localhost:8080")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonTypeServiceUrl = pokemonServiceUrl;
    }
}
