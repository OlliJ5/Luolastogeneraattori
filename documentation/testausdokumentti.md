# Testaus

Algoritmini testaus on vielä hyvin heikkoa. Yksikkötestaus on jäänyt kiireiden takia jälkeen.

## Yksikkötestaus

Porjektissa on käytetty JUnit-testejä. Toistaiseksi on testattu vasta Huoneiden sijoitusten oikeellisuutta luolastossa.


## Empiiriset testit

Ohjelmaa on testattu empiirisesti (valitettavasti) huomattavasti enemmän kuin koodatuilla testeillä. Ohjelmassa on yksi ylivoimaisesti 
suurin vika, joka on se, että liian suuria luolastoja ei voida luoda raskaan rekursiivisen Flood fill -metodin takia.

Ohjelmaa on testattu ja tarkkailtu paljon kehityksessä aputulostuksien avulla, joista on tarkkailtu, että tietyt arvot ovat oikein
ja metodit tekevät kuten halutaan.
