package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Paczka;

// @RestController powoduje, że Spring jako domyślne ustawienie dla metod przyjmie zwracanie treści,
// a nie nazwy szablonu, jak było w @Controller.
// Inaczej mówiąc: nie trzeba pisać @ResponseBody w każdej metodzie.

@RestController
@RequestMapping(path="/paczka", produces = {"application/xml", "application/json"})
public class RestKontroler {

	@GetMapping
	public Paczka get(@RequestParam(value="txt", defaultValue="domyślny tekst") String napis) {
		//String url = uriInfo.getAbsolutePath().toString();
		String url = "/???";
		
		return Paczka.utworz(napis, url);
	}
	
	@GetMapping("/{parametr}")
	public Paczka getPath(@PathVariable("parametr") String tekst) {
		return Paczka.utworz(tekst, "podzasob");
	}
	
	
}
