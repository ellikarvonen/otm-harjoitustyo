# Käyttöohje

## Ohjelman käynnistäminen
Ohjelman saa käynnistettyä komennolla: **java -jar todoapp.jar**

## Etusivu
Sovellus käynnoistyy etusivulle

## Uuden kurssin luominen

Uuden kurssin luominen onnistuu suurtymällä etusivulta "Lisää uusi kurssi" -sivulle. Käyttäjä Kirjoittaa kenttiin kurssin nimen ja opintopistemäärän. Tavoitearvosana on oletusarvoisesti "-", mutta sen voi vaihtaa pudotusvalikosta.
Kurssin lisäys onnistuu Lisää-napista.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/lisaa_uusi_kurssi.png)

 Jos kurssin nimi on liian pitkä, saman niminen kurssi on jo olemassa tai kurssin nimi on tyhjä, niin ruudulle ilmestyy virheviesti. Virheviesti ilmestyy myös jos opintopisteet eivät ole kokonaisluku.
![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/virheviesti_uusi_kurssi.png)

## Kurssin lisääminen suoritetuksi 

Kurssin voi lisätä suoritetuksi sivulta "Lisää kurssi suoritetuksi". Käyttäjä valitsee pudotusvalikosta kurssin ja arvosanan. Arvosana on oletusarvoisesti "0". Jos kursseja, joita voi lisätä suoritetuksi ei ole olemassa. Siirtyy sivusto virheviesti sivulle.

## Kurssitietojen tarkastelu

Kurssien tietoja voi tarkastella sivulta "Kurssitiedot". Käyttäjä valitsee kurssin pudotusvalikosta. Tiedot saa näkyville painamalla nappia "Tiedot". Sivun ylälaidasta näkee kurssien keskiarvon ja suoritettujen kurssien opintopistemäärän.

## Kurssin poistaminen

Kurssin voi poistaa Päivitä kurssitietoja -sivulta. Poistaminen onnistuu Poista kurssi -napista. Sovellus poistaa kurssin, joka on valittu pudotusvalikosta.
