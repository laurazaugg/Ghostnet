# Ghostnet - Readme
## Aufgabenstellung 3: Ghost Net Fishing
Du arbeitest im Web-Development-Team der großen Non-Profit-Organisation „Shea Sepherd“. Es soll ein neues
Projekt gestartet werden, mit dem Ziel, eine Web-App für den das Melden und Bergen von sogenannten
Geisternetzen zu entwickeln. Geisternetze sind herrenlose Fischernetze, die im Meer treiben. Der Requirements
Engineer hat sich bereits im Vorfeld mit ausgewählten Stakeholdern in der Organisation und der Fischerei
ausgetauscht und dazu die folgende Anforderung in das Fachkonzept geschrieben:

Ein Geisternetz hat die folgenden Eigenschaften:
- Standort (GPS-Koordinaten),
- geschätzte Größe und
- Status.

Der Status eines Geisternetzes kann folgende Ausprägungen haben:
- Gemeldet (Eine meldende Person hat das Geisternetz im System erfasst.)
- Bergung bevorstehend (Eine bergende Person hat die Bergung angekündigt.)
- Geborgen (Eine bergende Person hat das Geisternetz erfolgreich geborgen.)
- Verschollen (Eine beliebige Person hat festgestellt, dass das Geisternetz am gemeldeten Standort nicht
auffindbar ist.)

Es gibt meldende und bergende Personen. Beide Arten von Personen sind natürliche Personen mit einem Namen
und einer Telefonnummer für Rückfragen. Meldende Personen können anonym bleiben und brauchen dann keine
Telefonnummer. Man kann Netze aber nicht anonym als verschollen melden. Um unnötige Bergungsfahrten und
Missverständnisse zu vermeiden, können Geisternetze immer nur maximal einer bergenden Person zugeordnet
werden. Bergende Personen können allerdings mehr als ein Geisternetz gleichzeitig bergen.
Dazu hat er die folgenden User Stories im Product Backlog hinterlegt und nach der MoSCoW-Methode priorisiert:
1. MUST: Als meldende Person möchte ich Geisternetze (anonym) erfassen können.
2. MUST: Als bergende Person will ich mich für die Bergung eines Geisternetzes eintragen können.
3. MUST: Als bergende Person möchte ich sehen, welche Geisternetze noch zu bergen sind.
4. MUST: Als bergende Person möchte ich Geisternetze als geborgen melden können. 
5. COULD: Als bergende Person möchte ich sehen können, wer welche Geisternetze bergen möchte, um sich
   ggf. abzustimmen und die Bergungen umzuverteilen.

## Technologie-Stack
- Java
- Jakarta EE
  - CDI
  - JSF
  - JPA
- MySQL
- Maven
- TomEE
- CSS

## Projektaufbau
- `ghostnet.beans` - Beans
- `ghostnet.model` - JPA-Entities
- `ghostnet.service` - Datenbankzugriff
- `pages/` - JSF-Seiten
- `resources/` - CSS und Images

## MySQL einrichten
### 1. Datenbank anlegen
```
CREATE DATABASE ghostnet;
USE ghostnet;

CREATE TABLE user (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE ghostnet (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
latitude DOUBLE,
longitude DOUBLE,
size INT,
name VARCHAR(100),
number VARCHAR(50),
status VARCHAR(50),
recoverer VARCHAR(100)
);
```

### 2. Beispielnutzer anlegen
Zur Nutzung der Loginseite braucht es einen Nutzer mit dem Sie sich einloggen können.
Als Beispiel:

```
INSERT INTO user (username, password) VALUES ('Max Muster', 'login123');
```

### 3. Konfiguration
```
<persistence-unit name="ghostnetPU" transaction-type="JTA">
   <jta-data-source>jdbc/ghostnetDS</jta-data-source>
   <properties>
      <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/ghostnet"/>
      <property name="jakarta.persistence.jdbc.user" value="root"/>
      <property name="jakarta.persistence.jdbc.password" value="passwort"/>
      <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
      <property name="openjpa.jdbc.DBDictionary" value="mysql"/>
   </properties>
</persistence-unit>
```
## Anwendung starten
1. Projekt mit maven bauen:
   ```
   mvn clean install
   ```
2. WAR-Datei deployen
3. in Browser öffnen
   (http://localhost:8080/ghostnet)
