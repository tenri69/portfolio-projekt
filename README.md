# portfolio-projekt/Rekursion och fraktaler  (For English read down below)
Ett Java-projekt som visualiserar fraktaler och rekursiv triangeldelning i ett grafiskt gränssnitt byggt med Swing. Programmet använder en egen slump- och fördelningsfunktion för att skapa organiska och varierade mönster.

Detta projekt är ett test av rekursion där flera fraktaler implementeras i Java. Programmet använder ett färdigt Swing-baserat grafiskt gränssnitt. Jag har implementerat följande fraktaler:

- Kochs snöflinga
- Mountain-fraktal med triangeldelning
- Mountain-fraktal med slumpförskjutning av mittpunkter
- Mountain-fraktal utan sprickor, genom att återanvända mittpunkter mellan delade sidor

Projektet består av tre paket: fractal, koch och mountain.

## Paketbeskrivning

### fractal
Detta paket innehåller det grafiska användargränssnittet samt den abstrakta superklassen Fractal. Alla fraktaler som skapas ärver från Fractal. Klassen Point finns också här och representerar koordinater som används vid ritning.

### koch
Paketet innehåller klassen Koch som ritar Kochs snöflinga. Den centrala metoden är fractalLine, som använder rekursion för att dela en linje i fyra nya segment. Basfallet ritar en enkel linje. Vid order större än 0 delas linjen upp i fyra delar med längden length/3 och vinklarna alpha, alpha - 60, alpha + 60 och alpha.

### mountain
Paketet mountain innehåller min egen implementation av bergsfraktalen.

Version 1: triangeldelning utan slump.  
Version 2: triangeldelning med slumpförskjutning i y-led.  
Version 3: en version utan sprickor där mittpunkter återanvänds via en HashMap<Side, Point>.

Paketet innehåller även Side, som representerar en triangelkant, samt RandomUtilities som innehåller funktionen randFunc.

## Slumpfunktionen

```
public static double randFunc(double dev) {
    double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
    if (Math.random() < 0.5) {
        t = -t;
    }
    return t;
}
```

Denna funktion ger en störning som liknar en normalfördelning, vilket används för att skapa naturligare variationer i bergets form. Parametern dev halveras i varje rekursionsnivå.

## Sprickfria trianglar

För att undvika sprickor i bergsfraktalen används en HashMap<Side, Point>. Varje gång en triangel delas beräknas mittpunkter för dess tre sidor. Om en sida redan har delats tidigare hämtas samma mittpunkt från HashMap i stället för att beräkna en ny. Klassen Side implementerar egna versioner av equals och hashCode för att fungera som nyckel i en HashMap.

## Objektorienterade principer

Projektet använder polymorfism genom att alla fraktaler ärver från Fractal och hanteras på samma sätt av användargränssnittet. Jag utvecklade projektet stegvis och testade funktionerna visuellt, vilket liknar ett enkelt TDD-arbetssätt.

## Så här körs programmet

Starta FractalApplication.  
Välj fraktal i menyn.  
Ändra ordning med pilarna.  
Justera Delay för att styra animeringens hastighet.




----------------------------------------------------------------------------------------------------------------------------------------




# portfolio-projekt/Recursion and Fractals (English translation)


This is a test project in recursion where several fractals are implemented in Java. The program uses a prebuilt Swing-based graphical user interface. I implemented the following fractals:

- The Koch snowflake
- The Mountain fractal using triangle subdivision
- The Mountain fractal with random midpoint displacement
- A crack-free Mountain fractal that reuses midpoints between shared sides

The project is divided into three packages: fractal, koch, and mountain.

## Package descriptions

### fractal
This package contains the graphical user interface and the abstract superclass Fractal. All fractal classes inherit from Fractal. The Point class is also located here and represents coordinates used for drawing.

### koch
This package contains the Koch class, which draws the Koch snowflake. The main method is fractalLine, which uses recursion to divide a line into four smaller segments. The base case draws a simple line. For order values greater than 0, the line is divided into four segments with length length/3 and angles alpha, alpha - 60, alpha + 60, and alpha.

### mountain
The mountain package contains my implementation of the mountain fractal.

Version 1: triangle subdivision without randomness.  
Version 2: triangle subdivision with vertical random displacement.  
Version 3: a crack-free version where midpoints are reused using a HashMap<Side, Point>.

The package also includes Side, representing an edge of a triangle, and RandomUtilities, which contains the randFunc function.

## Random displacement function

```
public static double randFunc(double dev) {
    double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
    if (Math.random() < 0.5) {
        t = -t;
    }
    return t;
}
```

This function produces a value resembling a normal distribution. It is used to generate natural-looking variation in the mountain shape. The parameter dev is halved at each recursion level.

## Crack-free triangles

To prevent cracks in the mountain fractal, a HashMap<Side, Point> is used. When a triangle is subdivided, midpoints for its three sides are calculated. If the same side has already been processed earlier, the midpoint is retrieved from the HashMap rather than recalculated. The Side class provides custom equals and hashCode methods so it can be used as a valid key in a HashMap.

## Object-oriented principles

The project uses polymorphism since all fractals inherit from the Fractal superclass and are handled uniformly by the GUI. I implemented the project step by step and tested each part visually, following a simple TDD-like workflow.

## Running the program

Start FractalApplication.  
Choose a fractal from the menu.  
Use the arrow buttons to change the order.  
Adjust the Delay setting to control animation speed.
