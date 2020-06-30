package wersja_dom;

public class WypiszDzisiejszeKursy {

	public static void main(String[] args) {
		System.out.println("Startujemy...");
		Tabela tabela = ObslugaNBP.pobierzAktualnaTabele();
		System.out.println(tabela);

		for(Waluta waluta : tabela.getWszystkieWaluty()) {
			System.out.println(waluta);
		}
	}
}
