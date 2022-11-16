Package "cards"
-> Clasa "Card":
    Este o clasa facuta pentru a fi mostenita de clasele "Minion" si "Environment". Aceasta clasa
are forma basic a unei carti de joc. In ea exista doar copy constructors, setter-e si getter-e.

-> Clasa "Environment":
    Aceasta clasa nu prea face ceva. Ea mosteneste "Card".

-> Clasa "Minion":
    Aceasta clasa are si atributele "health" si "attackdamage", atribute care lipsesc la cartile
de tip "Environment". In aceasta clasa exista metodele "Attack", care ataca o carte de tip
"Minion" sau "Hero". De asemenea exista metoda "useAbility" care nu face nimic aici, dar este
rescrisa in clasele facute pentru cartile care au abilitati.

-> Restul claselor:
    Majoritatea sunt facute doar pentru a exista tipul respectiv in deck. Doar cele care
au abilitati speciale au metodele "useAbility" care suprascriu metoda de la "Minion".

Package "classes.myClasses"
-> Clasa "Actions":
    In aceasta clasa se administreaza comenzile. Mai exista si metodele "endTurn" care schimba
jucatorul curent si se trece la o runda noua (daca este cazul) si metoda "placeCard" care
apeleaza metoda "placeCard" din clasa "Table".

-> Clasa "Game":
    Aici se intampla efectiv jocul. Pentru inceput avem metoda "gamePrep" in care se "pregateste"
jocul (se amesteca deckurile si se trage cate o carte din ele), constructorul care seteaza jocul,
comanda de tras carti si metoda "newRound" in care se trece la o runda noua. Tot aici exista si
metoda care afisaza cartile de tip Environment din mana. Parametrii se inteleg din nume ce fac,
dar playerii sunt pusi intr-un array de tipul Player pentru a putea lucra cu amandoi in acelasi timp.

-> Clasa "Player":
    Aici sunt definiti playerii si atributele lor. Fiecare player are cate un erou, un deck din
din care trage carti si o mana unde se pun cartile trase. De asemnea playerii au mana.

-> Clasa "DeckInit":
    In aceasta clasa se initializeaza deckul trecand fiecare carte de la tipul "CardInput" la
tipul corect de carte.

-> Clasa "Error":
    In aceasta clasa sun salvate "starea" de eroare (daca a fost vreo eroare pana acum) si
mesajul de eroare.

-> Clasa "Statistics":
    Aici sunt salvate statusurile playerilor (jocuri jucate, jocuri castigate de fiecare player).

-> Clasa "Table":
    In aceasta clasa se intampla toate actiunile care au legatura cu masa. Ca parametrii sunt
doar un vector de array lists in care sunt cartile de pe masa si playerul curent. Dar aici sunt
definite toate metodele care au legatura cu masa. Metoda "placeCard" in care se pune cartea pe
randul corespunzator playerului, "getCardPosition" care reutrneaza pozitia cartii cerute,
"useEnvCard" in care se foloseste abilitatea unuei carti de tipul Environment, "getFrozenCards"
care returneaza toate cartile de pe masa care sunt inghetate, "attack" unde se verifica daca
toate datele sunt corecte inainte de a ataca folosind metoda din "Minion", 'isEnemyTank' care
verifica daca exista o carte de tip "Tank" pe randul inamic, "useCardAbility" care foloseste
abilitatea cartii care are abilitate, "attackHero" care ataca eroul inamic daca nu exista
tanci inamici prezenti, "checkHeroHealth" in care se verifica viata eroului si se anunta
castigatorul in cazul in care eroul moare, "useHeroAbility" in care se foloseste abilitatea
eroului si "checkHealth" in care se verifica daca fiecare carte re pe masa mai are viata
si daca nu se sterge de pe masa.s