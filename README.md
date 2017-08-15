# sandbox
REST Spring &amp; Angular 4 


## Instrukcja stawiania srodowiska
### Backend: 
1. Postaw lokalnie baze mysql na porcie 3312 i ustaw dla uzytkownika root haslo "root" oraz utworz w niej SCHEMA o nazwie SPRING_SANDBOX
2. Wykonaj polecenie "mvn flyway:migrate"
3. Wykonaj polecenie "mvn clean install" 
4. Odpal aplikacje 
### Frontend: 
1. Zainstaluj angular Cli komenda "npm install -g @angular/cli"
2. Wejdz w folder frontend wykonaj komende "npm install" 
3. Wykonaj polecenie "ng serve" Aplikacja powinna byc dostepna pod adresem localhost:4200
