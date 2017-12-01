package com.inabil.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Patterns;
import android.view.View;

import com.inabil.HomeActivity;
import com.inabil.R;
import com.inabil.helper.AuthLogin;
import com.inabil.helper.CommonFunctions;
import com.inabil.helper.Constants;
import com.inabil.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;

/**
 * Created by NABIL on 28-11-2017.
 */

public class SignupFragmentViewModel {

    public ObservableArrayList<String> types = new ObservableArrayList<>();
    public ObservableField<String> fName = new ObservableField<>();
    public ObservableField<String> lName = new ObservableField<>();
    public ObservableField<String> mNumber = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> pass = new ObservableField<>();

    public ObservableField<String> mNumberError = new ObservableField<>();
    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passError = new ObservableField<>();

    private Context context;

    public SignupFragmentViewModel(Context context) {
        this.context = context;
        types.addAll(Constants.userTypes);
    }

    public void onSignup(final View view) {
        if (email.get() == null || pass.get() == null || mNumber.get() == null || SignupFragment.getType().equals(Constants.defaultUserType)) {
            CommonFunctions.showMessage(view, context.getString(R.string.incomplete));
        } else {
            if (!CommonFunctions.isValidMobile(mNumber.get()))
                mNumberError.set(context.getString(R.string.mobile_error));
            else if (!isValidEmail(email.get()))
                emailError.set(context.getString(R.string.email_error));
            else if (!isValidPass(pass.get())) {
                passError.set(context.getString(R.string.pass_error));
                CommonFunctions.showMessage(view, context.getString(R.string.pass_error_txt));
            } else {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        User user = realm.where(User.class).equalTo("email", email.get()).findFirst();
                        if (user != null)
                            CommonFunctions.showMessage(view, context.getString(R.string.email_exist));
                        else {
                            User userAdd = new User(email.get(), SignupFragment.getType(), fName.get(), lName.get(), mNumber.get(), pass.get());
                            realm.copyToRealmOrUpdate(userAdd);
                            gotoHome(email.get());
                        }

                    }
                });
            }

        }

    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() ? true : false;
    }

    private boolean isValidPass(String pass) {
        if (pass.length() < 8)
            return false;
        else {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(pass);
            return m.find();
        }
    }

    private void gotoHome(String email) {
        AuthLogin.saveUser(context, email);

        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}

