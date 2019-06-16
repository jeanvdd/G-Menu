package com.orange.dgil.callback;

public interface DgilCallbacks {
    void anticlockwiseRotationDetected(int i);

    void breakDragDetected();

    void breakFlickDetected(FlickParameters flickParameters);

    void breakRotationDetected();

    void breakSymbolicDetected(SymbolParameters symbolParameters);

    void clockwiseRotationDetected(int i);

    void doubleTapDetected();

    void doubleTapPressDetected();

    void dragDetected();

    void endDragDetected();

    void endGestureDetected();

    void endRotationDetected();

    void fastStraightDetected();

    void flickDetected(FlickParameters flickParameters);

    void longLongPressDetected(int i, int i2);

    void longPressDetected(int i, int i2);

    void longPressDoubleTapDetected();

    void pendingDoubleTapDetected();

    void preSymbolicPatternDetected();

    void pressDetected(int i, int i2);

    void regularCurvedDetected();

    void releaseLongLongPressDetected(int i, int i2);

    void releaseLongPressDetected(int i, int i2);

    void seqCancelEarlyMove();

    void seqEarlyMoveDownDetected(int i);

    void seqEarlyMoveLeftDetected(int i);

    void seqEarlyMoveRightDetected(int i);

    void seqEarlyMoveUpDetected(int i);

    void seqMoveDownDetected();

    void seqMoveLeftDetected();

    void seqMoveRightDetected();

    void seqMoveUpDetected();

    void shortPressDetected(int i, int i2);

    void startMoveDetected();

    void symbolicDetected(SymbolParameters symbolParameters);

    void symbolicPatternDetected();

    void tapDetected(int i, int i2);
}
