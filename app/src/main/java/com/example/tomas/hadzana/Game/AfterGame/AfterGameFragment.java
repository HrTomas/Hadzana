package com.example.tomas.hadzana.Game.AfterGame;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;
import com.example.tomas.hadzana.StringConstants;

public class AfterGameFragment extends Fragment {

    Context CX;
    DatabaseOperations DB;
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

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
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
            ConditionGame = "";
            ConditionGameOptions = "";
        } else {
            ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK;
            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
            ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        }

        GameStatisticTable(AfterGame);
        ShowGameRecord(AfterGame);
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
        if(Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[0])*60 + Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[1]) != 0)
            OFENSIVE_TIME_PERCENT.setText(String.valueOf((((Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.TIME_IN_OFENSIVE).split(":")[1])) * 100) /
                    ((Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[1]))) + "%");
        else
            OFENSIVE_TIME_PERCENT.setText("0%");
        DEFENSIVE_TIME.setText(CR.getString(Constants.TIME_IN_DEFENSIVE));
        if(Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[0])*60 + Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[1]) != 0)
            DEFENSIVE_TIME_PERCENT.setText(String.valueOf((((Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.TIME_IN_DEFENSIVE).split(":")[1])) * 100) /
                    ((Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[0]) * 60) + Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[1]))) + "%");
        else
            DEFENSIVE_TIME_PERCENT.setText("0%");

        SHOTS.setText(String.valueOf(CR.getInt(Constants.GAME_COUNT_OF_SHOTS) + CR.getInt(Constants.GAME_COUNT_OF_MISS_SHOTS) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER_SHOTS)));
        PERCENTS_SHOTS.setText(CR.getString(Constants.GAME_PREDICTION_OF_SHOTS) + "%");
        MISS_BALLS.setText(CR.getString(Constants.GAME_COUNT_OF_MISS_BALLS));
        FAULS.setText(CR.getString(Constants.GAME_COUNT_OF_FAULS));
        YELLOW_CARDS.setText(CR.getString(Constants.GAME_COUNT_OF_YELLOW_CARDS));
        RED_CARDS.setText(CR.getString(Constants.GAME_COUNT_OF_RED_CARDS));
        PLUS_COUNT.setText(CR.getString(Constants.GAME_COUNT_OF_PLUS));
        MINUS_COUNT.setText(CR.getString(Constants.GAME_COUNT_OF_MINUS));
    }

    public void ShowGameRecord(View AfterGame) {
        LinearLayout GameRecordLayout = (LinearLayout) AfterGame.findViewById(R.id.GameRecordLayout);
        String ConcatenateInformation = "";
        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        CR.moveToLast();
        TextView GameReplay = new TextView(CX);
        GameReplay.setTextSize(TextSizeForScreens);
        GameReplay.setText(CR.getString(Constants.GAME_REPLAY));
        GameRecordLayout.addView(GameReplay);
        for(int i = CR.getString(Constants.GAME_REPORT).split("&").length - 1; i >= 0;i--) {
            if(CR.getString(Constants.GAME_REPORT).equals(""))
                break;
            ConcatenateInformation = StringConstants.IN_TIME + CR.getString(Constants.GAME_REPORT).split("&")[i].split("#")[4];
            if(CR.getString(Constants.GAME_REPORT).split("&")[i].split("#")[0].equals("Player")) {
                ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER + CR.getString(Constants.GAME_REPORT).split("&")[i].split("#")[1];
                switch(CR.getString(Constants.GAME_REPORT).split("&")[i].split("#")[3]) {
                    case "3" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_SHOTS_GOAL;
                        break;
                    case "4" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_SHOTS_MISS;
                        break;
                    case "5" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_SHOTS_GOALKEEPER;
                        break;
                    case "6" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_LOOSE_BALL;
                        break;
                    case "7" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_REACHED_FAUL_SHOT;
                        break;
                    case "8" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_PLUS;
                        break;
                    case "9" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_MINUS;
                        break;
                    case "10" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_FAUL;
                        break;
                    case "11" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_YELLOW_CARD;
                        break;
                    case "12" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_RED_CARD;
                        break;
                    case "13" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_REACHED_BALL;
                        break;
                    case "17" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_FAUL_SHOT_GOAL;
                        break;
                    case "18" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.PLAYER_FAUL_SHOT_MISS;
                        break;
                    default: break;
                }
            }
            else {
                ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER + CR.getString(Constants.GAME_REPORT).split("&")[i].split("#")[1];
                switch(CR.getString(Constants.GAME_REPORT).split("&")[i].split("#")[3]) {
                    case "3" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_CATCH;
                        break;
                    case "4" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_LONG_PASS_GOOD;
                        break;
                    case "5" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_LONG_PASS_BAD;
                        break;
                    case "6" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_SHORT_PASS_GOOD;
                        break;
                    case "7" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_SHORT_PASS_BAD;
                        break;
                    case "8" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_CATCH_FAUL_SHOT;
                        break;
                    case "9" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_MISS_FAUL_SHOT;
                        break;
                    case "10" :
                        ConcatenateInformation = ConcatenateInformation + StringConstants.GOALKEEPER_GOAL;
                        break;
                    default: break;
                }
            }
            TextView OneRecord = new TextView(CX);
            OneRecord.setTextSize(TextSizeForScreens);
            OneRecord.setText(ConcatenateInformation);
            GameRecordLayout.addView(OneRecord);
        }
    }

}