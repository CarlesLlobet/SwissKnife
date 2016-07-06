package xyz.carlesllobet.swissknife.UI;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import xyz.carlesllobet.swissknife.DB.UserFunctions;
import xyz.carlesllobet.swissknife.R;

/**
 * Created by CarlesLlobet on 26/01/2016.
 */
public class CalculatorActivity extends BaseActivity implements View.OnClickListener {

    private Button num1;
    private Button num2;
    private Button num3;
    private Button suma;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button resta;
    private Button num7;
    private Button num8;
    private Button num9;
    private Button multiplicacion;
    private Button coma;
    private Button num0;
    private Button igual;
    private Button division;
    private Button call;
    private Button ans;
    private Button ac;
    private Button del;

    private TextView pantalla;

    private String num = "", op = "", s = "";
    Double res = Double.valueOf(0), temp1 = Double.valueOf(0), temp2 = Double.valueOf(0), answer;
    Boolean calculated = false, saveRes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        pantalla = (TextView) findViewById(R.id.textView4);
        pantalla.setHint("0");

        num1 = (Button) findViewById(R.id.button);
        num1.setOnClickListener(this);
        num2 = (Button) findViewById(R.id.button2);
        num2.setOnClickListener(this);
        num3 = (Button) findViewById(R.id.button3);
        num3.setOnClickListener(this);
        suma = (Button) findViewById(R.id.button4);
        suma.setOnClickListener(this);
        num4 = (Button) findViewById(R.id.button5);
        num4.setOnClickListener(this);
        num5 = (Button) findViewById(R.id.button6);
        num5.setOnClickListener(this);
        num6 = (Button) findViewById(R.id.button7);
        num6.setOnClickListener(this);
        resta = (Button) findViewById(R.id.button8);
        resta.setOnClickListener(this);
        num7 = (Button) findViewById(R.id.button9);
        num7.setOnClickListener(this);
        num8 = (Button) findViewById(R.id.button10);
        num8.setOnClickListener(this);
        num9 = (Button) findViewById(R.id.button11);
        num9.setOnClickListener(this);
        multiplicacion = (Button) findViewById(R.id.button12);
        multiplicacion.setOnClickListener(this);
        coma = (Button) findViewById(R.id.button13);
        coma.setOnClickListener(this);
        num0 = (Button) findViewById(R.id.button14);
        num0.setOnClickListener(this);
        igual = (Button) findViewById(R.id.button15);
        igual.setOnClickListener(this);
        division = (Button) findViewById(R.id.button16);
        division.setOnClickListener(this);
        call = (Button) findViewById(R.id.button17);
        call.setOnClickListener(this);
        ans = (Button) findViewById(R.id.button18);
        ans.setOnClickListener(this);
        ac = (Button) findViewById(R.id.button19);
        ac.setOnClickListener(this);
        del = (Button) findViewById(R.id.button20);
        del.setOnClickListener(this);

