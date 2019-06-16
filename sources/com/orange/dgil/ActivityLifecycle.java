package com.orange.dgil;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.orange.android.activitylifecycle.ApplicationLifecycle;
import com.orange.android.activitylifecycle.Lifecycle;

class ActivityLifecycle {
    ActivityLifecycle() {
    }

    static void register(Context context, Lifecycle lifecycle) {
        if (context instanceof Activity) {
            ApplicationLifecycle.register(lifecycle, context);
        } else {
            Log.w(ActivityLifecycle.class.getSimpleName(), "We can not register for activity lifecycle events since the provided view context is not an activity context");
        }
    }
}
