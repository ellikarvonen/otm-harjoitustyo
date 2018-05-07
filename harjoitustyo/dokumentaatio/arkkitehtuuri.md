# Arkkitehtuurikuvaus
## Rakenne
### Luokkakaavio

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/opinnot_luokkakaavio.png)

### Pakkauskaavio

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/pakkauskaavio.png)

## Käyttöliittymä
Käyttöliittymässä on seuraavat näkymät:

- Etusivu
- Uuden kurssin lisääminen
- Kurssin lisääminen suoritetuksi
- Kurssitietojen tarkastelu
- Kurssitietojen päivittäminen
- Ilmoitussivu (kursseja ei olemassa)

Kaikki näkymät ovat toteutettu Scene-oliona. Vain yksi sivu näkyy kerrallaan. Käyttöliittymä on luokassa study.ui.main.

Käyttöliittymä on erillään sovelluslogiikasta ja se kutsuu sovelluslogiikasta studyService luokkaa sekä tarvittaessa, kuten poistamisessa ja listaamisessa, myös dao luokkia.

## Sovelluslogiikka

Sovelluslogiikasta huolehtii luokka study.domain.studyService luokka. Luokka tarjoaa käyttöliittymälle metodeja, kuten:
- String saveCourseAndGoalGrade(String name, String credit, Grade grade)
- String printAvarageGrade()
- String printComplitedCoursesCreditSum()
- String saveCourseComplited(String courseName, Grade grade)

## Tietojen pysyväistallennus

Pakkauksen study.dao luokat huolehtivat tietojen tallentamisesta ja poistamisesta *study.db* tietokantaan. Tiedot tallennetaan kolmeen eri tietokantatauluun, jotka ovat Course, CourseGrade ja Grade. Tietokanta on SQLite-tietokanta.

Tietokantataulu Course sisältää tiedon kurssin nimestä ja opintopisteistä. Kurssin nimen täytyy olla uniikki.

|PRIMARY KEY name (varchar (200)) | credit (Integer) |
|-----------------------------|:--------------------------:|
| Ohjelmistotekniikan menetelmät | 5 |
| Ohjelmoinnin perusteet | 5 | 

Tietokantataulu CourseDao puolestaan pitää kirjaa kurssin tavoitearvosanoista sekä saaduista arvosanoista. Jokaisella kurssilla voi olla vain yksi tavoitearvosana sekä saatu arvosana. Luku 1 viittaa arvosanatavoitteeseen ja 0 suoritettuun arvosaaan.

|PRIMARY KEY name (varchar (200) | grade (varchar(20)) | PRIMARY KEY goal (integer)  |
|-----------------------------|:--------------------------:|:--------:|
| Ohjelmistotekniikan menetelmät | - | 1 |
| Ohjelmistotekniikan menetelmät | 3 | 0 |
| Ohjelmoinnin perusteet | 3 | 0 | 

Tietokantataulu Grade pitää kirjaa kaikista mahdollisista arvosanoista. Arvosanan täytyy olla uniikki.

|PRIMARY KEY grade (varchar(20)) |
|:-----------------------------:|
| - |
| 1 |
| 2 |
| 3 |

## Päätoiminnallisuudet

### Kurssin lisääminen
Kurssin lisäämiseen tarkoitetulla sivustolla uuden kurssin lisäys toimii seuraavasti käyttäjän painaessa addButton nappia.
![alt taxt](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/sekvenssi_kurssinlisays.png)

Käyttäjä kirjoittaa kenttiin kurssin nimen ja opintopisteiden määrän sekä valitsee valikosta arvosanatavoitteensa. Painaessa "Lisää" (buttonAdd) -nappia tapahtumankäsittelijä kutsuu StudyService luokan saveCourseAndGoalGrade metodia. Metodiin annetaan parametreiksi käyttäjän syöttämät kurssin nimi ja opintopistemäärä, sekä käyttäjän valitsema arvosana. 

Metodi kutsuu edelleen metodia saveCourse, jossa parametreinä on kurssin nimi ja opintopistemäärä. Metodi tarkistaa, että samannimistä kurssia ei ole olemassa, opintopisteet voidaan muuntaa Integer muotoon, kurssin nimi ei ole liian pitkä (yli 200 merkkiä) ja kurssin nimi ei ole tyhjä. Jos ehdot täyttyvät, luodaan uusi kurssi ja lisätään se tietokantaan CourseDao luokan avulla ja saveCourseAndGoalGrade metodiin palautuu *true*. Lopuksi luodaan uusi CourseGrade ja tallennetaan se arvosanatavotteita ja saatuja arvosanoja kirjaa pitävään tietokantatauluun CourseGradeDaon avulla. 

Lopulta kurssin tallentamisen onnistuessa käyttäjälle palautuu ruutuun teksti "Kurssi lisätty!".

### Kurssin lisääminen suoritetuksi
Käyttäjä valitsee kurssin suorituksen lisäämiseen tarkoitetulla sivustolla kurssin ja arvosanan valikosta. Käyttäjän painaesssa buttonAdd -nappia tapahtumakäsittelijä kutsuu StudyServicen saveCourseComplited metodia. Metodiin tulee parametreiksi käyttäjän valitseman kurssin nimi ja arvosana.

Metodi saveCourse hakee arvosanan ja palauttaa saamansa arvon. Metodi luo uuden CourseGrade olion, jonka parametreinä on kurssin nimi, arvosana ja 0. Nolla merkitsee, että kyseessä on suoritus. Seuraavaksi metodi kutssuu CourseGradeDaon save metodia. Parametriksi tulee juuri luotu CourseGrade olio.

CourseGradeDaon metodi save tallentaa suorituksen tietokantaan ja palauttaa talletuksen jälkeen CourseGrade olion StudyServicen saveGrade metodiin. True palautuu saveGrade metodista saveCourseComplited metodiin. Tämä metodi puolestaan palauttaa "Kurssin suoritus" + kurssin nimi + "tallennettu!" -tekstin käyttäjälle.

Alla sekvenssikaavio 
![alt taxt](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/suoritus_sekvennssi.png)

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä
Käyttöliittymä on toteutettu luokassa Main. Käyttöliittymän metodit ovat liian pitkiä ja niitä tulisi hajoittaa pienemmiksi metodeiksi.
