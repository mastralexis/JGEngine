package io.github.mastralexis.jgengine.engine.physics;

import com.badlogic.gdx.math.Rectangle;
import io.github.mastralexis.jgengine.engine.framework.Family;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.GameSystem;

/**
 * Applies the velocity to the position components of all GameObjects
 */
public class MovementSystem extends GameSystem {

    private final Family movementFamily;
    private final Family collisionFamily;

    public MovementSystem() {
        this.priority = 1;
        this.movementFamily = Family.of(TransformComponent.class, VelocityComponent.class);
        this.collisionFamily = Family.of(TransformComponent.class, BoxColliderComponent.class);
    }

    @Override
    public void update(float delta) {

        Iterable<GameObject> obstacles = scene.getGameObjects(collisionFamily);

        for (GameObject go : scene.getGameObjects(movementFamily)) {
            TransformComponent pos = go.getComponent(TransformComponent.class);
            VelocityComponent vel = go.getComponent(VelocityComponent.class);
            BoxColliderComponent col = go.getComponent(BoxColliderComponent.class);
            // if the entity doesn't have a collider just move it without checking for collisions
            if (col == null) {
                pos.x += vel.x * delta;
                pos.y += vel.y * delta;
                continue;
            }

            // --- X AXIS MOVEMENT ---
            float oldX = pos.x;
            pos.x += vel.x * delta;
            col.hitBox.setPosition(pos.x, pos.y); // Sync the Rectangle with the new position

            if (checkCollision(go, col.hitBox, obstacles)) {
                // Collision detected! Revert X movement.
                pos.x = oldX;
                col.hitBox.x = oldX; // Sync back
            }


            // --- Handle Y Axis ---
            float originalY = pos.y;

            // Propose new Y position
            pos.y += vel.y * delta;

            // Sync hitbox to new Y (using the accepted X)
            col.hitBox.y = pos.y;

            if (checkCollision(go, col.hitBox, obstacles)) {
                // Collision detected! Revert Y movement.
                pos.y = originalY;
                col.hitBox.y = originalY; // Sync back
            }

        }
    }

    private boolean checkCollision(GameObject self, Rectangle selfRect, Iterable<GameObject> obstacles) {
        for (GameObject obstacle : obstacles) {
            // Don't check collision with self
            if (obstacle == self) continue;

            BoxColliderComponent obstacleCollider = obstacle.getComponent(BoxColliderComponent.class);

            // Ensure the obstacle's hitbox is synced with its position
            // (Ideally, static walls don't move, but we must ensure the rect is in the right place)
            TransformComponent obstaclePos = obstacle.getComponent(TransformComponent.class);
            obstacleCollider.hitBox.x = obstaclePos.x;
            obstacleCollider.hitBox.y = obstaclePos.y;

            if (selfRect.overlaps(obstacleCollider.hitBox)) {
                return true;
            }
        }
        return false;
    }
}
