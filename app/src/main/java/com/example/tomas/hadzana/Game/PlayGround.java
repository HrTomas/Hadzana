package com.example.tomas.hadzana.Game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomas.hadzana.CountingFunctions;
import com.example.tomas.hadzana.Game.AfterGame.AfterGameSumary;
import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayGround extends ActionBarActivity {

    Context CTX = this;
    DatabaseOperations DB = new DatabaseOperations(CTX);

    Chronometer CENTRAL_TIME, OFENSIVE_TIME, DEFENSIVE_TIME;
    boolean CENTER_TIME_IS_STOPED = true, OFENSIVE_TIME_IS_STOPED = true, DEFENSIVE_TIME_IS_STOPED = true, HALF_TIME_TIMES_UP = false, MatchEnds = false, GamePlayedHome = true;
    long CENTER_TIME_WHEN_STOPED = 0 , OFENSIVE_TIME_WHEN_STOPED = 0, DEFENSIVE_TIME_WHEN_STOPED = 0, HALF_TIME = 0;
    String TIME_WHEN_COME = "00:00", HALFTIME_SCORE = "0:0", TimeWhenGoalkeeperCome = "00:00", TimeWhenPowerPlayShouldEnd = "00:00", TimeWhenWeekingShouldEnd = "00:00", TurnamentPK,
            ConditionForCreatingTable, ConditionForCreatingTableGoalkeeper, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionOptionsGame, ConditionLineUp, MINUTES = "", SECONDS = "", GameReplay = "", GameReport = "";

    Button LEFT_LEFT, LEFT, LEFT_CENTER, RIGHT_CENTER, RIGHT, RIGHT_RIGHT, GOALKEEPR_BUTTON;

    TextView TEAM_NAME, NAME_OF_OPONENT, COUNT_OF_OPONENTS_GOALS;

    RelativeLayout ofensiveLayout, defensiveLayout, LAYOUT_FOR_EVERYTHINK, RED_YELLOW_CARDS_LAYOUT, GOALKEEPER_VALUES_LAYOUT, NewGameInformation, LayoutForSetNewPlayers, RightSideOpponent,
            GoalkeeperRecoveredPosition, GoalkeeperCatchPosition, MissPosition, GoalPosition, ResultFaulShot;

    Integer CountingForColorGeneratorForPlayground = 0, CountingForColorGeneratorForBench = 0, ColorID = 0, CountOfOfensive = 0, CountOfWeakening = 0, GoalWhileWeakening = 0,
            PowerPlaysWin = 0, CountOfFaulsOpponent = 0, CountOfMissShotsOpponent = 0, PKGame = 0, AreWeInPowerPlay = 0, ReachedGoalWhilePowerPlay = 0, ReachedGoalWhileWeakening = 0,
            TextSizeForScreens, TextSizeRostersMobile = 17, CountOfDefensive = 0, IDForGameRecord = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.activity_play_ground);
            TextSizeForScreens = 30;
            TextSizeRostersMobile = 30;
        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_play_ground_mobile);
//            TextSizeForScreens = 15;
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TurnamentPK = extras.getString("TurnamentPK");
        }
        ConditionForCreatingTable = " WHERE " + TableData.TableInfo.TurnamentPKRoster + " == " + TurnamentPK;
        ConditionForCreatingTableGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK;
        ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK;
        ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
        ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
        ConditionOptionsGame = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        ConditionLineUp = " WHERE " + TableData.TableInfo.TurnamentLineUpsPK + " == " + TurnamentPK;


        Button Tactics = (Button) findViewById(R.id.Tactics);
        Tactics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayGround.this, Tactics.class));
            }
        });
        GameReport = "";
        Button BackButton = (Button) findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackButtonHandler();
            }
        });
        ResetTableOfPlayers();
        SetInformation();
