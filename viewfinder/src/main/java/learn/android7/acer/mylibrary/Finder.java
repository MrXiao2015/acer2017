package learn.android7.acer.mylibrary;

import learn.android7.acer.mylibrary.provider.Provider;

/**
 * Created by xiaozheng on 2017/2/21.
 */

public interface Finder<T> {
    /**
     *
     * @param host 宿主对象
     * @param source 实际调用findViewById的对象
     * @param provider
     */
    void inject(T host, Object source, Provider provider);
}
