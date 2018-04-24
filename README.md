# Opinnot

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/arkkitehtuuri.md)

## Releaset
[viikko 5](https://github.com/ellikarvonen/otm-harjoitustyo/releases)

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
