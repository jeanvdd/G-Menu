package com.orange.dgil.callback;

import com.orange.dgil.Dgil;
import com.orange.dgil.model.loader.ModelsLoader;

public class DgilDefaultListener implements DgilListener {
    protected final Dgil dgil;
    private ModelsLoader modelsLoader;
    private boolean selected;
    private boolean symbolsMayChange;

    public Dgil dgil() {
        return this.dgil;
    }

    public DgilDefaultListener(ListenerParams parameters) {
        this.dgil = parameters.dgil();
        initModels(parameters);
    }

    public void symbolsMayChange() {
        this.symbolsMayChange = true;
    }

    public void onListenerSelected() {
        this.selected = true;
        this.dgil.resetSymbolicMultistrokeMem();
        if (this.modelsLoader != null) {
            this.symbolsMayChange = false;
            setLoadModelsRequest(true);
        }
    }

    public void onListenerUnselected() {
        if (this.modelsLoader != null) {
            setLoadModelsRequest(false);
            saveAdaptation();
        }
        this.selected = false;
    }

    public void setLoadModelsRequest(boolean loadRequest) {
        if (this.modelsLoader != null) {
            this.modelsLoader.setLoadModelsRequest(loadRequest);
        } else {
            System.out.println(DgilDefaultListener.class.getSimpleName() + ": You need to fetch models before trying to use it...");
        }
    }

    public void saveAdaptation() {
        if (this.modelsLoader != null && this.modelsLoader.modelsLoaded() && this.selected && !this.symbolsMayChange) {
            this.modelsLoader.saveAdaptation();
        }
    }

    private void initModels(ListenerParams parameters) {
        if (parameters.hasModelsStreamProvider()) {
            fetchModels(parameters.modelsStreamProvider());
        }
    }

    private void fetchModels(ModelsStreamProvider modelsStreamProvider) {
        if (this.modelsLoader != null) {
            System.out.println(DgilDefaultListener.class.getSimpleName() + ": Are you sure you want to reload models from scratch?");
        }
        this.modelsLoader = new ModelsLoader(this.dgil, modelsStreamProvider);
    }

    public void pressDetected(int downX, int downY) {
    }

    public void shortPressDetected(int downX, int downY) {
    }

    public void longPressDetected(int downX, int downY) {
    }

    public void longLongPressDetected(int downX, int downY) {
    }

    public void releaseLongPressDetected(int downX, int downY) {
    }

    public void releaseLongLongPressDetected(int downX, int downY) {
    }

    public void tapDetected(int downX, int downY) {
    }

    public void pendingDoubleTapDetected() {
    }

    public void doubleTapPressDetected() {
    }

    public void doubleTapDetected() {
    }

    public void longPressDoubleTapDetected() {
    }

    public void startMoveDetected() {
    }

    public void dragDetected() {
    }

    public void breakDragDetected() {
    }

    public void endDragDetected() {
    }

    public void fastStraightDetected() {
    }

    public void preSymbolicPatternDetected() {
    }

    public void symbolicPatternDetected() {
    }

    public void flickDetected(FlickParameters flickParameters) {
    }

    public void breakFlickDetected(FlickParameters flickParameters) {
    }

    public void symbolicDetected(SymbolParameters symbolParameters) {
    }

    public void breakSymbolicDetected(SymbolParameters symbolParameters) {
    }

    public void clockwiseRotationDetected(int rotationAngle) {
    }

    public void anticlockwiseRotationDetected(int rotationAngle) {
    }

    public void breakRotationDetected() {
    }

    public void endRotationDetected() {
    }

    public void regularCurvedDetected() {
    }

    public void seqMoveUpDetected() {
    }

    public void seqMoveDownDetected() {
    }

    public void seqMoveLeftDetected() {
    }

    public void seqMoveRightDetected() {
    }

    public void seqEarlyMoveUpDetected(int seqRelativePosPercent) {
    }

    public void seqEarlyMoveDownDetected(int seqRelativePosPercent) {
    }

    public void seqEarlyMoveLeftDetected(int seqRelativePosPercent) {
    }

    public void seqEarlyMoveRightDetected(int seqRelativePosPercent) {
    }

    public void seqCancelEarlyMove() {
    }

    public void endGestureDetected() {
    }
}
