package com.orange.dgil.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.util.BufferRecycler;

public class Settings {
    private static final int ACP_SIZE_BOX_MIN_UM = 7000;
    private static final boolean DISABLE_ON_MULTITOUCH = false;
    private static final boolean DOUBLETAP_ENABLED = false;
    private static final int DOUBLE_TAP_MAX_DURATION_MS = 500;
    private static final boolean FLICK_REPLAY_ENABLED = false;
    private static final int GLOBAL_MIN_SQUARE_UM = 2000;
    private static final int LONG_LONG_PRESS_TIMEOUT_MS = 2000;
    private static final int LONG_PRESS_DOUBLETAP_TIMEOUT_MS = 1000;
    private static final int LONG_PRESS_TIMEOUT_MS = 500;
    private static final boolean MSH_ENABLED = false;
    private static final int ONLINE_VECT_ERR_UM = 500;
    private static final int RECO_ABSOLUTE_REJECTION_THRESHOLD = 70;
    private static final int RECO_MIN_SQUARE_UM = 7000;
    private static final int RECO_RELATIVE_REJECTION_THRESHOLD = 5;
    private static final int ROTATION_STEP_DG = 30;
    private static final boolean SEQ_ABSCISSA_FROZEN = false;
    private static final int SEQ_BLOCKING_DISTANCE = 4000;
    private static final boolean SEQ_ENABLED = false;
    private static final boolean SEQ_INERTIA_ENABLED = true;
    private static final int SEQ_INERTIA_MASS = 40;
    private static final int SEQ_INERTIA_POS_CONST = 50000;
    private static final int SEQ_INERTIA_VERTICAL_MOVE_THRESH = 1;
    private static final boolean SEQ_ORDINATE_FROZEN = false;
    private static final int SEQ_SIGNIFICANT_DIST_X_UM = 15000;
    private static final int SEQ_SIGNIFICANT_DIST_Y_UM = 13000;
    private static final int SEQ_TH_START_INERTIA_VELOCITY_Y = 80000;
    private static final int SEQ_TYPE = 2;
    private static final int SHORT_PRESS_TIMEOUT_MS = 100;
    private static final int THRESHOLD_ACCELERATION_REGULAR = 300000;
    private static final int THRESHOLD_ENTER_ROTATION_DG = 720;
    private static final int THRESHOLD_VELOCITY_HIGH = 150000;
    private static final int THRESHOLD_VELOCITY_VERY_LOW_END = 2500;
    private static final float TH_ACP_FORM_FACTOR_1 = 0.27f;
    private static final float TH_ACP_FORM_FACTOR_2 = 0.27f;
    private static final float TH_MSH_FORM_FACTOR = 0.27f;
    private static final int T_MIN_REGULAR_MS = 180;
    private final IntegerField acpSizeBoxMinUm;
    private final BooleanField disableOnMultitouch;
    private final IntegerField doubleTapMaxDurationMs;
    private final BooleanField doubletapEnabled;
    private final List<Field> fields = new ArrayList();
    private final BooleanField flickReplayEnabled;
    private final IntegerField globalMinSquareUm;
    private final IntegerField longLongPressTimeoutMs;
    private final IntegerField longPressDoubletapTimeoutMs;
    private final IntegerField longPressTimeoutMs;
    private final BooleanField mshEnabled;
    private final IntegerField onlineVectErrUm;
    private final IntegerField recoAbsoluteRejectionThreshold;
    private final IntegerField recoMinSquareUm;
    private final IntegerField recoRelativeRejectionThreshold;
    private final IntegerField rotationStepDg;
    private final BooleanField seqAbscissaFrozen;
    private final IntegerField seqBlockingDistance;
    private final BooleanField seqEnabled;
    private final BooleanField seqInertiaEnabled;
    private final IntegerField seqInertiaMass;
    private final IntegerField seqInertiaPosConst;
    private final IntegerField seqInertiaVerticalMoveThresh;
    private final BooleanField seqOrdinateFrozen;
    private final IntegerField seqSignificantDistXUm;
    private final IntegerField seqSignificantDistYUm;
    private final IntegerField seqThStartInertiaVelocityY;
    private final IntegerField seqType;
    private final IntegerField shortPressTimeoutMs;
    private final IntegerField tMinRegularMs;
    private final FloatField thAcpFormFactor1;
    private final FloatField thAcpFormFactor2;
    private final FloatField thMshFormFactor;
    private final IntegerField thresholdAccelerationRegular;
    private final IntegerField thresholdEnterRotationDg;
    private final IntegerField thresholdVelocityHigh;
    private final IntegerField thresholdVelocityVeryLowEnd;

