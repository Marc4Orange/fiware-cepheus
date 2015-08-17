# Cepheus Broker manual

## Introduction

The Cepheus-broker component is a lightweight broker only supporting two kinds of operations:

- requests forwarding by keeping track of which components register Context Entities.
- pub/sub requests for Context Entities.

This keeps the implementation simple and sufficient for the use cases handled by the NGSI gateway.

The main goal of the Cepheus-broker is to sit between the IoT Agents or NGSI devices, forward their request to a remote NGSI broker (like Orion)
while allowing other NGSI components to subscribe to some the the updated Context Elements.

![broker](../fig/broker.png)

## Forwarding support

Request forwarding is base on the fact that all NGSI components will register their Context Entities on startup.

The broker will track these `registerContext` requests (keeping a list of all `providingApplication`)
before forwarding them back to the remote broker.

Then when `updateContext` or `queryContext` requests arrive, they will be either forwarded to the remote broker if no `providingApplication` matches
or forwarded to the corresponding `providingApplication`.

[broker forward](../fig/broker-forward.png)

## Pubsub support


