package com.miage.altea.tp.pokemon_ui.trainers.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.tp.pokemon_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class TrainersServiceImpl implements TrainersService {

    private RestTemplate restTemplate;
    private String trainerServiceUrl;

    @Autowired
    private PokemonTypeService service;

    public List<Trainer> listTrainers() {

        Locale locale = LocaleContextHolder.getLocale();
        HttpHeaders headers = new HttpHeaders();
        headers.setAcceptLanguage(Locale.LanguageRange.parse(locale.toLanguageTag()));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        Trainer[] objects= this.restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class, entity);

        List<PokemonType> pokemonTypes = this.service.listPokemonsTypes();

        if (objects != null) {
            Arrays.stream(objects).forEach(trainer -> this.matchTeam(trainer, pokemonTypes));
            return Arrays.asList(objects);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Trainer getTrainer(String name) {
        return null;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainers.service.url}")
    public void setTrainerServiceUrl(String trainersServiceUrl) {
        this.trainerServiceUrl = trainersServiceUrl;
    }

    public void matchTeam(Trainer trainer, List<PokemonType> pokemonTypes) {
        trainer.setTeams(new ArrayList<>());
        trainer.getTeam()
                .forEach(team -> {
                    PokemonType pokemon = pokemonTypes.stream()
                            .filter(type -> team.getPokemonType() == type.getId())
                            .findFirst().orElseGet(null);

                    if (pokemon != null) {

                        trainer.addPokemonTypeToTeam(pokemon);
                    }
                });
    }

}