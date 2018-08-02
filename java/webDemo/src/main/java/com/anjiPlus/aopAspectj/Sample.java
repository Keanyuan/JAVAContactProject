package com.anjiPlus.aopAspectj;

import java.util.Collection;

public interface Sample<T> {
    void sampleGenericMethod(T param);
    void sampleGenericCollectMethod(Collection<T> param);
}
