package io.github.mastralexis.jgengine.game.components;

import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class VelocityComponent implements GameComponent {
    public float x, y;
    public float speed;

    public VelocityComponent(float speed) {
        this.x = 0;
        this.y = 0;
        this.speed = speed;
    }
}
