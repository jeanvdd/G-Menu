package org.codehaus.jackson.map.util;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

public class RootNameLookup {
    protected LRUMap<ClassKey, SerializedString> _rootNames;

    public SerializedString findRootName(JavaType rootType, MapperConfig<?> config) {
        return findRootName(rootType.getRawClass(), (MapperConfig) config);
    }

    public synchronized SerializedString findRootName(Class<?> rootType, MapperConfig<?> config) {
        SerializedString name;
        ClassKey key = new ClassKey(rootType);
        if (this._rootNames == null) {
            this._rootNames = new LRUMap(20, Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        } else {
            name = (SerializedString) this._rootNames.get(key);
            if (name != null) {
            }
        }
        String nameStr = config.getAnnotationIntrospector().findRootName(((BasicBeanDescription) config.introspectClassAnnotations((Class) rootType)).getClassInfo());
        if (nameStr == null) {
            nameStr = rootType.getSimpleName();
        }
        name = new SerializedString(nameStr);
        this._rootNames.put(key, name);
        return name;
    }
}
