package com.davidcryer.simpleactivitiessample;

import android.support.annotation.ColorRes;

class ColorCycler {
    private int counter = 0;
    private @ColorRes int[] colors = {R.color.red, R.color.green, R.color.blue};

    @ColorRes int next() {
        return colors[counter++ % 3];
    }
}
