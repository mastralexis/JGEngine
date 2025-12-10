package io.github.mastralexis.jgengine.engine.core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class EngineAssetManager implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void queueStandardAssets() {
        // Queue loading tasks (doesn't load yet)
        manager.load("libgdx.png", Texture.class);
        manager.load("skins/uiskin.json", Skin.class);
    }

    public void finishLoading() {
        manager.finishLoading(); // Blocks until done
    }

    public Texture getTexture(String name) {
        return manager.get(name, Texture.class);
    }

    public Skin getSkin(String name) {
        return manager.get(name, Skin.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
