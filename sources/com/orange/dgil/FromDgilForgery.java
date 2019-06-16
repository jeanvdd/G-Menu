package com.orange.dgil;

import android.view.MotionEvent;

class FromDgilForgery {

    enum ForgeEvents {
        PRESS {
            void forgeAndDispatch(ForgeParameters params) {
                MotionEvent event = ForgeEvents.getEvent(0, params);
                params.getFromDgil().dispatchPressToDgilView(event);
                event.recycle();
            }
        },
        MOVE {
            void forgeAndDispatch(ForgeParameters params) {
                ForgeEvents.dispatchEvent(2, params);
            }
        },
        RELEASE_UP {
            void forgeAndDispatch(ForgeParameters params) {
                ForgeEvents.dispatchEvent(1, params);
            }
        },
        RELEASE_CANCEL {
            void forgeAndDispatch(ForgeParameters params) {
                ForgeEvents.dispatchEvent(3, params);
            }
        };

        abstract void forgeAndDispatch(ForgeParameters forgeParameters);

        private static MotionEvent getEvent(int motionEventAction, ForgeParameters params) {
            params.getEventBuilder().setAction(motionEventAction);
            return params.getEventBuilder().obtainMotionEvent(params.getX(), params.getY(), params.getT());
        }

        private static void dispatchEvent(int motionEventAction, ForgeParameters params) {
            MotionEvent event = getEvent(motionEventAction, params);
            params.getFromDgil().dispatchEventToDgilView(event);
            event.recycle();
        }
    }

    FromDgilForgery() {
    }
}
