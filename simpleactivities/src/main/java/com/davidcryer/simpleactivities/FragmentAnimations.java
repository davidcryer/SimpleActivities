package com.davidcryer.simpleactivities;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;

class FragmentAnimations {
    @AnimatorRes @AnimRes final int entry;
    @AnimatorRes @AnimRes final int exit;

    FragmentAnimations(int entry, int exit) {
        this.entry = entry;
        this.exit = exit;
    }
}