    public Settings(IFieldGetterSetter i) {
        int id = 0 + 1;
        this.seqAbscissaFrozen = new BooleanField(i, false, "seq_abscissa_frozen", 0);
        this.fields.add(this.seqAbscissaFrozen);
        int id2 = id + 1;
        this.thresholdEnterRotationDg = new IntegerField(i, THRESHOLD_ENTER_ROTATION_DG, "threshold_enter_rotation_dg", id);
        this.fields.add(this.thresholdEnterRotationDg);
        id = id2 + 1;
        this.seqInertiaEnabled = new BooleanField(i, SEQ_INERTIA_ENABLED, "seq_inertia_enabled", id2);
        this.fields.add(this.seqInertiaEnabled);
        id2 = id + 1;
        this.thresholdVelocityHigh = new IntegerField(i, THRESHOLD_VELOCITY_HIGH, "threshold_velocity_high", id);
        this.fields.add(this.thresholdVelocityHigh);
        id = id2 + 1;
        this.recoAbsoluteRejectionThreshold = new IntegerField(i, 70, "reco_absolute_rejection_threshold", id2);
        this.fields.add(this.recoAbsoluteRejectionThreshold);
        id2 = id + 1;
        this.globalMinSquareUm = new IntegerField(i, BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN, "global_min_square_um", id);
        this.fields.add(this.globalMinSquareUm);
        id = id2 + 1;
        this.disableOnMultitouch = new BooleanField(i, false, "disable_on_multitouch", id2);
        this.fields.add(this.disableOnMultitouch);
        id2 = id + 1;
        this.thresholdVelocityVeryLowEnd = new IntegerField(i, 2500, "threshold_velocity_very_low_end", id);
        this.fields.add(this.thresholdVelocityVeryLowEnd);
        id = id2 + 1;
        this.seqOrdinateFrozen = new BooleanField(i, false, "seq_ordinate_frozen", id2);
        this.fields.add(this.seqOrdinateFrozen);
        id2 = id + 1;
        this.thresholdAccelerationRegular = new IntegerField(i, THRESHOLD_ACCELERATION_REGULAR, "threshold_acceleration_regular", id);
        this.fields.add(this.thresholdAccelerationRegular);
        id = id2 + 1;
        this.recoRelativeRejectionThreshold = new IntegerField(i, 5, "reco_relative_rejection_threshold", id2);
        this.fields.add(this.recoRelativeRejectionThreshold);
        id2 = id + 1;
        this.longPressTimeoutMs = new IntegerField(i, 500, "long_press_timeout_ms", id);
        this.fields.add(this.longPressTimeoutMs);
        id = id2 + 1;
        this.seqSignificantDistYUm = new IntegerField(i, SEQ_SIGNIFICANT_DIST_Y_UM, "seq_significant_dist_y_um", id2);
        this.fields.add(this.seqSignificantDistYUm);
        id2 = id + 1;
        this.mshEnabled = new BooleanField(i, false, "msh_enabled", id);
        this.fields.add(this.mshEnabled);
        id = id2 + 1;
        this.seqThStartInertiaVelocityY = new IntegerField(i, SEQ_TH_START_INERTIA_VELOCITY_Y, "seq_th_start_inertia_velocity_y", id2);
        this.fields.add(this.seqThStartInertiaVelocityY);
        id2 = id + 1;
        this.rotationStepDg = new IntegerField(i, 30, "rotation_step_dg", id);
        this.fields.add(this.rotationStepDg);
        id = id2 + 1;
        this.thMshFormFactor = new FloatField(i, 0.27f, "th_msh_form_factor", id2);
        this.fields.add(this.thMshFormFactor);
        id2 = id + 1;
        this.seqType = new IntegerField(i, 2, "seq_type", id);
        this.fields.add(this.seqType);
        id = id2 + 1;
        this.seqInertiaMass = new IntegerField(i, 40, "seq_inertia_mass", id2);
        this.fields.add(this.seqInertiaMass);
        id2 = id + 1;
        this.shortPressTimeoutMs = new IntegerField(i, 100, "short_press_timeout_ms", id);
        this.fields.add(this.shortPressTimeoutMs);
        id = id2 + 1;
        this.doubleTapMaxDurationMs = new IntegerField(i, 500, "double_tap_max_duration_ms", id2);
        this.fields.add(this.doubleTapMaxDurationMs);
        id2 = id + 1;
        this.longPressDoubletapTimeoutMs = new IntegerField(i, 1000, "long_press_doubletap_timeout_ms", id);
        this.fields.add(this.longPressDoubletapTimeoutMs);
        id = id2 + 1;
        this.flickReplayEnabled = new BooleanField(i, false, "flick_replay_enabled", id2);
        this.fields.add(this.flickReplayEnabled);
        id2 = id + 1;
        this.tMinRegularMs = new IntegerField(i, T_MIN_REGULAR_MS, "t_min_regular_ms", id);
        this.fields.add(this.tMinRegularMs);
        id = id2 + 1;
        this.thAcpFormFactor2 = new FloatField(i, 0.27f, "th_acp_form_factor_2", id2);
        this.fields.add(this.thAcpFormFactor2);
        id2 = id + 1;
        this.thAcpFormFactor1 = new FloatField(i, 0.27f, "th_acp_form_factor_1", id);
        this.fields.add(this.thAcpFormFactor1);
        id = id2 + 1;
        this.recoMinSquareUm = new IntegerField(i, 7000, "reco_min_square_um", id2);
        this.fields.add(this.recoMinSquareUm);
        id2 = id + 1;
        this.doubletapEnabled = new BooleanField(i, false, "doubletap_enabled", id);
        this.fields.add(this.doubletapEnabled);
        id = id2 + 1;
        this.acpSizeBoxMinUm = new IntegerField(i, 7000, "acp_size_box_min_um", id2);
        this.fields.add(this.acpSizeBoxMinUm);
        id2 = id + 1;
        this.longLongPressTimeoutMs = new IntegerField(i, BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN, "long_long_press_timeout_ms", id);
        this.fields.add(this.longLongPressTimeoutMs);
        id = id2 + 1;
        this.seqBlockingDistance = new IntegerField(i, SEQ_BLOCKING_DISTANCE, "seq_blocking_distance", id2);
        this.fields.add(this.seqBlockingDistance);
        id2 = id + 1;
        this.seqInertiaVerticalMoveThresh = new IntegerField(i, 1, "seq_inertia_vertical_move_thresh", id);
        this.fields.add(this.seqInertiaVerticalMoveThresh);
        id = id2 + 1;
        this.onlineVectErrUm = new IntegerField(i, 500, "online_vect_err_um", id2);
        this.fields.add(this.onlineVectErrUm);
        id2 = id + 1;
        this.seqInertiaPosConst = new IntegerField(i, SEQ_INERTIA_POS_CONST, "seq_inertia_pos_const", id);
        this.fields.add(this.seqInertiaPosConst);
        id = id2 + 1;
        this.seqEnabled = new BooleanField(i, false, "seq_enabled", id2);
        this.fields.add(this.seqEnabled);
        id2 = id + 1;
        this.seqSignificantDistXUm = new IntegerField(i, SEQ_SIGNIFICANT_DIST_X_UM, "seq_significant_dist_x_um", id);
        this.fields.add(this.seqSignificantDistXUm);
    }

