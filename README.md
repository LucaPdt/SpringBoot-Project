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

Aggiorna la configurazione per la connessione al database nel file `application.properties` inserendo i datasource corretti e cambiando il il `datasource.driver` nel caso non si utilizzasse PostgreSQL.

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

Una esecuzione dei test corretta terminera' con una messaggi di questo tipo:

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

### 5.1: Commentare @PreAuthorize

Apri il file AuthenticationController e commenta la riga 34: `@PreAuthorize("hasAuthority('ROLE_ADMIN')")`

Questo passaggio disabilitera' la sicurezza sulla creazione di utenze personalizzate. (ci servira' per creare un admin)

### 5.2: Crea utenza ADMIN

Ricompila e Riavvia l'applicazione (Step 4.1 e 4.2) e tramite un Client come Postman manda una richiesta `POST` con body:

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

Questo passaggio Creera' una utenza con rulo `USER`

### 5.2: Scommentare @PreAuthorize

Ricommenta la lina `@PreAuthorize` commentata in precedenza, ricompila e riavvia l'applicazione.

### 5.3: Effettua il login con l'utenza ADMIN

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