# Käyttöohje

## Ohjelman käynnistäminen
Ohjelman saa käynnistettyä komennolla: **java -jar todoapp.jar**

## Etusivu
Sovellus käynnoistyy etusivulle.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/etusivu.png)

## Uuden kurssin luominen

Uuden kurssin luominen onnistuu suurtymällä etusivulta "Lisää uusi kurssi" -sivulle. Käyttäjä Kirjoittaa kenttiin kurssin nimen ja opintopistemäärän. Tavoitearvosana on oletusarvoisesti "-", mutta sen voi vaihtaa pudotusvalikosta.
Kurssin lisäys onnistuu Lisää-napista.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/lisaa_uusi_kurssi.png)

Jos kurssin nimi on liian pitkä, saman niminen kurssi on jo olemassa tai kurssin nimi on tyhjä, niin ruudulle ilmestyy virheviesti. Virheviesti ilmestyy myös jos opintopisteet eivät ole kokonaisluku.


## Kurssin lisääminen suoritetuksi 

Kurssin voi lisätä suoritetuksi sivulta "Lisää kurssi suoritetuksi". Käyttäjä valitsee pudotusvalikosta kurssin ja arvosanan. Arvosana on oletusarvoisesti "0". Jos kursseja, joita voi lisätä suoritetuksi ei ole olemassa. Siirtyy sivusto virheviesti sivulle.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/Screenshot%20from%202018-05-02%2013-10-52.png)
![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/suorittamattomia_kursseja_ei_ole.png)

## Kurssitietojen tarkastelu

Kurssien tietoja voi tarkastella sivulta "Kurssitiedot". Käyttäjä valitsee kurssin pudotusvalikosta. Tiedot saa näkyville painamalla nappia "Tiedot". Sivun ylälaidasta näkee kurssien keskiarvon ja suoritettujen kurssien opintopistemäärän.

![alt text](https://github.com/ellikarvonen/otm-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/kurssitiedot.png)

## Kurssin poistaminen

Kurssin voi poistaa Päivitä kurssitietoja -sivulta. Poistaminen onnistuu Poista kurssi -napista. Sovellus poistaa kurssin, joka on valittu pudotusvalikosta.
