import requests


def pobierz_pogode_dla_lokalizacji(loc_id):
    adres = f'https://www.metaweather.com/api/location/{loc_id}'
    dane = requests.get(adres).json()
    return dane['consolidated_weather']

def wypisz_pogode(pogoda):
    ''' Wypisuje informacje o pogodzie dla jednego dnia.
        Parametr pogoda jest pojedynczym elementem listy consolidated_weather,
        czyli słownikiem zawierającym takie pola jak min_temp, humidity, itd...
    '''

    print(f'Pogoda dla dnia {pogoda["applicable_date"]}:')
    print(f'\t{pogoda["weather_state_name"]}')
    print(f'\tTemperatura od {pogoda["min_temp"]:.1f} do {pogoda["max_temp"]:.1f}')
    print()


def main():
    # TODO Dla chętnych: użytkownik wpisuje nazwę miasta, my za pomocą zapytania takiego jak
    # https://www.metaweather.com/api/location/search/?query=Warsaw
    # pobieramy id lokalizacji i stosujemy w dalszej części programu

    loc_id = 523920
    weather = pobierz_pogode_dla_lokalizacji(loc_id)
    #print(weather)
    print('Dzisiejsza pogoda:')
    wypisz_pogode(weather[0])

    print('Prognozy na kolejne dni:')
    for w in weather[1:] :
        wypisz_pogode(w)


if __name__ == '__main__':
    main()
