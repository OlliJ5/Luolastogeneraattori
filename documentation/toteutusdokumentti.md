# Toteutusdokumentti

## Ohjelman yleisrakenne

Algoritmi on tehty [Kyseisen kirjoituksen](http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/) pohjalta.

Ohjelma aloitetaan alustamalla kaksiuloitteinen taulukko, joka on tietyn korkuinen ja tietyn levyinen.
Tämän jälkeen Luolastoon yritetään sijoittaa huoneita n määrä. Jos yritetään sijoittaa huone, jonka paikka ei ole (Menee jo
olemassaolevan huoneen päälle tai jokin osa huoneesta on luolaston ulkopuolella) kelvollinen, jatketaan seuraavaan huoneeseen.

Huoneiden sijoittamisen jälkeen luolasto täytetään käytävillä growing tree algoritmin avulla.
[Algoritmin toiminta on kuvattu täällä.](http://weblog.jamisbuck.org/2011/1/27/maze-generation-growing-tree-algorithm)

Tämän jälkeen luolaston eri osiot yhdistetään eli huoneiden ja käytävien välille asetetaan ovia, jotta luolastossa pääsee liikkumaan paikasta toiseen. Ovia voi tulla myös kahden huoneen tai kahden käytävän välille.

Sen jälkeen kun luolasto on yhdistetty eli paikoista pääsee toisiin, poistetaan käytävistä umpikujat, sillä umpikujien kanssa, varsinkin, jos pitää kaikki luolaston umpikujat ja käytävät sellaisenaan, tulee luolastosta hyvin epäselvä.

Lopussa luolastosta tehdään "hieno" eli sijoitetaan oikeisiin kohtiin oikeat ASCII-merkit kokonaislukujen sijaan, jotka ovat luolastossa algoritmin toiminnan aikana.

## Saavutetut aika- ja tilavaativuudet

#### Tulee kohta

## Työn mahdolliset puutteet ja parannusehdotukset

Algoritmissä on toki myös ongelmia. Projektin ydin eli DungeonGenerator-luokka on erittäin pitkä ja olisi pitänyt pilkkoa 
pienempiin osiin. Sen olisi voinut jakaa esimerkiksi osiin sen perusteella, että yksi luokka hoitaa käytävien sijoittamisen, yksi luokka yhdistää luolaston ja yksi luokka poistaa umpikujat. Näin koodi olisi paljon helpompilukuisempaa kaikille, myös itselleni. 

Nyt myös käytävät voivat yhistyä toisiin käytäviin, jos ne ovat tarpeeksi lähellä toisiaan. Tämä ei sinänsä ole mielestäni huono asia, sillä kyllähän käytävilläkin voi ovia olla, mutta se ei aina näytä mielestäni kovin kauniilta, joten sen olisi voinut hoitaa hieman jollain erilailla.

Joillain saattaa olla myös hieman ongelmia katsoa näitä ascii-merkkejä ja hahmottaa, miten luolasto kulkee, joten sovellukseen voisi koodata jonkun vaihtoehtoisen tavan katsella luolastoja. Tämän voisi toteuttaa graafisesti niin, että erilaisia kohtia luolastossa kuvattaisiin eri värisillä neliöillä. Myös graafinen käyttöliittymä olisi aina mukavempi kuin pelkkä tekstikäyttöliittymä, joka nyt on käytössä.


Lähteet:

* http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/

* http://weblog.jamisbuck.org/2011/1/27/maze-generation-growing-tree-algorithm
