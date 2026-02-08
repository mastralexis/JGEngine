package io.github.mastralexis.jgengine.engine.framework;

import com.badlogic.gdx.utils.ObjectIntMap;

/**
 * Global registry that maps {@link GameComponent} classes to unique integer indices.
 * <p>
 * <b>Purpose:</b><br>
 * This allows the engine to identify components using simple integers instead of slow Class lookups.
 * These indices are used to:
 * <ul>
 * <li>Store components in a fast array inside {@link GameObject}.</li>
 * <li>Create {@link com.badlogic.gdx.utils.Bits} signatures for {@link Family} matching.</li>
 * </ul>
 */
public class ComponentType {
    private static final ObjectIntMap<Class<? extends GameComponent>> classToIndex = new ObjectIntMap<>();
    private static int nextIndex = 0;

    /**
     * Returns the unique integer index for the given component class.
     * <p>
     * If the class is encountered for the first time, it is assigned the next available index.
     * This method is generally O(1) after the first lookup.
     *
     * @param type The component class (e.g., TransformComponent.class)
     * @return The unique integer ID for this component type.
     */
    public static int getIndexFor(Class<? extends GameComponent> type) {
        int index = classToIndex.get(type, -1);
        if (index == -1) {
            index = nextIndex++;
            classToIndex.put(type, index);
        }
        return index;
    }

    /**
     * @return The total number of unique component types registered so far.
     */
    public static int getCount() {
        return nextIndex;
    }
}
