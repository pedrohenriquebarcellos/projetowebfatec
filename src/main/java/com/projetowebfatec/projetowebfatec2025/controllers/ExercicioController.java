package com.projetowebfatec.projetowebfatec2025.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioController {

    private static final Double LOW = 18.5;
    private static final Double NORMAl = 24.9;
    private static final Double ABOVENORMAL = 29.9;
    private static final Double OBESITY1 = 34.9;
    private static final Double OBESITY2 = 39.9;
    private static final Double OBESITY3 = 40.0;

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

    /**
     * Check if the received number is Odd or Even
     * @param number
     * @return String
     */
    @GetMapping("check-number/{number}")
    public String CheckOddEvenNumber(@PathVariable String number) {
        try {
            if (!IsNumeric(number)) {
                throw new NumberFormatException();
            }

            if (Integer.parseInt(number) % 2 == 0) {
                return "Esse número é Par";
            }

            return "O número é impar";
        } catch (Exception e) {
            return "Precisa ser um número";
        }
    }

    /**
     * Verify if the param is a Number
     * @param value
     * @return Boolean
     */
    public Boolean IsNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Get IMC using Weight and Height
     * @param weight
     * @param height
     * @return String
     */
    @GetMapping("get-imc/{weight}/{height}")
    public String GetIMC(@PathVariable String weight, @PathVariable String height) {
        try {
            if (!IsNumeric(weight) && !IsNumeric(height)) {
                throw new NumberFormatException();
            }

            if (Double.parseDouble(weight) == 0|| Double.parseDouble(height) == 0) {
                throw new NumberFormatException();
            }

            Double imc = Double.parseDouble(weight) / Math.pow(Double.parseDouble(height), 2);

            if (imc < LOW ) {
                return "Below weight";
            } 

            if (imc <= NORMAl) {
                return "Normal weight";
            }  

            if (imc <= ABOVENORMAL) {
                return "Above normal weight";
            }

            if (imc <= OBESITY1) {
                return "Obesity 1 weight";
            }

            if (imc <= OBESITY2) {
                return "Obesity 2 weight";
            }

            return "Obesity 3 weight";

        } catch (NumberFormatException e) {
            return "Os valores passados são inválidos";
        }
    }
}
