package learn.android7.acer.mylibrary.provider;

import android.content.Context;
import android.view.View;

/**
 * Created by xiaozheng on 2017/2/21.
 */

public class ViewProvider implements Provider {

    @Override
    public Context getContext(Object source) {
        return ((View) source).getContext();
    }

    @Override
    public View findView(Object source, int id) {
        return ((View) source).findViewById(id);
    }
}
