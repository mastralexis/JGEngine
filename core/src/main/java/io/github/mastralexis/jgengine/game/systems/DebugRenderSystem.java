package io.github.mastralexis.jgengine.game.systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.mastralexis.jgengine.engine.framework.Family;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;
import io.github.mastralexis.jgengine.game.components.BoxColliderComponent;
import io.github.mastralexis.jgengine.game.components.TransformComponent;

public class DebugRenderSystem extends GameSystem {
    private final ShapeRenderer shapeRenderer;
    private final Family colliderFamily;

    public DebugRenderSystem() {
        this.priority = 200; // Run AFTER the normal RenderSystem so lines are drawn ON TOP
        this.shapeRenderer = new ShapeRenderer();

        // We only care about things that have a Position and a Collider
        this.colliderFamily = Family.of(TransformComponent.class, BoxColliderComponent.class);
    }

    @Override
    public void update(float delta) {
        // 1. Prepare the ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Draw outlines (use Filled for solid)
        shapeRenderer.setColor(Color.GREEN); // Make the lines green

        // 2. Iterate through all objects with colliders
        for (GameObject go : scene.getGameObjects(colliderFamily)) {
            TransformComponent pos = go.getComponent(TransformComponent.class);
            BoxColliderComponent col = go.getComponent(BoxColliderComponent.class);

            // Draw the rectangle based on the collider's HitBox data
            // Note: If you implemented the offsets discussed earlier, use them here!
            shapeRenderer.rect(col.hitBox.x, col.hitBox.y, col.hitBox.width, col.hitBox.height);
        }

        shapeRenderer.end();
    }
}
