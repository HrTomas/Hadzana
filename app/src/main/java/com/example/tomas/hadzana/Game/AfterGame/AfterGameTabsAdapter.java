package com.example.tomas.hadzana.Game.AfterGame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class AfterGameTabsAdapter extends FragmentStatePagerAdapter {
    private int TOTAL_TABS = 3;
    private String ha;
    public AfterGameTabsAdapter(FragmentManager fm, String TurnamentPK) {
        super(fm);
        // TODO Auto-generated constructor stub
        ha = TurnamentPK;
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        Bundle TurnamentPK = new Bundle();
        TurnamentPK.putString("TurnamentPK", ha);
        switch (index) {
            case 0:
                Fragment AfterGameFragment = new AfterGameFragment();
                AfterGameFragment.setArguments(TurnamentPK);
                return AfterGameFragment;

            case 1:
                Fragment AfterGamePlayers = new AfterGamePlayers();
                AfterGamePlayers.setArguments(TurnamentPK);
                return AfterGamePlayers;

            case 2:
                Fragment AfterGameGoalkeepers = new AfterGameGoalkeepers();
                AfterGameGoalkeepers.setArguments(TurnamentPK);
                return AfterGameGoalkeepers;
        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }

}


