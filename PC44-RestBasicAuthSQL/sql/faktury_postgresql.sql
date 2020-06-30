DROP TABLE IF EXISTS uzytkowicy;
DROP TABLE IF EXISTS pozycje;
DROP TABLE IF EXISTS faktury;
DROP TABLE IF EXISTS firmy;
DROP TABLE IF EXISTS adresy;
DROP SEQUENCE IF EXISTS seq_adresy;
DROP SEQUENCE IF EXISTS seq_faktury;

CREATE SEQUENCE seq_adresy START WITH 1;
CREATE SEQUENCE seq_faktury START WITH 1;

CREATE TABLE adresy (
	id_adresu INTEGER,
	ulica VARCHAR(200),
	numer VARCHAR(50),
	kod_pocztowy VARCHAR(10),
	miasto VARCHAR(100),
	PRIMARY KEY (id_adresu)
);


CREATE TABLE firmy (
	nip CHAR(10),
	nazwa VARCHAR(100) NOT NULL,
	id_adresu INTEGER NOT NULL,
	PRIMARY KEY (nip),
	FOREIGN KEY (id_adresu) REFERENCES adresy(id_adresu)
);

CREATE TABLE faktury (
	id_faktury INTEGER,
	sprzedawca CHAR(10) NOT NULL,
	kupujacy CHAR(10),
	numer_faktury VARCHAR(100) NOT NULL,
	data_wystawienia DATE NOT NULL,
	PRIMARY KEY (id_faktury),
	UNIQUE (sprzedawca, numer_faktury),
	FOREIGN KEY (sprzedawca) REFERENCES firmy(nip),
	FOREIGN KEY (kupujacy) REFERENCES firmy(nip)
);

CREATE TABLE pozycje (
	id_faktury INTEGER NOT NULL,
	poz INTEGER NOT NULL,
	opis VARCHAR(250),
	cena_netto NUMERIC(12,2) NOT NULL,
	stawka_vat NUMERIC(3,2),
	ilosc INTEGER NOT NULL DEFAULT 1,
	PRIMARY KEY(id_faktury, poz)
);

CREATE TABLE uzytkownicy (
	username VARCHAR(100),
	password VARCHAR(150) NOT NULL,
	imie VARCHAR(100),
	nazwisko VARCHAR(100),
	email VARCHAR(100),
	firma CHAR(10),
	PRIMARY KEY (username),
	FOREIGN KEY (firma) REFERENCES firmy(nip)
);


BEGIN;

INSERT INTO adresy (id_adresu, ulica, numer, kod_pocztowy, miasto)
VALUES (1, 'Marszałkowska', '1', '01-123', 'Warszawa');

INSERT INTO firmy(nip, nazwa, id_adresu)
VALUES ('1111111111', 'Sklep meblowy', 1);

INSERT INTO uzytkownicy (username, password, imie, nazwisko, email, firma)
VALUES ('ala', 'abc123', 'Ala', 'Kowalska', 'ala@example.org', '1111111111');


INSERT INTO adresy (id_adresu, ulica, numer, kod_pocztowy, miasto)
VALUES (2, 'Wołoska', '77', '03-321', 'Warszawa');

INSERT INTO firmy(nip, nazwa, id_adresu)
VALUES ('2222222222', 'Korpo', 2);

INSERT INTO uzytkownicy (username, password, imie, nazwisko, email, firma)
VALUES ('bartek', 'bca231', 'Bartek', 'Borkowski', 'bartek@example.org', '2222222222');


INSERT INTO adresy (id_adresu, ulica, numer, kod_pocztowy, miasto)
VALUES (3, 'Floriańska', '13', '31-021', 'Kraków');

INSERT INTO firmy(nip, nazwa, id_adresu)
VALUES ('1231238899', 'Firma szkoleniowa', 3);

INSERT INTO uzytkownicy (username, password, imie, nazwisko, email, firma)
VALUES ('celina', 'cba321', 'Celina', 'Czajkowska', 'celina@example.org', '1231238899');


INSERT INTO adresy (id_adresu, ulica, numer, kod_pocztowy, miasto)
VALUES (4, 'Chorzowska', '211/25', '40-100', 'Katowice');

INSERT INTO firmy(nip, nazwa, id_adresu)
VALUES ('4444444444', 'Sklep komputerowy', 4);

INSERT INTO uzytkownicy (username, password, imie, nazwisko, email, firma)
VALUES ('dorota', 'dda441', 'Dorota', 'Dziembowska', 'dorota@example.org', '4444444444');


INSERT INTO faktury (id_faktury, sprzedawca, kupujacy, numer_faktury, data_wystawienia)
VALUES (1, '1111111111', '2222222222', '1/2019', '2019-01-01');

INSERT INTO pozycje (id_faktury, poz, opis, cena_netto, stawka_vat, ilosc)
VALUES (1, 1, 'biurko', 500.00, 0.23, 1);

INSERT INTO pozycje (id_faktury, poz, opis, cena_netto, stawka_vat, ilosc)
VALUES (1, 2, 'krzesło', 390.00, 0.23, 2);


INSERT INTO faktury (id_faktury, sprzedawca, kupujacy, numer_faktury, data_wystawienia)
VALUES (2, '1111111111', '1231238899', '2/2019', '2019-10-01');

INSERT INTO pozycje (id_faktury, poz, opis, cena_netto, stawka_vat, ilosc)
VALUES (2, 1, 'tablica', 610.00, 0.08, 2);

INSERT INTO pozycje (id_faktury, poz, opis, cena_netto, stawka_vat, ilosc)
VALUES (2, 2, 'krzesło', 125.99, 0.23, 30);

INSERT INTO pozycje (id_faktury, poz, opis, cena_netto, stawka_vat, ilosc)
VALUES (2, 3, 'kanapa', 1281.00, 0.23, 1);

INSERT INTO faktury (id_faktury, sprzedawca, kupujacy, numer_faktury, data_wystawienia)
VALUES (3, '1111111111', '1231238899', '3/2019', '2019-12-01');


COMMIT;
