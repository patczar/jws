package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Paczka {
	private final int numer;
	private String napis;
	private final String data;
	private final String czas;
	private final String url;

	private static AtomicInteger licznik = new AtomicInteger();
	
	public Paczka() {
		this(0, null, null, null, null);
	}

	private Paczka(int numer, String napis, String data, String czas, String url) {
		this.numer = numer;
		this.napis = napis;
		this.data = data;
		this.czas = czas;
		this.url = url;
	}
	
	public static Paczka utworz(String napis, String url) {
		int numer = licznik.incrementAndGet();
		String data = LocalDate.now().toString();
		String czas = LocalTime.now().toString();
		return new Paczka(numer, napis, data, czas, url);
	}

	public String getNapis() {
		return napis;
	}

	public void setNapis(String napis) {
		this.napis = napis;
	}

	public int getNumer() {
		return numer;
	}

	public String getData() {
		return data;
	}

	public String getCzas() {
		return czas;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "Paczka [numer=" + numer + ", napis=" + napis + ", data=" + data + ", czas=" + czas + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(czas, data, napis, numer, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paczka other = (Paczka) obj;
		return Objects.equals(czas, other.czas) && Objects.equals(data, other.data)
				&& Objects.equals(napis, other.napis) && numer == other.numer && Objects.equals(url, other.url);
	}

}
