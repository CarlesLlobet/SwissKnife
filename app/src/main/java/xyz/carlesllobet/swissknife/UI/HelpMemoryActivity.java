package xyz.carlesllobet.swissknife.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import xyz.carlesllobet.swissknife.R;

/**
 * Created by JEDI on 10/08/2015.
 */
public class HelpMemoryActivity extends BaseActivity {

    private TextView wiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_memory);

        wiki = (TextView) findViewById(R.id.moreInfo);
        wiki.setMovementMethod(LinkMovementMethod.getInstance());

        setTitle(R.string.tituloAyuda);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
