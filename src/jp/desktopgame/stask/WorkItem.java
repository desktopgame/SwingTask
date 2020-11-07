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
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import javax.swing.SwingWorker;

/**
 * 非同期で実行される処理をまとめるクラスです
 *
 * @author desktopgame
 * @param <T>
 * @param <V>
 */
public class WorkItem<T, V> {

    private Executor executor;
    private Reporter<T, V> doInBackground;
    private Updatable<V> updatable;
    Consumer<T> done;
    CancellationTokenSource<T, V> tokenSource;
    private boolean invalid;

    public WorkItem(Reporter<T, V> doInBackground) {
        this.executor = new Executor();
        this.tokenSource = new CancellationTokenSource(executor);
        this.doInBackground = doInBackground;
    }

    /* */ T doInBackground() throws Exception {
        return doInBackground.report(executor.getPublishable(), tokenSource.token);
    }

    /**
     * 非同期スレッドから送信されたデータをEDTで受信するコールバックを設定します.
     *
     * @param updatable
     * @return
     */
    public CancellationWorkItem<T, V> process(Updatable<V> updatable) {
        if (invalid) {
            throw new IllegalStateException();
        }
        this.invalid = true;
        this.updatable = Objects.requireNonNull(updatable);
        return new CancellationWorkItem<>(this);
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
        this.done = Objects.requireNonNull(done);
        execute();
        return tokenSource;
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
        return _forget();
    }

    Future<T> _forget() {
        execute();
        return executor;
    }

    void execute() {
        this.invalid = true;
        executor.execute();
    }

    protected class Executor extends SwingWorker<T, V> {

        @Override
        protected T doInBackground() throws Exception {
            return WorkItem.this.doInBackground();
        }

        @Override
        protected void process(List<V> arg0) {
            super.process(arg0); //To change body of generated methods, choose Tools | Templates.
            if (updatable != null) {
                updatable.update(arg0);
            }
        }

        @Override
        protected void done() {
            super.done(); //To change body of generated methods, choose Tools | Templates.
            if (done != null) {
                try {
                    done.accept(get());
                } catch (InterruptedException | ExecutionException ex) {
                    throw new IllegalStateException(ex);
                }
            }
        }

        public Publishable<V> getPublishable() {
            return this::publish;
        }
    }
}
