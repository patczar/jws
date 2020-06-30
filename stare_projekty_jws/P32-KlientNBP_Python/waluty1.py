import json
from urllib import request

# W tej wersji stosujemy sposób wczytywania JSONa dostepny w bibliotece standardowej
# (bez instalowania czegokolwiek)
ADRES='http://api.nbp.pl/api/exchangerates/tables/A/?format=json'
response = request.urlopen(ADRES)

# odczyt danych w postaci tekstowej
txt = response.read().decode('utf-8')

# zamknięcie połączenia sieciowego
response.close()

print(txt)
print(f'Tekst ma długość {len(txt)}')

# zamiana tekstu na obiekt jsonowy (lista / słownik itd.)
dane = json.loads(txt)
print('Typ 1:', type(dane))
# Struktura danych z NBP jest taka, że dostajemy listę, a w tej liście jest słownik reprezentujący jedną tabelę z notowaniami walut

# to już jest słownik
tabela = dane[0]
print(f'Typ 2:', type(tabela))
print()

print('Numer tabeli:', tabela['no'])
print('Data tabeli:', tabela['effectiveDate'])

# to jest lista
waluty = tabela['rates']
for waluta in waluty:
    print(waluta['code'], waluta['currency'], waluta['mid'])
