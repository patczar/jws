import requests

ADRES='http://api.nbp.pl/api/exchangerates/tables/A/?format=json'

dane = requests.get(ADRES).json()
tabela = dane[0]

print('Numer tabeli:', tabela['no'])
print('Data tabeli:', tabela['effectiveDate'])

for waluta in tabela['rates']:
    print(f"{waluta['code']:3} {'('+waluta['currency']+')':38} {waluta['mid']:<8}")
