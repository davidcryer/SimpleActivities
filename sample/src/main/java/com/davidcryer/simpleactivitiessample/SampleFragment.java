package com.davidcryer.simpleactivitiessample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SampleFragment extends Fragment {
    private final static String ARGS_BACKGROUND = "background";
    private Unbinder unbinder;
    private SampleActivity sampleActivity;

    static SampleFragment newInstance(final @ColorRes int background) {
        final SampleFragment fragment = new SampleFragment();
        final Bundle args = new Bundle();
        args.putInt(ARGS_BACKGROUND, background);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sampleActivity = (SampleActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sampleActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setBackground(view);
    }

    private void setBackground(final View view) {
        final Bundle args = getArguments();
        if (args != null) {
            view.setBackgroundResource(args.getInt(ARGS_BACKGROUND));
        }
    }

    @OnClick(R.id.add_fragment)
    void addFragment() {
        sampleActivity.addSampleFragment();
    }

    @OnClick(R.id.replace_fragment)
    void replaceFragment() {
        sampleActivity.replaceSampleFragment();
    }
}
