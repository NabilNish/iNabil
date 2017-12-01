package com.inabil.auth;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inabil.R;
import com.inabil.databinding.AuthSignupBinding;
import com.inabil.helper.Constants;

/**
 * Created by NABIL on 28-11-2017.
 */

public class SignupFragment extends Fragment {

    private static AuthSignupBinding binding;
    private SignupFragmentViewModel signupFragmentViewModel;

    public SignupFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupFragmentViewModel = new SignupFragmentViewModel(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.auth_signup, container, false);
        binding.setSignupViewModel(signupFragmentViewModel);

        View view = binding.getRoot();
        return view;
    }


    public static String getType() {
        AppCompatSpinner spinner = binding.type;
        return spinner.getSelectedItem() != null ? spinner.getSelectedItem().toString() : Constants.defaultUserType;
    }


}

