package com.davidcryer.simpleactivities;

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
        if (!hasFragment(tag)) {
            addFragment(view, fragmentProvider.fragment(), tag);
        }
    }

    protected void replace(@IdRes final int view, final String tag, final FragmentProvider fragmentProvider) {
        if (!hasFragment(tag)) {
            replaceFragment(view, fragmentProvider.fragment(), tag);
        }
    }

    private void addFragment(@IdRes final int view, final Fragment fragment, final String tag) {
        addOrReplaceFragment(transaction -> transaction.add(view, fragment, tag));
    }

    private void replaceFragment(@IdRes final int view, final Fragment fragment, final String tag) {
        addOrReplaceFragment(transaction -> transaction.replace(view, fragment, tag));
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

    protected interface FragmentProvider {
        Fragment fragment();
    }

    private interface AddOrReplaceFragmentAction {
        void performAddOrReplace(FragmentTransaction transaction);
    }
}
