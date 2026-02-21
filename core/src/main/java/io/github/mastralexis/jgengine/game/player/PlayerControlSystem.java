package io.github.mastralexis.jgengine.game.player;

import io.github.mastralexis.jgengine.engine.framework.Family;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.engine.physics.VelocityComponent;

// Translates Input from InputComponent into Velocity (intent to motion)
public class PlayerControlSystem extends GameSystem {

    private final Family family;

    public PlayerControlSystem() {
        this.family = Family.of(InputComponent.class, VelocityComponent.class);
    }

    @Override
    public void update(float delta) {
        for (GameObject obj : scene.getGameObjects(family)) {
            InputComponent input = obj.getComponent(InputComponent.class);
            VelocityComponent vel = obj.getComponent(VelocityComponent.class);

            // apply the Input to the Velocity
            // IMPORTANT: We only modify velocity if there IS input.
            // this allows knockback/external forces to degrade naturally if we aren't pressing keys.

            if (input.horizontal != 0 || input.vertical != 0) {
                vel.x = input.horizontal * vel.speed;   // speed * axis
                vel.y = input.vertical * vel.speed;

                // Normalize vector for diagonals
                if (input.horizontal != 0 && input.vertical != 0) {
                    vel.x *= 0.7071f;
                    vel.y *= 0.7071f;
                }
            } else {
                // if no input, WE decide how to stop.
                // instant stop (Arcade style):
                vel.x = 0;
                vel.y = 0;

                // OR Friction stop (Better for knockback):
                // vel.x = MathUtils.lerp(vel.x, 0, 10 * delta);
            }
        }
    }
}
