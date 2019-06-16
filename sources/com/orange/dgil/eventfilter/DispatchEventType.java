package com.orange.dgil.eventfilter;

import com.orange.dgil.event.EventProperties;

public enum DispatchEventType {
    TO_DGIL {
        public void dispatchToNextLayer(EventProperties ev) {
            ev.getForward().eventToDgilAnalysis(ev.getEvent(), ev.getDgilPointerIndex());
        }
    },
    DIRECTLY_TO_CHILD_VIEWS_FIRST_MULTITOUCH {
        public void dispatchToNextLayer(EventProperties ev) {
            ev.getForward().firstMultitouchEvent(ev.getEvent());
        }
    },
    DIRECTLY_TO_CHILD_VIEWS {
        public void dispatchToNextLayer(EventProperties ev) {
            ev.getForward().eventToChildViews(ev.getEvent());
        }
    },
    NO_DISPATCH {
        public void dispatchToNextLayer(EventProperties ev) {
        }
    };

    public abstract void dispatchToNextLayer(EventProperties eventProperties);
}
