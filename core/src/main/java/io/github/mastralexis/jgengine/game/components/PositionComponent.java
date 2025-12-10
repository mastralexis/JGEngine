package io.github.mastralexis.jgengine.game.components;

import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class PositionComponent implements GameComponent {
    public float x, y;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
