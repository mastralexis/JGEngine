package io.github.mastralexis.jgengine.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.mastralexis.jgengine.engine.framework.Family;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.game.components.PlayerComponent;
import io.github.mastralexis.jgengine.game.components.VelocityComponent;

public class PlayerInputSystem extends GameSystem {

    private final Family inputFamily;

    public PlayerInputSystem() {
        this.inputFamily = Family.of(PlayerComponent.class, VelocityComponent.class);
    }

    @Override
    public void update(float delta) {
        for (GameObject obj : scene.getGameObjects(inputFamily)) {
            // reset the velocity if the player is not moving
            VelocityComponent vel = obj.getComponent(VelocityComponent.class);
            vel.x = 0;
            vel.y = 0;

            // check WASD and Arrow keys (for now)
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) vel.y = vel.speed;
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) vel.y = -vel.speed;
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) vel.x = -vel.speed;
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) vel.x = vel.speed;

            // Make diagonal movement slower
            if (vel.x != 0 && vel.y != 0) {
                vel.x *= 0.7071f; // 1 over sqrt(2)
                vel.y *= 0.7071f;
            }
        }
    }
}
