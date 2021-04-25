[![Build Status](https://travis-ci.org/juncevich/relay-system.svg?branch=master)](https://travis-ci.org/juncevich/relay-system)
[![codecov](https://codecov.io/gh/juncevich/relay-system/branch/master/graph/badge.svg)](https://codecov.io/gh/juncevich/relay-system)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fba092c64a384a2d84b54f222b972039)](https://www.codacy.com/app/juncevich/relay-system?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=juncevich/relay-system&amp;utm_campaign=Badge_Grade)
## Relay system

Data Service:
 
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=relay-data-service&metric=alert_status)](https://sonarcloud.io/dashboard?id=relay-data-service)

Config Server: 

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=relay-config-server&metric=alert_status)](https://sonarcloud.io/dashboard?id=relay-config-server)


## Setup

* install nodejs
* install npm
* install Angular cli


Run services in local r8s:

skaffold dev --port-forward --trigger=manual --no-prune

Install postgres to k8s

helm install pg-release -f data-service/k8s/pg_values.yaml bitnami/postgresql
https://artifacthub.io/packages/helm/bitnami/postgresql

Design prototype:

https://ninjamock.com/s/HMR47Wx


