package com.miage.altea.tp.pokemon_ui.controller;

import com.miage.altea.tp.pokemon_ui.trainers.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TrainersController {

    @Autowired
    private TrainersService service;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        ModelAndView modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("pokemonTrainers", service.listTrainers());
        return modelAndView;	}

    @GetMapping("/trainer")
    public ModelAndView trainers(String trainerName){
        ModelAndView modelAndView = new ModelAndView("trainer");
        modelAndView.addObject("team",service.getTrainer(trainerName));
        return modelAndView;
    }

}
