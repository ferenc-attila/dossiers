# Képesítő vizsga gyakorló feladat (Spring Boot JPA CRUD alkalmazás)
## Ügyiratkezelő

## Entitások

### Ügyintéző (Case manager)

- name (String)
- department (String)
- dossiers

### Ügyirat (Dossier)

- date of arrival (date)
- deadline (int)
- subject
- description
- department
- status (enum) SOLVED, UNANSWERED
- case manager

## REST API

- ügyintéző felvétele
- ügyintézők listázása, szűrés osztály alapján
- ügyintéző törlése, ügyiratok átruházásával
- ügyintéző beosztás és osztály módosítása

- ügyirat felvétele, ügyintéző hozzárendelése osztály, és megválaszolatlan ügyiratok száma alapján
- ügyiratok listázása (osztályra vagy ügyintézőre szűrés)
- megválaszolatlan ügyiratok listázása
- ügyirat statisztika (ügyintéző/megválaszolt, megválaszolatlan határidőn belül, megválaszolatlan határidőn túl) (opcionális)
- ügyirat státuszának módosítása