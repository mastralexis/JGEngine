package io.github.mastralexis.jgengine.game.systems;

import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.game.components.PositionComponent;
import io.github.mastralexis.jgengine.game.components.VelocityComponent;

/**
 * Applies the velocity to the position components of all GameObjects
 */
public class MovementSystem extends GameSystem {

    public MovementSystem() {
        this.priority = 1;
    }

    @Override
    public void update(float delta) {
        for (GameObject go : scene.getGameObjects()) {
            if (go.hasComponent(PositionComponent.class) && go.hasComponent(VelocityComponent.class)) {
                PositionComponent pos = go.getComponent(PositionComponent.class);
                VelocityComponent vel = go.getComponent(VelocityComponent.class);
                pos.x += vel.x * delta;
                pos.y += vel.y * delta;
            }
        }
    }
}
