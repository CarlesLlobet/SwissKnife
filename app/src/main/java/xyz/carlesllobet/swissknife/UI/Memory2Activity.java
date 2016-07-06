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
public class Memory2Activity extends BaseActivity implements View.OnClickListener {

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

        Intent intent = getIntent();
        baraja = intent.getIntExtra("baraja", 0);

        otraUp = punt = acertadas = 0;

        animacio = new CoolImageFlipper(getApplicationContext());

        setTitle("Memory");

        setContentView(R.layout.activity_memory2);

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
        card17 = (ImageButton) findViewById(R.id.card17);
        card18 = (ImageButton) findViewById(R.id.card18);
        card19 = (ImageButton) findViewById(R.id.card19);
        card20 = (ImageButton) findViewById(R.id.card20);
        card21 = (ImageButton) findViewById(R.id.card21);
        card22 = (ImageButton) findViewById(R.id.card22);
        card23 = (ImageButton) findViewById(R.id.card23);
        card24 = (ImageButton) findViewById(R.id.card24);
        card25 = (ImageButton) findViewById(R.id.card25);
        card26 = (ImageButton) findViewById(R.id.card26);
        card27 = (ImageButton) findViewById(R.id.card27);
        card28 = (ImageButton) findViewById(R.id.card28);
        card29 = (ImageButton) findViewById(R.id.card29);
        card30 = (ImageButton) findViewById(R.id.card30);
        card31 = (ImageButton) findViewById(R.id.card31);
        card32 = (ImageButton) findViewById(R.id.card32);
        card33 = (ImageButton) findViewById(R.id.card33);
        card34 = (ImageButton) findViewById(R.id.card34);
        card35 = (ImageButton) findViewById(R.id.card35);
        card36 = (ImageButton) findViewById(R.id.card36);

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
        card17.setOnClickListener(this);
        card18.setOnClickListener(this);
        card19.setOnClickListener(this);
        card20.setOnClickListener(this);
        card21.setOnClickListener(this);
        card22.setOnClickListener(this);
        card23.setOnClickListener(this);
        card24.setOnClickListener(this);
        card25.setOnClickListener(this);
        card26.setOnClickListener(this);
        card27.setOnClickListener(this);
        card28.setOnClickListener(this);
        card29.setOnClickListener(this);
        card30.setOnClickListener(this);
        card31.setOnClickListener(this);
        card32.setOnClickListener(this);
        card33.setOnClickListener(this);
        card34.setOnClickListener(this);
        card35.setOnClickListener(this);
        card36.setOnClickListener(this);

        for (int i = 1; i < 37; ++i) setImage(i, 18);

        solucio = new HashMap<>(36);

