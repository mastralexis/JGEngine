package io.github.mastralexis.jgengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mastralexis.jgengine.engine.core.EngineAssetManager;
import io.github.mastralexis.jgengine.game.screens.MenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;  // public to be available
    public EngineAssetManager assets;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // 1. Initialize and Load Assets
        assets = new EngineAssetManager();
        assets.queueStandardAssets(); // Queues assets
        assets.finishLoading();       // Blocks until everything is ready

        // 2. Start the Menu (now safe to access assets)
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if (screen != null) screen.dispose();
        batch.dispose();
        assets.dispose();
    }
}
