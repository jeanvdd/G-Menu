package com.orange.dgil.callback;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.conf.Settings;
import java.util.Arrays;
import java.util.Collection;

class RecoStrategy {
    private final Settings dgilSettings;
    private final Collection<String> rejectedSymbols = Arrays.asList(new String[]{"rejected", "junk"});
    private final SymbolParameters symbolParams;

    RecoStrategy(Settings dgilSettings, SymbolParameters symbolParams) {
        this.dgilSettings = dgilSettings;
        this.symbolParams = symbolParams;
    }

    public boolean isRejected() {
        return isSymbolRejected(this.symbolParams.symbol1(), this.symbolParams.symbolSim1(), this.symbolParams.symbolSquareSideMaxlen());
    }

    public boolean isAmbiguous() {
        return this.symbolParams.symbolSim1() - this.symbolParams.symbolSim2() <= this.dgilSettings.getRecoRelativeRejectionThreshold();
    }

    public boolean isTop2Rejected() {
        return isSymbolRejected(this.symbolParams.symbol2(), this.symbolParams.symbolSim2(), this.symbolParams.symbolSquareSideMaxlen());
    }

    @VisibleForTesting
    boolean isSymbolRejected(String symbolName, int similarity, int sizeUm) {
        return isRejectedOnName(symbolName) || isRejectedOnSimilarity(similarity) || isRejectedOnSize(sizeUm);
    }

    @VisibleForTesting
    boolean isRejectedOnName(String symbolName) {
        return this.rejectedSymbols.contains(symbolName);
    }

    @VisibleForTesting
    boolean isRejectedOnSimilarity(int similarity) {
        return similarity < this.dgilSettings.getRecoAbsoluteRejectionThreshold();
    }

    @VisibleForTesting
    boolean isRejectedOnSize(int sizeUm) {
        return sizeUm < this.dgilSettings.getRecoMinSquareUm();
    }
}
