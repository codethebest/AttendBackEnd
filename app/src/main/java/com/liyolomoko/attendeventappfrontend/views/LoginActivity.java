package com.liyolomoko.attendeventappfrontend.views;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liyolomoko.attendeventappfrontend.R;
import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.repositories.user.UserRegistrationRepository;
import com.liyolomoko.attendeventappfrontend.repositories.user.impl.UserRegistrationRepositoryImp;
import com.liyolomoko.attendeventappfrontend.service.user.impl.UserRegistrationServiceimpl;

public class LoginActivity extends AppCompatActivity {

    UserRegistrationRepository repo;
    UserRegistrationServiceimpl service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnlogin(View v)
    {
        //repo = new UserRegistrationRepositoryImp(App.getAppContext());
       /* String username = ((TextView)findViewById(R.id.tbUsername)).getText().toString();
        String password = ((TextView)findViewById(R.id.tbpassword)).getText().toString();
*/
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    public void btnregister(View v )
    {
        Intent intent = new Intent (this,RegisterActivity.class);
        startActivity(intent);
    }
}
