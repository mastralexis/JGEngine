package io.github.mastralexis.jgengine.engine.commons.components;

import com.badlogic.gdx.math.Rectangle;
import io.github.mastralexis.jgengine.engine.framework.GameComponent;

public class BoxColliderComponent implements GameComponent {
    public Rectangle hitBox;

    public BoxColliderComponent(float width, float height) {
        this.hitBox = new Rectangle(0,0,width,height);
    }
}
