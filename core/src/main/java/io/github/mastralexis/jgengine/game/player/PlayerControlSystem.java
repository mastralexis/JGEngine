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
            // modify velocity IF there is input.
            if (input.horizontal != 0 || input.vertical != 0) {
                vel.x = input.horizontal * vel.speed;   // speed * axis
                vel.y = input.vertical * vel.speed;

                // normalize vector for diagonals
                if (input.horizontal != 0 && input.vertical != 0) {
                    vel.x *= 0.7071f;
                    vel.y *= 0.7071f;
                }
            } else {
                // if no input stop instantly (no sliding around)
                vel.x = 0;
                vel.y = 0;
            }
        }
    }
}
