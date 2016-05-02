package com.example.tomas.hadzana.Game.BeforGame;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Configuration;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.Game.PlayGround;
import com.example.tomas.hadzana.R;

public class BeforGameActivity extends AppCompatActivity {

    String  TurnamentPK = "0", ConditionPlayer, ConditionGoalkeeper, ConditionGame;
    Integer TextSizeForScreens;
    Context CX = this;
    DatabaseOperations DB = new DatabaseOperations(CX);
    LinearLayout ListMainFormation, ListRoster, GoalkeeperForStart, RosterGoalkeeper;
    EditText TeamName, OpponentName, TimeForMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_game);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.activity_before_game);
            TextSizeForScreens = 30;
        }
//        else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_add_players_to_database_mobile);
//            TextSizeForScreens = 15;
//        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TurnamentPK = extras.getString("TurnamentPK");
        }
        ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPKRoster + " == " + TurnamentPK;
        ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK;
        ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;

        ShowPlayers();
        ShowGoalkeepers();

        Button StartGame = (Button) findViewById(R.id.StartGame);
        StartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsEverythingSet())
                    StartMatch();
            }
        });
    }

    public void ShowPlayers() {
        ListMainFormation = (LinearLayout) findViewById(R.id.ListMainFormation);
        ListRoster = (LinearLayout) findViewById(R.id.ListRoster);
        Cursor CR = DB.GetInformationPlayer(DB, ConditionPlayer);
        if(CR.moveToFirst()) {
            do {
                final TextView OnePlayerForGame = new TextView(CX);
                OnePlayerForGame.setTextSize(TextSizeForScreens);
                OnePlayerForGame.setText(CR.getString(Constants.NUMBER) + " " + CR.getString(Constants.FIRST_NAME).charAt(0) + ". " + CR.getString(Constants.SECOND_NAME));
                OnePlayerForGame.setVisibility(View.GONE);
                final TextView OnePlayerRoster = new TextView(CX);
                OnePlayerRoster.setTextSize(TextSizeForScreens);
                OnePlayerRoster.setText(CR.getString(Constants.NUMBER) + " " + CR.getString(Constants.FIRST_NAME).charAt(0) + ". " + CR.getString(Constants.SECOND_NAME));
                OnePlayerRoster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!CountVisiblePlayers(ListMainFormation, 6)) {
                            OnePlayerRoster.setVisibility(View.GONE);
                            OnePlayerForGame.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(getBaseContext(), "Mate vybranych 6 hracou.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                ListRoster.addView(OnePlayerRoster);
                OnePlayerForGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnePlayerRoster.setVisibility(View.VISIBLE);
                        OnePlayerForGame.setVisibility(View.GONE);
                    }
                });
                ListMainFormation.addView(OnePlayerForGame);
            }while(CR.moveToNext());
        }
    }

    public void ShowGoalkeepers() {
        GoalkeeperForStart = (LinearLayout) findViewById(R.id.GoalkeeperForStart);
        RosterGoalkeeper = (LinearLayout) findViewById(R.id.RosterGoalkeeper);
        Cursor CR = DB.GetInformationGoalkeeper(DB, ConditionGoalkeeper);
        if(CR.moveToFirst()) {
            do {
                final TextView OnePlayerForGame = new TextView(CX);
                OnePlayerForGame.setTextSize(TextSizeForScreens);
                OnePlayerForGame.setText(CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER) + " " + CR.getString(Constants.GOALKEEPER_FIRST_NAME_ROSTER).charAt(0) + ". " + CR.getString(Constants.GOALKEEPER_SECOND_NAME_ROSTER));
                OnePlayerForGame.setVisibility(View.GONE);
                final TextView OnePlayerRoster = new TextView(CX);
                OnePlayerRoster.setTextSize(TextSizeForScreens);
                OnePlayerRoster.setText(CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER) + " " + CR.getString(Constants.GOALKEEPER_FIRST_NAME_ROSTER).charAt(0) + ". " + CR.getString(Constants.GOALKEEPER_SECOND_NAME_ROSTER));
                OnePlayerRoster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!CountVisiblePlayers(GoalkeeperForStart, 1)) {
                            OnePlayerRoster.setVisibility(View.GONE);
                            OnePlayerForGame.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(getBaseContext(), "Uz mate vybraneho brankara.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                RosterGoalkeeper.addView(OnePlayerRoster);
                OnePlayerForGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnePlayerRoster.setVisibility(View.VISIBLE);
                        OnePlayerForGame.setVisibility(View.GONE);
                    }
                });
                GoalkeeperForStart.addView(OnePlayerForGame);
            }while(CR.moveToNext());
        }
    }

    public Boolean CountVisiblePlayers(LinearLayout LayoutForCount, Integer Count) {
        Integer CountOfVisible = 0;
        for(int i = 0; i < LayoutForCount.getChildCount(); i++) {
            if(LayoutForCount.getChildAt(i).getVisibility() == View.VISIBLE)
                CountOfVisible++;
            if(CountOfVisible == Count)
                return true;
        }
        return false;
    }

    public Boolean IsEverythingSet() {
        ListMainFormation = (LinearLayout) findViewById(R.id.ListMainFormation);
        GoalkeeperForStart = (LinearLayout) findViewById(R.id.GoalkeeperForStart);
        EditText TeamName, OpponentName, TimeForMatch;
        TeamName = (EditText) findViewById(R.id.TeamName);
        OpponentName = (EditText) findViewById(R.id.OpponentName);
        TimeForMatch = (EditText) findViewById(R.id.TimeForMatch);
        if(CountVisiblePlayers(ListMainFormation, 6) && CountVisiblePlayers(GoalkeeperForStart, 1))
            if(!TeamName.getText().toString().equals("") && !OpponentName.getText().toString().equals("") && !TimeForMatch.getText().toString().equals(""))
                return true;
        return false;
    }

    public void StartMatch(){
        ListMainFormation = (LinearLayout) findViewById(R.id.ListMainFormation);
        GoalkeeperForStart = (LinearLayout) findViewById(R.id.GoalkeeperForStart);
        PutGame();
        Intent PlayeGround = new Intent(getApplicationContext(), PlayGround.class);
        Integer j = 0;
        for(int i = 0; i < ListMainFormation.getChildCount(); i++) {
            if((ListMainFormation).getChildAt(i).getVisibility() == View.VISIBLE) {
                j++;
                PlayeGround.putExtra(String.valueOf(j), ((TextView) (ListMainFormation).getChildAt(i)).getText().toString().split(" ")[0]);
            }
        }
        for(int i = 0; i < GoalkeeperForStart.getChildCount(); i++) {
            if((GoalkeeperForStart).getChildAt(i).getVisibility() == View.VISIBLE) {
                PlayeGround.putExtra("G", ((TextView) (GoalkeeperForStart).getChildAt(i)).getText().toString().split(" ")[0]);
            }
        }
        PlayeGround.putExtra("TurnamentPK", TurnamentPK);
        startActivity(PlayeGround);
        finish();
    }

    public void PutGame() {
        Integer PKGame = 0;
        EditText TeamName, OpponentName, TimeForMatch;
        TeamName = (EditText) findViewById(R.id.TeamName);
        OpponentName = (EditText) findViewById(R.id.OpponentName);
        TimeForMatch = (EditText) findViewById(R.id.TimeForMatch);
        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        if(CR.moveToFirst()) {
            CR.moveToLast();
            PKGame = CR.getInt(Constants.PK_NUMBER_GAME) + 1;
        }
        DB.putInformationGame(DB, TeamName.getText().toString(), OpponentName.getText().toString(), TimeForMatch.getText().toString(), PKGame, TurnamentPK);
    }
}
