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

### Projektin pakettirakenne

Projekti on jaettu neljään pakettin, jotka ovat UI, generaattori, performance ja tietorakenteet. 

UI-paketti vastaa projektin käyttöliittymästä ja koostuu luokasta TUI, joka vastaa projektin tekstikäyttöliittymästä.

Generaattori-paketti koostuu luokista Connector, CorridorCell, Dungeongenerator, Main ja Room. Generaattori paketti vastaa luolastojen luomisesta, sekä ohjelman aloittamisesta.

Performance-paketti vastaa suorituskyvyn testaamisesta ja siihen kuuluu luokka PerformanceTester.

Tietorakenteet-paketti vastaa projektin vaatimista tietorakenteista, joita ovat ArrayList sekä Union-find -rakenne.

## Saavutetut aika- ja tilavaativuudet

Huoneiden sijoitus:

Aikavaativuus on lineaarinen huoneiden määrään nähden eli O(n)
Huoneiden sijoitus ei vie ylimääräistä tilaa, mutta tilaa vie koko ajan luolasto, 
jonka tilavaativuus on O(h*w), jossa h on luolaston korekus ja w luolaston leveys.

Käytävien luominen:

Käytävien luomisessa pahimmassa tapauksessa algoritmi joutuu käymään luolaston joka solun läpi, joten aikavaativuus on 
O(h*w), jossa h on luolaston korkeus ja w luolaston leveys. Tilavaativuus on myös sama.

Luolaston yhdistäminen:

Luolaston yhdistämisessä haetaan ensin mahdolliset yhdistäjä kohdat, eli käydään luolasto läpi. Tämän aikavaativuus on O(h*w). Sitten luolasto yhdistetään Kruskalin algoritmillä, jonka aikavaativuus on O(m log(n)). Tilavaativuus tässä on O(h*w)+O(m), joka tulee luolaston koosta ja listasta, jossa pidetään yllä yhdistäjäkohtia.

Umpikujien poisto:

Pahimmassa tapauksessa lähes jokaine luolaston kohta joudutaan käymään läpi kaksi kertaa, joten aikavaativuus on 2*O(h*w) eli O(h*w). Tilavaativuus tässä rekursiivisessa funktiossa on O(h*w), jos sama käytävä peittää koko luolaston ja luolastossa ei ole huoneita. Silloin algortimi joutuu käymään koko käytävän läpi.


## Työn mahdolliset puutteet ja parannusehdotukset

Algoritmissä on toki myös ongelmia. Projektin ydin eli DungeonGenerator-luokka on erittäin pitkä ja olisi pitänyt pilkkoa 
pienempiin osiin. Sen olisi voinut jakaa esimerkiksi osiin sen perusteella, että yksi luokka hoitaa käytävien sijoittamisen, yksi luokka yhdistää luolaston ja yksi luokka poistaa umpikujat. Näin koodi olisi paljon helpompilukuisempaa kaikille, myös itselleni. 

Nyt myös käytävät voivat yhistyä toisiin käytäviin, jos ne ovat tarpeeksi lähellä toisiaan. Tämä ei sinänsä ole mielestäni huono asia, sillä kyllähän käytävilläkin voi ovia olla, mutta se ei aina näytä mielestäni kovin kauniilta, joten sen olisi voinut hoitaa hieman jollain erilailla.

Joillain saattaa olla myös hieman ongelmia katsoa näitä ascii-merkkejä ja hahmottaa, miten luolasto kulkee, joten sovellukseen voisi koodata jonkun vaihtoehtoisen tavan katsella luolastoja. Tämän voisi toteuttaa graafisesti niin, että erilaisia kohtia luolastossa kuvattaisiin eri värisillä neliöillä. Myös graafinen käyttöliittymä olisi aina mukavempi kuin pelkkä tekstikäyttöliittymä, joka nyt on käytössä.

Ohjelmassa on myös ongelmana isot luolastot, jos ne eivät mahdu terminaalin ikkunaan. Ratkaisuna voisi olla avata luolasto generoimisen jälkeen jossain muualla, esimerkiksi jossain tekstieditorissa, automaattisesti.


Lähteet:

* http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/

* http://weblog.jamisbuck.org/2011/1/27/maze-generation-growing-tree-algorithm
