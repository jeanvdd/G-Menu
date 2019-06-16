package com.orange.dgil.trail.android.drawingtool.quillpen;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.orange.dgil.trail.android.drawingtool.TrailOptions;

class BitmapDrawer {
    private int hRadius;
    private final Paint paint = new Paint();
    private final RectF rectF = new RectF();
    private final TrailOptions trailOptions;
    private int wRadius;

    public BitmapDrawer(TrailOptions trailOptions) {
        this.trailOptions = trailOptions;
    }

    void updateDrawParameters() {
        this.paint.setColor(this.trailOptions.getColor());
        updateRadius();
    }

    private void updateRadius() {
        QuillParameters quillParameters = this.trailOptions.getQuillParameters();
        this.wRadius = quillParameters.getQuillWidthPixels() / 2;
        this.hRadius = quillParameters.getQuillHeightPixels() / 2;
    }

    void forceColor(int color) {
        this.paint.setColor(color);
    }

    void drawPoint(Canvas canvas, int x, int y) {
        this.rectF.set((float) (x - this.wRadius), (float) (y - this.hRadius), (float) (this.wRadius + x), (float) (this.hRadius + y));
        canvas.drawOval(this.rectF, this.paint);
    }

    void drawCirclePoint(Canvas canvas, int x, int y) {
        this.rectF.set((float) (x - this.wRadius), (float) (y - this.wRadius), (float) (this.wRadius + x), (float) (this.wRadius + y));
        canvas.drawOval(this.rectF, this.paint);
    }
}
