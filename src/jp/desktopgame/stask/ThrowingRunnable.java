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
 * {@link java.lang.Runnable}を例外をスローできるように拡張したインターフェイスです.
 *
 * @author desktopgame
 */
public interface ThrowingRunnable {

    /**
     * 処理を実行するか例外をスローして中断します.
     *
     * @throws Exception
     */
    public void run() throws Exception;
}
