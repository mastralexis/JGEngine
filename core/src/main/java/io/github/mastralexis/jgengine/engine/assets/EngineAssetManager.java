package io.github.mastralexis.jgengine.engine.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

/**
 * TODO: UNIMPLEMENTED METHODS
 * Wrapper for {@link AssetManager}
 * EngineAssetManager manages all the assets of the game
 * it unloads and loads assets based on the level
 */
/**
 * Centralized manager for handling all game assets (Textures, Skins, Audio, etc.).
 * <p>
 * This class wraps LibGDX's {@link com.badlogic.gdx.assets.AssetManager} to provide:
 * <ul>
 * <li>A single source of truth for file paths (preventing hardcoded strings in Screens).</li>
 * <li>Simplified, type-safe retrieval methods (e.g., {@link #getTexture(String)}).</li>
 * <li>Lifecycle management (loading queues and disposal).</li>
 * </ul>
 * <p>
 * <strong>Usage:</strong><br>
 * This class is instantiated once in {@link io.github.mastralexis.jgengine.Main} and
 * shared across all screens via {@code game.assets}.
 * * @see com.badlogic.gdx.assets.AssetManager
 */
public class EngineAssetManager implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void queueStandardAssets() {
        // Queue loading tasks (doesn't load yet)
        manager.load("libgdx.png", Texture.class);
        manager.load("skins/uiskin.json", Skin.class);
        manager.load("sprites/player/wayne-t-pose.png", Texture.class);
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
