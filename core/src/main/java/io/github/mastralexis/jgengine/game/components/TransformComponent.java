package io.github.mastralexis.jgengine.game.components;

import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class TransformComponent implements GameComponent {
    public float x, y;
    public float rotation = 0; // In degrees
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;

    public TransformComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
    }
}
