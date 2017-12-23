package com.davidcryer.simpleactivities;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

public abstract class SimpleAppBarActivity extends SimpleFragmentManagerUtilsActivity {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        setupToolbar();
        addInitialFragmentIfNone();
    }

    private void setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            setupActionBar(actionBar);
        }
    }

    protected abstract void setupActionBar(@NonNull final ActionBar actionBar);

    private void addInitialFragmentIfNone() {
        if (!hasFragment(getContentFragmentViewContainer())) {
            addInitialFragment();
        }
    }

    protected abstract void addInitialFragment();

    boolean hasFragment() {
        return hasFragment(getContentFragmentViewContainer());
    }

    Fragment fragment() {
        return fragment(getContentFragmentViewContainer());
    }

    protected void add(String tag, FragmentProvider fragmentProvider) {
        add(getContentFragmentViewContainer(), tag, fragmentProvider);
    }

    protected void replace(String tag, FragmentProvider fragmentProvider) {
        replace(getContentFragmentViewContainer(), tag, fragmentProvider);
    }

    @IdRes
    protected int getContentFragmentViewContainer() {
        return R.id.content;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (hasMoreThanOneFragmentOnBackStack()) {
            super.onBackPressed();
        } else {
            finish();
        }
    }
}
