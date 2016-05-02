package com.example.tomas.hadzana.Summary;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class SummaryPowerplay extends Fragment {

    Context CX;
    DatabaseOperations DB;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    TextView CountOfPowerPlays, WinPowerPlays, PredictionOfensive;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View SummaryPowerplay = inflater.inflate(R.layout.summary_powerplay, container, false);
        TurnamentPK = getArguments().getString("TurnamentPK");

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

        ShowValues(SummaryPowerplay);

        return SummaryPowerplay;
    }

    public void ShowValues(View SummaryPowerPlay) {
        CountOfPowerPlays = (TextView) SummaryPowerPlay.findViewById(R.id.CountOfPowerPlays);
        WinPowerPlays = (TextView) SummaryPowerPlay.findViewById(R.id.WinPowerPlays);
        PredictionOfensive = (TextView) SummaryPowerPlay.findViewById(R.id.PredictionOfensive);

        CountOfPowerPlays.setText(CountValue(Constants.POWERPLAYS_COUNT));
        WinPowerPlays.setText(CountValue(Constants.POWERPLAYS_WIN));
        if(!CountOfPowerPlays.getText().toString().equals("0"))
            PredictionOfensive.setText(String.valueOf((Integer.parseInt(WinPowerPlays.getText().toString())*100)/
                    Integer.parseInt(CountOfPowerPlays.getText().toString())) + "%");
        else
            PredictionOfensive.setText("0%");

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
}