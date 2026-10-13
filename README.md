# TP1-DAR2026 : Communication Client/Serveur avec les sockets TCP

**Nom et prénom :** Aicha Masmoudi

**Filière et Groupe :** LSI3.1.1

## Étape 1 : Vérification de Git
J'ai vérifié que Git est installé avec la commande `git --version`.
La version installée est 2.56.0.


## Étape 2 : Vérification de Java
J'ai vérifié la version de Java avec `java --version`.
La version installée est OpenJDK 17.0.17.


J'ai ensuite créé le répertoire de travail `Aicha Masmoudi_LSI3_DevAppReparties` qui contient le projet `TP1-DAR2026`.


## Étape 3 : Création du serveur
J'ai créé le package `serverPackage` et la classe `Server`.

Résultat au lancement du serveur :


![serveur en attente](images/serveur-attente.png)

**Interprétation :** le serveur crée une socket d'écoute sur le port 1234, puis reste bloqué sur `accept()` en attendant la connexion d'un client.

## Étape 4 : Création du client
J'ai créé le package `clientPackage` et la classe `Client`.

Résultat côté client :

![client](images/client.png)


Résultat côté serveur après la connexion du client :

![serveur connecté](images/resultat-cote-serveur.png)


**Interprétation :** le client se connecte au serveur et lui envoie le nombre 5. Le serveur le multiplie par 3 et renvoie 15, que le client affiche. Ensuite les deux sockets se ferment.


# Activité 1-2 : Service de calculatrice

## Étape 1 : Préparation de l'espace de travail

## Étape 2 : Service de multiplication

### Test avec DataInputStream / DataOutputStream
![test positif](images/test-positif.png)


**Interprétation :** le client envoie 1, le serveur renvoie 5 (1 × 5).

![test négatif](images/test-negatif.png)


**Interprétation :** avec `writeInt`/`readInt`, le négatif -4 donne bien -20,
alors qu'avec `read()`/`write()` (un seul octet) le résultat serait faux.

![test zéro](images/test-zero.png)

### Version avec boucle
![session complète client](images/boucle-client.png)
![session complète serveur](images/boucle-serveur.png)


**Interprétation :** le client enchaîne plusieurs saisies, et la saisie de 0
arrête proprement la communication : les sockets sont fermés des deux côtés.