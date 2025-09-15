# 🚀 Concourse CI/CD Demo — Spring Boot + Docker

Ce projet est une démonstration de pipeline **Concourse CI/CD** permettant de :
- Récupérer le code source depuis GitHub
- Compiler et tester une application **Spring Boot**
- Construire une image Docker
- Pousser l’image sur **Docker Hub**

---

## 📦 Prérequis

- [Docker](https://docs.docker.com/get-docker/) installé
- [Docker Hub](https://hub.docker.com/) compte + PAT (Personal Access Token)
- [Concourse](https://concourse-ci.org/) installé (ex: via `docker-compose` ou `kind`)
- [fly CLI](https://concourse-ci.org/fly.html) installé

---

## 🔑 Configuration

Crée un fichier `ci/params.yml` (non versionné, ajoute-le à `.gitignore`) :

```yaml
github_uri: https://github.com/wilydamtchou/concourse-demo.git
github_username: ton-username
github_token: ton-pat-token
docker_repository: willyydamtchou/spring-concourse-demo
docker_username: ton-username
docker_token: ton-pat-token

## 🔑 Connexion à Concourse

```bash
fly -t tutorial login -c http://localhost:8080 -u test -p test
```

- `-t tutorial` : alias (target) de ton serveur Concourse
- `-c` : URL du web UI Concourse
- `-u` / `-p` : utilisateur et mot de passe

---

## ⚙️ Gestion des pipelines

### Définir un pipeline
```bash
fly -t tutorial set-pipeline -p demo-pipeline -c pipeline.yml --load-vars-from ci/params.yml
```

### Activer un pipeline
```bash
fly -t tutorial unpause-pipeline --pipeline demo-pipeline
```

### Supprimer un pipeline
```bash
fly -t tutorial destroy-pipeline -p demo-pipeline
```

---

## ▶️ Lancer et suivre les jobs

### Déclencher manuellement un job
```bash
fly -t tutorial trigger-job -j demo-pipeline/build-and-push
```

### Suivre les logs d’un job
```bash
fly -t tutorial watch -j demo-pipeline/build-and-push
```

---

## 🔍 Inspection et debug

### Lister les pipelines existants
```bash
fly -t tutorial pipelines
```

### Lister les jobs d’un pipeline
```bash
fly -t tutorial jobs -p demo-pipeline
```

### Intercepter un conteneur pour debug
```bash
fly -t tutorial intercept -j demo-pipeline/build-and-push
```

---

## 📝 Notes

- `tutorial` est le **target name** (modifiable).
- `demo-pipeline` est le **nom du pipeline** (modifiable).
- `secrets.yml` contient les **secrets** (Docker Hub, GitHub, etc.).