package com.orange.dgil.msh;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;

public class MSHColors {
    private int dragColor = 0;
    private int symbolicColor = InputDeviceCompat.SOURCE_ANY;
    private int symbolicColorNok = SupportMenu.CATEGORY_MASK;
    private int symbolicColorOk = -16711936;

    public void setDragColor(int dragColor) {
        this.dragColor = dragColor;
    }

    public void setSymbolicColor(int symbolicColor) {
        this.symbolicColor = symbolicColor;
    }

    public void setSymbolicColorNok(int symbolicColorNok) {
        this.symbolicColorNok = symbolicColorNok;
    }

    public void setSymbolicColorOk(int symbolicColorOk) {
        this.symbolicColorOk = symbolicColorOk;
    }

    public int getDragColor() {
        return this.dragColor;
    }

    public int getSymbolicColor() {
        return this.symbolicColor;
    }

    public int getSymbolicColorOk() {
        return this.symbolicColorOk;
    }

    public int getSymbolicColorNok() {
        return this.symbolicColorNok;
    }

    public boolean isDragColorTransparent() {
        return this.dragColor == 0;
    }
}
