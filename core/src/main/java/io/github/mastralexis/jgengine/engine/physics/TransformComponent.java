package io.github.mastralexis.jgengine.engine.physics;

import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class TransformComponent implements GameComponent {
    public float x, y;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float rotation = 0; // In degrees
    public boolean isHidden = false;

    public TransformComponent(float x, float y) {
        this.x = x;
        this.y = y;
        isHidden = false;
    }

    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
    }
}
