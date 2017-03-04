package learn.android7.acer.mylibrary.provider;

import android.content.Context;
import android.view.View;

/**
 * Created by xiaozheng on 2017/2/21.
 */

public interface Provider {
    Context getContext(Object source);

    View findView(Object source, int id);
}
