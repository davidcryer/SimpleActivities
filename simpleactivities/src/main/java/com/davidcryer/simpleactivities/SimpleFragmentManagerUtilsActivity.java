package com.davidcryer.simpleactivities;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

abstract class SimpleFragmentManagerUtilsActivity extends AppCompatActivity {

    boolean hasFragment(@IdRes final int view) {
        return fragment(view) != null;
    }

    Fragment fragment(final int view) {
        return getSupportFragmentManager().findFragmentById(view);
    }

    boolean hasFragment(final String tag) {
        return fragment(tag) != null;
    }

    Fragment fragment(final String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    protected void add(@IdRes final int view, final String tag, final FragmentProvider fragmentProvider) {
        this.add(view, tag, fragmentProvider, noAnims());
    }

    protected void add(@IdRes final int view, final String tag, final FragmentProvider fragmentProvider, final FragmentAnimations initialAnims) {
        this.add(view, tag, fragmentProvider, initialAnims, noAnims());
    }

    protected void add(@IdRes final int view, final String tag, final FragmentProvider fragmentProvider, final FragmentAnimations initialAnims, final FragmentAnimations popAnims) {
        if (!hasFragment(tag)) {
            addFragment(view, fragmentProvider.fragment(), tag, initialAnims, popAnims);
        }
    }

    protected void replace(@IdRes final int view, final String tag, final FragmentProvider fragmentProvider) {
        this.replace(view, tag, fragmentProvider, noAnims());
    }

    protected void replace(@IdRes final int view, final String tag, final FragmentProvider fragmentProvider, final FragmentAnimations initialAnims) {
        this.replace(view, tag, fragmentProvider, initialAnims, noAnims());
    }

    protected void replace(@IdRes final int view, final String tag, final FragmentProvider fragmentProvider, final FragmentAnimations initialAnims, final FragmentAnimations popAnims) {
        if (!hasFragment(tag)) {
            replaceFragment(view, fragmentProvider.fragment(), tag, initialAnims, popAnims);
        }
    }

    private void addFragment(@IdRes final int view, final Fragment fragment, final String tag, final FragmentAnimations initialAnims, final FragmentAnimations popAnims) {
        addOrReplaceFragment(transaction -> {
            transaction.add(view, fragment, tag);
            transaction.setCustomAnimations(initialAnims.entry, initialAnims.exit, popAnims.entry, popAnims.exit);
        });
    }

    private void replaceFragment(@IdRes final int view, final Fragment fragment, final String tag, final FragmentAnimations initialAnims, final FragmentAnimations popAnims) {
        addOrReplaceFragment(transaction -> {
            transaction.replace(view, fragment, tag);
            transaction.setCustomAnimations(initialAnims.entry, initialAnims.exit, popAnims.entry, popAnims.exit);
        });
    }

    private void addOrReplaceFragment(final AddOrReplaceFragmentAction action) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        action.performAddOrReplace(transaction);
        transaction.addToBackStack(null).commit();
    }

    void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    boolean hasMoreThanOneFragmentOnBackStack() {
        return getSupportFragmentManager().getBackStackEntryCount() > 1;
    }

    protected FragmentAnimations anims(@AnimatorRes @AnimRes final int entry, @AnimatorRes @AnimRes final int exit) {
        return new FragmentAnimations(entry, exit);
    }

    FragmentAnimations noAnims() {
        return new FragmentAnimations(0, 0);
    }

    protected interface FragmentProvider {
        Fragment fragment();
    }

    private interface AddOrReplaceFragmentAction {
        void performAddOrReplace(FragmentTransaction transaction);
    }
}
