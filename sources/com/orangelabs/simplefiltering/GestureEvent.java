package com.orangelabs.simplefiltering;

import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;

public class GestureEvent {
    public static volatile transient /* synthetic */ IncrementalChange $change;
    private String primitive;

    GestureEvent(Object[] objArr, InstantReloadException instantReloadException) {
        switch (((String) objArr[0]).hashCode()) {
            case -1968665286:
                return;
            case 1720538726:
                this((String) objArr[1]);
                return;
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{(String) objArr[0], Integer.valueOf(((String) objArr[0]).hashCode()), "com/orangelabs/simplefiltering/GestureEvent"}));
        }
    }

    public static /* synthetic */ Object access$super(GestureEvent gestureEvent, String str, Object... objArr) {
        switch (str.hashCode()) {
            case -2128160755:
                return super.toString();
            case -1600833221:
                super.wait(((Number) objArr[0]).longValue(), ((Number) objArr[1]).intValue());
                return null;
            case -1554832987:
                super.finalize();
                return null;
            case -1166127280:
                super.notify();
                return null;
            case -1021472056:
                super.wait(((Number) objArr[0]).longValue());
                return null;
            case -712101345:
                super.notifyAll();
                return null;
            case 201261558:
                return super.getClass();
            case 244142972:
                super.wait();
                return null;
            case 1403628309:
                return new Integer(super.hashCode());
            case 1814730534:
                return new Boolean(super.equals(objArr[0]));
            case 2025021518:
                return super.clone();
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{str, Integer.valueOf(str.hashCode()), "com/orangelabs/simplefiltering/GestureEvent"}));
        }
    }

    public String getPrimitive() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange == null) {
            return this.primitive;
        }
        return (String) incrementalChange.access$dispatch("getPrimitive.()Ljava/lang/String;", new Object[]{this});
    }

    public GestureEvent(String primitive) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            Object[] objArr = new Object[]{objArr, primitive};
            primitive = objArr[1];
            this((Object[]) incrementalChange.access$dispatch("init$args.([Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", objArr), null);
        }
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("init$body.(Lcom/orangelabs/simplefiltering/GestureEvent;Ljava/lang/String;)V", new Object[]{this, primitive});
            return;
        }
        this.primitive = "";
        this.primitive = primitive;
    }
}
