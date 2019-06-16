package com.orangelabs.simplefiltering;

import android.graphics.Color;
import android.support.v7.widget.ActivityChooserView.ActivityChooserViewAdapter;
import android.util.Log;
import android.widget.SearchView;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;
import com.orange.android.activitylifecycle.LifecycleEvent;
import com.orange.dgil.AndroidListenerParams;
import com.orange.dgil.Dgil;
import com.orange.dgil.callback.FlickParameters;
import com.orange.dgil.callback.ListenerParams;
import com.orange.dgil.callback.SymbolParameters;
import com.orange.dgil.conf.Modes.Mode;
import com.orange.dgil.conf.Settings;
import com.orange.dgil.msh.DgilDefaultMSHlistener;
import com.orange.dgil.msh.MSHColors;
import com.orange.dgil.trail.android.ITrailDrawer;
import com.orange.dgil.trail.android.impl.TrailDrawer;
import java.io.IOException;

public class MyMshListener extends DgilDefaultMSHlistener {
    public static volatile transient /* synthetic */ IncrementalChange $change = null;
    private static final String ADAPTATION_FILENAME = "abc_ox9_july04.bin";
    private static final String ALPHABETIC_SET = "abcdefghijklmnopqrstuvwxyz";
    private static final int COL_SYM = -16777216;
    private static final int COL_SYM_DRAG = Color.argb(40, 0, 0, 0);
    private static final int COL_SYM_NOK = -65536;
    private static final int COL_SYM_OK = -16777216;
    private static final String MODELS_ASSET_FILENAME = "abc_ox9_july04.json";
    private EpisodeHistory episodeHistory;
    private boolean flagAlternative;
    private boolean flagOneStroke;
    private boolean flagSymbolicPattern;
    private GestureEpisode gestureEpisode;
    private String mem_alternative;
    private SearchView mshFilter;

