# JGEngine
**JGEngine** is a custom 2D Game Engine framework built on top of **LibGDX**.
It implements a "Component-Based Entity" architecture (Hybrid ECS), combining the modularity of an Entity Component System with the ease of use of Object-Oriented Programming.

## The Core Framework (`engine.framework`)
* **GameObject:** A container entity that holds data. It has a unique ID and a list of components.
* **GameComponent:** Pure data wrappers (e.g., `PositionComponent`, `VelocityComponent`).
* **GameSystem:** Logic processors that iterate over scenes (e.g., `RenderSystem`, `PhysicsSystem`).
* **Scene:** The manager that holds all active `GameObjects` of the current `Screen` and runs the `GameSystems`.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## How to Run
This project uses Gradle. You can run it directly from the terminal:
- Windows:
  - `gradlew lwjgl3:run`
- Linux / Mac:
  - `./gradlew lwjgl3:run`

> **Created by:** mastralexis  
> **Framework:** LibGDX (LWJGL3)  
> **Architecture:** Composition over Inheritance (Unity-Style)
