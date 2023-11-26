# Annuaire Téléphonique Java Swing

Ce projet Java Swing implémente un annuaire téléphonique interactif. L'application permet à l'utilisateur de visualiser et d'enregistrer des informations sur les contacts téléphoniques associés à un nom spécifique.

## Fonctionnalités

- **Interface Utilisateur (UI):** L'application utilise une interface graphique avec Java Swing pour offrir une expérience conviviale à l'utilisateur.

- **Connexion à la Base de Données:** L'application se connecte à une base de données MySQL locale pour récupérer et afficher les contacts associés au nom d'utilisateur.

- **Enregistrement des Contacts:** Les utilisateurs peuvent enregistrer les contacts dans un fichier JSON local en utilisant le bouton "Enregistrer".

## Prérequis

- Java Development Kit (JDK)
- MySQL Server (local)
- Bibliothèque MySQL Connector/J (Inclus dans le dossier lib)

## Instructions d'utilisation

1. **Configuration de la Base de Données:**
   - Assurez-vous que MySQL Server est en cours d'exécution.
   - Créez une base de données nommée "gtele" avec un utilisateur "root" (mot de passe vide).
   - Exécutez le script SQL fourni (`gtele.sql`) pour créer la table nécessaire (`clientname`).

2. **Configuration du Projet:**
   - Assurez-vous d'avoir le JDK installé sur votre machine.
   - Ajoutez le fichier JAR de MySQL Connector/J à votre projet pour la connectivité à la base de données.

3. **Exécution de l'Application:**
   - Exécutez le fichier `AnnuaireTelephonique.java`.
   - Assurez-vous que l'interface graphique s'affiche correctement.

4. **Utilisation de l'Application:**
   - Saisissez le nom d'utilisateur dans le champ de texte.
   - Les contacts associés à ce nom seront affichés dans la liste.
   - Utilisez le bouton "Enregistrer" pour sauvegarder les contacts au format JSON.

5. **Fermeture de l'Application:**
   - Assurez-vous de fermer correctement la connexion à la base de données en utilisant la méthode `fermerConnexionBD()`.

## Remarques

- Le code utilise des bibliothèques standard de Java ainsi que la bibliothèque Swing pour l'interface utilisateur.
- Le fichier JSON est enregistré avec le nom d'utilisateur et un horodatage pour éviter les conflits.

## Auteur

Ce projet a été développé par [El makhfi Mohamed].

N'hésitez pas à explorer et à personnaliser le code selon vos besoins. Si vous rencontrez des problèmes ou si vous avez des questions, n'hésitez pas à ouvrir une issue sur ce référentiel. Merci de l'utiliser!
