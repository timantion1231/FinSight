package com.finsight;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class Randomizer {
    public static <T> T randomize (Class<T> clas){
        EnhancedRandom rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        return rnd.nextObject(clas);
    }
}
