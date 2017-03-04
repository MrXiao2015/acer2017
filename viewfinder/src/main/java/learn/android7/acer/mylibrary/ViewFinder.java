package learn.android7.acer.mylibrary;

import android.app.Activity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import learn.android7.acer.mylibrary.provider.ActivityProvider;
import learn.android7.acer.mylibrary.provider.Provider;
import learn.android7.acer.mylibrary.provider.ViewProvider;

/**
 * Created by xiaozheng on 2017/2/21.
 */

public class ViewFinder {
    private static final Map<String, Finder> FINDER_MAP = new HashMap<>();

    private static final ActivityProvider PROVIDER_ACTIVITY = new ActivityProvider();
    private static final ViewProvider PROVIDER_VIEW = new ViewProvider();

    public static void inject(Activity activity) {
        inject(activity, activity, PROVIDER_ACTIVITY);
    }

    public static void inject(View view) {
        inject(view, view);
    }

    public static void inject(Object host, View view) {
        inject(host, view, PROVIDER_VIEW);
    }

    /**
     * @param host     注解的View变量所在的类
     * @param source   表示查找View的地方。Activity & View自身就可以查找，Fragment需要在自己的itemView中查找
     * @param provider 是一个接口，定义了不同的对象如何去查找目标View
     */
    public static void inject(Object host, Object source, Provider provider) {
        String className = host.getClass().getName();
        try {
            Finder finder = FINDER_MAP.get(className);
            if (finder == null) {
                Class<?> finderClass = Class.forName(className + "$$Finder");
                finder = (Finder) finderClass.newInstance();
                FINDER_MAP.put(className, finder);
            }
            finder.inject(host, source, provider);
        } catch (Exception e) {
            throw new RuntimeException("Unabe to inject for" + className, e);
        }
    }

}
