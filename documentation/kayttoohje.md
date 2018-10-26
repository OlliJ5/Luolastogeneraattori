# Käyttöohje

Lataa tiedosto [Luolastogeneraattori-1.0-SNAPSHOT.jar](https://github.com/OlliJ5/Luolastogeneraattori/releases)

## Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla
```
java -jar Luolastogeneraattori-1.0-SNAPSHOT.jar
```
## Ohjelman toiminta

Ohjelmassa on 6 komentoa. Käydään ne nyt läpi

### Generaattorin ajaminen

Komennolla r sovellus ajaa generaattorin ja tulostaa luodun luolaston terminaaliin.

Alussa Luotavan luolaston koko on 15x60, johon yritetään sijoittaa 6 vähintään 2x2 kokoista huonetta.

### Generaattorin asetukset

Komennolla s päästään asetuksiin.

Jos asetuksia haluttan muuttaa pitää ensimmäiseen kysymykseen antaa vastaukseksi y.

Ohjelma kysyy sitten halutun luolaston korkeutta, jolle kuuluu antaa kokonaisluku kymmenen ja 256 välillä. Ohjelma hyväksyy myös
syötteen, jos kirjoittaa esim. 'vaikka 50'. Se tunnistaa siitä luvun 50 ja asettaa sen korkeudeksi.

Sitten ohjelma kysyy luolaston leveyttä, jolle on samat kriteerit kuin korkeudelle.

Sitten ohjelma kuinka monta huonetta luolastoon haluttan yrittää asettaa. Tähän kuuluu antaa syötteeksi kokonaisluku nollan ja 
kymmenen tuhannen välillä.

Sitten ohjelma kysyy vielä haluttua huoneiden minimikorkeutta. Tähän annetaan kokonaisluku, joka on vähintään kaksi ja korkeintaan
1/4 luolaston korkeudesta.

Sitten ohjelma kysyy haluttua huoneiden minimileveyttä. Tähän annetaan kokonaisluku, joka on vähintään kaksi ja korkeintaan 
1/4 luolaston leveydestä.

### Apu
Komennolla h saa auki pienen infon sovelluksesta

### Suorituskyky
Komennolla p saa ajettua suorituskykytestin, joka luo eri kokoisia luolastoja ja kertoo niiden luomisajan ja keskiarvon
samanlaisten luomisessa.

### Demo
Komennolla d saa ajettua demon, jossa luodaan luolasto ja näytetään miltä se eri askeleissa näyttää sekä mitä se kussakin askeleessa 
tekee.

### Sovelluksen lopettaminen
Komennolla q ohjelma saadaan pysäytettyä


