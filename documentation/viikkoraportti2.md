# Viikkoraportti 2

Käytetyt tunnit: 11h

Tällä viikolla olen aloittanut projektini koodamisen sekä ylläpitänyt Github-repon siisteyttä. Olen myös kirjoittanut JavaDoceja 
sekä aloittanut projektini yksikkötestauksen. 

Ohjelma on edistynyt mielestäni hyvin. Ohjelmalla saa luotua eri kokoisia ASCII-merkeistä 
koostuvia luolastoja, joissa huoneita on eri tiheyksin. Huoneen paikat ja koot ovat satunnaisia. Huoneiden sijoituksen jälkeen 
luolasto täyttyy myös käytävistä Flood Fill -algoritmin avulla. 

Tällä viikolla opin Flood fill -algoritmistä paljon. Opin sen toimimintamenetelmän sekä luin useita pseudokoodeja ja koodeja sen 
totetuttamisesta käytännössä. Minun piti myös itse soveltaa aika paljon, että sain algoritmin toimimaan koodissani. Nyt algoritmi 
täyttää luolaston kauniisti käytävillä, vaikka floodFill-metodi näyttää aika hirveältä. Koodauksen alussa piti palauttaa javaa mieleen, 
sillä viime koodauskerrasta on jo useampi kuukausi. Se palasi kuitenkin aika nopeasti takaisin päähän ja koodaus alkoi sujumaan nopeasti. 
Piti myös palauttaa mieleen JavaDocien tekeminen ja JUnit-testien tekeminen.

Seuraavaksi työssä pitää aloittaa luolaston yhtenäistäminen eli käytävien ja huoneiden välille pitää rakentaa "ovia" tai vain avata 
huoneet jostain kohtaa avoimiksi. Tämän pitäisi onnistua virittävän puun avulla. Koodia pitäisi myös hieman refaktoroida, sillä nyt 
lähes kaikki koodi on samassa luokassa. Voisin esimerkiksi tehdä huoneesta oman luokan, joka selkeyttäisi useita asioita.

Vaikeuksia tuotti hieman testaus. Varsinkin void-metodieni, kuten placeRoom-metodin testausta en saanut toteutettua, sillä huoneen paikka 
ja koko on satunnainen, ja metodi ei palauta mitään, mistä voisin tarkistaa onko huone oikean kokoinen ja oikealla kohdalla. Myöskään 
placeCorridors-metodia en saanut testattua osittain samasta syystä ja varsinkin rekursiivisen metodin floodFill-testaus on itselleni 
hyvin hämärä. Testaukseen mielelläni otan jotain vinkkejä.




