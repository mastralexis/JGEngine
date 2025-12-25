package io.github.mastralexis.jgengine.game.components;

import com.badlogic.gdx.Game;
import io.github.mastralexis.jgengine.engine.framework.GameComponent;

// intent
public class InputComponent implements GameComponent {
    public float horizontal = 0;    // -1 (left) / 1 (right)
    public float vertical = 0;      // -1 (up)  / 1 (down)
}
