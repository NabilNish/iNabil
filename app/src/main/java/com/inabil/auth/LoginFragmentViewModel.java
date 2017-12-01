package com.inabil.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.inabil.HomeActivity;
import com.inabil.R;
import com.inabil.helper.AuthLogin;
import com.inabil.helper.CommonFunctions;
import com.inabil.model.User;

import io.realm.Realm;

/**
 * Created by NABIL on 28-11-2017.
 */

public class LoginFragmentViewModel {

    public ObservableField<String> emailET = new ObservableField<>();
    public ObservableField<String> passET = new ObservableField<>();

    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passError = new ObservableField<>();

    private Context context;

    public LoginFragmentViewModel(Context context) {
        this.context = context;
    }

    public void onLogin(final View view) {

        if (emailET.get() == null || passET.get() == null) {
            CommonFunctions.showMessage(view, context.getString(R.string.incomplete));
        } else {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User user = realm.where(User.class).equalTo("email", emailET.get()).findFirst();
                    if (user != null) {
                        user = realm.where(User.class).equalTo("password", passET.get()).findFirst();
                        if (user != null)
                            gotoHome(emailET.get());
                        else {
                            passError.set(context.getString(R.string.pass_error));
                            CommonFunctions.showMessage(view, context.getString(R.string.pass_error_txt));
                        }
                    } else
                        emailError.set(context.getString(R.string.email_error));

                }
            });
        }

    }

    private void gotoHome(String email) {
        AuthLogin.saveUser(context, email);

        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}
