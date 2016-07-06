package xyz.carlesllobet.swissknife.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import xyz.carlesllobet.swissknife.DB.UserFunctions;
import xyz.carlesllobet.swissknife.R;

/**
 * Created by JEDI on 17/08/2015.
 */
public class PasswordActivity extends BaseActivity implements View.OnClickListener  {
    private Button btnChange;

    private EditText oldPass;
    private EditText userName;
    private EditText newPass;
    private EditText repeated;

    private TextView error;

    private UserFunctions userFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userFunctions = new UserFunctions();

        if(userFunctions.isUserAdmin(getApplicationContext())) {
            setContentView(R.layout.activity_password_admin);
            userName = (EditText) findViewById(R.id.userName);
        }
        else{
            setContentView(R.layout.activity_password);
            oldPass = (EditText) findViewById(R.id.oldPassword);
        }

        newPass = (EditText) findViewById(R.id.newPassword);
        repeated = (EditText) findViewById(R.id.newPassword2);

        error = (TextView) findViewById(R.id.change_error);

        setTitle(R.string.tituloPassword);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChange:
                String nP = newPass.getText().toString();
                String rP = repeated.getText().toString();
                if (nP.equals(rP)) {
                    userFunctions = new UserFunctions();
                    if (userFunctions.isUserAdmin(getApplicationContext())) {
                        String un = userName.getText().toString();
                        userFunctions.setPass(getApplicationContext(),un, nP);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.notifPass), Toast.LENGTH_LONG).show();
                    } else {
                        String oP = oldPass.getText().toString();
                        String un = userFunctions.getUserName(getApplicationContext());
                        if(userFunctions.getPass(getApplicationContext(),un).equals(oP)){
                            userFunctions.setPass(getApplicationContext(),un,nP);
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.notifPass), Toast.LENGTH_LONG).show();
                        }
                        else error.setText(R.string.error1);
                    }
                    Intent intent = new Intent(PasswordActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    //tancar menu
                }
                else error.setText(R.string.error2);
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_others, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_help:
                Intent intent = new Intent(this, HelpSettingsActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
