package io.github.mastralexis.jgengine.game.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mastralexis.jgengine.engine.framework.Family;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.game.components.PositionComponent;
import io.github.mastralexis.jgengine.game.components.SpriteComponent;

public class RenderSystem extends GameSystem {
    private final SpriteBatch batch;
    private final Family renderFamily;    // the family of components this the render system uses

    public RenderSystem(SpriteBatch batch) {
        this.batch = batch;
        this.priority = 100; // the render system is the last to run
        this.renderFamily = Family.of(PositionComponent.class, SpriteComponent.class); // objects with Position && Sprite
    }

    @Override
    public void update(float deltaTime) {
        for (GameObject go : scene.getGameObjects(renderFamily)) {
            // to use this system we only need Position and SpriteComponents
            PositionComponent pos = go.getComponent(PositionComponent.class);
            SpriteComponent sprite = go.getComponent(SpriteComponent.class);
            batch.draw(sprite.textureRegion, pos.x, pos.y, sprite.width, sprite.height);
        }
    }
}