//        GameOptions();
        Game();
        Players();
    }

    /*TODO: set option for disable this button, this code works just fine*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[1]) != 0 && !TurnamentPK.equals("0"))
                    OpenDialog();
                else
                    finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void SetInformation() {
        LEFT_LEFT = (Button) findViewById(R.id.buttonLeftLeft);
        LEFT = (Button) findViewById(R.id.buttonLeft);
        LEFT_CENTER = (Button) findViewById(R.id.buttonLeftCenter);
        RIGHT_CENTER = (Button) findViewById(R.id.buttonRightCenter);
        RIGHT = (Button) findViewById(R.id.buttonRight);
        RIGHT_RIGHT = (Button) findViewById(R.id.buttonRightRight);
        GOALKEEPR_BUTTON = (Button) findViewById(R.id.buttonGoalKeeper);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            LEFT_LEFT.setText(extras.getString("1"));
            LEFT.setText(extras.getString("2"));
            LEFT_CENTER.setText(extras.getString("3"));
            RIGHT_CENTER.setText(extras.getString("4"));
            RIGHT.setText(extras.getString("5"));
            RIGHT_RIGHT.setText(extras.getString("6"));
            GOALKEEPR_BUTTON.setText(extras.getString("G"));
        }
        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        TEAM_NAME = (TextView) findViewById(R.id.HomeTeam);
        NAME_OF_OPONENT = (TextView) findViewById(R.id.HostTeam);
        CENTRAL_TIME = (Chronometer) findViewById(R.id.Time);
        if(CR.moveToLast()) {
            TEAM_NAME.setText(CR.getString(Constants.TEAM_NAME));
            NAME_OF_OPONENT.setText(CR.getString(Constants.NAME_OF_OPONENT));
            HALF_TIME = (Long.parseLong(CR.getString(Constants.MATCH_TIME)) * -60000)/2;
            PKGame = CR.getInt(Constants.PK_NUMBER_GAME);
        }
    }

    public void OpenDialog() {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Namerane hodnoty nebudu ulozene. Chcete odist?");
        dlgAlert.setTitle("Tlacitko \"Spat\" stlacene.");
        dlgAlert.setPositiveButton("Ano", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DB.deleteGame(DB, String.valueOf(PKGame));
                finish();
            }
        });
        dlgAlert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //stay on this layout
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Home:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.Out:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    public void GameRecord() {
        TextView HomeTeamCountOfGoals = (TextView) findViewById(R.id.HomeTeamCountOfGoals);
        TextView HostTeamCountOfGoals = (TextView) findViewById(R.id.HostTeamCountOfGoals);
        Integer Time = Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0])*60 + Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[1]);
        Cursor CR = DB.getInformationGameOption(DB, ConditionOptionsGame);
        if(CR.moveToFirst()) {
            CR.moveToLast();
            if(Time % (Integer.parseInt(CR.getString(1))*60) == 0 && Time != 0)
                GameReplay = GameReplay + HomeTeamCountOfGoals.getText().toString() + ":" + HostTeamCountOfGoals.getText().toString() + "#";

        }
    }

    public void Game() {
        final TextView COUNT_OF_HOME_TEAM_GOALS, Opponent;
        COUNT_OF_HOME_TEAM_GOALS = (TextView) findViewById(R.id.HomeTeamCountOfGoals);
        Opponent = (TextView) findViewById(R.id.HostTeam);
        Opponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OppoentValues();
            }
        });

        ofensiveLayout = (RelativeLayout) findViewById(R.id.RightSideOfensive);
        defensiveLayout = (RelativeLayout) findViewById(R.id.RightSideDefensive);
        COUNT_OF_OPONENTS_GOALS = (TextView) findViewById(R.id.HostTeamCountOfGoals);

        Button changeLayoutOfensive, changeLayoutDefensive;

        OFENSIVE_TIME = (Chronometer) findViewById(R.id.OfensiveTime);
        changeLayoutOfensive = (Button) findViewById(R.id.Ofensive);
        changeLayoutOfensive.setOnClickListener(new View.OnClickListener() {
            /* Set ofensive layout visible*/
            @Override
            public void onClick(View v) {
                if (PreGameOptions()) {
                    if (ofensiveLayout.getVisibility() == View.GONE) {
                        SetLayoutGone(FindWitchLayoutIsVisible());
                        ofensiveLayout.setVisibility(View.VISIBLE);
                        if (!CENTER_TIME_IS_STOPED) {
                            ChangeChronomomenter();
                        }
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Nastav cisla hracou, cas na polcas, meno tymu, meno supera", Toast.LENGTH_LONG).show();
                }
            }
        });

        changeLayoutDefensive = (Button) findViewById(R.id.Defensive);
        changeLayoutDefensive.setOnClickListener(new View.OnClickListener() {
            /*Set defensive layout visible*/
            @Override
            public void onClick(View v) {
                if (PreGameOptions()) {
                    if (defensiveLayout.getVisibility() == View.GONE) {
                        SetLayoutGone(FindWitchLayoutIsVisible());
                        defensiveLayout.setVisibility(View.VISIBLE);
                        if (!CENTER_TIME_IS_STOPED) {
                            ChangeChronomomenter();
                        }
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Nastav cisla hracou, cas na polcas, meno tymu, meno supera", Toast.LENGTH_LONG).show();
                }
            }
        });

        CENTRAL_TIME = (Chronometer) findViewById(R.id.Time);
        CENTRAL_TIME.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ChangeGameInformation(null, null, HALF_TIME);
                return true;
            }
        });
        CENTRAL_TIME.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (!TimeWhenPowerPlayShouldEnd.equals("00:00")) {
                    if (TimeWhenPowerPlayShouldEnd.split(":")[1].equals(CENTRAL_TIME.getText().toString().split(":")[1])) {
                        if (TimeWhenPowerPlayShouldEnd.split(":")[0].equals(CENTRAL_TIME.getText().toString().split(":")[0])) {
                            TimeWhenPowerPlayShouldEnd = "00:00";
                            AreWeInPowerPlay--;
                        }
                    }
                }
                if (!TimeWhenWeekingShouldEnd.equals("00:00")) {
                    if (TimeWhenWeekingShouldEnd.split(":")[1].equals(CENTRAL_TIME.getText().toString().split(":")[1])) {
                        if (TimeWhenWeekingShouldEnd.split(":")[0].equals(CENTRAL_TIME.getText().toString().split(":")[0])) {
                            TimeWhenWeekingShouldEnd = "00:00";
                            AreWeInPowerPlay++;
                        }
                    }
                }
                if ((CENTRAL_TIME.getBase() - SystemClock.elapsedRealtime()) < HALF_TIME) {
                    GameRecord();
                    if (HALF_TIME_TIMES_UP) {
                        if ((CENTRAL_TIME.getBase() - SystemClock.elapsedRealtime()) < HALF_TIME + HALF_TIME) {
                            CENTRAL_TIME.stop();
                            CENTER_TIME_IS_STOPED = true;
                            if (ofensiveLayout.getVisibility() == View.VISIBLE) {
                                //Ofensive
                                OFENSIVE_TIME.stop();
                                OFENSIVE_TIME_WHEN_STOPED = OFENSIVE_TIME.getBase() - SystemClock.elapsedRealtime();
                                OFENSIVE_TIME_IS_STOPED = true;
                            }

                            LEFT_LEFT = (Button) findViewById(R.id.buttonLeftLeft);
                            UpdatePlayrsTimeOnPlayground(LEFT_LEFT.getText().toString(), TIME_WHEN_COME);
                            LEFT = (Button) findViewById(R.id.buttonLeft);
                            UpdatePlayrsTimeOnPlayground(LEFT.getText().toString(), TIME_WHEN_COME);
                            LEFT_CENTER = (Button) findViewById(R.id.buttonLeftCenter);
                            UpdatePlayrsTimeOnPlayground(LEFT_CENTER.getText().toString(), TIME_WHEN_COME);
                            RIGHT_CENTER = (Button) findViewById(R.id.buttonRightCenter);
                            UpdatePlayrsTimeOnPlayground(RIGHT_CENTER.getText().toString(), TIME_WHEN_COME);
                            RIGHT = (Button) findViewById(R.id.buttonRight);
                            UpdatePlayrsTimeOnPlayground(RIGHT.getText().toString(), TIME_WHEN_COME);
                            RIGHT_RIGHT = (Button) findViewById(R.id.buttonRightRight);
                            UpdatePlayrsTimeOnPlayground(RIGHT_RIGHT.getText().toString(), TIME_WHEN_COME);
                            GOALKEEPR_BUTTON = (Button) findViewById(R.id.buttonGoalKeeper);
                            UpdatePlayrsTimeOnPlayground(GOALKEEPR_BUTTON.getText().toString(), TimeWhenGoalkeeperCome);

                            DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.MatchSuccessfullyCompleted, "true", TurnamentPK);
                            MatchEnds = true;
                            RelativeLayout SHOW_AFTER_GAME_SUMARY = (RelativeLayout) findViewById(R.id.ShowAfterMatchStatistic);
                            SHOW_AFTER_GAME_SUMARY.setVisibility(View.VISIBLE);
                            Button Statistic = (Button) findViewById(R.id.Statistic);
                            Statistic.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AfterGameSumary();
                                }
                            });
                        }
                    } else {
                        HALF_TIME_TIMES_UP = true;
                        CENTRAL_TIME.stop();
                        CENTER_TIME_IS_STOPED = true;
                        CENTER_TIME_WHEN_STOPED = HALF_TIME;
                        if (ofensiveLayout.getVisibility() == View.VISIBLE) {
                            //Ofensive
                            OFENSIVE_TIME.stop();
                            OFENSIVE_TIME_WHEN_STOPED = OFENSIVE_TIME.getBase() - SystemClock.elapsedRealtime();
                            OFENSIVE_TIME_IS_STOPED = true;
                        }
                        HALFTIME_SCORE = COUNT_OF_HOME_TEAM_GOALS.getText().toString() + ":" + COUNT_OF_OPONENTS_GOALS.getText().toString();
                        RelativeLayout SHOW_AFTER_GAME_SUMARY = (RelativeLayout) findViewById(R.id.ShowAfterMatchStatistic);
                        SHOW_AFTER_GAME_SUMARY.setVisibility(View.VISIBLE);
                        Button Statistic = (Button) findViewById(R.id.Statistic);
                        Statistic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AfterGameSumary();
                            }
                        });
                    }
                } else if (!HALF_TIME_TIMES_UP)
                    GameRecord();
            }
        });
        CENTRAL_TIME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreGameOptions()) {
                    if (CENTER_TIME_IS_STOPED) {
                        if(!MatchEnds) {
                            RelativeLayout SHOW_AFTER_GAME_SUMARY = (RelativeLayout) findViewById(R.id.ShowAfterMatchStatistic);
                            SHOW_AFTER_GAME_SUMARY.setVisibility(View.GONE);
                            CENTRAL_TIME.setBase(SystemClock.elapsedRealtime() + CENTER_TIME_WHEN_STOPED);
                            CENTRAL_TIME.start();
                            CENTER_TIME_IS_STOPED = false;
                            if (ofensiveLayout.getVisibility() == View.VISIBLE) {
                                //Ofensive
                                OFENSIVE_TIME.setBase(SystemClock.elapsedRealtime() + OFENSIVE_TIME_WHEN_STOPED);
                                OFENSIVE_TIME.start();
                                OFENSIVE_TIME_IS_STOPED = false;
                            }
                        }
                    } else {
                        CENTRAL_TIME.stop();
                        CENTER_TIME_WHEN_STOPED = CENTRAL_TIME.getBase() - SystemClock.elapsedRealtime();
                        CENTER_TIME_IS_STOPED = true;
                        if (ofensiveLayout.getVisibility() == View.VISIBLE) {
                            //Ofensive
                            OFENSIVE_TIME.stop();
                            OFENSIVE_TIME_WHEN_STOPED = OFENSIVE_TIME.getBase() - SystemClock.elapsedRealtime();
                            OFENSIVE_TIME_IS_STOPED = true;
                        }
                        RelativeLayout SHOW_AFTER_GAME_SUMARY = (RelativeLayout) findViewById(R.id.ShowAfterMatchStatistic);
                        SHOW_AFTER_GAME_SUMARY.setVisibility(View.VISIBLE);
                        Button Statistic = (Button) findViewById(R.id.Statistic);
                        Statistic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AfterGameSumary();
                            }
                        });
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Nastav cisla hracou, cas na polcas, meno tymu, meno supera", Toast.LENGTH_LONG).show();
                }
            }
        });

        TEAM_NAME = (TextView) findViewById(R.id.HomeTeam);
        TEAM_NAME.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ChangeGameInformation(null, TEAM_NAME, -1);
                return true;
            }
        });
        NAME_OF_OPONENT = (TextView) findViewById(R.id.HostTeam);
        NAME_OF_OPONENT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ChangeGameInformation(null, NAME_OF_OPONENT, -1);
                return true;
            }
        });
    }

    public void OppoentValues() {
        RightSideOpponent = (RelativeLayout) findViewById(R.id.RightSideOpponent);
        SetLayoutVisible(RightSideOpponent);
        final RelativeLayout ThisLayoutWasVisible = FindWitchLayoutIsVisible();
        SetLayoutGone(ThisLayoutWasVisible);
        Button buttonShotMissOpponent, buttonFaulOpponent;
        buttonShotMissOpponent = (Button) findViewById(R.id.buttonShotMissOpponent);
        buttonShotMissOpponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountOfMissShotsOpponent++;
                SetLayoutVisible(ThisLayoutWasVisible);
                SetLayoutGone(RightSideOpponent);
            }
        });
        buttonFaulOpponent = (Button) findViewById(R.id.buttonFaulOpponent);
        buttonFaulOpponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountOfFaulsOpponent++;
                if(Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + 2 < 10)
                    TimeWhenPowerPlayShouldEnd = "0" + String.valueOf(Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + 2) + ":" + CENTRAL_TIME.getText().toString().split(":")[1];
                else
                    TimeWhenPowerPlayShouldEnd = String.valueOf(Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + 2) + ":" + CENTRAL_TIME.getText().toString().split(":")[1];
                AreWeInPowerPlay++;
                SetLayoutVisible(ThisLayoutWasVisible);
                SetLayoutGone(RightSideOpponent);
            }
        });
    }

    public void Players() {

        LEFT_LEFT = (Button) findViewById(R.id.buttonLeftLeft);
        LEFT_LEFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                LEFT_LEFT.setBackgroundColor(0xff2fbaff);
                LEFT_LEFT.setHint("color");
                StatisticInformations(LEFT_LEFT.getText().toString());
            }
        });
        LEFT_LEFT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetDeafaultBackground();
                ChangePlayers();
                TIME_WHEN_COME = CENTRAL_TIME.getText().toString();
                return true;
            }
        });
        LEFT = (Button) findViewById(R.id.buttonLeft);
        LEFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                LEFT.setBackgroundColor(0xff2fbaff);
                LEFT.setHint("color");
                StatisticInformations(LEFT.getText().toString());
            }
        });
        LEFT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetDeafaultBackground();
                ChangePlayers();
                TIME_WHEN_COME = CENTRAL_TIME.getText().toString();
                return true;
            }
        });
        LEFT_CENTER = (Button) findViewById(R.id.buttonLeftCenter);
        LEFT_CENTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                LEFT_CENTER.setBackgroundColor(0xff2fbaff);
                LEFT_CENTER.setHint("color");
                StatisticInformations(LEFT_CENTER.getText().toString());
            }
        });
        LEFT_CENTER.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetDeafaultBackground();
                ChangePlayers();
                TIME_WHEN_COME = CENTRAL_TIME.getText().toString();
                return true;
            }
        });
        RIGHT_CENTER = (Button) findViewById(R.id.buttonRightCenter);
        RIGHT_CENTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                RIGHT_CENTER.setBackgroundColor(0xff2fbaff);
                RIGHT_CENTER.setHint("color");
                StatisticInformations(RIGHT_CENTER.getText().toString());
            }
        });
        RIGHT_CENTER.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetDeafaultBackground();
                ChangePlayers();
                TIME_WHEN_COME = CENTRAL_TIME.getText().toString();
                return true;
            }
        });
        RIGHT = (Button) findViewById(R.id.buttonRight);
        RIGHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                RIGHT.setBackgroundColor(0xff2fbaff);
                RIGHT.setHint("color");
                StatisticInformations(RIGHT.getText().toString());
            }
        });
        RIGHT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetDeafaultBackground();
                ChangePlayers();
                TIME_WHEN_COME = CENTRAL_TIME.getText().toString();
                return true;
            }
        });
        RIGHT_RIGHT = (Button) findViewById(R.id.buttonRightRight);
        RIGHT_RIGHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                RIGHT_RIGHT.setBackgroundColor(0xff2fbaff);
                RIGHT_RIGHT.setHint("color");
                StatisticInformations(RIGHT_RIGHT.getText().toString());
            }
        });
        RIGHT_RIGHT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetDeafaultBackground();
                ChangePlayers();
                TIME_WHEN_COME = CENTRAL_TIME.getText().toString();
                return true;
            }
        });
        GOALKEEPR_BUTTON = (Button) findViewById(R.id.buttonGoalKeeper);
        GOALKEEPR_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                GOALKEEPR_BUTTON.setBackgroundColor(0xff2fbaff);
                GoalKeeper(GOALKEEPR_BUTTON.getText().toString());
            }
        });
        GOALKEEPR_BUTTON.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetDeafaultBackground();
                ChangeGoalkeeper(GOALKEEPR_BUTTON);
                return true;
            }
        });
    }

    public void StatisticInformations(final String PLAYERS_NUMBER) {
        if(PLAYERS_NUMBER.matches("^[0-9]+$")) {
            final Cursor CR = DB.getInformationPlayers(DB, " where " + TableData.TableInfo.PlayerNumber + " = " + PLAYERS_NUMBER + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
            if(!CR.moveToFirst())
                return;
            final Button GOAL_ON_TARGET_BUTTON, SHOT_MISS_BUTTON, SHOT_GOALKEEPER_BUTTON, MISS_BALL_BUTTON, REACH_FAUL_SHOT_BUTTON, PLUS_BUTTON, MINUS_BUTTON, FAUL_BUTTON, ReachBall;
            final TextView COUNT_OF_HOME_TEAM_GOALS;
            COUNT_OF_HOME_TEAM_GOALS = (TextView) findViewById(R.id.HomeTeamCountOfGoals);
            //Ofensive
            GOAL_ON_TARGET_BUTTON = (Button) findViewById(R.id.buttonShotOnTarget);
            GOAL_ON_TARGET_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        if(!TimeWhenWeekingShouldEnd.equals("00:00") && (TimeWhenPowerPlayShouldEnd.equals("00:00") || AreWeInPowerPlay < 0))
                            GoalWhileWeakening++;
                        if(!TimeWhenPowerPlayShouldEnd.equals("00:00") && (TimeWhenWeekingShouldEnd.equals("00:00") || AreWeInPowerPlay > 0))
                            PowerPlaysWin++;
                        COUNT_OF_HOME_TEAM_GOALS.setText(String.valueOf((Integer.parseInt(COUNT_OF_HOME_TEAM_GOALS.getText().toString()) + 1)));
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.GoalOnTarget, String.valueOf(CR.getInt(Constants.GOAL_ON_TARGET) + 1), TurnamentPK);
                        MoreDetailGoal(PLAYERS_NUMBER);
                    }
                }
            });
            SHOT_MISS_BUTTON = (Button) findViewById(R.id.buttonShotMiss);
            SHOT_MISS_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.ShotMiss + "#" + "4#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.ShotMiss, String.valueOf(CR.getInt(Constants.SHOT_MISS) + 1), TurnamentPK);
                        SetLayoutGone(FindWitchLayoutIsVisible());
                        MoreDetailMiss(PLAYERS_NUMBER);
                    }
                }
            });
            SHOT_GOALKEEPER_BUTTON = (Button) findViewById(R.id.buttonShotToGoalkeeper);
            SHOT_GOALKEEPER_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.ShotGoalkeeper + "#" + "5#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.ShotGoalkeeper, String.valueOf(CR.getInt(Constants.SHOT_GOALKEEPER) + 1), TurnamentPK);
                        SetLayoutGone(FindWitchLayoutIsVisible());
                        MoreDetailMiss(PLAYERS_NUMBER);
                    }
                }
            });
            MISS_BALL_BUTTON = (Button) findViewById(R.id.buttonMissBall);
            MISS_BALL_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        SetDeafaultBackground();
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.MissBall + "#" + "6#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.MissBall, String.valueOf(CR.getInt(Constants.MISS_BALL) + 1), TurnamentPK);
                        SetLayoutGone(FindWitchLayoutIsVisible());
                        defensiveLayout.setVisibility(View.VISIBLE);
                        ChangeChronomomenter();
                        ShowInformation();
                    }
                }
            });
            REACH_FAUL_SHOT_BUTTON = (Button) findViewById(R.id.buttonReachFaulShot);
            REACH_FAUL_SHOT_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.ReachFaulShot + "#" + "7#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.ReachFaulShot, String.valueOf(CR.getInt(Constants.REACH_FAUL_SHOT) + 1), TurnamentPK);
                        MoreDetailFaulShot(PLAYERS_NUMBER);
                        ShowInformation();
                    }
                }
            });
            //Defensive
            PLUS_BUTTON = (Button) findViewById(R.id.buttonPlus);
            PLUS_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        SetDeafaultBackground();
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.Plus + "#" + "8#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.Plus, String.valueOf(CR.getInt(Constants.PLUS) + 1), TurnamentPK);
                        ShowInformation();
                    }
                }
            });
            MINUS_BUTTON = (Button) findViewById(R.id.buttonMinus);
            MINUS_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        SetDeafaultBackground();
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.Minus + "#" + "9#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.Minus, String.valueOf(CR.getInt(Constants.MINUS) + 1), TurnamentPK);
                        ShowInformation();
                    }
                }
            });
            final boolean FAUL_BUTTON_OFENSIVE;
            if(ofensiveLayout.getVisibility() == View.VISIBLE) {
                FAUL_BUTTON = (Button) findViewById(R.id.buttonFaulOfensive);
                FAUL_BUTTON_OFENSIVE = true;
            } else {
                FAUL_BUTTON = (Button) findViewById(R.id.buttonFaulDefensive);
                FAUL_BUTTON_OFENSIVE = false;
            }
            FAUL_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        SetDeafaultBackground();
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.Faul + "#" + "10#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.Faul, String.valueOf(CR.getInt(Constants.FAUL) + 1), TurnamentPK);
                        CountOfWeakening++;
                        if(Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + 2 < 10)
                            TimeWhenWeekingShouldEnd = "0" + String.valueOf(Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + 2) + ":" + CENTRAL_TIME.getText().toString().split(":")[1];
                        else
                            TimeWhenWeekingShouldEnd = String.valueOf(Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + 2) + ":" + CENTRAL_TIME.getText().toString().split(":")[1];
                        AreWeInPowerPlay--;
                        SetLayoutGone(FindWitchLayoutIsVisible());
                        defensiveLayout.setVisibility(View.VISIBLE);
                        ChangeChronomomenter();
                        ShowInformation();
                    }
                }
            });
            FAUL_BUTTON.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    RED_YELLOW_CARDS_LAYOUT = (RelativeLayout) findViewById(R.id.RedAndYellowCards);
                    if (ofensiveLayout.getVisibility() == View.VISIBLE) {
                        ofensiveLayout.setVisibility(View.GONE);
                    } else {
                        defensiveLayout.setVisibility(View.GONE);
                    }
                    RED_YELLOW_CARDS_LAYOUT.setVisibility(View.VISIBLE);

                    final Button YELLOW_CARD, RED_CARD;
                    YELLOW_CARD = (Button) findViewById(R.id.buttonYellowCard);
                    YELLOW_CARD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                                SetDeafaultBackground();
                                GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.YellowCard + "#" + "11#" + CENTRAL_TIME.getText().toString() + "#" +
                                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                                DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.YellowCard, String.valueOf(CR.getInt(Constants.YELLOW_CARD) + 1), TurnamentPK);
                                if (FAUL_BUTTON_OFENSIVE) {
                                    ofensiveLayout.setVisibility(View.VISIBLE);
                                } else {
                                    defensiveLayout.setVisibility(View.VISIBLE);
                                }
                                RED_YELLOW_CARDS_LAYOUT.setVisibility(View.GONE);
                                ShowInformation();
                            }
                        }
                    });
                    RED_CARD = (Button) findViewById(R.id.buttonRedCard);
                    RED_CARD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                                SetDeafaultBackground();
                                GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.RedCard + "#" + "12#" + CENTRAL_TIME.getText().toString() + "#" +
                                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                                DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.RedCard, String.valueOf(CR.getInt(Constants.RED_CARD) + 1), TurnamentPK);
                                if (FAUL_BUTTON_OFENSIVE) {
                                    ofensiveLayout.setVisibility(View.VISIBLE);
                                } else {
                                    defensiveLayout.setVisibility(View.VISIBLE);
                                }
                                RED_YELLOW_CARDS_LAYOUT.setVisibility(View.GONE);
                                ShowInformation();
                            }
                        }
                    });
                    return true;
                }
            });
            ReachBall = (Button) findViewById(R.id.ReachBall);
            ReachBall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckIfIsPickedSomePlayer(PLAYERS_NUMBER)) {
                        SetDeafaultBackground();
                        GameReport = "Player#" + PLAYERS_NUMBER + "#" + TableData.TableInfo.ReachBall + "#" + "13#" + CENTRAL_TIME.getText().toString() + "#" +
                                String.valueOf(IDForGameRecord++) + "&" + GameReport;
                        DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.ReachBall, String.valueOf(CR.getInt(Constants.REACH_BALL) + 1), TurnamentPK);
                        SetLayoutGone(FindWitchLayoutIsVisible());
                        ofensiveLayout.setVisibility(View.VISIBLE);
                        ChangeChronomomenter();
                        ShowInformation();
                    }
                }
            });
            DB.updatePlayersInformations(DB, PLAYERS_NUMBER, TableData.TableInfo.ValuesAreConvertedPlayer, "false", TurnamentPK);
        }
    }

    public void MoreDetailGoal(final String PlayersNumber) {

        final Cursor CR = DB.getInformationPlayers(DB, " where " + TableData.TableInfo.PlayerNumber + " = " + PlayersNumber + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        GoalkeeperRecoveredPosition = (RelativeLayout) findViewById(R.id.GoalkeeperRecoveredPosition);
        GoalkeeperCatchPosition = (RelativeLayout) findViewById(R.id.GoalkeeperCatchPosition);

        SetLayoutGone(FindWitchLayoutIsVisible());
        GoalPosition = (RelativeLayout) findViewById(R.id.GoalPosition);
        GoalPosition.setVisibility(View.VISIBLE);
        Button BrejkGoal, GoalWing, Goal6m, Goal7m, Goal9m;
        BrejkGoal = (Button) findViewById(R.id.BrejkGoal);
        BrejkGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameReport = "Player#" + PlayersNumber + "#" + TableData.TableInfo.BrejkGoal + "#" + "19#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.BrejkGoal, String.valueOf(CR.getInt(Constants.BREJK_GOAL) + 1), TurnamentPK);
                GoalPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        GoalWing = (Button) findViewById(R.id.GoalWing);
        GoalWing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameReport = "Player#" + PlayersNumber + "#" + TableData.TableInfo.GoalWing + "#" + "21#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.GoalWing, String.valueOf(CR.getInt(Constants.GOAL_WING) + 1), TurnamentPK);
                GoalPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Goal6m = (Button) findViewById(R.id.Goal6m);
        Goal6m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameReport = "Player#" + PlayersNumber + "#" + TableData.TableInfo.Goal6m + "#" + "23#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.Goal6m, String.valueOf(CR.getInt(Constants.GOAL_6M) + 1), TurnamentPK);
                GoalPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Goal7m = (Button) findViewById(R.id.Goal7m);
        Goal7m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameReport = "Player#" + PlayersNumber + "#" + TableData.TableInfo.Goal7m + "#" + "24#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.Goal7m, String.valueOf(CR.getInt(Constants.GOAL_7M) + 1), TurnamentPK);
                GoalPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Goal9m = (Button) findViewById(R.id.Goal9m);
        Goal9m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameReport = "Player#" + PlayersNumber + "#" + TableData.TableInfo.Goal9m + "#" + "25#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.Goal9m, String.valueOf(CR.getInt(Constants.GOAL_9M) + 1), TurnamentPK);
                GoalPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
    }

    public void MoreDetailMiss(final String PlayersNumber) {

        final Cursor CR = DB.getInformationPlayers(DB, " where " + TableData.TableInfo.PlayerNumber + " = " + PlayersNumber + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        SetLayoutGone(FindWitchLayoutIsVisible());
        MissPosition = (RelativeLayout) findViewById(R.id.MissPosition);
        MissPosition.setVisibility(View.VISIBLE);
        Button BrejkMiss, MissWing, Miss6m, Miss7m, Miss9m;
        BrejkMiss = (Button) findViewById(R.id.BrejkMiss);
        BrejkMiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.BrejkMiss, String.valueOf(CR.getInt(Constants.BREJK_MISS) + 1), TurnamentPK);
                MissPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        MissWing = (Button) findViewById(R.id.MissWing);
        MissWing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.MissWing, String.valueOf(CR.getInt(Constants.MISS_WING) + 1), TurnamentPK);
                MissPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Miss6m = (Button) findViewById(R.id.Miss6m);
        Miss6m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.Miss6m, String.valueOf(CR.getInt(Constants.MISS_6M) + 1), TurnamentPK);
                MissPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Miss7m = (Button) findViewById(R.id.Miss7m);
        Miss7m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.Miss7m, String.valueOf(CR.getInt(Constants.MISS_7M) + 1), TurnamentPK);
                MissPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Miss9m = (Button) findViewById(R.id.Miss9m);
        Miss9m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.Miss9m, String.valueOf(CR.getInt(Constants.MISS_9M) + 1), TurnamentPK);
                MissPosition.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
    }

    public void MoreDetailFaulShot(final String PlayersNumber) {

        final TextView COUNT_OF_HOME_TEAM_GOALS = (TextView) findViewById(R.id.HomeTeamCountOfGoals);

        final Cursor CR = DB.getInformationPlayers(DB, " where " + TableData.TableInfo.PlayerNumber + " = " + PlayersNumber + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        SetLayoutGone(FindWitchLayoutIsVisible());
        ResultFaulShot = (RelativeLayout) findViewById(R.id.ResultFaulShot);
        ResultFaulShot.setVisibility(View.VISIBLE);
        Button FaulShotGoal, FaulShotMiss;
        FaulShotGoal = (Button) findViewById(R.id.FaulShotGoal);
        FaulShotGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameReport = "Player#" + PlayersNumber + "#" + TableData.TableInfo.FaulShotGoal + "#" + "17#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.FaulShotGoal, String.valueOf(CR.getInt(Constants.FAUL_SHOT_GOAL) + 1), TurnamentPK);
                ResultFaulShot.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                COUNT_OF_HOME_TEAM_GOALS.setText(String.valueOf((Integer.parseInt(COUNT_OF_HOME_TEAM_GOALS.getText().toString()) + 1)));
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        FaulShotMiss = (Button) findViewById(R.id.FaulShotMiss);
        FaulShotMiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updatePlayersInformations(DB, PlayersNumber, TableData.TableInfo.FaulShotMiss, String.valueOf(CR.getInt(Constants.FAUL_SHOT_MISS) + 1), TurnamentPK);
                ResultFaulShot.setVisibility(View.GONE);
                defensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
    }

    public void ChangeGameInformation(final Button FOR_CHANGE_BUTTON, final TextView FOR_CHANGE_TEXT_VIEW, final long HALF_TIME_FOR_CHANGE) {
        if(Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) + Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[1]) == 0) {
            LAYOUT_FOR_EVERYTHINK = (RelativeLayout) findViewById(R.id.HideEverything);
            LAYOUT_FOR_EVERYTHINK.setVisibility(View.GONE);
            NewGameInformation = (RelativeLayout) findViewById(R.id.NewGameInformation);
            NewGameInformation.setVisibility(View.VISIBLE);
            final EditText EDITTEXT_NEW_INFORMATION;
            EDITTEXT_NEW_INFORMATION = (EditText) findViewById(R.id.editNewInformation);
            if (FOR_CHANGE_TEXT_VIEW != null && FOR_CHANGE_BUTTON == null)
                EDITTEXT_NEW_INFORMATION.setHint("Nazov tymu");
            else
                EDITTEXT_NEW_INFORMATION.setHint("Celkovy cas v min.");
            final Button CONFIRM_NEW_PLAYER_ON_THE_GROUND;
            CONFIRM_NEW_PLAYER_ON_THE_GROUND = (Button) findViewById(R.id.buttonConfirm);
            CONFIRM_NEW_PLAYER_ON_THE_GROUND.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FOR_CHANGE_TEXT_VIEW != null && FOR_CHANGE_BUTTON == null) {
                        FOR_CHANGE_TEXT_VIEW.setText(EDITTEXT_NEW_INFORMATION.getText().toString());
                    }
                    if (HALF_TIME_FOR_CHANGE != -1 && FOR_CHANGE_BUTTON == null && FOR_CHANGE_TEXT_VIEW == null) {
                        EDITTEXT_NEW_INFORMATION.setHint("Celkovy cas v min.");
                        if (EDITTEXT_NEW_INFORMATION.getText().toString().matches("^[0-9]+$"))
                            HALF_TIME = (Long.parseLong(EDITTEXT_NEW_INFORMATION.getText().toString()) * -60000)/2;
                        else
                            Toast.makeText(getBaseContext(), "Cas zadajte cislicami.", Toast.LENGTH_SHORT).show();
                    }
                    if(EDITTEXT_NEW_INFORMATION.getText().toString().equals(""))
                        Toast.makeText(getBaseContext(), "Hodnota nemoze byt prazdna.", Toast.LENGTH_LONG).show();
                    else {
                        EDITTEXT_NEW_INFORMATION.setText("");
                        LAYOUT_FOR_EVERYTHINK.setVisibility(View.VISIBLE);
                        NewGameInformation.setVisibility(View.GONE);
                    }
                    if(FOR_CHANGE_TEXT_VIEW == null && HALF_TIME_FOR_CHANGE == -1) {
                        FOR_CHANGE_BUTTON.setText(EDITTEXT_NEW_INFORMATION.getText().toString());
                    }
                }
            });
        }
    }

    public void ChangeChronomomenter() {
        SetDeafaultBackground();
        if(ofensiveLayout.getVisibility() == View.VISIBLE && !CENTER_TIME_IS_STOPED) {
            OFENSIVE_TIME.setBase(SystemClock.elapsedRealtime() + OFENSIVE_TIME_WHEN_STOPED);
            OFENSIVE_TIME.start();
            OFENSIVE_TIME_IS_STOPED = false;
            CountOfOfensive++;
        }
        if(defensiveLayout.getVisibility() == View.VISIBLE) {
            OFENSIVE_TIME.stop();
            OFENSIVE_TIME_WHEN_STOPED = OFENSIVE_TIME.getBase() - SystemClock.elapsedRealtime();
            OFENSIVE_TIME_IS_STOPED = true;
        }
    }

    public void GameOptions() {
        Cursor CR = DB.getInformationGameOption(DB, ConditionOptionsGame);
        CR.moveToLast();
        TextView TEAM_NAME = (TextView) findViewById(R.id.HomeTeam);
        if(!CR.getString(2).equals(""))
            TEAM_NAME.setText(CR.getString(2));
        if(!CR.getString(3).equals(""))
            HALF_TIME = (Long.parseLong(CR.getString(3)) * -60000) / 2;
        LEFT_LEFT = (Button) findViewById(R.id.buttonLeftLeft);
        LEFT = (Button) findViewById(R.id.buttonLeft);
        LEFT_CENTER = (Button) findViewById(R.id.buttonLeftCenter);
        RIGHT_CENTER = (Button) findViewById(R.id.buttonRightCenter);
        RIGHT = (Button) findViewById(R.id.buttonRight);
        RIGHT_RIGHT = (Button) findViewById(R.id.buttonRightRight);
        int i;
        if(CR.getString(0).equals("6")) {
            LEFT_LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(5).equals(""))
                LEFT_LEFT.setText(CR.getString(5));
            LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(6).equals(""))
                LEFT.setText(CR.getString(6));
            LEFT_CENTER.setVisibility(View.VISIBLE);
            if(!CR.getString(7).equals(""))
                LEFT_CENTER.setText(CR.getString(7));
            RIGHT_CENTER.setVisibility(View.VISIBLE);
            if(!CR.getString(8).equals(""))
                RIGHT_CENTER.setText(CR.getString(8));
            RIGHT.setVisibility(View.VISIBLE);
            if(!CR.getString(9).equals(""))
                RIGHT.setText(CR.getString(9));
            RIGHT_RIGHT.setVisibility(View.VISIBLE);
            if(!CR.getString(10).equals(""))
                RIGHT_RIGHT.setText(CR.getString(10));
            for(i = 5; i <= 10; i++)
                UpdatePlayrsTimeOnPlayground(CR.getString(i), "00:00");
        } else
        if(CR.getString(0).equals("5")) {
            LEFT_LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(5).equals(""))
                LEFT_LEFT.setText(CR.getString(5));
            LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(6).equals(""))
                LEFT.setText(CR.getString(6));
            LEFT_CENTER.setVisibility(View.VISIBLE);
            if(!CR.getString(7).equals(""))
                LEFT_CENTER.setText(CR.getString(7));
            RIGHT_CENTER.setVisibility(View.VISIBLE);
            if(!CR.getString(8).equals(""))
                RIGHT_CENTER.setText(CR.getString(8));
            RIGHT.setVisibility(View.VISIBLE);
            if(!CR.getString(9).equals(""))
                RIGHT.setText(CR.getString(9));
        } else
        if(CR.getString(0).equals("4")) {
            LEFT_LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(5).equals(""))
                LEFT_LEFT.setText(CR.getString(5));
            LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(6).equals(""))
                LEFT.setText(CR.getString(6));
            LEFT_CENTER.setVisibility(View.VISIBLE);
            if(!CR.getString(7).equals(""))
                LEFT_CENTER.setText(CR.getString(7));
            RIGHT_CENTER.setVisibility(View.VISIBLE);
            if(!CR.getString(8).equals(""))
                RIGHT_CENTER.setText(CR.getString(8));
        } else
        if(CR.getString(0).equals("3")) {
            LEFT_LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(5).equals(""))
                LEFT_LEFT.setText(CR.getString(5));
            LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(6).equals(""))
                LEFT.setText(CR.getString(6));
            LEFT_CENTER.setVisibility(View.VISIBLE);
            if(!CR.getString(7).equals(""))
                LEFT_CENTER.setText(CR.getString(7));
        } else
        if(CR.getString(0).equals("2")) {
            LEFT_LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(5).equals(""))
                LEFT_LEFT.setText(CR.getString(5));
            LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(6).equals(""))
                LEFT.setText(CR.getString(6));
        } else
        if(CR.getString(0).equals("1")) {
            LEFT_LEFT.setVisibility(View.VISIBLE);
            if(!CR.getString(5).equals(""))
                LEFT_LEFT.setText(CR.getString(5));
        }
        GOALKEEPR_BUTTON = (Button) findViewById(R.id.buttonGoalKeeper);
        if(!CR.getString(11).equals(""))
            GOALKEEPR_BUTTON.setText(CR.getString(11));

        final Button GOAL_ON_TARGET_BUTTON, SHOT_MISS_BUTTON, SHOT_GOALKEEPER_BUTTON, MISS_BALL_BUTTON, REACH_FAUL_SHOT_BUTTON, PLUS_BUTTON, MINUS_BUTTON, FAUL_BUTTON, FAUL_BUTTON_DEFENSIVE, YELLOW_CARD, RED_CARD;
        GOAL_ON_TARGET_BUTTON = (Button) findViewById(R.id.buttonShotOnTarget);
        if(!Boolean.parseBoolean(CR.getString(12)))
            GOAL_ON_TARGET_BUTTON.setVisibility(View.GONE);
        SHOT_MISS_BUTTON = (Button) findViewById(R.id.buttonShotMiss);
        if(!Boolean.parseBoolean(CR.getString(13)))
            SHOT_MISS_BUTTON.setVisibility(View.GONE);
        SHOT_GOALKEEPER_BUTTON = (Button) findViewById(R.id.buttonShotToGoalkeeper);
        if(!Boolean.parseBoolean(CR.getString(14)))
            SHOT_GOALKEEPER_BUTTON.setVisibility(View.GONE);
        MISS_BALL_BUTTON = (Button) findViewById(R.id.buttonMissBall);
        if(!Boolean.parseBoolean(CR.getString(15)))
            MISS_BALL_BUTTON.setVisibility(View.GONE);
        REACH_FAUL_SHOT_BUTTON = (Button) findViewById(R.id.buttonReachFaulShot);
        if(!Boolean.parseBoolean(CR.getString(16)))
            REACH_FAUL_SHOT_BUTTON.setVisibility(View.GONE);
        PLUS_BUTTON = (Button) findViewById(R.id.buttonPlus);
        if(!Boolean.parseBoolean(CR.getString(17)))
            PLUS_BUTTON.setVisibility(View.GONE);
        MINUS_BUTTON = (Button) findViewById(R.id.buttonMinus);
        if(!Boolean.parseBoolean(CR.getString(18)))
            MINUS_BUTTON.setVisibility(View.GONE);
        if(!Boolean.parseBoolean(CR.getString(19))) {
            FAUL_BUTTON = (Button) findViewById(R.id.buttonFaulOfensive);
            FAUL_BUTTON.setVisibility(View.GONE);
            FAUL_BUTTON_DEFENSIVE = (Button) findViewById(R.id.buttonFaulDefensive);
            FAUL_BUTTON_DEFENSIVE.setVisibility(View.GONE);
        }
        YELLOW_CARD = (Button) findViewById(R.id.buttonYellowCard);
        if(!Boolean.parseBoolean(CR.getString(20)))
            YELLOW_CARD.setVisibility(View.GONE);
        RED_CARD = (Button) findViewById(R.id.buttonRedCard);
        if(!Boolean.parseBoolean(CR.getString(21)))
            RED_CARD.setVisibility(View.GONE);
    }

    public boolean PreGameOptions() {

        LEFT_LEFT = (Button) findViewById(R.id.buttonLeftLeft);
        if(LEFT_LEFT.getText().toString().equals("Cislo hraca"))
            return false;
        LEFT = (Button) findViewById(R.id.buttonLeft);
        if(!LEFT.getText().toString().matches("^[0-9]+$"))
            return false;
        LEFT_CENTER = (Button) findViewById(R.id.buttonLeftCenter);
        if(!LEFT_CENTER.getText().toString().matches("^[0-9]+$"))
            return false;
        RIGHT_CENTER = (Button) findViewById(R.id.buttonRightCenter);
        if(!RIGHT_CENTER.getText().toString().matches("^[0-9]+$"))
            return false;
        RIGHT = (Button) findViewById(R.id.buttonRight);
        if(!RIGHT.getText().toString().matches("^[0-9]+$"))
            return false;
        RIGHT_RIGHT = (Button) findViewById(R.id.buttonRightRight);
        if(!RIGHT_RIGHT.getText().toString().matches("^[0-9]+$"))
            return false;
        GOALKEEPR_BUTTON = (Button) findViewById(R.id.buttonGoalKeeper);
        if(!GOALKEEPR_BUTTON.getText().toString().matches("^[0-9]+$"))
            return false;

        if(HALF_TIME == 0)
            return false;

        TEAM_NAME = (TextView) findViewById(R.id.HomeTeam);
        if(TEAM_NAME.getText().toString().equals("Sledovany tym"))
            return false;
        NAME_OF_OPONENT = (TextView) findViewById(R.id.HostTeam);
        if(NAME_OF_OPONENT.getText().toString().equals("Super"))
            return false;

        RelativeLayout HomeOrOut = (RelativeLayout) findViewById(R.id.HomeOrOut);
        RelativeLayout SomeData = (RelativeLayout) findViewById(R.id.SomeData);
        if(HomeOrOut.getVisibility() == View.VISIBLE) {
            RadioButton Home = (RadioButton) findViewById(R.id.Home);

            if(Home.isChecked())
                GamePlayedHome = true;
            else
                GamePlayedHome = false;
            HomeOrOut.setVisibility(View.GONE);
            SomeData.setVisibility(View.VISIBLE);
        }

        return true;
    }

    public void ResetTableOfPlayers() {
        DB.ResetTableOfPlayersForGame(DB);
        Cursor CR = DB.GetInformationPlayer(DB, ConditionForCreatingTable);
        if(CR.moveToFirst()) {
            do {
                DB.putInformationPlayers(DB, CR.getString(Constants.FIRST_NAME), CR.getString(Constants.SECOND_NAME), Integer.parseInt(CR.getString(Constants.NUMBER)), TurnamentPK);
            }while(CR.moveToNext());
        }
        DB.ResetTableOfGoalkeepersForGame(DB);
        CR = DB.GetInformationGoalkeeper(DB, ConditionForCreatingTableGoalkeeper);
        if(CR.moveToFirst()) {
            do {
                DB.putInformationGoalkeeper(DB, CR.getString(Constants.GOALKEEPER_FIRST_NAME_ROSTER), CR.getString(Constants.GOALKEEPER_SECOND_NAME_ROSTER), Integer.parseInt(CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER)),
                        TurnamentPK, "false");
            }while(CR.moveToNext());
        }
    }

    public void UpdatePlayrsTimeOnPlayground(final String PLAYERS_NUMBER_FOR_SET_TIME, final String TIME_WHEN_PLAYER_ARRIVE) {

        Cursor CR = DB.getInformationPlayers(DB, " where " + TableData.TableInfo.PlayerNumber + " == " + PLAYERS_NUMBER_FOR_SET_TIME + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
        if(CR.moveToFirst()) {
            do {
                MINUTES = String.valueOf(Integer.parseInt(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND).split(":")[0]) + (Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) -
                        Integer.parseInt(TIME_WHEN_PLAYER_ARRIVE.split(":")[0])));
                SECONDS = String.valueOf(Integer.parseInt(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND).split(":")[1]) + Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[1]) -
                        Integer.parseInt(TIME_WHEN_PLAYER_ARRIVE.split(":")[1]));
                if(Integer.parseInt(SECONDS) < 0) {
                    MINUTES = String.valueOf(Integer.parseInt(MINUTES) - 1);
                    SECONDS = String.valueOf(60 + Integer.parseInt(SECONDS));
                }
                if(Integer.parseInt(MINUTES) < 10) {
                    MINUTES = "0" + MINUTES;
                }
                if(Integer.parseInt(SECONDS) < 10) {
                    SECONDS = "0" + SECONDS;
                }
                DB.updatePlayersInformations(DB, PLAYERS_NUMBER_FOR_SET_TIME, TableData.TableInfo.PlayersTimeOnPlayground, MINUTES + ":" + SECONDS, TurnamentPK);
                            /*TODO: Think about hourse, if game have more than hour this will not work :( */
                MINUTES = "";
                SECONDS = "";
            }while(CR.moveToNext());
        } else {
            CR = DB.getInformationGoalkeeper(DB, " where " + TableData.TableInfo.GoalkeeperNumber + " == " + PLAYERS_NUMBER_FOR_SET_TIME + " AND " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK);
            if(CR.moveToFirst()) {
                do {
                    MINUTES = String.valueOf(Integer.parseInt(CR.getString(Constants.TIME_ON_PLAYGROUND_GOALKEEPER).split(":")[0]) +
                            (Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[0]) - Integer.parseInt(TIME_WHEN_PLAYER_ARRIVE.split(":")[0])));
                    SECONDS = String.valueOf(Integer.parseInt(CR.getString(Constants.TIME_ON_PLAYGROUND_GOALKEEPER).split(":")[1]) +
                            Integer.parseInt(CENTRAL_TIME.getText().toString().split(":")[1]) - Integer.parseInt(TIME_WHEN_PLAYER_ARRIVE.split(":")[1]));
                    if(Integer.parseInt(SECONDS) < 0) {
                        MINUTES = String.valueOf(Integer.parseInt(MINUTES) - 1);
                        SECONDS = String.valueOf(60 + Integer.parseInt(SECONDS));
                    }
                    if(Integer.parseInt(MINUTES) < 10) {
                        MINUTES = "0" + MINUTES;
                    }
                    if(Integer.parseInt(SECONDS) < 10) {
                        SECONDS = "0" + SECONDS;
                    }
                    DB.updateGoalkeeperInformations(DB, PLAYERS_NUMBER_FOR_SET_TIME, TableData.TableInfo.TimeOnPlaygroundGoalkeeper, MINUTES + ":" + SECONDS, TurnamentPK);
                            /*TODO: Think about hourse, if game have more than hour this will not work :( */
                    MINUTES = "";
                    SECONDS = "";
                }while(CR.moveToNext());
            }
        }
    }

    public void GoalKeeper(final String GOALKEEPER_NUMBER) {

        final Cursor CR = DB.getInformationGoalkeeper(DB, " where " + TableData.TableInfo.GoalkeeperNumber + " = " + GOALKEEPER_NUMBER + " AND " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        final RelativeLayout THIS_LAYOUT_WAS_VISIBLE = SetLayoutGone(FindWitchLayoutIsVisible());
        final RelativeLayout LONG_PASS_LAYOUT, SHOR_PASS_LAYOUT, FAUL_SHOT_LAYOUT;
        GOALKEEPER_VALUES_LAYOUT = (RelativeLayout) findViewById(R.id.GoalkeeperValues);
        GOALKEEPER_VALUES_LAYOUT.setVisibility(View.VISIBLE);
        LONG_PASS_LAYOUT = (RelativeLayout) findViewById(R.id.GoodBadLongPass);
        SHOR_PASS_LAYOUT = (RelativeLayout) findViewById(R.id.GoodBadShortPass);
        FAUL_SHOT_LAYOUT = (RelativeLayout) findViewById(R.id.CatchMissFaulShot);
        COUNT_OF_OPONENTS_GOALS = (TextView) findViewById(R.id.HostTeamCountOfGoals);
        final Button CATCH, LONG_PASS, GOOD_LONG_PASS, BAD_LONG_PASS, SHORT_PASS, GOOD_SHORT_PASS, BAD_SHORT_PASS, FAUL_SHOT, CATCH_FAUL_SHOT, MISS_FAUL_SHOT, GOAL;
        CATCH = (Button) findViewById(R.id.CatchShot);
        CATCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GOALKEEPER_VALUES_LAYOUT.setVisibility(View.GONE);
                GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.Catch + "#" + "3#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.Catch, String.valueOf(CR.getInt(Constants.CATCH) + 1), TurnamentPK);
                MoreDetailCatch(GOALKEEPER_NUMBER);
            }
        });
        LONG_PASS = (Button) findViewById(R.id.LongPass);
        LONG_PASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GOALKEEPER_VALUES_LAYOUT.setVisibility(View.GONE);
                LONG_PASS_LAYOUT.setVisibility(View.VISIBLE);
            }
        });
        GOOD_LONG_PASS = (Button) findViewById(R.id.GoodLongPass);
        GOOD_LONG_PASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                LONG_PASS_LAYOUT.setVisibility(View.GONE);
                GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.PassLongGood + "#" + "4#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.PassLongGood, String.valueOf(CR.getInt(Constants.PASS_LONG_GOOD) + 1), TurnamentPK);
                SetLayoutVisible(THIS_LAYOUT_WAS_VISIBLE);
            }
        });
        BAD_LONG_PASS = (Button) findViewById(R.id.BadLongShot);
        BAD_LONG_PASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                LONG_PASS_LAYOUT.setVisibility(View.GONE);
                GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.PassLongBad + "#" + "5#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.PassLongBad, String.valueOf(CR.getInt(Constants.PASS_LONG_BAD) + 1), TurnamentPK);
                SetLayoutVisible(THIS_LAYOUT_WAS_VISIBLE);
            }
        });
        SHORT_PASS = (Button) findViewById(R.id.ShortPass);
        SHORT_PASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GOALKEEPER_VALUES_LAYOUT.setVisibility(View.GONE);
                SHOR_PASS_LAYOUT.setVisibility(View.VISIBLE);
            }
        });
        GOOD_SHORT_PASS = (Button) findViewById(R.id.GoodShortPass);
        GOOD_SHORT_PASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                SHOR_PASS_LAYOUT.setVisibility(View.GONE);
                GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.PassShortGood + "#" + "6#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.PassShortGood, String.valueOf(CR.getInt(Constants.PASS_SHORT_GOOD) + 1), TurnamentPK);
                SetLayoutVisible(THIS_LAYOUT_WAS_VISIBLE);
            }
        });
        BAD_SHORT_PASS = (Button) findViewById(R.id.BadShortPass);
        BAD_SHORT_PASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                SHOR_PASS_LAYOUT.setVisibility(View.GONE);
                GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.PassShortBad + "#" + "7#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.PassShortBad, String.valueOf(CR.getInt(Constants.PASS_SHORT_BAD) + 1), TurnamentPK);
                SetLayoutVisible(THIS_LAYOUT_WAS_VISIBLE);
            }
        });
        FAUL_SHOT = (Button) findViewById(R.id.FaulShot);
        FAUL_SHOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GOALKEEPER_VALUES_LAYOUT.setVisibility(View.GONE);
                FAUL_SHOT_LAYOUT.setVisibility(View.VISIBLE);
            }
        });
        CATCH_FAUL_SHOT = (Button) findViewById(R.id.CatchFaulShot);
        CATCH_FAUL_SHOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                FAUL_SHOT_LAYOUT.setVisibility(View.GONE);
                GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.CatchFaulShot + "#" + "8#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.CatchFaulShot, String.valueOf(CR.getInt(Constants.CATCH_FAUL_SHOT) + 1), TurnamentPK);
                SetLayoutVisible(ofensiveLayout);
                ChangeChronomomenter();
            }
        });
        MISS_FAUL_SHOT = (Button) findViewById(R.id.MissFaulShot);
        MISS_FAUL_SHOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                FAUL_SHOT_LAYOUT.setVisibility(View.GONE);
                GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.MissFaulShot + "#" + "9#" + CENTRAL_TIME.getText().toString() + "#" +
                        String.valueOf(IDForGameRecord++) + "&" + GameReport;
                DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.MissFaulShot, String.valueOf(CR.getInt(Constants.MISS_FAUL_SHOT) + 1), TurnamentPK);
                COUNT_OF_OPONENTS_GOALS.setText(String.valueOf((Integer.parseInt(COUNT_OF_OPONENTS_GOALS.getText().toString()) + 1)));
                SetLayoutVisible(ofensiveLayout);
                ChangeChronomomenter();
            }
        });
        GOAL = (Button) findViewById(R.id.Goal);
        GOAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GOALKEEPER_VALUES_LAYOUT.setVisibility(View.GONE);
                CR.moveToFirst();
                do {
                    GameReport = "Goalkeeper#" + GOALKEEPER_NUMBER + "#" + TableData.TableInfo.RecoveredGoals + "#" + "10#" + CENTRAL_TIME.getText().toString() + "#" +
                            String.valueOf(IDForGameRecord++) + "&" + GameReport;
                    DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.RecoveredGoals, String.valueOf(CR.getInt(Constants.RECOVERED_GOALS) + 1), TurnamentPK);
                } while (CR.moveToNext());
                COUNT_OF_OPONENTS_GOALS.setText(String.valueOf((Integer.parseInt(COUNT_OF_OPONENTS_GOALS.getText().toString()) + 1)));
                if (!TimeWhenWeekingShouldEnd.equals("00:00") && (TimeWhenPowerPlayShouldEnd.equals("00:00") || AreWeInPowerPlay < 0))
                    ReachedGoalWhileWeakening++;
                if (!TimeWhenPowerPlayShouldEnd.equals("00:00") && (TimeWhenWeekingShouldEnd.equals("00:00") || AreWeInPowerPlay > 0))
                    ReachedGoalWhilePowerPlay++;
                MoreDetailRecovered(GOALKEEPER_NUMBER);
            }
        });
        DB.updateGoalkeeperInformations(DB, GOALKEEPER_NUMBER, TableData.TableInfo.ValuesAreConvertedGoalkeeper, "false", TurnamentPK);
    }

    public void MoreDetailCatch(final String PlayersNumber) {

        final Cursor CR = DB.getInformationGoalkeeper(DB, " where " + TableData.TableInfo.GoalkeeperNumber + " = " + PlayersNumber + " AND " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        SetLayoutGone(FindWitchLayoutIsVisible());
        GoalkeeperCatchPosition = (RelativeLayout) findViewById(R.id.GoalkeeperCatchPosition);
        GoalkeeperCatchPosition.setVisibility(View.VISIBLE);
        Button BrejkCatch, WingCatch, Catch6m, Catch7m, Catch9m;
        BrejkCatch = (Button) findViewById(R.id.BrejkCatch);
        BrejkCatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.BrejkCatch, String.valueOf(CR.getInt(Constants.BREJK_CATCH) + 1), TurnamentPK);
                GoalkeeperCatchPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        WingCatch = (Button) findViewById(R.id.WingCatch);
        WingCatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.WingCatch, String.valueOf(CR.getInt(Constants.WING_CATCH) + 1), TurnamentPK);
                GoalkeeperCatchPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Catch6m = (Button) findViewById(R.id.Catch6m);
        Catch6m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.Catch6m, String.valueOf(CR.getInt(Constants.CATCH_6M) + 1), TurnamentPK);
                GoalkeeperCatchPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Catch7m = (Button) findViewById(R.id.Catch7m);
        Catch7m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.Catch7m, String.valueOf(CR.getInt(Constants.CATCH_7M) + 1), TurnamentPK);
                GoalkeeperCatchPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Catch9m = (Button) findViewById(R.id.Catch9m);
        Catch9m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.Catch9m, String.valueOf(CR.getInt(Constants.CATCH_9M) + 1), TurnamentPK);
                GoalkeeperCatchPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
    }

    public void MoreDetailRecovered(final String PlayersNumber) {

        final Cursor CR = DB.getInformationGoalkeeper(DB, " where " + TableData.TableInfo.GoalkeeperNumber + " = " + PlayersNumber + " AND " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        SetLayoutGone(FindWitchLayoutIsVisible());
        GoalkeeperRecoveredPosition = (RelativeLayout) findViewById(R.id.GoalkeeperRecoveredPosition);
        GoalkeeperRecoveredPosition.setVisibility(View.VISIBLE);
        Button BrejkRecovered, WingRecovered, Recovered6m, Recovered7m, Recovered9m;
        BrejkRecovered = (Button) findViewById(R.id.BrejkRecovered);
        BrejkRecovered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.BrejkRecovered, String.valueOf(CR.getInt(Constants.BREJK_RECOVERED) + 1), TurnamentPK);
                GoalkeeperRecoveredPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        WingRecovered = (Button) findViewById(R.id.WingRecovered);
        WingRecovered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.WingRecovered, String.valueOf(CR.getInt(Constants.WING_RECOVERED) + 1), TurnamentPK);
                GoalkeeperRecoveredPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Recovered6m = (Button) findViewById(R.id.Recovered6m);
        Recovered6m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.Recovered6m, String.valueOf(CR.getInt(Constants.RECOVERED_6M) + 1), TurnamentPK);
                GoalkeeperRecoveredPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Recovered7m = (Button) findViewById(R.id.Recovered7m);
        Recovered7m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.Recovered7m, String.valueOf(CR.getInt(Constants.RECOVERED_7M) + 1), TurnamentPK);
                GoalkeeperRecoveredPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
        Recovered9m = (Button) findViewById(R.id.Recovered9m);
        Recovered9m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDeafaultBackground();
                DB.updateGoalkeeperInformations(DB, PlayersNumber, TableData.TableInfo.Recovered9m, String.valueOf(CR.getInt(Constants.RECOVERED_9M) + 1), TurnamentPK);
                GoalkeeperRecoveredPosition.setVisibility(View.GONE);
                ofensiveLayout.setVisibility(View.VISIBLE);
                ChangeChronomomenter();
                ShowInformation();
            }
        });
    }

    public RelativeLayout FindWitchLayoutIsVisible() {
        ofensiveLayout = (RelativeLayout) findViewById(R.id.RightSideOfensive);
        defensiveLayout = (RelativeLayout) findViewById(R.id.RightSideDefensive);
        RED_YELLOW_CARDS_LAYOUT = (RelativeLayout) findViewById(R.id.RedAndYellowCards);
        GOALKEEPER_VALUES_LAYOUT = (RelativeLayout) findViewById(R.id.GoalkeeperValues);
        RightSideOpponent = (RelativeLayout) findViewById(R.id.RightSideOpponent);
        if(ofensiveLayout.getVisibility() == View.VISIBLE)
            return ofensiveLayout;
        else {
            if(defensiveLayout.getVisibility() == View.VISIBLE)
                return defensiveLayout;
            else {
                if(RED_YELLOW_CARDS_LAYOUT.getVisibility() == View.VISIBLE)
                    return RED_YELLOW_CARDS_LAYOUT;
                else {
                    if (GOALKEEPER_VALUES_LAYOUT.getVisibility() == View.VISIBLE)
                        return GOALKEEPER_VALUES_LAYOUT;
                    else
                        return RightSideOpponent;
                }
            }
            /*TODO: Else if both layout are gone = some aborting of command*/
        }
    }

    public void SetLayoutVisible(RelativeLayout MAKE_VISIBLE_THIS_LAYOUT_PLEASE) {
        MAKE_VISIBLE_THIS_LAYOUT_PLEASE.setVisibility(View.VISIBLE);
    }

    public RelativeLayout SetLayoutGone(RelativeLayout MAKE_GONE_THIS_LAYOUT_PLEASE) {
        MAKE_GONE_THIS_LAYOUT_PLEASE.setVisibility(View.GONE);
        return MAKE_GONE_THIS_LAYOUT_PLEASE;
    }
    /*TODO: Use this functions in whole code getting layouts for get visible or gone*/

    public void AfterGameSumary() {

        TextView NAME_HOME_TEAM, NAME_HOST_TEAM, COUNT_OF_HOME_TEAM_GOALS, COUNT_OF_OPONENTS_GOALS;

        NAME_HOME_TEAM = (TextView) findViewById(R.id.HomeTeam);
        NAME_HOST_TEAM = (TextView) findViewById(R.id.HostTeam);
        COUNT_OF_OPONENTS_GOALS = (TextView) findViewById(R.id.HostTeamCountOfGoals);
        COUNT_OF_HOME_TEAM_GOALS = (TextView) findViewById(R.id.HomeTeamCountOfGoals);

        CountingFunctions CF = new CountingFunctions(CTX);
        CF.GameTeam(TurnamentPK);
        CF.GamePlayer(TurnamentPK);
        CF.GameGoalkeeper(TurnamentPK);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        if(MatchEnds) {
            Cursor CR = DB.getInformationPlayers(DB," where " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK); /*TODO: groub by number for correct record*/
            if(CR.moveToFirst()) {
                do {
                    DB.PutInformationPlayerHistory(DB, CR.getString(Constants.PLAYER_FIRST_NAME), CR.getString(Constants.PLAYER_SECOND_NAME), CR.getInt(Constants.PLAYER_NUMBER), CR.getInt(Constants.GOAL_ON_TARGET),
                            CR.getInt(Constants.SHOT_MISS), CR.getInt(Constants.SHOT_GOALKEEPER), CR.getInt(Constants.MISS_BALL), CR.getInt(Constants.REACH_FAUL_SHOT), CR.getInt(Constants.PLUS),
                            CR.getInt(Constants.MINUS), CR.getInt(Constants.FAUL), CR.getInt(Constants.YELLOW_CARD), CR.getInt(Constants.RED_CARD), CR.getInt(Constants.REACH_BALL), CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND),
                            CR.getInt(Constants.FAUL_SHOT_GOAL), CR.getInt(Constants.FAUL_SHOT_MISS), CR.getInt(Constants.BREJK_GOAL), CR.getInt(Constants.BREJK_MISS), CR.getInt(Constants.GOAL_WING), CR.getInt(Constants.MISS_WING),
                            CR.getInt(Constants.GOAL_6M), CR.getInt(Constants.GOAL_7M), CR.getInt(Constants.GOAL_9M), CR.getInt(Constants.MISS_6M), CR.getInt(Constants.MISS_7M), CR.getInt(Constants.MISS_9M),
                            CR.getInt(Constants.CATCH_BY_GOALKEEPER_BREJK), CR.getInt(Constants.CATCH_BY_GOALKEEPER_WING), CR.getInt(Constants.CATCH_BY_GOALKEEPER_6M), CR.getInt(Constants.CATCH_BY_GOALKEEPER_7M),
                            CR.getInt(Constants.CATCH_BY_GOALKEEPER_9M), CR.getInt(Constants.SHOTS_PREDICTION_FOR_GAME), CR.getInt(Constants.SHOTS_PREDICTION_BREJK_FOR_GAME), CR.getInt(Constants.SHOTS_PREDICTION_WING_FOR_GAME),
                            CR.getInt(Constants.SHOTS_PREDICTION_6M_FOR_GAME), CR.getInt(Constants.SHOTS_PREDICTION_7M_FOR_GAME), CR.getInt(Constants.SHOTS_PREDICTION_9M_FOR_GAME), CR.getInt(Constants.FAUL_SHOTS_PREDICTION_FOR_GAME),
                            CR.getInt(Constants.EFFICIENT_IN_DEFENSIVE_FOR_GAME), formattedDate, TurnamentPK);
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ValuesChange, "true", TurnamentPK);
                }while(CR.moveToNext());
            }
            CR = DB.getInformationGoalkeeper(DB, " where " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK);
            if(CR.moveToFirst()) {
                do {
                    DB.PutInformationGoalkeeperHistory(DB, CR.getString(Constants.GOALKEEPER_FIRST_NAME), CR.getString(Constants.GOALKEEPER_SECOND_NAME), CR.getInt(Constants.GOALKEEPER_NUMBER), CR.getInt(Constants.CATCH),
                            CR.getInt(Constants.PASS_LONG_GOOD), CR.getInt(Constants.PASS_LONG_BAD), CR.getInt(Constants.PASS_SHORT_GOOD), CR.getInt(Constants.PASS_SHORT_BAD), CR.getInt(Constants.CATCH_FAUL_SHOT),
                            CR.getInt(Constants.MISS_FAUL_SHOT), CR.getInt(Constants.RECOVERED_GOALS), CR.getString(Constants.TIME_ON_PLAYGROUND_GOALKEEPER), CR.getInt(Constants.BREJK_CATCH), CR.getInt(Constants.BREJK_RECOVERED),
                            CR.getInt(Constants.WING_CATCH), CR.getInt(Constants.WING_RECOVERED), CR.getInt(Constants.CATCH_6M), CR.getInt(Constants.CATCH_7M), CR.getInt(Constants.CATCH_9M), CR.getInt(Constants.RECOVERED_6M),
                            CR.getInt(Constants.RECOVERED_7M), CR.getInt(Constants.RECOVERED_9M), CR.getInt(Constants.FAUL_GOALKEEPER), CR.getInt(Constants.YELLOW_CARD_GOALKEEPER), CR.getInt(Constants.RED_CARD_GOALKEEPER),
                            CR.getInt(Constants.PREDICTION_OF_CATCH_FOR_GAME), CR.getInt(Constants.PREDICTION_OF_CATCH_BREJK_FOR_GAME), CR.getInt(Constants.PREDICTION_OF_CATCH_WING_FOR_GAME),
                            CR.getInt(Constants.PREDICTION_OF_CATCH_6M_FOR_GAME), CR.getInt(Constants.PREDICTION_OF_CATCH_7M_FOR_GAME), CR.getInt(Constants.PREDICTION_OF_CATCH_9M_FOR_GAME),
                            CR.getInt(Constants.PREDICTION_OF_LONG_PASS_FOR_GAME), CR.getInt(Constants.PREDICTION_OF_SHORT_PASS_FOR_GAME), CR.getInt(Constants.PREDICTION_OF_CATCH_FAUL_SHOT_FOR_GAME),
                            CR.getInt(Constants.COUNT_OF_INTERVENTIONS_FOR_GAME), formattedDate, TurnamentPK);
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.GoalkeeperValuesChange, "true", TurnamentPK);
                }while(CR.moveToNext());
            }
            DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.DateTimePKGame, formattedDate, TurnamentPK);
            DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.GamePlayedHome, String.valueOf(GamePlayedHome), TurnamentPK);
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryValuesChange, "true", TurnamentPK);
        }

        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.HalftimeScore, HALFTIME_SCORE, TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.FinalScore, COUNT_OF_HOME_TEAM_GOALS.getText().toString() + ":" + COUNT_OF_OPONENTS_GOALS.getText().toString(), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.TimeInOfensive, OFENSIVE_TIME.getText().toString(), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.TimeInDefensive, CalculateDefensiveTime(CENTRAL_TIME.getText().toString(), OFENSIVE_TIME.getText().toString()), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.MatchTime, CENTRAL_TIME.getText().toString(), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.GameReplay, GameReplay, TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.PowerPlaysCount, String.valueOf(CountOfFaulsOpponent), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.PowerPlaysWin, String.valueOf(PowerPlaysWin), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.CountOfOfensive, String.valueOf(CountOfOfensive), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.CountOfWeakening, String.valueOf(CountOfWeakening), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.GoalWhileWeakening, String.valueOf(GoalWhileWeakening), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.ReachedGoalWhileWeakening, String.valueOf(ReachedGoalWhileWeakening), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.ReachedGoalWhilePowerPlay, String.valueOf(ReachedGoalWhilePowerPlay), TurnamentPK);
        DB.updateInformationsGame(DB, String.valueOf(PKGame), TableData.TableInfo.GameReport, GameReport, TurnamentPK);

        DB.putInformationOpponent(DB, String.valueOf(CountOfMissShotsOpponent), String.valueOf(CountOfFaulsOpponent), formattedDate, String.valueOf(0), TurnamentPK);

