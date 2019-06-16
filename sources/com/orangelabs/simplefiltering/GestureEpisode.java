package com.orangelabs.simplefiltering;

import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;
import java.util.ArrayList;
import java.util.Collection;

public class GestureEpisode extends ArrayList<GestureEvent> {
    public static volatile transient /* synthetic */ IncrementalChange $change;

    GestureEpisode(Object[] objArr, InstantReloadException instantReloadException) {
        switch (((String) objArr[0]).hashCode()) {
            case -1854694082:
                return;
            case -1660912251:
                super(((Number) objArr[1]).intValue());
                return;
            case -511850385:
                super((Collection) objArr[1]);
                return;
            case 5217213:
                this();
                return;
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{(String) objArr[0], Integer.valueOf(((String) objArr[0]).hashCode()), "com/orangelabs/simplefiltering/GestureEpisode"}));
        }
    }

    public static /* synthetic */ Object access$super(GestureEpisode gestureEpisode, String str, Object... objArr) {
        switch (str.hashCode()) {
            case -2146825139:
                return super.toArray((Object[]) objArr[0]);
            case -2128160755:
                return super.toString();
            case -1892396190:
                return super.iterator();
            case -1738987735:
                return new Integer(super.lastIndexOf(objArr[0]));
            case -1600833221:
                super.wait(((Number) objArr[0]).longValue(), ((Number) objArr[1]).intValue());
                return null;
            case -1554832987:
                super.finalize();
                return null;
            case -1530880303:
                return new Boolean(super.addAll(((Number) objArr[0]).intValue(), (Collection) objArr[1]));
            case -1499470522:
                return super.toArray();
            case -1335065210:
                return new Boolean(super.contains(objArr[0]));
            case -1306005412:
                return super.set(((Number) objArr[0]).intValue(), objArr[1]);
            case -1272099756:
                super.clear();
                return null;
            case -1166127280:
                super.notify();
                return null;
            case -1151564244:
                return super.get(((Number) objArr[0]).intValue());
            case -1021472056:
                super.wait(((Number) objArr[0]).longValue());
                return null;
            case -1004646043:
                super.trimToSize();
                return null;
            case -969187752:
                return new Boolean(super.retainAll((Collection) objArr[0]));
            case -776414407:
                return new Boolean(super.removeAll((Collection) objArr[0]));
            case -724682940:
                super.ensureCapacity(((Number) objArr[0]).intValue());
                return null;
            case -712101345:
                super.notifyAll();
                return null;
            case -475350822:
                return super.remove(((Number) objArr[0]).intValue());
            case -332530133:
                return new Boolean(super.remove(objArr[0]));
            case -242754226:
                return new Boolean(super.isEmpty());
            case 117664640:
                super.removeRange(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
                return null;
            case 195222152:
                return new Boolean(super.add(objArr[0]));
            case 201261558:
                return super.getClass();
            case 244142972:
                super.wait();
                return null;
            case 330270722:
                return super.subList(((Number) objArr[0]).intValue(), ((Number) objArr[1]).intValue());
            case 368150985:
                return super.listIterator(((Number) objArr[0]).intValue());
            case 393176958:
                return new Boolean(super.containsAll((Collection) objArr[0]));
            case 497758839:
                super.add(((Number) objArr[0]).intValue(), objArr[1]);
                return null;
            case 845773819:
                return new Integer(super.size());
            case 1403628309:
                return new Integer(super.hashCode());
            case 1481950310:
                return super.listIterator();
            case 1580890655:
                return new Integer(super.indexOf(objArr[0]));
            case 1730268220:
                return new Boolean(super.addAll((Collection) objArr[0]));
            case 1814730534:
                return new Boolean(super.equals(objArr[0]));
            case 2025021518:
                return super.clone();
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{str, Integer.valueOf(str.hashCode()), "com/orangelabs/simplefiltering/GestureEpisode"}));
        }
    }

    public GestureEpisode() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            this((Object[]) incrementalChange.access$dispatch("init$args.([Ljava/lang/Object;)Ljava/lang/Object;", new Object[]{r2}), null);
        }
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("init$body.(Lcom/orangelabs/simplefiltering/GestureEpisode;)V", new Object[]{this});
        }
    }
}
