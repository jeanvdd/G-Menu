package com.orangelabs.simplefiltering;

import android.widget.ArrayAdapter;
import android.widget.SearchView.OnQueryTextListener;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;

public class MyFilterListener implements OnQueryTextListener {
    public static volatile transient /* synthetic */ IncrementalChange $change;
    private ArrayAdapter adapter;

    MyFilterListener(Object[] objArr, InstantReloadException instantReloadException) {
        switch (((String) objArr[0]).hashCode()) {
            case -1968665286:
                return;
            case 1099197949:
                this((ArrayAdapter) objArr[1]);
                return;
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{(String) objArr[0], Integer.valueOf(((String) objArr[0]).hashCode()), "com/orangelabs/simplefiltering/MyFilterListener"}));
        }
    }

    public static /* synthetic */ Object access$super(MyFilterListener myFilterListener, String str, Object... objArr) {
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
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{str, Integer.valueOf(str.hashCode()), "com/orangelabs/simplefiltering/MyFilterListener"}));
        }
    }

    public boolean onQueryTextChange(String query) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            return ((Boolean) incrementalChange.access$dispatch("onQueryTextChange.(Ljava/lang/String;)Z", new Object[]{this, query})).booleanValue();
        }
        this.adapter.getFilter().filter(query);
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange == null) {
            return false;
        }
        return ((Boolean) incrementalChange.access$dispatch("onQueryTextSubmit.(Ljava/lang/String;)Z", new Object[]{this, query})).booleanValue();
    }

    public MyFilterListener(ArrayAdapter aa) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            Object[] objArr = new Object[]{objArr, aa};
            aa = objArr[1];
            this((Object[]) incrementalChange.access$dispatch("init$args.([Ljava/lang/Object;Landroid/widget/ArrayAdapter;)Ljava/lang/Object;", objArr), null);
        }
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("init$body.(Lcom/orangelabs/simplefiltering/MyFilterListener;Landroid/widget/ArrayAdapter;)V", new Object[]{this, aa});
            return;
        }
        this.adapter = aa;
    }
}
