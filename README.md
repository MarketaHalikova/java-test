Poznámka k implemetaci:
- pro runtime i testing byla použita H2 databáze, v závislosti na tom byl zvolen způsob vložení modelových dat




Zadání:

Jde o jednoduchou aplikaci postavenou na Springu a RESTu, která slouží k evidenci JavaScriptových frameworků. Jako Java programátoři víme, že jejich svět je značně nepřehledný a nestálý. Proto je dobré evidovat jejich nejdůležitější vlastnosti na jednom místě. Tvým úkolem proto bude přidat k entitě frameworku vlastnosti “version“, “deprecationDate” a “hypeLevel”. Ty označují verzi frameworku, datum kdy bude/byl framework označen za zastaralý a aktuální úroveň fanatického iracionálního obdivu :-). Datové typy vol dle svého uvážení. Mysli na to, že verzí frameworku může být víc než jedna. Dále přidej možnost vytvářet nové frameworky a upravovat ty stávající. Stejně tak přidej možnost frameworky mazat. A nakonec přidej možnost mezi frameworky vyhledávat. Veškeré nově přidané funkce pokryj testy. Dokončený úkol nahraj do Git repozitáře a pošli nám na něj odkaz.

Co k tomu budeš potřebovat nainstalovat:
IDE (většinou tu používáme Eclipse, ale klidně použij IntelliJ IDEA nebo jiné IDE)
gradle (buildovací nástroj)
 
Jak to rozchodit:
Aplikaci lze spustit pomocí gradle, příkazem bootRun. Není to ale úplně potřeba. Stačí ověřit funkčnost pomocí testů.
