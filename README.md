# Evidence pojištění 

## Popis 

Simulace webové aplikaci pro práci s pojištenci a pojištěním.
Podporuje uživatelské a admin. rozhrání. Práva správce umožnují plnou práci s databázi (CRUD). 
Má 3 databázové tabulky (users,addresses,insurred) propojených pomocí foreign-key. 
Tabulky mají vazbu 1:1, kromě users - insurred (1:N).

## Jak se použivá

Vstupní stránka, po kliknuti na popisky, zobrazí produkty, které pojištovna nabízí.
Pro dalši práce s aplikaci uživatel má 2 možnosti: přihlášení nebo registrace. 
Po registraci uživatel se přihlásí, prográm zjístí zda-li uživatel je srpávce, a podle toho 
přesměruje na odpovidajicí stránky(profile uživatele nebo databáze). 
  V připadě uživatele, v profilu se zobrazí jméno a přijmení, adresa uživatele.
Po kliknuti na 'Sjednat' se zobrazí dropdown s produkty a uživatele bude požadano o vyplnění formy 
pro objednání pojištění.
  V připadě správce se zobrazí stránka s databázi uživatelu a možnosti manipulace s nimi : 
přidat dalšiho, smazat, zobrazit pojištění a přidělit práva správce. 
Po kliknuti na 'Zobrazit pojištění' se otevře stránka pro manipulaci s pojištěním dáneho uživatele: 
Smazat pojištění, upravit pojištění, přidat pojištění. 
  Aplikace má navíc 2 informační stránky(o nás, kontakty) a stránky pro prácee s chybové hlášky(400,404,500).
Pro ostatní chyby bude uživatel přesměrovan na stránku error. 

## Technologií použito

+ IntelliJ Idea
+ Spring-Boot
+ Maven
+ Java
+ HTML&CSS
+ JavaScript
+ Thymeleaf
+ PostgreSQL
+ Postman
+ Dia
+ Bootstrap
  
