# Setup for demo

## Game demo

### Create namespace

```bash
kubectl create ns game-demo
```

### Private image registry access

Create deploy token at https://gitlab.insa-rennes.fr/devops-2022/iacone-dabat-chauveau/-/settings/repository with `read_registry` access, save it in `./registry_token` and note the username.
If it doesn't work use a GitLab personal access token instead.

```bash
# Creates image pull secret in game-demo namespace.
kubectl -n game-demo create secret docker-registry insa-gitlab-registry --docker-server=gitlab.insa-rennes.fr:5050 --docker-username=**deploy token username** --docker-password=$(cat ./registry_token)
# Configures the default service account to use the pull secret.
kubectl -n game-demo patch sa default -p '{"imagePullSecrets": [{"name": "insa-gitlab-registry"}]}'
```

### Apply demo kustomization

```bash
kubectl apply -k manifests/demo
```

## Monitoring demo

```bash
kubectl create ns monitoring
kubectl apply -k monitoring/demo
```

Access visualisation at http://localhost:30300 (credentials: admin, admin). Go to `Explore` tab.

#### Logs
Choose `Loki` datasource to explore logs. Use label `app_kubernetes_io_name` to select `backend` or `frontend` pods.

#### Metrics
Same as logs but with `Prometheus` datasource.

#### Traces
Choose `Tempo` datasource. Only service available corresponds to `backend` pods.
