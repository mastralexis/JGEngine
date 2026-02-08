package io.github.mastralexis.jgengine.game.factories;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * If you add Box2D later (which you almost certainly will for a platformer),
 * creating physics bodies is very verbose. You don't want BodyDef logic cluttering your level code.
 */
public class PhysicsFactory {
    public Body createBox(float x, float y, float width, float height, boolean isStatic) {
        return null;
    }
}
