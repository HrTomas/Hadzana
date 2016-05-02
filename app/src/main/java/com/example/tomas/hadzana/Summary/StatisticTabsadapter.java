package com.example.tomas.hadzana.Summary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tomas.hadzana.Summary.SummaryDefensive;
import com.example.tomas.hadzana.Summary.SummaryGame;
import com.example.tomas.hadzana.Summary.SummaryGoalkeepers;
import com.example.tomas.hadzana.Summary.SummaryOfensive;
import com.example.tomas.hadzana.Summary.SummaryPlayers;
import com.example.tomas.hadzana.Summary.SummaryPowerplay;

public class StatisticTabsadapter  extends FragmentStatePagerAdapter {

    private int TOTAL_TABS = 6;
    private String ha;
    public StatisticTabsadapter(FragmentManager fm, String tomas) {
        super(fm);
        // TODO Auto-generated constructor stub
        ha = tomas;
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        Bundle TurnamentPK = new Bundle();
        TurnamentPK.putString("TurnamentPK", ha);
        switch (index) {
            case 0:
                Fragment SummaryGame = new SummaryGame();
                SummaryGame.setArguments(TurnamentPK);
                return SummaryGame;

            case 1:
                Fragment SummaryPowerplay = new SummaryPowerplay();
                SummaryPowerplay.setArguments(TurnamentPK);
                return SummaryPowerplay;

            case 2:
                Fragment SummaryOfensive = new SummaryOfensive();
                SummaryOfensive.setArguments(TurnamentPK);
                return SummaryOfensive;

            case 3:
                Fragment SummaryDefensive = new SummaryDefensive();
                SummaryDefensive.setArguments(TurnamentPK);
                return SummaryDefensive;

            case 4:
                Fragment SummaryPlayers = new SummaryPlayers();
                SummaryPlayers.setArguments(TurnamentPK);
                return SummaryPlayers;

            case 5:
                Fragment SummaryGoalkeepers = new SummaryGoalkeepers();
                SummaryGoalkeepers.setArguments(TurnamentPK);
                return SummaryGoalkeepers;
        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }
}
