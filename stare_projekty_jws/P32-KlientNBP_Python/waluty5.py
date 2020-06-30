#!/usr/bin/python3

# Powyższy komentarz umożliwia uruchamianie tego programu pod Linuxem bezpośrednio poleceniem
# ./waluty5

import requests

def pobierz_biezace_kursy():
    '''Funkcja pobiera tabelę (słownik) zawierająca bieżące kursy walut'''
    ADRES = 'http://api.nbp.pl/api/exchangerates/tables/A/?format=json'
    dane = requests.get(ADRES).json()
    return dane[0]


def przelicz(kwota, waluta):
    kwotaPln = kwota * waluta['mid']
    kwotaWaluta = kwota / waluta['mid']
    print(f'Waluta {waluta["code"]}: {waluta["currency"]}')
    print(f'Kurs: {waluta["mid"]}')
    print(f'{kwota:.2f} {waluta["code"]} = {kwotaPln:.2f} PLN')
    print(f'{kwota:.2f} PLN = {kwotaWaluta:.2f} {waluta["code"]}')
    print()


def main():
    tabela = pobierz_biezace_kursy()

    print('Numer tabeli:', tabela['no'])
    print('Data tabeli:', tabela['effectiveDate'])

    kod = input('Podaj kod waluty: ')
    kwota = float(input('Podaj kwotę: '))

    for waluta in tabela['rates']:
        if waluta['code'] == kod:
            przelicz(kwota, waluta)


# Taki if sprawdza czy ten plik został uruchomiony jako program
if __name__ == '__main__':
    main()

# Może się zdarzyć, że ten plik jest ładowany do innego programu jako moduł.
# Inny program robi import tego pliku - wówczas powyższy if nie jest spełniony
# i wtedy main() się nie wykona
