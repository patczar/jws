import json
from urllib import request

ADRES='http://api.nbp.pl/api/exchangerates/tables/A/?format=json'
with request.urlopen(ADRES) as response:
    txt = response.read().decode('utf-8')

dane = json.loads(txt)
tabela = dane[0]

print('Numer tabeli:', tabela['no'])
print('Data tabeli:', tabela['effectiveDate'])

for waluta in tabela['rates']:
    print(f"{waluta['code']:3} {'('+waluta['currency']+')':38} {waluta['mid']:<8}")
