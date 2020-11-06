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
 * 非同期で実行される処理を定義します.
 *
 * @author desktopgame
 */
public interface Reporter<T, V> {

    /**
     * 計算結果Tを計算しながら、必要に応じて途中のデータを送信します. キャンセル要求は引数のトークンから確認できます。
     *
     * @param pub
     * @param token
     * @return
     * @throws Exception
     */
    public T report(Publishable<V> pub, CancellationToken token) throws Exception;
}
