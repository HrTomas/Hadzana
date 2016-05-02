package com.example.tomas.hadzana.History;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HistoryPlayers extends Fragment {

    Context CX;
    DatabaseOperations DB;
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionHistoryPlayer, ConditionHistoryGoalkeeper, ConditionGameOptions;

    int one = 0, two = 0, three = 0, four = 0, count = 0;

    Integer TextSizeForScreens;

    Button BackToTable;

    View AfterGamePlayers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AfterGamePlayers = inflater.inflate(R.layout.summary_players, container, false);
        TurnamentPK = getArguments().getString("TurnamentPK");

        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 600) {
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TextSizeForScreens = 30;
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_summary_mobile);
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
            ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK ;
            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
            ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        }
        ConditionHistoryPlayer = " WHERE " + TableData.TableInfo.HistoryDatetimaPlayer + " == " + "\"" + Constants.DATE_TIME_FOR_HISTORY + "\"";
        ConditionHistoryGoalkeeper = " WHERE " + TableData.TableInfo.HistoryDatetimeGoalkeeper + " == " + "\"" + Constants.DATE_TIME_FOR_HISTORY + "\"";

        ShowTableOfPlayers(AfterGamePlayers);
        BackToTable = (Button) AfterGamePlayers.findViewById(R.id.BackToTable);
        BackToTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayersTable = (ScrollView) AfterGamePlayers.findViewById(R.id.PlayersTable);
                PlayersTable.setVisibility(View.VISIBLE);
                PlayersDetails = (RelativeLayout) AfterGamePlayers.findViewById(R.id.PlayersDetails);
                PlayersDetails.setVisibility(View.GONE);
            }
        });
        return AfterGamePlayers;
    }

    public void ShowTableOfPlayers(View AfterGamePlayers) {
        LinearLayout ListOfNumbers, ListOfNames, ListOfTimeOnPlayground, ListPlayedGames, ListExploitation, ListShotsPrediction, ListGoalOnTarget, ListShotMiss, ListShotGoalkeeper, ListFaulShotPrediction, ListFaulShotGoal,
                ListFaulGoalMiss, ListReachFaulShot, ListEfficientDefensive, ListPlus, ListMinus, ListFaul, ListYellowCard, ListRedCard, ListReachBall, ListMissBall, ListPredictionBrejk, ListBrejkGoal, ListBrejkMiss,
                ListBrejkGoalkeeper, ListPredicitionWing, ListWingGoal, ListWingMiss, ListWingGoalkeeper, ListPrediction6m, List6mGoal, List6mMiss, List6mGoalkeeper, ListPrediction7m, List7mGoal, List7mMiss, List7mGoalkeeper,
                ListPrediction9m, List9mGoal, List9mMiss, List9mGoalkeeper;

        ListOfNumbers = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListOfNumbers);
        ListOfNumbers.removeAllViews();
        ListOfNames = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListOfNames);
        ListOfNames.removeAllViews();
        ListOfTimeOnPlayground = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListOfTimeOnPlayground);
        ListOfTimeOnPlayground.removeAllViews();
        ListPlayedGames = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListPlayedGames);
        ListPlayedGames.setVisibility(View.GONE);
        ListExploitation = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListExploitation);
        ListExploitation.setVisibility(View.GONE);
        ListShotsPrediction = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListShotsPrediction);
        ListShotsPrediction.removeAllViews();
        ListGoalOnTarget = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListGoalOnTarget);
        ListGoalOnTarget.removeAllViews();
        ListShotMiss = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListShotMiss);
        ListShotMiss.removeAllViews();
        ListShotGoalkeeper = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListShotGoalkeeper);
        ListShotGoalkeeper.removeAllViews();
        ListFaulShotPrediction = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListFaulShotPrediction);
        ListFaulShotPrediction.removeAllViews();
        ListFaulShotGoal = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListFaulShotGoal);
        ListFaulShotGoal.removeAllViews();
        ListFaulGoalMiss = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListFaulGoalMiss);
        ListFaulGoalMiss.removeAllViews();
        ListReachFaulShot = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListReachFaulShot);
        ListReachFaulShot.removeAllViews();
        ListEfficientDefensive = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListEfficientDefensive);
        ListEfficientDefensive.removeAllViews();
        ListPlus = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListPlus);
        ListPlus.removeAllViews();
        ListMinus = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListMinus);
        ListMinus.removeAllViews();
        ListFaul = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListFaul);
        ListFaul.removeAllViews();
        ListYellowCard = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListYellowCard);
        ListYellowCard.removeAllViews();
        ListRedCard = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListRedCard);
        ListRedCard.removeAllViews();
        ListReachBall = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListReachBall);
        ListReachBall.removeAllViews();
        ListMissBall = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListMissBall);
        ListMissBall.removeAllViews();
        ListPredictionBrejk = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListPredictionBrejk);
        ListPredictionBrejk.removeAllViews();
        ListBrejkGoal = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListBrejkGoal);
        ListBrejkGoal.removeAllViews();
        ListBrejkMiss = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListBrejkMiss);
        ListBrejkMiss.removeAllViews();
        ListBrejkGoalkeeper = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListBrejkGoalkeeper);
        ListBrejkGoalkeeper.removeAllViews();
        ListPredicitionWing = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListPredicitionWing);
        ListPredicitionWing.removeAllViews();
        ListWingGoal = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListWingGoal);
        ListWingGoal.removeAllViews();
        ListWingMiss = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListWingMiss);
        ListWingMiss.removeAllViews();
        ListWingGoalkeeper = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListWingGoalkeeper);
        ListWingGoalkeeper.removeAllViews();
        ListPrediction6m = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListPrediction6m);
        ListPrediction6m.removeAllViews();
        List6mGoal = (LinearLayout) AfterGamePlayers.findViewById(R.id.List6mGoal);
        List6mGoal.removeAllViews();
        List6mMiss = (LinearLayout) AfterGamePlayers.findViewById(R.id.List6mMiss);
        List6mMiss.removeAllViews();
        List6mGoalkeeper = (LinearLayout) AfterGamePlayers.findViewById(R.id.List6mGoalkeeper);
        List6mGoalkeeper.removeAllViews();
        ListPrediction7m = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListPrediction7m);
        ListPrediction7m.removeAllViews();
        List7mGoal = (LinearLayout) AfterGamePlayers.findViewById(R.id.List7mGoal);
        List7mGoal.removeAllViews();
        List7mMiss = (LinearLayout) AfterGamePlayers.findViewById(R.id.List7mMiss);
        List7mMiss.removeAllViews();
        List7mGoalkeeper = (LinearLayout) AfterGamePlayers.findViewById(R.id.List7mGoalkeeper);
        List7mGoalkeeper.removeAllViews();
        ListPrediction9m = (LinearLayout) AfterGamePlayers.findViewById(R.id.ListPrediction9m);
        ListPrediction9m.removeAllViews();
        List9mGoal = (LinearLayout) AfterGamePlayers.findViewById(R.id.List9mGoal);
        List9mGoal.removeAllViews();
        List9mMiss = (LinearLayout) AfterGamePlayers.findViewById(R.id.List9mMiss);
        List9mMiss.removeAllViews();
        List9mGoalkeeper = (LinearLayout) AfterGamePlayers.findViewById(R.id.List9mGoalkeeper);
        List9mGoalkeeper.removeAllViews();
        int i = 0;
        Integer Color = 0;
        Cursor CR = DB.GetInformationPlayerHistory(DB, ConditionHistoryPlayer);
        if (CR.moveToFirst()) {
            do {
                if(i % 2 != 0)
                    Color = 0xC2C2C2FF;
                else
                    Color = 0;
                final TextView HistoryPlayerNumber = new TextView(CX);
                HistoryPlayerNumber.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryPlayerNumber.setPadding(0, 0, 16, 0);
                HistoryPlayerNumber.setBackgroundColor(Color);
                HistoryPlayerNumber.setText(CR.getString(Constants.HISTORY_PLAYER_NUMBER));
                ListOfNumbers.addView(HistoryPlayerNumber);
                final TextView HistoryPlayerName = new TextView(CX);
                HistoryPlayerName.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryPlayerName.setPadding(0, 0, 16, 0);
                HistoryPlayerName.setBackgroundColor(Color);
                HistoryPlayerName.setText(CR.getString(Constants.HISTORY_PLAYER_FIRST_NAME) + " " + CR.getString(Constants.HISTORY_PLAYER_SECOND_NAME));
                ListOfNames.addView(HistoryPlayerName);
                final TextView HistoryPlayersTimeOnPlayground = new TextView(CX);
                HistoryPlayersTimeOnPlayground.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryPlayersTimeOnPlayground.setPadding(0, 0, 16, 0);
                HistoryPlayersTimeOnPlayground.setBackgroundColor(Color);
                HistoryPlayersTimeOnPlayground.setText(CR.getString(Constants.HISTORY_PLAYERS_TIME_ON_PLAYGROUND));
                ListOfTimeOnPlayground.addView(HistoryPlayersTimeOnPlayground);
                final TextView HistoryShotsPrediction = new TextView(CX);
                HistoryShotsPrediction.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction.setPadding(0, 0, 16, 0);
                HistoryShotsPrediction.setBackgroundColor(Color);
                HistoryShotsPrediction.setText(CR.getString(Constants.HISTORY_SHOTS_PREDICTION) + "%");
                ListShotsPrediction.addView(HistoryShotsPrediction);
                final TextView HistoryGoalOnTarget = new TextView(CX);
                HistoryGoalOnTarget.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryGoalOnTarget.setPadding(0, 0, 16, 0);
                HistoryGoalOnTarget.setBackgroundColor(Color);
                HistoryGoalOnTarget.setText(CR.getString(Constants.HISTORY_GOAL_ON_TARGET));
                ListGoalOnTarget.addView(HistoryGoalOnTarget);
                final TextView HistoryShotMiss = new TextView(CX);
                HistoryShotMiss.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotMiss.setPadding(0, 0, 16, 0);
                HistoryShotMiss.setBackgroundColor(Color);
                HistoryShotMiss.setText(CR.getString(Constants.HISTORY_SHOT_MISS));
                ListShotMiss.addView(HistoryShotMiss);
                final TextView HistoryShotGoalkeeper = new TextView(CX);
                HistoryShotGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotGoalkeeper.setPadding(0, 0, 16, 0);
                HistoryShotGoalkeeper.setBackgroundColor(Color);
                HistoryShotGoalkeeper.setText(CR.getString(Constants.HISTORY_SHOT_GOALKEEPER));
                ListShotGoalkeeper.addView(HistoryShotGoalkeeper);
                final TextView HistoryFaulShotsPrediction = new TextView(CX);
                HistoryFaulShotsPrediction.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryFaulShotsPrediction.setPadding(0, 0, 16, 0);
                HistoryFaulShotsPrediction.setBackgroundColor(Color);
                HistoryFaulShotsPrediction.setText(CR.getString(Constants.HISTORY_FAUL_SHOTS_PREDICTION) + "%");
                ListFaulShotPrediction.addView(HistoryFaulShotsPrediction);
                final TextView HistoryFaulShotGoal = new TextView(CX);
                HistoryFaulShotGoal.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryFaulShotGoal.setPadding(0, 0, 16, 0);
                HistoryFaulShotGoal.setBackgroundColor(Color);
                HistoryFaulShotGoal.setText(CR.getString(Constants.HISTORY_FAUL_SHOT_GOAL));
                ListFaulShotGoal.addView(HistoryFaulShotGoal);
                final TextView HistoryFaulShotMiss = new TextView(CX);
                HistoryFaulShotMiss.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryFaulShotMiss.setPadding(0, 0, 16, 0);
                HistoryFaulShotMiss.setBackgroundColor(Color);
                HistoryFaulShotMiss.setText(CR.getString(Constants.HISTORY_FAUL_SHOT_MISS));
                ListFaulGoalMiss.addView(HistoryFaulShotMiss);
                final TextView HistoryReachFaulShot = new TextView(CX);
                HistoryReachFaulShot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryReachFaulShot.setPadding(0, 0, 16, 0);
                HistoryReachFaulShot.setBackgroundColor(Color);
                HistoryReachFaulShot.setText(CR.getString(Constants.HISTORY_REACH_FAUL_SHOT));
                ListReachFaulShot.addView(HistoryReachFaulShot);
                final TextView HistoryEfficientInDefensive = new TextView(CX);
                HistoryEfficientInDefensive.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryEfficientInDefensive.setPadding(0, 0, 16, 0);
                HistoryEfficientInDefensive.setBackgroundColor(Color);
                HistoryEfficientInDefensive.setText(CR.getString(Constants.HISTORY_EFFICIENT_IN_DEFENSIVE) + "%");
                ListEfficientDefensive.addView(HistoryEfficientInDefensive);
                final TextView HistoryPlus = new TextView(CX);
                HistoryPlus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryPlus.setPadding(0, 0, 16, 0);
                HistoryPlus.setBackgroundColor(Color);
                HistoryPlus.setText(CR.getString(Constants.HISTORY_PLUS));
                ListPlus.addView(HistoryPlus);
                final TextView HistoryMinus = new TextView(CX);
                HistoryMinus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryMinus.setPadding(0, 0, 16, 0);
                HistoryMinus.setBackgroundColor(Color);
                HistoryMinus.setText(CR.getString(Constants.HISTORY_MINUS));
                ListMinus.addView(HistoryMinus);
                final TextView HistoryFaul = new TextView(CX);
                HistoryFaul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryFaul.setPadding(0, 0, 16, 0);
                HistoryFaul.setBackgroundColor(Color);
                HistoryFaul.setText(CR.getString(Constants.HISTORY_FAUL));
                ListFaul.addView(HistoryFaul);
                final TextView HistoryYellowCard = new TextView(CX);
                HistoryYellowCard.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryYellowCard.setPadding(0, 0, 16, 0);
                HistoryYellowCard.setBackgroundColor(Color);
                HistoryYellowCard.setText(CR.getString(Constants.HISTORY_YELLOW_CARD));
                ListYellowCard.addView(HistoryYellowCard);
                final TextView HistoryRedCard = new TextView(CX);
                HistoryRedCard.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryRedCard.setPadding(0, 0, 16, 0);
                HistoryRedCard.setBackgroundColor(Color);
                HistoryRedCard.setText(CR.getString(Constants.HISTORY_RED_CARD));
                ListRedCard.addView(HistoryRedCard);
                final TextView HistoryReachBall = new TextView(CX);
                HistoryReachBall.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryReachBall.setPadding(0, 0, 16, 0);
                HistoryReachBall.setBackgroundColor(Color);
                HistoryReachBall.setText(CR.getString(Constants.HISTORY_REACH_BALL));
                ListReachBall.addView(HistoryReachBall);
                final TextView HistoryMissBall = new TextView(CX);
                HistoryMissBall.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryMissBall.setPadding(0, 0, 16, 0);
                HistoryMissBall.setBackgroundColor(Color);
                HistoryMissBall.setText(CR.getString(Constants.HISTORY_MISS_BALL));
                ListMissBall.addView(HistoryMissBall);
                final TextView HistoryShotsPredictionBrejk = new TextView(CX);
                HistoryShotsPredictionBrejk.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPredictionBrejk.setPadding(0, 0, 16, 0);
                HistoryShotsPredictionBrejk.setBackgroundColor(Color);
                HistoryShotsPredictionBrejk.setText(CR.getString(Constants.HISTORY_SHOTS_PREDICTION_BREJK) + "%");
                ListPredictionBrejk.addView(HistoryShotsPredictionBrejk);
                final TextView HistoryBrejkGoal = new TextView(CX);
                HistoryBrejkGoal.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryBrejkGoal.setPadding(0, 0, 16, 0);
                HistoryBrejkGoal.setBackgroundColor(Color);
                HistoryBrejkGoal.setText(CR.getString(Constants.HISTORY_BREJK_GOAL));
                ListBrejkGoal.addView(HistoryBrejkGoal);
                final TextView HistoryBrejkMiss = new TextView(CX);
                HistoryBrejkMiss.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryBrejkMiss.setPadding(0, 0, 16, 0);
                HistoryBrejkMiss.setBackgroundColor(Color);
                HistoryBrejkMiss.setText(CR.getString(Constants.HISTORY_BREJK_MISS));
                ListBrejkMiss.addView(HistoryBrejkMiss);
                final TextView HistoryCatchByGoalkeeperBrejk = new TextView(CX);
                HistoryCatchByGoalkeeperBrejk.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryCatchByGoalkeeperBrejk.setPadding(0, 0, 16, 0);
                HistoryCatchByGoalkeeperBrejk.setBackgroundColor(Color);
                HistoryCatchByGoalkeeperBrejk.setText(CR.getString(Constants.HISTORY_CATCH_BY_GOALKEEPER_BREJK));
                ListBrejkGoalkeeper.addView(HistoryCatchByGoalkeeperBrejk);
                final TextView HistoryShotsPredictionWing = new TextView(CX);
                HistoryShotsPredictionWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPredictionWing.setPadding(0, 0, 16, 0);
                HistoryShotsPredictionWing.setBackgroundColor(Color);
                HistoryShotsPredictionWing.setText(CR.getString(Constants.HISTORY_SHOTS_PREDICTION_WING) + "%");
                ListPredicitionWing.addView(HistoryShotsPredictionWing);
                final TextView HistoryGoalWing = new TextView(CX);
                HistoryGoalWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryGoalWing.setPadding(0, 0, 16, 0);
                HistoryGoalWing.setBackgroundColor(Color);
                HistoryGoalWing.setText(CR.getString(Constants.HISTORY_GOAL_WING));
                ListWingGoal.addView(HistoryGoalWing);
                final TextView HistoryMissWing = new TextView(CX);
                HistoryMissWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryMissWing.setPadding(0, 0, 16, 0);
                HistoryMissWing.setBackgroundColor(Color);
                HistoryMissWing.setText(CR.getString(Constants.HISTORY_MISS_WING));
                ListWingMiss.addView(HistoryMissWing);
                final TextView HistoryCatchByGoalkeeperWing = new TextView(CX);
                HistoryCatchByGoalkeeperWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryCatchByGoalkeeperWing.setPadding(0, 0, 16, 0);
                HistoryCatchByGoalkeeperWing.setBackgroundColor(Color);
                HistoryCatchByGoalkeeperWing.setText(CR.getString(Constants.HISTORY_CATCH_BY_GOALKEEPER_WING));
                ListWingGoalkeeper.addView(HistoryCatchByGoalkeeperWing);
                final TextView HistoryShotsPrediction6m = new TextView(CX);
                HistoryShotsPrediction6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction6m.setPadding(0, 0, 16, 0);
                HistoryShotsPrediction6m.setBackgroundColor(Color);
                HistoryShotsPrediction6m.setText(CR.getString(Constants.HISTORY_SHOTS_PREDICTION_6M) + "%");
                ListPrediction6m.addView(HistoryShotsPrediction6m);
                final TextView HistoryGoal6m = new TextView(CX);
                HistoryGoal6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryGoal6m.setPadding(0, 0, 16, 0);
                HistoryGoal6m.setBackgroundColor(Color);
                HistoryGoal6m.setText(CR.getString(Constants.HISTORY_GOAL_6M));
                List6mGoal.addView(HistoryGoal6m);
                final TextView HistoryMiss6m = new TextView(CX);
                HistoryMiss6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryMiss6m.setPadding(0, 0, 16, 0);
                HistoryMiss6m.setBackgroundColor(Color);
                HistoryMiss6m.setText(CR.getString(Constants.HISTORY_MISS_6M));
                List6mMiss.addView(HistoryMiss6m);
                final TextView HistoryCatchByGoalkeeper6m = new TextView(CX);
                HistoryCatchByGoalkeeper6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryCatchByGoalkeeper6m.setPadding(0, 0, 16, 0);
                HistoryCatchByGoalkeeper6m.setBackgroundColor(Color);
                HistoryCatchByGoalkeeper6m.setText(CR.getString(Constants.HISTORY_CATCH_BY_GOALKEEPER_6M));
                List6mGoalkeeper.addView(HistoryCatchByGoalkeeper6m);
                final TextView HistoryShotsPrediction7m = new TextView(CX);
                HistoryShotsPrediction7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction7m.setPadding(0, 0, 16, 0);
                HistoryShotsPrediction7m.setBackgroundColor(Color);
                HistoryShotsPrediction7m.setText(CR.getString(Constants.HISTORY_SHOTS_PREDICTION_7M) + "%");
                ListPrediction7m.addView(HistoryShotsPrediction7m);
                final TextView HistoryGoal7m = new TextView(CX);
                HistoryGoal7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryGoal7m.setPadding(0, 0, 16, 0);
                HistoryGoal7m.setBackgroundColor(Color);
                HistoryGoal7m.setText(CR.getString(Constants.HISTORY_GOAL_7M));
                List7mGoal.addView(HistoryGoal7m);
                final TextView HistoryMiss7m = new TextView(CX);
                HistoryMiss7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryMiss7m.setPadding(0, 0, 16, 0);
                HistoryMiss7m.setBackgroundColor(Color);
                HistoryMiss7m.setText(CR.getString(Constants.HISTORY_MISS_7M));
                List7mMiss.addView(HistoryMiss7m);
                final TextView HistoryCatchByGoalkeeper7m = new TextView(CX);
                HistoryCatchByGoalkeeper7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryCatchByGoalkeeper7m.setPadding(0, 0, 16, 0);
                HistoryCatchByGoalkeeper7m.setBackgroundColor(Color);
                HistoryCatchByGoalkeeper7m.setText(CR.getString(Constants.HISTORY_CATCH_BY_GOALKEEPER_7M));
                List7mGoalkeeper.addView(HistoryCatchByGoalkeeper7m);
                final TextView HistoryShotsPrediction9m = new TextView(CX);
                HistoryShotsPrediction9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction9m.setPadding(0, 0, 16, 0);
                HistoryShotsPrediction9m.setBackgroundColor(Color);
                HistoryShotsPrediction9m.setText(CR.getString(Constants.HISTORY_SHOTS_PREDICTION_9M) + "%");
                ListPrediction9m.addView(HistoryShotsPrediction9m);
                final TextView HistoryGoal9m = new TextView(CX);
                HistoryGoal9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryGoal9m.setPadding(0, 0, 16, 0);
                HistoryGoal9m.setBackgroundColor(Color);
                HistoryGoal9m.setText(CR.getString(Constants.HISTORY_GOAL_9M));
                List9mGoal.addView(HistoryGoal9m);
                final TextView HistoryMiss9m = new TextView(CX);
                HistoryMiss9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryMiss9m.setPadding(0, 0, 16, 0);
                HistoryMiss9m.setBackgroundColor(Color);
                HistoryMiss9m.setText(CR.getString(Constants.HISTORY_MISS_9M));
                List9mMiss.addView(HistoryMiss9m);
                final TextView HistoryCatchByGoalkeeper9m = new TextView(CX);
                HistoryCatchByGoalkeeper9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryCatchByGoalkeeper9m.setPadding(0, 0, 16, 0);
                HistoryCatchByGoalkeeper9m.setBackgroundColor(Color);
                HistoryCatchByGoalkeeper9m.setText(CR.getString(Constants.HISTORY_CATCH_BY_GOALKEEPER_9M));
                List9mGoalkeeper.addView(HistoryCatchByGoalkeeper9m);
                i++;
            }while(CR.moveToNext());
        }
    }

    public void ShowPlayersDetail(View AfterGamePlayers, String PlayerNumber) {
        PlayersTable = (ScrollView) AfterGamePlayers.findViewById(R.id.PlayersTable);
        PlayersTable.setVisibility(View.GONE);
        PlayersDetails = (RelativeLayout) AfterGamePlayers.findViewById(R.id.PlayersDetails);
        PlayersDetails.setVisibility(View.VISIBLE);
        Integer CountOfGoals = 0, CountOfMissShots = 0, CountOfShotsToGoalkeeper = 0, CountOfMissBalls = 0, CountOfReachFaulsShots = 0, CountOfPlus = 0, CountOfMinus = 0,
                CountOfYellowCards = 0, CountOfRedCards = 0, CountOfReachBalls = 0, CountOfTimeOnPlayground = 0, CountOfPlayedGames = 0;
        Cursor CR = DB.GetInformationPlayer(DB, ConditionPlayer);
        if (CR.moveToFirst()) {
            do {
                if (CR.getString(Constants.PLAYER_NUMBER).equals(PlayerNumber)) {
                    CountOfGoals = CountOfGoals + Integer.valueOf(CR.getString(Constants.GOAL_ON_TARGET));
                    CountOfMissShots = CountOfMissShots + Integer.valueOf(CR.getString(Constants.SHOT_MISS));
                    CountOfShotsToGoalkeeper = CountOfShotsToGoalkeeper + Integer.valueOf(CR.getString(Constants.SHOT_GOALKEEPER));
                    CountOfMissBalls = CountOfMissBalls + Integer.valueOf(CR.getString(Constants.MISS_BALL));
                    CountOfReachFaulsShots = CountOfReachFaulsShots + Integer.valueOf(CR.getString(Constants.REACH_FAUL_SHOT));
                    CountOfPlus = CountOfPlus + Integer.valueOf(CR.getString(Constants.PLUS));
                    CountOfMinus = CountOfMinus + Integer.valueOf(CR.getString(Constants.MINUS));
                    CountOfYellowCards = CountOfYellowCards + Integer.valueOf(CR.getString(Constants.YELLOW_CARD));
                    CountOfRedCards = CountOfRedCards + Integer.valueOf(CR.getString(Constants.RED_CARD));
                    CountOfReachBalls = CountOfReachBalls + Integer.valueOf(CR.getString(Constants.REACH_BALL));
                    CountOfTimeOnPlayground = CountOfTimeOnPlayground + Integer.valueOf(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND).split(":")[0]) * 60 +
                            Integer.valueOf(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND).split(":")[1]);
                    ;
                }
            } while (CR.moveToNext());
        }
        TextView Values = (TextView) AfterGamePlayers.findViewById(R.id.Values);
        Values.setText(String.valueOf(CountOfGoals) + "\n" + String.valueOf(CountOfMissShots) + "\n" + String.valueOf(CountOfShotsToGoalkeeper) + "\n" + String.valueOf(CountOfMissBalls) + "\n" +
                String.valueOf(CountOfReachFaulsShots) + "\n" + String.valueOf(CountOfPlus) + "\n" + String.valueOf(CountOfMinus) + "\n" + String.valueOf(CountOfYellowCards) + "\n" +
                String.valueOf(CountOfRedCards) + "\n" + String.valueOf(CountOfReachBalls) + "\n" + String.valueOf(CountOfTimeOnPlayground / 60) + ":" + String.valueOf(CountOfTimeOnPlayground % 60) + "\n" +
                String.valueOf(CountOfPlayedGames));
    }
}