    MyMshListener(Object[] objArr, InstantReloadException instantReloadException) {
        switch (((String) objArr[0]).hashCode()) {
            case 377147342:
                super((ListenerParams) objArr[1]);
                return;
            case 1687813161:
                this((Dgil) objArr[1], (SearchView) objArr[2]);
                return;
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{(String) objArr[0], Integer.valueOf(((String) objArr[0]).hashCode()), "com/orangelabs/simplefiltering/MyMshListener"}));
        }
    }

    public static /* synthetic */ Object access$super(MyMshListener myMshListener, String str, Object... objArr) {
        switch (str.hashCode()) {
            case -2128160755:
                return super.toString();
            case -2123832528:
                super.longPressDetected(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case -2112216780:
                super.seqMoveUpDetected();
                return null;
            case -2098109136:
                super.shortPressDetected(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case -1979419488:
                super.seqMoveLeftDetected();
                return null;
            case -1955431639:
                super.releaseLongPressDetected(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case -1886629702:
                super.regularCurvedDetected();
                return null;
            case -1850722025:
                super.endGestureDetected();
                return null;
            case -1768445099:
                super.seqMoveRightDetected();
                return null;
            case -1721983876:
                super.breakDragDetected();
                return null;
            case -1600833221:
                super.wait(((Number) objArr[0]).longValue(), ((Number) objArr[1]).intValue());
                return null;
            case -1558160243:
                super.releaseLongLongPressDetected(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case -1558136762:
                super.breakRotationDetected();
                return null;
            case -1554832987:
                super.finalize();
                return null;
            case -1553054636:
                super.longPressDoubleTapDetected();
                return null;
            case -1402780502:
                super.clockwiseRotationDetected(((Number) objArr[0]).intValue());
                return null;
            case -1399649718:
                super.seqEarlyMoveUpDetected(((Number) objArr[0]).intValue());
                return null;
            case -1268648990:
                super.flickDetected((FlickParameters) objArr[0]);
                return null;
            case -1166127280:
                super.notify();
                return null;
            case -1113334000:
                super.setListenerDrawer((ITrailDrawer) objArr[0]);
                return null;
            case -1021472056:
                super.wait(((Number) objArr[0]).longValue());
                return null;
            case -933328618:
                return new Boolean(super.wasATap());
            case -898801330:
                super.onListenerUnselected();
                return null;
            case -873845254:
                super.doubleTapPressDetected();
                return null;
            case -828201945:
                return super.getConfiguration();
            case -713633898:
                super.mshForceDefaultDragMode();
                return null;
            case -712101345:
                super.notifyAll();
                return null;
            case -633519813:
                super.doubleTapDetected();
                return null;
            case -554959440:
                super.symbolicDetected((SymbolParameters) objArr[0]);
                return null;
            case -489960537:
                super.symbolicPatternDetected();
                return null;
            case -101332148:
                super.pressDetected(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case -95282573:
                super.seqEarlyMoveRightDetected(((Number) objArr[0]).intValue());
                return null;
            case 39041548:
                return super.listenerDrawer();
            case 55547233:
                super.symbolsMayChange();
                return null;
            case 159256110:
                return super.dgilAndroid();
            case 193903588:
                super.pendingDoubleTapDetected();
                return null;
            case 201261558:
                return super.getClass();
            case 244142972:
                super.wait();
                return null;
            case 267248023:
                super.init();
                return null;
            case 420942682:
                super.mshCompleted((String) objArr[0], ((Boolean) objArr[1]).booleanValue());
                return null;
            case 461281123:
                super.breakFlickDetected((FlickParameters) objArr[0]);
                return null;
            case 529190644:
                super.onLifecycleEvent((LifecycleEvent) objArr[0]);
                return null;
            case 631235634:
                return super.dgilView();
            case 644966318:
                return super.context();
            case 829845644:
                super.anticlockwiseRotationDetected(((Number) objArr[0]).intValue());
                return null;
            case 933021986:
                super.seqCancelEarlyMove();
                return null;
            case 1154022135:
                super.saveAdaptation();
                return null;
            case 1173879029:
                super.onListenerSelected();
                return null;
            case 1184136876:
                super.tapDetected(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case 1284334114:
                super.endRotationDetected();
                return null;
            case 1328149159:
                super.mshRejected();
                return null;
            case 1403628309:
                return new Integer(super.hashCode());
            case 1478031537:
                super.breakSymbolicDetected((SymbolParameters) objArr[0]);
                return null;
            case 1523901456:
                super.setLoadModelsRequest(((Boolean) objArr[0]).booleanValue());
                return null;
            case 1584015519:
                super.fastStraightDetected();
                return null;
            case 1624875235:
                super.seqEarlyMoveDownDetected(((Number) objArr[0]).intValue());
                return null;
            case 1736479832:
                super.endDragDetected();
                return null;
            case 1745185044:
                super.longLongPressDetected(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case 1756358843:
                super.seqMoveDownDetected();
                return null;
            case 1779863966:
                super.seqEarlyMoveLeftDetected(((Number) objArr[0]).intValue());
                return null;
            case 1797561468:
                return super.dgil();
            case 1814730534:
                return new Boolean(super.equals(objArr[0]));
            case 1816163076:
                super.preSymbolicPatternDetected();
                return null;
            case 1988226300:
                super.startMoveDetected();
                return null;
            case 2025021518:
                return super.clone();
            case 2088361309:
                super.dragDetected();
                return null;
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{str, Integer.valueOf(str.hashCode()), "com/orangelabs/simplefiltering/MyMshListener"}));
        }
    }

    private void addOneLetter(String letter) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("addOneLetter.(Ljava/lang/String;)V", new Object[]{this, letter});
            return;
        }
        this.mshFilter.setQuery(String.valueOf(this.mshFilter.getQuery()) + letter, true);
    }

    private void displayAlternativeWeights() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("displayAlternativeWeights.()V", new Object[]{this});
            return;
        }
        byte[] mu = dgil().mshGetAdaptMu();
        Log.d("DGIL", "-- Adaptation displayed -- ");
        for (int i = 0; i < mu.length; i++) {
            if (mu[i] != 5) {
                Log.d("DGIL", "mu: " + String.valueOf(mu[i]));
            }
        }
    }

    private void eraseAll() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("eraseAll.()V", new Object[]{this});
            return;
        }
        this.mshFilter.setQuery("", true);
    }

    private void eraseOneLetter() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("eraseOneLetter.()V", new Object[]{this});
            return;
        }
        String query = String.valueOf(this.mshFilter.getQuery());
        if (query.length() <= 1) {
            this.mshFilter.setQuery("", true);
        } else if (query.length() > 1) {
            this.mshFilter.setQuery(query.substring(0, query.length() - 1), true);
        }
    }

    private void initDrawer() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("initDrawer.()V", new Object[]{this});
            return;
        }
        setListenerDrawer(new TrailDrawer(dgilView()));
        listenerDrawer().getTrailOptions().selectMarkerPen();
        MSHColors colors = getConfiguration().getColors();
        colors.setSymbolicColor(-16777216);
        colors.setSymbolicColorOk(-16777216);
        colors.setSymbolicColorNok(-65536);
        colors.setDragColor(COL_SYM_DRAG);
    }

    private void setDgilMode() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("setDgilMode.()V", new Object[]{this});
            return;
        }
        dgil().setMode(Mode.POINTING_ANDROID_VERTICAL);
    }

    private void updateDgilSettings() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("updateDgilSettings.()V", new Object[]{this});
            return;
        }
        Settings settings = this.dgil.getSettings();
        settings.restoreDefaults();
        settings.setMshEnabled(true);
        settings.setLongPressTimeoutMs(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        setDgilMode();
        settings.setThAcpFormFactor1(0.15f);
        settings.setThMshFormFactor(0.23f);
    }

    public void breakDragDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("breakDragDetected.()V", new Object[]{this});
            return;
        }
        super.breakDragDetected();
        this.gestureEpisode.add(new GestureEvent("BREAK_DRAG"));
    }

    public void breakFlickDetected(FlickParameters flickParameters) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("breakFlickDetected.(Lcom/orange/dgil/callback/FlickParameters;)V", new Object[]{this, flickParameters});
            return;
        }
        super.breakFlickDetected(flickParameters);
        this.gestureEpisode.add(new GestureEvent("BREAK_FLICK"));
    }

    public void breakSymbolicDetected(SymbolParameters symbolParameters) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("breakSymbolicDetected.(Lcom/orange/dgil/callback/SymbolParameters;)V", new Object[]{this, symbolParameters});
            return;
        }
        super.breakSymbolicDetected(symbolParameters);
        this.gestureEpisode.add(new GestureEvent("BREAK_SYMBOLIC"));
    }

    public void doubleTapDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("doubleTapDetected.()V", new Object[]{this});
            return;
        }
        super.doubleTapDetected();
        this.gestureEpisode.add(new GestureEvent("DOUBLE_TAP"));
    }

    public void doubleTapPressDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("doubleTapPressDetected.()V", new Object[]{this});
            return;
        }
        super.doubleTapPressDetected();
        this.gestureEpisode.add(new GestureEvent("DOUBLE_TAP_PRESS"));
    }

    public void dragDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("dragDetected.()V", new Object[]{this});
            return;
        }
        super.dragDetected();
        this.gestureEpisode.add(new GestureEvent("DRAG"));
    }

    public void endDragDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("endDragDetected.()V", new Object[]{this});
            return;
        }
        super.endDragDetected();
        this.gestureEpisode.add(new GestureEvent("END_DRAG"));
    }

    public void endGestureDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("endGestureDetected.()V", new Object[]{this});
            return;
        }
        super.endGestureDetected();
        String primitive = "END_GESTURE";
        Log.d("DGIL", primitive + " / " + this.flagAlternative);
        this.gestureEpisode.add(new GestureEvent(primitive));
        this.gestureEpisode.add(new GestureEvent("-------------"));
        this.episodeHistory.add(this.gestureEpisode);
    }

    public void fastStraightDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("fastStraightDetected.()V", new Object[]{this});
            return;
        }
        super.fastStraightDetected();
        this.gestureEpisode.add(new GestureEvent("FAST_STRAIGHT"));
    }

    public void flickDetected(FlickParameters flickParameters) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("flickDetected.(Lcom/orange/dgil/callback/FlickParameters;)V", new Object[]{this, flickParameters});
            return;
        }
        super.flickDetected(flickParameters);
        this.gestureEpisode.add(new GestureEvent("FLICK"));
    }

    public void longLongPressDetected(int downX, int downY) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("longLongPressDetected.(II)V", new Object[]{this, new Integer(downX), new Integer(downY)});
            return;
        }
        super.longLongPressDetected(downX, downY);
        this.gestureEpisode.add(new GestureEvent("LONG_LONG_PRESS"));
    }

    public void longPressDetected(int downX, int downY) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("longPressDetected.(II)V", new Object[]{this, new Integer(downX), new Integer(downY)});
            return;
        }
        super.longPressDetected(downX, downY);
        this.gestureEpisode.add(new GestureEvent("LONG_PRESS"));
    }

    public void longPressDoubleTapDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("longPressDoubleTapDetected.()V", new Object[]{this});
            return;
        }
        super.longPressDoubleTapDetected();
        this.gestureEpisode.add(new GestureEvent("LONG_PRESS_DOUBLE_TAP"));
    }

    public void mshCompleted(String symbol, boolean formFactorValid) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("mshCompleted.(Ljava/lang/String;Z)V", new Object[]{this, symbol, new Boolean(formFactorValid)});
            return;
        }
        boolean oneStrokeLetterFormFactorOk;
        String letter = null;
        Log.d("DGIL", "Msh completed:" + symbol + " / formFactorValid = " + formFactorValid);
        boolean flagCorrection = symbol.equals("erase");
        boolean isLetter = ALPHABETIC_SET.contains(symbol);
        if (this.flagSymbolicPattern && this.flagOneStroke && isLetter) {
            oneStrokeLetterFormFactorOk = true;
        } else {
            oneStrokeLetterFormFactorOk = false;
        }
        boolean multiStrokeLetterFormFactorOk;
        if (formFactorValid && !this.flagOneStroke && isLetter) {
            multiStrokeLetterFormFactorOk = true;
        } else {
            multiStrokeLetterFormFactorOk = false;
        }
        String alternative = dgil().mshAdaptUpdateCurrAlt(flagCorrection);
        this.flagAlternative = false;
        if (flagCorrection) {
            eraseOneLetter();
            if (alternative != null && ALPHABETIC_SET.contains(alternative)) {
                letter = alternative;
                Log.d("DGIL", "Alternative: " + alternative);
                this.flagAlternative = true;
                this.mem_alternative = alternative;
            }
        } else if (oneStrokeLetterFormFactorOk || multiStrokeLetterFormFactorOk) {
            letter = symbol;
        } else if (symbol.equals("clean")) {
            eraseAll();
        } else {
            letter = specialLetters(symbol);
        }
        if (letter != null) {
            addOneLetter(letter);
        } else {
            listenerDrawer().clear();
        }
        setDgilMode();
        this.flagSymbolicPattern = false;
        this.flagOneStroke = false;
    }

    public void mshRejected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("mshRejected.()V", new Object[]{this});
            return;
        }
        Log.d("DGIL", "Msh rejected:");
        setDgilMode();
        this.flagSymbolicPattern = false;
        this.flagAlternative = false;
    }

    public void onListenerSelected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("onListenerSelected.()V", new Object[]{this});
            return;
        }
        super.onListenerSelected();
        Log.d("DGIL", "on listener from MSH mode");
    }

    public void pendingDoubleTapDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("pendingDoubleTapDetected.()V", new Object[]{this});
            return;
        }
        super.pendingDoubleTapDetected();
        this.gestureEpisode.add(new GestureEvent("PENDIND_DOUBLE_TAP"));
    }

    public void preSymbolicPatternDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("preSymbolicPatternDetected.()V", new Object[]{this});
            return;
        }
        super.preSymbolicPatternDetected();
    }

    public void pressDetected(int x, int y) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("pressDetected.(II)V", new Object[]{this, new Integer(x), new Integer(y)});
            return;
        }
        super.pressDetected(x, y);
        this.gestureEpisode = new GestureEpisode();
        this.gestureEpisode.add(new GestureEvent("PRESS"));
        if (this.flagOneStroke) {
            this.flagOneStroke = false;
        } else {
            this.flagOneStroke = true;
        }
    }

    public void regularCurvedDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("regularCurvedDetected.()V", new Object[]{this});
            return;
        }
        super.regularCurvedDetected();
        this.gestureEpisode.add(new GestureEvent("REGULAR_CURVED"));
    }

    public void releaseLongLongPressDetected(int downX, int downY) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("releaseLongLongPressDetected.(II)V", new Object[]{this, new Integer(downX), new Integer(downY)});
            return;
        }
        super.releaseLongLongPressDetected(downX, downY);
        this.gestureEpisode.add(new GestureEvent("RELEASE_LONG_LONG_PRESS"));
    }

    public void releaseLongPressDetected(int downX, int downY) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("releaseLongPressDetected.(II)V", new Object[]{this, new Integer(downX), new Integer(downY)});
            return;
        }
        super.releaseLongPressDetected(downX, downY);
        this.gestureEpisode.add(new GestureEvent("RELEASE_LONG_PRESS"));
    }

    public void shortPressDetected(int downX, int downY) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("shortPressDetected.(II)V", new Object[]{this, new Integer(downX), new Integer(downY)});
            return;
        }
        super.shortPressDetected(downX, downY);
        this.gestureEpisode.add(new GestureEvent("SHORT_PRESS"));
    }

    public String specialLetters(String symbol) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            return (String) incrementalChange.access$dispatch("specialLetters.(Ljava/lang/String;)Ljava/lang/String;", new Object[]{this, symbol});
        }
        String letter = null;
        if (symbol.equals("l_prefix_itf") && this.flagSymbolicPattern) {
            letter = "l";
        } else if (symbol.equals("prefix_it") && this.flagSymbolicPattern) {
            letter = "l";
        } else if (symbol.equals("prefix_j") && this.flagSymbolicPattern) {
            letter = "j";
        } else if (symbol.equals("i")) {
            letter = "i";
        } else if (symbol.equals("j")) {
            letter = "j";
        }
        return letter;
    }

    public void startMoveDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("startMoveDetected.()V", new Object[]{this});
            return;
        }
        super.startMoveDetected();
        this.gestureEpisode.add(new GestureEvent("START_MOVE"));
    }

    public void symbolicDetected(SymbolParameters symbolParameters) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("symbolicDetected.(Lcom/orange/dgil/callback/SymbolParameters;)V", new Object[]{this, symbolParameters});
            return;
        }
        super.symbolicDetected(symbolParameters);
        String primitive = "SYMBOLIC (" + symbolParameters.symbol1() + ")";
        Log.d("DGIL", primitive);
        this.gestureEpisode.add(new GestureEvent(primitive));
    }

    public void symbolicPatternDetected() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("symbolicPatternDetected.()V", new Object[]{this});
            return;
        }
        super.symbolicPatternDetected();
        this.flagSymbolicPattern = true;
        this.gestureEpisode.add(new GestureEvent("SYMBOLIC_PATTERN"));
    }

    public void tapDetected(int downX, int downY) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("tapDetected.(II)V", new Object[]{this, new Integer(downX), new Integer(downY)});
            return;
        }
        super.tapDetected(downX, downY);
        listenerDrawer().hide();
        this.gestureEpisode.add(new GestureEvent("TAP"));
        if (this.flagAlternative) {
            Log.d("DGIL", "Commit alternative when it follows a tap! ");
            dgil().mshAdaptValidateAlt(this.mem_alternative);
            this.flagAlternative = false;
        }
    }

    public MyMshListener(Dgil dgil, SearchView filter) throws IOException {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            Object[] objArr = new Object[]{objArr, dgil, filter};
            dgil = objArr[1];
            filter = objArr[2];
            this((Object[]) incrementalChange.access$dispatch("init$args.([Ljava/lang/Object;Lcom/orange/dgil/Dgil;Landroid/widget/SearchView;)Ljava/lang/Object;", objArr), null);
        } else {
            super(new AndroidListenerParams(dgil, MODELS_ASSET_FILENAME, ADAPTATION_FILENAME));
        }
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("init$body.(Lcom/orangelabs/simplefiltering/MyMshListener;Lcom/orange/dgil/Dgil;Landroid/widget/SearchView;)V", new Object[]{this, dgil, filter});
            return;
        }
        this.flagOneStroke = false;
        this.flagSymbolicPattern = false;
        this.flagAlternative = false;
        this.mem_alternative = "";
        initDrawer();
        updateDgilSettings();
        this.episodeHistory = new EpisodeHistory();
        this.mshFilter = filter;
        displayAlternativeWeights();
    }
}
