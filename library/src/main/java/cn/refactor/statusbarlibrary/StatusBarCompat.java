package cn.refactor.statusbarlibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者 : andy
 * 日期 : 15/11/3 09:58
 * 邮箱 : andyxialm@gmail.com
 * 描述 : 兼容Kitkat以及Lollipop的Translucent Bars
 */
public class StatusBarCompat {

    public static void init(Activity activity) {
        int defalutColor = activity.getResources().getColor(R.color.colorPrimaryDark);
        init(activity, defalutColor);
    }

    public static void init(Activity activity, int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(statusBarColor);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int defalutColor = activity.getResources().getColor(R.color.colorPrimaryDark);
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (statusBarColor > 0) {
                defalutColor = statusBarColor;
            }
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(defalutColor);
            contentView.addView(statusBarView, params);
        }
    }

    /**
     * 获得状态栏高度
     * @param context
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
