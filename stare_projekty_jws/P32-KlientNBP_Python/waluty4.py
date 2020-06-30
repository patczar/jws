import requests

print('Podawaj daty w formacie YYYY-MM-DD')
print('Wciśnij enter aby zakończyć')
while True:
    data = input('Podaj datę: ')
    if data == '':
        break

    ADRES=f'http://api.nbp.pl/api/exchangerates/tables/A/{data}?format=json'

    try:
        dane = requests.get(ADRES).json()
        tabela = dane[0]
        print('Numer tabeli:', tabela['no'])
        print('Data tabeli:', tabela['effectiveDate'])

        for waluta in tabela['rates']:
            print(f"{waluta['code']:3} {'('+waluta['currency']+')':38} {waluta['mid']:<8}")
    except Exception:
        print(f'Brak danych dla daty {data}')

print('Do widzenia')
