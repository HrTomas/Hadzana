package com.example.tomas.hadzana.Summary;


import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
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

public class Summary extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {

    Context CX = this;
    DatabaseOperations DB = new DatabaseOperations(CX);
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    private ViewPager tabsviewPager;
    private ActionBar mActionBar;
    private StatisticTabsadapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 600) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_summary);
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
        DeleteSurplusData();

        CountingFunctions CF = new CountingFunctions(CX);
        CF.SummaryGame(TurnamentPK);
        CF.SummaryPlayer(TurnamentPK);
        CF.SummaryGoalkeeper(TurnamentPK);

        mTabsAdapter = new StatisticTabsadapter(getSupportFragmentManager(), TurnamentPK);

        tabsviewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setTitle("Statistika");
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab SummaryGame = getSupportActionBar().newTab().setText("  Hra  ").setTabListener(this);
        ActionBar.Tab SummaryPowerplay = getSupportActionBar().newTab().setText("  Presilovky  ").setTabListener(this);
        ActionBar.Tab SummaryOfensive = getSupportActionBar().newTab().setText("  Utok  ").setTabListener(this);
        ActionBar.Tab SummaryDefensive = getSupportActionBar().newTab().setText("  Obrana  ").setTabListener(this);
        ActionBar.Tab SummaryPlayers = getSupportActionBar().newTab().setText("  Hraci  ").setTabListener(this);
        ActionBar.Tab SummaryGoalkeepers = getSupportActionBar().newTab().setText("  Brankari  ").setTabListener(this);

        getSupportActionBar().addTab(SummaryGame);
        getSupportActionBar().addTab(SummaryPowerplay);
        getSupportActionBar().addTab(SummaryOfensive);
        getSupportActionBar().addTab(SummaryDefensive);
        getSupportActionBar().addTab(SummaryPlayers);
        getSupportActionBar().addTab(SummaryGoalkeepers);

        //This helps in providing swiping effect for v7 compat library
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

    public void DeleteSurplusData() {
        boolean Match = false;
        String DateTime = "";
        Cursor CRG = DB.getInformationGame(DB, ConditionGame);
        if(CRG.moveToFirst()) {
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

//        Bundle extras = getIntent().getExtras();

//        if (extras != null) {
//            TurnamentPK = extras.getString("TurnamentPK");
//        }
//        if(TurnamentPK.equals("0")) {
//            ConditionPlayer = "";
//            ConditionGoalkeeper = "";
//            ConditionTableOfPlayers = "";
//            ConditionGame = "";
//            ConditionGameOptions = "";
//        } else {
//            ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK;
//            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
//            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
//            ConditionTableOfPlayers = " WHERE " + TableData.TableInfo.TurnamentTableOfPlayerPK + " == " + TurnamentPK;
//            ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
//        }
//
//        DeleteSurplusData();
//        Cursor CR = DB.getInformationGame(DB, "");
//        if(!CR.moveToFirst()) {
//            Toast.makeText(getBaseContext(), "Ziadne odohrane zapasy.", Toast.LENGTH_LONG).show();
//            CR.close();
//            finish();
//        }
//        GameStatistic = (RelativeLayout) findViewById(R.id.GameStatistic);
//        Buttons = (RelativeLayout) findViewById(R.id.Buttons);
//        GoalkeepersTable = (ScrollView) findViewById(R.id.GoalkeepersTable);
//        PlayersTable = (ScrollView) findViewById(R.id.PlayersTable);
//        GraphGameRecord = (RelativeLayout) findViewById(R.id.GraphGameRecord);
//        CompareLayout = (RelativeLayout) findViewById(R.id.CompareLayout);
//        HideEverything = (RelativeLayout) findViewById(R.id.HideEverything);
//
//        CompareFunction();
//
//        Button ShowPlayers, ShowGameRecord, ShowGoalkeepers, Compare;
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
//        Compare = (Button) findViewById(R.id.Compare);
//        Compare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HideEverything.setVisibility(View.GONE);
//                CompareLayout.setVisibility(View.VISIBLE);
//                CompareFunction();
//            }
//        });
//
//        BarChart chart = (BarChart) findViewById(R.id.chart);
//        BarData data = new BarData(getXAxisValues(), getDataSet());
//        chart.setData(data);
//        chart.setDescription("");
//        chart.animateXY(2000, 2000);
//        chart.invalidate();
//
//        GameSummary();

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        switch(keyCode){
//            case KeyEvent.KEYCODE_BACK:
//                ChangLayouts();
//                return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//
//
//
//    public String CountValue(String PlayersNumber, Integer PositionInString) {
//        Integer FinalCount = 0;
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(0).split("#")[2].equals(PlayersNumber)) {
//                    FinalCount = FinalCount + Integer.parseInt(CR.getString(0).split("#")[PositionInString]);
//                }
//            }while(CR.moveToNext());
//        }
//        return String.valueOf(FinalCount);
//    }
//
//    public String CountTime(String PlayersNumber, Integer PositionInString) {
//        String FinalCountMinutes = "0";
//        String FinalCountSeconds = "0";
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(0).split("#")[2].equals(PlayersNumber)) {
//                    FinalCountMinutes = String.valueOf(Integer.parseInt(FinalCountMinutes) + Integer.parseInt(CR.getString(0).split("#")[PositionInString].split(":")[0]));
//                    FinalCountSeconds = String.valueOf(Integer.parseInt(FinalCountSeconds) + Integer.parseInt(CR.getString(0).split("#")[PositionInString].split(":")[1]));
//                    if(Integer.parseInt(FinalCountSeconds) > 59) {
//                        FinalCountMinutes = String.valueOf(Integer.parseInt(FinalCountMinutes) + 1);
//                        FinalCountSeconds = String.valueOf(Integer.parseInt(FinalCountSeconds) - 60);
//                    }
//                }
//            }while(CR.moveToNext());
//        }
//        return FinalCountMinutes + ":" + FinalCountSeconds;
//    }
//
//
//
//private String[] mParties = new String[] {
//        "Pocet striel", "Goly", "Chytene brankarom", "Strely mimo", "Stratene lopty", "Ziskane sedmicky", "Plusove body", "Minusove body",
//        "Vylucenia"
//};
//
//    public void setData(String PlayersNumber) {
//
//        float mult = 10;
//        int cnt = 9;
//
//        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
////        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
//
//        // IMPORTANT: In a PieChart, no values (Entry) should have the same
//        // xIndex (even if from different DataSets), since no values can be
//        // drawn above each other.
////        for (int i = 0; i < cnt; i++) {
////            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
////        }
//        TextView Values = (TextView) findViewById(R.id.Values);
//        String StringValues = String.valueOf(Values.getText());
//        for(int i = 0;i< 9;i++) {
//            yVals1.add(new Entry((float) (Integer.parseInt(StringValues.split("\n")[i])), i));
//        }
//
////        for (int i = 0; i < cnt; i++) {
////            yVals2.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
////        }
//
//        ArrayList<String> xVals = new ArrayList<String>();
//
//        for (int i = 0; i < cnt; i++)
//            xVals.add(mParties[i % mParties.length]);
//
//        RadarDataSet set1 = new RadarDataSet(yVals1, "Meno hraca");
//        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//        set1.setDrawFilled(true);
//        set1.setLineWidth(2f);
//
////        RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
////        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
////        set2.setDrawFilled(true);
////        set2.setLineWidth(2f);
//
//        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
//        sets.add(set1);
////        sets.add(set2);
//
//        RadarData data = new RadarData(xVals, sets);
//        data.setValueTypeface(tf);
//        data.setValueTextSize(8f);
//        data.setDrawValues(false);
//
//        mChart.setData(data);
//
//        mChart.invalidate();
//    }
//
//    public void CompareFunction() {
//        final ScrollView FirstList, SecondList, ThirdList, FourthList;
//        FirstList = (ScrollView) findViewById(R.id.FirstList);
//        FirstList.setVisibility(View.VISIBLE);
//        SecondList = (ScrollView) findViewById(R.id.SecondList);
//        SecondList.setVisibility(View.VISIBLE);
//        ThirdList = (ScrollView) findViewById(R.id.ThirdList);
//        ThirdList.setVisibility(View.VISIBLE);
//        FourthList = (ScrollView) findViewById(R.id.FourthList);
//        FourthList.setVisibility(View.VISIBLE);
//        final LinearLayout FirstListNumbers, FirstListNames, SecondListNumbers, SecondListNames, ThirdListNumbers, ThirdListNames, FourthListNumbers, FourthListNames;
//        FirstListNumbers = (LinearLayout) findViewById(R.id.FirstListNumbers);
//        FirstListNames = (LinearLayout) findViewById(R.id.FirstListNames);
//
//        SecondListNumbers = (LinearLayout) findViewById(R.id.SecondListNumbers);
//        SecondListNames = (LinearLayout) findViewById(R.id.SecondListNames);
//
//        ThirdListNumbers = (LinearLayout) findViewById(R.id.ThirdListNumbers);
//        ThirdListNames = (LinearLayout) findViewById(R.id.ThirdListNames);
//
//        FourthListNumbers = (LinearLayout) findViewById(R.id.FourthListNumbers);
//        FourthListNames = (LinearLayout) findViewById(R.id.FourthListNames);
//
//        Cursor CR = DB.getInformationPlayers(DB, ConditionPlayer);
//        if(CR.moveToFirst()) {
//            do {
//                final TextView NumberFirst = new TextView(CX);
//                final TextView NameFirst = new TextView(CX);
//                final TextView NumberSecond = new TextView(CX);
//                final TextView NameSecond = new TextView(CX);
//                final TextView NumberThird = new TextView(CX);
//                final TextView NameThird = new TextView(CX);
//                final TextView NumberFourth = new TextView(CX);
//                final TextView NameFourth = new TextView(CX);
//
//                NumberFirst.setText(CR.getString(Constants.PLAYER_NUMBER));
//                NumberFirst.setTextSize(TextSizeForScreens);
//                NumberFirst.setHint("");
//
//                NameFirst.setText(CR.getString(Constants.PLAYER_FIRST_NAME).charAt(0) + "." + CR.getString(Constants.PLAYER_SECOND_NAME));
//                NameFirst.setTextSize(TextSizeForScreens);
//                NameFirst.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (one < 5) {
//                            if(NumberFirst.getHint().toString().equals("")) {
//                                NumberFirst.setBackgroundColor(0xFF2869FF);
//                                NumberFirst.setHint("0");
//                                NameFirst.setBackgroundColor(0xFF2869FF);
//                                one++;
//                            } else {
//                                NumberFirst.setBackgroundColor(0);
//                                NumberFirst.setHint("");
//                                NameFirst.setBackgroundColor(0);
//                                one--;
//                            }
//                        } else {
//                            one = 0;
//                            ShowValuesForCompare(FirstList, FirstListNumbers, FirstListNames, 1);
//                        }
//
//                    }
//                });
//                FirstListNumbers.addView(NumberFirst);
//                FirstListNames.addView(NameFirst);
//
//                NumberSecond.setText(CR.getString(Constants.PLAYER_NUMBER));
//                NumberSecond.setTextSize(TextSizeForScreens);
//                NumberSecond.setHint("");
//
//                NameSecond.setText(CR.getString(Constants.PLAYER_FIRST_NAME).charAt(0) + "." + CR.getString(Constants.PLAYER_SECOND_NAME));
//                NameSecond.setTextSize(TextSizeForScreens);
//                NameSecond.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (two < 5) {
//                            if(NumberSecond.getHint().toString().equals("")) {
//                                NumberSecond.setBackgroundColor(0xFF2869FF);
//                                NumberSecond.setHint("0");
//                                NameSecond.setBackgroundColor(0xFF2869FF);
//                                two++;
//                            } else {
//                                NumberSecond.setBackgroundColor(0);
//                                NumberSecond.setHint("");
//                                NameSecond.setBackgroundColor(0);
//                                two--;
//                            }
//                        } else {
//                            two = 0;
//                            ShowValuesForCompare(SecondList, SecondListNumbers, SecondListNames, 2);
//                        }
//
//                    }
//                });
//                SecondListNumbers.addView(NumberSecond);
//                SecondListNames.addView(NameSecond);
//
//                NumberThird.setText(CR.getString(Constants.PLAYER_NUMBER));
//                NumberThird.setTextSize(TextSizeForScreens);
//                NumberThird.setHint("");
//
//                NameThird.setText(CR.getString(Constants.PLAYER_FIRST_NAME).charAt(0) + "." + CR.getString(Constants.PLAYER_SECOND_NAME));
//                NameThird.setTextSize(TextSizeForScreens);
//                NameThird.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (three < 5) {
//                            if(NumberThird.getHint().toString().equals("")) {
//                                NumberThird.setBackgroundColor(0xFF2869FF);
//                                NumberThird.setHint("0");
//                                NameThird.setBackgroundColor(0xFF2869FF);
//                                three++;
//                            } else {
//                                NumberThird.setBackgroundColor(0);
//                                NumberThird.setHint("");
//                                NameThird.setBackgroundColor(0);
//                                three--;
//                            }
//                        } else {
//                            three = 0;
//                            ShowValuesForCompare(ThirdList, ThirdListNumbers, ThirdListNames, 3);
//                        }
//                    }
//                });
//                ThirdListNumbers.addView(NumberThird);
//                ThirdListNames.addView(NameThird);
//
//                NumberFourth.setText(CR.getString(Constants.PLAYER_NUMBER));
//                NumberFourth.setTextSize(TextSizeForScreens);
//                NumberFourth.setHint("");
//                NameFourth.setText(CR.getString(Constants.PLAYER_FIRST_NAME).charAt(0) + "." + CR.getString(Constants.PLAYER_SECOND_NAME));
//                NameFourth.setTextSize(TextSizeForScreens);
//                NameFourth.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (four < 5) {
//                            if(NumberFourth.getHint().toString().equals("")) {
//                                NumberFourth.setBackgroundColor(0xFF2869FF);
//                                NumberFourth.setHint("0");
//                                NameFourth.setBackgroundColor(0xFF2869FF);
//                                four++;
//                            } else {
//                                NumberFourth.setBackgroundColor(0);
//                                NumberFourth.setHint("");
//                                NameFourth.setBackgroundColor(0);
//                                four--;
//                            }
//                        } else {
//                            four = 0;
//                            ShowValuesForCompare(FourthList, FourthListNumbers, FourthListNames, 4);
//                        }
//                    }
//                });
//                FourthListNumbers.addView(NumberFourth);
//                FourthListNames.addView(NameFourth);
//            }while(CR.moveToNext());
//        }
//    }
//
//private String[] Values = new String[] {
//        "Pocet striel", "Goly", "Strely mimo", "Chytene brankarom", "Stratene lopty", "Ziskane sedmicky", "Plusove body", "Minusove body",
//        "Vylucenia", "Zlte Karty", "Ziskane lopty", "Pokutovy hod gol", "Pokutovy hod vedla", "Brejk gol", "Brejk vedla", "Gol z kridla", "Vedla z kridla",
//        "Gol 6m", "Gol 7m", "Gol 9m", "Vedla 6m", "Vedla 7m", "Vedla 9m"
//};
//
//    public void ShowValuesForCompare(final ScrollView ListOfPlayers, final LinearLayout PlayersNumbers, final LinearLayout PlayersNames, final int ID) {
//        PlayersNumbers.setVisibility(View.GONE);
//        PlayersNames.removeAllViews();
//        for(int i = 0; i < Values.length; i++) {
//            final TextView OneValue = new TextView(CX);
//            OneValue.setTextSize(TextSizeForScreens);
//            OneValue.setText(Values[i]);
//            OneValue.setHint("");
//            OneValue.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (count < 9) {
//                        if (OneValue.getHint().toString().equals("")) {
//                            OneValue.setBackgroundColor(0xFF2869FF);
//                            OneValue.setHint("0");
//                            count++;
//                        } else {
//                            OneValue.setBackgroundColor(0);
//                            OneValue.setHint("");
//                            count--;
//                        }
//                    } else {
//                        count = 0;
//                        ShowChart(ListOfPlayers, PlayersNumbers, PlayersNames, ID);
//                    }
//                }
//            });
//            PlayersNames.addView(OneValue);
//        }
//    }
//
//    public void ShowChart(ScrollView ListOfPlayers, LinearLayout PlayersNumbers, LinearLayout PlayersNames, int ID) {
//        ListOfPlayers.setVisibility(View.GONE);
//
//        if(ID == 1)
//            mChart = (RadarChart) findViewById(R.id.chartComparePlayers1);
//        if(ID == 2)
//            mChart = (RadarChart) findViewById(R.id.chartComparePlayers2);
//        if(ID == 3)
//            mChart = (RadarChart) findViewById(R.id.chartComparePlayers3);
//        if(ID == 4)
//            mChart = (RadarChart) findViewById(R.id.chartComparePlayers4);
//        mChart.setVisibility(View.VISIBLE);
//
////        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//
//        mChart.setDescription("");
//
//        mChart.setWebLineWidth(1.5f);
//        mChart.setWebLineWidthInner(0.75f);
//        mChart.setWebAlpha(100);
//
//        // create a custom MarkerView (extend MarkerView) and specify the layout
//        // to use for it
////        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
////
////        // set the marker to the chart
////        mChart.setMarkerView(mv);
//
//        setDataCompare(PlayersNumbers, PlayersNames);
//
//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setTypeface(tf);
//        xAxis.setTextSize(9f);
//
//        YAxis yAxis = mChart.getYAxis();
//        yAxis.setTypeface(tf);
//        yAxis.setLabelCount(5, false);
//        yAxis.setTextSize(9f);
//        yAxis.setStartAtZero(true);
//
//        Legend l = mChart.getLegend();
//        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        l.setTypeface(tf);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(5f);
//
//    }
//
//    public void setDataCompare(LinearLayout PlayersForCompare, LinearLayout PlayersNames) {
//
//        float mult = 10;
//        int cnt = 9;
//
//        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals4 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals5 = new ArrayList<Entry>();
//
//        // IMPORTANT: In a PieChart, no values (Entry) should have the same
//        // xIndex (even if from different DataSets), since no values can be
//        // drawn above each other.
////        for (int i = 0; i < cnt; i++) {
////            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
////        }
//
//        String Players = "";
//        for(int i = 0;i < PlayersForCompare.getChildCount(); i++) {
//            if(!((TextView)PlayersForCompare.getChildAt(i)).getHint().equals("")) {
//                Players = Players + ((TextView)PlayersForCompare.getChildAt(i)).getText().toString() + "#";
//            }
//        }
//
//        for(int i = 0;i< 9;i++) {
//            yVals1.add(new Entry((float) (i), i));
//        }
//
//        for (int i = 0; i < cnt; i++) {
//            yVals2.add(new Entry((float)  (i), i));
//        }
//
//        for (int i = 0; i < cnt; i++) {
//            yVals3.add(new Entry((float)  (i), i));
//        }
//
//        for (int i = 0; i < cnt; i++) {
//            yVals4.add(new Entry((float)  (i), i));
//        }
//
//        for (int i = 0; i < cnt; i++) {
//            yVals5.add(new Entry((float)  (i), i));
//        }
//
//        ArrayList<String> xVals = new ArrayList<String>();
//
//        for (int i = 0; i < PlayersNames.getChildCount(); i++) {
//            if(!((TextView)PlayersNames.getChildAt(i)).getHint().equals(""))
//                xVals.add(((TextView)PlayersNames.getChildAt(i)).getText().toString());
//        }
//
//        RadarDataSet set1 = new RadarDataSet(yVals1, Players.split("#")[0]);
//        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//        set1.setDrawFilled(true);
//        set1.setLineWidth(2f);
//
//        RadarDataSet set2 = new RadarDataSet(yVals2, Players.split("#")[1]);
//        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);
//        set2.setDrawFilled(true);
//        set2.setLineWidth(2f);
//
//        RadarDataSet set3 = new RadarDataSet(yVals3, Players.split("#")[2]);
//        set3.setColor(ColorTemplate.VORDIPLOM_COLORS[2]);
//        set3.setDrawFilled(true);
//        set3.setLineWidth(2f);
//
//        RadarDataSet set4 = new RadarDataSet(yVals4, Players.split("#")[3]);
//        set4.setColor(ColorTemplate.VORDIPLOM_COLORS[3]);
//        set4.setDrawFilled(true);
//        set4.setLineWidth(2f);
//
//        RadarDataSet set5 = new RadarDataSet(yVals5, Players.split("#")[4]);
//        set5.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
//        set5.setDrawFilled(true);
//        set5.setLineWidth(2f);
//
//        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
//        sets.add(set1);
//        sets.add(set2);
//        sets.add(set3);
//        sets.add(set4);
//        sets.add(set5);
//
//        RadarData data = new RadarData(xVals, sets);
//        data.setValueTypeface(tf);
//        data.setValueTextSize(8f);
//        data.setDrawValues(false);
//
//        mChart.setData(data);
//
//        mChart.invalidate();
//    }
//

}