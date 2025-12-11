package io.github.mastralexis.jgengine.game.components;

import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class HealthComponent implements GameComponent {
    public int health;

    public HealthComponent(int health) { this.health = health; }
}
