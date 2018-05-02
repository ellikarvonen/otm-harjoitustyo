# Arkkitehtuurikuvaus
## Rakenne
Luokkakaavio

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/opinnot_luokkakaavio.png)

Pakkauskaavio

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/pakkauskaavio.png)

## Käyttöliittymä
Käyttöliittymässä on seuraavat näkymät:

..* Etusivu


## Päätoiminnallisuudet

### Kurssin lisääminen
Kurssin lisäämiseen tarkoitetulla sivustolla uuden kurssin lisäys toimii seuraavasti käyttäjän painaessa addButton nappia.
![alt taxt](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/sekvenssi_kurssinlisays.png)

Käyttäjä kirjoittaa kenttiin kurssin nimen ja opintopisteiden määrän sekä valitsee valikosta arvosanatavoitteensa. Painaessa "Lisää" (buttonAdd) -nappia tapahtumankäsittelijä kutsuu StudyService luokan saveCourseAndGoalGrade metodia. Metodiin annetaan parametreiksi käyttäjän syöttämät kurssin nimi ja opintopistemäärä, sekä käyttäjän valitsema arvosana. 

Metodi kutsuu edelleen metodia saveCourse, jossa parametreinä on kurssin nimi ja opintopistemäärä. Metodi tarkistaa, että samannimistä kurssia ei ole olemassa, opintopisteet voidaan muuntaa Integer muotoon, kurssin nimi ei ole liian pitkä (yli 200 merkkiä) ja kurssin nimi ei ole tyhjä. Jos ehdot täyttyvät, luodaan uusi kurssi ja lisätään se tietokantaan CourseDao luokan avulla ja saveCourseAndGoalGrade metodiin palautuu *true*. Lopuksi luodaan uusi CourseGrade ja tallennetaan se arvosanatavotteita ja saatuja arvosanoja kirjaa pitävään tietokantatauluun CourseGradeDaon avulla. 

Lopulta kurssin tallentamisen onnistuessa käyttäjälle palautuu ruutuun teksti "Kurssi lisätty!".
