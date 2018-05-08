# Testausdokumentti

Ohjelmaa on testattu sekä JUnitilla yksikkö- ja integraatiotestein. Lisäksi ohjelmaa on testattu manuaalisesti.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Paukkauksen study.domain luokkien testaus tapahtuu [CourseTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/CourseTest.java), [CourseGradeTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/CourseGradeTest.java), [GradeTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/GradeTest.java) ja [StudyServiceTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/StudyServiceTest.java) testeissä.

### DAO-luokat

DAO-luokkia testetaan luomalla ensin tietokanta testejä varten. DAO-luokkien testaus tapahtuu CourseDaoTest, CourseGradeDaoTest, GradeDaoTest testeissä. Lisäksi Statistics ja Database luokkia testaan DatabaseTest ja StatisticsTest testeillä.

### Testauskattavuus
Käyttöliittymää ei ole testattu. Testauksen rivikattavuus oli muuten **XX** ja haarautumiskattavuus **XX**

**KUVA**

Testaamatta jäi tilanteita, joissa ulkopuolelta tulee virhe, esimerkiksi sovellus on kahdessa ikkunassa auki.

## Järjestelmätestaus

### Asennus
### Toiminnallisuudet

Käyttöohjeessa ja vaatimusmäärittelydokumentissa olevat toiminnalisuudet on käyty läpi.
Syötekenttiä on yrittetty täyttää myös virheellisillä arvoilla, kuten tyhjillä ja kirjaimilla, kun syöte tulisi olla numero.
