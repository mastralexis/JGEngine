package io.github.mastralexis.jgengine.engine.framework;

public abstract class GameSystem {
    protected Scene scene;      // access the world
    public int priority = 0;    // higher runs later (Render = 100, Input = 0)

    // Called when the Scene starts using this system
    public void attach(Scene scene) {
        this.scene = scene;
    }

    public abstract void update(float delta);
}
