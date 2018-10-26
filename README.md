# Luolastogeneraattori

Projekti tiralabra kurssille, syksy 2018

## Kuvaus
Työn aihe on luoda luolastogeneraattori, joka luo erilaisia ASCII-merkeistä koostuvia luolastoja peleille ja lisää pelien sisältöä antamalla mahdollisuuden luoda käytännössä loputtoman määrän erilaisia luolastoja. Pelissä #-merkit ovat seiniä, pisteet käytäviä ja tyhjät kohdat eli välilyönneistä koostuvat kohdat ovat huoneita.

## Komentorivikomennot

Komennot tulee suorittaa projektin juuressa eli samassa hakemistossa, missä esim. pom.xml sijaitsee

### Suoritus

Ohjelman vois suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=generaattori.Main
```

### Javadoc


Javadoc generoidaan komennolla

```
mvn javadoc:javadoc
```
Sitä voi tarkastella avaamalla selaimella tiedoston target/site/apidocs

### Testaus
Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti saadaan komennolla
```
mvn test jacoco:report
```
Raporttia voi tarkastella avaamalla selaimella tiedoston target/site/jacoco/index.html

## Jarin generointi
Suoritettavan jarin voi generoida komennolla
```
mvn package
```

Luo hakemistoon target jar-tiedoston Luolastogeneraattori-1.0-SNAPSHOT.jar



## Dokumentaatio
* [Määrittelydokumentti](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/m%C3%A4%C3%A4rittelydokumentti.md)
* [Testaus](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/testausdokumentti.md)
* [Toteutus](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/toteutusdokumentti.md)
* [Käyttöohje](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/kayttoohje.md)

## Viikkopalautukset

### Viikko 1
* [Viikkoraportti 1](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/viikkoraportti1.md)
* [Määrittelydokumentti](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/m%C3%A4%C3%A4rittelydokumentti.md)


### Viikko 2
* [Viikkoraportti 2](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/viikkoraportti2.md)

### Viikko 3
* [Viikkoraportti 3](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/viikkoraportti3.md)

### Viikko 4
* [Viikkoraportti 4](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/viikkoraportti4.md)
* [Testaus](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/testausdokumentti.md)
* [Toteutus](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/toteutusdokumentti.md)

### Viikko 5
* [Viikkoraportti 5](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/viikkoraportti5.md)

### Viikko 6
* [Viikkoraportti 6](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/viikkoraportti6.md)

### Viikko 7
* [Viikkoraportti 7](https://github.com/OlliJ5/Luolastogeneraattori/blob/master/documentation/viikkoraportti7.md)
