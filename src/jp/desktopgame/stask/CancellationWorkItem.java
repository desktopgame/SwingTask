/*
 * SwingTask
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.stask;

import java.util.Objects;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 *
 * @author desktopgame
 * @param <T>
 * @param <V>
 */
public class CancellationWorkItem<T, V> {

    private WorkItem<T, V> parent;
    private boolean invalid;

    protected CancellationWorkItem(WorkItem<T, V> parent) {
        this.parent = Objects.requireNonNull(parent);
    }

    /**
     * 非同期処理が終了した後に呼ばれるコールバックを設定してタスクを開始します.
     *
     * @param done
     * @return
     */
    public CancellationTokenSource<T, V> done(Consumer<T> done) {
        if (invalid) {
            throw new IllegalStateException();
        }
        this.invalid = true;
        parent.done = Objects.requireNonNull(done);
        parent.execute();
        return parent.tokenSource;
    }

    /**
     * タスクを開始します.
     *
     * @return
     */
    public Future<T> forget() {
        if (invalid) {
            throw new IllegalStateException();
        }
        this.invalid = true;
        return parent._forget();
    }
}
