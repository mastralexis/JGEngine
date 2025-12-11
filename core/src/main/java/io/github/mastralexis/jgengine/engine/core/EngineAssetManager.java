package io.github.mastralexis.jgengine.engine.core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class EngineAssetManager implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void queueStandardAssets() {
        // Queue loading tasks (doesn't load yet)
        manager.load("libgdx.png", Texture.class);
        manager.load("skins/uiskin.json", Skin.class);
        manager.load("sprites/player/wayne-t-pose-scaled.png", Texture.class);
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

    // 2. LEVEL 1 Specifics
    public void queueLevel1Assets() {
//        manager.load("maps/level1_forest.png", Texture.class);
//        manager.load("music/forest_theme.mp3", Music.class);
    }

    public void unloadLevelAssets() {
        // Unload specific files to free RAM
        // Note: You must be precise here!

    }
}
