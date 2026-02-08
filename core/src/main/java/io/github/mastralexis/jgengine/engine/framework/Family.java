package io.github.mastralexis.jgengine.engine.framework;

import com.badlogic.gdx.utils.Bits;

import java.util.Set;

/**
 * Represents a group of Components that a System requires.
 * <p>
 * <b>What it does:</b>
 * Systems ask for Entities belonging to Family A for example.
 * <p>
 * <b>How it's used:</b>
 * 1. Systems define a Family to specify what data they need (e.g., Position + Sprite).
 * 2. The Scene uses this Family object as a key in a Map (Cache) to store a list of pre-filtered entities.
 * 3. This avoids looping through every single object in the game every frame.
 *
 * <p>
 *     Currently I cannot add a component to an GameObject after the object has been updated
 *     Because I don't update the families after that but that's ok because I'm not going to do it.
 * <p/>
 */
public class Family {
    private final Bits requiredBits;

    private Family(Bits bits) {
        this.requiredBits = bits;
    }
    /**
     * A static factory method to easily create a Family without manually creating a Set.
     * <p>
     * <b>Usage:</b> Family.of(PositionComponent.class, SpriteComponent.class)
     *
     * @param components A variable list of Component classes that define this family.
     * @return A new Family record containing an immutable Set of these components.
     */
    @SafeVarargs
    public static Family of(Class<? extends GameComponent>... components) {
        Bits bits = new Bits();
        for (Class<? extends GameComponent> c : components) {
            bits.set(ComponentType.getIndexFor(c));
        }
        return new Family(bits);
    }

    /**
     * Checks if a specific GameObject belongs to this Family.
     * <p>
     * <b>Logic:</b>
     * It iterates through the required components of this Family and checks if the
     * GameObject has ALL of them. If even one is missing, it returns false.
     *
     * @param gameObject The object to check.
     * @return true if the object has all the required components; false otherwise.
     */
    public boolean matches(GameObject gameObject) {
        // Returns true if the object's bits contain all of the family's required bits
        return gameObject.getComponentBits().containsAll(requiredBits);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return requiredBits.equals(family.requiredBits);
    }

    @Override
    public int hashCode() {
        return requiredBits.hashCode();
    }
}
