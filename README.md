# Ghostnet - Readme
## Aufgabenstellung 3: Ghost Net Fishing
Du arbeitest im Web-Development-Team der großen Non-Profit-Organisation „Shea Sepherd“. Es soll ein neues
Projekt gestartet werden, mit dem Ziel, eine Web-App für den das Melden und Bergen von sogenannten
Geisternetzen zu entwickeln. Geisternetze sind herrenlose Fischernetze, die im Meer treiben. Der Requirements
Engineer hat sich bereits im Vorfeld mit ausgewählten Stakeholdern in der Organisation und der Fischerei
ausgetauscht und dazu die folgende Anforderung in das Fachkonzept geschrieben:

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
