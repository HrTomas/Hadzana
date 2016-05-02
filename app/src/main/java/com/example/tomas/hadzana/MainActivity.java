package com.example.tomas.hadzana;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomas.hadzana.AddPlayers.AddPlayersToDatabase;
import com.example.tomas.hadzana.Game.BeforGame.BeforGameActivity;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.History.HistoryListOfMatches;
import com.example.tomas.hadzana.Summary.Summary;

public class MainActivity extends AppCompatActivity {

    Context CTX = this;
    DatabaseOperations DB = new DatabaseOperations(CTX);

    RelativeLayout WelcomScreen, Turnaments, NewTurnament, MenuForTurnament;
    LinearLayout ListOfTurnaments;

    String TurnamentPK;

    Integer TextSizeForScreens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.activity_main);
            TextSizeForScreens = 30;
        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_main_mobile);
//            TextSizeForScreens = 15;
        }

        Button AddPlayersGlobal, FreeMatch, Turnament, MatchHistoryGlobal, SummaryGlobal, GameOptionsGlobal;
        AddPlayersGlobal = (Button) findViewById(R.id.AddPlayersGlobal);
        AddPlayersGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddPlayersToDatabase.class);
                i.putExtra("TurnamentPK", "0");
                startActivity(i);
            }
        });
        FreeMatch = (Button) findViewById(R.id.FreeMatch);
        FreeMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BeforGameActivity.class);
                i.putExtra("TurnamentPK", "0");
                startActivity(i);
            }
        });
        Turnament = (Button) findViewById(R.id.Turnament);
//        if (config.smallestScreenWidthDp >= 600)
            Turnament.getLayoutParams().width = config.screenWidthDp/3;
        Turnament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTurnaments();
            }
        });
        MatchHistoryGlobal = (Button) findViewById(R.id.MatchHistoryGlobal);
        MatchHistoryGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HistoryListOfMatches.class);
                i.putExtra("TurnamentPK", "0");
                i.putExtra("DateTime", "0");
                startActivity(i);
            }
        });
        SummaryGlobal = (Button) findViewById(R.id.SummaryGlobal);
        SummaryGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Summary.class);
                i.putExtra("TurnamentPK", "0");
                startActivity(i);
            }
        });
        GameOptionsGlobal = (Button) findViewById(R.id.GameOptionsGlobal);
        GameOptionsGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), GameOptions.class);
