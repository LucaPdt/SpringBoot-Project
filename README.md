# Concessionaria API - Spring Boot

Questa è un'applicazione Spring Boot che espone delle REST API per la gestione di una concessionaria. Consente di effettuare operazioni CRUD su un database, che include:

- Una tabella per la gestione delle automobili
- Tabelle per la gestione delle utenze e dei ruoli dell'applicazione

### Funzionalita' principali:

- Operazioni CRUD sulle varie tabelle del database
- Autenticazione e autorizzazione con Spring Security con token JWT
- Test di unita' per verificare il funzionamento

### Principali teconlogie utilizzate:

- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- JUnit & Mockito
- OpenApi (Swagger) per la documentazione delle API
- Git

## Struttura principale:

Il codice è strutturato in vari package:

- __model__:
  - __entity__: Classi che rappresentato le entità nel database, ogni entità è mappata a una tabella nel database
  - __dto__: I principali DataTransferObject in ingresso, utilizzati per la validazione e il trasferimento dei dati tra i vari layer dell'applicazione 
  - __response__: I principali DTO in uscita, utilizzati per formattare le risposte delle API
- __repository__: Contiene le repository JPA relative alle entità
- __service__: Classi che si occupano dell'interazione con i Repository
- __command__: Classi che gestiscono la logica di business e si interfacciano con i Service
- __controller__: Classi che ricevono le richieste HTTP

<br>

- __exception__: Contiene le classi relative alla gestione delle eccezioni. Include un ExceptionHandler, Response per le eccezioni, e eccezioni personalizzate per migliorare la gestione degli errori
- __security__: Contiene le classi relative alla sicurezza, come la gestione dell'autenticazione tramite JWT e la protezione delle risorse tramite Spring Security
- __configuration__: Classi di configurazione

<br>

Vi sono poi nella directory di Test alcuni test unitari.

# Guida per Avviare e Testare l'applicazione

Questa guida ti aiuterà a clonare l'applicazione Spring Boot, configurare il database e testare l'applicazione.

Segui questi passaggi per configurare l'ambiente di sviluppo e testare l'applicazione.

## Prerequisiti

Assicurati di avere i seguenti strumenti installati sul tuo sistema:

- [Java JDK 17](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)

In alternativa (__Scelta consigliata__) utilizza Intellij Community

E' inoltre necessario un sistema di gestione del database (Lo script allegato e' creato per PostgreSQL, potrebbe essere necessario modificare lo script nel caso si utilizzasero DBMS differenti)

## Passo 1: Clonare la Repository GitHub

Clona la repository contenente l'applicazione Spring Boot da GitHub. Esegui il seguente comando nella tua shell:

```
git clone https://github.com/LucaPdt/Challenge-AutoXY.git
cd <nome_repository>
```

## Passo 2: Creare il Database (Postgres)

Crea il database:

```
psql -U <tuo_utente> -d postgres -c "CREATE DATABASE <nome_database>;"
```

Esegui lo script allegato nel database appena creato

> Assicurati di eseguire il comando della directory del file `script.sql` oppure specifica il path del file

```
psql -U <tuo_utente> -d <nome_database> < script.sql
```

Dettagli:

- `-U <tuo_utente>` specifica l'utente PostgreSQL
- `-d postgres` Specifica il database a cui connettersi (puoi usare postgres come database predefinito per eseguire lo script di creazione del database).
- `< script.sql` Esegue il file SQL come input.

  `f script.sql` Alternativa che esegue direttamente il file.

## Passo 3: Aggiorna application.properties

Aggiorna la configurazione per la connessione al database nel file `application.properties` inserendo i datasource corretti e cambiando il `datasource.driver` nel caso non si utilizzasse PostgreSQL.

Nel caso si fosse cambiato DBMS aggiornare anche `pom.xml` inserendo le dipendenze maven per il DBMS scelto.

## Passo 4: Compilare e avviare l'applicazione

Una volta che hai configurato correttamente il database, puoi compilare e avviare l'applicazione Spring Boot con Maven.

Esegui i seguenti comandi:

### 4.1: Compilare l'applicazione

> Comando Mawen Wrapper, per eseguire i comandi Maven senza installarlo manualmente </br> Spring Boot include Maven Wrapper nel progetto, quindi puoi usare mvnw invece di mvn.


```
.\mvnw clean install
```

### 4.2: Testare l'applicazione

Verifica che i test eseguino correttamente

```
.\mvnw test
```

Una esecuzione dei test corretta terminera' con una serie di messaggi di questo tipo:

```
[INFO] Results:
[INFO]
[INFO] Tests run: 26, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.552 s
[INFO] Finished at: 2025-02-20T18:10:04+01:00
[INFO] ------------------------------------------------------------------------
```

### 4.3: Avviare l'applicazione

```
.\mvnw spring-boot:run
```
L'applicazione sarà avviata e sarà possibile accedervi all'indirizzo predefinito, solitamente `http://localhost:8080`.

## Passo 5: Creazione delle utenze

In quanto il Programma utilizza un sistema di autenticazione e' necessario creare delle utenze per poter accedere agli endpoint.

Segui questi passaggi per creare una utenza `ADMIN` che ci permettera' di accedere a tutti gli endpoint

### 5.1 Crea utenza USER

Tramite un Client come Postman (oppure da [swagger](http://localhost:8080/swagger-ui/index.html)) manda una richiesta `POST` con body:

```json
{
  "username": "username",
  "password": "password"
}
```

all'endpoint `http://localhost:8080/api/auth/register`

Questo creera' una utenza `USER` necessaria per passare i sitemi di autenticazione di base.

<br>

### 5.2: Commentare @PreAuthorize

Apri il file AuthenticationController e commenta la riga 34: `@PreAuthorize("hasAuthority('ROLE_ADMIN')")`

Questo passaggio disabilitera' il controllo sul ruolo nella creazione di utenze personalizzate. (ci servira' per creare un admin)

### 5.3: Crea utenza ADMIN

Ricompila e Riavvia l'applicazione (Step 4.1 e 4.2) e tramite un Client come Postman (oppure da [swagger](http://localhost:8080/swagger-ui/index.html)) manda una richiesta `POST` con body:

```json
{
  "username": "username",
  "password": "password",
  "role": {
    "name": "ROLE_ADMIN"
  }
}
```

all'endpoint `http://localhost:8080/api/auth/register/role`

<br>

Questo passaggio Creera' una utenza con rulo `ADMIN`

### 5.4: Scommentare @PreAuthorize

Scommenta la riga 34 `@PreAuthorize` commentata in precedenza, ricompila e riavvia l'applicazione.

### 5.5: Effettua il login con l'utenza ADMIN

Invia una richiesta `POST` con body:
```json
{
"username": "tuo username",
"password": "tua password"
}
```

all'endpoint `http://localhost:8080/api/auth/login`

<br>

Se hai eseguito i passaggi correttamente riceverai come response un token di accesso.

Copia questo token di accesso e inseriscilo nel campo: `Authorization -> BearerToken -> Token` di Postman per avere accesso a tutte le API.

Oppure se si sta utilizzando Swagger effettua il login con il token premendo sul pulsante `Authorize`.