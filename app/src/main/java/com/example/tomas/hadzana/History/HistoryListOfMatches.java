package com.example.tomas.hadzana.History;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class HistoryListOfMatches extends AppCompatActivity {

    Context CX = this;
    DatabaseOperations DB = new DatabaseOperations(CX);

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    Integer TextSizeForScreens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list_of_matches);
        TextSizeForScreens = 30;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TurnamentPK = extras.getString("TurnamentPK");
        }
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

        DeleteSurplusData();
        ShowMatches();

    }

    public void ShowMatches() {
        final RelativeLayout TableOfMatches = (RelativeLayout) findViewById(R.id.TableOfMatches);
        TableOfMatches.setVisibility(View.VISIBLE);
        LinearLayout TeamNames = (LinearLayout) findViewById(R.id.TeamNames);
        LinearLayout FinalScore = (LinearLayout) findViewById(R.id.FinalScore);
        LinearLayout HomeOrOut = (LinearLayout) findViewById(R.id.HomeOrOut);
        LinearLayout DateTimeOfMatch = (LinearLayout) findViewById(R.id.DateTimeOfMatch);
        Integer i = 0;
        final Cursor CR = DB.getInformationGame(DB, ConditionGame);
        if (CR.moveToFirst()) {
            do {
                final TextView OneRowDateTime = new TextView(CX);
                OneRowDateTime.setTextSize(TextSizeForScreens);
                OneRowDateTime.setPadding(0, 0, 24, 0);
                if(i % 2 != 0)
                    OneRowDateTime.setBackgroundColor(0xC2C2C2FF);
                else
                    OneRowDateTime.setBackgroundColor(0);
                OneRowDateTime.setText(CR.getString(Constants.DATETIME_PK_GAME));
                OneRowDateTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.DATE_TIME_FOR_HISTORY = OneRowDateTime.getText().toString();
                        Intent i = new Intent(getApplicationContext(), HistoryGameFragment.class);
                        i.putExtra("TurnamentPK", TurnamentPK);
                        startActivity(i);
                    }
                });
                OneRowDateTime.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        DB.deleteGameByDateTime(DB, OneRowDateTime.getText().toString());
                        return true;
                    }
                });
                DateTimeOfMatch.addView(OneRowDateTime);
                final TextView OneRowTeamNames = new TextView(CX);
                OneRowTeamNames.setTextSize(TextSizeForScreens);
                OneRowTeamNames.setPadding(0, 0, 24, 0);
                if(i % 2 != 0)
                    OneRowTeamNames.setBackgroundColor(0xC2C2C2FF);
                else
                    OneRowTeamNames.setBackgroundColor(0);
                OneRowTeamNames.setGravity(Gravity.CENTER);
                OneRowTeamNames.setText(CR.getString(Constants.TEAM_NAME) + " vs " + CR.getString(Constants.NAME_OF_OPONENT));
                OneRowTeamNames.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.DATE_TIME_FOR_HISTORY = OneRowDateTime.getText().toString();
                        Intent i = new Intent(getApplicationContext(), HistoryGameFragment.class);
                        i.putExtra("TurnamentPK", TurnamentPK);
                        startActivity(i);
                    }
                });
                TeamNames.addView(OneRowTeamNames);
                final TextView OneRowFinalScore = new TextView(CX);
                OneRowFinalScore.setTextSize(TextSizeForScreens);
                OneRowFinalScore.setPadding(0, 0, 24, 0);
                if(i % 2 != 0)
                    OneRowFinalScore.setBackgroundColor(0xC2C2C2FF);
                else
                    OneRowFinalScore.setBackgroundColor(0);
                OneRowFinalScore.setText(CR.getString(Constants.FINAL_SCORE));
                OneRowFinalScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.DATE_TIME_FOR_HISTORY = OneRowDateTime.getText().toString();
                        Intent i = new Intent(getApplicationContext(), HistoryGameFragment.class);
                        i.putExtra("TurnamentPK", TurnamentPK);
                        startActivity(i);
                    }
                });
                FinalScore.addView(OneRowFinalScore);
                final TextView OneRowTeamHomeOrOut = new TextView(CX);
                OneRowTeamHomeOrOut.setTextSize(TextSizeForScreens);
                OneRowTeamHomeOrOut.setPadding(0, 0, 24, 0);
                if(i % 2 != 0)
                    OneRowTeamHomeOrOut.setBackgroundColor(0xC2C2C2FF);
                else
                    OneRowTeamHomeOrOut.setBackgroundColor(0);
                if(CR.getString(Constants.GAME_PLAYED_HOME).equals("true"))
                    OneRowTeamHomeOrOut.setText("Doma");
                else
                    OneRowTeamHomeOrOut.setText("Vonku");
                OneRowTeamHomeOrOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.DATE_TIME_FOR_HISTORY = OneRowDateTime.getText().toString();
                        Intent i = new Intent(getApplicationContext(), HistoryGameFragment.class);
                        i.putExtra("TurnamentPK", TurnamentPK);
                        startActivity(i);
                    }
                });
                HomeOrOut.addView(OneRowTeamHomeOrOut);
                i++;
            } while (CR.moveToNext());
        }
    }

    public void DeleteSurplusData() {
        boolean Match = false;
        String DateTime = "";
        Cursor CRG = DB.getInformationGame(DB, ConditionGame);
        if (CRG.moveToFirst()) {
            do {
                if (CRG.getString(Constants.MATCH_SUCCESSFULLY_COMPLETED).equals("false") || CRG.getString(Constants.TURNAMENT_GAME_PK).equals("0")) {
                    DateTime = CRG.getString(Constants.DATETIME_PK_GAME);
                    DB.deleteGame(DB, CRG.getString(Constants.PK_NUMBER_GAME));
                }
            } while (CRG.moveToNext());
        }
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers);
//        if (CR.moveToFirst())
//            do {
//                if (CRG.moveToFirst()) {
//                    do {
//                        if (CR.getString(0).split("#")[13].equals(CRG.getString(7)) || CR.getString(0).split("#")[15].equals(CRG.getString(7)))
//                            Match = true;
//                    } while (CRG.moveToNext());
//                }
//                if (!Match)
//                    DB.deleteFromTableOfPlayers(DB, CR.getString(0), TurnamentPK, DateTime);
//                Match = false; /*TODO: maybe i solve problem with deleting game*/
//            } while (CR.moveToNext());

    }

}
