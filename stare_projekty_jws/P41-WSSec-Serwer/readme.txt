Usługa zabezpieczona za pomocą WS-Security, polityka "encrypt and sign" w oparciu o klucze asymetryczne.
Ta konfiguracja jest właściwa dla serwerów z grupy JBoss, na innych serwerach szczegóły będą się różnić.

Kroki zabezpieczania aplikacji SOAP:

Przygotowania:
 * Zwykła aplikacja jak P06-OgloszeniaSoap

 * Wygenerowanie (lub w innym scenariuszu ręczne napisanie) WSDL dla tej usługi
   i przekonfigurowanie tak, aby klasa z implementacją (SerwisOgloszeniowy) wskazywała istniejący plik WSDL.
   Od tej pory serwer nie generuje WSDL automatycznie, tylko używa naszego.
   
 Właściwa konfiguracja WS Security:
 * pom.xml - dodatkowe biblioteki oraz wpis w MANIFEST
 * src/webapp/WEB-INF/wsdl/SerwisOgloszeniowy.wsdl
    - ręcznie dopisana (no dobrze, skopiowana z przykładu...) konfiguracja: reguły polityki (koniec pliku) i odwołanie do niej w binding
 * src/webapp/WEB-INF/jaxws-endpoint-config.xml
    - ustawienia rozumiane przez JBossWS
 * src/main/resources - plik z kluczami oraz propertiesy
 * klasa SerwisOgloszeniowy - adnotacje odwołujące się do WSDL i powyższego pliku z konfiguracją JBoss
 * klasa PasswordCallback
 
Polecenia generowania kluczy - patrz plik generowanie_kluczy.txt
 

Ustawienia klienta:
 * klasy wygenerowane poleceniem
 wsimport -keep -s src/main/java -d target/classes -p ogloszenia.generated "http://localhost:8080/P41-WSSec-Serwer-1.0/SerwisOgloszeniowyService?wsdl"
 
 * pom.xml - wiele bibliotek
 * src/main/resources/META-INF - plik z kluczami oraz propertiesy;
 * klasa PasswordCallback
 * oraz odpowiedni kod w programach łączących się z serwerem: Klient1 i Klient2.
 
 
 