        Aleatorio();
    }

    private void Aleatorio() {
        Integer[] use = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Boolean[] cards = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        Random r = new Random();
        Integer count = 17;
        while (count >= 0) {
            Integer i = r.nextInt(37 - 1) + 1;
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
                Toast.makeText(Memory2Activity.this, R.string.novaPartida, Toast.LENGTH_SHORT).show();
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(1, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(2, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(3, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(4, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(5, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(6, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(7, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(8, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(9, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(10, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(11, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(12, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(13, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(14, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(15, 18); //Numero de carta
                                setImage(otraUp, 18);
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
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(16, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card17:
                aux = solucio.get(17); //Numero de carta
                setImage(17, aux);     //Numero de carta

                final boolean up17, right17; // ha de ser final??

                if (otraUp == 0) up17 = false;
                else up17 = true;
                if (solucio.get(otraUp) == aux) right17 = true;
                else right17 = false;

                if (!up17) {
                    otraUp = 17; //Numero de carta
                    card17.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up17) {
                            if (right17) {
                                setImage(17, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(17, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card18:
                aux = solucio.get(18); //Numero de carta
                setImage(18, aux);     //Numero de carta

                final boolean up18, right18; // ha de ser final??

                if (otraUp == 0) up18 = false;
                else up18 = true;
                if (solucio.get(otraUp) == aux) right18 = true;
                else right18 = false;

                if (!up18) {
                    otraUp = 18; //Numero de carta
                    card18.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up18) {
                            if (right18) {
                                setImage(18, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(18, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card19:
                aux = solucio.get(19); //Numero de carta
                setImage(19, aux);     //Numero de carta

                final boolean up19, right19; // ha de ser final??

                if (otraUp == 0) up19 = false;
                else up19 = true;
                if (solucio.get(otraUp) == aux) right19 = true;
                else right19 = false;

                if (!up19) {
                    otraUp = 19; //Numero de carta
                    card19.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up19) {
                            if (right19) {
                                setImage(19, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(19, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card20:
                aux = solucio.get(20); //Numero de carta
                setImage(20, aux);     //Numero de carta

                final boolean up20, right20; // ha de ser final??

                if (otraUp == 0) up20 = false;
                else up20 = true;
                if (solucio.get(otraUp) == aux) right20 = true;
                else right20 = false;

                if (!up20) {
                    otraUp = 20; //Numero de carta
                    card20.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up20) {
                            if (right20) {
                                setImage(20, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(20, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card21:
                aux = solucio.get(21); //Numero de carta
                setImage(21, aux);     //Numero de carta

                final boolean up21, right21; // ha de ser final??

                if (otraUp == 0) up21 = false;
                else up21 = true;
                if (solucio.get(otraUp) == aux) right21 = true;
                else right21 = false;

                if (!up21) {
                    otraUp = 21; //Numero de carta
                    card21.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up21) {
                            if (right21) {
                                setImage(21, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(21, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card22:
                aux = solucio.get(22); //Numero de carta
                setImage(22, aux);     //Numero de carta

                final boolean up22, right22; // ha de ser final??

                if (otraUp == 0) up22 = false;
                else up22 = true;
                if (solucio.get(otraUp) == aux) right22 = true;
                else right22 = false;

                if (!up22) {
                    otraUp = 22; //Numero de carta
                    card22.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up22) {
                            if (right22) {
                                setImage(22, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(22, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card23:
                aux = solucio.get(23); //Numero de carta
                setImage(23, aux);     //Numero de carta

                final boolean up23, right23; // ha de ser final??

                if (otraUp == 0) up23 = false;
                else up23 = true;
                if (solucio.get(otraUp) == aux) right23 = true;
                else right23 = false;

                if (!up23) {
                    otraUp = 23; //Numero de carta
                    card23.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up23) {
                            if (right23) {
                                setImage(23, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(23, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card24:
                aux = solucio.get(24); //Numero de carta
                setImage(24, aux);     //Numero de carta

                final boolean up24, right24; // ha de ser final??

                if (otraUp == 0) up24 = false;
                else up24 = true;
                if (solucio.get(otraUp) == aux) right24 = true;
                else right24 = false;

                if (!up24) {
                    otraUp = 24; //Numero de carta
                    card24.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up24) {
                            if (right24) {
                                setImage(24, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(24, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card25:
                aux = solucio.get(25); //Numero de carta
                setImage(25, aux);     //Numero de carta

                final boolean up25, right25; // ha de ser final??

                if (otraUp == 0) up25 = false;
                else up25 = true;
                if (solucio.get(otraUp) == aux) right25 = true;
                else right25 = false;

                if (!up25) {
                    otraUp = 25; //Numero de carta
                    card25.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up25) {
                            if (right25) {
                                setImage(25, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(25, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card26:
                aux = solucio.get(26); //Numero de carta
                setImage(26, aux);     //Numero de carta

                final boolean up26, right26; // ha de ser final??

                if (otraUp == 0) up26 = false;
                else up26 = true;
                if (solucio.get(otraUp) == aux) right26 = true;
                else right26 = false;

                if (!up26) {
                    otraUp = 26; //Numero de carta
                    card26.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up26) {
                            if (right26) {
                                setImage(26, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(26, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card27:
                aux = solucio.get(27); //Numero de carta
                setImage(27, aux);     //Numero de carta

                final boolean up27, right27; // ha de ser final??

                if (otraUp == 0) up27 = false;
                else up27 = true;
                if (solucio.get(otraUp) == aux) right27 = true;
                else right27 = false;

                if (!up27) {
                    otraUp = 27; //Numero de carta
                    card27.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up27) {
                            if (right27) {
                                setImage(27, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(27, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card28:
                aux = solucio.get(28); //Numero de carta
                setImage(28, aux);     //Numero de carta

                final boolean up28, right28; // ha de ser final??

                if (otraUp == 0) up28 = false;
                else up28 = true;
                if (solucio.get(otraUp) == aux) right28 = true;
                else right28 = false;

                if (!up28) {
                    otraUp = 28; //Numero de carta
                    card28.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up28) {
                            if (right28) {
                                setImage(28, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(28, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card29:
                aux = solucio.get(29); //Numero de carta
                setImage(29, aux);     //Numero de carta

                final boolean up29, right29; // ha de ser final??

                if (otraUp == 0) up29 = false;
                else up29 = true;
                if (solucio.get(otraUp) == aux) right29 = true;
                else right29 = false;

                if (!up29) {
                    otraUp = 29; //Numero de carta
                    card29.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up29) {
                            if (right29) {
                                setImage(29, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(29, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card30:
                aux = solucio.get(30); //Numero de carta
                setImage(30, aux);     //Numero de carta

                final boolean up30, right30; // ha de ser final??

                if (otraUp == 0) up30 = false;
                else up30 = true;
                if (solucio.get(otraUp) == aux) right30 = true;
                else right30 = false;

                if (!up30) {
                    otraUp = 30; //Numero de carta
                    card30.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up30) {
                            if (right30) {
                                setImage(30, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(30, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card31:
                aux = solucio.get(31); //Numero de carta
                setImage(31, aux);     //Numero de carta

                final boolean up31, right31; // ha de ser final??

                if (otraUp == 0) up31 = false;
                else up31 = true;
                if (solucio.get(otraUp) == aux) right31 = true;
                else right31 = false;

                if (!up31) {
                    otraUp = 31; //Numero de carta
                    card31.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }


                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up31) {
                            if (right31) {
                                setImage(31, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(31, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card32:
                aux = solucio.get(32); //Numero de carta
                setImage(32, aux);     //Numero de carta

                final boolean up32, right32; // ha de ser final??

                if (otraUp == 0) up32 = false;
                else up32 = true;
                if (solucio.get(otraUp) == aux) right32 = true;
                else right32 = false;

                if (!up32) {
                    otraUp = 32; //Numero de carta
                    card32.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up32) {
                            if (right32) {
                                setImage(32, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(32, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card33:
                aux = solucio.get(33); //Numero de carta
                setImage(33, aux);     //Numero de carta

                final boolean up33, right33; // ha de ser final??

                if (otraUp == 0) up33 = false;
                else up33 = true;
                if (solucio.get(otraUp) == aux) right33 = true;
                else right33 = false;

                if (!up33) {
                    otraUp = 33; //Numero de carta
                    card33.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up33) {
                            if (right33) {
                                setImage(33, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(33, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card34:
                aux = solucio.get(34); //Numero de carta
                setImage(34, aux);     //Numero de carta

                final boolean up34, right34; // ha de ser final??

                if (otraUp == 0) up34 = false;
                else up34 = true;
                if (solucio.get(otraUp) == aux) right34 = true;
                else right34 = false;

                if (!up34) {
                    otraUp = 34; //Numero de carta
                    card34.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up34) {
                            if (right34) {
                                setImage(34, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(34, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card35:
                aux = solucio.get(35); //Numero de carta
                setImage(35, aux);     //Numero de carta

                final boolean up35, right35; // ha de ser final??

                if (otraUp == 0) up35 = false;
                else up35 = true;
                if (solucio.get(otraUp) == aux) right35 = true;
                else right35 = false;

                if (!up35) {
                    otraUp = 35; //Numero de carta
                    card35.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up35) {
                            if (right35) {
                                setImage(35, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(35, 18); //Numero de carta
                                setImage(otraUp, 18);
                                otraUp = 0;
                            }
                            BlockCards(false);
                        }
                    }
                }, 2000);
                break;
            case R.id.card36:
                aux = solucio.get(36); //Numero de carta
                setImage(36, aux);     //Numero de carta

                final boolean up36, right36; // ha de ser final??

                if (otraUp == 0) up36 = false;
                else up36 = true;
                if (solucio.get(otraUp) == aux) right36 = true;
                else right36 = false;

                if (!up36) {
                    otraUp = 36; //Numero de carta
                    card36.setClickable(false);
                } else {
                    ++punt;
                    intents.setText(punt.toString());
                    BlockCards(true);
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (up36) {
                            if (right36) {
                                setImage(36, -1);        //Numero de carta
                                setImage(otraUp, -1);
                                otraUp = 0;
                                ++acertadas;
                                if (acertadas == 18) fiPartida(Memory2Activity.this);
                            } else {
                                setImage(36, 18); //Numero de carta
                                setImage(otraUp, 18);
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
        if (userFunctions.getPunt2(context, un) == 0) userFunctions.setPunt2(context, un, punt);
        else if (punt < userFunctions.getPunt2(context, un))
            userFunctions.setPunt2(context, un, punt);


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

            card17.setClickable(false);
            card18.setClickable(false);
            card19.setClickable(false);
            card20.setClickable(false);
            card21.setClickable(false);
            card22.setClickable(false);
            card23.setClickable(false);
            card24.setClickable(false);
            card25.setClickable(false);
            card26.setClickable(false);
            card27.setClickable(false);
            card28.setClickable(false);
            card29.setClickable(false);
            card30.setClickable(false);
            card31.setClickable(false);
            card32.setClickable(false);
            card33.setClickable(false);
            card34.setClickable(false);
            card35.setClickable(false);
            card36.setClickable(false);

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

            if (card17.getAlpha() == 1f) card17.setClickable(true);
            if (card18.getAlpha() == 1f) card18.setClickable(true);
            if (card19.getAlpha() == 1f) card19.setClickable(true);
            if (card20.getAlpha() == 1f) card20.setClickable(true);
            if (card21.getAlpha() == 1f) card21.setClickable(true);
            if (card22.getAlpha() == 1f) card22.setClickable(true);
            if (card23.getAlpha() == 1f) card23.setClickable(true);
            if (card24.getAlpha() == 1f) card24.setClickable(true);
            if (card25.getAlpha() == 1f) card25.setClickable(true);
            if (card26.getAlpha() == 1f) card26.setClickable(true);
            if (card27.getAlpha() == 1f) card27.setClickable(true);
            if (card28.getAlpha() == 1f) card28.setClickable(true);
            if (card29.getAlpha() == 1f) card29.setClickable(true);
            if (card30.getAlpha() == 1f) card30.setClickable(true);
            if (card31.getAlpha() == 1f) card31.setClickable(true);
            if (card32.getAlpha() == 1f) card32.setClickable(true);
            if (card33.getAlpha() == 1f) card33.setClickable(true);
            if (card34.getAlpha() == 1f) card34.setClickable(true);
            if (card35.getAlpha() == 1f) card35.setClickable(true);
            if (card36.getAlpha() == 1f) card36.setClickable(true);

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
                    animacio.flipImage(getDrawable(imagen), card6);
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
                    animacio.flipImage(getDrawable(imagen), card16);
                    break;
                case 17:
                    animacio.flipImage(getDrawable(imagen), card17);
                    break;
                case 18:
                    animacio.flipImage(getDrawable(imagen), card18);
                    break;
                case 19:
                    animacio.flipImage(getDrawable(imagen), card19);
                    break;
                case 20:
                    animacio.flipImage(getDrawable(imagen), card20);
                    break;
                case 21:
                    animacio.flipImage(getDrawable(imagen), card21);
                    break;
                case 22:
                    animacio.flipImage(getDrawable(imagen), card22);
                    break;
                case 23:
                    animacio.flipImage(getDrawable(imagen), card23);
                    break;
                case 24:
                    animacio.flipImage(getDrawable(imagen), card24);
                    break;
                case 25:
                    animacio.flipImage(getDrawable(imagen), card25);
                    break;
                case 26:
                    animacio.flipImage(getDrawable(imagen), card26);
                    break;
                case 27:
                    animacio.flipImage(getDrawable(imagen), card27);
                    break;
                case 28:
                    animacio.flipImage(getDrawable(imagen), card28);
                    break;
                case 29:
                    animacio.flipImage(getDrawable(imagen), card29);
                    break;
                case 30:
                    animacio.flipImage(getDrawable(imagen), card30);
                    break;
                case 31:
                    animacio.flipImage(getDrawable(imagen), card31);
                    break;
                case 32:
                    animacio.flipImage(getDrawable(imagen), card32);
                    break;
                case 33:
                    animacio.flipImage(getDrawable(imagen), card33);
                    break;
                case 34:
                    animacio.flipImage(getDrawable(imagen), card34);
                    break;
                case 35:
                    animacio.flipImage(getDrawable(imagen), card35);
                    break;
                case 36:
                    animacio.flipImage(getDrawable(imagen), card36);
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
                case 17:
                    card17.setAlpha(0.1f);
                    card17.setClickable(false);
                    break;
                case 18:
                    card18.setAlpha(0.1f);
                    card18.setClickable(false);
                    break;
                case 19:
                    card19.setAlpha(0.1f);
                    card19.setClickable(false);
                    break;
                case 20:
                    card20.setAlpha(0.1f);
                    card20.setClickable(false);
                    break;
                case 21:
                    card21.setAlpha(0.1f);
                    card21.setClickable(false);
                    break;
                case 22:
                    card22.setAlpha(0.1f);
                    card22.setClickable(false);
                    break;
                case 23:
                    card23.setAlpha(0.1f);
                    card23.setClickable(false);
                    break;
                case 24:
                    card24.setAlpha(0.1f);
                    card24.setClickable(false);
                    break;
                case 25:
                    card25.setAlpha(0.1f);
                    card25.setClickable(false);
                    break;
                case 26:
                    card26.setAlpha(0.1f);
                    card26.setClickable(false);
                    break;
                case 27:
                    card27.setAlpha(0.1f);
                    card27.setClickable(false);
                    break;
                case 28:
                    card28.setAlpha(0.1f);
                    card28.setClickable(false);
                    break;
                case 29:
                    card29.setAlpha(0.1f);
                    card29.setClickable(false);
                    break;
                case 30:
                    card30.setAlpha(0.1f);
                    card30.setClickable(false);
                    break;
                case 31:
                    card31.setAlpha(0.1f);
                    card31.setClickable(false);
                    break;
                case 32:
                    card32.setAlpha(0.1f);
                    card32.setClickable(false);
                    break;
                case 33:
                    card33.setAlpha(0.1f);
                    card33.setClickable(false);
                    break;
                case 34:
                    card34.setAlpha(0.1f);
                    card34.setClickable(false);
                    break;
                case 35:
                    card35.setAlpha(0.1f);
                    card35.setClickable(false);
                    break;
                case 36:
                    card36.setAlpha(0.1f);
                    card36.setClickable(false);
                    break;
            }
        }
    }

    private int getImage(int image) {
        //19 CARTES, (0->18)
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
                    return R.mipmap.be_jedi;
                case 9:
                    return R.mipmap.be_jedi_laser;
                case 10:
                    return R.mipmap.be_jedi_power;
                case 11:
                    return R.mipmap.fucking_jedi;
                case 12:
                    return R.mipmap.jedi_govna;
                case 13:
                    return R.mipmap.jedi_master;
                case 14:
                    return R.mipmap.love_jedi;
                case 15:
                    return R.mipmap.never_be_jedi;
                case 16:
                    return R.mipmap.yoda_keep;
                case 17:
                    return R.mipmap.jedi_will_be;
                case 18:
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
                    return R.mipmap.anna;
                case 9:
                    return R.mipmap.birras;
                case 10:
                    return R.mipmap.cara_joaquin;
                case 11:
                    return R.mipmap.joaquin_down;
                case 12:
                    return R.mipmap.joaquin_pro;
                case 13:
                    return R.mipmap.oscar;
                case 14:
                    return R.mipmap.purus_black;
                case 15:
                    return R.mipmap.vinyes;
                case 16:
                    return R.mipmap.vinyes_cuerpo;
                case 17:
                    return R.mipmap.carles_bad;
                case 18:
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
                    return R.mipmap.coffee_jedi;
                case 9:
                    return R.mipmap.festafib;
                case 10:
                    return R.mipmap.cartell_c3po;
                case 11:
                    return R.mipmap.cartell_yoda;
                case 12:
                    return R.mipmap.cartell_r2d2;
                case 13:
                    return R.mipmap.cartell_boba;
                case 14:
                    return R.mipmap.cartell_soldier;
                case 15:
                    return R.mipmap.cartell_imperio;
                case 16:
                    return R.mipmap.cartell_vader;
                case 17:
                    return R.mipmap.cartell_hulk;
                case 18:
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



