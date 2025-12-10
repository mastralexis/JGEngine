package io.github.mastralexis.jgengine.game.components;

import com.badlogic.gdx.graphics.Texture;
import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class SpriteComponent implements GameComponent {
    public Texture texture;

    public SpriteComponent(Texture texture) {
        this.texture = texture;
    }
}
