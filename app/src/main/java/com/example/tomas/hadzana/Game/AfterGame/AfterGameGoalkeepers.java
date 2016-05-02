package com.example.tomas.hadzana.Game.AfterGame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.CountingFunctions;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class AfterGameGoalkeepers extends Fragment {

    Context CX;
    DatabaseOperations DB;
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    Button BackToTable;

    View AfterGameGoalkeepers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AfterGameGoalkeepers = inflater.inflate(R.layout.summary_goalkeepers, container, false);
        TurnamentPK = getArguments().getString("TurnamentPK");

        //        if (config.smallestScreenWidthDp >= 600) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TextSizeForScreens = 30;
//        } else {
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

        ShowTableOfGoalkeepers(AfterGameGoalkeepers);
        BackToTable = (Button) AfterGameGoalkeepers.findViewById(R.id.BackToTable);
        BackToTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayersTable = (ScrollView) AfterGameGoalkeepers.findViewById(R.id.GoalkeepersTable);
                PlayersTable.setVisibility(View.VISIBLE);
                PlayersDetails = (RelativeLayout) AfterGameGoalkeepers.findViewById(R.id.GoalkeeperDetails);
                PlayersDetails.setVisibility(View.GONE);
            }
        });
        return AfterGameGoalkeepers;
    }

    public void ShowTableOfGoalkeepers(View AfterGameGoalkeepers) {
        LinearLayout ListGoalkeeperNumber, ListGoalkeepersNames, ListTimeOnPlaygroundGoalkeepers, ListInterventions, ListPredictionOfCatch, ListCountCatch, ListRecoveredGoals, ListPredictionsFaulShot, ListCatchFaulShot,
                ListMissFaulShot, ListPredictionBrejkCatch, ListBrejkCatch, ListBrejkRecovered, ListPredictionWingCatch, ListWingCatch, ListWingRecovered, ListPrediction6m, List6mCatch, List6Recovered, ListPrediction7m,
                List7mCacth, List7mRecovered, ListPrediction9m, List9mCatch, List9mRecovered, ListPredictionOfLongPass, ListPassLongGood, ListPassLongBad, ListPredictionOfShortPass, ListPassShortGood, ListPassShortBad,
                ListFaulGoalkeeper, ListYellowCardGoalkeeper, ListRedCardGoalkeeper;
        ListGoalkeeperNumber = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListGoalkeeperNumber);
        ListGoalkeeperNumber.removeAllViews();
        ListGoalkeepersNames = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListGoalkeepersNames);
        ListGoalkeepersNames.removeAllViews();
        ListTimeOnPlaygroundGoalkeepers = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListTimeOnPlaygroundGoalkeepers);
        ListTimeOnPlaygroundGoalkeepers.removeAllViews();
        ListInterventions = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListInterventions);
        ListInterventions.removeAllViews();
        ListPredictionOfCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPredictionOfCatch);
        ListPredictionOfCatch.removeAllViews();
        ListPredictionOfCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPredictionOfCatch);
        ListPredictionOfCatch.removeAllViews();
        ListCountCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListCountCatch);
        ListCountCatch.removeAllViews();
        ListRecoveredGoals = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListRecoveredGoals);
        ListRecoveredGoals.removeAllViews();
        ListPredictionsFaulShot = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPredictionsFaulShot);
        ListPredictionsFaulShot.removeAllViews();
        ListCatchFaulShot = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListCatchFaulShot);
        ListCatchFaulShot.removeAllViews();
        ListMissFaulShot = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListMissFaulShot);
        ListMissFaulShot.removeAllViews();
        ListPredictionBrejkCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPredictionBrejkCatch);
        ListPredictionBrejkCatch.removeAllViews();
        ListBrejkCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListBrejkCatch);
        ListBrejkCatch.removeAllViews();
        ListBrejkRecovered = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListBrejkRecovered);
        ListBrejkRecovered.removeAllViews();
        ListPredictionWingCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPredictionWingCatch);
        ListPredictionWingCatch.removeAllViews();
        ListWingCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListWingCatch);
        ListWingCatch.removeAllViews();
        ListWingRecovered = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListWingRecovered);
        ListWingRecovered.removeAllViews();
        ListPrediction6m = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPrediction6m);
        ListPrediction6m.removeAllViews();
        List6mCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.List6mCatch);
        List6mCatch.removeAllViews();
        List6Recovered = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.List6Recovered);
        List6Recovered.removeAllViews();
        ListPrediction7m = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPrediction7m);
        ListPrediction7m.removeAllViews();
        List7mCacth = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.List7mCacth);
        List7mCacth.removeAllViews();
        List7mRecovered = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.List7mRecovered);
        List7mRecovered.removeAllViews();
        ListPrediction9m = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPrediction9m);
        ListPrediction9m.removeAllViews();
        List9mCatch = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.List9mCatch);
        List9mCatch.removeAllViews();
        List9mRecovered = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.List9mRecovered);
        List9mRecovered.removeAllViews();
        ListPredictionOfLongPass = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPredictionOfLongPass);;
        ListPredictionOfLongPass.removeAllViews();
        ListPassLongGood = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPassLongGood);
        ListPassLongGood.removeAllViews();
        ListPassLongBad = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPassLongBad);
        ListPassLongBad.removeAllViews();
        ListPredictionOfShortPass = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPredictionOfShortPass);
        ListPredictionOfShortPass.removeAllViews();
        ListPassShortGood = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPassShortGood);
        ListPassShortGood.removeAllViews();
        ListPassShortBad = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListPassShortBad);
        ListPassShortBad.removeAllViews();
        ListFaulGoalkeeper = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListFaulGoalkeeper);
        ListFaulGoalkeeper.removeAllViews();
        ListYellowCardGoalkeeper = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListYellowCardGoalkeeper);
        ListYellowCardGoalkeeper.removeAllViews();
        ListRedCardGoalkeeper = (LinearLayout) AfterGameGoalkeepers.findViewById(R.id.ListRedCardGoalkeeper);
        ListRedCardGoalkeeper.removeAllViews();
        int i = 0;
        Integer Color = 0;
        Cursor CR = DB.getInformationGoalkeeper(DB, ConditionGoalkeeper);
        if(CR.moveToFirst()) {
            do {
                if(i % 2 != 0)
                    Color = 0xC2C2C2FF;
                else
                    Color = 0;
                final TextView GoalkeeperNumber = new TextView(CX);
                GoalkeeperNumber.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                GoalkeeperNumber.setPadding(0, 0, 16, 0);
                GoalkeeperNumber.setBackgroundColor(Color);
                GoalkeeperNumber.setText(CR.getString(Constants.GOALKEEPER_NUMBER));
                ListGoalkeeperNumber.addView(GoalkeeperNumber);
                final TextView GoalkeeperName = new TextView(CX);
                GoalkeeperName.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                GoalkeeperName.setPadding(0, 0, 16, 0);
                GoalkeeperName.setBackgroundColor(Color);
                GoalkeeperName.setText(CR.getString(Constants.GOALKEEPER_FIRST_NAME) + " " + CR.getString(Constants.GOALKEEPER_SECOND_NAME));
                ListGoalkeepersNames.addView(GoalkeeperName);
                final TextView TimeOnPlayGroundGoalkeeper = new TextView(CX);
                TimeOnPlayGroundGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                TimeOnPlayGroundGoalkeeper.setPadding(0, 0, 16, 0);
                TimeOnPlayGroundGoalkeeper.setBackgroundColor(Color);
                TimeOnPlayGroundGoalkeeper.setText(CR.getString(Constants.TIME_ON_PLAYGROUND_GOALKEEPER));
                ListTimeOnPlaygroundGoalkeepers.addView(TimeOnPlayGroundGoalkeeper);
                final TextView Interventions = new TextView(CX);
                Interventions.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Interventions.setPadding(0, 0, 16, 0);
                Interventions.setBackgroundColor(0);
                Interventions.setText(CR.getString(Constants.COUNT_OF_INTERVENTIONS_FOR_GAME));
                ListInterventions.addView(Interventions);
                final TextView PredictionOfCatch = new TextView(CX);
                PredictionOfCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch.setPadding(0, 0, 16, 0);
                PredictionOfCatch.setBackgroundColor(0);
                PredictionOfCatch.setText(CR.getString(Constants.PREDICTION_OF_CATCH_FOR_GAME) + "%");
                ListPredictionOfCatch.addView(PredictionOfCatch);
                final TextView Catch = new TextView(CX);
                Catch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Catch.setPadding(0, 0, 16, 0);
                Catch.setBackgroundColor(Color);
                Catch.setText(CR.getString(Constants.CATCH));
                ListCountCatch.addView(Catch);
                final TextView RecoveredGoals = new TextView(CX);
                RecoveredGoals.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                RecoveredGoals.setPadding(0, 0, 16, 0);
                RecoveredGoals.setBackgroundColor(Color);
                RecoveredGoals.setText(CR.getString(Constants.RECOVERED_GOALS));
                ListRecoveredGoals.addView(RecoveredGoals);
                final TextView PredictionOfCatchFaulshot = new TextView(CX);
                PredictionOfCatchFaulshot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatchFaulshot.setPadding(0, 0, 16, 0);
                PredictionOfCatchFaulshot.setBackgroundColor(0);
                PredictionOfCatchFaulshot.setText(CR.getString(Constants.PREDICTION_OF_CATCH_FAUL_SHOT_FOR_GAME) + "%");
                ListPredictionsFaulShot.addView(PredictionOfCatchFaulshot);
                final TextView CatchFaulShot = new TextView(CX);
                CatchFaulShot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CatchFaulShot.setPadding(0, 0, 16, 0);
                CatchFaulShot.setBackgroundColor(Color);
                CatchFaulShot.setText(CR.getString(Constants.CATCH_FAUL_SHOT));
                ListCatchFaulShot.addView(CatchFaulShot);
                final TextView MissFaulShot = new TextView(CX);
                MissFaulShot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                MissFaulShot.setPadding(0, 0, 16, 0);
                MissFaulShot.setBackgroundColor(Color);
                MissFaulShot.setText(CR.getString(Constants.MISS_FAUL_SHOT));
                ListMissFaulShot.addView(MissFaulShot);
                final TextView PredictionOfCatchBrejk = new TextView(CX);
                PredictionOfCatchBrejk.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatchBrejk.setPadding(0, 0, 16, 0);
                PredictionOfCatchBrejk.setBackgroundColor(0);
                PredictionOfCatchBrejk.setText(CR.getString(Constants.PREDICTION_OF_CATCH_BREJK_FOR_GAME) + "%");
                ListPredictionBrejkCatch.addView(PredictionOfCatchBrejk);
                final TextView BrejkCatch = new TextView(CX);
                BrejkCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                BrejkCatch.setPadding(0, 0, 16, 0);
                BrejkCatch.setBackgroundColor(Color);
                BrejkCatch.setText(CR.getString(Constants.BREJK_CATCH));
                ListBrejkCatch.addView(BrejkCatch);
                final TextView BrejkRecovered = new TextView(CX);
                BrejkRecovered.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                BrejkRecovered.setPadding(0, 0, 16, 0);
                BrejkRecovered.setBackgroundColor(Color);
                BrejkRecovered.setText(CR.getString(Constants.BREJK_RECOVERED));
                ListBrejkRecovered.addView(BrejkRecovered);
                final TextView PredictionOfCatchWing = new TextView(CX);
                PredictionOfCatchWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatchWing.setPadding(0, 0, 16, 0);
                PredictionOfCatchWing.setBackgroundColor(0);
                PredictionOfCatchWing.setText(CR.getString(Constants.PREDICTION_OF_CATCH_WING_FOR_GAME) + "%");
                ListPredictionWingCatch.addView(PredictionOfCatchWing);
                final TextView WingCatch = new TextView(CX);
                WingCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                WingCatch.setPadding(0, 0, 16, 0);
                WingCatch.setBackgroundColor(Color);
                WingCatch.setText(CR.getString(Constants.WING_CATCH));
                ListWingCatch.addView(WingCatch);
                final TextView WingRecovered = new TextView(CX);
                WingRecovered.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                WingRecovered.setPadding(0, 0, 16, 0);
                WingRecovered.setBackgroundColor(Color);
                WingRecovered.setText(CR.getString(Constants.WING_RECOVERED));
                ListWingRecovered.addView(WingRecovered);
                final TextView PredictionOfCatch6m = new TextView(CX);
                PredictionOfCatch6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch6m.setPadding(0, 0, 16, 0);
                PredictionOfCatch6m.setBackgroundColor(0);
                PredictionOfCatch6m.setText(CR.getString(Constants.PREDICTION_OF_CATCH_6M_FOR_GAME) + "%");
                ListPrediction6m.addView(PredictionOfCatch6m);
                final TextView Catch6m = new TextView(CX);
                Catch6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Catch6m.setPadding(0, 0, 16, 0);
                Catch6m.setBackgroundColor(Color);
                Catch6m.setText(CR.getString(Constants.CATCH_6M));
                List6mCatch.addView(Catch6m);
                final TextView Recovered6m = new TextView(CX);
                Recovered6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Recovered6m.setPadding(0, 0, 16, 0);
                Recovered6m.setBackgroundColor(Color);
                Recovered6m.setText(CR.getString(Constants.RECOVERED_6M));
                List6Recovered.addView(Recovered6m);
                final TextView PredictionOfCatch7m = new TextView(CX);
                PredictionOfCatch7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch7m.setPadding(0, 0, 16, 0);
                PredictionOfCatch7m.setBackgroundColor(0);
                PredictionOfCatch7m.setText(CR.getString(Constants.PREDICTION_OF_CATCH_7M_FOR_GAME) + "%");
                ListPrediction7m.addView(PredictionOfCatch7m);
                final TextView Catch7m = new TextView(CX);
                Catch7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Catch7m.setPadding(0, 0, 16, 0);
                Catch7m.setBackgroundColor(Color);
                Catch7m.setText(CR.getString(Constants.CATCH_7M));
                List7mCacth.addView(Catch7m);
                final TextView Recovered7m = new TextView(CX);
                Recovered7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Recovered7m.setPadding(0, 0, 16, 0);
                Recovered7m.setBackgroundColor(Color);
                Recovered7m.setText(CR.getString(Constants.RECOVERED_7M));
                List7mRecovered.addView(Recovered7m);
                final TextView PredictionOfCatch9m = new TextView(CX);
                PredictionOfCatch9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch9m.setPadding(0, 0, 16, 0);
                PredictionOfCatch9m.setBackgroundColor(0);
                PredictionOfCatch9m.setText(CR.getString(Constants.PREDICTION_OF_CATCH_9M_FOR_GAME) + "%");
                ListPrediction9m.addView(PredictionOfCatch9m);
                final TextView Catch9m = new TextView(CX);
                Catch9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Catch9m.setPadding(0, 0, 16, 0);
                Catch9m.setBackgroundColor(Color);
                Catch9m.setText(CR.getString(Constants.CATCH_9M));
                List9mCatch.addView(Catch9m);
                final TextView Recovered9m = new TextView(CX);
                Recovered9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Recovered9m.setPadding(0, 0, 16, 0);
                Recovered9m.setBackgroundColor(Color);
                Recovered9m.setText(CR.getString(Constants.RECOVERED_9M));
                List9mRecovered.addView(Recovered9m);
                final TextView PredictionOfLongPass = new TextView(CX);
                PredictionOfLongPass.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfLongPass.setPadding(0, 0, 16, 0);
                PredictionOfLongPass.setBackgroundColor(Color);
                PredictionOfLongPass.setText(CR.getString(Constants.PREDICTION_OF_LONG_PASS_FOR_GAME) + "%");
                ListPredictionOfLongPass.addView(PredictionOfLongPass);
                final TextView PassLongGood = new TextView(CX);
                PassLongGood.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PassLongGood.setPadding(0, 0, 16, 0);
                PassLongGood.setBackgroundColor(Color);
                PassLongGood.setText(CR.getString(Constants.PASS_LONG_GOOD));
                ListPassLongGood.addView(PassLongGood);
                final TextView PassLongBad = new TextView(CX);
                PassLongBad.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PassLongBad.setPadding(0, 0, 16, 0);
                PassLongBad.setBackgroundColor(Color);
                PassLongBad.setText(CR.getString(Constants.PASS_LONG_BAD));
                ListPassLongBad.addView(PassLongBad);
                final TextView PredictionOfShortPass = new TextView(CX);
                PredictionOfShortPass.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfShortPass.setPadding(0, 0, 16, 0);
                PredictionOfShortPass.setBackgroundColor(Color);
                PredictionOfShortPass.setText(CR.getString(Constants.PREDICTION_OF_SHORT_PASS_FOR_GAME) + "%");
                ListPredictionOfShortPass.addView(PredictionOfShortPass);
                final TextView PassShortGood = new TextView(CX);
                PassShortGood.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PassShortGood.setPadding(0, 0, 16, 0);
                PassShortGood.setBackgroundColor(Color);
                PassShortGood.setText(CR.getString(Constants.PASS_SHORT_GOOD));
                ListPassShortGood.addView(PassShortGood);
                final TextView PassShortBad = new TextView(CX);
                PassShortBad.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PassShortBad.setPadding(0, 0, 16, 0);
                PassShortBad.setBackgroundColor(Color);
                PassShortBad.setText(CR.getString(Constants.PASS_SHORT_BAD));
                ListPassShortBad.addView(PassShortBad);
                final TextView FaulGoalkeeper = new TextView(CX);
                FaulGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                FaulGoalkeeper.setPadding(0, 0, 16, 0);
                FaulGoalkeeper.setBackgroundColor(Color);
                FaulGoalkeeper.setText(CR.getString(Constants.FAUL_GOALKEEPER));
                ListFaulGoalkeeper.addView(FaulGoalkeeper);
                final TextView YellowCardGoalkeeper = new TextView(CX);
                YellowCardGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                YellowCardGoalkeeper.setPadding(0, 0, 16, 0);
                YellowCardGoalkeeper.setBackgroundColor(Color);
                YellowCardGoalkeeper.setText(CR.getString(Constants.YELLOW_CARD_GOALKEEPER));
                ListYellowCardGoalkeeper.addView(YellowCardGoalkeeper);
                final TextView RedCardGoalkeeper = new TextView(CX);
                RedCardGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                RedCardGoalkeeper.setPadding(0, 0, 16, 0);
                RedCardGoalkeeper.setBackgroundColor(Color);
                RedCardGoalkeeper.setText(CR.getString(Constants.RED_CARD_GOALKEEPER));
                ListRedCardGoalkeeper.addView(RedCardGoalkeeper);

                i++;
            }while(CR.moveToNext());
        }
    }

    public void ShowGoalkeeperDetail(View AfterGameGoalkeepers, String PlayerNumber) {
//        GoalkeepersTable = (ScrollView) AfterGameGoalkeepers.findViewById(R.id.GoalkeepersTable);
//        GoalkeepersTable.setVisibility(View.GONE);
//        GoalkeeperDetails = (RelativeLayout) AfterGameGoalkeepers.findViewById(R.id.GoalkeeperDetails);
//        GoalkeeperDetails.setVisibility(View.VISIBLE);
//        Integer CountOfEncashment = 0, CountOfCatch = 0, CountOfGoodLongPass = 0, CountOfBadLongPass = 0, CountOfGoodShortPass = 0, CountOfBadSortPass = 0,
//                CountOfCatchFaulShots = 0, CountOfMissFaulShots = 0, CountOfTimeOnPlayground = 0, CountOfPlayedGames = 0;
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(0).split("#")[2].equals(PlayerNumber)){
//                    CountOfPlayedGames++;
//                    CountOfCatch = CountOfCatch + Integer.valueOf(CR.getString(0).split("#")[3]);
//                    CountOfGoodLongPass = CountOfGoodLongPass + Integer.valueOf(CR.getString(0).split("#")[4]);
//                    CountOfBadLongPass = CountOfBadLongPass + Integer.valueOf(CR.getString(0).split("#")[5]);
//                    CountOfGoodShortPass = CountOfGoodShortPass + Integer.valueOf(CR.getString(0).split("#")[6]);
//                    CountOfBadSortPass = CountOfBadSortPass + Integer.valueOf(CR.getString(0).split("#")[7]);
//                    CountOfCatchFaulShots = CountOfCatchFaulShots + Integer.valueOf(CR.getString(0).split("#")[8]);
//                    CountOfMissFaulShots = CountOfMissFaulShots + Integer.valueOf(CR.getString(0).split("#")[9]);
//                    CountOfEncashment = CountOfEncashment + Integer.valueOf(CR.getString(0).split("#")[10]);
//                    CountOfTimeOnPlayground = CountOfTimeOnPlayground + Integer.valueOf(CR.getString(0).split("#")[12].split(":")[0])*60 + Integer.valueOf(CR.getString(0).split("#")[12].split(":")[1]);;
//                }
//            }while(CR.moveToNext());
//        }
//        TextView Values = (TextView) AfterGameGoalkeepers.findViewById(R.id.ValuesGoalkeeper);
//        String CONCATENATE_OF_VALUES;
//        String Minutes, Seconds;
//        Minutes = String.valueOf(CountOfTimeOnPlayground / 60);
//        Seconds = String.valueOf(CountOfTimeOnPlayground % 60);
//        if(Integer.parseInt(Minutes) < 10) {
//            Minutes = "0" + Minutes;
//        }
//        if(Integer.parseInt(Seconds) < 10) {
//            Seconds = "0" + Seconds;
//        }
//        CONCATENATE_OF_VALUES = Minutes + ":" + Seconds + "\n" + String.valueOf(CountOfPlayedGames) + "\n" + String.valueOf(CountOfEncashment) + "\n" + String.valueOf(CountOfCatch) + "\n";
//        if((CountOfCatch + CountOfEncashment) != 0)
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf((CountOfCatch * 100)/(CountOfCatch + CountOfEncashment)) + "%" + "\n" +
//                    String.valueOf(CountOfGoodLongPass) + "\n" + String.valueOf(CountOfBadLongPass) + "\n";
//        else
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%"  + "\n" + String.valueOf(CountOfGoodLongPass) + "\n" + String.valueOf(CountOfBadLongPass) + "\n";
//        if(CountOfGoodLongPass + CountOfBadLongPass != 0)
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf((CountOfGoodLongPass * 100) / (CountOfGoodLongPass + CountOfBadLongPass)) + "%" + "\n" + String.valueOf(CountOfGoodShortPass) + "\n" + String.valueOf(CountOfBadSortPass) + "\n";
//        else
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%" + "\n" + String.valueOf(CountOfGoodShortPass) + "\n" + String.valueOf(CountOfBadSortPass) + "\n";
//        if((CountOfGoodShortPass + CountOfBadSortPass) != 0)
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf((CountOfGoodShortPass * 100) / (CountOfGoodShortPass + CountOfBadSortPass)) + "%" + "\n" + String.valueOf(CountOfCatchFaulShots) + "\n" + String.valueOf(CountOfMissFaulShots) + "\n";
//        else
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%" + "\n" + String.valueOf(CountOfCatchFaulShots) + "\n" + String.valueOf(CountOfMissFaulShots) + "\n";
//        if((CountOfCatchFaulShots + CountOfMissFaulShots) != 0)
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + String.valueOf((CountOfCatchFaulShots * 100)/(CountOfCatchFaulShots + CountOfMissFaulShots)) + "%";
//        else
//            CONCATENATE_OF_VALUES = CONCATENATE_OF_VALUES + "0%";
//        Values.setText(CONCATENATE_OF_VALUES);
    }

    public String CountValue(String PlayersNumber, Integer PositionInString) {
        Integer FinalCount = 0;
        Cursor CR = DB.getInformationGoalkeeper(DB, ConditionGoalkeeper);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(Constants.GOALKEEPER_NUMBER).equals(PlayersNumber)) {
                    FinalCount = FinalCount + Integer.parseInt(CR.getString(PositionInString));
                }
            }while(CR.moveToNext());
        }
        return String.valueOf(FinalCount);
    }

    public String CountTime(String PlayersNumber, Integer PositionInString) {
        String FinalCountMinutes = "0";
        String FinalCountSeconds = "0";
        Cursor CR = DB.getInformationGoalkeeper(DB, ConditionGoalkeeper);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(Constants.GOALKEEPER_NUMBER).equals(PlayersNumber)) {
                    FinalCountMinutes = String.valueOf(Integer.parseInt(FinalCountMinutes) + Integer.parseInt(CR.getString(PositionInString).split(":")[0]));
                    FinalCountSeconds = String.valueOf(Integer.parseInt(FinalCountSeconds) + Integer.parseInt(CR.getString(PositionInString).split(":")[1]));
                    if(Integer.parseInt(FinalCountSeconds) > 59) {
                        FinalCountMinutes = String.valueOf(Integer.parseInt(FinalCountMinutes) + 1);
                        FinalCountSeconds = String.valueOf(Integer.parseInt(FinalCountSeconds) - 60);
                    }
                }
            }while(CR.moveToNext());
        }
        if(Integer.valueOf(FinalCountMinutes) < 10)
            FinalCountMinutes = "0" + FinalCountMinutes;
        if(Integer.valueOf(FinalCountSeconds) < 10)
            FinalCountSeconds = "0" + FinalCountSeconds;
        return FinalCountMinutes + ":" + FinalCountSeconds;
    }
}