    public boolean getSeqAbscissaFrozen() {
        return this.seqAbscissaFrozen.get();
    }

    public BooleanField getSeqAbscissaFrozenField() {
        return this.seqAbscissaFrozen;
    }

    public void setSeqAbscissaFrozen(boolean val) {
        this.seqAbscissaFrozen.set(val);
    }

    public int getThresholdEnterRotationDg() {
        return this.thresholdEnterRotationDg.get();
    }

    public IntegerField getThresholdEnterRotationDgField() {
        return this.thresholdEnterRotationDg;
    }

    public void setThresholdEnterRotationDg(int val) {
        this.thresholdEnterRotationDg.set(val);
    }

    public boolean getSeqInertiaEnabled() {
        return this.seqInertiaEnabled.get();
    }

    public BooleanField getSeqInertiaEnabledField() {
        return this.seqInertiaEnabled;
    }

    public void setSeqInertiaEnabled(boolean val) {
        this.seqInertiaEnabled.set(val);
    }

    public int getThresholdVelocityHigh() {
        return this.thresholdVelocityHigh.get();
    }

    public IntegerField getThresholdVelocityHighField() {
        return this.thresholdVelocityHigh;
    }

    public void setThresholdVelocityHigh(int val) {
        this.thresholdVelocityHigh.set(val);
    }

