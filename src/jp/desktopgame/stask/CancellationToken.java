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
 * 非同期に実行される処理が、キャンセルが要求されているかどうかを確認するために使用されるクラスです.
 *
 * @author desktopgame
 */
public class CancellationToken {

    private CancellationTokenSource source;

    public CancellationToken(CancellationTokenSource source) {
        this.source = source;
    }

    /**
     * キャンセルが要求されていたら例外をスローします.
     */
    public void throwIfCancellationRequested() {
        if (isCancellationRequested()) {
            throw new IllegalStateException();
        }
    }

    /**
     * キャンセルが要求されているかどうかを返します.
     *
     * @return
     */
    public boolean isCancellationRequested() {
        return source.isCancellationRequested();
    }
}
