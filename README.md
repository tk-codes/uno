# UNO - Card Game
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tk-codes_uno&metric=coverage)](https://sonarcloud.io/dashboard?id=tk-codes_uno)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=tk-codes_uno&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=tk-codes_uno)

> Rebuilt the core game logic in domain-driven-design.

![uno-ui](./doc/new-uno-v2.gif)
*Note: GUI is built merely to try out the core domain logic. It is not suitable for serious game.*

## Instructions

**Run Application**

Run the `main` method in the UnoApp class or use the following command in the terminal:

```
./gradlew run
```

**Run Unit Tests**

```
./gradlew test
```

## Event Storming

![event-storming](./doc/event-storming.jpg).

## Core Domain

The core game logic is built according to [official uno rules](https://en.wikipedia.org/wiki/Uno_(card_game)#Official_rules).

### Uno Card

Cards are value objects, i.e. immutable. The following card types are available in Uno: 
* Number Card
* Skip Action Card
* Reverse Action Card
* Draw Two Action Card
* Wild Color Card
* Wild Draw Four Card

Initially wild cards don't have a color. When drawn, a new value object is created with the chosen color. 

![card-uml](./doc/cards.png)


