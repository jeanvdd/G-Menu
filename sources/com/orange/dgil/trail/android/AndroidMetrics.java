package com.orange.dgil.trail.android;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;

public class AndroidMetrics {
    private static final int MICROMETERS_PER_INCH = 25400;
    private final float density;
    private final int heightPixels;
    private final int widthPixels;
    private final float ydpi;

    public AndroidMetrics(float ydpi, float density, int widthPixels, int heightPixels) {
        this.ydpi = ydpi;
        this.density = density;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
    }

    public float getYdpi() {
        return this.ydpi;
    }

    public float getDensity() {
        return this.density;
    }

    public int getWidthPixels() {
        return this.widthPixels;
    }

    public int getHeightPixels() {
        return this.heightPixels;
    }

    public int getMicrometersPerPixel() {
        return (int) (25400.0f / this.ydpi);
    }

    public int pixelsToMillimeters(int pixels) {
        return pixelsToMicrometers(pixels) / DefaultDrawerConf.QUILL_HEIGHT_UM;
    }

    public int pixelsToMicrometers(int pixels) {
        return getMicrometersPerPixel() * pixels;
    }

    public int micrometersToPixels(int micrometers) {
        return micrometers / getMicrometersPerPixel();
    }

    public static AndroidMetrics get(Context ctx) {
        DisplayMetrics metrics = new DisplayMetrics();
        updateMetrics(((WindowManager) ctx.getSystemService("window")).getDefaultDisplay(), metrics);
        return new AndroidMetrics(metrics.ydpi, metrics.density, metrics.widthPixels, metrics.heightPixels);
    }

    private static void updateMetrics(Display display, DisplayMetrics metrics) {
        try {
            doUpdateMetrics(display, metrics);
        } catch (NoSuchMethodException e) {
            showErrorAndSetDefaults(e, display, metrics);
        } catch (IllegalAccessException e2) {
            showErrorAndSetDefaults(e2, display, metrics);
        } catch (InvocationTargetException e3) {
            showErrorAndSetDefaults(e3, display, metrics);
        }
    }

    private static void doUpdateMetrics(Display display, DisplayMetrics metrics) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (VERSION.SDK_INT >= 14 && VERSION.SDK_INT < 17) {
            updateMetricsBeforeJBMR1(display, metrics);
        } else if (VERSION.SDK_INT >= 17) {
            updateMetricsStartingWithJBMR1(display, metrics);
        } else {
            throw new NoSuchMethodException("The library does not support devices before android ICE CREAM SANDWICH (api 14)");
        }
    }

    private static void updateMetricsBeforeJBMR1(Display display, DisplayMetrics metrics) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        display.getMetrics(metrics);
        metrics.widthPixels = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
        metrics.heightPixels = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(display, new Object[0])).intValue();
    }

    private static void updateMetricsStartingWithJBMR1(Display display, DisplayMetrics metrics) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Display.class.getMethod("getRealMetrics", new Class[]{DisplayMetrics.class}).invoke(display, new Object[]{metrics});
    }

    private static void showErrorAndSetDefaults(Exception e, Display display, DisplayMetrics metrics) {
        Log.e(AndroidMetrics.class.getName(), String.format("Failed to get metrics, %s, %s", new Object[]{e.getMessage(), e.getClass().getName()}));
        display.getMetrics(metrics);
    }
}