//                i.putExtra("TurnamentPK", "0");
//                startActivity(i);
                Toast.makeText(getBaseContext(), "Coming soon.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                ChangeLayout();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ShowTurnaments() {
        WelcomScreen = (RelativeLayout) findViewById(R.id.WelcomScreen);
        WelcomScreen.setVisibility(View.GONE);
        Turnaments  = (RelativeLayout) findViewById(R.id.Turnaments);
        Turnaments.setVisibility(View.VISIBLE);
        ListOfTurnaments = (LinearLayout) findViewById(R.id.ListOfTurnaments);
        ListOfTurnaments.removeAllViews();
        Button buttonNewTurnament = (Button) findViewById(R.id.buttonNewTurnament);
        buttonNewTurnament.setVisibility(View.VISIBLE);
        Cursor CR = DB.getInformationTurnament(DB, "");
        if(CR.moveToFirst()) {
            do {
                final TextView OneTurnament = new TextView(CTX);
                OneTurnament.setTextSize(TextSizeForScreens);
                OneTurnament.setText(CR.getString(0));
                OneTurnament.setGravity(Gravity.CENTER);
                OneTurnament.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClearColors();
                        OneTurnament.setBackgroundColor(0x79125cfc);
                        NewActivity(OneTurnament.getText().toString());
                    }
                });
                ListOfTurnaments.addView(OneTurnament);
            }while(CR.moveToNext());
        } else
            NewTurnament();
        buttonNewTurnament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTurnament();
            }
        });
    }

    public void NewTurnament() {
        ClearColors();
        NewTurnament = (RelativeLayout) findViewById(R.id.NewTurnament);
        NewTurnament.setVisibility(View.VISIBLE);
        MenuForTurnament = (RelativeLayout) findViewById(R.id.MenuForTurnament);
        MenuForTurnament.setVisibility(View.GONE);
        final Button buttonNewTurnament = (Button) findViewById(R.id.buttonNewTurnament);
        buttonNewTurnament.setVisibility(View.GONE);

        final Cursor CR = DB.getInformationTurnament(DB, "");
        final EditText TurnamentName = (EditText) findViewById(R.id.TurnamentName);
        TurnamentName.setText("");
        final Button InsertTurnament = (Button) findViewById(R.id.InsertTurnament);
        InsertTurnament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckForIfUniq(TurnamentName.getText().toString())) {
                    if(CR.moveToLast()) {
                        DB.putInformationTurnament(DB, TurnamentName.getText().toString(), String.valueOf(Integer.parseInt(CR.getString(1)) + 1));
                        DB.PutInformationSummaryGame(DB, String.valueOf(Integer.parseInt(CR.getString(1)) + 1));
                        DB.putInformationGameOption(DB, "6", "1", "", "", "", "", "", "", "", "", "", "","", "", "", "", "", "", "", "", "", "", "", "", "", String.valueOf(Integer.parseInt(CR.getString(1)) + 1));
                    } else {
                        DB.putInformationTurnament(DB, TurnamentName.getText().toString(), "1");
                        DB.PutInformationSummaryGame(DB, "1");
                        DB.putInformationGameOption(DB, "6", "1", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "1");
                    }
                    NewTurnament.setVisibility(View.GONE);
                    buttonNewTurnament.setVisibility(View.VISIBLE);
                    ShowTurnaments();
                } else
                    Toast.makeText(getBaseContext(), "Nazov turnaja musi byt unikatny.", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void ClearColors() {
        ListOfTurnaments = (LinearLayout) findViewById(R.id.ListOfTurnaments);
        for(int i = 0; i < ListOfTurnaments.getChildCount(); i++) {
            ListOfTurnaments.getChildAt(i).setBackgroundColor(0);
        }
    }

    public boolean CheckForIfUniq(String TurnamentName) {
        Cursor CR = DB.getInformationTurnament(DB, "");
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(0).equals(TurnamentName))
                    return false;
            }while(CR.moveToNext());
        }
        return true;
    }

    public void NewActivity(String TurnamentName) {

        Cursor CR = DB.getInformationTurnament(DB, " WHERE " + TableData.TableInfo.TurnamentName + " == " + "\"" + TurnamentName + "\"");
        CR.moveToLast();
        TurnamentPK = CR.getString(1);

        NewTurnament = (RelativeLayout) findViewById(R.id.NewTurnament);
        NewTurnament.setVisibility(View.GONE);
        MenuForTurnament = (RelativeLayout) findViewById(R.id.MenuForTurnament);
        MenuForTurnament.setVisibility(View.VISIBLE);

        Button ADD_PLAYERS, START_MATCH, GAME_OPTIONS, MATCH_STATISTICS, QUIT;
        ADD_PLAYERS = (Button) findViewById(R.id.AddPlayers);
        START_MATCH = (Button) findViewById(R.id.StartMatch);
        GAME_OPTIONS = (Button) findViewById(R.id.GameOptions);
        MATCH_STATISTICS = (Button) findViewById(R.id.MatchStatistics);
        QUIT = (Button) findViewById(R.id.Quit);

        ADD_PLAYERS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddPlayersToDatabase.class);
                i.putExtra("TurnamentPK", TurnamentPK);
                startActivity(i);
//                startActivity(new Intent(GameMenu.this, AddPlayersToDatabase.class));
            }
        });

        START_MATCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BeforGameActivity.class);
                i.putExtra("TurnamentPK", TurnamentPK);
                startActivity(i);
            }
        });

        GAME_OPTIONS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), GameOptions.class);
//                i.putExtra("TurnamentPK", TurnamentPK);
//                startActivity(i);
                Toast.makeText(getBaseContext(), "Coming soon.", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(GameMenu.this, GameOptions.class));
            }
        });

        MATCH_STATISTICS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HistoryListOfMatches.class);
                i.putExtra("TurnamentPK", TurnamentPK);
                startActivity(i);
//                startActivity(new Intent(GameMenu.this, HistoryGameFragmentController.class));
            }
        });

        QUIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Summary.class);
                i.putExtra("TurnamentPK", TurnamentPK);
                startActivity(i);
//                startActivity(new Intent(GameMenu.this, Summary.class));
            }
        });
    }

    public void ChangeLayout() {
        WelcomScreen = (RelativeLayout) findViewById(R.id.WelcomScreen);
        Turnaments = (RelativeLayout) findViewById(R.id.Turnaments);
        if(WelcomScreen.getVisibility() == View.VISIBLE)
            finish();
        else {
            if(Turnaments.getVisibility() == View.VISIBLE) {
                Turnaments.setVisibility(View.GONE);
                WelcomScreen.setVisibility(View.VISIBLE);
            }
        }
    }
}
