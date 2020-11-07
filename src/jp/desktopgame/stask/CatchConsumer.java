/*
 * SwingTask
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.stask;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * 例外か任意の値を受け取るインターフェイスです.
 *
 * @author desktopgame
 * @param <T>
 */
public interface CatchConsumer<T> {

    /**
     * 値の計算が終了するか例外がスローされると呼ばれます.
     *
     * @param paramOpt
     * @param exOpt
     */
    public void consume(Optional<T> paramOpt, Optional<ExecutionException> exOpt);
}
