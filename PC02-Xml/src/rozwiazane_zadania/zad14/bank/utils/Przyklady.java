package rozwiazane_zadania.zad14.bank.utils;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import rozwiazane_zadania.zad14.bank.model.*;

/**
 * @author Patryk Czarnik
 * Czesc przykladu "Bank" do szkolenia z JAXB / WebSerwisow.
 * 
 * Klasa do generowania przykladowych danych.
 */
public class Przyklady {

	public static Bank przykladowyBank() {
		Osoba osoba = przykladowaOsoba();
		Konto konto1 = new Konto("12-13", 100, osoba, null);
		Konto konto2 = new Konto("XXX", 150, osoba, null);
		List<Konto> konta = new LinkedList<Konto>();
		konta.add(konto1);
		konta.add(konto2);
		Bank bank = new Bank();
		bank.setKonta(konta);
		bank.setNazwa("Wiejska kasa pozyczkowa");
		return bank;
	}

	public static Osoba przykladowaOsoba() {
		Adres adr1 = new Adres("Ciekawa", "25a", "5", "12-345", "Warszawa", "Polska");		
		Osoba osoba = new Osoba("Kowalski", "Janek", Calendar.getInstance(), Plec.MEZCZYZNA, adr1, null);	
		return osoba;
	}
}
