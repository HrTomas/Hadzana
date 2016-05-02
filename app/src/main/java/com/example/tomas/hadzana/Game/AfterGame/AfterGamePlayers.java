package com.example.tomas.hadzana.Game.AfterGame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.pm.ActivityInfo;

import android.content.Context;
import android.content.res.Configuration;
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

public class AfterGamePlayers extends Fragment {

    Context CX;
    DatabaseOperations DB;
    RelativeLayout PlayersDetails, GameStatistic, GoalkeeperDetails, Buttons, GraphGameRecord, CompareLayout, HideEverything;

    ScrollView GoalkeepersTable, PlayersTable;

    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
            ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK;
            ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK;
            ConditionGame = " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK;
            ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        }

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
        Cursor CR = DB.getInformationPlayers(DB, ConditionPlayer + " GROUP BY " + TableData.TableInfo.PlayerNumber);
        if(CR.moveToFirst()) {
            do {
                if(i % 2 != 0)
                    Color = 0xC2C2C2FF;
                else
                    Color = 0;
                final TextView PlayerNumber = new TextView(CX);
                PlayerNumber.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PlayerNumber.setPadding(0, 0, 16, 0);
                PlayerNumber.setBackgroundColor(Color);
                PlayerNumber.setText(CR.getString(Constants.PLAYER_NUMBER));
                ListOfNumbers.addView(PlayerNumber);
                final TextView PlayerName = new TextView(CX);
                PlayerName.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PlayerName.setPadding(0, 0, 16, 0);
                PlayerName.setBackgroundColor(Color);
                PlayerName.setText(CR.getString(Constants.PLAYER_FIRST_NAME) + " " + CR.getString(Constants.PLAYER_SECOND_NAME));
                ListOfNames.addView(PlayerName);
                final TextView PlayersTimeOnPlayground = new TextView(CX);
                PlayersTimeOnPlayground.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                PlayersTimeOnPlayground.setPadding(0, 0, 16, 0);
                PlayersTimeOnPlayground.setBackgroundColor(Color);
                PlayersTimeOnPlayground.setText(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND));
                ListOfTimeOnPlayground.addView(PlayersTimeOnPlayground);
                final TextView HistoryShotsPrediction = new TextView(CX);
                HistoryShotsPrediction.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryShotsPrediction.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryShotsPrediction.setBackgroundColor(0);
                HistoryShotsPrediction.setText(CR.getString(Constants.SHOTS_PREDICTION_FOR_GAME) + "%");
                ListShotsPrediction.addView(HistoryShotsPrediction);
                final TextView GoalOnTarget = new TextView(CX);
                GoalOnTarget.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                GoalOnTarget.setPadding(0, 0, 16, 0);
                GoalOnTarget.setBackgroundColor(Color);
                GoalOnTarget.setText(CR.getString(Constants.GOAL_ON_TARGET));
                ListGoalOnTarget.addView(GoalOnTarget);
                final TextView ShotMiss = new TextView(CX);
                ShotMiss.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                ShotMiss.setPadding(0, 0, 16, 0);
                ShotMiss.setBackgroundColor(Color);
                ShotMiss.setText(CR.getString(Constants.SHOT_MISS));
                ListShotMiss.addView(ShotMiss);
                final TextView ShotGoalkeeper = new TextView(CX);
                ShotGoalkeeper.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                ShotGoalkeeper.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    ShotGoalkeeper.setBackgroundColor(0xC2C2C2FF);
                else
                    ShotGoalkeeper.setBackgroundColor(0);
                ShotGoalkeeper.setText(CR.getString(Constants.SHOT_GOALKEEPER));
                ListShotGoalkeeper.addView(ShotGoalkeeper);
                final TextView HistoryFaulShotsPrediction = new TextView(CX);
                HistoryFaulShotsPrediction.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryFaulShotsPrediction.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryFaulShotsPrediction.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryFaulShotsPrediction.setBackgroundColor(0);
                HistoryFaulShotsPrediction.setText(CR.getString(Constants.FAUL_SHOTS_PREDICTION_FOR_GAME) + "%");
                ListFaulShotPrediction.addView(HistoryFaulShotsPrediction);
                final TextView FaulShotGoal = new TextView(CX);
                FaulShotGoal.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                FaulShotGoal.setPadding(0, 0, 16, 0);
                FaulShotGoal.setBackgroundColor(Color);
                FaulShotGoal.setText(CR.getString(Constants.FAUL_SHOT_GOAL));
                ListFaulShotGoal.addView(FaulShotGoal);
                final TextView FaulShotMiss = new TextView(CX);
                FaulShotMiss.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                FaulShotMiss.setPadding(0, 0, 16, 0);
                FaulShotMiss.setBackgroundColor(Color);
                FaulShotMiss.setText(CR.getString(Constants.FAUL_SHOT_MISS));
                ListFaulGoalMiss.addView(FaulShotMiss);
                final TextView ReachFaulShot = new TextView(CX);
                ReachFaulShot.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                ReachFaulShot.setPadding(0, 0, 16, 0);
                ReachFaulShot.setBackgroundColor(Color);
                ReachFaulShot.setText(CR.getString(Constants.REACH_FAUL_SHOT));
                ListReachFaulShot.addView(ReachFaulShot);
                final TextView HistoryEfficientInDefensive = new TextView(CX);
                HistoryEfficientInDefensive.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryEfficientInDefensive.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryEfficientInDefensive.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryEfficientInDefensive.setBackgroundColor(0);
                HistoryEfficientInDefensive.setText(CR.getString(Constants.EFFICIENT_IN_DEFENSIVE_FOR_GAME) + "%");
                ListEfficientDefensive.addView(HistoryEfficientInDefensive);
                final TextView Plus = new TextView(CX);
                Plus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Plus.setPadding(0, 0, 16, 0);
                Plus.setBackgroundColor(Color);
                Plus.setText(CR.getString(Constants.PLUS));
                ListPlus.addView(Plus);
                final TextView Minus = new TextView(CX);
                Minus.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Minus.setPadding(0, 0, 16, 0);
                Minus.setBackgroundColor(Color);
                Minus.setText(CR.getString(Constants.MINUS));
                ListMinus.addView(Minus);
                final TextView Faul = new TextView(CX);
                Faul.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Faul.setPadding(0, 0, 16, 0);
                Faul.setBackgroundColor(Color);
                Faul.setText(CR.getString(Constants.FAUL));
                ListFaul.addView(Faul);
                final TextView YellowCard = new TextView(CX);
                YellowCard.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                YellowCard.setPadding(0, 0, 16, 0);
                YellowCard.setBackgroundColor(Color);
                YellowCard.setText(CR.getString(Constants.YELLOW_CARD));
                ListYellowCard.addView(YellowCard);
                final TextView RedCard = new TextView(CX);
                RedCard.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                RedCard.setPadding(0, 0, 16, 0);
                RedCard.setBackgroundColor(Color);
                RedCard.setText(CR.getString(Constants.RED_CARD));
                ListRedCard.addView(RedCard);
                final TextView ReachBall = new TextView(CX);
                ReachBall.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                ReachBall.setPadding(0, 0, 16, 0);
                ReachBall.setBackgroundColor(Color);
                ReachBall.setText(CR.getString(Constants.REACH_BALL));
                ListReachBall.addView(ReachBall);
                final TextView MissBall = new TextView(CX);
                MissBall.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                MissBall.setPadding(0, 0, 16, 0);
                MissBall.setBackgroundColor(Color);
                MissBall.setText(CR.getString(Constants.MISS_BALL));
                ListMissBall.addView(MissBall);
                final TextView HistoryShotsPredictionBrejk = new TextView(CX);
                HistoryShotsPredictionBrejk.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPredictionBrejk.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryShotsPredictionBrejk.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryShotsPredictionBrejk.setBackgroundColor(0);
                HistoryShotsPredictionBrejk.setText(CR.getString(Constants.SHOTS_PREDICTION_BREJK_FOR_GAME) + "%");
                ListPredictionBrejk.addView(HistoryShotsPredictionBrejk);
                final TextView BrejkGoal = new TextView(CX);
                BrejkGoal.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                BrejkGoal.setPadding(0, 0, 16, 0);
                BrejkGoal.setBackgroundColor(Color);
                BrejkGoal.setText(CR.getString(Constants.BREJK_GOAL));
                ListBrejkGoal.addView(BrejkGoal);
                final TextView BrejkMiss = new TextView(CX);
                BrejkMiss.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                BrejkMiss.setPadding(0, 0, 16, 0);
                BrejkMiss.setBackgroundColor(Color);
                BrejkMiss.setText(CR.getString(Constants.BREJK_MISS));
                ListBrejkMiss.addView(BrejkMiss);
                final TextView CatchByGoalkeeperBrejk = new TextView(CX);
                CatchByGoalkeeperBrejk.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CatchByGoalkeeperBrejk.setPadding(0, 0, 16, 0);
                CatchByGoalkeeperBrejk.setBackgroundColor(Color);
                CatchByGoalkeeperBrejk.setText(CR.getString(Constants.CATCH_BY_GOALKEEPER_BREJK));
                ListBrejkGoalkeeper.addView(CatchByGoalkeeperBrejk);
                final TextView HistoryShotsPredictionWing = new TextView(CX);
                HistoryShotsPredictionWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPredictionWing.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryShotsPredictionWing.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryShotsPredictionWing.setBackgroundColor(0);
                HistoryShotsPredictionWing.setText(CR.getString(Constants.SHOTS_PREDICTION_WING_FOR_GAME) + "%");
                ListPredicitionWing.addView(HistoryShotsPredictionWing);
                final TextView GoalWing = new TextView(CX);
                GoalWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                GoalWing.setPadding(0, 0, 16, 0);
                GoalWing.setBackgroundColor(Color);
                GoalWing.setText(CR.getString(Constants.GOAL_WING));
                ListWingGoal.addView(GoalWing);
                final TextView MissWing = new TextView(CX);
                MissWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                MissWing.setPadding(0, 0, 16, 0);
                MissWing.setBackgroundColor(Color);
                MissWing.setText(CR.getString(Constants.MISS_WING));
                ListWingMiss.addView(MissWing);
                final TextView CatchByGoalkeeperWing = new TextView(CX);
                CatchByGoalkeeperWing.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CatchByGoalkeeperWing.setPadding(0, 0, 16, 0);
                CatchByGoalkeeperWing.setBackgroundColor(Color);
                CatchByGoalkeeperWing.setText(CR.getString(Constants.CATCH_BY_GOALKEEPER_WING));
                ListWingGoalkeeper.addView(CatchByGoalkeeperWing);
                final TextView HistoryShotsPrediction6m = new TextView(CX);
                HistoryShotsPrediction6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction6m.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryShotsPrediction6m.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryShotsPrediction6m.setBackgroundColor(0);
                HistoryShotsPrediction6m.setText(CR.getString(Constants.SHOTS_PREDICTION_6M_FOR_GAME) + "%");
                ListPrediction6m.addView(HistoryShotsPrediction6m);
                final TextView Goal6m = new TextView(CX);
                Goal6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Goal6m.setPadding(0, 0, 16, 0);
                Goal6m.setBackgroundColor(Color);
                Goal6m.setText(CR.getString(Constants.GOAL_6M));
                List6mGoal.addView(Goal6m);
                final TextView Miss6m = new TextView(CX);
                Miss6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Miss6m.setPadding(0, 0, 16, 0);
                Miss6m.setBackgroundColor(Color);
                Miss6m.setText(CR.getString(Constants.MISS_6M));
                List6mMiss.addView(Miss6m);
                final TextView CatchByGoalkeeper6m = new TextView(CX);
                CatchByGoalkeeper6m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CatchByGoalkeeper6m.setPadding(0, 0, 16, 0);
                CatchByGoalkeeper6m.setBackgroundColor(Color);
                CatchByGoalkeeper6m.setText(CR.getString(Constants.CATCH_BY_GOALKEEPER_6M));
                List6mGoalkeeper.addView(CatchByGoalkeeper6m);
                final TextView HistoryShotsPrediction7m = new TextView(CX);
                HistoryShotsPrediction7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction7m.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryShotsPrediction7m.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryShotsPrediction7m.setBackgroundColor(0);
                HistoryShotsPrediction7m.setText(CR.getString(Constants.SHOTS_PREDICTION_7M_FOR_GAME) + "%");
                ListPrediction7m.addView(HistoryShotsPrediction7m);
                final TextView Goal7m = new TextView(CX);
                Goal7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Goal7m.setPadding(0, 0, 16, 0);
                Goal7m.setBackgroundColor(Color);
                Goal7m.setText(CR.getString(Constants.GOAL_7M));
                List7mGoal.addView(Goal7m);
                final TextView Miss7m = new TextView(CX);
                Miss7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Miss7m.setPadding(0, 0, 16, 0);
                Miss7m.setBackgroundColor(Color);
                Miss7m.setText(CR.getString(Constants.MISS_7M));
                List7mMiss.addView(Miss7m);
                final TextView CatchByGoalkeeper7m = new TextView(CX);
                CatchByGoalkeeper7m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CatchByGoalkeeper7m.setPadding(0, 0, 16, 0);
                CatchByGoalkeeper7m.setBackgroundColor(Color);
                CatchByGoalkeeper7m.setText(CR.getString(Constants.CATCH_BY_GOALKEEPER_7M));
                List7mGoalkeeper.addView(CatchByGoalkeeper7m);
                final TextView HistoryShotsPrediction9m = new TextView(CX);
                HistoryShotsPrediction9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                HistoryShotsPrediction9m.setPadding(0, 0, 16, 0);
                if(i % 2 != 0)
                    HistoryShotsPrediction9m.setBackgroundColor(0xC2C2C2FF);
                else
                    HistoryShotsPrediction9m.setBackgroundColor(0);
                HistoryShotsPrediction9m.setText(CR.getString(Constants.SHOTS_PREDICTION_9M_FOR_GAME) + "%");
                ListPrediction9m.addView(HistoryShotsPrediction9m);
                final TextView Goal9m = new TextView(CX);
                Goal9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Goal9m.setPadding(0, 0, 16, 0);
                Goal9m.setBackgroundColor(Color);
                Goal9m.setText(CR.getString(Constants.GOAL_9M));
                List9mGoal.addView(Goal9m);
                final TextView Miss9m = new TextView(CX);
                Miss9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                Miss9m.setPadding(0, 0, 16, 0);
                Miss9m.setBackgroundColor(Color);
                Miss9m.setText(CR.getString(Constants.MISS_9M));
                List9mMiss.addView(Miss9m);
                final TextView CatchByGoalkeeper9m = new TextView(CX);
                CatchByGoalkeeper9m.setTextSize(TextSizeForScreens); /*TODO: set size from constats in dimens */
                CatchByGoalkeeper9m.setPadding(0, 0, 16, 0);
                CatchByGoalkeeper9m.setBackgroundColor(Color);
                CatchByGoalkeeper9m.setText(CR.getString(Constants.CATCH_BY_GOALKEEPER_9M));
                List9mGoalkeeper.addView(CatchByGoalkeeper9m);

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
        Cursor CR = DB.getInformationPlayers(DB, ConditionPlayer);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(Constants.PLAYER_NUMBER).equals(PlayerNumber)){
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
                    CountOfTimeOnPlayground = CountOfTimeOnPlayground + Integer.valueOf(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND).split(":")[0])*60 +
                            Integer.valueOf(CR.getString(Constants.PLAYERS_TIME_ON_PLAYGROUND).split(":")[1]);;
                }
            }while(CR.moveToNext());
        }
        TextView Values = (TextView) AfterGamePlayers.findViewById(R.id.Values);
        Values.setText(String.valueOf(CountOfGoals) + "\n" + String.valueOf(CountOfMissShots) + "\n" + String.valueOf(CountOfShotsToGoalkeeper) + "\n" + String.valueOf(CountOfMissBalls) + "\n" +
                String.valueOf(CountOfReachFaulsShots) + "\n" + String.valueOf(CountOfPlus) + "\n" + String.valueOf(CountOfMinus) + "\n" + String.valueOf(CountOfYellowCards) + "\n" +
                String.valueOf(CountOfRedCards) + "\n" + String.valueOf(CountOfReachBalls) + "\n" + String.valueOf(CountOfTimeOnPlayground / 60) + ":" + String.valueOf(CountOfTimeOnPlayground % 60) + "\n" +
                String.valueOf(CountOfPlayedGames));
    }

    public String CountValue(String PlayersNumber, Integer Position) {
        Integer FinalCount = 0;
        Cursor CR = DB.getInformationPlayers(DB, ConditionPlayer);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(2).equals(PlayersNumber)) {
                    FinalCount = FinalCount + Integer.parseInt(CR.getString(Position));
                }
            }while(CR.moveToNext());
        }
        return String.valueOf(FinalCount);
    }

    public String CountTime(String PlayersNumber, Integer PositionInString) {
        String FinalCountMinutes = "0";
        String FinalCountSeconds = "0";
        Cursor CR = DB.getInformationPlayers(DB, ConditionPlayer);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(Constants.PLAYER_NUMBER).equals(PlayersNumber)) {
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
