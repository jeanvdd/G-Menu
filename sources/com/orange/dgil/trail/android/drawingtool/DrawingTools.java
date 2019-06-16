package com.orange.dgil.trail.android.drawingtool;

import android.view.View;
import com.orange.dgil.trail.android.AndroidMetrics;
import com.orange.dgil.trail.android.animation.AnimManager;
import com.orange.dgil.trail.android.drawingtool.markerpen.MarkerPaths;
import com.orange.dgil.trail.android.drawingtool.quillpen.QuillPen;

public class DrawingTools {
    private IDrawingTool drawingTool;
    private MarkerPaths markerPaths;
    private QuillPen quillPen;
    private TrailOptions trailOptions;

    public IDrawingTool getDrawingTool() {
        return this.drawingTool;
    }

    public TrailOptions getTrailOptions() {
        return this.trailOptions;
    }

    public DrawingTools(View view, AnimManager animManager) {
        initDrawingTools(view, animManager);
    }

    private void initDrawingTools(View view, AnimManager animManager) {
        AndroidMetrics androidMetrics = AndroidMetrics.get(view.getContext());
        this.trailOptions = new TrailOptions(androidMetrics, this);
        DrawingToolsContext drawingToolsContext = new DrawingToolsContext(view, animManager, androidMetrics, this.trailOptions);
        this.markerPaths = new MarkerPaths(drawingToolsContext);
        this.quillPen = new QuillPen(drawingToolsContext);
        selectQuillPen();
    }

    public void selectMarkerPen() {
        this.drawingTool = this.markerPaths;
    }

    public void selectQuillPen() {
        this.drawingTool = this.quillPen;
    }
}
