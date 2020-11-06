/*
 * SwingTask
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.stask;

import java.util.List;

/**
 * 非同期スレッドから送信されたデータをGUIに適用するインターフェイスです.
 *
 * @author desktopgame
 */
public interface Updatable<V> {

    /**
     * GUIにデータを適用します.
     *
     * @param chunks
     */
    public void update(List<V> chunks);
}