    public int getRecoAbsoluteRejectionThreshold() {
        return this.recoAbsoluteRejectionThreshold.get();
    }

    public IntegerField getRecoAbsoluteRejectionThresholdField() {
        return this.recoAbsoluteRejectionThreshold;
    }

    public void setRecoAbsoluteRejectionThreshold(int val) {
        this.recoAbsoluteRejectionThreshold.set(val);
    }

    public int getGlobalMinSquareUm() {
        return this.globalMinSquareUm.get();
    }

    public IntegerField getGlobalMinSquareUmField() {
        return this.globalMinSquareUm;
    }

    public void setGlobalMinSquareUm(int val) {
        this.globalMinSquareUm.set(val);
    }

    public boolean getDisableOnMultitouch() {
        return this.disableOnMultitouch.get();
    }

    public BooleanField getDisableOnMultitouchField() {
        return this.disableOnMultitouch;
    }

    public void setDisableOnMultitouch(boolean val) {
        this.disableOnMultitouch.set(val);
    }

    public int getThresholdVelocityVeryLowEnd() {
        return this.thresholdVelocityVeryLowEnd.get();
    }

    public IntegerField getThresholdVelocityVeryLowEndField() {
        return this.thresholdVelocityVeryLowEnd;
    }

    public void setThresholdVelocityVeryLowEnd(int val) {
        this.thresholdVelocityVeryLowEnd.set(val);
    }

    public boolean getSeqOrdinateFrozen() {
        return this.seqOrdinateFrozen.get();
    }

    public BooleanField getSeqOrdinateFrozenField() {
        return this.seqOrdinateFrozen;
    }

    public void setSeqOrdinateFrozen(boolean val) {
        this.seqOrdinateFrozen.set(val);
    }

    public int getThresholdAccelerationRegular() {
        return this.thresholdAccelerationRegular.get();
    }

    public IntegerField getThresholdAccelerationRegularField() {
        return this.thresholdAccelerationRegular;
    }

    public void setThresholdAccelerationRegular(int val) {
        this.thresholdAccelerationRegular.set(val);
    }

    public int getRecoRelativeRejectionThreshold() {
        return this.recoRelativeRejectionThreshold.get();
    }

    public IntegerField getRecoRelativeRejectionThresholdField() {
        return this.recoRelativeRejectionThreshold;
    }

    public void setRecoRelativeRejectionThreshold(int val) {
        this.recoRelativeRejectionThreshold.set(val);
    }

    public int getLongPressTimeoutMs() {
        return this.longPressTimeoutMs.get();
    }

    public IntegerField getLongPressTimeoutMsField() {
        return this.longPressTimeoutMs;
    }

    public void setLongPressTimeoutMs(int val) {
        this.longPressTimeoutMs.set(val);
    }

    public int getSeqSignificantDistYUm() {
        return this.seqSignificantDistYUm.get();
    }

    public IntegerField getSeqSignificantDistYUmField() {
        return this.seqSignificantDistYUm;
    }

    public void setSeqSignificantDistYUm(int val) {
        this.seqSignificantDistYUm.set(val);
    }

    public boolean getMshEnabled() {
        return this.mshEnabled.get();
    }

    public BooleanField getMshEnabledField() {
        return this.mshEnabled;
    }

    public void setMshEnabled(boolean val) {
        this.mshEnabled.set(val);
    }

    public int getSeqThStartInertiaVelocityY() {
        return this.seqThStartInertiaVelocityY.get();
    }

