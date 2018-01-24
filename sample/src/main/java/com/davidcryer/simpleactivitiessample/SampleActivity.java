package com.davidcryer.simpleactivitiessample;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.davidcryer.simpleactivities.SimpleAppBarActivity;

public class SampleActivity extends SimpleAppBarActivity {
    private final ColorCycler colorCycler = new ColorCycler();

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {

    }

    @Override
    protected void addInitialFragment() {
        add(null, this::sampleFragment);
    }

    void addSampleFragment() {
        add(null, this::sampleFragment, anims(R.anim.enter_right, 0), anims(0, R.anim.exit_right));
    }

    void replaceSampleFragment() {
        add(null, this::sampleFragment, anims(R.anim.enter_right, R.anim.exit_left), anims(R.anim.enter_left, R.anim.exit_right));
    }

    SampleFragment sampleFragment() {
        return SampleFragment.newInstance(colorCycler.next());
    }
}
