package com.example.demo;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Paczka;

// To jest tradycyjny kontroler webowy (Spring MVC)
// Domyślnie traktuje wyniki metod jak nazwy szablonów, których szuka w src/main/webapp (albo gdzie indziej w zależności od konfiguracji)
// Aby wynik metody stał się treścią odpowiedzi, trzeba użyć @ResponseBody
@Controller
public class HelloKontroler {
	
	// adnotacja @GetMapping / @PostMapping itd dopiero od Spring 5
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		System.out.println("ping");
		return "Hello, teraz jest godzina " + LocalTime.now();
	}
	
	// starsza wersja adnotacji Spring MVC:
	@RequestMapping(path="/daj-stronke", method=RequestMethod.GET)
	public String stronka() {
		// tutaj wskazuję szablon, który ma być użyty do wygenerowania odpowiedzi
		
		return "stronka.html";
	}
	
	@RequestMapping(path="/daj-jsona",
			method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public Paczka dajJsona() {
		return Paczka.utworz("Pozdro ze Spring MVC", "?");
	}
	
}
