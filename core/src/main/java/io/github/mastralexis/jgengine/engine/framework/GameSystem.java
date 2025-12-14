package io.github.mastralexis.jgengine.engine.framework;

public abstract class GameSystem {
    protected Scene scene;      // Holds the GameObjects and Systems of the Current Screen
    public int priority = 0;    // higher runs later (Render = 100, Input = 0)

    // Called when the Scene starts using this system
    public void attach(Scene scene) {
        this.scene = scene;
    }

    // Every class that extends GameSystem must implement that method
    public abstract void update(float delta);
}
