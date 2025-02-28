package com.projetowebfatec.projetowebfatec2025.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioController {

    @GetMapping("")
    public String HelloWorld() {
        return "No Param defined";
    }

    @GetMapping("{name}")
    public String GetRouteName(@PathVariable Optional<String> name) {
        return name.get();
    }

    @GetMapping("/get-age/{age}")
    public String ReturnAge(@PathVariable Integer age) {
        try {
            if (age < 0) {
                throw new NumberFormatException();
            }

            if (age <= 12) {
                return "Criança";
            }

            if (age <= 18) {
                return "Adolescente";
            }

            if (age <= 60) {
                return "Adulto";
            }

            return "Idoso";

        } catch (NumberFormatException e) {
            return "Erro: O valor de 'age' não é um número válido!";
        }
    }
}
