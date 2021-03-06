package xyz.carlesllobet.swissknife.Domain;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.carlesllobet.swissknife.UI.RankingFragment;


public class PagerAdapter extends FragmentPagerAdapter {


    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "4 x 4", "6 x 6" };
    private Context context;
    Fragment tab = null;

    //creadora
    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    //crea las tabs, siempre tiene que retornar con el numero de tabs que queremos mostrar
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //Lanza el fragment asociado con el numero de tab
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                tab = new RankingFragment(true);
                break;
            case 1:
                tab = new RankingFragment(false);
                break;
        }
        return tab;
    }

    //pone el nombre en cada tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
