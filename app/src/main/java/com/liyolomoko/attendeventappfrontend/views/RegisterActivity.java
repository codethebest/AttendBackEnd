package com.liyolomoko.attendeventappfrontend.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.liyolomoko.attendeventappfrontend.R;
import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.user.UserRegistration;
import com.liyolomoko.attendeventappfrontend.factories.user.UserRegistrationFactory;
import com.liyolomoko.attendeventappfrontend.service.user.UserRegistrationService;
import com.liyolomoko.attendeventappfrontend.service.user.impl.UserRegistrationServiceimpl;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void btnaddaccount(View v)
    {

        String gender ;
        String name = ((EditText)findViewById(R.id.tbname)).getText().toString();
        String username = ((EditText)findViewById(R.id.tbUsername)).getText().toString();
        String password =((EditText)findViewById(R.id.tbpassword)).getText().toString();
        CheckBox checkBoxmale = (CheckBox)findViewById(R.id.cBMale);
        CheckBox checkBoxfemale = (CheckBox)findViewById(R.id.cbfemale);

        if (checkBoxfemale.isChecked())
        {
            gender = "female";
        }
        else
            gender ="male";

        UserRegistration userRegistration = UserRegistrationFactory.getUserRegistration(username,password,name,gender);
        UserRegistrationService service = UserRegistrationServiceimpl.getInstance();
        service.addUser(App.getAppContext(),userRegistration);

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
