package com.example.tomas.hadzana.History;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HistoryTabsAdapter extends FragmentStatePagerAdapter {
    private int TOTAL_TABS = 3;
    private String ha;
    public HistoryTabsAdapter(FragmentManager fm, String TurnamentPK) {
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
                Fragment HistoryGame = new HistoryGame();
                HistoryGame.setArguments(TurnamentPK);
                return HistoryGame;

            case 1:
                Fragment HistoryPlayers = new HistoryPlayers();
                HistoryPlayers.setArguments(TurnamentPK);
                return HistoryPlayers;

            case 2:
                Fragment HistoryGoalkeepers = new HistoryGoalkeepers();
                HistoryGoalkeepers.setArguments(TurnamentPK);
                return HistoryGoalkeepers;
        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }

}
