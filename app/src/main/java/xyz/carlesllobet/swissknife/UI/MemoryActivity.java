package xyz.carlesllobet.swissknife.UI;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.HashMap;
import java.util.Random;

import xyz.carlesllobet.swissknife.DB.UserFunctions;
import xyz.carlesllobet.swissknife.R;

/**
 * Created by CarlesLlobet on 26/01/2016.
 */
public class MemoryActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20, card21, card22, card23, card24, card25, card26, card27, card28, card29, card30, card31, card32, card33, card34, card35, card36;

    Integer punt;
    private TextView intents;

    private int baraja;

    private int acertadas;

    private CoolImageFlipper animacio;

    private HashMap<Integer, Integer> solucio;

    private int otraUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        otraUp = punt = acertadas = baraja = 0;

        animacio = new CoolImageFlipper(getApplicationContext());

        setTitle("Memory");

        setContentView(R.layout.activity_memory);

        card1 = (ImageButton) findViewById(R.id.card1);
        card2 = (ImageButton) findViewById(R.id.card2);
        card3 = (ImageButton) findViewById(R.id.card3);
        card4 = (ImageButton) findViewById(R.id.card4);
        card5 = (ImageButton) findViewById(R.id.card5);
        card6 = (ImageButton) findViewById(R.id.card6);
        card7 = (ImageButton) findViewById(R.id.card7);
        card8 = (ImageButton) findViewById(R.id.card8);
        card9 = (ImageButton) findViewById(R.id.card9);
        card10 = (ImageButton) findViewById(R.id.card10);
        card11 = (ImageButton) findViewById(R.id.card11);
        card12 = (ImageButton) findViewById(R.id.card12);
        card13 = (ImageButton) findViewById(R.id.card13);
        card14 = (ImageButton) findViewById(R.id.card14);
        card15 = (ImageButton) findViewById(R.id.card15);
        card16 = (ImageButton) findViewById(R.id.card16);

        intents = (TextView) findViewById(R.id.intents);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
        card11.setOnClickListener(this);
        card12.setOnClickListener(this);
        card13.setOnClickListener(this);
        card14.setOnClickListener(this);
        card15.setOnClickListener(this);
        card16.setOnClickListener(this);

        View radioButtonGroupView = View.inflate(this, R.layout.new_game, null);
        final RadioGroup radioButtonGroup = (RadioGroup) radioButtonGroupView.findViewById(R.id.select_baraja);
        final RadioGroup radioButtonGroup2 = (RadioGroup) radioButtonGroupView.findViewById(R.id.select_size);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.novaPartida);
        builder.setMessage(R.string.novaPartidaText)
                .setView(radioButtonGroupView)
                .setCancelable(false)
                .setPositiveButton(R.string.btnStart, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
                        int radioButtonID2 = radioButtonGroup2.getCheckedRadioButtonId();
                        View radioButton = radioButtonGroup.findViewById(radioButtonID);
                        View radioButton2 = radioButtonGroup2.findViewById(radioButtonID2);
                        int idx = radioButtonGroup.indexOfChild(radioButton);
                        int idx2 = radioButtonGroup2.indexOfChild(radioButton2);
                        if (idx == 0) baraja = 0;
                        else if (idx == 1) {
                            baraja = 1;
                            for (int i = 1; i < 17; ++i) setImage(i, 8);
                        } else {
                            baraja = 2;
                            for (int i = 1; i < 17; ++i) setImage(i, 8);
                        }
                        if (idx2 == 1) {
                            Intent intent = new Intent(MemoryActivity.this, Memory2Activity.class);
                            intent.putExtra("baraja", baraja);
                            startActivity(intent);
                        }
                    }
                })
                .setIcon(R.drawable.ic_gamepad_variant_grey600_24dp)
                .show();


        solucio = new HashMap<>(16);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                Aleatorio();
            }
        }, 0);
    }

    private void Aleatorio() {
        Integer[] use = {0, 0, 0, 0, 0, 0, 0, 0};
        Boolean[] cards = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        Random r = new Random();
        Integer count = 7;
        while (count >= 0) {
            Integer i = r.nextInt(17 - 1) + 1;
            if (use[count] < 2 && cards[i - 1] == false) {
                solucio.put(i, count);
                cards[i - 1] = true;
                ++use[count];
            } else if (use[count] == 2) --count;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memory, menu);
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
            case R.id.action_restart:
                startActivity((new Intent(getApplicationContext(), MemoryActivity.class)));
                Toast.makeText(MemoryActivity.this, R.string.novaPartida, Toast.LENGTH_SHORT).show();
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
                startActivity((new Intent(getApplicationContext(), HelpMemoryActivity.class)));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Integer aux;
        Handler handler = new Handler();
        switch (v.getId()) {
            case R.id.card1:
                aux = solucio.get(1); //Numero de carta
                setImage(1, aux);     //Numero de carta

                final boolean up1, right1; // ha de ser final??

                if (otraUp == 0) up1 = false;
                else up1 = true;
                if (solucio.get(otraUp) == aux) right1 = true;
                else right1 = false;

                if (!up1) {
                    otraUp = 1; //Numero de carta
                    card1.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up1) {
                            if (right1) {
                                setImage(1, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(1, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card2:
                aux = solucio.get(2); //Numero de carta
                setImage(2, aux);     //Numero de carta

                final boolean up2, right2; // ha de ser final??

                if (otraUp == 0) up2 = false;
                else up2 = true;
                if (solucio.get(otraUp) == aux) right2 = true;
                else right2 = false;

                if (!up2) {
                    otraUp = 2; //Numero de carta
                    card2.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up2) {
                            if (right2) {
                                setImage(2, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(2, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card3:
                aux = solucio.get(3); //Numero de carta
                setImage(3, aux);     //Numero de carta

                final boolean up3, right3; // ha de ser final??

                if (otraUp == 0) up3 = false;
                else up3 = true;
                if (solucio.get(otraUp) == aux) right3 = true;
                else right3 = false;

                if (!up3) {
                    otraUp = 3; //Numero de carta
                    card3.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up3) {
                            if (right3) {
                                setImage(3, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(3, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card4:
                aux = solucio.get(4); //Numero de carta
                setImage(4, aux);     //Numero de carta

                final boolean up4, right4; // ha de ser final??

                if (otraUp == 0) up4 = false;
                else up4 = true;
                if (solucio.get(otraUp) == aux) right4 = true;
                else right4 = false;

                if (!up4) {
                    otraUp = 4; //Numero de carta
                    card4.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up4) {
                            if (right4) {
                                setImage(4, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(4, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card5:
                aux = solucio.get(5); //Numero de carta
                setImage(5, aux);     //Numero de carta

                final boolean up5, right5; // ha de ser final??

                if (otraUp == 0) up5 = false;
                else up5 = true;
                if (solucio.get(otraUp) == aux) right5 = true;
                else right5 = false;

                if (!up5) {
                    otraUp = 5; //Numero de carta
                    card5.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up5) {
                            if (right5) {
                                setImage(5, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(5, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card6:
                aux = solucio.get(6); //Numero de carta
                setImage(6, aux);     //Numero de carta

                final boolean up6, right6; // ha de ser final??

                if (otraUp == 0) up6 = false;
                else up6 = true;
                if (solucio.get(otraUp) == aux) right6 = true;
                else right6 = false;

                if (!up6) {
                    otraUp = 6; //Numero de carta
                    card6.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up6) {
                            if (right6) {
                                setImage(6, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(6, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card7:
                aux = solucio.get(7); //Numero de carta
                setImage(7, aux);     //Numero de carta

                final boolean up7, right7; // ha de ser final??

                if (otraUp == 0) up7 = false;
                else up7 = true;
                if (solucio.get(otraUp) == aux) right7 = true;
                else right7 = false;

                if (!up7) {
                    otraUp = 7; //Numero de carta
                    card7.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up7) {
                            if (right7) {
                                setImage(7, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(7, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card8:
                aux = solucio.get(8); //Numero de carta
                setImage(8, aux);     //Numero de carta

                final boolean up8, right8; // ha de ser final??

                if (otraUp == 0) up8 = false;
                else up8 = true;
                if (solucio.get(otraUp) == aux) right8 = true;
                else right8 = false;

                if (!up8) {
                    otraUp = 8; //Numero de carta
                    card8.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up8) {
                            if (right8) {
                                setImage(8, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(8, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card9:
                aux = solucio.get(9); //Numero de carta
                setImage(9, aux);     //Numero de carta

                final boolean up9, right9; // ha de ser final??

                if (otraUp == 0) up9 = false;
                else up9 = true;
                if (solucio.get(otraUp) == aux) right9 = true;
                else right9 = false;

                if (!up9) {
                    otraUp = 9; //Numero de carta
                    card9.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up9) {
                            if (right9) {
                                setImage(9, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(9, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card10:
                aux = solucio.get(10); //Numero de carta
                setImage(10, aux);     //Numero de carta

                final boolean up10, right10; // ha de ser final??

                if (otraUp == 0) up10 = false;
                else up10 = true;
                if (solucio.get(otraUp) == aux) right10 = true;
                else right10 = false;

                if (!up10) {
                    otraUp = 10; //Numero de carta
                    card10.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up10) {
                            if (right10) {
                                setImage(10, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(10, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card11:
                aux = solucio.get(11); //Numero de carta
                setImage(11, aux);     //Numero de carta

                final boolean up11, right11; // ha de ser final??

                if (otraUp == 0) up11 = false;
                else up11 = true;
                if (solucio.get(otraUp) == aux) right11 = true;
                else right11 = false;

                if (!up11) {
                    otraUp = 11; //Numero de carta
                    card11.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up11) {
                            if (right11) {
                                setImage(11, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(11, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card12:
                aux = solucio.get(12); //Numero de carta
                setImage(12, aux);     //Numero de carta

                final boolean up12, right12; // ha de ser final??

                if (otraUp == 0) up12 = false;
                else up12 = true;
                if (solucio.get(otraUp) == aux) right12 = true;
                else right12 = false;

                if (!up12) {
                    otraUp = 12; //Numero de carta
                    card12.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up12) {
                            if (right12) {
                                setImage(12, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(12, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card13:
                aux = solucio.get(13); //Numero de carta
                setImage(13, aux);     //Numero de carta

                final boolean up13, right13; // ha de ser final??

                if (otraUp == 0) up13 = false;
                else up13 = true;
                if (solucio.get(otraUp) == aux) right13 = true;
                else right13 = false;

                if (!up13) {
                    otraUp = 13; //Numero de carta
                    card13.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up13) {
                            if (right13) {
                                setImage(13, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(13, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card14:
                aux = solucio.get(14); //Numero de carta
                setImage(14, aux);     //Numero de carta

                final boolean up14, right14; // ha de ser final??

                if (otraUp == 0) up14 = false;
                else up14 = true;
                if (solucio.get(otraUp) == aux) right14 = true;
                else right14 = false;

                if (!up14) {
                    otraUp = 14; //Numero de carta
                    card14.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up14) {
                            if (right14) {
                                setImage(14, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(14, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card15:
                aux = solucio.get(15); //Numero de carta
                setImage(15, aux);     //Numero de carta

                final boolean up15, right15; // ha de ser final??

                if (otraUp == 0) up15 = false;
                else up15 = true;
                if (solucio.get(otraUp) == aux) right15 = true;
                else right15 = false;

                if (!up15) {
                    otraUp = 15; //Numero de carta
                    card15.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }


                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up15) {
                            if (right15) {
                                setImage(15, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(15, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card16:
                aux = solucio.get(16); //Numero de carta
                setImage(16, aux);     //Numero de carta

                final boolean up16, right16; // ha de ser final??

                if (otraUp == 0) up16 = false;
                else up16 = true;
                if (solucio.get(otraUp) == aux) right16 = true;
                else right16 = false;

                if (!up16) {
                    otraUp = 16; //Numero de carta
                    card16.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up16) {
                            if (right16) {
                                setImage(16, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 8) fiPartida(MemoryActivity.this);
                            } else {
                                setImage(16, 8); //Numero de carta
                                setImage(otraUp, 8);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
        }
    }


    private void fiPartida(Context context) {
        //actualitzar puntuació
        UserFunctions userFunctions = new UserFunctions();
        String un = userFunctions.getUserName(context);
        if (userFunctions.getPunt(context, un) == 0) userFunctions.setPunt(context, un, punt);
        else if (punt < userFunctions.getPunt(context, un))
            userFunctions.setPunt(context, un, punt);

        if (userFunctions.getNotif(context, un)) {
            Notification n = new Notification.Builder(context)
                    .setContentTitle("Memory")
                    .setContentText(getResources().getString(R.string.notifGame1) + punt + getResources().getString(R.string.notifGame2))
                    .setSmallIcon(R.drawable.ic_gamepad_variant_grey600_24dp)
                    .setWhen(System.currentTimeMillis())
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Hide the notification after its selected

            notificationManager.notify(0, n);
            userFunctions.setLastNotif(getApplicationContext(), userFunctions.getUserName(getApplicationContext()), getResources().getString(R.string.notifGame1) + punt + getResources().getString(R.string.notifGame2));
        }
        if (userFunctions.getToast(context, un)) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.notifGame1) + punt + getResources().getString(R.string.notifGame2),
                    Toast.LENGTH_LONG).show();
        }

        //Notificar final partida
        new AlertDialog.Builder(context)
                .setTitle(R.string.finalPartida)
                .setMessage(R.string.finalPartidaText)
                .setPositiveButton(R.string.btnSi, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity((new Intent(getApplicationContext(), MemoryActivity.class)));
                    }
                })
                .setNegativeButton(R.string.btnNo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity((new Intent(getApplicationContext(), RankingHolder.class)));
                    }
                })
                .setIcon(R.mipmap.ic_swissknife)
                .show();
    }

    private void BlockCards(boolean b) {
        if (b) {
            card1.setClickable(false);
            card2.setClickable(false);
            card3.setClickable(false);
            card4.setClickable(false);
            card5.setClickable(false);
            card6.setClickable(false);
            card7.setClickable(false);
            card8.setClickable(false);
            card9.setClickable(false);
            card10.setClickable(false);
            card11.setClickable(false);
            card12.setClickable(false);
            card13.setClickable(false);
            card14.setClickable(false);
            card15.setClickable(false);
            card16.setClickable(false);
        } else {
            if (card1.getAlpha() == 1f) card1.setClickable(true);
            if (card2.getAlpha() == 1f) card2.setClickable(true);
            if (card3.getAlpha() == 1f) card3.setClickable(true);
            if (card4.getAlpha() == 1f) card4.setClickable(true);
            if (card5.getAlpha() == 1f) card5.setClickable(true);
            if (card6.getAlpha() == 1f) card6.setClickable(true);
            if (card7.getAlpha() == 1f) card7.setClickable(true);
            if (card8.getAlpha() == 1f) card8.setClickable(true);
            if (card9.getAlpha() == 1f) card9.setClickable(true);
            if (card10.getAlpha() == 1f) card10.setClickable(true);
            if (card11.getAlpha() == 1f) card11.setClickable(true);
            if (card12.getAlpha() == 1f) card12.setClickable(true);
            if (card13.getAlpha() == 1f) card13.setClickable(true);
            if (card14.getAlpha() == 1f) card14.setClickable(true);
            if (card15.getAlpha() == 1f) card15.setClickable(true);
            if (card16.getAlpha() == 1f) card16.setClickable(true);
        }
    }

    private void setImage(Integer card, Integer image) {
        if (image >= 0 && image <= 18) {
            int imagen = getImage(image);
            switch (card) {
                case 1:
                    animacio.flipImage(getDrawable(imagen), card1);
                    break;
                case 2:
                    animacio.flipImage(getDrawable(imagen), card2);
                    break;
                case 3:
                    animacio.flipImage(getDrawable(imagen), card3);
                    break;
                case 4:
                    animacio.flipImage(getDrawable(imagen), card4);
                    break;
                case 5:
                    animacio.flipImage(getDrawable(imagen), card5);
                    break;
                case 6:
                    animacio.flipImage(getDrawable(imagen),card6);
                    break;
                case 7:
                    animacio.flipImage(getDrawable(imagen), card7);
                    break;
                case 8:
                    animacio.flipImage(getDrawable(imagen), card8);
                    break;
                case 9:
                    animacio.flipImage(getDrawable(imagen), card9);
                    break;
                case 10:
                    animacio.flipImage(getDrawable(imagen), card10);
                    break;
                case 11:
                    animacio.flipImage(getDrawable(imagen), card11);
                    break;
                case 12:
                    animacio.flipImage(getDrawable(imagen), card12);
                    break;
                case 13:
                    animacio.flipImage(getDrawable(imagen), card13);
                    break;
                case 14:
                    animacio.flipImage(getDrawable(imagen), card14);
                    break;
                case 15:
                    animacio.flipImage(getDrawable(imagen), card15);
                    break;
                case 16:
                    animacio.flipImage(getDrawable(imagen),card16);
                    break;
            }
        } else if (image == -1) {
            switch (card) {
                case 1:
                    card1.setAlpha(0.1f);
                    card1.setClickable(false);
                    break;
                case 2:
                    card2.setAlpha(0.1f);
                    card2.setClickable(false);
                    break;
                case 3:
                    card3.setAlpha(0.1f);
                    card3.setClickable(false);
                    break;
                case 4:
                    card4.setAlpha(0.1f);
                    card4.setClickable(false);
                    break;
                case 5:
                    card5.setAlpha(0.1f);
                    card5.setClickable(false);
                    break;
                case 6:
                    card6.setAlpha(0.1f);
                    card6.setClickable(false);
                    break;
                case 7:
                    card7.setAlpha(0.1f);
                    card7.setClickable(false);
                    break;
                case 8:
                    card8.setAlpha(0.1f);
                    card8.setClickable(false);
                    break;
                case 9:
                    card9.setAlpha(0.1f);
                    card9.setClickable(false);
                    break;
                case 10:
                    card10.setAlpha(0.1f);
                    card10.setClickable(false);
                    break;
                case 11:
                    card11.setAlpha(0.1f);
                    card11.setClickable(false);
                    break;
                case 12:
                    card12.setAlpha(0.1f);
                    card12.setClickable(false);
                    break;
                case 13:
                    card13.setAlpha(0.1f);
                    card13.setClickable(false);
                    break;
                case 14:
                    card14.setAlpha(0.1f);
                    card14.setClickable(false);
                    break;
                case 15:
                    card15.setAlpha(0.1f);
                    card15.setClickable(false);
                    break;
                case 16:
                    card16.setAlpha(0.1f);
                    card16.setClickable(false);
                    break;

            }
        }
    }

    private int getImage(int image) {
        if (baraja == 2) {
            switch (image) {
                case 0:
                    return R.mipmap.eat_chocolate;
                case 1:
                    return R.mipmap.eat_roll;
                case 2:
                    return R.mipmap.go_swiss;
                case 3:
                    return R.mipmap.im_swiss;
                case 4:
                    return R.mipmap.keep_budget;
                case 5:
                    return R.mipmap.money_swissbank;
                case 6:
                    return R.mipmap.spray_chocolate;
                case 7:
                    return R.mipmap.stop_keepcalm;
                case 8:
                    return R.mipmap.back;
            }
        } else if (baraja == 1) {
            switch (image) {
                case 0:
                    return R.mipmap.carles;
                case 1:
                    return R.mipmap.fred_tetas;
                case 2:
                    return R.mipmap.joan;
                case 3:
                    return R.mipmap.gato_joaquin;
                case 4:
                    return R.mipmap.joaquin;
                case 5:
                    return R.mipmap.marcos_creppe;
                case 6:
                    return R.mipmap.pezon;
                case 7:
                    return R.mipmap.joan_xurro;
                case 8:
                    return R.mipmap.purus;
            }
        } else {
            switch (image) {
                case 0:
                    return R.mipmap.boba;
                case 1:
                    return R.mipmap.c3po;
                case 2:
                    return R.mipmap.mercenaire;
                case 3:
                    return R.mipmap.old_logo;
                case 4:
                    return R.mipmap.r2d2;
                case 5:
                    return R.mipmap.soldier;
                case 6:
                    return R.mipmap.vader;
                case 7:
                    return R.mipmap.yoda;
                case 8:
                    return R.mipmap.logo;
            }
        }
        return R.mipmap.logo;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkMenuItem(1);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

