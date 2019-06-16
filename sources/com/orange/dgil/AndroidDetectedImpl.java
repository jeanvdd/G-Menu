package com.orange.dgil;

import android.widget.Toast;
import com.google.common.annotations.VisibleForTesting;

class AndroidDetectedImpl {
    private static final int PEP_THRESH = 90;
    private final Context ctx;

    AndroidDetectedImpl(Context ctx) {
        this.ctx = ctx;
    }

    void tapDetected(int x, int y) {
        DgilAndroid dgil = this.ctx.getDgil();
        if (shouldSimulateTap(dgil)) {
            dgil.pressFromDgil(x, y);
            dgil.releaseFromDgil(1, 0, 1);
        }
    }

    @VisibleForTesting
    boolean shouldSimulateTap(DgilAndroid dgil) {
        return (dgil.inForward() || dgil.getSettings().getSeqEnabled()) ? false : true;
    }

    void flickDetected() {
        this.ctx.getFromDgil().setFlickReplayMode(true);
        this.ctx.getForwardFlick().replay();
    }

    boolean symbolicDetectedInternal(String s1, int sim1) {
        if (!s1.equals("pep")) {
            return false;
        }
        this.ctx.getDrawer().hide();
        if (sim1 > 90) {
            showPepToast();
        }
        return true;
    }

    @VisibleForTesting
    void showPepToast() {
        Toast.makeText(this.ctx.getDgilView().getContext(), "Créer c'est résister, résister c'est créer.\n\nA nos enfants, Daphnée Méline et Tristan, Yann, Erell et Louen, Eléonore et Victor.", 1).show();
    }
}
