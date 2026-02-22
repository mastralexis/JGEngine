package io.github.mastralexis.jgengine.engine.assets;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

import java.awt.*;

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

    // Load Assets
    public void loadTexture(String filePath) { manager.load(filePath, Texture.class); }
    public void loadSkin(String filePath) { manager.load(filePath, Skin.class); }
    public void loadFont(String filePath) { manager.load(filePath, Font.class); }
    public void loadSound(String filePath) { manager.load(filePath, Sound.class); }
    public void loadMusic(String filePath) { manager.load(filePath, Music.class); }

    // Loading execution
    public void finishLoading() { manager.finishLoading(); } // blocks until done
    public boolean update() { return manager.update(); }// returns true if loading is done

    // Retrieve loaded assets
    public Texture getTexture(String name) { return manager.get(name, Texture.class); }
    public Skin getSkin(String name) { return manager.get(name, Skin.class); }
    public Sound getSound(String name) { return manager.get(name, Sound.class); }
    public Music getMusic(String name) { return manager.get(name, Music.class); }

    // Cleanup
    public void unload(String filePath) {
        if (manager.isLoaded(filePath)) manager.unload(filePath);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
