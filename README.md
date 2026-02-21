# JGEngine
**JGEngine** is a custom 2D Game Engine framework I'm trying to build on top of the **LibGDX** game framework.

This is primarily a **personal learning/hobby project** and very much **incomplete** with the goal to understand the internals of a game engine and modular software architecture in general. Currently, everything is code-driven. **But**, if it goes well the goal is to build a GUI on top it.

It implements a "Component-Based" architecture, combining the modularity of an Entity Component System with the ease of use of Object-Oriented Programming.


## The Core Framework (`engine.framework`)
* **GameObject:** A container entity that holds data. It has a unique ID and a list of components.
* **GameComponent:** The objects attached to GameObjects. Pure data wrappers (e.g., `PositionComponent`, `VelocityComponent`).
* **GameSystem:** Logic processors that iterate over scenes (e.g., `RenderSystem`, `PhysicsSystem`).
* **Scene:** The manager that holds all active `GameObjects` of the current `Screen` and runs the `GameSystems`.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3.

## How to Run
It uses Gradle. You can run it directly from the terminal:
- Windows:
  - `gradlew lwjgl3:run`
- Linux / Mac:
  - `./gradlew lwjgl3:run`

> **Created by:** mastralexis  
> **Framework:** LibGDX (LWJGL3)  
> **Architecture:** Composition over Inheritance
