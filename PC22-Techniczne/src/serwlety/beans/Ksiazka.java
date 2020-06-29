package serwlety.beans;

public class Ksiazka {
    private String tytul = "Effective Java";
    private String autor = "Joshua Bloch";
    private int cena = 40;
    
    public String getTytul() {
        return tytul.toUpperCase();
    }
    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getCena() {
        return cena;
    }
    public void setCena(int cena) {
        this.cena = cena;
    }
    @Override
    public String toString() {
        return String.format("Ksiazka [tytul=%s, autor=%s, cena=%s]", tytul, autor, cena);
    }
}


