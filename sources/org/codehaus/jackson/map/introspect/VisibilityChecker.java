package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;

public interface VisibilityChecker<T extends VisibilityChecker<T>> {

    /* renamed from: org.codehaus.jackson.map.introspect.VisibilityChecker$1 */
    static /* synthetic */ class C00301 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$annotate$JsonMethod = new int[JsonMethod.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.GETTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.SETTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.CREATOR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.FIELD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.IS_GETTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.ALL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    @JsonAutoDetect(creatorVisibility = Visibility.ANY, fieldVisibility = Visibility.PUBLIC_ONLY, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY, setterVisibility = Visibility.ANY)
    public static class Std implements VisibilityChecker<Std> {
        protected static final Std DEFAULT = new Std((JsonAutoDetect) Std.class.getAnnotation(JsonAutoDetect.class));
        protected final Visibility _creatorMinLevel;
        protected final Visibility _fieldMinLevel;
        protected final Visibility _getterMinLevel;
        protected final Visibility _isGetterMinLevel;
        protected final Visibility _setterMinLevel;

        public static Std defaultInstance() {
            return DEFAULT;
        }

        public Std(JsonAutoDetect ann) {
            JsonMethod[] incl = ann.value();
            this._getterMinLevel = hasMethod(incl, JsonMethod.GETTER) ? ann.getterVisibility() : Visibility.NONE;
            this._isGetterMinLevel = hasMethod(incl, JsonMethod.IS_GETTER) ? ann.isGetterVisibility() : Visibility.NONE;
            this._setterMinLevel = hasMethod(incl, JsonMethod.SETTER) ? ann.setterVisibility() : Visibility.NONE;
            this._creatorMinLevel = hasMethod(incl, JsonMethod.CREATOR) ? ann.creatorVisibility() : Visibility.NONE;
            this._fieldMinLevel = hasMethod(incl, JsonMethod.FIELD) ? ann.fieldVisibility() : Visibility.NONE;
        }

        public Std(Visibility getter, Visibility isGetter, Visibility setter, Visibility creator, Visibility field) {
            this._getterMinLevel = getter;
            this._isGetterMinLevel = isGetter;
            this._setterMinLevel = setter;
            this._creatorMinLevel = creator;
            this._fieldMinLevel = field;
        }

        public Std(Visibility v) {
            if (v == Visibility.DEFAULT) {
                this._getterMinLevel = DEFAULT._getterMinLevel;
                this._isGetterMinLevel = DEFAULT._isGetterMinLevel;
                this._setterMinLevel = DEFAULT._setterMinLevel;
                this._creatorMinLevel = DEFAULT._creatorMinLevel;
                this._fieldMinLevel = DEFAULT._fieldMinLevel;
                return;
            }
            this._getterMinLevel = v;
            this._isGetterMinLevel = v;
            this._setterMinLevel = v;
            this._creatorMinLevel = v;
            this._fieldMinLevel = v;
        }

        public Std with(JsonAutoDetect ann) {
            if (ann == null) {
                return this;
            }
            Std curr = this;
            JsonMethod[] incl = ann.value();
            return withGetterVisibility(hasMethod(incl, JsonMethod.GETTER) ? ann.getterVisibility() : Visibility.NONE).withIsGetterVisibility(hasMethod(incl, JsonMethod.IS_GETTER) ? ann.isGetterVisibility() : Visibility.NONE).withSetterVisibility(hasMethod(incl, JsonMethod.SETTER) ? ann.setterVisibility() : Visibility.NONE).withCreatorVisibility(hasMethod(incl, JsonMethod.CREATOR) ? ann.creatorVisibility() : Visibility.NONE).withFieldVisibility(hasMethod(incl, JsonMethod.FIELD) ? ann.fieldVisibility() : Visibility.NONE);
        }

        public Std with(Visibility v) {
            if (v == Visibility.DEFAULT) {
                return DEFAULT;
            }
            return new Std(v);
        }

        public Std withVisibility(JsonMethod method, Visibility v) {
            switch (C00301.$SwitchMap$org$codehaus$jackson$annotate$JsonMethod[method.ordinal()]) {
                case 1:
                    return withGetterVisibility(v);
                case 2:
                    return withSetterVisibility(v);
                case 3:
                    return withCreatorVisibility(v);
                case 4:
                    return withFieldVisibility(v);
                case 5:
                    return withIsGetterVisibility(v);
                case 6:
                    return with(v);
                default:
                    return this;
            }
        }

        public Std withGetterVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._getterMinLevel;
            }
            if (this._getterMinLevel == v) {
                return this;
            }
            return new Std(v, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        public Std withIsGetterVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._isGetterMinLevel;
            }
            if (this._isGetterMinLevel == v) {
                return this;
            }
            return new Std(this._getterMinLevel, v, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        public Std withSetterVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._setterMinLevel;
            }
            if (this._setterMinLevel == v) {
                return this;
            }
            return new Std(this._getterMinLevel, this._isGetterMinLevel, v, this._creatorMinLevel, this._fieldMinLevel);
        }

        public Std withCreatorVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._creatorMinLevel;
            }
            if (this._creatorMinLevel == v) {
                return this;
            }
            return new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, v, this._fieldMinLevel);
        }

        public Std withFieldVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._fieldMinLevel;
            }
            return this._fieldMinLevel == v ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, v);
        }

        public boolean isCreatorVisible(Member m) {
            return this._creatorMinLevel.isVisible(m);
        }

        public boolean isCreatorVisible(AnnotatedMember m) {
            return isCreatorVisible(m.getMember());
        }

        public boolean isFieldVisible(Field f) {
            return this._fieldMinLevel.isVisible(f);
        }

        public boolean isFieldVisible(AnnotatedField f) {
            return isFieldVisible(f.getAnnotated());
        }

        public boolean isGetterVisible(Method m) {
            return this._getterMinLevel.isVisible(m);
        }

        public boolean isGetterVisible(AnnotatedMethod m) {
            return isGetterVisible(m.getAnnotated());
        }

        public boolean isIsGetterVisible(Method m) {
            return this._isGetterMinLevel.isVisible(m);
        }

        public boolean isIsGetterVisible(AnnotatedMethod m) {
            return isIsGetterVisible(m.getAnnotated());
        }

        public boolean isSetterVisible(Method m) {
            return this._setterMinLevel.isVisible(m);
        }

        public boolean isSetterVisible(AnnotatedMethod m) {
            return isSetterVisible(m.getAnnotated());
        }

        private static boolean hasMethod(JsonMethod[] methods, JsonMethod method) {
            for (JsonMethod curr : methods) {
                if (curr == method || curr == JsonMethod.ALL) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "[Visibility:" + " getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
        }
    }

    boolean isCreatorVisible(Member member);

    boolean isCreatorVisible(AnnotatedMember annotatedMember);

    boolean isFieldVisible(Field field);

    boolean isFieldVisible(AnnotatedField annotatedField);

    boolean isGetterVisible(Method method);

    boolean isGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isIsGetterVisible(Method method);

    boolean isIsGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isSetterVisible(Method method);

    boolean isSetterVisible(AnnotatedMethod annotatedMethod);

    T with(Visibility visibility);

    T with(JsonAutoDetect jsonAutoDetect);

    T withCreatorVisibility(Visibility visibility);

    T withFieldVisibility(Visibility visibility);

    T withGetterVisibility(Visibility visibility);

    T withIsGetterVisibility(Visibility visibility);

    T withSetterVisibility(Visibility visibility);

    T withVisibility(JsonMethod jsonMethod, Visibility visibility);
}
