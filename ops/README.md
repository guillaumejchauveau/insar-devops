## Setup for demo

### Create namespace

```bash
kubectl create ns game-demo
```

### Private image registry access

Create token at https://gitlab.insa-rennes.fr/devops-2022/iacone-dabat-chauveau/-/settings/access_tokens with `read_registry` access, save it in `./registry_token`.
If it doesn't work use a GitLab personal access token instead.

```bash
# Creates image pull secret in game-demo namespace.
kubectl -n game-demo create secret docker-registry insa-gitlab-registry --docker-server=gitlab.insa-rennes.fr:5050 --docker-username=gitlab-token --docker-password=$(cat ./registry_token)
# Configures the default service account to use the pull secret.
kubectl -n game-demo patch sa default -p '{"imagePullSecrets": [{"name": "insa-gitlab-registry"}]}'
```

### Apply demo kutomization

```bash
kubectl apply -k ops/manifests/demo
```
