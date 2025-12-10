package io.github.mastralexis.jgengine.game.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.game.components.PositionComponent;
import io.github.mastralexis.jgengine.game.components.SpriteComponent;

public class RenderSystem extends GameSystem {
    private SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        this.batch = batch;
        this.priority = 100; // the render system is the last to run
    }

    @Override
    public void update(float deltaTime) {
        for (GameObject go : scene.getGameObjects()) {
            // to use this system we only need Position and SpriteComponents
            if (go.hasComponent(PositionComponent.class) && go.hasComponent(SpriteComponent.class)) {
                PositionComponent pos = go.getComponent(PositionComponent.class);
                SpriteComponent sprite = go.getComponent(SpriteComponent.class);
                batch.draw(sprite.texture, pos.x, pos.y);
            }
        }
    }
}
