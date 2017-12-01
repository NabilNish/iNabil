package com.inabil.auth;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inabil.R;
import com.inabil.databinding.AuthLoginBinding;

/**
 * Created by NABIL on 28-11-2017.
 */

public class LoginFragment extends Fragment {

    private AuthLoginBinding binding;
    private LoginFragmentViewModel loginFragmentViewModel;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginFragmentViewModel = new LoginFragmentViewModel(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.auth_login, container, false);
        binding.setLoginViewModel(loginFragmentViewModel);

        View view = binding.getRoot();
        return view;
    }

}

