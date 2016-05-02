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

public class SummaryDefensive extends Fragment {

    Context CX;
    DatabaseOperations DB;
    TextView TimeInDefensive, PredictionOfDefensive, CountOfPlus, CountOfMinus, RatioPlusMinus, CountOfFauls, CountOfYellowCards, CountOfRedCards;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View SummaryDefensive = inflater.inflate(R.layout.summary_defensive, container, false);
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

        ShowDefensiveValues(SummaryDefensive);

        return SummaryDefensive;
    }

    public void ShowDefensiveValues(View SummaryDefensive) {
        TimeInDefensive = (TextView) SummaryDefensive.findViewById(R.id.TimeInDefensive);
        PredictionOfDefensive = (TextView) SummaryDefensive.findViewById(R.id.PredictionOfDefensive);
        CountOfPlus = (TextView) SummaryDefensive.findViewById(R.id.CountOfPlus);
        CountOfMinus = (TextView) SummaryDefensive.findViewById(R.id.CountOfMinus);
        RatioPlusMinus = (TextView) SummaryDefensive.findViewById(R.id.RatioPlusMinus);
        CountOfFauls = (TextView) SummaryDefensive.findViewById(R.id.CountOfFauls);
        CountOfYellowCards = (TextView) SummaryDefensive.findViewById(R.id.CountOfYellowCards);
        CountOfRedCards = (TextView) SummaryDefensive.findViewById(R.id.CountOfRedCards);

        TimeInDefensive.setText(CountTime(Constants.TIME_IN_DEFENSIVE));
        if(Integer.parseInt(CountValue(Constants.COUNT_OF_DEFENSIVE)) != 0)
            PredictionOfDefensive.setText(String.valueOf(Integer.parseInt(CountValue(Constants.FINAL_SCORE))*100/
                    Integer.parseInt(CountValue(Constants.COUNT_OF_DEFENSIVE))) + "%");
        else
            PredictionOfDefensive.setText("0%");
        CountOfPlus.setText(CountValuePlayers(Constants.PLUS));
        CountOfMinus.setText(CountValuePlayers(Constants.MINUS));
        if(Integer.parseInt(CountOfPlus.getText().toString()) != 0 || Integer.parseInt(CountOfMinus.getText().toString()) != 0)
            RatioPlusMinus.setText(String.valueOf((Integer.parseInt(CountOfPlus.getText().toString())*100)/(Integer.parseInt(CountOfPlus.getText().toString())+Integer.parseInt(CountOfPlus.getText().toString()))) + "%");
        else
            RatioPlusMinus.setText("0%");
        CountOfFauls.setText(CountValuePlayers(Constants.FAUL));
        CountOfYellowCards.setText(CountValuePlayers(Constants.YELLOW_CARD));
        CountOfRedCards.setText(CountValuePlayers(Constants.RED_CARD));
    }

    public String CountValue(Integer ColumnForCount) {
        Integer FinalCount = 0;
        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        if(CR.moveToFirst()) {
            do {
                if(ColumnForCount == Constants.FINAL_SCORE)
                    FinalCount = FinalCount + Integer.parseInt(CR.getString(ColumnForCount).split(":")[1]);
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

    public String CountTime(Integer ColumnForCount) {
        String FinalCountMinutes = "0";
        String FinalCountSeconds = "0";
        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        if(CR.moveToFirst()) {
            do {
                FinalCountMinutes = String.valueOf(Integer.parseInt(FinalCountMinutes) + Integer.parseInt(CR.getString(ColumnForCount).split(":")[0]));
                FinalCountSeconds = String.valueOf(Integer.parseInt(FinalCountSeconds) + Integer.parseInt(CR.getString(ColumnForCount).split(":")[1]));
                if(Integer.parseInt(FinalCountSeconds) > 59) {
                    FinalCountMinutes = String.valueOf(Integer.parseInt(FinalCountMinutes) + 1);
                    FinalCountSeconds = String.valueOf(Integer.parseInt(FinalCountSeconds) - 60);
                }
            }while(CR.moveToNext());
        }
        return FinalCountMinutes + ":" + FinalCountSeconds;
    }
}