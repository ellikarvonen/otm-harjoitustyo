# Opinnot

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Testaus

Komento testien suorittamiseen: **mvn test**

Komento testiraportin luomiseen: **mvn jacoco:report**

Kattavuusraporttia pystyy tarkastelemaan avaamalla selaimella target/site/jacoco/index.html.


### Checkstyle

Tarkistukset, jotka määritellään tiedostossa [checkstyle.xml](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/Opinnot/checkstyle.xml) voi suorittaa komennolla: **mvn jxr:jxr checkstyle:checkstyle**

Mahdolliset virheet pystyy tarkistamaan avaamalla selaimella target/site/checkstyle.html.