        setTitle(R.string.tituloCalc);
    }

    @Override
    public void onClick(View v) {
        s = (String) pantalla.getText();
        pantalla.setHint("");
        if (calculated) {
            s = "";
            num = "";
            calculated = false;
        }

        switch (v.getId()) {
            case R.id.button: //Uno
                s = s + "1";
                pantalla.setText(s);
                num = num + "1";
                saveRes = false;
                break;
            case R.id.button2: //Dos
                s = s + "2";
                pantalla.setText(s);
                num = num + "2";
                saveRes = false;
                break;
            case R.id.button3: //3
                s = s + "3";
                pantalla.setText(s);
                num = num + "3";
                saveRes = false;
                break;
            case R.id.button4: //Suma
                s = "";
                pantalla.setText(s);
                switch (op) {
                    case "":
                        if (!saveRes && !num.equals("")) {                             
                            if (num.equals(".")) num = "0";
                            temp1 = Double.parseDouble(num);
                            res = Double.valueOf(0);
                        } else if (saveRes) saveRes = false;
                        op = "+";
                        calculated = true;
                        break;
                    default:
                        if (num.equals("")||num.equals(".")) temp2 = Double.valueOf(0);
                        else temp2 = Double.parseDouble(num);
                        calcula();
                        temp1 = res;
                        op = "+";
                        s = res.toString();
                        pantalla.setText(s);
                        calculated = true;
                        break;
                }
                //num = "";
                break;
            case R.id.button5: //Cuatro
                s = s + "4";
                pantalla.setText(s);
                num = num + "4";
                saveRes = false;
                break;
            case R.id.button6: //Cinco
                s = s + "5";
                pantalla.setText(s);
                num = num + "5";
                saveRes = false;
                break;
            case R.id.button7: //Seis
                s = s + "6";
                pantalla.setText(s);
                num = num + "6";
                saveRes = false;
                break;
            case R.id.button8: //Resta
                s = "";
                pantalla.setText(s);
                switch (op) {
                    case "":
                        if (!saveRes && !num.equals("")) {                             
                            if (num.equals(".")) num = "0";
                            temp1 = Double.parseDouble(num);
                            res = Double.valueOf(0);
                        } else if (saveRes) saveRes = false;
                        op = "-";
                        calculated = true;
                        break;
                    default:
                        if (num.equals("")||num.equals(".")) temp2 = Double.valueOf(0);
                        else temp2 = Double.parseDouble(num);
                        calcula();
                        temp1 = res;
                        op = "-";
                        s = res.toString();
                        pantalla.setText(s);
                        calculated = true;
                        break;
                }
                break;
            case R.id.button9: //Siete
                s = s + "7";
                pantalla.setText(s);
                num = num + "7";
                saveRes = false;
                break;
            case R.id.button10: //Ocho
                s = s + "8";
                pantalla.setText(s);
                num = num + "8";
                saveRes = false;
                break;
            case R.id.button11: //Nueve
                s = s + "9";
                pantalla.setText(s);
                num = num + "9";
                saveRes = false;
                break;
            case R.id.button12: //Multiplic.
                s = "";
                pantalla.setText(s);
                switch (op) {
                    case "":
                        if (!saveRes && !num.equals("")) {
                            if (num.equals(".")) num = "0";
                            temp1 = Double.parseDouble(num);
                            res = Double.valueOf(0);
                        } else if (saveRes) saveRes = false;
                        op = "*";
                        calculated = true;
                        break;
                    default:
                        if (num.equals("")||num.equals(".")) temp2 = Double.valueOf(0);
                        else temp2 = Double.parseDouble(num);
                        calcula();
                        temp1 = res;
                        op = "*";
                        s = res.toString();
                        pantalla.setText(s);
                        calculated = true;
                        break;
                }
                break;
            case R.id.button13: //Coma
                s = s + ".";
                pantalla.setText(s);
                num = num + ".";
                saveRes = false;
                break;
            case R.id.button14: //Zero
                s = s + "0";
                pantalla.setText(s);
                num = num + "0";
                saveRes = false;
                break;
            case R.id.button15: //Igual
                s = "";
                pantalla.setText(s);
                saveRes = true;
                switch (op) {
                    case "":
                        if (!saveRes && !num.equals("")) {                             
                            if (num.equals(".")) num = "0";
                            temp1 = Double.parseDouble(num);
                            answer = temp1;
                            s = temp1.toString();
                            pantalla.setText(s);
                        } else {
                            answer = res;
                            s = res.toString();
                            pantalla.setText(s);
                        }
                        op = "";
                        calculated = true;
                        break;
                    default:
                        if (num.equals("")||num.equals(".")) temp2 = Double.valueOf(0);
                        else temp2 = Double.parseDouble(num);
                        calcula();
                        temp1 = res;
                        op = "";
                        answer = res;
                        s = res.toString();
                        pantalla.setText(s);
                        calculated = true;
                        break;
                }
                num = res.toString();
                break;
            case R.id.button16: //Division
                s = "";
                pantalla.setText(s);
                switch (op) {
                    case "":
                        if (!saveRes && !num.equals("")) {                             
                            if (num.equals(".")) num = "0";
                            temp1 = Double.parseDouble(num);
                            res = Double.valueOf(0);
                        } else if (saveRes) saveRes = false;
                        op = "/";
                        calculated = true;
                        break;
                    default:
                        if (num.equals("")||num.equals(".")) temp2 = Double.valueOf(0);
                        else temp2 = Double.parseDouble(num);
                        calcula();
                        temp1 = res;
                        op = "/";
                        s = res.toString();
                        pantalla.setText(s);
                        calculated = true;
                        break;
                }
                break;
            case R.id.button17: //Call
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String[] aux2 = pantalla.getText().toString().split(Pattern.quote("."));
                String phone = aux2[0];
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
                break;
            case R.id.button18: //ANS
                s = answer.toString();
                num = s;
                pantalla.setText(s);
                saveRes = false;
                break;
            case R.id.button19: //AC
                temp1 = temp2 = Double.valueOf(0);
                num = "";
                op = "";
                s = "";
                pantalla.setText(s);
                pantalla.setHint("0");
                break;
            case R.id.button20: //DEL
                if (s.length() > 0) {
                    s = s.substring(0, s.length() - 1);
                    if (s.length() == 0) pantalla.setHint("0");
                }
                pantalla.setText(s);
                num = s;
                //Borrar l'últim valor de la pantalla
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        UserFunctions userFunctions = new UserFunctions();
        String un = userFunctions.getUserName(getApplicationContext());
        if (userFunctions.getToast(getApplicationContext(), un))
            menu.getItem(1).getSubMenu().getItem(0).setChecked(true);
        if (userFunctions.getNotif(getApplicationContext(), un))
            menu.getItem(1).getSubMenu().getItem(1).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UserFunctions userFunctions = new UserFunctions();
        String un = userFunctions.getUserName(getApplicationContext());
        switch (item.getItemId()) {
            case R.id.action_web:
                String url = "http://www.nyan.cat";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.action_toast:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                if (item.isChecked()) userFunctions.setToast(getApplicationContext(), un, true);
                else userFunctions.setToast(getApplicationContext(), un, false);
                break;
            case R.id.action_state:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                if (item.isChecked()) userFunctions.setNotif(getApplicationContext(), un, true);
                else userFunctions.setNotif(getApplicationContext(), un, false);
                break;
            case R.id.action_help:
                Intent intent = new Intent(this, HelpCalculatorActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void calcula() {
        switch (op) {
            case "+":                
                res = (temp1 + temp2);
                temp1 = temp2 = Double.valueOf(0);
                op = "";
                break;
            case "-":
                res = (temp1 - temp2);
                temp1 = temp2 = Double.valueOf(0);
                op = "";
                break;
            case "*":
                res = (temp1 * temp2);
                temp1 = temp2 = Double.valueOf(0);
                op = "";
                break;
            case "/":
                if (temp2 == 0) {
                    s = "";
                    pantalla.setText(s);
                    res = temp1 = temp2 = Double.valueOf(0);
                    num = "";
                    UserFunctions userFunctions = new UserFunctions();
                    if (userFunctions.getNotif(getApplicationContext(), userFunctions.getUserName(getApplicationContext()))) {
                        Context context = CalculatorActivity.this;
                        Notification n = new Notification.Builder(context)
                                .setContentTitle(getResources().getString(R.string.tituloCalc))
                                .setContentText(getResources().getString(R.string.notifDiv))
                                .setSmallIcon(R.drawable.ic_calculator_grey600_24dp)
                                .setWhen(System.currentTimeMillis())
                                .build();

                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        // Hide the notification after its selected

                        notificationManager.notify(0, n);
                        userFunctions.setLastNotif(getApplicationContext(), userFunctions.getUserName(getApplicationContext()), getResources().getString(R.string.notifDiv));
                    }
                    if (userFunctions.getToast(getApplicationContext(), userFunctions.getUserName(getApplicationContext()))) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.notifDiv), Toast.LENGTH_LONG).show();
                    }

                } else {
                    res = (temp1 / temp2);
                    temp1 = temp2 = Double.valueOf(0);
                    op = "";
                    break;

                }
        }
        num = res.toString();
        if (res.isNaN()) {
            s = "";
            pantalla.setText(s);
            res = temp1 = temp2 = Double.valueOf(0);
            num = "";
            UserFunctions userFunctions = new UserFunctions();
            if (userFunctions.getNotif(getApplicationContext(), userFunctions.getUserName(getApplicationContext()))) {
                Context context = CalculatorActivity.this;
                Notification n = new Notification.Builder(context)
                        .setContentTitle(getResources().getString(R.string.tituloCalc))
                        .setContentText(getResources().getString(R.string.notifText))
                        .setSmallIcon(R.drawable.ic_calculator_grey600_24dp)
                        .setWhen(System.currentTimeMillis())
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Hide the notification after its selected

                notificationManager.notify(0, n);

                userFunctions.setLastNotif(getApplicationContext(), userFunctions.getUserName(getApplicationContext()), getResources().getString(R.string.notifText));
            }
            if (userFunctions.getToast(getApplicationContext(), userFunctions.getUserName(getApplicationContext()))) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.notifText), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (num != null) outState.putString("num", num);
        if (op != null) outState.putString("op", op);
        if (s != null) outState.putString("s", s);

        if (res != null) outState.putDouble("res", res);
        if (temp1 != null) outState.putDouble("temp1", temp1);
        if (temp2 != null) outState.putDouble("temp2", temp2);
        if (answer != null) outState.putDouble("answer", answer);

        if (calculated != null) outState.putBoolean("calculated", calculated);
        if (saveRes != null) outState.putBoolean("saveRes", saveRes);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("num")) num = savedInstanceState.getString("num");
        if (savedInstanceState.containsKey("op")) op = savedInstanceState.getString("op");
        if (savedInstanceState.containsKey("s")) s = savedInstanceState.getString("s");

        if (savedInstanceState.containsKey("res")) res = savedInstanceState.getDouble("res");
        if (savedInstanceState.containsKey("temp1")) temp1 = savedInstanceState.getDouble("temp1");
        if (savedInstanceState.containsKey("temp2")) temp2 = savedInstanceState.getDouble("temp2");
        if (savedInstanceState.containsKey("answer"))
            answer = savedInstanceState.getDouble("answer");

        if (savedInstanceState.containsKey("calculated"))
            calculated = savedInstanceState.getBoolean("calculated");
        if (savedInstanceState.containsKey("calculated"))
            calculated = savedInstanceState.getBoolean("calculated");

        pantalla.setText(s);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkMenuItem(3);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
