package android7.acer.learn.tool;

import android.content.Context;
import android.util.DisplayMetrics;

import android7.acer.learn.LearnApplication;

public class ScreenUtil {
	public static int SCREEN_HEIGHT;
	public static int SCREEN_WIDTH;
	private static float SCREEN_DENSITY;
	private static float SCREEN_SCALED_DENSITY;

	static {
		DisplayMetrics metrics = LearnApplication.getInstance().getResources().getDisplayMetrics();
		SCREEN_DENSITY = metrics.density;
		SCREEN_SCALED_DENSITY = metrics.scaledDensity;
		SCREEN_HEIGHT = metrics.heightPixels;
		SCREEN_WIDTH = metrics.widthPixels;
	}

	public static float dp2px(float dp) {
		return SCREEN_DENSITY * dp + 0.5f;
	}

	@Deprecated
	public static int dip2px(Context context, float dp) {
		return (int) dp2px(dp);
	}

	public static int dp2px(int dp) {
		return (int) dp2px((float) dp);
	}

	public static int px2dp(int pixel) {
		return Math.round(pixel / SCREEN_DENSITY);
	}

	public static int sp2px(float spValue) {
		return (int) (spValue * SCREEN_SCALED_DENSITY + 0.5f);
	}

}