//        LEFT_LEFT = (Button) findViewById(R.id.buttonLeftLeft);
//        UpdatePlayrsTimeOnPlayground(LEFT_LEFT.getText().toString(), TIME_WHEN_COME);
//        LEFT = (Button) findViewById(R.id.buttonLeft);
//        UpdatePlayrsTimeOnPlayground(LEFT.getText().toString(), TIME_WHEN_COME);
//        LEFT_CENTER = (Button) findViewById(R.id.buttonLeftCenter);
//        UpdatePlayrsTimeOnPlayground(LEFT_CENTER.getText().toString(), TIME_WHEN_COME);
//        RIGHT_CENTER = (Button) findViewById(R.id.buttonRightCenter);
//        UpdatePlayrsTimeOnPlayground(RIGHT_CENTER.getText().toString(), TIME_WHEN_COME);
//        RIGHT = (Button) findViewById(R.id.buttonRight);
//        UpdatePlayrsTimeOnPlayground(RIGHT.getText().toString(), TIME_WHEN_COME);
//        RIGHT_RIGHT = (Button) findViewById(R.id.buttonRightRight);
//        UpdatePlayrsTimeOnPlayground(RIGHT_RIGHT.getText().toString(), TIME_WHEN_COME);
//        GOALKEEPR_BUTTON = (Button) findViewById(R.id.buttonGoalKeeper);
//        UpdatePlayrsTimeOnPlayground(GOALKEEPR_BUTTON.getText().toString(), TimeWhenGoalkeeperCome);
//        DB.updateGoalkeeperInformations(DB, GOALKEEPR_BUTTON.getText().toString(), TableData.TableInfo.DateTimePKGoalkeeper, String.valueOf(PKGame), TurnamentPK);

        SwitchToStatistic();
    }

    public String CalculateDefensiveTime(String CentralTime, String OfensiveTime) {
        Integer Time = 0;
        Time = (Integer.parseInt(CentralTime.split(":")[0])*60 + Integer.parseInt(CentralTime.split(":")[1])) -
                (Integer.parseInt(OfensiveTime.split(":")[0])*60 + Integer.parseInt(OfensiveTime.split(":")[1]));
        if(Time/60 < 10) {
            if(Time%60 < 10)
                return "0" + String.valueOf(Time/60) + ":" + "0" + String.valueOf(Time%60);
            else
                return "0" + String.valueOf(Time/60) + ":" + String.valueOf(Time%60);
        } else {
            if(Time%60 < 10)
                return String.valueOf(Time/60) + ":" + "0" + String.valueOf(Time%60);
            else
                return String.valueOf(Time/60) + ":" + String.valueOf(Time%60);
        }
    }

    public void SwitchToStatistic() {
        if(MatchEnds)
            finish();
        Intent i = new Intent(getApplicationContext(), AfterGameSumary.class);
        i.putExtra("TurnamentPK", TurnamentPK);
        startActivity(i);
    }

    public void ChangePlayers() {
        LAYOUT_FOR_EVERYTHINK = (RelativeLayout) findViewById(R.id.HideEverything);
        LAYOUT_FOR_EVERYTHINK.setVisibility(View.GONE);
        LayoutForSetNewPlayers = (RelativeLayout) findViewById(R.id.layoutNewPlayerOnTheGround);
        LayoutForSetNewPlayers.setVisibility(View.VISIBLE);
        ShowFormations();
        RelativeLayout LeftSide = (RelativeLayout) findViewById(R.id.LeftSide);
        final LinearLayout PlayersOnPlayground, PlayersForChange, PlayersInLineUp, OfensiveFormations;
        PlayersOnPlayground = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        PlayersForChange = (LinearLayout) findViewById(R.id.PlayersForChange);
        PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        Button Change = (Button) findViewById(R.id.Change);
//        Cursor CR = DB.getInformationGameOption(DB, ConditionOptionsGame);
//        CR.moveToLast();
        Cursor CRP = DB.getInformationPlayers(DB, ConditionPlayer); /*TODO:*/
        if(CRP.moveToFirst()) {
            for(int i = 0; i < 6; i++) {
                final TextView Line = new TextView(CTX);
                Line.setTextSize(TextSizeRostersMobile);
                Line.setHint("");
                CRP.moveToFirst();
                do {
                    if(CRP.getString(Constants.PLAYER_NUMBER).equals(((TextView) LeftSide.getChildAt(i)).getText().toString())) {
                        Line.setText(CRP.getString(Constants.PLAYER_NUMBER) + " " + CRP.getString(Constants.PLAYER_SECOND_NAME) + " " + CRP.getString(Constants.PLAYER_FIRST_NAME).charAt(0) + ".");
                        UpdatePlayrsTimeOnPlayground(CRP.getString(Constants.PLAYER_NUMBER), TIME_WHEN_COME);
                        break;
                    }
                }while(CRP.moveToNext());
                Line.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ColorID = ColorGeneratorForPlayground(Line.getHint().toString());
                        Line.setBackgroundColor(ColorID);
                        if(ColorID == 0)
                            Line.setHint(String.valueOf(""));
                        else
                            Line.setHint(String.valueOf(ColorID));
                    }
                });
                PlayersOnPlayground.addView(Line);
            }
            CRP.moveToFirst();
            do {
                final TextView PlayersFromBench = new TextView(CTX);
                PlayersFromBench.setTextSize(TextSizeRostersMobile);
                PlayersFromBench.setHint("");
                PlayersFromBench.setText(CRP.getString(Constants.PLAYER_NUMBER) + " " + CRP.getString(Constants.PLAYER_SECOND_NAME) + " " + CRP.getString(Constants.PLAYER_FIRST_NAME).charAt(0) + ".");
                PlayersFromBench.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ColorID = ColorGeneratorForBench(PlayersFromBench.getHint().toString());
                        PlayersFromBench.setBackgroundColor(ColorID);
                        if(ColorID == 0)
                            PlayersFromBench.setHint("");
                        else
                            PlayersFromBench.setHint(String.valueOf(ColorID));
                    }
                });
                PlayersForChange.addView(PlayersFromBench);
            } while (CRP.moveToNext());
        }
        HidePlayersOnPlaygroud();
        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountingForColorGeneratorForPlayground = 0;
                CountingForColorGeneratorForBench = 0;
                SendPlayersFromBanch();
                PlayersInLineUp.removeAllViews();
            }
        });
    }

    public void ChangeGoalkeeper(final Button ActiveGoalkeeper) {
        final String OriginalGoalkeeper = ActiveGoalkeeper.getText().toString();
        LAYOUT_FOR_EVERYTHINK = (RelativeLayout) findViewById(R.id.HideEverything);
        LAYOUT_FOR_EVERYTHINK.setVisibility(View.GONE);
        LayoutForSetNewPlayers = (RelativeLayout) findViewById(R.id.layoutNewPlayerOnTheGround);
        LayoutForSetNewPlayers.setVisibility(View.VISIBLE);
        RelativeLayout LeftSide = (RelativeLayout) findViewById(R.id.LeftSide);
        final LinearLayout PlayersOnPlayground, PlayersForChange, PlayersInLineUp, OfensiveFormations;
        PlayersForChange = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        Button Change = (Button) findViewById(R.id.Change);
        Cursor CRP = DB.getInformationGoalkeeper(DB, ConditionGoalkeeper);
        CRP.moveToFirst();
        do {
            final TextView PlayersFromBench = new TextView(CTX);
            PlayersFromBench.setTextSize(TextSizeRostersMobile);
            PlayersFromBench.setHint("");
            PlayersFromBench.setText(CRP.getString(Constants.GOALKEEPER_NUMBER) + " " + CRP.getString(Constants.GOALKEEPER_SECOND_NAME) + " " +
                    CRP.getString(Constants.GOALKEEPER_FIRST_NAME).charAt(0) + ".");
            PlayersFromBench.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!PlayersFromBench.getHint().toString().equals("")) {
                        PlayersFromBench.setHint("");
                        PlayersFromBench.setBackgroundColor(0);
                        SetOriginalGoalkeeper(OriginalGoalkeeper);
                    } else {
                        ResetGoalkeepersList();
                        PlayersFromBench.setBackgroundColor(0x55C17DFF);
                        PlayersFromBench.setHint(String.valueOf(0x55C17DFF));
                    }
                }
            });
            PlayersForChange.addView(PlayersFromBench);
        } while (CRP.moveToNext());
        SetOriginalGoalkeeper(OriginalGoalkeeper);
        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SentGoalkeeper(ActiveGoalkeeper);
                ResetGoalkeepersList();
                PlayersForChange.removeAllViews();
                LAYOUT_FOR_EVERYTHINK.setVisibility(View.VISIBLE);
                LayoutForSetNewPlayers = (RelativeLayout) findViewById(R.id.layoutNewPlayerOnTheGround);
                LayoutForSetNewPlayers.setVisibility(View.GONE);
            }
        });
    }

    public void SetOriginalGoalkeeper(String OriginalGoalkeeper) {
        final LinearLayout PlayersForChange;
        PlayersForChange = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        for(int i = 0; i < PlayersForChange.getChildCount(); i++) {
            if (((TextView)PlayersForChange.getChildAt(i)).getText().toString().split(" ")[0].equals(OriginalGoalkeeper)) {
                ((TextView)PlayersForChange.getChildAt(i)).setHint(String.valueOf(0x55C17DFF));
                PlayersForChange.getChildAt(i).setBackgroundColor(0x55C17DFF);
            }
        }
    }

    public void ResetGoalkeepersList() {
        final LinearLayout PlayersForChange;
        PlayersForChange = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        for(int i = 0; i < PlayersForChange.getChildCount(); i++) {
            if (!((TextView)PlayersForChange.getChildAt(i)).getHint().toString().equals("")) {
                ((TextView)PlayersForChange.getChildAt(i)).setHint("");
                PlayersForChange.getChildAt(i).setBackgroundColor(0);
            }
        }
    }

    public void SentGoalkeeper(Button Goalkeeper) {
        final LinearLayout PlayersForChange;
        PlayersForChange = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        for(int i = 0; i < PlayersForChange.getChildCount(); i++) {
            if (!((TextView)PlayersForChange.getChildAt(i)).getHint().toString().equals("")) {
                Goalkeeper.setText(((TextView) PlayersForChange.getChildAt(i)).getText().toString().split(" ")[0]);
            }
        }
    }

    public void ResetValueForLineUp(String Hint) {
        LinearLayout PlayersOnPlayground, PlayersForChange, PlayersInLineUp, OfensiveFormations;
        PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        for(int i = 0; i < 13; i++) { /*TODO: delete magical number*/
            if(!Hint.equals("0")) {
                if(((TextView)PlayersInLineUp.getChildAt(i)).getHint().toString().equals(Hint)) {
                    PlayersInLineUp.getChildAt(i).setBackgroundColor(0);
                    ((TextView)PlayersInLineUp.getChildAt(i)).setHint("");
                }
            } else {
                PlayersInLineUp.setBackgroundColor(0);
                ((TextView)PlayersInLineUp.getChildAt(i)).setHint("");
            }
        }
    }

    public void ShowFormations() {
        final LinearLayout PlayersOnPlayground, PlayersForChange, PlayersInLineUp, OfensiveFormations;
        OfensiveFormations = (LinearLayout) findViewById(R.id.OfensiveFormations);
        PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        Cursor CR = DB.getInformationLineUp(DB, ConditionLineUp);
        if(CR.moveToFirst()) {
            do {
                final TextView OneFormation = new TextView(CTX);
                OneFormation.setTextSize(TextSizeRostersMobile);
                OneFormation.setText(CR.getString(0).split("#")[0]);
                OneFormation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PlayersInLineUp.getChildCount() != 0 && ((TextView)PlayersInLineUp.getChildAt(PlayersInLineUp.getChildCount()-1)).getText().toString().equals(OneFormation.getText().toString())) {
                            PlayersInLineUp.removeAllViews();
                            HidePlayersOnPlaygroud();
                        } else
                            ShowPlayersInLineUp(OneFormation.getText().toString());
                    }
                });
                OfensiveFormations.addView(OneFormation);
            }while(CR.moveToNext());
        }
    }

    public void ShowPlayersInLineUp(String LineUpName) {
        CountingForColorGeneratorForBench = 0;
        CountingForColorGeneratorForPlayground = 0;
        LinearLayout PlayersOnPlayground, PlayersForChange, PlayersInLineUp, OfensiveFormations;
        PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        PlayersInLineUp.removeAllViewsInLayout();
        Cursor CR = DB.getInformationLineUp(DB, ConditionLineUp);
        Cursor CRP;

        if(CR.moveToFirst()) {
            do {
                if(CR.getString(0).split("#")[0].equals(LineUpName)) {
                    for(int i = 1; i <= 6 ; i++) {
                        final TextView OnePlayerInLineUp = new TextView(CTX);
                        OnePlayerInLineUp.setTextSize(TextSizeRostersMobile);
                        OnePlayerInLineUp.setHint("");
                        CRP = DB.getInformationPlayers(DB, " where " + TableData.TableInfo.PlayerNumber + " == " + Integer.parseInt(CR.getString(0).split("#")[i]) + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
                        CRP.moveToLast();
                        OnePlayerInLineUp.setText(CRP.getString(Constants.PLAYER_NUMBER) + " " + CRP.getString(Constants.PLAYER_SECOND_NAME) + " " + CRP.getString(Constants.PLAYER_FIRST_NAME).charAt(0) + ".");
                        ColorID = ColorGeneratorForBench(OnePlayerInLineUp.getHint().toString());
                        OnePlayerInLineUp.setBackgroundColor(ColorID);
                        if(ColorID == 0)
                            OnePlayerInLineUp.setHint("");
                        else
                            OnePlayerInLineUp.setHint(String.valueOf(ColorID));
                        PlayersInLineUp.addView(OnePlayerInLineUp);
                    }
                    final TextView OnePlayerInLineUp = new TextView(CTX);
                    OnePlayerInLineUp.setText(LineUpName);
                    OnePlayerInLineUp.setVisibility(View.GONE);
                    PlayersInLineUp.addView(OnePlayerInLineUp);
                }
            }while(CR.moveToNext());
        }
        HidePlayersOnPlaygroud();
        PlayersOnPlayground = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        ResetValuesAfterChanging(PlayersOnPlayground);
        for(int i = 0; i < PlayersOnPlayground.getChildCount(); i++) {
            ColorID = ColorGeneratorForPlayground(((TextView)PlayersOnPlayground.getChildAt(i)).getHint().toString());
            PlayersOnPlayground.getChildAt(i).setBackgroundColor(ColorID);
            if(ColorID == 0)
                ((TextView) PlayersOnPlayground.getChildAt(i)).setHint("");
            else
                ((TextView) PlayersOnPlayground.getChildAt(i)).setHint(String.valueOf(ColorID));
        }
    }

    public void SendPlayersFromBanch() {
        LinearLayout PlayersOnPlayground, PlayersForChange, PlayersInLineUp, OfensiveFormations;
        PlayersOnPlayground = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        PlayersForChange = (LinearLayout) findViewById(R.id.PlayersForChange);
        PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        if(CountOfColor(PlayersOnPlayground) < CountOfColor(PlayersForChange)) {
            int j = 0;
            for(int i = 0; i < PlayersForChange.getChildCount(); i++) {
                if (!(((TextView) PlayersForChange.getChildAt(i)).getHint()).equals("")) {
                    ((TextView) PlayersOnPlayground.getChildAt(j)).setText(((TextView) PlayersForChange.getChildAt(i)).getText().toString());
                    j++;
                }
            }
        } else {
            for(int i = 0; i < PlayersOnPlayground.getChildCount(); i++) {
                for(int j = 0; j< PlayersForChange.getChildCount(); j++) {
                    if ((((TextView)PlayersOnPlayground.getChildAt(i)).getHint()).equals(((TextView)PlayersForChange.getChildAt(j)).getHint()) && !(((TextView)PlayersOnPlayground.getChildAt(i)).getHint()).equals("")) {
                        ((TextView) PlayersOnPlayground.getChildAt(i)).setText(((TextView) PlayersForChange.getChildAt(j)).getText().toString());
                    }
                }
                for(int j = 0; j< PlayersInLineUp.getChildCount(); j++) {
                    if ((((TextView)PlayersOnPlayground.getChildAt(i)).getHint()).equals(((TextView)PlayersInLineUp.getChildAt(j)).getHint()) && !(((TextView)PlayersOnPlayground.getChildAt(i)).getHint()).equals("")) {
                        ((TextView) PlayersOnPlayground.getChildAt(i)).setText(((TextView) PlayersInLineUp.getChildAt(j)).getText().toString());
                    }
                }
            }
        }

        PlayersInLineUp.removeAllViewsInLayout();
        HidePlayersOnPlaygroud();
        ResetValuesAfterChanging(PlayersOnPlayground);
        ResetValuesAfterChanging(PlayersForChange);
        RelativeLayout LeftSide = (RelativeLayout) findViewById(R.id.LeftSide);
        int i = 0;
        while(i < PlayersOnPlayground.getChildCount()) {
            ((TextView) LeftSide.getChildAt(i)).setText(((TextView) PlayersOnPlayground.getChildAt(i)).getText().toString().split(" ")[0]);
            i++;
        }
        PlayersOnPlayground.removeAllViewsInLayout();
        PlayersForChange.removeAllViewsInLayout();
        OfensiveFormations = (LinearLayout) findViewById(R.id.OfensiveFormations);
        OfensiveFormations.removeAllViewsInLayout();
        LAYOUT_FOR_EVERYTHINK.setVisibility(View.VISIBLE);
        LayoutForSetNewPlayers = (RelativeLayout) findViewById(R.id.layoutNewPlayerOnTheGround);
        LayoutForSetNewPlayers.setVisibility(View.GONE);
    }

    public int CountOfColor(LinearLayout LayoutForCount) {
        int j = 0;
        for(int i = 0; i < LayoutForCount.getChildCount(); i++) {
            if(!(((TextView)LayoutForCount.getChildAt(i)).getHint()).equals(""))
                j++;
        }
        return j;
    }

    public void HidePlayersOnPlaygroud() {
        LinearLayout PlayersOnPlayground, PlayersForChange, PlayersInLineUp, OfensiveFormations;
        PlayersOnPlayground = (LinearLayout) findViewById(R.id.PlayersOnPlayground);
        PlayersForChange = (LinearLayout) findViewById(R.id.PlayersForChange);
        PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        for(int j = 0; j< PlayersForChange.getChildCount(); j++) {
            PlayersForChange.getChildAt(j).setVisibility(View.VISIBLE);
        }
        for(int i = 0; i < PlayersOnPlayground.getChildCount(); i++) {
            for(int j = 0; j< PlayersForChange.getChildCount(); j++) {
                if((((TextView)PlayersForChange.getChildAt(j)).getText().toString().split(" ")[0]).equals(((TextView)PlayersOnPlayground.getChildAt(i)).getText().toString().split(" ")[0]))
                    PlayersForChange.getChildAt(j).setVisibility(View.GONE);
            }
        }
        for(int i = 0; i < PlayersInLineUp.getChildCount(); i++) {
            for(int j = 0; j< PlayersForChange.getChildCount(); j++) {
                if((((TextView)PlayersForChange.getChildAt(j)).getText().toString().split(" ")[0]).equals(((TextView)PlayersInLineUp.getChildAt(i)).getText().toString().split(" ")[0]))
                    PlayersForChange.getChildAt(j).setVisibility(View.GONE);
            }
        }
    }

    public void ResetValuesAfterChanging(LinearLayout LayoutForReset) {
        for(int i = 0; i < LayoutForReset.getChildCount(); i++) {
            ((TextView)LayoutForReset.getChildAt(i)).setHint("");
            LayoutForReset.getChildAt(i).setBackgroundColor(0);
        }
    }

    public int ColorGeneratorForPlayground(String Hint) {
        LinearLayout PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        if(!Hint.equals("")) {
            if(PlayersInLineUp.getChildCount() != 0) {
                ResetValueForLineUp(Hint);
            } else
                CountingForColorGeneratorForPlayground--;
            return 0;
        }
//        Cursor CR = DB.getInformationGameOption(DB, ConditionOptionsGame);
//        CR.moveToLast();
        if(CountingForColorGeneratorForPlayground == 6 && PlayersInLineUp.getChildCount() == 0)
            CountingForColorGeneratorForPlayground = 0;
        CountingForColorGeneratorForPlayground++;
        if(CountingForColorGeneratorForPlayground == 1)
            return 0x55C17DFF;
        if(CountingForColorGeneratorForPlayground == 2)
            return 0x66FFDB6A;
        if(CountingForColorGeneratorForPlayground == 3)
            return 0x444AFF00;
        if(CountingForColorGeneratorForPlayground == 4)
            return 0x44005BFF;
        if(CountingForColorGeneratorForPlayground == 5)
            return 0x44FF0057;
        if(CountingForColorGeneratorForPlayground == 6)
            return 0x44B6FF00;
        if(CountingForColorGeneratorForPlayground == 7)
            return 0x22FFDB6A;
        if(CountingForColorGeneratorForPlayground == 8)
            return 0x224AFF00;
        if(CountingForColorGeneratorForPlayground == 9)
            return 0x22005BFF;
        if(CountingForColorGeneratorForPlayground == 10)
            return 0x22FF0057;
        if(CountingForColorGeneratorForPlayground == 11)
            return 0x22B6FF00;
        return 0;

    }

    public int ColorGeneratorForBench(String Hint) {
        LinearLayout PlayersInLineUp = (LinearLayout) findViewById(R.id.PlayersInLineUp);
        if(!Hint.equals("") && PlayersInLineUp.getChildCount() == 0) {
            CountingForColorGeneratorForBench--;
            return 0;
        }
//        Cursor CR = DB.getInformationGameOption(DB, ConditionOptionsGame);
//        CR.moveToLast();
        if(CountingForColorGeneratorForBench == 6)
            if(PlayersInLineUp.getChildCount() == 0 && CountingForColorGeneratorForBench < 12)
                CountingForColorGeneratorForBench = 6;
        CountingForColorGeneratorForBench++;
        if(CountingForColorGeneratorForBench == 1)
            return 0x55C17DFF;
        if(CountingForColorGeneratorForBench == 2)
            return 0x66FFDB6A;
        if(CountingForColorGeneratorForBench == 3)
            return 0x444AFF00;
        if(CountingForColorGeneratorForBench == 4)
            return 0x44005BFF;
        if(CountingForColorGeneratorForBench == 5)
            return 0x44FF0057;
        if(CountingForColorGeneratorForBench == 6)
            return 0x44B6FF00;
        if(CountingForColorGeneratorForBench == 7)
            return 0x22FFDB6A;
        if(CountingForColorGeneratorForBench == 8)
            return 0x224AFF00;
        if(CountingForColorGeneratorForBench == 9)
            return 0x22005BFF;
        if(CountingForColorGeneratorForBench == 10)
            return 0x22FF0057;
        if(CountingForColorGeneratorForBench == 11)
            return 0x22B6FF00;
        return 0;

    }

    public void ChangeLayout() {
        ofensiveLayout = (RelativeLayout) findViewById(R.id.RightSideOfensive);
        defensiveLayout = (RelativeLayout) findViewById(R.id.RightSideDefensive);
        if(OFENSIVE_TIME_IS_STOPED) {
            ofensiveLayout.setVisibility(View.GONE);
            defensiveLayout.setVisibility(View.VISIBLE);
            ChangeChronomomenter();
        } else {
            ofensiveLayout.setVisibility(View.VISIBLE);
            defensiveLayout.setVisibility(View.GONE);
            ChangeChronomomenter();
        }
        LAYOUT_FOR_EVERYTHINK = (RelativeLayout) findViewById(R.id.HideEverything);
        LAYOUT_FOR_EVERYTHINK.setVisibility(View.GONE);
        RED_YELLOW_CARDS_LAYOUT = (RelativeLayout) findViewById(R.id.RedAndYellowCards);
        RED_YELLOW_CARDS_LAYOUT.setVisibility(View.GONE);
        GOALKEEPER_VALUES_LAYOUT = (RelativeLayout) findViewById(R.id.GoalkeeperValues);
        GOALKEEPER_VALUES_LAYOUT.setVisibility(View.GONE);
        NewGameInformation = (RelativeLayout) findViewById(R.id.NewGameInformation);
        NewGameInformation.setVisibility(View.GONE);
        LayoutForSetNewPlayers = (RelativeLayout) findViewById(R.id.layoutNewPlayerOnTheGround);
        LayoutForSetNewPlayers.setVisibility(View.GONE);
        RightSideOpponent = (RelativeLayout) findViewById(R.id.RightSideOpponent);
        RightSideOpponent.setVisibility(View.GONE);
        GoalkeeperRecoveredPosition = (RelativeLayout) findViewById(R.id.GoalkeeperRecoveredPosition);
        GoalkeeperRecoveredPosition.setVisibility(View.GONE);
        GoalkeeperCatchPosition = (RelativeLayout) findViewById(R.id.GoalkeeperCatchPosition);
        GoalkeeperCatchPosition.setVisibility(View.GONE);
        MissPosition = (RelativeLayout) findViewById(R.id.MissPosition);
        MissPosition.setVisibility(View.GONE);
        GoalPosition = (RelativeLayout) findViewById(R.id.GoalPosition);
        GoalPosition.setVisibility(View.GONE);
        ResultFaulShot = (RelativeLayout) findViewById(R.id.ResultFaulShot);
        ResultFaulShot.setVisibility(View.GONE);
        RelativeLayout SHOW_AFTER_GAME_SUMARY = (RelativeLayout) findViewById(R.id.ShowAfterMatchStatistic);
        SHOW_AFTER_GAME_SUMARY.setVisibility(View.GONE);
        RelativeLayout LONG_PASS_LAYOUT = (RelativeLayout) findViewById(R.id.GoodBadLongPass);
        LONG_PASS_LAYOUT.setVisibility(View.GONE);
        RelativeLayout SHOR_PASS_LAYOUT = (RelativeLayout) findViewById(R.id.GoodBadShortPass);
        SHOR_PASS_LAYOUT.setVisibility(View.GONE);
        RelativeLayout FAUL_SHOT_LAYOUT = (RelativeLayout) findViewById(R.id.CatchMissFaulShot);
        FAUL_SHOT_LAYOUT.setVisibility(View.GONE);
        RelativeLayout LeftSide = (RelativeLayout) findViewById(R.id.LeftSide);
        LeftSide.setVisibility(View.GONE);
    }

    public void ShowInformation() {
        Cursor CR = DB.getInformationPlayers(DB, ConditionPlayer);
        Integer CountOfShots = 0, CountOfMissBalls = 0, CountOfFauls = 0;
        TextView BestPlayer = (TextView) findViewById(R.id.textView54);
        TextView BestDefender = (TextView) findViewById(R.id.textView56);
        String BestShooter = BestPlayer.getText().toString(), BestDefensivePlayer = BestDefender.getText().toString();
        if(CR.moveToFirst()) {
//            if(BestShooter.equals("") && BestDefensivePlayer.equals("")){
//                BestShooter = CR.getString(3) + " " + CR.getString(0) + " " + CR.getString(1);
//                BestDefensivePlayer = CR.getString(8) + " " + CR.getString(0) + " " + CR.getString(1);
//            }
//            do {
//                CountOfShots = CountOfShots + Integer.valueOf(CR.getString(3))  + Integer.valueOf(CR.getString(4))  + Integer.valueOf(CR.getString(5));
//                CountOfMissBalls = CountOfMissBalls + Integer.valueOf(CR.getString(6));
//                CountOfFauls = CountOfFauls + Integer.valueOf(CR.getString(10)) + Integer.valueOf(CR.getString(11)) + Integer.valueOf(CR.getString(12));
//                if(Integer.valueOf(CR.getString(3)) > Integer.valueOf(BestShooter.split(" ")[0])) {
//                    BestShooter = CR.getString(3) + " " + CR.getString(0) + " " + CR.getString(1);
//                }
//                if(Integer.valueOf(CR.getString(8)) > Integer.valueOf(BestDefensivePlayer.split(" ")[0])) {
//                    BestDefensivePlayer = CR.getString(8) + " " + CR.getString(0) + " " + CR.getString(1);
//                }
//            }while(CR.moveToNext());
            TextView Shots = (TextView) findViewById(R.id.textView48);
            Shots.setText(String.valueOf(CountOfShots));
            TextView MissBalls = (TextView) findViewById(R.id.textView50);
            MissBalls.setText(String.valueOf(CountOfMissBalls));
            TextView Fauls = (TextView) findViewById(R.id.textView52);
            Fauls.setText(String.valueOf(CountOfFauls));
            if(BestShooter.split(" ").length == 3) {
                BestPlayer.setText(BestShooter.split(" ")[1] + " " + BestShooter.split(" ")[2]);
                BestDefender.setText(BestShooter.split(" ")[1] + " " + BestShooter.split(" ")[2]);
            }
            if(BestShooter.split(" ").length == 2) {
                BestPlayer.setText(BestShooter.split(" ")[1]);
                BestDefender.setText(BestShooter.split(" ")[1]);
            }
        }
    }

    public void SetDeafaultBackground() {
        RelativeLayout LeftSide = (RelativeLayout) findViewById(R.id.LeftSide);
        for(int i = 0; i < LeftSide.getChildCount(); i++) {
            LeftSide.getChildAt(i).setBackgroundColor(0xc9bbbbbb);
            ((Button)LeftSide.getChildAt(i)).setHint("0");
        }
    }

    public Boolean CheckIfIsPickedSomePlayer(String Number) {
        RelativeLayout LeftSide = (RelativeLayout) findViewById(R.id.LeftSide);
        for(int i = 0; i < LeftSide.getChildCount(); i++) {
            if(!((Button)LeftSide.getChildAt(i)).getHint().equals("0") && ((Button)LeftSide.getChildAt(i)).getText().toString().equals(Number))
                return true;
        }
        return false;
    }

    public void BackButtonHandler() { /*TODO: Abort weeking or power play if faul value is rolling back*/
        final TextView COUNT_OF_HOME_TEAM_GOALS = (TextView) findViewById(R.id.HomeTeamCountOfGoals);
        COUNT_OF_OPONENTS_GOALS = (TextView) findViewById(R.id.HostTeamCountOfGoals);
        Cursor CR;
        if(!GameReport.equals("")) {                        //0                 #1            #2           #3                #4   #5
            String LastRecord = GameReport.split("&")[0];   //PlayerOrGoalkeeper#PlayersNumber#NameOfColumn#NumberOfOperation#Time#ID
            GameReport = GameReport.replace(LastRecord + "&", "");
            switch (LastRecord.split("#")[0]) {
                case "Player":
                    CR = DB.getInformationPlayers(DB, ConditionPlayer + " AND " + TableData.TableInfo.PlayerNumber + " == " + LastRecord.split("#")[1]);
                    if(CR.moveToLast()) {
                        DB.updatePlayersInformations(DB, LastRecord.split("#")[1], LastRecord.split("#")[2],
                                String.valueOf(Integer.parseInt(CR.getString(Integer.parseInt(LastRecord.split("#")[3]))) - 1), TurnamentPK);
                    }
                    switch (LastRecord.split("#")[2]) {
                        case "FaulShotGoal":
                        case "BrejkGoal" :
                        case "GoalWing" :
                        case "Goal6m" :
                        case "Goal7m" :
                        case "Goal9m" :
                            COUNT_OF_HOME_TEAM_GOALS.setText(String.valueOf(Integer.parseInt(COUNT_OF_HOME_TEAM_GOALS.getText().toString()) - 1));
                            break;
                    }
                    switch (LastRecord.split("#")[2]) {
                        case "FaulShotGoal":
                            DB.updatePlayersInformations(DB, LastRecord.split("#")[1], TableData.TableInfo.ReachFaulShot,
                                    String.valueOf(Integer.parseInt(CR.getString(Integer.parseInt(LastRecord.split("#")[3]))) - 1), TurnamentPK);
                            break;
                        case "BrejkGoal" :
                        case "GoalWing" :
                        case "Goal6m" :
                        case "Goal7m" :
                        case "Goal9m" :
                            DB.updatePlayersInformations(DB, LastRecord.split("#")[1], TableData.TableInfo.GoalOnTarget,
                                    String.valueOf(Integer.parseInt(CR.getString(Integer.parseInt(LastRecord.split("#")[3]))) - 1), TurnamentPK);
                            break;
                    }
                    break;
                case "Goalkeeper":
                    if(LastRecord.split("#")[2].equals("miss_faul_shot") || LastRecord.split("#")[2].equals("goal")) {
                        COUNT_OF_OPONENTS_GOALS.setText(String.valueOf((Integer.parseInt(COUNT_OF_OPONENTS_GOALS.getText().toString()) - 1)));
                    }
                    CR = DB.getInformationGoalkeeper(DB, ConditionGoalkeeper + " AND " + TableData.TableInfo.GoalkeeperNumber + " == " + LastRecord.split("#")[1]);
                    if(CR.moveToLast()) {
                        DB.updateGoalkeeperInformations(DB, LastRecord.split("#")[1], LastRecord.split("#")[2],
                                String.valueOf(Integer.parseInt(CR.getString(Integer.parseInt(LastRecord.split("#")[3]))) - 1), TurnamentPK);
                    }
                    break;
                default: break;
                    /*TODO: implement back handling for miss shots and shots catch by oponent goalkeeper*/
//                public static final String BrejkMiss = "BrejkMiss";                             //20
//                public static final String MissWing = "MissWing";                               //22
//                public static final String Miss6m = "Miss6m";                                   //26
//                public static final String Miss7m = "Miss7m";                                   //27
//                public static final String Miss9m = "Miss9m";                                   //28
            }
        }
    }
}
