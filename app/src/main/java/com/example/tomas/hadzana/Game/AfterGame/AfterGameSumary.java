package com.example.tomas.hadzana.Game.AfterGame;

import android.content.Context;
import android.content.res.Configuration;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class AfterGameSumary extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {

    Context CX = this;
    DatabaseOperations DB = new DatabaseOperations(CX);
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    private ViewPager tabsviewPager;
    private ActionBar mActionBar;
    private AfterGameTabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 600) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_after_game_sumary);
        TextSizeForScreens = 30;
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_summary_mobile);
//            TextSizeForScreens = 15;
//        }
        tabsviewPager = (ViewPager) findViewById(R.id.tabspagerstatistic);

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

        mTabsAdapter = new AfterGameTabsAdapter(getSupportFragmentManager(), TurnamentPK);

        tabsviewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setTitle("Statistika");
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab SummaryGame = getSupportActionBar().newTab().setText("  Hra  ").setTabListener(this);
        ActionBar.Tab SummaryPlayers = getSupportActionBar().newTab().setText("  Hraci  ").setTabListener(this);
        ActionBar.Tab SummaryGoalkeepers = getSupportActionBar().newTab().setText("  Brankari  ").setTabListener(this);

        getSupportActionBar().addTab(SummaryGame);
        getSupportActionBar().addTab(SummaryPlayers);
        getSupportActionBar().addTab(SummaryGoalkeepers);

        tabsviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(ActionBar.Tab selectedtab, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        tabsviewPager.setCurrentItem(selectedtab.getPosition()); //update tab position on tab
    }

    @Override
    public void onTabUnselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
//    Context CTX = this;
//    DatabaseOperations DB = new DatabaseOperations(CTX);
//
//    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord;
//
//    ScrollView GoalkeepersTable, PlayersTable;
//
//    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame;
//
//    Integer TextSizeForScreens;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 600) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            setContentView(R.layout.activity_after_game_sumary);
//            TextSizeForScreens = 30;
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_after_game_sumary_mobile);
//            TextSizeForScreens = 15;
//        }
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            TurnamentPK = extras.getString("TurnamentPK");
//        }
//        if(TurnamentPK.equals("0")) {
//            ConditionPlayer = "";
//            ConditionGoalkeeper = "";
//            ConditionGame = "";
//        } else {
//            ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK;
//            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
//            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
//        }
//
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        GoalkeepersTable = (ScrollView) findViewById(R.id.GoalkeepersTable);
//        PlayersTable = (ScrollView) findViewById(R.id.PlayersTable);
//        GraphGameRecord = (RelativeLayout) findViewById(R.id.GraphGameRecord);
//
//        Button ShowPlayers, ShowGameRecord, ShowGoalkeepers;
//        ShowPlayers = (Button) findViewById(R.id.ShowPlayers);
//        ShowPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PlayersTable.setVisibility(View.VISIBLE);
//                GoalkeepersTable.setVisibility(View.GONE);
//                GraphGameRecord.setVisibility(View.GONE);
//                ShowTableOfPlayers();
//            }
//        });
//        ShowPlayers.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                GameStatistic.setVisibility(View.GONE);
//                Buttons.setVisibility(View.GONE);
//                GoalkeepersTable.setVisibility(View.GONE);
//                PlayersTable.setVisibility(View.VISIBLE);
//                ShowTableOfPlayers();
//                return true;
//            }
//        });
//        ShowGameRecord = (Button) findViewById(R.id.ShowGameRecord);
//        ShowGameRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PlayersTable.setVisibility(View.GONE);
//                GoalkeepersTable.setVisibility(View.GONE);
//                GraphGameRecord.setVisibility(View.VISIBLE);
//            }
//        });
//        ShowGameRecord.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                GameStatistic.setVisibility(View.GONE);
//                Buttons.setVisibility(View.GONE);
//                return true;
//            }
//        });
//        ShowGoalkeepers = (Button) findViewById(R.id.ShowGoalkeepers);
//        ShowGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PlayersTable.setVisibility(View.GONE);
//                GoalkeepersTable.setVisibility(View.VISIBLE);
//                GraphGameRecord.setVisibility(View.GONE);
//                ShowTableOfGoalkeepers();
//            }
//        });
//        ShowGoalkeepers.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                GameStatistic.setVisibility(View.GONE);
//                Buttons.setVisibility(View.GONE);
//                PlayersTable.setVisibility(View.GONE);
//                GoalkeepersTable.setVisibility(View.VISIBLE);
//                ShowTableOfGoalkeepers();
//                return true;
//            }
//        });
//
//        GameStatisticTable();
//    }
//

//