    public IntegerField getSeqThStartInertiaVelocityYField() {
        return this.seqThStartInertiaVelocityY;
    }

    public void setSeqThStartInertiaVelocityY(int val) {
        this.seqThStartInertiaVelocityY.set(val);
    }

    public int getRotationStepDg() {
        return this.rotationStepDg.get();
    }

    public IntegerField getRotationStepDgField() {
        return this.rotationStepDg;
    }

    public void setRotationStepDg(int val) {
        this.rotationStepDg.set(val);
    }

    public float getThMshFormFactor() {
        return this.thMshFormFactor.get();
    }

    public FloatField getThMshFormFactorField() {
        return this.thMshFormFactor;
    }

    public void setThMshFormFactor(float val) {
        this.thMshFormFactor.set(val);
    }

    public int getSeqType() {
        return this.seqType.get();
    }

    public IntegerField getSeqTypeField() {
        return this.seqType;
    }

    public void setSeqType(int val) {
        this.seqType.set(val);
    }

    public int getSeqInertiaMass() {
        return this.seqInertiaMass.get();
    }

    public IntegerField getSeqInertiaMassField() {
        return this.seqInertiaMass;
    }

    public void setSeqInertiaMass(int val) {
        this.seqInertiaMass.set(val);
    }

    public int getShortPressTimeoutMs() {
        return this.shortPressTimeoutMs.get();
    }

    public IntegerField getShortPressTimeoutMsField() {
        return this.shortPressTimeoutMs;
    }

    public void setShortPressTimeoutMs(int val) {
        this.shortPressTimeoutMs.set(val);
    }

    public int getDoubleTapMaxDurationMs() {
        return this.doubleTapMaxDurationMs.get();
    }

    public IntegerField getDoubleTapMaxDurationMsField() {
        return this.doubleTapMaxDurationMs;
    }

    public void setDoubleTapMaxDurationMs(int val) {
        this.doubleTapMaxDurationMs.set(val);
    }

    public int getLongPressDoubletapTimeoutMs() {
        return this.longPressDoubletapTimeoutMs.get();
    }

    public IntegerField getLongPressDoubletapTimeoutMsField() {
        return this.longPressDoubletapTimeoutMs;
    }

    public void setLongPressDoubletapTimeoutMs(int val) {
        this.longPressDoubletapTimeoutMs.set(val);
    }

    public boolean getFlickReplayEnabled() {
        return this.flickReplayEnabled.get();
    }

    public BooleanField getFlickReplayEnabledField() {
        return this.flickReplayEnabled;
    }

    public void setFlickReplayEnabled(boolean val) {
        this.flickReplayEnabled.set(val);
    }

    public int getTMinRegularMs() {
        return this.tMinRegularMs.get();
    }

    public IntegerField getTMinRegularMsField() {
        return this.tMinRegularMs;
    }

    public void setTMinRegularMs(int val) {
        this.tMinRegularMs.set(val);
    }

    public float getThAcpFormFactor2() {
        return this.thAcpFormFactor2.get();
    }

    public FloatField getThAcpFormFactor2Field() {
        return this.thAcpFormFactor2;
    }

    public void setThAcpFormFactor2(float val) {
        this.thAcpFormFactor2.set(val);
    }

    public float getThAcpFormFactor1() {
        return this.thAcpFormFactor1.get();
    }

    public FloatField getThAcpFormFactor1Field() {
        return this.thAcpFormFactor1;
    }

    public void setThAcpFormFactor1(float val) {
        this.thAcpFormFactor1.set(val);
    }

    public int getRecoMinSquareUm() {
        return this.recoMinSquareUm.get();
    }

    public IntegerField getRecoMinSquareUmField() {
        return this.recoMinSquareUm;
    }

    public void setRecoMinSquareUm(int val) {
        this.recoMinSquareUm.set(val);
    }

    public boolean getDoubletapEnabled() {
        return this.doubletapEnabled.get();
    }

    public BooleanField getDoubletapEnabledField() {
        return this.doubletapEnabled;
    }

    public void setDoubletapEnabled(boolean val) {
        this.doubletapEnabled.set(val);
    }

    public int getAcpSizeBoxMinUm() {
        return this.acpSizeBoxMinUm.get();
    }

