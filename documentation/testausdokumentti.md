# Testaus


## Yksikkötestaus

Porjektissa on käytetty JUnit-testejä.

## Suorituskyky

Suorituskyvyn testauksen avuksi on luotu oma luokka PerformanceTester, joka testaa algoritmin suoritusaikaa eri parametrein. 

Testien tuloksia:

|Luolaston koko|Huoneiden määrä|Aika|
|-----|-----|-----|
|20x60 |50|41 ms|
|40x120|150|593 ms|
|80x240|500|4663 ms|
|80x240|2000|380 ms|


Testeissä on siis testattu kuinka kauan algoritmin suorittamisessa kestää, kun halutaan luoda eri kokoinen luolasto.
Pienten luolastojen luominen on hyvin nopeaa, mutta halutun luolaston suuuretessa tietysti myös algoritmin suoritusaika on 
pidempi.

Suorituskykytestausta täytyy vielä jatkaa paljon. Algorimin tehokkuutta täytyy testata vielä eri kokoisilla luolastoilla.
Varsinkin huoneiden määrän vaikutusta, jonka suuren merkityksen näkee kahdesta viimeisestä testistä, täytyy vielä tutkia tarkemmin.



## Empiiriset testit

Ohjelmaa on testattu empiirisesti enemmän kuin koodatuilla testeillä. Ohjelmassa on yksi ylivoimaisesti 
suurin vika, joka on se, että liian suuria luolastoja ei voida luoda raskaan rekursiivisen Flood fill -metodin takia.

Ohjelmaa on testattu ja tarkkailtu paljon kehityksessä aputulostuksien avulla, joista on tarkkailtu, että tietyt arvot ovat oikein
ja metodit tekevät kuten halutaan.
