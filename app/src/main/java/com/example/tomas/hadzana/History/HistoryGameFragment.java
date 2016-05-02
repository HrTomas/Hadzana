package com.example.tomas.hadzana.History;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.CountingFunctions;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class HistoryGameFragment extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {

    Context CX = this;
    DatabaseOperations DB = new DatabaseOperations(CX);
    RelativeLayout TableOfMatches, PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionGame;

    Integer TextSizeForScreens;

    private ViewPager tabsviewPager;
    private ActionBar mActionBar;
    private HistoryTabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.history_pager);
            TextSizeForScreens = 30;
        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_after_game_sumary_mobile);
//            TextSizeForScreens = 15;
        }

        tabsviewPager = (ViewPager) findViewById(R.id.tabspagerstatistic);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TurnamentPK = extras.getString("TurnamentPK");
        }

        if (TurnamentPK.equals("0")) {
            ConditionGame = "";
        } else {
            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
        }

        ShowActionBar();
    }

    public void ShowActionBar() {

        mTabsAdapter = new HistoryTabsAdapter(getSupportFragmentManager(), TurnamentPK);

        tabsviewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setTitle("Historia");
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
}
//
//    public void DeleteSurplusData() {
//        Cursor CRG = DB.getInformationGame(DB, "");
//        if(CRG.moveToFirst()) {
//            do {
//                if (CRG.getString(Constants.TURNAMENT_GAME_PK).equals("0"))
//                    DB.deleteGame(DB, CRG.getString(Constants.PK_NUMBER_GAME));
//            } while (CRG.moveToNext());
//        }
//    }
//
//    public void MatchStatistics() {
//
//        TableOfMatches = (RelativeLayout) findViewById(R.id.TableOfMatches);
//        TableOfMatches.setVisibility(View.VISIBLE);
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        GameStatistic.setVisibility(View.GONE);
//
//        LinearLayout MatchStatistics = (LinearLayout) findViewById(R.id.GameValues);
//        final Cursor CR = DB.getInformationGame(DB, ConditionGame);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(Constants.MATCH_SUCCESSFULLY_COMPLETED).equals("false"))
//                    DB.deleteGame(DB, CR.getString(Constants.PK_NUMBER_GAME));
//                else {
//                    final TextView OneRow = new TextView(this);
//                    OneRow.setText(CR.getString(Constants.TEAM_NAME) + " " + CR.getString(Constants.NAME_OF_OPONENT) + " " + CR.getString(Constants.FINAL_SCORE) + " " + CR.getString(Constants.DATETIME_PK_GAME));
//                    OneRow.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                    OneRow.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ShowDetails(new StringBuffer((new StringBuffer(OneRow.getText().toString()).reverse().toString()).split(" ")[1]).reverse().toString() + " " + new StringBuffer((new StringBuffer(OneRow.getText().toString()).reverse().toString()).split(" ")[0]).reverse().toString());
//                        }
//                    });
//                    MatchStatistics.addView(OneRow);
//                }
//            }while(CR.moveToNext());
//        }
//    }
//
//    public void ShowDetails(final String DateTime) {
//        TableOfMatches = (RelativeLayout) findViewById(R.id.TableOfMatches);
//        TableOfMatches.setVisibility(View.GONE);
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        GameStatistic.setVisibility(View.VISIBLE);
//        Cursor CR = DB.getInformationGame(DB, " WHERE " + TableData.TableInfo.DateTimePKGame + " == " + "\"" + DateTime + "\"" + " AND " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK);
//        if(CR.moveToFirst()) {
//            do {
//                TextView OFENSIVE_TIME, DEFENSIVE_TIME, OFENSIVE_TIME_PERCENT, DEFENSIVE_TIME_PERCENT, SHOTS, PERCENTS_SHOTS, MISS_BALLS, FAULS, YELLOW_CARDS, RED_CARDS, PLUS_COUNT, MINUS_COUNT, SCORE, HALF_TIME_SCORE, NAMES_OF_TEAMS;
//                OFENSIVE_TIME = (TextView) findViewById(R.id.OfensiveTime);
//                DEFENSIVE_TIME = (TextView) findViewById(R.id.DefensiveTime);
//                OFENSIVE_TIME_PERCENT = (TextView) findViewById(R.id.OfensiveTimePercent);
//                DEFENSIVE_TIME_PERCENT = (TextView) findViewById(R.id.DefensiveTimePercent);
//                SHOTS = (TextView) findViewById(R.id.CountOfShots);
//                PERCENTS_SHOTS = (TextView) findViewById(R.id.PercentsShots);
//                MISS_BALLS = (TextView) findViewById(R.id.MissBals);
//                FAULS = (TextView) findViewById(R.id.FaulCount);
//                YELLOW_CARDS = (TextView) findViewById(R.id.YellowCardsCount);
//                RED_CARDS = (TextView) findViewById(R.id.RedCardsCount);
//                PLUS_COUNT = (TextView) findViewById(R.id.PlusCount);
//                MINUS_COUNT = (TextView) findViewById(R.id.MinusCount);
//                SCORE = (TextView) findViewById(R.id.Score);
//                HALF_TIME_SCORE = (TextView) findViewById(R.id.HalftimeScore);
//                NAMES_OF_TEAMS = (TextView) findViewById(R.id.TeamNames);
//
//                NAMES_OF_TEAMS.setText(CR.getString(0) + " vs " + CR.getString(1));
//                HALF_TIME_SCORE.setText(CR.getString(2));
//                SCORE.setText(CR.getString(3));
//                OFENSIVE_TIME.setText(CR.getString(4));
//                if(Integer.parseInt(CR.getString(4).split(":")[0]) != 0)
//                    OFENSIVE_TIME_PERCENT.setText(String.valueOf((((Integer.parseInt(CR.getString(4).split(":")[0]) * 60) + Integer.parseInt(CR.getString(4).split(":")[1])) * 100) / (Integer.parseInt(CR.getString(6).split(":")[0]) * 60)) + "%");
//                else
//                    OFENSIVE_TIME_PERCENT.setText("0%");
//                DEFENSIVE_TIME.setText(CR.getString(5));
//                if(Integer.parseInt(CR.getString(5).split(":")[0]) != 0)
//                    DEFENSIVE_TIME_PERCENT.setText(String.valueOf((((Integer.parseInt(CR.getString(5).split(":")[0]) * 60) + Integer.parseInt(CR.getString(5).split(":")[1])) * 100) / (Integer.parseInt(CR.getString(6).split(":")[0]) * 60)) + "%");
//                else
//                    DEFENSIVE_TIME_PERCENT.setText("0%");
//                int SHOTS_INTEGER = 0;
//                int GOALS = 0;
//                int MISS_BALLS_INTEGER = 0;
//                int FAULS_INTEGER = 0;
//                int YELLOW_CARDS_INTEGER = 0;
//                int RED_CARDS_INTEGER = 0;
//                int PLUS_INTEGER = 0;
//                int MINUS_INTEGER = 0;
//                Cursor CRP = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers + TableData.TableInfo.DatetimaPlayerHistory + " == " + "\"" + DateTime + "\"");
//                if(CRP.moveToFirst()) {
//                    do {
//                        if(CRP.getString(0).split("#")[15].equals(DateTime)) {
//                            SHOTS_INTEGER = SHOTS_INTEGER + Integer.parseInt(CRP.getString(0).split("#")[3]) + Integer.parseInt(CRP.getString(0).split("#")[4]) + Integer.parseInt(CRP.getString(0).split("#")[5]);
//                            GOALS = GOALS + Integer.parseInt(CRP.getString(0).split("#")[3]);
//                            MISS_BALLS_INTEGER = MISS_BALLS_INTEGER + Integer.parseInt(CRP.getString(0).split("#")[6]);
//                            FAULS_INTEGER = FAULS_INTEGER + Integer.parseInt(CRP.getString(0).split("#")[10]);
//                            YELLOW_CARDS_INTEGER = YELLOW_CARDS_INTEGER + Integer.parseInt(CRP.getString(0).split("#")[11]);
//                            RED_CARDS_INTEGER = RED_CARDS_INTEGER + Integer.parseInt(CRP.getString(0).split("#")[12]);
//                            PLUS_INTEGER = PLUS_INTEGER + Integer.parseInt(CRP.getString(0).split("#")[8]);
//                            MINUS_INTEGER = MINUS_INTEGER + Integer.parseInt(CRP.getString(0).split("#")[9]);
//                        }
//                    }while(CRP.moveToNext());
//                    SHOTS.setText(String.valueOf(SHOTS_INTEGER));
//                    if(SHOTS_INTEGER == 0)
//                        PERCENTS_SHOTS.setText(String.valueOf("0%"));
//                    else
//                        PERCENTS_SHOTS.setText(String.valueOf((100*GOALS)/SHOTS_INTEGER) + "%");
//                    MISS_BALLS.setText(String.valueOf(MISS_BALLS_INTEGER));
//                    FAULS.setText(String.valueOf(FAULS_INTEGER));
//                    YELLOW_CARDS.setText(String.valueOf(YELLOW_CARDS_INTEGER));
//                    RED_CARDS.setText(String.valueOf(RED_CARDS_INTEGER));
//                    PLUS_COUNT.setText(String.valueOf(PLUS_INTEGER));
//                    MINUS_COUNT.setText(String.valueOf(MINUS_INTEGER));
//                }
//            }while(CR.moveToNext());
//        }
//
//        BarChart chart = (BarChart) findViewById(R.id.chart);
//        BarData data = new BarData(getXAxisValues(DateTime), getDataSet(DateTime));
//        chart.setData(data);
//        chart.setDescription("");
//        chart.animateXY(2000, 2000);
//        chart.invalidate();
//
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        GameStatistic.setVisibility(View.VISIBLE);
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        Buttons.setVisibility(View.VISIBLE);
//        GoalkeepersTable = (ScrollView) findViewById(R.id.GoalkeepersTable);
//        PlayersTable = (ScrollView) findViewById(R.id.PlayersTable);
//        GraphGameRecord = (RelativeLayout) findViewById(R.id.GraphGameRecord);
//        GraphGameRecord.setVisibility(View.VISIBLE);
//
//        Button ShowPlayers, ShowGameRecord, ShowGoalkeepers;
//        ShowPlayers = (Button) findViewById(R.id.ShowPlayers);
//        ShowPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PlayersTable.setVisibility(View.VISIBLE);
//                GoalkeepersTable.setVisibility(View.GONE);
//                GraphGameRecord.setVisibility(View.GONE);
//                ShowTableOfPlayers(DateTime);
//            }
//        });
//        ShowPlayers.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                GameStatistic.setVisibility(View.GONE);
//                Buttons.setVisibility(View.GONE);
//                GoalkeepersTable.setVisibility(View.GONE);
//                PlayersTable.setVisibility(View.VISIBLE);
//                ShowTableOfPlayers(DateTime);
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
//                ShowTableOfGoalkeepers(DateTime);
//            }
//        });
//        ShowGoalkeepers.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                GameStatistic.setVisibility(View.GONE);
//                Buttons.setVisibility(View.GONE);
//                PlayersTable.setVisibility(View.GONE);
//                GoalkeepersTable.setVisibility(View.VISIBLE);
//                ShowTableOfGoalkeepers(DateTime);
//                return true;
//            }
//        });
//    }
//
//    public void ShowTableOfPlayers(final String DateTime) {
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
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers + TableData.TableInfo.DatetimaPlayerHistory + " == " + "\"" + DateTime + "\"");
//        CR.moveToFirst();
//        do {
//            if(CR.getString(0).split("#")[15].equals(DateTime)) {
//                final TextView Numbers = new TextView(this);
//                Numbers.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Numbers.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Numbers.setBackgroundColor(0);
//                Numbers.setText(CR.getString(0).split("#")[2]);
//                PlayersNumbers.addView(Numbers);
//                final TextView Names = new TextView(this);
//                Names.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Names.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Names.setBackgroundColor(0);
//                Names.setText(CR.getString(0).split("#")[0].toCharArray()[0] + ". " + CR.getString(0).split("#")[1]);
//                Names.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ShowPlayersDetail(Numbers.getText().toString(), DateTime);
//                    }
//                });
//                PlayersNames.addView(Names);
//                final TextView Times = new TextView(this);
//                Times.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Times.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Times.setBackgroundColor(0);
//                Times.setText(CR.getString(0).split("#")[14]);
//                TimeOnPlaygroundPlayers.addView(Times);
//                final TextView CountOfShots = new TextView(this);
//                CountOfShots.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    CountOfShots.setBackgroundColor(0xC2C2C2FF);
//                else
//                    CountOfShots.setBackgroundColor(0);
//                CountOfShots.setText(String.valueOf(Integer.parseInt(CR.getString(0).split("#")[3]) + Integer.parseInt(CR.getString(0).split("#")[4]) + Integer.parseInt(CR.getString(0).split("#")[5])));
//                ShotsPlayers.addView(CountOfShots);
//                final TextView Goals = new TextView(this);
//                Goals.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Goals.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Goals.setBackgroundColor(0);
//                Goals.setText(CR.getString(0).split("#")[3]);
//                GoalsPlayers.addView(Goals);
//                final TextView MissShots = new TextView(this);
//                MissShots.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    MissShots.setBackgroundColor(0xC2C2C2FF);
//                else
//                    MissShots.setBackgroundColor(0);
//                MissShots.setText(CR.getString(0).split("#")[4]);
//                MissShotsPlayers.addView(MissShots);
//                final TextView CatchByGoalkeeper = new TextView(this);
//                CatchByGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    CatchByGoalkeeper.setBackgroundColor(0xC2C2C2FF);
//                else
//                    CatchByGoalkeeper.setBackgroundColor(0);
//                CatchByGoalkeeper.setText(CR.getString(0).split("#")[5]);
//                CatchByGoalkPlayers.addView(CatchByGoalkeeper);
//                final TextView Prediction = new TextView(this);
//                Prediction.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Prediction.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Prediction.setBackgroundColor(0);
//                if(Integer.parseInt(CR.getString(0).split("#")[3]) + Integer.parseInt(CR.getString(0).split("#")[4]) + Integer.parseInt(CR.getString(0).split("#")[5]) != 0)
//                    Prediction.setText(String.valueOf((Integer.parseInt(CR.getString(0).split("#")[3]) * 100) / (Integer.parseInt(CR.getString(0).split("#")[3]) + Integer.parseInt(CR.getString(0).split("#")[4]) + Integer.parseInt(CR.getString(0).split("#")[5]))) + "%");
//                else
//                    Prediction.setText("0%");
//                PredictionOfShots.addView(Prediction);
//                final TextView MissBalls = new TextView(this);
//                MissBalls.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    MissBalls.setBackgroundColor(0xC2C2C2FF);
//                else
//                    MissBalls.setBackgroundColor(0);
//                MissBalls.setText(CR.getString(0).split("#")[6]);
//                MissBallsPlayers.addView(MissBalls);
//                final TextView ReachFaul = new TextView(this);
//                ReachFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    ReachFaul.setBackgroundColor(0xC2C2C2FF);
//                else
//                    ReachFaul.setBackgroundColor(0);
//                ReachFaul.setText(CR.getString(0).split("#")[7]);
//                ReachFaulShotPlayers.addView(ReachFaul);
//                final TextView Plus = new TextView(this);
//                Plus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Plus.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Plus.setBackgroundColor(0);
//                Plus.setText(CR.getString(0).split("#")[8]);
//                PlusPlayers.addView(Plus);
//                final TextView Minus = new TextView(this);
//                Minus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Minus.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Minus.setBackgroundColor(0);
//                Minus.setText(CR.getString(0).split("#")[9]);
//                MinusPlayers.addView(Minus);
//                final TextView Fauls = new TextView(this);
//                Fauls.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Fauls.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Fauls.setBackgroundColor(0);
//                Fauls.setText(CR.getString(0).split("#")[10]);
//                FaulsPlayers.addView(Fauls);
//                final TextView Yellow = new TextView(this);
//                Yellow.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Yellow.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Yellow.setBackgroundColor(0);
//                Yellow.setText(CR.getString(0).split("#")[11]);
//                YellowCardsPlayers.addView(Yellow);
//                final TextView Red = new TextView(this);
//                Red.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Red.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Red.setBackgroundColor(0);
//                Red.setText(CR.getString(0).split("#")[12]);
//                RedCardsPlayers.addView(Red);
//                final TextView ReachBall = new TextView(this);
//                ReachBall.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    ReachBall.setBackgroundColor(0xC2C2C2FF);
//                else
//                    ReachBall.setBackgroundColor(0);
//                ReachBall.setText(CR.getString(0).split("#")[13]);
//                ReachBallsPlayers.addView(ReachBall);
//
//                i++;
//            }
//        }while(CR.moveToNext());
//    }
//
//    public void ShowPlayersDetail(String PlayersNumber, String DateTime) {
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
//        String CONCATENATE_OF_VALUES = "";
//        TextView Values = (TextView) findViewById(R.id.Values);
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers + TableData.TableInfo.DatetimaPlayerHistory + " == " + "\"" + DateTime + "\"");
//        Integer i = 0;
//        if(CR.moveToFirst()) {
//            i = 0;
//            do {
//                if(CR.getString(0).split("#")[15].equals(DateTime) && CR.getString(0).split("#")[2].equals(PlayersNumber)){
//                    PLAYERS_NAME.setText(CR.getString(0).split("#")[0] + " " + CR.getString(0).split("#")[1]);
//                    CONCATENATE_OF_VALUES = CR.getString(0).split("#")[14] + "\n" + CR.getString(0).split("#")[3];
//                    if(Integer.parseInt(CR.getString(0).split("#")[3]) + Integer.parseInt(CR.getString(0).split("#")[4]) + Integer.parseInt(CR.getString(0).split("#")[5]) != 0) {
//                        CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "\n" + String.valueOf(Integer.parseInt(CR.getString(0).split("#")[3]) * 100 / (Integer.parseInt(CR.getString(0).split("#")[3]) + Integer.parseInt(CR.getString(0).split("#")[4]) +
//                                Integer.parseInt(CR.getString(0).split("#")[5]))) + "%";
//                    } else {
//                        CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "\n" + String.valueOf(Integer.parseInt(CR.getString(0).split("#")[3]) * 100) + "%";
//                    }
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "\n" + CR.getString(0).split("#")[5] + "\n" + CR.getString(0).split("#")[4] + "\n" + CR.getString(0).split("#")[6] + "\n" + CR.getString(0).split("#")[7] + "\n" + CR.getString(0).split("#")[8] +
//                            "\n" + CR.getString(0).split("#")[9] + "\n" + CR.getString(0).split("#")[10] + "\n" + CR.getString(0).split("#")[11] + "\n" + CR.getString(0).split("#")[12] + "\n" + CR.getString(0).split("#")[15];
//                }
//            }while(CR.moveToNext());
//            Values.setText(CONCATENATE_OF_VALUES);
//        }
//    }
//
//    public void ShowTableOfGoalkeepers(final String DateTime) {
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
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers + TableData.TableInfo.DatetimaPlayerHistory + " == " + "\"" + DateTime + "\"");
//        CR.moveToFirst();
//        do {
//            if(CR.getString(0).split("#")[13].equals(DateTime)) {
//                final TextView Numbers = new TextView(this);
//                Numbers.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Numbers.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Numbers.setBackgroundColor(0);
//                Numbers.setText(CR.getString(0).split("#")[2]);
//                GoalkeepersNumbers.addView(Numbers);
//                final TextView Names = new TextView(this);
//                Names.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Names.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Names.setBackgroundColor(0);
//                Names.setText(CR.getString(0).split("#")[0].toCharArray()[0] + ". " + CR.getString(0).split("#")[1]);
//                Names.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ShowGoalkeeperDetail(Numbers.getText().toString(), DateTime);
//                    }
//                });
//                GoalkeepersNames.addView(Names);
//                final TextView Times = new TextView(this);
//                Times.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Times.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Times.setBackgroundColor(0);
//                Times.setText(CR.getString(0).split("#")[12]);
//                TimeOnPlaygroundGoalkeepers.addView(Times);
//                final TextView Recovered = new TextView(this);
//                Recovered.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Recovered.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Recovered.setBackgroundColor(0);
//                Recovered.setText(CR.getString(0).split("#")[10]);
//                RecoveredGoalsGoalkeepers.addView(Recovered);
//                final TextView Catch = new TextView(this);
//                Catch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    Catch.setBackgroundColor(0xC2C2C2FF);
//                else
//                    Catch.setBackgroundColor(0);
//                Catch.setText(CR.getString(0).split("#")[3]);
//                CatchShotsGoalkeepers.addView(Catch);
//                final TextView PredictionCatch = new TextView(this);
//                PredictionCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    PredictionCatch.setBackgroundColor(0xC2C2C2FF);
//                else
//                    PredictionCatch.setBackgroundColor(0);
//                if((Integer.parseInt(CR.getString(0).split("#")[10]) + Integer.parseInt(CR.getString(0).split("#")[3])) != 0)
//                    PredictionCatch.setText(String.valueOf((Integer.parseInt(CR.getString(0).split("#")[3]) * 100) / (Integer.parseInt(CR.getString(0).split("#")[10]) + Integer.parseInt(CR.getString(0).split("#")[3]))) + "%");
//                else
//                    PredictionCatch.setText("0%");
//                PredictionOfCathe.addView(PredictionCatch);
//                final TextView GoodLP = new TextView(this);
//                GoodLP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    GoodLP.setBackgroundColor(0xC2C2C2FF);
//                else
//                    GoodLP.setBackgroundColor(0);
//                GoodLP.setText(CR.getString(0).split("#")[4]);
//                GoodLongPassGoalkeepers.addView(GoodLP);
//                final TextView BadLP = new TextView(this);
//                BadLP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    BadLP.setBackgroundColor(0xC2C2C2FF);
//                else
//                    BadLP.setBackgroundColor(0);
//                BadLP.setText(CR.getString(0).split("#")[5]);
//                BadLongPassGoalkeepers.addView(BadLP);
//                final TextView PredictionGoodLP = new TextView(this);
//                PredictionGoodLP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    PredictionGoodLP.setBackgroundColor(0xC2C2C2FF);
//                else
//                    PredictionGoodLP.setBackgroundColor(0);
//                if((Integer.parseInt(CR.getString(0).split("#")[4]) + Integer.parseInt(CR.getString(0).split("#")[5])) != 0)
//                    PredictionGoodLP.setText(String.valueOf((Integer.parseInt(CR.getString(0).split("#")[4]) * 100) / (Integer.parseInt(CR.getString(0).split("#")[4]) + Integer.parseInt(CR.getString(0).split("#")[5]))) + "%");
//                else
//                    PredictionGoodLP.setText("0%");
//                PredictionOfGoodLongPass.addView(PredictionGoodLP);
//                final TextView GoodSP = new TextView(this);
//                GoodSP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    GoodSP.setBackgroundColor(0xC2C2C2FF);
//                else
//                    GoodSP.setBackgroundColor(0);
//                GoodSP.setText(CR.getString(0).split("#")[6]);
//                GoodShortPassGoalkeepers.addView(GoodSP);
//                final TextView BadSP = new TextView(this);
//                BadSP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    BadSP.setBackgroundColor(0xC2C2C2FF);
//                else
//                    BadSP.setBackgroundColor(0);
//                BadSP.setText(CR.getString(0).split("#")[7]);
//                BadShortPassGoalkeepers.addView(BadSP);
//                final TextView PredictionGoodSP = new TextView(this);
//                PredictionGoodSP.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    PredictionGoodSP.setBackgroundColor(0xC2C2C2FF);
//                else
//                    PredictionGoodSP.setBackgroundColor(0);
//                if((Integer.parseInt(CR.getString(0).split("#")[6]) + Integer.parseInt(CR.getString(0).split("#")[7])) != 0)
//                    PredictionGoodSP.setText(String.valueOf((Integer.parseInt(CR.getString(0).split("#")[6]) * 100) / (Integer.parseInt(CR.getString(0).split("#")[6]) + Integer.parseInt(CR.getString(0).split("#")[7]))) + "%");
//                else
//                    PredictionGoodSP.setText("0%");
//                PredictionOfGoodShortPass.addView(PredictionGoodSP);
//                final TextView CatchFaul = new TextView(this);
//                CatchFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    CatchFaul.setBackgroundColor(0xC2C2C2FF);
//                else
//                    CatchFaul.setBackgroundColor(0);
//                CatchFaul.setText(CR.getString(0).split("#")[8]);
//                CatcheFaulShotGoalkeeper.addView(CatchFaul);
//                final TextView MissFaul = new TextView(this);
//                MissFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    MissFaul.setBackgroundColor(0xC2C2C2FF);
//                else
//                    MissFaul.setBackgroundColor(0);
//                MissFaul.setText(CR.getString(0).split("#")[9]);
//                MissFaulShotGoalkeeper.addView(MissFaul);
//                final TextView PredictionCatchFaul = new TextView(this);
//                PredictionCatchFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
//                if(i % 2 != 0)
//                    PredictionCatchFaul.setBackgroundColor(0xC2C2C2FF);
//                else
//                    PredictionCatchFaul.setBackgroundColor(0);
//                if((Integer.parseInt(CR.getString(0).split("#")[8]) + Integer.parseInt(CR.getString(0).split("#")[9])) != 0)
//                    PredictionCatchFaul.setText(String.valueOf((Integer.parseInt(CR.getString(0).split("#")[8]) * 100) / (Integer.parseInt(CR.getString(0).split("#")[8]) + Integer.parseInt(CR.getString(0).split("#")[9]))) + "%");
//                else
//                    PredictionCatchFaul.setText("0%");
//                PredictionOfCatchFaulShot.addView(PredictionCatchFaul);
//
//                i++;
//            }
//        }while(CR.moveToNext());
//    }
//
//    public void ShowGoalkeeperDetail(String Number, String DateTime) {
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        Buttons.setVisibility(View.GONE);
//        PlayersDetails = (RelativeLayout) findViewById(R.id.PlayersDetails);
//        PlayersDetails.setVisibility(View.GONE);
//        PlayersTable = (ScrollView) findViewById(R.id.PlayersTable);
//        PlayersTable.setVisibility(View.GONE);
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        GameStatistic.setVisibility(View.GONE);
//        GoalkeeperDetails = (RelativeLayout) findViewById(R.id.GoalkeeperDetails);
//        GoalkeeperDetails.setVisibility(View.VISIBLE);
//        GoalkeepersTable = (ScrollView) findViewById(R.id.GoalkeepersTable);
//        GoalkeepersTable.setVisibility(View.GONE);
//
//        TextView PLAYERS_NAME = (TextView) findViewById(R.id.GoalkeeperName);
//        TextView VALUES_FOR_CHOOSEN_PLAYER = (TextView) findViewById(R.id.ValuesGoalkeeper);
//        String CONCATENATE_OF_VALUES = "";
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers + TableData.TableInfo.DatetimaPlayerHistory + " == " + "\"" + DateTime + "\"");
//        CR.moveToFirst();
//        do {
//            if(CR.getString(0).split("#")[13].equals(DateTime) && CR.getString(0).split("#")[2].equals(Number)) {
//                PLAYERS_NAME.setText(CR.getString(0).split("#")[0] + " " + CR.getString(0).split("#")[1]);
//                CONCATENATE_OF_VALUES = CR.getString(0).split("#")[12] + "\n" + CR.getString(0).split("#")[10] + "\n" + CR.getString(0).split("#")[3] + "\n";
//                if(Integer.valueOf(CR.getString(0).split("#")[10]) + Integer.valueOf(CR.getString(0).split("#")[3]) != 0)
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(0).split("#")[3])*100)/(Integer.valueOf(CR.getString(0).split("#")[10]) + Integer.valueOf(CR.getString(0).split("#")[3]))) + "%") + "\n" +
//                            CR.getString(0).split("#")[4] + "\n" + CR.getString(0).split("#")[5] + "\n";
//                else
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%"  + "\n" + CR.getString(0).split("#")[4] + "\n" + CR.getString(0).split("#")[5] + "\n";
//                if(Integer.valueOf(CR.getString(0).split("#")[4]) + Integer.valueOf(CR.getString(0).split("#")[5]) != 0)
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(0).split("#")[4]) * 100) / (Integer.valueOf(CR.getString(0).split("#")[4]) + Integer.valueOf(CR.getString(0).split("#")[5]))) + "%") + "\n" +
//                            CR.getString(0).split("#")[6] + "\n" + CR.getString(0).split("#")[7] + "\n";
//                else
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%" + "\n" + CR.getString(0).split("#")[6] + "\n" + CR.getString(0).split("#")[7] + "\n";
//                if(Integer.valueOf(CR.getString(0).split("#")[6]) + Integer.valueOf(CR.getString(0).split("#")[7]) != 0)
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(0).split("#")[6]) * 100) / (Integer.valueOf(CR.getString(0).split("#")[6]) + Integer.valueOf(CR.getString(0).split("#")[7]))) + "%") + "\n" +
//                            CR.getString(0).split("#")[8] + "\n" + CR.getString(0).split("#")[9] + "\n";
//                else
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%" + "\n" + CR.getString(0).split("#")[8] + "\n" + CR.getString(0).split("#")[9] + "\n";
//                if(Integer.valueOf(CR.getString(0).split("#")[9]) + Integer.valueOf(CR.getString(0).split("#")[8]) != 0)
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf(((Integer.valueOf(CR.getString(0).split("#")[8]) * 100) / (Integer.valueOf(CR.getString(0).split("#")[9]) + Integer.valueOf(CR.getString(0).split("#")[8]))) + "%");
//                else
//                    CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%";
//            }
//        }while(CR.moveToNext());
//        VALUES_FOR_CHOOSEN_PLAYER.setText(CONCATENATE_OF_VALUES);
//    }
//
//    private ArrayList<BarDataSet> getDataSet(final String DateTime) {
//        ArrayList<BarDataSet> dataSets = null;
//        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
//        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
//        int i = 0;
//        Cursor CR = DB.getInformationGame(DB, ConditionGame);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(Constants.DATETIME_PK_GAME).equals(DateTime)) {
//                    for(int j = 0; j < CR.getString(Constants.GAME_REPLAY).split("#").length; j++) {
//                        BarEntry v1e1 = new BarEntry(Float.parseFloat(CR.getString(Constants.GAME_REPLAY).split("#")[j].split(":")[0]), j);
//                        valueSet1.add(v1e1);
//                        BarEntry v2e1 = new BarEntry(Float.parseFloat(CR.getString(Constants.GAME_REPLAY).split("#")[j].split(":")[1]), j);
//                        valueSet2.add(v2e1);
//                    }
//                }
//            }while(CR.moveToNext());
//        }
//
//        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Strelene goly");
//        barDataSet1.setColor(Color.rgb(0, 155, 0));
//        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Inkasovane");
//        barDataSet2.setColor(Color.rgb(0, 0, 155));
//
//        dataSets = new ArrayList<>();
//        dataSets.add(barDataSet1);
//        dataSets.add(barDataSet2);
//        return dataSets;
//    }
//
//    private ArrayList<String> getXAxisValues(final String DateTime) {
//        ArrayList<String> xAxis = new ArrayList<>();
//        Cursor CR = DB.getInformationGame(DB, ConditionGame);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(Constants.DATETIME_PK_GAME).equals(DateTime)) {
//                    for(int j = 1; j <= CR.getString(Constants.GAME_REPLAY).split("#").length; j++) {
//                        xAxis.add(String.valueOf((Integer.parseInt(CR.getString(Constants.MATCH_TIME).split(":")[0])/CR.getString(Constants.GAME_REPLAY).split("#").length)*j)+":00");
//                    }
//                }
//            }while(CR.moveToNext());
//        }
//        return xAxis;
//    }
//
//    public void ChangLayouts() {
//        TableOfMatches = (RelativeLayout) findViewById(R.id.TableOfMatches);
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        PlayersDetails = (RelativeLayout) findViewById(R.id.PlayersDetails);
//        PlayersTable = (ScrollView) findViewById(R.id.PlayersTable);
//        GoalkeeperDetails = (RelativeLayout) findViewById(R.id.GoalkeeperDetails);
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        PlayersDetails = (RelativeLayout) findViewById(R.id.PlayersDetails);
//        GoalkeepersTable = (ScrollView) findViewById(R.id.GoalkeepersTable);
//        GraphGameRecord = (RelativeLayout) findViewById(R.id.GraphGameRecord);
//        if(TableOfMatches.getVisibility() == View.VISIBLE)
//            finish();
//        else {
//            if (GameStatistic.getVisibility() == View.VISIBLE) {
//                SetLayoutGone(GameStatistic);
//                SetLayoutVisible(TableOfMatches);
//                SetLayoutGone(Buttons);
//                PlayersTable.setVisibility(View.GONE);
//                GoalkeepersTable.setVisibility(View.GONE);
//                GraphGameRecord.setVisibility(View.GONE);
//            } else {
//                if(PlayersDetails.getVisibility() == View.VISIBLE) {
//                    SetLayoutGone(PlayersDetails);
//                    SetLayoutVisible(GameStatistic);
//                    Buttons.setVisibility(View.VISIBLE);
//                    PlayersTable.setVisibility(View.VISIBLE);
//                } else {
//                    if(GoalkeeperDetails.getVisibility() == View.VISIBLE) {
//                        SetLayoutGone(GoalkeeperDetails);
//                        SetLayoutVisible(GameStatistic);
//                        Buttons.setVisibility(View.VISIBLE);
//                        GoalkeepersTable.setVisibility(View.VISIBLE);
//                    } else {
//                        if (GameStatistic.getVisibility() == View.GONE && Buttons.getVisibility() == View.GONE) {
//                            if (GoalkeepersTable.getVisibility() == View.VISIBLE) {
//                                GoalkeepersTable.setVisibility(View.VISIBLE);
//                                PlayersTable.setVisibility(View.GONE);
//                            } else {
//                                if (PlayersTable.getVisibility() == View.VISIBLE) {
//                                    PlayersTable.setVisibility(View.VISIBLE);
//                                    GoalkeepersTable.setVisibility(View.GONE);
//                                } else {
//                                    GraphGameRecord.setVisibility(View.VISIBLE);
//                                }
//                            }
//                            GameStatistic.setVisibility(View.VISIBLE);
//                            Buttons.setVisibility(View.VISIBLE);
//                        }
//                    }
//                }
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
//}
