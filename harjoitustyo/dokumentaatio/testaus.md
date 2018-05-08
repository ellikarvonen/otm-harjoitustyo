# Testausdokumentti

Ohjelmaa on testattu sekä JUnitilla yksikkö- ja integraatiotestein. Lisäksi ohjelmaa on testattu manuaalisesti.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Paukkauksen study.domain luokkien testaus tapahtuu [CourseTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/CourseTest.java), [CourseGradeTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/CourseGradeTest.java), [GradeTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/GradeTest.java) ja [StudyServiceTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/domainTest/StudyServiceTest.java) testeissä.

### DAO-luokat

DAO-luokkia testetaan luomalla ensin tietokanta testejä varten. DAO-luokkien testaus tapahtuu [CourseDaoTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/daoTest/CourseDaoTest.java), [CourseGradeDaoTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/daoTest/CourseGradeDaoTest.java), [GradeDaoTest testeissä](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/daoTest/GradeDaoTest.java). Lisäksi [Statistics](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/main/java/study/dao/Statistics.java) ja [Database](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/main/java/study/dao/Database.java) luokkia testaan [DatabaseTest]() ja [StatisticsTest](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/src/test/java/daoTest/StatisticsTest.java) testeillä.

### Testauskattavuus
Käyttöliittymää ei ole testattu. Testauksen rivikattavuus oli muuten **XX** ja haarautumiskattavuus **XX**

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/testiraportti.png)

Testaamatta jäi tilanteita, joissa ulkopuolelta tulee virhe, esimerkiksi sovellus on kahdessa ikkunassa auki.

## Järjestelmätestaus

### Asennus

Sovellus on ladattu ja sitä on testattu [käyttöohjeen](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/kayttoohje.md) määrittämällä tavalla Linux-ympäristössä. Lisäksi on testattu, että sovelluksen tarvitsema tietokanta on jo olemassa ja jos sitä ei ole, niin sovellus luo sen.

### Toiminnallisuudet

Käyttöohjeessa ja [vaatimusmäärittelydokumentissa](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/vaatimusmaarittely.md) olevat toiminnalisuudet on käyty läpi.
Syötekenttiä on yrittetty täyttää myös virheellisillä arvoilla, kuten tyhjillä ja kirjaimilla, kun syöte tulisi olla numero.
