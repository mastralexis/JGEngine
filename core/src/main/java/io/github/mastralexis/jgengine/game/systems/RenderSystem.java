package io.github.mastralexis.jgengine.game.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mastralexis.jgengine.engine.framework.Family;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.game.components.TransformComponent;
import io.github.mastralexis.jgengine.game.components.SpriteComponent;

public class RenderSystem extends GameSystem {
    private final SpriteBatch batch;
    private final Family renderFamily;    // the family of components this the render system uses

    public RenderSystem(SpriteBatch batch) {
        this.batch = batch;
        this.priority = 100; // the render system is the last to run
        this.renderFamily = Family.of(TransformComponent.class, SpriteComponent.class); // objects with Position && Sprite
    }

    @Override
    public void update(float deltaTime) {
        for (GameObject go : scene.getGameObjects(renderFamily)) {
            // to use this system we only need Position and SpriteComponents
            TransformComponent transform = go.getComponent(TransformComponent.class);
            SpriteComponent sprite = go.getComponent(SpriteComponent.class);
            // Get the origin (center of the sprite) for rotation
            float originX = sprite.width / 2f;
            float originY = sprite.height / 2f;

            batch.draw(
                sprite.textureRegion,
                transform.x, transform.y,      // Position
                originX, originY,              // Origin (center of rotation)
                sprite.width, sprite.height,   // Base Dimensions
                transform.scaleX, transform.scaleY, // Scale
                transform.rotation             // Rotation
            );
        }
    }
}
