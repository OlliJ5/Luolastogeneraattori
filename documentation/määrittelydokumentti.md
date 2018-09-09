# Määrittelydokumentti

#### Mitä algoritmeja ja tietorakenteita toteutat työssäsi
Toteutan työssäni luolastogeneraattorin, joka koostuu useasta pienemmästä osasta.

Ensin pitää luoda luolaston huoneet. Tässä käytetään apuna satunnaisuutta.
Sitten luodaan luolastoon käytäviä ja yhdistetään luolasto virittävän puun avulla. Virittävän puun selvitän Kruskalin algoritmillä.

Työssä tarvitsemiani tietorakenteita ovat ainakin taulukot ja Union find

Käytetyt algoritmit ja tietorakenteet selkeytyvät työn edetessä ja päivitän siten tätä dokumenttia, mutta tässä on alustava runko.

#### Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet
Luon luolastogeneraattorin, jonka avulla voi luoda satunnaisia luolastoja peleihin. Tämä lisää pelien pelattavuutta tuomalla niihin
lisää (lähes loputtomasti) erilaista sisältöä. Valitsin kyseiset algoritmit, sillä tutkiskelun ja useiden artikkeleiden ja keskusteluiden 
pohjalta nämä vaikuttivat parhaimmilta sen kannalta minkä näköisiä luolastoja saa luotua sekä tietysti kurssi omaisesti nopeuden kannalta.



#### Mitä syötteitä ohjelma saa ja miten näitä käytetään
Lopullinen ohjelma saa ainakin luolaston kartan koon syötteenä eli voi luoda eri kokoisia luolastoja. Mahdollisesti myös ajan 
salliessa luolaston huondeiden ja käytävien määrät ovat ainakin tietyssä määrin säädeltävissä.


#### Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)
Luolaston huoneiden lisäämisen pitäisi toimia ainakin lineaarisessa ajassa lisättyjen huoneiden määrään eli O(n)




#### Lähteet

* http://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php

* http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/
