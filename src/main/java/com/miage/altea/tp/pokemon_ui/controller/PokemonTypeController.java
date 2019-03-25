package com.miage.altea.tp.pokemon_ui.controller;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PokemonTypeController {

    @Autowired
    public PokemonTypeService service;

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        ModelAndView modelAndView = new ModelAndView("pokedex");
        modelAndView.addObject("pokemonTypes", service.listPokemonsTypes());
        return modelAndView;	}

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.service = pokemonTypeService;
    }


}
