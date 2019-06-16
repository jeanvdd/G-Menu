package com.orange.dgil.trail.android.drawingtool.quillpen;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

class QuillBitmap {
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private final Paint paint = new Paint();
    private final View view;

    public QuillBitmap(View view) {
        this.view = view;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Canvas getBitmapCanvas() {
        return this.bitmapCanvas;
    }

    void lazyLoading() {
        if (shouldAllocateNewBitmap()) {
            releaseBitmap();
            this.bitmap = getNewBitmap();
            this.bitmapCanvas = new Canvas(this.bitmap);
        }
    }

    private boolean shouldAllocateNewBitmap() {
        return (this.bitmap != null && this.bitmap.getWidth() == this.view.getWidth() && this.bitmap.getHeight() == this.view.getHeight()) ? false : true;
    }

    void releaseBitmap() {
        if (this.bitmap != null) {
            this.bitmapCanvas.setBitmap(null);
            this.bitmapCanvas = null;
            this.bitmap.recycle();
            this.bitmap = null;
        }
    }

    void drawBitmap(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paint);
    }

    boolean isLoaded() {
        return this.bitmap != null;
    }

    void reset() {
        resetAlpha();
        eraseBitmap();
    }

    void resetAlpha() {
        this.paint.setAlpha(255);
    }

    private void eraseBitmap() {
        if (isLoaded()) {
            this.bitmap.eraseColor(0);
        }
    }

    void setAlpha(int alpha) {
        this.paint.setAlpha(alpha);
    }

    private Bitmap getNewBitmap() {
        return Bitmap.createBitmap(this.view.getWidth(), this.view.getHeight(), Config.ARGB_8888);
    }
}
