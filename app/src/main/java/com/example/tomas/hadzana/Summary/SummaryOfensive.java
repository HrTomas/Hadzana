package com.example.tomas.hadzana.Summary;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class SummaryOfensive extends Fragment {

    Context CX;
    DatabaseOperations DB;
    TextView TimeInOfensive, CountOfOfensive, WinOfensiveCount, CountOfShots, PredictionOfShots, CountOf6mShots, PredictionOf6mShots, CountOf9mShots,
            PredictionOf9mShots, CountOfWingShots, PredictionOfWingShots, CountOf7mShots, PredictionOf7mShots, CountOfBrejks, PredictionOfBrejkShots;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View SummaryOfensive = inflater.inflate(R.layout.summary_ofensive, container, false);
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
            ConditionGame = "";
            ConditionGameOptions = "";
        } else {
            ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK;
            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
            ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        }

        ShowOfensiveValues(SummaryOfensive);

        return SummaryOfensive;
    }

    public void ShowOfensiveValues(View SummaryOfensive) {
        Integer Prediction = 0;

        TimeInOfensive = (TextView) SummaryOfensive.findViewById(R.id.TimeInOfensive);
        CountOfOfensive = (TextView) SummaryOfensive.findViewById(R.id.CountOfOfensive);
        WinOfensiveCount = (TextView) SummaryOfensive.findViewById(R.id.WinOfensiveCount);
        CountOfShots = (TextView) SummaryOfensive.findViewById(R.id.CountOfShots);
        PredictionOfShots = (TextView) SummaryOfensive.findViewById(R.id.PredictionOfShots);
        CountOf6mShots = (TextView) SummaryOfensive.findViewById(R.id.CountOf6mShots);
        PredictionOf6mShots = (TextView) SummaryOfensive.findViewById(R.id.PredictionOf6mShots);
        CountOf9mShots = (TextView) SummaryOfensive.findViewById(R.id.CountOf9mShots);
        PredictionOf9mShots = (TextView) SummaryOfensive.findViewById(R.id.PredictionOf9Shots);
        CountOfWingShots = (TextView) SummaryOfensive.findViewById(R.id.CountOfWingShots);
        PredictionOfWingShots = (TextView) SummaryOfensive.findViewById(R.id.PredictionOfWingShots);
        CountOf7mShots = (TextView) SummaryOfensive.findViewById(R.id.CountOf7mShots);
        PredictionOf7mShots = (TextView) SummaryOfensive.findViewById(R.id.PredictionOf7mShots);
        CountOfBrejks = (TextView) SummaryOfensive.findViewById(R.id.CountOfBrejks);
        PredictionOfBrejkShots = (TextView) SummaryOfensive.findViewById(R.id.PredictionOfBrejkShots);
        Cursor CR = DB.GetInformationSummaryGame(DB, " WHERE " + TableData.TableInfo.SummaryGameTurnamentPK + " == " + TurnamentPK);
        if(!CR.moveToLast())
            return;
        CountOfOfensive.setText(CountValue(Constants.COUNT_OF_OFENSIVE));
        if(!CountOfOfensive.getText().toString().equals("0"))
            WinOfensiveCount.setText(String.valueOf((Integer.parseInt(CountValue(Constants.FINAL_SCORE)) * 100) / Integer.parseInt(CountOfOfensive.getText().toString())) + "%");
        else
            WinOfensiveCount.setText("0%");
        CountOfShots.setText(String.valueOf(CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_SHOTS) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_SHOTS) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_SHOTS)));
        PredictionOfShots.setText(CR.getString(Constants.SUMMARY_GAME_PREDICTION_OF_SHOTS) + "%");
        CountOf6mShots.setText(String.valueOf(CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_6M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_6M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_6M)));
        PredictionOf6mShots.setText(CR.getString(Constants.SUMMARY_GAME_PREDICTION_OF_SHOTS_6M) + "%");
        CountOf9mShots.setText(String.valueOf(CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_9M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_9M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_9M)));
        PredictionOf9mShots.setText(CR.getString(Constants.SUMMARY_GAME_PREDICTION_OF_SHOTS_9M) + "%");
        CountOfWingShots.setText(String.valueOf(CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_WING) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_WING) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_WING)));
        PredictionOfWingShots.setText(CR.getString(Constants.SUMMARY_GAME_PREDICTION_OF_SHOTS_WING) + "%");
        CountOf7mShots.setText(String.valueOf(CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_7M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_7M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_7M)));
        PredictionOf7mShots.setText(CR.getString(Constants.SUMMARY_GAME_PREDICTION_OF_SHOTS_7M) + "%");
        CountOfBrejks.setText(String.valueOf(CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_BREJK) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_BREJK) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_BREJK)));
        PredictionOfBrejkShots.setText(CR.getString(Constants.SUMMARY_GAME_PREDICTION_OF_SHOTS_BREJK) + "%");
    }

    public String CountValue(Integer ColumnForCount) {
        Integer FinalCount = 0;
        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        if(CR.moveToFirst()) {
            do {
                if(ColumnForCount == Constants.FINAL_SCORE)
                    FinalCount = FinalCount + Integer.parseInt(CR.getString(ColumnForCount).split(":")[0]);
                else
                    FinalCount = FinalCount + Integer.parseInt(CR.getString(ColumnForCount));
            }while(CR.moveToNext());
        }
        return String.valueOf(FinalCount);
    }

    public String CountValuePlayers(Integer PositionInString) {
        Integer FinalCount = 0;
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(0).split("#").length > 26) // Goalkeeper have less values than player so i dont want count his values
//                    FinalCount = FinalCount + Integer.parseInt(CR.getString(0).split("#")[PositionInString]);
//            }while(CR.moveToNext());
//        }
        return String.valueOf(FinalCount);
    }
}