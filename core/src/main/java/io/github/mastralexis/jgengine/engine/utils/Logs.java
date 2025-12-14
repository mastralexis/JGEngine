package io.github.mastralexis.jgengine.engine.utils;

import com.badlogic.gdx.Gdx;

/**
 * Basic Log class that uses libgdx log
 */
public class Logs {
    public static boolean DEBUG = true;

    public static void log(String tag, String message) {
        if (DEBUG) {
            Gdx.app.log(tag, message);
        }
    }

    public static void error(String tag, String message) {
        Gdx.app.error(tag, message);
    }
}
