package com.orange.dgil.trail.android.impl;

import android.graphics.Canvas;
import android.view.View;
import com.orange.dgil.trail.android.ITrailDrawer;
import com.orange.dgil.trail.android.animation.AnimManager;
import com.orange.dgil.trail.android.animation.AnimParameters;
import com.orange.dgil.trail.android.animation.IAnimDrawer;
import com.orange.dgil.trail.android.animation.IAnimListener;
import com.orange.dgil.trail.android.drawingtool.DrawingTools;
import com.orange.dgil.trail.android.drawingtool.IDrawingTool;
import com.orange.dgil.trail.android.drawingtool.TrailOptions;

public class TrailDrawer implements ITrailDrawer, IAnimDrawer {
    private final AnimManager animManager = new AnimManager(this);
    private IAnimListener animationListener;
    private IDrawingTool drawingTool;
    private DrawingTools drawingTools;
    private boolean inGesture;
    private boolean multistrokeEnabled = true;
    private View view;
    private boolean visible = true;

    public TrailDrawer(View view) {
        init(view);
    }

    private void init(View view) {
        setView(view);
        this.drawingTools = new DrawingTools(view, this.animManager);
        updateDrawingTool();
    }

    private void updateDrawingTool() {
        IDrawingTool selectedTool = this.drawingTools.getDrawingTool();
        if (this.drawingTool != selectedTool) {
            trimMemory();
            this.drawingTool = selectedTool;
        }
    }

    public void trimMemory() {
        if (this.drawingTool != null) {
            this.drawingTool.trimMemory();
        }
    }

    void setView(View view) {
        this.view = view;
        view.setWillNotDraw(false);
    }

    public void setMultistrokeEnabled(boolean enable) {
        this.multistrokeEnabled = enable;
    }

    public void setAnimationListener(IAnimListener animationListener) {
        this.animationListener = animationListener;
    }

    public void touchDown(int x, int y) {
        this.inGesture = true;
        if (!this.multistrokeEnabled) {
            clear();
        }
        updateDrawingTool();
        this.drawingTool.touchDown(x, y);
        this.animManager.reset();
    }

    public void touchMove(int x, int y) {
        if (this.inGesture) {
            this.drawingTool.touchMove(x, y);
            if (this.visible) {
                this.drawingTool.invalidateAreaOnMove();
            }
        }
    }

    public void touchUp() {
        if (this.inGesture) {
            this.inGesture = false;
            this.drawingTool.touchUp();
        }
    }

    public void touchCancel() {
        this.inGesture = false;
        hide();
        this.animManager.reset();
        notifyAnimationFinished();
    }

    public void draw(Canvas canvas) {
        if (this.visible) {
            this.drawingTool.draw(canvas);
        }
    }

    public void invalidatePath() {
        this.drawingTool.invalidatePath();
    }

    public void clear() {
        if (this.visible) {
            hide();
            this.drawingTool.reset();
        } else {
            this.drawingTool.reset();
            hide();
        }
        show();
    }

    public void hide() {
        this.visible = false;
        invalidatePath();
    }

    public void show() {
        this.animManager.reset();
        this.visible = true;
    }

    public void showAndRedrawPath() {
        this.animManager.reset();
        invalidatePath();
        this.visible = true;
    }

    public void animate() {
        if (this.visible) {
            this.animManager.start();
        }
    }

    public void animateAlpha(int color) {
        if (this.visible) {
            getAnimationParameters().setColorForAlphaAnimation(color);
            this.drawingTool.forceRedrawForAnimation(getTrailOptions().getColor() != color);
            animate();
        }
    }

    public void animateToColor(int color) {
        if (this.visible) {
            getAnimationParameters().setColorProperties(getTrailOptions().getColor(), color);
            animate();
        }
    }

    public void show(int color) {
        getTrailOptions().setColor(color);
        show();
    }

    public void showAndRedrawPath(int color) {
        getTrailOptions().setColor(color);
        showAndRedrawPath();
    }

    public AnimParameters getAnimationParameters() {
        return this.animManager.getAnimationParameters();
    }

    public void animationFinished() {
        if (!this.multistrokeEnabled) {
            hide();
        }
        this.animManager.reset();
        notifyAnimationFinished();
    }

    private void notifyAnimationFinished() {
        if (this.animationListener != null) {
            this.animationListener.animationFinished();
        }
    }

    public TrailOptions getTrailOptions() {
        return this.drawingTools.getTrailOptions();
    }

    public View getView() {
        return this.view;
    }
}
