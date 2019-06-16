package com.orange.dgil.conf;

public class Metaclasses {

    public enum Metaclass {
        PRESS,
        SHORT_PRESS,
        LONG_PRESS,
        LONG_LONG_PRESS,
        RELEASE_LONG_PRESS,
        RELEASE_LONG_LONG_PRESS,
        TAP,
        PENDING_DOUBLETAP,
        DOUBLETAP_PRESS,
        DOUBLETAP_RELEASE,
        LONG_PRESS_DOUBLETAP,
        START_MOVE,
        DRAG,
        FAST_STRAIGHT,
        SYMBOLIC_PATTERN,
        SYMBOLIC,
        FLICK,
        REGULAR_CURVED,
        CLOCKWISE_ROTATION,
        ANTICLOCKWISE_ROTATION,
        BREAK_DRAG,
        BREAK_SYMBOLIC,
        BREAK_FLICK,
        BREAK_ROTATION,
        END_DRAG,
        END_ROTATION,
        SEQ_MOVE_UP,
        SEQ_MOVE_DOWN,
        SEQ_MOVE_LEFT,
        SEQ_MOVE_RIGHT,
        SEQ_EARLY_MOVE_UP,
        SEQ_EARLY_MOVE_DOWN,
        SEQ_EARLY_MOVE_LEFT,
        SEQ_EARLY_MOVE_RIGHT,
        SEQ_CANCEL_EARLY_MOVE,
        END_GESTURE
    }
}
