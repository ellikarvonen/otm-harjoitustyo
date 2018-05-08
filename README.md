# Opinnot

Sovellus pitää kirjaa käyttäjän lisäämistä kursseista. Kursseille voi lisätä tavoitearvosanoja ja kursseja voi lisätä suoritetuksi. Sovelluksessa pystyy tarkastelemaan ja muuttamaan kurssin tietoja. 

Sovellus on tehty Helsingin Yliopiston Tietojenkäsittelytieteen Ohjelmistotekniikan menetelmät kurssilla harjoitustyöksi.

## Dokumentaatio
[Käyttöohje](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/arkkitehtuuri.md)

[Testaus](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/tyoaikakirjanpito.md)


## Releaset
[viikko 5](https://github.com/ellikarvonen/otm-harjoitustyo/releases/tag/viikko5)

[viikko 6](https://github.com/ellikarvonen/otm-harjoitustyo/releases/tag/viikko6)

[loppupalautus](https://github.com/ellikarvonen/otm-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Testaus

Komento testien suorittamiseen: **mvn test**

Komento testiraportin luomiseen: **mvn jacoco:report**

Kattavuusraporttia pystyy tarkastelemaan avaamalla selaimella target/site/jacoco/index.html.

### Suoritettavan jarin generointi

Komento jar-tiedoston generointiin: **mvn package**

Tiedosto Opinnot-1.0-SNAPSHOT.jar on hakemistossa *target*.


### Checkstyle

Tarkistukset, jotka määritellään tiedostossa [checkstyle.xml](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/checkstyle.xml) voi suorittaa komennolla: **mvn jxr:jxr checkstyle:checkstyle**

Mahdolliset virheet pystyy tarkistamaan avaamalla selaimella target/site/checkstyle.html.

### JavaDoc

JavaDocin generointi: **mvn javadoc:javadoc**

JavaDocin tarkastelu onnistuua avaamalla selaimella target/site/apidocs/index.html.
