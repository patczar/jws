W tym projekcie eksperymentowaliśmy z adnotacjami JAXB.

Polecenie generowania schemy na podstawie klas:
schemagen -cp bin src/ogloszenia/model/OgloszenieSamochodowe.java

Polecenie generowania klas z powrotem na podstawie schemy:
xjc -d src -p wygenerowane1 schema1.xsd

Po dodaniu XML-owych adnotacji do pliku schema2.xsd:
xjc -d src -p wygenerowane2 schema2.xsd

Można też te "adnotacje" umieścić w dodatkowym pliku - tutaj bindings3.xml
Polecenie generowania kals na podstawie schemay i pliku bindings:
xjc -b bindings3.xml -d src -p wygenerowane3 schema3.xsd
