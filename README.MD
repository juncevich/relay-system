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

Install postgres to k8s helm repo add bitnami https://charts.bitnami.com/bitnami
helm install pg-release -f data-service/k8s_pg/pg_values.yaml bitnami/postgresql
https://artifacthub.io/packages/helm/bitnami/postgresql

Design prototype:

https://ninjamock.com/s/HMR47Wx

kubectl create namespace data
kubectl create namespace history
kubectl create namespace gateway
kubectl create namespace postgres
kubectl create namespace mongo

kubectl apply -f ../k8s/rbac-cluster-role.yaml -n default

kubectl create serviceaccount api-service-account -n data
kubectl create serviceaccount api-service-account -n history
kubectl create serviceaccount api-service-account -n gateway
kubectl create serviceaccount api-service-account -n postgres
kubectl create serviceaccount api-service-account -n mongo

kubectl create clusterrolebinding service-pod-reader-data --clusterrole=microservices-kubernetes-namespace-reader
--serviceaccount=data:api-service-account
kubectl create clusterrolebinding service-pod-reader-history --clusterrole=microservices-kubernetes-namespace-reader
--serviceaccount=history:api-service-account
kubectl create clusterrolebinding service-pod-reader-gateway --clusterrole=microservices-kubernetes-namespace-reader
--serviceaccount=gateway:api-service-account
kubectl create clusterrolebinding service-pod-reader-postgres --clusterrole=microservices-kubernetes-namespace-reader
--serviceaccount=postgres:api-service-account
kubectl create clusterrolebinding service-pod-reader-mongo --clusterrole=microservices-kubernetes-namespace-reader
--serviceaccount=mongo:api-service-account

Planned services:

1. Timeline service, for presenting operations history. Need to be realised on reactive spring boot.
2. Feedback service. Simple REST service on Golang.