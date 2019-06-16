package com.orange.dgil.callback;

import com.orange.dgil.conf.Settings;
import java.util.HashMap;
import java.util.Map;

public class SymbolParameters {
    private Map<Integer, Integer> hashProtosSims;
    private int[] protosSims;
    private final RecoStrategy recoStrategy;
    private String symbol1;
    private String symbol2;
    private int symbolSim1;
    private int symbolSim2;
    private int symbolSquareSideMaxlen;

    public String symbol1() {
        return this.symbol1;
    }

    public String symbol2() {
        return this.symbol2;
    }

    public int symbolSim1() {
        return this.symbolSim1;
    }

    public int symbolSim2() {
        return this.symbolSim2;
    }

    public int symbolSquareSideMaxlen() {
        return this.symbolSquareSideMaxlen;
    }

    SymbolParameters(Settings dgilSettings) {
        this.recoStrategy = new RecoStrategy(dgilSettings, this);
    }

    void set(String symbol1, String symbol2, int symbolSim1, int symbolSim2, int symbolSquareSideMaxlen, int[] protosSims) {
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
        this.symbolSim1 = symbolSim1;
        this.symbolSim2 = symbolSim2;
        this.symbolSquareSideMaxlen = symbolSquareSideMaxlen;
        this.protosSims = protosSims;
        updateHashProtoSims();
    }

    public boolean isRejected() {
        return this.recoStrategy.isRejected();
    }

    public boolean isAmbiguous() {
        return this.recoStrategy.isAmbiguous();
    }

    public boolean isTop2Rejected() {
        return this.recoStrategy.isTop2Rejected();
    }

    public Map<Integer, Integer> getHashProtosSims() {
        if (this.hashProtosSims != null) {
            return this.hashProtosSims;
        }
        throw new DgilNoPrototypesAvailableException("no symbol prototypes available");
    }

    private void updateHashProtoSims() {
        if (this.protosSims == null) {
            this.hashProtosSims = null;
            return;
        }
        this.hashProtosSims = new HashMap();
        for (int i = 0; i < this.protosSims.length - 1; i += 2) {
            this.hashProtosSims.put(Integer.valueOf(this.protosSims[i]), Integer.valueOf(this.protosSims[i + 1]));
        }
    }
}
