# Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma aloitetaan alustamalla kaksiuloitteinen taulukko, joka on tietyn korkuinen ja tietyn levyinen.
Tämän jälkeen Luolastoon yritetään sijoittaa huoneita n määrä. Jos yritetään sijoittaa huone, jonka paikka ei ole (Menee jo
olemassaolevan huoneen päälle tai jokin osa huoneesta on luolaston ulkopuolella) kelvollinen, jatketaan seuraavaan huoneeseen.

Huoneiden sijoittamisen jälkeen sijoitetaan luolastoon käytäviä Flood fill -algoritmin avulla. Tämän jälkeen luolaston eri osiot 
yhdistetään. Eli huoneiden ja käytävien välille luodaan ovia. Tämä tehdään Kruskalin algoritmillä ja Union find -rakenteen avulla.
Lopussa luolastosta tehdään "hieno" eli sijoitetaan oikeisiin kohtiin oikeat ASCII-merkit.

## Saavutetut aika- ja tilavaativuudet

#### Tulee kohta

## Työn mahdolliset puutteet ja parannusehdotukset

Algoritmissä on joitain ongelmia. Päälimmäisin ja suurin ongelma algoritmissäni on Flood Fill -algoritmi, jonka takia liian 
suuri kokoisia luolastoja ei voida luoda tai muuten saadaan rekursiosta johtuva Stack Overflow.

Algoritmissä pitäisi vielä myös parantaa käytäviä poistamalla umpikujia, jolloin luolastosta tulisi siistimpi ja vähemmän sekainen.
