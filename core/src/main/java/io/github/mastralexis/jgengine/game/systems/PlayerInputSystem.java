package io.github.mastralexis.jgengine.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.mastralexis.jgengine.engine.framework.Family;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.game.components.InputComponent;
import io.github.mastralexis.jgengine.game.components.PlayerComponent;
import io.github.mastralexis.jgengine.game.components.VelocityComponent;

// Translates hardware to intent.
public class PlayerInputSystem extends GameSystem {

    private final Family inputFamily;

    public PlayerInputSystem() {
        this.inputFamily = Family.of(PlayerComponent.class, InputComponent.class);
    }

    @Override
    public void update(float delta) {
        for (GameObject obj : scene.getGameObjects(inputFamily)) {
            InputComponent input = obj.getComponent(InputComponent.class);

            // reset the intent
            input.horizontal = 0;
            input.vertical = 0;

            // check WASD and Arrow keys (for now)
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))      input.vertical = 1;
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))    input.vertical = -1;
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))    input.horizontal = -1;
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))   input.horizontal = 1;
        }
    }
}
