Projekt wielomodułowy pokazujący przede wszystkim możliwości tworzenia klienta REST dla własnego serwera, ale także kilka innych możliwości.
Poza programem szkolenia dodałem tutaj też stronę serwera i klienta w wersji SOAP
także wykonane w technice współdielenia kodu Javy (interfejsu i modelu) między klientem a serwerem.

Opis wg modułów
* ogloszenia-model
  Klasy modelu takie jak OgloszenieSamochodowe.
  Używany przez wszystkie pozostałe moduły.
  
* ogloszenia-db
  Implementacja dostepu do bazy danych (ostatecznie tylko SQLite).
  Używany przez moduły działające na serwerze (soap i rest)
  
* ogloszenia-api
  Definiuje interfejsy będące częścią wspólną strony serwera i strony klienta
  (po zmianach: zarówno w wersji REST, jak i SOAP).
  
* ogloszenia-soap
  Implementacja usługi w wersji SOAP, jak w projekcie 09, z tym że wydzieliłem (do modułu api)
  interfejs ISerwisOgloszeniowy zawierający większość adnotacji JAX-WS.
  Nie ma także handlerów.

* ogloszenia-rest
  Implementacja usługi w wersji REST, podobna do projektu 15 (jedna klasa na katalog zasobów),
  z tym że adnotacje JAX-RS są teraz umieszczone w interfejsach z modułu api.

* ogloszenia-ear
  Projekt EAR zbierający wszystkie moduły, które mają działać na serwerze: ogloszenia-soap i ogloszenia-rest

* ogloszenia-klient-soap
  Klient SOAP, różniący się tym od projektu 11, że tutaj klasy modelu oraz interfejs
  SEI (ISerwisOgloszeniowy) są współdzielone między klientem a serwerem (za pośrednictwem modułu api).
  Nie było etapu generowania klas na podstawie WSDL.

* ogloszenia-klient-rest
  Klient REST, prezentujący kilka sposobów dostępu do serwera, m.in. podejście z obiektem proxy
  - jest to niestandardowe rozszerzenie biblioteki RestEasy (JBoss).
