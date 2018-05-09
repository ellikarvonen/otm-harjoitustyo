# Käyttöohje

## Ohjelman käynnistäminen
Ohjelman saa käynnistettyä komennolla: **java -jar Opinnot-1.jar**

## Etusivu
Sovellus käynnistyy etusivulle.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/etusivu.png)

## Uuden kurssin luominen

Uuden kurssin luominen onnistuu siirtymällä etusivulta "Lisää uusi kurssi" -sivulle. Käyttäjä kirjoittaa kenttiin kurssin nimen ja opintopistemäärän. Tavoitearvosana on oletusarvoisesti "-", mutta sen voi vaihtaa pudotusvalikosta.
Kurssin lisäys onnistuu Lisää-napista.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/lisaa_uusi_kurssi.png)

Jos kurssin nimi on liian pitkä, saman niminen kurssi on jo olemassa tai kurssin nimi on tyhjä, niin ruudulle ilmestyy virheviesti. Virheviesti ilmestyy myös, jos opintopisteet eivät ole kokonaisluku.


## Kurssin lisääminen suoritetuksi 

Kurssin voi lisätä suoritetuksi sivulta "Lisää kurssi suoritetuksi". Käyttäjä valitsee pudotusvalikosta kurssin ja arvosanan. Arvosana on oletusarvoisesti "0". Jos kursseja, joita voi lisätä suoritetuksi ei ole olemassa siirtyy sivusto virheviesti sivulle.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/Screenshot%20from%202018-05-02%2013-10-52.png)
![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/suorittamattomia_kursseja_ei_ole.png)

## Kurssitietojen tarkastelu

Kurssien tietoja voi tarkastella sivulta "Kurssitiedot". Käyttäjä valitsee kurssin pudotusvalikosta. Tiedot saa näkyville painamalla nappia "Tiedot". Sivun ylälaidasta näkee kurssien keskiarvon ja suoritettujen kurssien opintopistemäärän.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/kurssitiedot.png)

## Kurssin tietojen päivittäminen

Kurssin tietojen päivitys onnistuu Päivitä kurssitietoja -sivulta.
Kurssin opintopistemäärää, arvosanatavoitetta ja arvosanaa voi päivittää. Suoritusarvosanaa voi päivittää vain, jos kurssi on suoritettu. Opintopistemäärän tulee olla kokonaisluku. Sovellus kertoo, jos päivitys onnistuu. Käyttäjä syöttää uuden opintopistemäärän kenttään ja valitsee arvosanat pudotusvalikoista. Jos käyttäjä ei halua muuttaa kyseistä arvoa, jättää hän kohdan tyhjäksi.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/muutakurssin_tietoja.png)

## Kurssin poistaminen 

Kurssin voi poistaa Päivitä kurssitietoja -sivulta. Poistaminen onnistuu Poista kurssi -napista. Sovellus poistaa kurssin, joka on valittu pudotusvalikosta.
