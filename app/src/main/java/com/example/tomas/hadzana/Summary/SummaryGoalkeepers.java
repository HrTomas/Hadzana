package com.example.tomas.hadzana.Summary;

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
import android.widget.Toast;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class SummaryGoalkeepers extends Fragment {

    Context CX;
    DatabaseOperations DB;
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    Button BackToTable;

    View SummaryGoalkeepers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SummaryGoalkeepers = inflater.inflate(R.layout.summary_goalkeepers, container, false);
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
            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK;
            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
            ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        }

        ShowTableOfGoalkeepers(SummaryGoalkeepers);
        BackToTable = (Button) SummaryGoalkeepers.findViewById(R.id.BackToTable);
        BackToTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayersTable = (ScrollView) SummaryGoalkeepers.findViewById(R.id.PlayersTable);
                PlayersTable.setVisibility(View.VISIBLE);
                PlayersDetails = (RelativeLayout) SummaryGoalkeepers.findViewById(R.id.PlayersDetails);
                PlayersDetails.setVisibility(View.GONE);
            }
        });
        return SummaryGoalkeepers;
    }

    public void ShowTableOfGoalkeepers(View SummaryGoalkeepers) {
        LinearLayout ListGoalkeeperNumber, ListGoalkeepersNames, ListTimeOnPlaygroundGoalkeepers, ListInterventions, ListPredictionOfCatch, ListCountCatch, ListRecoveredGoals, ListPredictionsFaulShot, ListCatchFaulShot,
                ListMissFaulShot, ListPredictionBrejkCatch, ListBrejkCatch, ListBrejkRecovered, ListPredictionWingCatch, ListWingCatch, ListWingRecovered, ListPrediction6m, List6mCatch, List6Recovered, ListPrediction7m,
                List7mCacth, List7mRecovered, ListPrediction9m, List9mCatch, List9mRecovered, ListPredictionOfLongPass, ListPassLongGood, ListPassLongBad, ListPredictionOfShortPass, ListPassShortGood, ListPassShortBad,
                ListFaulGoalkeeper, ListYellowCardGoalkeeper, ListRedCardGoalkeeper;
        ListGoalkeeperNumber = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListGoalkeeperNumber);
        ListGoalkeeperNumber.removeAllViews();
        ListGoalkeepersNames = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListGoalkeepersNames);
        ListGoalkeepersNames.removeAllViews();
        ListTimeOnPlaygroundGoalkeepers = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListTimeOnPlaygroundGoalkeepers);
        ListTimeOnPlaygroundGoalkeepers.removeAllViews();
        ListInterventions = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListInterventions);
        ListInterventions.removeAllViews();
        ListPredictionOfCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPredictionOfCatch);
        ListPredictionOfCatch.removeAllViews();
        ListPredictionOfCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPredictionOfCatch);
        ListPredictionOfCatch.removeAllViews();
        ListCountCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListCountCatch);
        ListCountCatch.removeAllViews();
        ListRecoveredGoals = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListRecoveredGoals);
        ListRecoveredGoals.removeAllViews();
        ListPredictionsFaulShot = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPredictionsFaulShot);
        ListPredictionsFaulShot.removeAllViews();
        ListCatchFaulShot = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListCatchFaulShot);
        ListCatchFaulShot.removeAllViews();
        ListMissFaulShot = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListMissFaulShot);
        ListMissFaulShot.removeAllViews();
        ListPredictionBrejkCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPredictionBrejkCatch);
        ListPredictionBrejkCatch.removeAllViews();
        ListBrejkCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListBrejkCatch);
        ListBrejkCatch.removeAllViews();
        ListBrejkRecovered = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListBrejkRecovered);
        ListBrejkRecovered.removeAllViews();
        ListPredictionWingCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPredictionWingCatch);
        ListPredictionWingCatch.removeAllViews();
        ListWingCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListWingCatch);
        ListWingCatch.removeAllViews();
        ListWingRecovered = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListWingRecovered);
        ListWingRecovered.removeAllViews();
        ListPrediction6m = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPrediction6m);
        ListPrediction6m.removeAllViews();
        List6mCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.List6mCatch);
        List6mCatch.removeAllViews();
        List6Recovered = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.List6Recovered);
        List6Recovered.removeAllViews();
        ListPrediction7m = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPrediction7m);
        ListPrediction7m.removeAllViews();
        List7mCacth = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.List7mCacth);
        List7mCacth.removeAllViews();
        List7mRecovered = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.List7mRecovered);
        List7mRecovered.removeAllViews();
        ListPrediction9m = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPrediction9m);
        ListPrediction9m.removeAllViews();
        List9mCatch = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.List9mCatch);
        List9mCatch.removeAllViews();
        List9mRecovered = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.List9mRecovered);
        List9mRecovered.removeAllViews();
        ListPredictionOfLongPass = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPredictionOfLongPass);;
        ListPredictionOfLongPass.removeAllViews();
        ListPassLongGood = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPassLongGood);
        ListPassLongGood.removeAllViews();
        ListPassLongBad = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPassLongBad);
        ListPassLongBad.removeAllViews();
        ListPredictionOfShortPass = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPredictionOfShortPass);
        ListPredictionOfShortPass.removeAllViews();
        ListPassShortGood = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPassShortGood);
        ListPassShortGood.removeAllViews();
        ListPassShortBad = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListPassShortBad);
        ListPassShortBad.removeAllViews();
        ListFaulGoalkeeper = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListFaulGoalkeeper);
        ListFaulGoalkeeper.removeAllViews();
        ListYellowCardGoalkeeper = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListYellowCardGoalkeeper);
        ListYellowCardGoalkeeper.removeAllViews();
        ListRedCardGoalkeeper = (LinearLayout) SummaryGoalkeepers.findViewById(R.id.ListRedCardGoalkeeper);
        ListRedCardGoalkeeper.removeAllViews();

        int i = 0;
        Integer Color = 0;
        Cursor CR = DB.GetInformationGoalkeeper(DB, ConditionGoalkeeper);
        if(CR.moveToFirst()) {
            do {
                if(i % 2 != 0)
                    Color = 0xC2C2C2FF;
                else
                    Color = 0;
                final TextView GoalkeeperNumberRoster = new TextView(CX);
                GoalkeeperNumberRoster.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                GoalkeeperNumberRoster.setPadding(0, 0, 16, 0);
                GoalkeeperNumberRoster.setBackgroundColor(Color);
                GoalkeeperNumberRoster.setText(CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER));
                ListGoalkeeperNumber.addView(GoalkeeperNumberRoster);
                final TextView GoalkeeperName = new TextView(CX);
                GoalkeeperName.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                GoalkeeperName.setPadding(0, 0, 16, 0);
                GoalkeeperName.setBackgroundColor(Color);
                GoalkeeperName.setText(CR.getString(Constants.GOALKEEPER_FIRST_NAME_ROSTER) + " " + CR.getString(Constants.GOALKEEPER_SECOND_NAME_ROSTER));
                ListGoalkeepersNames.addView(GoalkeeperName);
                final TextView CountOfTimeOnPlayGroundGoalkeeper = new TextView(CX);
                CountOfTimeOnPlayGroundGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountOfTimeOnPlayGroundGoalkeeper.setPadding(0, 0, 16, 0);
                CountOfTimeOnPlayGroundGoalkeeper.setBackgroundColor(Color);
                CountOfTimeOnPlayGroundGoalkeeper.setText(CR.getString(Constants.COUNT_OF_TIME_ON_PLAYGROUND_GOALKEEPER));
                ListTimeOnPlaygroundGoalkeepers.addView(CountOfTimeOnPlayGroundGoalkeeper);
                final TextView CountOfInterventions = new TextView(CX);
                CountOfInterventions.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountOfInterventions.setPadding(0, 0, 16, 0);
                CountOfInterventions.setBackgroundColor(Color);
                CountOfInterventions.setText(CR.getString(Constants.COUNT_OF_INTERVENTIONS));
                ListInterventions.addView(CountOfInterventions);
                final TextView PredictionOfCatch = new TextView(CX);
                PredictionOfCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch.setPadding(0, 0, 16, 0);
                PredictionOfCatch.setBackgroundColor(Color);
                PredictionOfCatch.setText(CR.getString(Constants.PREDICTION_OF_CATCH) + "%");
                ListPredictionOfCatch.addView(PredictionOfCatch);
                final TextView CountCatch = new TextView(CX);
                CountCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountCatch.setPadding(0, 0, 16, 0);
                CountCatch.setBackgroundColor(Color);
                CountCatch.setText(CR.getString(Constants.COUNT_CATCH));
                ListCountCatch.addView(CountCatch);
                final TextView CountRecoveredGoals = new TextView(CX);
                CountRecoveredGoals.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountRecoveredGoals.setPadding(0, 0, 16, 0);
                CountRecoveredGoals.setBackgroundColor(Color);
                CountRecoveredGoals.setText(CR.getString(Constants.COUNT_RECOVERED_GOALS));
                ListRecoveredGoals.addView(CountRecoveredGoals);
                final TextView PredictionOfCatchFaulshot = new TextView(CX);
                PredictionOfCatchFaulshot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatchFaulshot.setPadding(0, 0, 16, 0);
                PredictionOfCatchFaulshot.setBackgroundColor(Color);
                PredictionOfCatchFaulshot.setText(CR.getString(Constants.PREDICTION_OF_CATCH_FAUL_SHOT) + "%");
                ListPredictionsFaulShot.addView(PredictionOfCatchFaulshot);
                final TextView CountCatchFaulShot = new TextView(CX);
                CountCatchFaulShot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountCatchFaulShot.setPadding(0, 0, 16, 0);
                CountCatchFaulShot.setBackgroundColor(Color);
                CountCatchFaulShot.setText(CR.getString(Constants.COUNT_CATCH_FAUL_SHOT));
                ListCatchFaulShot.addView(CountCatchFaulShot);
                final TextView CountMissFaulShot = new TextView(CX);
                CountMissFaulShot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountMissFaulShot.setPadding(0, 0, 16, 0);
                CountMissFaulShot.setBackgroundColor(Color);
                CountMissFaulShot.setText(CR.getString(Constants.COUNT_MISS_FAUL_SHOT));
                ListMissFaulShot.addView(CountMissFaulShot);
                final TextView PredictionOfCatchBrejk = new TextView(CX);
                PredictionOfCatchBrejk.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatchBrejk.setPadding(0, 0, 16, 0);
                PredictionOfCatchBrejk.setBackgroundColor(Color);
                PredictionOfCatchBrejk.setText(CR.getString(Constants.PREDICTION_OF_CATCH_BREJK) + "%");
                ListPredictionBrejkCatch.addView(PredictionOfCatchBrejk);
                final TextView CountBrejkCatch = new TextView(CX);
                CountBrejkCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountBrejkCatch.setPadding(0, 0, 16, 0);
                CountBrejkCatch.setBackgroundColor(Color);
                CountBrejkCatch.setText(CR.getString(Constants.COUNT_BREJK_CATCH));
                ListBrejkCatch.addView(CountBrejkCatch);
                final TextView CountBrejkRecovered = new TextView(CX);
                CountBrejkRecovered.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountBrejkRecovered.setPadding(0, 0, 16, 0);
                CountBrejkRecovered.setBackgroundColor(Color);
                CountBrejkRecovered.setText(CR.getString(Constants.COUNT_BREJK_RECOVERED));
                ListBrejkRecovered.addView(CountBrejkRecovered);
                final TextView PredictionOfCatchWing = new TextView(CX);
                PredictionOfCatchWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatchWing.setPadding(0, 0, 16, 0);
                PredictionOfCatchWing.setBackgroundColor(Color);
                PredictionOfCatchWing.setText(CR.getString(Constants.PREDICTION_OF_CATCH_WING) + "%");
                ListPredictionWingCatch.addView(PredictionOfCatchWing);
                final TextView CountWingCatch = new TextView(CX);
                CountWingCatch.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountWingCatch.setPadding(0, 0, 16, 0);
                CountWingCatch.setBackgroundColor(Color);
                CountWingCatch.setText(CR.getString(Constants.COUNT_WING_CATCH));
                ListWingCatch.addView(CountWingCatch);
                final TextView CountWingRecovered = new TextView(CX);
                CountWingRecovered.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountWingRecovered.setPadding(0, 0, 16, 0);
                CountWingRecovered.setBackgroundColor(Color);
                CountWingRecovered.setText(CR.getString(Constants.COUNT_WING_RECOVERED));
                ListWingRecovered.addView(CountWingRecovered);
                final TextView PredictionOfCatch6m = new TextView(CX);
                PredictionOfCatch6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch6m.setPadding(0, 0, 16, 0);
                PredictionOfCatch6m.setBackgroundColor(Color);
                PredictionOfCatch6m.setText(CR.getString(Constants.PREDICTION_OF_CATCH_6M) + "%");
                ListPrediction6m.addView(PredictionOfCatch6m);
                final TextView CountCatch6m = new TextView(CX);
                CountCatch6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountCatch6m.setPadding(0, 0, 16, 0);
                CountCatch6m.setBackgroundColor(Color);
                CountCatch6m.setText(CR.getString(Constants.COUNT_CATCH_6M));
                List6mCatch.addView(CountCatch6m);
                final TextView CountRecovered6m = new TextView(CX);
                CountRecovered6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountRecovered6m.setPadding(0, 0, 16, 0);
                CountRecovered6m.setBackgroundColor(Color);
                CountRecovered6m.setText(CR.getString(Constants.COUNT_RECOVERED_6M));
                List6Recovered.addView(CountRecovered6m);
                final TextView PredictionOfCatch7m = new TextView(CX);
                PredictionOfCatch7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch7m.setPadding(0, 0, 16, 0);
                PredictionOfCatch7m.setBackgroundColor(Color);
                PredictionOfCatch7m.setText(CR.getString(Constants.PREDICTION_OF_CATCH_7M) + "%");
                ListPrediction7m.addView(PredictionOfCatch7m);
                final TextView CountCatch7m = new TextView(CX);
                CountCatch7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountCatch7m.setPadding(0, 0, 16, 0);
                CountCatch7m.setBackgroundColor(Color);
                CountCatch7m.setText(CR.getString(Constants.COUNT_CATCH_7M));
                List7mCacth.addView(CountCatch7m);
                final TextView CountRecovered7m = new TextView(CX);
                CountRecovered7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountRecovered7m.setPadding(0, 0, 16, 0);
                CountRecovered7m.setBackgroundColor(Color);
                CountRecovered7m.setText(CR.getString(Constants.COUNT_RECOVERED_7M));
                List7mRecovered.addView(CountRecovered7m);
                final TextView PredictionOfCatch9m = new TextView(CX);
                PredictionOfCatch9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfCatch9m.setPadding(0, 0, 16, 0);
                PredictionOfCatch9m.setBackgroundColor(Color);
                PredictionOfCatch9m.setText(CR.getString(Constants.PREDICTION_OF_CATCH_9M) + "%");
                ListPrediction9m.addView(PredictionOfCatch9m);
                final TextView CountCatch9m = new TextView(CX);
                CountCatch9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountCatch9m.setPadding(0, 0, 16, 0);
                CountCatch9m.setBackgroundColor(Color);
                CountCatch9m.setText(CR.getString(Constants.COUNT_CATCH_9M));
                List9mCatch.addView(CountCatch9m);
                final TextView CountRecovered9m = new TextView(CX);
                CountRecovered9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountRecovered9m.setPadding(0, 0, 16, 0);
                CountRecovered9m.setBackgroundColor(Color);
                CountRecovered9m.setText(CR.getString(Constants.COUNT_RECOVERED_9M));
                List9mRecovered.addView(CountRecovered9m);
                final TextView PredictionOfLongPass = new TextView(CX);
                PredictionOfLongPass.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfLongPass.setPadding(0, 0, 16, 0);
                PredictionOfLongPass.setBackgroundColor(Color);
                PredictionOfLongPass.setText(CR.getString(Constants.PREDICTION_OF_LONG_PASS) + "%");
                ListPredictionOfLongPass.addView(PredictionOfLongPass);
                final TextView CountPassLongGood = new TextView(CX);
                CountPassLongGood.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountPassLongGood.setPadding(0, 0, 16, 0);
                CountPassLongGood.setBackgroundColor(Color);
                CountPassLongGood.setText(CR.getString(Constants.COUNT_PASS_LONG_GOOD));
                ListPassLongGood.addView(CountPassLongGood);
                final TextView CountPassLongBad = new TextView(CX);
                CountPassLongBad.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountPassLongBad.setPadding(0, 0, 16, 0);
                CountPassLongBad.setBackgroundColor(Color);
                CountPassLongBad.setText(CR.getString(Constants.COUNT_PASS_LONG_BAD));
                ListPassLongBad.addView(CountPassLongBad);
                final TextView PredictionOfShortPass = new TextView(CX);
                PredictionOfShortPass.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PredictionOfShortPass.setPadding(0, 0, 16, 0);
                PredictionOfShortPass.setBackgroundColor(Color);
                PredictionOfShortPass.setText(CR.getString(Constants.PREDICTION_OF_SHORT_PASS) + "%");
                ListPredictionOfShortPass.addView(PredictionOfShortPass);
                final TextView CountPassShortGood = new TextView(CX);
                CountPassShortGood.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountPassShortGood.setPadding(0, 0, 16, 0);
                CountPassShortGood.setBackgroundColor(Color);
                CountPassShortGood.setText(CR.getString(Constants.COUNT_PASS_SHORT_GOOD));
                ListPassShortGood.addView(CountPassShortGood);
                final TextView CountPassShortBad = new TextView(CX);
                CountPassShortBad.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountPassShortBad.setPadding(0, 0, 16, 0);
                CountPassShortBad.setBackgroundColor(Color);
                CountPassShortBad.setText(CR.getString(Constants.COUNT_PASS_SHORT_BAD));
                ListPassShortBad.addView(CountPassShortBad);
                final TextView CountFaulGoalkeeper = new TextView(CX);
                CountFaulGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountFaulGoalkeeper.setPadding(0, 0, 16, 0);
                CountFaulGoalkeeper.setBackgroundColor(Color);
                CountFaulGoalkeeper.setText(CR.getString(Constants.COUNT_FAUL_GOALKEEPER));
                ListFaulGoalkeeper.addView(CountFaulGoalkeeper);
                final TextView CountYellowCardGoalkeeper = new TextView(CX);
                CountYellowCardGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountYellowCardGoalkeeper.setPadding(0, 0, 16, 0);
                CountYellowCardGoalkeeper.setBackgroundColor(Color);
                CountYellowCardGoalkeeper.setText(CR.getString(Constants.COUNT_YELLOW_CARD_GOALKEEPER));
                ListYellowCardGoalkeeper.addView(CountYellowCardGoalkeeper);
                final TextView CountRedCardGoalkeeper = new TextView(CX);
                CountRedCardGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CountRedCardGoalkeeper.setPadding(0, 0, 16, 0);
                CountRedCardGoalkeeper.setBackgroundColor(Color);
                CountRedCardGoalkeeper.setText(CR.getString(Constants.COUNT_RED_CARD_GOALKEEPER));
                ListRedCardGoalkeeper.addView(CountRedCardGoalkeeper);

                i++;
            }while(CR.moveToNext());
        }
    }

    public void ShowGoalkeeperDetail(View SummaryGoalkeepers, String PlayerNumber) {
//        GoalkeepersTable = (ScrollView) SummaryGoalkeepers.findViewById(R.id.GoalkeepersTable);
//        GoalkeepersTable.setVisibility(View.GONE);
//        GoalkeeperDetails = (RelativeLayout) SummaryGoalkeepers.findViewById(R.id.GoalkeeperDetails);
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
//        TextView Values = (TextView) SummaryGoalkeepers.findViewById(R.id.ValuesGoalkeeper);
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
//        Cursor CR = DB.getInformationTableOfPlayers(DB, ConditionTableOfPlayers);
//        if(CR.moveToFirst()) {
//            do {
//                if(CR.getString(0).split("#")[2].equals(PlayersNumber)) {
//                    FinalCount = FinalCount + Integer.parseInt(CR.getString(0).split("#")[PositionInString]);
//                }
//            }while(CR.moveToNext());
//        }
        return String.valueOf(FinalCount);
    }

    public String CountTime(String PlayersNumber, Integer PositionInString) {
        String FinalCountMinutes = "0";
        String FinalCountSeconds = "0";
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
        return FinalCountMinutes + ":" + FinalCountSeconds;
    }
}