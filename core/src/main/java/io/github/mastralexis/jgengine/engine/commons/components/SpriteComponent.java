package io.github.mastralexis.jgengine.engine.commons.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class SpriteComponent implements GameComponent {
    public TextureRegion textureRegion;
    public float width;
    public float height;

    public SpriteComponent(Texture texture) {
        this.textureRegion = new TextureRegion(texture);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public SpriteComponent(Texture texture, float width, float height) {
        this.textureRegion = new TextureRegion(texture);
        this.width = width;
        this.height = height;
    }
}
