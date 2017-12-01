package com.inabil;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inabil.helper.AuthLogin;
import com.inabil.helper.CommonFunctions;
import com.inabil.model.User;

import io.realm.Realm;

/**
 * Created by NABIL on 29-11-2017.
 */

public class HomeActivityViewModel {

    public ObservableField<String> fname = new ObservableField<>();
    public ObservableField<String> lname = new ObservableField<>();
    public ObservableField<String> mNo = new ObservableField<>();

    public ObservableBoolean isHomeLoading = new ObservableBoolean(false);

    private Context context;
    private Realm realm;
    private User user;

    public HomeActivityViewModel(final Context context) {
        this.context = context;
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                user = realm.where(User.class).equalTo("email", AuthLogin.getUser(context)).findFirst();
                String userFname = user.getFirstName();
                String userLname = user.getLastName();
                fname.set(userFname != null && !userFname.matches("") ? "First Name: " + userFname : "First Name: -nil-");
                lname.set(userLname != null && !userLname.matches("") ? "Last Name: " + userLname : "Last Name: -nil-");
                mNo.set(user.getMobile());
            }
        });
    }

    public void onMobileEdit(final View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText edittext = new EditText(context);
        edittext.setInputType(InputType.TYPE_CLASS_PHONE);
        edittext.setText(user.getMobile());
        edittext.setSelection(edittext.getText().length());
        alert.setMessage(context.getString(R.string.change_mobile));
        alert.setTitle(context.getString(R.string.app_name));
        alert.setIcon(R.mipmap.ic_launcher);

        alert.setView(edittext);

        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String mobile = edittext.getText().toString();
                if (!CommonFunctions.isValidMobile(mobile))
                    edittext.setError(context.getString(R.string.mobile_error));
                else {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            User user = realm.where(User.class).equalTo("email", AuthLogin.getUser(context)).findFirst();
                            user.setMobile(mobile);
                            mNo.set(mobile);
                            CommonFunctions.showMessage(view, context.getString(R.string.changed_mobile));
                        }
                    });
                }
            }
        });

        alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    public void onUserType(View view) {
        Toast.makeText(context, "Your account type is " + user.getUserType(), Toast.LENGTH_SHORT).show();
    }

    public void onLogout(View view) {
        AuthLogin.saveUser(context, null);
        isHomeLoading.set(true);//Progress dialog is deprecated
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(context, Mainsplash.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }, 5000);
    }


}
