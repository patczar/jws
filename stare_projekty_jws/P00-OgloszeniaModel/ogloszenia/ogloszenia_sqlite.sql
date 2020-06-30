CREATE TABLE sprzedawcy (
  id_sprzedawcy INTEGER PRIMARY KEY,
  nazwa TEXT NOT NULL,
  ulica TEXT,
  kod_pocztowy TEXT,
  miasto TEXT,
  telefon TEXT,
  email TEXT
);

CREATE TABLE ogloszenia (
  id_ogloszenia INTEGER PRIMARY KEY,
  id_sprzedawcy INTEGER REFERENCES sprzedawcy(id_sprzedawcy),
  tytul TEXT,
  data_wystawienia TEXT, -- w SQLite nie ma DATE / TIMESTAMP
  cena REAL, -- w SQLite nie ma NUMBER
  opis TEXT,
  marka TEXT NOT NULL,
  model TEXT NOT NULL,
  generacja TEXT,
  kolor TEXT,
  rocznik INTEGER,
  przebieg INTEGER,
  pojemnosc REAL,
  moc REAL,
  paliwo TEXT
);

BEGIN TRANSACTION;

INSERT INTO sprzedawcy(id_sprzedawcy, nazwa, ulica, kod_pocztowy, miasto, telefon, email)
VALUES (1, 'Komis Bolka', 'Starocmentarna 5', '77-777', 'Wólka', '771231234', NULL);

INSERT INTO sprzedawcy(id_sprzedawcy, nazwa, ulica, kod_pocztowy, miasto, telefon, email)
VALUES (2, 'Auto jak nowe', 'Graniczna 13', '12-345', 'Pruszków', '666444333', 'auto@jaknowe.pl');

INSERT INTO sprzedawcy(id_sprzedawcy, nazwa, ulica, kod_pocztowy, miasto, telefon, email)
VALUES (3, 'Janusz Januszowski', 'Smutna', '56-654', 'Łódź', '987654321', NULL);


INSERT INTO ogloszenia(
     id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)
    VALUES (1, 1, '2017-10-10 20:20:00', 5500, 'Sprzedam Opla', 'Opel Astra II 1.4 jak nowy', 'Opel', 'Astra', 'G', 'srebrny', 2005, 150000, 1.4, 90, 'BENZYNA');

INSERT INTO ogloszenia(
     id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)
    VALUES (2, 2, '2017-10-20 07:21:12', 12000, 'Passat niebity igła', 'Ładny Passat TDI dla chłopaka z polskiej wsi', 'Volkswagen', 'Passat', 'C', 'srebrny', 2003, 179000, 2.0, 125, 'OLEJ');

INSERT INTO ogloszenia(
     id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)
    VALUES (3, 3, '2017-11-03 08:18:00', 3300, 'Złoty Matiz', NULL, 'Dewoo', 'Matiz', NULL, 'złoty', 1999, 123300, 1.0, 75, 'GAZ');

INSERT INTO ogloszenia(
     id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)
    VALUES (4, 1, '2017-10-29 12:12:00', 17000, 'Meganka prosto z Francji', 'Mało jeżdżone, niepalone, opłaca się!', 'Renault', 'Megane', 'II', 'biały', 2009, 130000, 1.9, 130, 'OLEJ');

INSERT INTO ogloszenia(
     id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)
    VALUES (5, 1, '2017-10-27 12:12:00', 99999, 'Mazda szósteczka nówka', 'Prosto z warszawskiej ulicy...', 'Mazda', '6', 'III', 'czerwony', 2015, 8000, 2.0, 150, 'BENZYNA');

INSERT INTO ogloszenia(
     id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)
	VALUES (6, 2, '2017-08-22 08:00:00', 12500, 'Fiacik', NULL, 'Fiat', 'Punto', '2', 'niebieski', 2005, 87000, 1.2, 82, 'GAZ');

INSERT INTO ogloszenia(
     id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)
	VALUES (7, 3, '2017-09-01 21:21:21', 18200, 'Yaris od kobiety', 'Sprzedam używaną Yariskę', 'Toyota', 'Yaris', 'II', 'srebrny', 2009, 113100, 1.4, 100, 'BENZYNA');

COMMIT;
