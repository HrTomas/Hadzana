package com.example.tomas.hadzana.History;


import android.content.Context;
import android.content.res.Configuration;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class HistoryGame extends Fragment {

    Context CX;
    DatabaseOperations DB;
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionHistoryPlayer, ConditionHistoryGoalkeeper, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    View AfterGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AfterGame = inflater.inflate(R.layout.after_game, container, false);
        TurnamentPK = getArguments().getString("TurnamentPK");

        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 600) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TextSizeForScreens = 30;
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_summary_mobile);
//            TextSizeForScreens = 15;
//        }

        CX = getActivity();
        DB = new DatabaseOperations(CX);

        if(TurnamentPK.equals("0")) {
            ConditionPlayer = "";
            ConditionGoalkeeper = "";
            ConditionHistoryPlayer = "";
            ConditionHistoryGoalkeeper = "";
            ConditionGame = " WHERE " + TableData.TableInfo.DateTimePKGame + " == " + "\"" + Constants.DATE_TIME_FOR_HISTORY + "\"";
            ConditionGameOptions = "";
        } else {
            ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK;
            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK + " AND " + TableData.TableInfo.DateTimePKGame + " == " + "\"" + Constants.DATE_TIME_FOR_HISTORY + "\"";
            ConditionHistoryPlayer = " WHERE " + TableData.TableInfo.HistoryDatetimaPlayer + " == " + "\"" + Constants.DATE_TIME_FOR_HISTORY + "\"";
            ConditionHistoryGoalkeeper = " WHERE " + TableData.TableInfo.HistoryDatetimeGoalkeeper + " == " + "\"" + Constants.DATE_TIME_FOR_HISTORY + "\"";
            ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        }

        GameStatisticTable(AfterGame);

        return AfterGame;
    }

    public void GameStatisticTable(View AfterGame) {

        TextView OFENSIVE_TIME, DEFENSIVE_TIME, OFENSIVE_TIME_PERCENT, DEFENSIVE_TIME_PERCENT, SHOTS, PERCENTS_SHOTS, MISS_BALLS, FAULS, YELLOW_CARDS, RED_CARDS, PLUS_COUNT, MINUS_COUNT, SCORE, HALF_TIME_SCORE, NAMES_OF_TEAMS;
        OFENSIVE_TIME = (TextView) AfterGame.findViewById(R.id.OfensiveTime);
        DEFENSIVE_TIME = (TextView) AfterGame.findViewById(R.id.DefensiveTime);
        OFENSIVE_TIME_PERCENT = (TextView) AfterGame.findViewById(R.id.OfensiveTimePercent);
        DEFENSIVE_TIME_PERCENT = (TextView) AfterGame.findViewById(R.id.DefensiveTimePercent);
        SHOTS = (TextView) AfterGame.findViewById(R.id.CountOfShots);
        PERCENTS_SHOTS = (TextView) AfterGame.findViewById(R.id.PercentsShots);
        MISS_BALLS = (TextView) AfterGame.findViewById(R.id.MissBals);
        FAULS = (TextView) AfterGame.findViewById(R.id.FaulCount);
        YELLOW_CARDS = (TextView) AfterGame.findViewById(R.id.YellowCardsCount);
        RED_CARDS = (TextView) AfterGame.findViewById(R.id.RedCardsCount);
        PLUS_COUNT = (TextView) AfterGame.findViewById(R.id.PlusCount);
        MINUS_COUNT = (TextView) AfterGame.findViewById(R.id.MinusCount);
        SCORE = (TextView) AfterGame.findViewById(R.id.Score);
        HALF_TIME_SCORE = (TextView) AfterGame.findViewById(R.id.HalftimeScore);
        NAMES_OF_TEAMS = (TextView) AfterGame.findViewById(R.id.TeamNames);

        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        CR.moveToLast();
        NAMES_OF_TEAMS.setText(CR.getString(Constants.TEAM_NAME) + " vs " + CR.getString(Constants.NAME_OF_OPONENT));
        HALF_TIME_SCORE.setText(CR.getString(Constants.HALF_TIME_SCORE));
        SCORE.setText(CR.getString(Constants.FINAL_SCORE));
        OFENSIVE_TIME.setText(CR.getString(Constants.TIME_IN_OFENSIVE));
//        if(Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[0])*60 + Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[1]) != 0)
//            OFENSIVE_TIME_PERCENT.setText(String.valueOf((((Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[1])) * 100) /
//                    ((Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[1]))) + "%");
//        else
//            OFENSIVE_TIME_PERCENT.setText("0%");
        DEFENSIVE_TIME.setText(CR.getString(Constants.TIME_IN_DEFENSIVE));
//        if(Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[0])*60 + Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[1]) != 0)
//            DEFENSIVE_TIME_PERCENT.setText(String.valueOf((((Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[1])) * 100) /
//                    ((Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[1]))) + "%");
//        else
//            DEFENSIVE_TIME_PERCENT.setText("0%");
        SHOTS.setText(String.valueOf(CR.getInt(Constants.GAME_COUNT_OF_SHOTS) + CR.getInt(Constants.GAME_COUNT_OF_MISS_SHOTS) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER_SHOTS)));
        PERCENTS_SHOTS.setText(CR.getString(Constants.GAME_PREDICTION_OF_SHOTS) + "%");
        MISS_BALLS.setText(CR.getString(Constants.GAME_COUNT_OF_MISS_BALLS));
        FAULS.setText(CR.getString(Constants.GAME_COUNT_OF_FAULS));
        YELLOW_CARDS.setText(CR.getString(Constants.GAME_COUNT_OF_YELLOW_CARDS));
        RED_CARDS.setText(CR.getString(Constants.GAME_COUNT_OF_RED_CARDS));
        PLUS_COUNT.setText(CR.getString(Constants.GAME_COUNT_OF_PLUS));
        MINUS_COUNT.setText(CR.getString(Constants.GAME_COUNT_OF_MINUS));
    }

}