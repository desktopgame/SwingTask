/*
 * SwingTask
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.stask;

/**
 * 非同期スレッドからEDTへデータを送信するためのインターフェイスです.
 *
 * @author desktopgame
 */
public interface Publishable<V> {

    /**
     * 指定のデータチャンクを送信し、EDTのコールバックを起動します.
     *
     * @param chunks
     */
    public void publish(V... chunks);
}
