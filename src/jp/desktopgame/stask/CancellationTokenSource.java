/*
 * SwingTask
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.stask;

import javax.swing.SwingWorker;

/**
 * 外部のスレッド(EDTを含む)から非同期処理のキャンセルをリクエストするために使用するクラスです.
 *
 * @author desktopgame
 * @param <T>
 * @param <V>
 */
public class CancellationTokenSource<T, V> {

    private SwingWorker<T, V> proxy;
    final CancellationToken token;

    public CancellationTokenSource(SwingWorker<T, V> proxy) {
        this.proxy = proxy;
        this.token = new CancellationToken(this);
    }

    /**
     * キャンセルを要求します.
     *
     * @param mayInterruptIfRunning
     */
    public void cancel(boolean mayInterruptIfRunning) {
        proxy.cancel(mayInterruptIfRunning);
    }

    /**
     * キャンセルが要求されているかどうかを返します.
     *
     * @return
     */
    public boolean isCancellationRequested() {
        return proxy.isCancelled();
    }
}
