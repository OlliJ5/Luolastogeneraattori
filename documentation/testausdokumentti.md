# Testaus


## Yksikkötestaus

Porjektissa on käytetty JUnit-testejä.

### Testikattavuus

<img src="https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/testikattavuus.png" width="800">

Sovelluksessa tietorakenteiden testeissä sekä rivikattavuus ja haarautumakattavuus ovat 100%.

Generaattori-pakkauksessa, jossa kaikki sovelluksen oleellinen työ tehdään, rivikattavuus on 93% ja haarautumakattavuus on 
81%. Connector- ja CorridorCell-luokissa ei ollut varsinaisesti mitään testattavaa, sillä niissä on vain valmiiksi generoituja metodeja get ja equals. Olen kuitenkin getterit testannut läpi.
Koko sovelluksen tärkein testikohde on Dungeongenerator-luokka, joka vastaa koko algoritmin toiminnasta. Tämän luokan metodeja olen testannut melko kattavasti perustilanteita läpikäyden. Testejä voisi kuitenkin olla kattavammin ja käydä tarkemmin rajatapauksiakin läpi. Käytävien rakentamisen testaaminen oli erityisesti hankalaa, sillä siinä käytettiin satunnaisuutta käytävän seuraavana suuntana, joten se on tällä hetkellä heikoiten testattu asia.

Suorituskyvyn testaamisesta vastaavan paketin ainoaa luokkaa PerformanceTester, en ole testannut ollenkaan, sillä  en kokenut sen testaamisen tuovan mitään oleellista.

Myöskään UI-paketin Tui-luokkaa, joka vastaa ohjelman käyttöliittymästä, ei ole testattu yksikkötesteillä ollenkaan.



## Suorituskyky

Suorituskyvyn testauksen avuksi on luotu oma luokka PerformanceTester, joka testaa algoritmin suoritusaikaa eri parametrein. 
Jokainen eri kokoinen luolasto eri huoneiden määrällä on luotu 10 kertaa ja aika on niiden keskiarvo.
Testit voidaan toistaa graafisessa käyttöliittymässä komennolla 'p', joka ajaa tehokkuustestit läpi ja antaa niistä tiedon jokaisen kohdalla, kuinka kauan luonti kesti ja keskiarvon kymmenestä luontikerrasta.

Testien tuloksia(Viikko 6):

|Luolaston koko|Huoneiden määrä|Aika|
|-----|-----|-----|
|20x60 |50|41 ms|
|40x120|150|593 ms|
|80x240|500|4663 ms|
|80x240|2000|380 ms|

Testien tuloksia(Viikko 7):

|Luolaston koko|Huoneiden määrä|Aika|
|------|------|------|
|20x60|50|13ms|
|40x120|150|36ms|
|80x240|500|138ms|
|500x500|1000|2457ms|
|500x500|60|3296ms|


Testeissä on siis testattu kuinka kauan algoritmin suorittamisessa kestää, kun halutaan luoda eri kokoinen luolasto.
Pienten luolastojen luominen on hyvin nopeaa, mutta halutun luolaston suuuretessa tietysti myös algoritmin suoritusaika on 
pidempi.

Suurin aikasyöppö viikolla 6 tehdyissä testeissä oli rekursiivinen FloodFill -algoritmi, jonka korvasin paremmalla toteutuksella. Myös Luolastojen ulkonäkö parani, mutta tärkeimpänä tehokkuus kasvoi.


## Muu testaus

Käyttöliittymää ei ole testattu koodatuilla testeillä, mutta sitä on yritetty rikkoa manuaalisesti virheellisillä syötteillä
