# Brick Breaker

A two-level brick-breaking game built in Java, featuring power-ups, heart-shaped brick layouts, and a full save/load system.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Gameplay](#gameplay)
- [Power-Ups](#power-ups)
- [Levels](#levels)
- [Controls](#controls)
- [Screens and Flow](#screens-and-flow)
- [Getting Started](#getting-started)

---

## Overview

Brick Breaker is a classic arcade-style game where you control a paddle to bounce a ball and destroy bricks. Clear all the bricks to advance through two unique levels, collect power-ups to gain temporary abilities, and try not to run out of lives!

---

## Features

- Main menu with New Game, Load Game, and Quit options
- Two hand-crafted levels with distinct brick layouts
- Randomly generated bricks in structured and heart-shaped patterns
- Multi-hit bricks with varying durability (brick levels)
- Power-up system with timed effects
- Pause and resume functionality
- Save and load game state
- Congratulatory dialogue on level completion
- Winner screen on full game completion
- Game Over screen with instant restart

---

## Gameplay

You control a paddle at the bottom of the screen. A ball bounces around and destroys bricks on contact. Each brick has a durability level and requires a set number of hits to destroy. After breaking bricks, power-ups may randomly drop at intervals. Move your paddle to catch them and gain special abilities.

You have a limited number of lives. Every time the ball falls below your paddle, you lose one life. If all lives are lost, it is game over. When all bricks in a level are cleared, you advance to the next level.

---

## Power-Ups

Power-ups drop randomly after you break a certain number of bricks. They fall toward the bottom of the screen and must be caught with your paddle to activate. Some are temporary and expire after a short duration, returning the paddle to its normal state.

| Power-Up | Effect | Duration |
|----------|--------|----------|
| Fireball | The ball burns through bricks with increased power | Timed |
| Bullet | Shoot projectiles from the paddle to destroy bricks directly | Timed |
| (others) | Additional power-ups may appear during gameplay | Varies |

---

## Levels

### Level 1 - Structured Layout

Bricks are generated in a structured, organized grid pattern. The layout is randomized each time you start a new game, so no two playthroughs are exactly the same. Clear all bricks to complete the level.

### Level 2 - Heart Shape

Bricks are arranged in a heart shape. This level loads automatically after completing Level 1. A congratulatory dialogue box is shown before the level begins. Clear the heart to win the game.

---

## Controls

| Key / Action | Function |
|---|---|
| Mouse / Arrow Keys | Move the paddle |
| Escape | Pause or resume the game |
| Enter | Restart from Game Over screen |

---

## Screens and Flow

```
Main Menu
   |
   |-- New Game --------> Level 1 (randomly generated layout)
   |                          |
   |                          |-- All bricks cleared --> Congratulations Dialogue --> Level 2 (heart layout)
   |                          |                                                            |
   |                          |                                                            |-- All bricks cleared --> Winner Screen
   |                          |
   |                          |-- Lives run out --> Game Over (press Enter to restart)
   |
   |-- Load Game -------> Resume from last saved point
   |
   |-- Quit ------------> Exit the game
```

---

## Getting Started

### Requirements

- Java Development Kit (JDK) 8 or higher
- A Java-compatible IDE (IntelliJ IDEA, Eclipse, NetBeans) or the command line

### Running the Game

**From the command line:**

```bash
# Compile
javac -d out src/**/*.java

# Run
java -cp out Main
```

**From an IDE:**

Open the project folder in your IDE, locate the `Main.java` entry point, and run it directly.

### Saving and Loading

The game saves your progress automatically when you pause and exit. To resume, select "Load" from the main menu and you will be placed back at the same level and state where you left off.

---

## Project Structure

```
BrickBreaker/
    src/
        Main.java              Entry point
        GamePanel.java         Core game loop and rendering
        Paddle.java            Paddle logic
        Ball.java              Ball physics and collision
        Brick.java             Brick types and durability
        PowerUp.java           Power-up behaviour and effects
        Level.java             Level generation and layout
        GameState.java         Save and load logic
    assets/
        sounds/                Audio files
        images/                Sprite and background assets
    README.md
```

---

## License

This project was created as a personal Java project. Feel free to use it for learning purposes.
