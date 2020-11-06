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
import java.util.concurrent.Callable;

/**
 * {@link javax.swing.SwingWorker}を関数型インターフェイスメソッドチェインで呼び出せるようにしたユーティリティです.
 *
 * @author desktopgame
 */
public class SwingTask {

    private SwingTask() {
    }

    /**
     * 非同期で実行されるタスクを生成します.
     *
     * @param <T>
     * @param <V>
     * @param reporter
     * @return
     */
    public static <T, V> WorkItem<T, V> create(Reporter<T, V> reporter) {
        return new WorkItem<>(reporter);
    }

    /**
     * 非同期で実行されるタスクを生成します.
     *
     * @param <T>
     * @param callable
     * @return
     */
    public static <T> CancellationWorkItem<T, Void> create(Callable<T> callable) {
        return SwingTask.create((Publishable<Void> v, CancellationToken tok) -> callable.call()).process(SwingTask::emptyProc);
    }

    private static void emptyProc(List<Void> chunks) {
    }
}