    public IntegerField getAcpSizeBoxMinUmField() {
        return this.acpSizeBoxMinUm;
    }

    public void setAcpSizeBoxMinUm(int val) {
        this.acpSizeBoxMinUm.set(val);
    }

    public int getLongLongPressTimeoutMs() {
        return this.longLongPressTimeoutMs.get();
    }

    public IntegerField getLongLongPressTimeoutMsField() {
        return this.longLongPressTimeoutMs;
    }

    public void setLongLongPressTimeoutMs(int val) {
        this.longLongPressTimeoutMs.set(val);
    }

    public int getSeqBlockingDistance() {
        return this.seqBlockingDistance.get();
    }

    public IntegerField getSeqBlockingDistanceField() {
        return this.seqBlockingDistance;
    }

    public void setSeqBlockingDistance(int val) {
        this.seqBlockingDistance.set(val);
    }

    public int getSeqInertiaVerticalMoveThresh() {
        return this.seqInertiaVerticalMoveThresh.get();
    }

    public IntegerField getSeqInertiaVerticalMoveThreshField() {
        return this.seqInertiaVerticalMoveThresh;
    }

    public void setSeqInertiaVerticalMoveThresh(int val) {
        this.seqInertiaVerticalMoveThresh.set(val);
    }

    public int getOnlineVectErrUm() {
        return this.onlineVectErrUm.get();
    }

    public IntegerField getOnlineVectErrUmField() {
        return this.onlineVectErrUm;
    }

    public void setOnlineVectErrUm(int val) {
        this.onlineVectErrUm.set(val);
    }

    public int getSeqInertiaPosConst() {
        return this.seqInertiaPosConst.get();
    }

    public IntegerField getSeqInertiaPosConstField() {
        return this.seqInertiaPosConst;
    }

    public void setSeqInertiaPosConst(int val) {
        this.seqInertiaPosConst.set(val);
    }

    public boolean getSeqEnabled() {
        return this.seqEnabled.get();
    }

    public BooleanField getSeqEnabledField() {
        return this.seqEnabled;
    }

    public void setSeqEnabled(boolean val) {
        this.seqEnabled.set(val);
    }

    public int getSeqSignificantDistXUm() {
        return this.seqSignificantDistXUm.get();
    }

    public IntegerField getSeqSignificantDistXUmField() {
        return this.seqSignificantDistXUm;
    }

    public void setSeqSignificantDistXUm(int val) {
        this.seqSignificantDistXUm.set(val);
    }

    public List<BooleanField> getBooleanFields() {
        return Field.getBooleanFields(this.fields);
    }

    public List<IntegerField> getIntegerFields() {
        return Field.getIntegerFields(this.fields);
    }

    public List<FloatField> getFloatFields() {
        return Field.getFloatFields(this.fields);
    }

    public List<StringField> getStringFields() {
        return Field.getStringFields(this.fields);
    }

    public void set(String name, String val) {
        Field.set(this.fields, name, val);
    }

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap();
        for (BooleanField b : getBooleanFields()) {
            map.put(b.getName(), b.get() ? "true" : "false");
        }
        for (IntegerField i : getIntegerFields()) {
            map.put(i.getName(), Integer.toString(i.get()));
        }
        for (FloatField f : getFloatFields()) {
            map.put(f.getName(), Float.toString(f.get()));
        }
        for (StringField str : getStringFields()) {
            map.put(str.getName(), str.get());
        }
        return map;
    }

    public Map<String, String> getDefMap() {
        Map<String, String> map = new HashMap();
        for (BooleanField b : getBooleanFields()) {
            map.put(b.getName(), b.getDef() ? "true" : "false");
        }
        for (IntegerField i : getIntegerFields()) {
            map.put(i.getName(), Integer.toString(i.get()));
        }
        for (FloatField f : getFloatFields()) {
            map.put(f.getName(), Float.toString(f.get()));
        }
        for (StringField str : getStringFields()) {
            map.put(str.getName(), str.get());
        }
        return map;
    }

    public void setMap(Map<String, String> map) {
        for (Entry<String, String> e : map.entrySet()) {
            set((String) e.getKey(), (String) e.getValue());
        }
    }

    public void restoreDefaults() {
        setMap(getDefMap());
    }
}
