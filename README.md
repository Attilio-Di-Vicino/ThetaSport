# ThetaSport

ThetaSport is e-commerce created for a university project. It is built in javautilizing the MVC, DAO, Singleton, Strategy, Abstract Factory, Visitor and Chain of Responsibility patterns. The application uses an Artificial Intelligence algorithm, TF-IDF, to empower the admin to propose personalized offers to users.

## Descrizione

Il progetto prevede la realizzazione di un ecommerce di abbigliamento sportivo, con funzionalità per la gestione degli utenti, dei prodotti e degli ordini.

## Tecnologie utilizzate

- Java Development Kit (JDK) versione 17
- MySQL 8.0
- Apache Tomcat 10.1.71
- JSTL 1.2.1
- Bootstrap 5.0.0

## Configurazione

1. Clonare il repository:

    ```
    git clone https://github.com/Attilio-Di-Vicino/ThetaSport.git
    ```

2. Aprire il progetto in IntelliJ IDEA.

3. Configurare il database MySQL:

    - Creare un nuovo schema nel database con il nome `THETASPORTDB`.
    - esegui il file `THETASPORTDB/scriptDB.sql` per creare le tabelle e inserire i dati di esempio.

4. Configurare il server Tomcat:

    - Aggiungere una nuova configurazione di run per Tomcat.
    - Selezionare la cartella Tomcat come deployment directory.
    - Selezionare la cartella `web` come artifact.

5. Avviare il server Tomcat e accedere all'applicazione all'indirizzo `http://localhost:8080`.

## Crediti

Il progetto è stato realizzato da:

- Attilio Di Vicino
- Lorenzo Pergamo
- Mario Vista

## Licenze

Il progetto ThetaSport è rilasciato sotto la licenza MIT.


