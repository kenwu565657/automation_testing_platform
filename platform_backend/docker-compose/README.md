# Docker Local Development

## Commands

```bash
cd docker

# ── Daily dev: infra only, run services in IDE ──
docker compose up -d

# ── Add Kibana + Kafka UI ──
docker compose --profile tools up -d

# ── Full stack in Docker ──
docker compose --profile services up -d

# ── Everything ──
docker compose --profile services --profile tools up -d

# ── Stop ──
docker compose --profile services --profile tools down

# ── Nuclear ──
docker compose down -v
```

## Ports

| Service         | Port   |
|-----------------|--------|
| Gateway         | 8080   |
| Admin (Spring)  | 8081   |
| Engine (Vert.x) | 8082   |
| Report (Ktor)   | 8083   |
| PostgresSQL     | 5432   |
| Kafka           | 9093   |
| Elasticsearch   | 9200   |
| Redis           | 6379   |
| Kibana          | 5601   |
| Kafka UI        | 9090   |
```