//
//    public void ShowTableOfPlayers() {
//        LinearLayout PlayersNumbers, PlayersNames, TimeOnPlaygroundPlayers, ShotsPlayers, GoalsPlayers, MissShotsPlayers, CatchByGoalkPlayers, PredictionOfShots, MissBallsPlayers,
//                ReachFaulShotPlayers, PlusPlayers, MinusPlayers, FaulsPlayers, YellowCardsPlayers, RedCardsPlayers, ReachBallsPlayers;
//
//        PlayersNumbers = (LinearLayout) findViewById(R.id.PlayersNumbers);
//        PlayersNumbers.removeAllViews();
//        PlayersNames = (LinearLayout) findViewById(R.id.PlayersNames);
//        PlayersNames.removeAllViews();
//        TimeOnPlaygroundPlayers = (LinearLayout) findViewById(R.id.TimeOnPlaygroundPlayers);
//        TimeOnPlaygroundPlayers.removeAllViews();
//        TimeOnPlaygroundPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Odohrany cas.", Toast.LENGTH_LONG).show();
//            }
//        });
//        ShotsPlayers = (LinearLayout) findViewById(R.id.ShotsPlayers);
//        ShotsPlayers.removeAllViews();
//        ShotsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Sucet striel.", Toast.LENGTH_LONG).show();
//            }
//        });
//        GoalsPlayers = (LinearLayout) findViewById(R.id.GoalsPlayers);
//        GoalsPlayers.removeAllViews();
//        GoalsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Pocet golov.", Toast.LENGTH_LONG).show();
//            }
//        });
//        MissShotsPlayers = (LinearLayout) findViewById(R.id.MissShotsPlayers);
//        MissShotsPlayers.removeAllViews();
//        MissShotsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Strely mimo.", Toast.LENGTH_LONG).show();
//            }
//        });
//        CatchByGoalkPlayers = (LinearLayout) findViewById(R.id.CatchByGoalkPlayers);
//        CatchByGoalkPlayers.removeAllViews();
//        CatchByGoalkPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Chytene brankarom.", Toast.LENGTH_LONG).show();
//            }
//        });
//        PredictionOfShots = (LinearLayout) findViewById(R.id.PredictionOfShots);
//        PredictionOfShots.removeAllViews();
//        PredictionOfShots.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Uspesnost strelby.", Toast.LENGTH_LONG).show();
//            }
//        });
//        MissBallsPlayers = (LinearLayout) findViewById(R.id.MissBallsPlayers);
//        MissBallsPlayers.removeAllViews();
//        MissBallsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Stratene lopty.", Toast.LENGTH_LONG).show();
//            }
//        });
//        ReachFaulShotPlayers = (LinearLayout) findViewById(R.id.ReachFaulShotPlayers);
//        ReachFaulShotPlayers.removeAllViews();
//        ReachFaulShotPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Ziskany pokutovy hod.", Toast.LENGTH_LONG).show();
//            }
//        });
//        PlusPlayers = (LinearLayout) findViewById(R.id.PlusPlayers);
//        PlusPlayers.removeAllViews();
//        PlusPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Plusove body v obrane.", Toast.LENGTH_LONG).show();
//            }
//        });
//        MinusPlayers = (LinearLayout) findViewById(R.id.MinusPlayers);
//        MinusPlayers.removeAllViews();
//        MinusPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Minusove body v obrane.", Toast.LENGTH_LONG).show();
//            }
//        });
//        FaulsPlayers = (LinearLayout) findViewById(R.id.FaulsPlayers);
//        FaulsPlayers.removeAllViews();
//        FaulsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Pocet vyluceni.", Toast.LENGTH_LONG).show();
//            }
//        });
//        YellowCardsPlayers = (LinearLayout) findViewById(R.id.YellowCardsPlayers);
//        YellowCardsPlayers.removeAllViews();
//        YellowCardsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Zlte karty.", Toast.LENGTH_LONG).show();
//            }
//        });
//        RedCardsPlayers = (LinearLayout) findViewById(R.id.RedCardsPlayers);
//        RedCardsPlayers.removeAllViews();
//        RedCardsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Cervene karty.", Toast.LENGTH_LONG).show();
//            }
//        });
//        ReachBallsPlayers = (LinearLayout) findViewById(R.id.ReachBallsPlayers);
//        ReachBallsPlayers.removeAllViews();
//        ReachBallsPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Ziskane lopty v obrane.", Toast.LENGTH_LONG).show();
//            }
//        });
//        int i = 0;
//        Cursor CR = DB.getInformationPlayers(DB, ConditionPlayer + " GROUP BY " + TableData.TableInfo.PlayerNumber);
//        CR.moveToFirst();
//        do {
//            final TextView Numbers = new TextView(this);
//            Numbers.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Numbers.setBackgroundColor(0xC2C2C2FF);
//            else
//                Numbers.setBackgroundColor(0);
//            Numbers.setText(CR.getString(Constants.PLAYER_NUMBER));
//            PlayersNumbers.addView(Numbers);
//            final TextView Names = new TextView(this);
//            Names.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Names.setBackgroundColor(0xC2C2C2FF);
//            else
//                Names.setBackgroundColor(0);
//            Names.setText(CR.getString(Constants.PLAYER_FIRST_NAME).toCharArray()[0] + ". " + CR.getString(Constants.PLAYER_SECOND_NAME));
//            Names.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ShowPlayersDetail(Numbers.getText().toString());
//                }
//            });
//            PlayersNames.addView(Names);
//            final TextView Times = new TextView(this);
//            Times.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Times.setBackgroundColor(0xC2C2C2FF);
//            else
//                Times.setBackgroundColor(0);
//            Times.setText(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND));
//            TimeOnPlaygroundPlayers.addView(Times);
//            final TextView CountOfShots = new TextView(this);
//            CountOfShots.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                CountOfShots.setBackgroundColor(0xC2C2C2FF);
//            else
//                CountOfShots.setBackgroundColor(0);
//            CountOfShots.setText(String.valueOf(Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) + Integer.parseInt(CR.getString(Constants.SHOT_MISS)) + Integer.parseInt(CR.getString(Constants.SHOT_GOALKEEPER))));
//            ShotsPlayers.addView(CountOfShots);
//            final TextView Goals = new TextView(this);
//            Goals.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Goals.setBackgroundColor(0xC2C2C2FF);
//            else
//                Goals.setBackgroundColor(0);
//            Goals.setText(CR.getString(Constants.GOAL_ON_TARGET));
//            GoalsPlayers.addView(Goals);
//            final TextView MissShots = new TextView(this);
//            MissShots.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                MissShots.setBackgroundColor(0xC2C2C2FF);
//            else
//                MissShots.setBackgroundColor(0);
//            MissShots.setText(CR.getString(Constants.SHOT_MISS));
//            MissShotsPlayers.addView(MissShots);
//            final TextView CatchByGoalkeeper = new TextView(this);
//            CatchByGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                CatchByGoalkeeper.setBackgroundColor(0xC2C2C2FF);
//            else
//                CatchByGoalkeeper.setBackgroundColor(0);
//            CatchByGoalkeeper.setText(CR.getString(Constants.SHOT_GOALKEEPER));
//            CatchByGoalkPlayers.addView(CatchByGoalkeeper);
//            final TextView Prediction = new TextView(this);
//            Prediction.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Prediction.setBackgroundColor(0xC2C2C2FF);
//            else
//                Prediction.setBackgroundColor(0);
//            if(Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) + Integer.parseInt(CR.getString(Constants.SHOT_MISS)) + Integer.parseInt(CR.getString(Constants.SHOT_GOALKEEPER)) != 0)
//                Prediction.setText(String.valueOf((Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) * 100) / (Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) + Integer.parseInt(CR.getString(Constants.SHOT_MISS)) +
//                        Integer.parseInt(CR.getString(Constants.SHOT_GOALKEEPER)))) + "%");
//            else
//                Prediction.setText("0%");
//            PredictionOfShots.addView(Prediction);
//            final TextView MissBalls = new TextView(this);
//            MissBalls.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                MissBalls.setBackgroundColor(0xC2C2C2FF);
//            else
//                MissBalls.setBackgroundColor(0);
//            MissBalls.setText(CR.getString(Constants.MISS_BALL));
//            MissBallsPlayers.addView(MissBalls);
//            final TextView ReachFaul = new TextView(this);
//            ReachFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                ReachFaul.setBackgroundColor(0xC2C2C2FF);
//            else
//                ReachFaul.setBackgroundColor(0);
//            ReachFaul.setText(CR.getString(Constants.REACH_FAUL_SHOT));
//            ReachFaulShotPlayers.addView(ReachFaul);
//            final TextView Plus = new TextView(this);
//            Plus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Plus.setBackgroundColor(0xC2C2C2FF);
//            else
//                Plus.setBackgroundColor(0);
//            Plus.setText(CR.getString(Constants.PLUS));
//            PlusPlayers.addView(Plus);
//            final TextView Minus = new TextView(this);
//            Minus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Minus.setBackgroundColor(0xC2C2C2FF);
//            else
//                Minus.setBackgroundColor(0);
//            Minus.setText(CR.getString(Constants.MINUS));
//            MinusPlayers.addView(Minus);
//            final TextView Fauls = new TextView(this);
//            Fauls.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Fauls.setBackgroundColor(0xC2C2C2FF);
//            else
//                Fauls.setBackgroundColor(0);
//            Fauls.setText(CR.getString(Constants.FAUL));
//            FaulsPlayers.addView(Fauls);
//            final TextView Yellow = new TextView(this);
//            Yellow.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Yellow.setBackgroundColor(0xC2C2C2FF);
//            else
//                Yellow.setBackgroundColor(0);
//            Yellow.setText(CR.getString(Constants.YELLOW_CARD));
//            YellowCardsPlayers.addView(Yellow);
//            final TextView Red = new TextView(this);
//            Red.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Red.setBackgroundColor(0xC2C2C2FF);
//            else
//                Red.setBackgroundColor(0);
//            Red.setText(CR.getString(Constants.RED_CARD));
//            RedCardsPlayers.addView(Red);
//            final TextView ReachBall = new TextView(this);
//            ReachBall.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                ReachBall.setBackgroundColor(0xC2C2C2FF);
//            else
//                ReachBall.setBackgroundColor(0);
//            ReachBall.setText(CR.getString(Constants.REACH_BALL));
//            ReachBallsPlayers.addView(ReachBall);
//
//            i++;
//        }while(CR.moveToNext());
//    }
//
//    public void ShowTableOfGoalkeepers() {
//        LinearLayout GoalkeepersNumbers, GoalkeepersNames, TimeOnPlaygroundGoalkeepers, RecoveredGoalsGoalkeepers, CatchShotsGoalkeepers, PredictionOfCathe, GoodLongPassGoalkeepers, BadLongPassGoalkeepers, PredictionOfGoodLongPass,
//                GoodShortPassGoalkeepers, BadShortPassGoalkeepers, PredictionOfGoodShortPass, CatcheFaulShotGoalkeeper, MissFaulShotGoalkeeper, PredictionOfCatchFaulShot;
//
//        GoalkeepersNumbers = (LinearLayout) findViewById(R.id.GoalkeepersNumbers);
//        GoalkeepersNumbers.removeAllViews();
//        GoalkeepersNames = (LinearLayout) findViewById(R.id.GoalkeepersNames);
//        GoalkeepersNames.removeAllViews();
//        TimeOnPlaygroundGoalkeepers = (LinearLayout) findViewById(R.id.TimeOnPlaygroundGoalkeepers);
//        TimeOnPlaygroundGoalkeepers.removeAllViews();
//        TimeOnPlaygroundGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Odohrany cas.", Toast.LENGTH_LONG).show();
//            }
//        });
//        RecoveredGoalsGoalkeepers = (LinearLayout) findViewById(R.id.RecoveredGoalsGoalkeepers);
//        RecoveredGoalsGoalkeepers.removeAllViews();
//        RecoveredGoalsGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Inkasovane goly.", Toast.LENGTH_LONG).show();
//            }
//        });
//        CatchShotsGoalkeepers = (LinearLayout) findViewById(R.id.CatchShotsGoalkeepers);
//        CatchShotsGoalkeepers.removeAllViews();
//        CatchShotsGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Chytene srely.", Toast.LENGTH_LONG).show();
//            }
//        });
//        PredictionOfCathe = (LinearLayout) findViewById(R.id.PredictionOfCathe);
//        PredictionOfCathe.removeAllViews();
//        PredictionOfCathe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Uspesnost zakrakov.", Toast.LENGTH_LONG).show();
//            }
//        });
//        GoodLongPassGoalkeepers = (LinearLayout) findViewById(R.id.GoodLongPassGoalkeepers);
//        GoodLongPassGoalkeepers.removeAllViews();
//        GoodLongPassGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Dobra dlha prihravka", Toast.LENGTH_LONG).show();
//            }
//        });
//        BadLongPassGoalkeepers = (LinearLayout) findViewById(R.id.BadLongPassGoalkeepers);
//        BadLongPassGoalkeepers.removeAllViews();
//        BadLongPassGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Zla dlha prihravka", Toast.LENGTH_LONG).show();
//            }
//        });
//        PredictionOfGoodLongPass = (LinearLayout) findViewById(R.id.PredictionOfGoodLongPass);
//        PredictionOfGoodLongPass.removeAllViews();
//        PredictionOfGoodLongPass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Uspesnost dlhej prihravky.", Toast.LENGTH_LONG).show();
//            }
//        });
//        GoodShortPassGoalkeepers = (LinearLayout) findViewById(R.id.GoodShortPassGoalkeepers);
//        GoodShortPassGoalkeepers.removeAllViews();
//        GoodShortPassGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Dobra kratka prihravka", Toast.LENGTH_LONG).show();
//            }
//        });
//        BadShortPassGoalkeepers = (LinearLayout) findViewById(R.id.BadShortPassGoalkeepers);
//        BadShortPassGoalkeepers.removeAllViews();
//        BadShortPassGoalkeepers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Zla kratka prihravka", Toast.LENGTH_LONG).show();
//            }
//        });
//        PredictionOfGoodShortPass = (LinearLayout) findViewById(R.id.PredictionOfGoodShortPass);
//        PredictionOfGoodShortPass.removeAllViews();
//        PredictionOfGoodShortPass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Uspesnost kratkej prihravky", Toast.LENGTH_LONG).show();
//            }
//        });
//        CatcheFaulShotGoalkeeper = (LinearLayout) findViewById(R.id.CatcheFaulShotGoalkeeper);
//        CatcheFaulShotGoalkeeper.removeAllViews();
//        CatcheFaulShotGoalkeeper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Chyteny pokutovy hod.", Toast.LENGTH_LONG).show();
//            }
//        });
//        MissFaulShotGoalkeeper = (LinearLayout) findViewById(R.id.MissFaulShotGoalkeeper);
//        MissFaulShotGoalkeeper.removeAllViews();
//        MissFaulShotGoalkeeper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Inkasovany z pokutoveho hodu.", Toast.LENGTH_LONG).show();
//            }
//        });
//        PredictionOfCatchFaulShot = (LinearLayout) findViewById(R.id.PredictionOfCatchFaulShot);
//        PredictionOfCatchFaulShot.removeAllViews();
//        PredictionOfCatchFaulShot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "Uspesnost zakroku pri pokutovom hode.", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        int i = 0;
//        Cursor CR = DB.getInformationGoalkeeper(DB, ConditionGoalkeeper + " GROUP BY " + TableData.TableInfo.GoalkeeperNumber);
//        CR.moveToFirst();
//        do {
//            final TextView Numbers = new TextView(this);
//            Numbers.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Numbers.setBackgroundColor(0xC2C2C2FF);
//            else
//                Numbers.setBackgroundColor(0);
//            Numbers.setText(CR.getString(Constants.GOALKEEPER_NUMBER));
//            GoalkeepersNumbers.addView(Numbers);
//            final TextView Names = new TextView(this);
//            Names.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Names.setBackgroundColor(0xC2C2C2FF);
//            else
//                Names.setBackgroundColor(0);
//            Names.setText(CR.getString(Constants.GOALKEEPER_FIRST_NAME).toCharArray()[0] + ". " + CR.getString(Constants.GOALKEEPER_SECOND_NAME));
//            Names.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ShowGoalkeeperDetail(Numbers.getText().toString());
//                }
//            });
//            GoalkeepersNames.addView(Names);
//            final TextView Times = new TextView(this);
//            Times.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Times.setBackgroundColor(0xC2C2C2FF);
//            else
//                Times.setBackgroundColor(0);
//            Times.setText(CR.getString(Constants.TIME_ON_PLAYGROUND_GOALKEEPER));
//            TimeOnPlaygroundGoalkeepers.addView(Times);
//            final TextView Recovered = new TextView(this);
//            Recovered.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Recovered.setBackgroundColor(0xC2C2C2FF);
//            else
//                Recovered.setBackgroundColor(0);
//            Recovered.setText(CR.getString(Constants.GOAL));
//            RecoveredGoalsGoalkeepers.addView(Recovered);
//            final TextView Catch = new TextView(this);
//            Catch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                Catch.setBackgroundColor(0xC2C2C2FF);
//            else
//                Catch.setBackgroundColor(0);
//            Catch.setText(CR.getString(Constants.CATCH));
//            CatchShotsGoalkeepers.addView(Catch);
//            final TextView PredictionCatch = new TextView(this);
//            PredictionCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                PredictionCatch.setBackgroundColor(0xC2C2C2FF);
//            else
//                PredictionCatch.setBackgroundColor(0);
//            if((Integer.parseInt(CR.getString(Constants.GOAL)) + Integer.parseInt(CR.getString(Constants.CATCH))) != 0)
//                PredictionCatch.setText(String.valueOf((Integer.parseInt(CR.getString(Constants.CATCH))*100)/(Integer.parseInt(CR.getString(Constants.GOAL)) +
//                    Integer.parseInt(CR.getString(Constants.CATCH)))) + "%");
//            else
//                PredictionCatch.setText("0%");
//            PredictionOfCathe.addView(PredictionCatch);
//            final TextView GoodLP = new TextView(this);
//            GoodLP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                GoodLP.setBackgroundColor(0xC2C2C2FF);
//            else
//                GoodLP.setBackgroundColor(0);
//            GoodLP.setText(CR.getString(Constants.PASS_LONG_GOOD));
//            GoodLongPassGoalkeepers.addView(GoodLP);
//            final TextView BadLP = new TextView(this);
//            BadLP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                BadLP.setBackgroundColor(0xC2C2C2FF);
//            else
//                BadLP.setBackgroundColor(0);
//            BadLP.setText(CR.getString(Constants.PASS_LONG_BAD));
//            BadLongPassGoalkeepers.addView(BadLP);
//            final TextView PredictionGoodLP = new TextView(this);
//            PredictionGoodLP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                PredictionGoodLP.setBackgroundColor(0xC2C2C2FF);
//            else
//                PredictionGoodLP.setBackgroundColor(0);
//            if((Integer.parseInt(CR.getString(Constants.PASS_LONG_GOOD)) + Integer.parseInt(CR.getString(Constants.PASS_LONG_BAD))) != 0)
//                PredictionGoodLP.setText(String.valueOf((Integer.parseInt(CR.getString(Constants.PASS_LONG_GOOD))*100)/(Integer.parseInt(CR.getString(Constants.PASS_LONG_GOOD)) +
//                    Integer.parseInt(CR.getString(Constants.PASS_LONG_BAD)))) + "%");
//            else
//                PredictionGoodLP.setText("0%");
//            PredictionOfGoodLongPass.addView(PredictionGoodLP);
//            final TextView GoodSP = new TextView(this);
//            GoodSP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                GoodSP.setBackgroundColor(0xC2C2C2FF);
//            else
//                GoodSP.setBackgroundColor(0);
//            GoodSP.setText(CR.getString(Constants.PASS_SHORT_GOOD));
//            GoodShortPassGoalkeepers.addView(GoodSP);
//            final TextView BadSP = new TextView(this);
//            BadSP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                BadSP.setBackgroundColor(0xC2C2C2FF);
//            else
//                BadSP.setBackgroundColor(0);
//            BadSP.setText(CR.getString(Constants.PASS_SHORT_BAD));
//            BadShortPassGoalkeepers.addView(BadSP);
//            final TextView PredictionGoodSP = new TextView(this);
//            PredictionGoodSP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                PredictionGoodSP.setBackgroundColor(0xC2C2C2FF);
//            else
//                PredictionGoodSP.setBackgroundColor(0);
//            if((Integer.parseInt(CR.getString(Constants.PASS_SHORT_GOOD)) + Integer.parseInt(CR.getString(Constants.PASS_SHORT_BAD))) != 0)
//                PredictionGoodSP.setText(String.valueOf((Integer.parseInt(CR.getString(Constants.PASS_SHORT_GOOD)) * 100) / (Integer.parseInt(CR.getString(Constants.PASS_SHORT_GOOD)) +
//                    Integer.parseInt(CR.getString(Constants.PASS_SHORT_BAD)))) + "%");
//            else
//                PredictionGoodSP.setText("0%");
//            PredictionOfGoodShortPass.addView(PredictionGoodSP);
//            final TextView CatchFaul = new TextView(this);
//            CatchFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                CatchFaul.setBackgroundColor(0xC2C2C2FF);
//            else
//                CatchFaul.setBackgroundColor(0);
//            CatchFaul.setText(CR.getString(Constants.CATCH_FAUL_SHOT));
//            CatcheFaulShotGoalkeeper.addView(CatchFaul);
//            final TextView MissFaul = new TextView(this);
//            MissFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                MissFaul.setBackgroundColor(0xC2C2C2FF);
//            else
//                MissFaul.setBackgroundColor(0);
//            MissFaul.setText(CR.getString(Constants.MISS_FAIL_SHOT));
//            MissFaulShotGoalkeeper.addView(MissFaul);
//            final TextView PredictionCatchFaul = new TextView(this);
//            PredictionCatchFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//            if(i % 2 != 0)
//                PredictionCatchFaul.setBackgroundColor(0xC2C2C2FF);
//            else
//                PredictionCatchFaul.setBackgroundColor(0);
//            if((Integer.parseInt(CR.getString(Constants.CATCH_FAUL_SHOT)) + Integer.parseInt(CR.getString(Constants.MISS_FAIL_SHOT))) != 0)
//                PredictionCatchFaul.setText(String.valueOf((Integer.parseInt(CR.getString(Constants.CATCH_FAUL_SHOT)) * 100) / (Integer.parseInt(CR.getString(Constants.CATCH_FAUL_SHOT)) +
//                    Integer.parseInt(CR.getString(Constants.MISS_FAIL_SHOT)))) + "%");
//            else
//                PredictionCatchFaul.setText("0%");
//            PredictionOfCatchFaulShot.addView(PredictionCatchFaul);
//
//            i++;
//        }while(CR.moveToNext());
//    }
//
//    public void ShowPlayersDetail(String PLAYERS_NUMBER) {
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        Buttons.setVisibility(View.GONE);
//        PlayersDetails = (RelativeLayout) findViewById(R.id.PlayersDetails);
//        PlayersDetails.setVisibility(View.VISIBLE);
//        PlayersTable = (ScrollView) findViewById(R.id.PlayersTable);
//        PlayersTable.setVisibility(View.GONE);
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        GameStatistic.setVisibility(View.GONE);
//        GoalkeeperDetails = (RelativeLayout) findViewById(R.id.GoalkeeperDetails);
//        GoalkeeperDetails.setVisibility(View.GONE);
//
//        TextView PLAYERS_NAME = (TextView) findViewById(R.id.PlayersName);
//        TextView VALUES_FOR_CHOOSEN_PLAYER = (TextView) findViewById(R.id.Values);
//        String CONCATENATE_OF_VALUES = "";
//        Cursor CR = DB.getInformationPlayers(DB, " where " + TableData.TableInfo.PlayerNumber + " = " + PLAYERS_NUMBER + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
//        CR.moveToFirst();
//        do {
//            PLAYERS_NAME.setText(CR.getString(Constants.PLAYER_FIRST_NAME) + " " + CR.getString(Constants.PLAYER_SECOND_NAME));
//            CONCATENATE_OF_VALUES = CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND) + "\n" + CR.getString(Constants.GOAL_ON_TARGET);
//            if(Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) + Integer.parseInt(CR.getString(Constants.SHOT_MISS)) + Integer.parseInt(CR.getString(Constants.SHOT_GOALKEEPER)) != 0) {
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "\n" + String.valueOf(Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) * 100 / (Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) +
//                        Integer.parseInt(CR.getString(Constants.SHOT_MISS)) + Integer.parseInt(CR.getString(Constants.SHOT_GOALKEEPER)))) + "%";
//            } else {
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "\n" + String.valueOf(Integer.parseInt(CR.getString(Constants.GOAL_ON_TARGET)) * 100) + "%";
//            }
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "\n" + CR.getString(Constants.SHOT_GOALKEEPER) + "\n" + CR.getString(Constants.SHOT_MISS) + "\n" + CR.getString(Constants.MISS_BALL) + "\n" +
//                    CR.getString(Constants.REACH_FAUL_SHOT) + "\n" + CR.getString(Constants.PLUS) + "\n" + CR.getString(Constants.MINUS) + "\n" + CR.getString(Constants.FAUL) + "\n" +
//                    CR.getString(Constants.YELLOW_CARD) + "\n" + CR.getString(Constants.RED_CARD) + "\n" + CR.getString(Constants.DATETIME_PK_PLAYER);
//        }while(CR.moveToNext());
//        VALUES_FOR_CHOOSEN_PLAYER.setText(CONCATENATE_OF_VALUES);
//    }
//
//    public void ShowGoalkeeperDetail(String Number) {
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        GoalkeepersTable = (ScrollView) findViewById(R.id.GoalkeepersTable);
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        GoalkeeperDetails = (RelativeLayout) findViewById(R.id.GoalkeeperDetails);
//        GameStatistic.setVisibility(View.GONE);
//        GoalkeepersTable.setVisibility(View.GONE);
//        Buttons.setVisibility(View.GONE);
//        GoalkeeperDetails.setVisibility(View.VISIBLE);
//
//        TextView PLAYERS_NAME = (TextView) findViewById(R.id.GoalkeeperName);
//        TextView VALUES_FOR_CHOOSEN_PLAYER = (TextView) findViewById(R.id.ValuesGoalkeeper);
//        String CONCATENATE_OF_VALUES = "";
//        Cursor CR = DB.getInformationGoalkeeper(DB, " where " + TableData.TableInfo.GoalkeeperNumber + " = " + Number + " AND " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK);
//        CR.moveToFirst();
//        do {
//            PLAYERS_NAME.setText(CR.getString(Constants.GOALKEEPER_FIRST_NAME) + " " + CR.getString(Constants.GOALKEEPER_SECOND_NAME));
//            CONCATENATE_OF_VALUES = CR.getString(Constants.TIME_ON_PLAYGROUND_GOALKEEPER) + "\n" + CR.getString(Constants.GOAL) + "\n" + CR.getString(Constants.CATCH) + "\n";
//            if(Integer.valueOf(CR.getString(Constants.GOAL)) + Integer.valueOf(CR.getString(Constants.CATCH)) != 0)
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(Constants.CATCH))*100)/(Integer.valueOf(CR.getString(Constants.GOAL)) +
//                    Integer.valueOf(CR.getString(Constants.CATCH)))) + "%") + "\n" +
//                    CR.getString(Constants.PASS_LONG_GOOD) + "\n" + CR.getString(Constants.PASS_LONG_BAD) + "\n";
//            else
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%"  + "\n" + CR.getString(Constants.PASS_LONG_GOOD) + "\n" + CR.getString(Constants.PASS_LONG_BAD) + "\n";
//            if(Integer.valueOf(CR.getString(Constants.PASS_LONG_GOOD)) + Integer.valueOf(CR.getString(Constants.PASS_LONG_BAD)) != 0)
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(Constants.PASS_LONG_GOOD)) * 100) / (Integer.valueOf(CR.getString(Constants.PASS_LONG_GOOD)) +
//                    Integer.valueOf(CR.getString(Constants.PASS_LONG_BAD)))) + "%") + "\n" + CR.getString(Constants.PASS_SHORT_GOOD) + "\n" + CR.getString(Constants.PASS_SHORT_BAD) + "\n";
//            else
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%" + "\n" + CR.getString(Constants.PASS_SHORT_GOOD) + "\n" + CR.getString(Constants.PASS_SHORT_BAD) + "\n";
//            if(Integer.valueOf(CR.getString(Constants.PASS_SHORT_GOOD)) + Integer.valueOf(CR.getString(Constants.PASS_SHORT_BAD)) != 0)
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(Constants.PASS_SHORT_GOOD)) * 100) / (Integer.valueOf(CR.getString(Constants.PASS_SHORT_GOOD)) +
//                    Integer.valueOf(CR.getString(Constants.PASS_SHORT_BAD)))) + "%") + "\n" + CR.getString(Constants.CATCH_FAUL_SHOT) + "\n" + CR.getString(Constants.MISS_FAIL_SHOT) + "\n";
//            else
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%" + "\n" + CR.getString(Constants.CATCH_FAUL_SHOT) + "\n" + CR.getString(Constants.MISS_FAIL_SHOT) + "\n";
//            if(Integer.valueOf(CR.getString(Constants.MISS_FAIL_SHOT)) + Integer.valueOf(CR.getString(Constants.CATCH_FAUL_SHOT)) != 0)
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(Constants.CATCH_FAUL_SHOT)) * 100) / (Integer.valueOf(CR.getString(Constants.MISS_FAIL_SHOT)) +
//                    Integer.valueOf(CR.getString(Constants.CATCH_FAUL_SHOT)))) + "%");
//            else
//                CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%";
//        }while(CR.moveToNext());
//        VALUES_FOR_CHOOSEN_PLAYER.setText(CONCATENATE_OF_VALUES);
//    }
//
//    public void ChangLayouts() {
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        PlayersTable = (ScrollView) findViewById(R.id.PlayersTable);
//        GoalkeepersTable = (ScrollView) findViewById(R.id.GoalkeepersTable);
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        PlayersDetails = (RelativeLayout) findViewById(R.id.PlayersDetails);
//        if(GameStatistic.getVisibility() == View.VISIBLE)
//            finish();
//        else {
//            if(GameStatistic.getVisibility() == View.GONE && Buttons.getVisibility() == View.GONE) {
//                if(GoalkeepersTable.getVisibility() == View.VISIBLE) {
//                    GoalkeepersTable.setVisibility(View.VISIBLE);
//                    PlayersTable.setVisibility(View.GONE);
//
//                } else {
//                    if(PlayersTable.getVisibility() == View.VISIBLE) {
//                        PlayersTable.setVisibility(View.VISIBLE);
//                        GoalkeepersTable.setVisibility(View.GONE);
//                    } else {
//                        if(PlayersDetails.getVisibility() == View.VISIBLE) {
//                            PlayersDetails.setVisibility(View.GONE);
//                            PlayersTable.setVisibility(View.VISIBLE);
//                        }
//                        if(GoalkeeperDetails.getVisibility() == View.VISIBLE) {
//                            GoalkeeperDetails.setVisibility(View.GONE);
//                            GoalkeepersTable.setVisibility(View.VISIBLE);
//                        }
//
//                    }
//                }
//                GameStatistic.setVisibility(View.VISIBLE);
//                Buttons.setVisibility(View.VISIBLE);
//            }
//        }
//    }
//
//    public void SetLayoutVisible(RelativeLayout MAKE_VISIBLE_THIS_LAYOUT_PLEASE) {
//        MAKE_VISIBLE_THIS_LAYOUT_PLEASE.setVisibility(View.VISIBLE);
//    }
//
//    public RelativeLayout SetLayoutGone(RelativeLayout MAKE_GONE_THIS_LAYOUT_PLEASE) {
//        MAKE_GONE_THIS_LAYOUT_PLEASE.setVisibility(View.GONE);
//        return MAKE_GONE_THIS_LAYOUT_PLEASE;
//    }
}
