package com.example.tomas.hadzana;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;

public class CountingFunctions {

    public static DatabaseOperations DB;

    public static String PlayerInformations[] = {
            TableData.TableInfo.FirstName,
            TableData.TableInfo.SecondName,
            TableData.TableInfo.Number,
            TableData.TableInfo.CountGoalOnTarget,
            TableData.TableInfo.CountShotMiss,
            TableData.TableInfo.CountShotGoalkeeper,
            TableData.TableInfo.CountMissBall,
            TableData.TableInfo.CountReachFaulShot,
            TableData.TableInfo.CountPlus,
            TableData.TableInfo.CountMinus,
            TableData.TableInfo.CountFaul,
            TableData.TableInfo.CountYellowCard,
            TableData.TableInfo.CountRedCard,
            TableData.TableInfo.CountReachBall,
            TableData.TableInfo.CountFaulShotGoal,
            TableData.TableInfo.CountFaulShotMiss,
            TableData.TableInfo.CountBrejkGoal,
            TableData.TableInfo.CountBrejkMiss,
            TableData.TableInfo.CountGoalWing,
            TableData.TableInfo.CountMissWing,
            TableData.TableInfo.CountGoal6m,
            TableData.TableInfo.CountGoal7m,
            TableData.TableInfo.CountGoal9m,
            TableData.TableInfo.CountMiss6m,
            TableData.TableInfo.CountMiss7m,
            TableData.TableInfo.CountMiss9m,
            TableData.TableInfo.CountCatchByGoalkeeperBrejk,
            TableData.TableInfo.CountCatchByGoalkeeperWing,
            TableData.TableInfo.CountCatchByGoalkeeper6m,
            TableData.TableInfo.CountCatchByGoalkeeper7m,
            TableData.TableInfo.CountCatchByGoalkeeper9m,
            TableData.TableInfo.CountPlayersTimeOnPlayground,
            TableData.TableInfo.ShotsPrediction,
            TableData.TableInfo.ShotsPredictionBrejk,
            TableData.TableInfo.ShotsPredictionWing,
            TableData.TableInfo.ShotsPrediction6m,
            TableData.TableInfo.ShotsPrediction7m,
            TableData.TableInfo.ShotsPrediction9m,
            TableData.TableInfo.FaulShotsPrediction,
            TableData.TableInfo.EfficientInDefensive,
            TableData.TableInfo.CountOfPlayedGames,
            TableData.TableInfo.Exploitation,
            TableData.TableInfo.PrimaryPosition,
            TableData.TableInfo.SecondaryPosition,
            TableData.TableInfo.BirthDate,
            TableData.TableInfo.SocialSecurityNumber,
            TableData.TableInfo.Gender,
            TableData.TableInfo.Note,
            TableData.TableInfo.ValuesChange,
            TableData.TableInfo.PrimaryKeyForPlayer,
            TableData.TableInfo.TurnamentPKRoster};

    public static String GoalkeepersInformations[] = {
            TableData.TableInfo.GoalkeeperFirstNameRoster,
            TableData.TableInfo.GoalkeeperSecondNameRoster,
            TableData.TableInfo.GoalkeeperNumberRoster,
            TableData.TableInfo.CountCatch,
            TableData.TableInfo.CountPassLongGood,
            TableData.TableInfo.CountPassLongBad,
            TableData.TableInfo.CountPassShortGood,
            TableData.TableInfo.CountPassShortBad,
            TableData.TableInfo.CountCatchFaulShot,
            TableData.TableInfo.CountMissFaulShot,
            TableData.TableInfo.CountRecoveredGoals,
            TableData.TableInfo.CountBrejkCatch,
            TableData.TableInfo.CountBrejkRecovered,
            TableData.TableInfo.CountWingCatch,
            TableData.TableInfo.CountWingRecovered,
            TableData.TableInfo.CountCatch6m,
            TableData.TableInfo.CountCatch7m,
            TableData.TableInfo.CountCatch9m,
            TableData.TableInfo.CountRecovered6m,
            TableData.TableInfo.CountRecovered7m,
            TableData.TableInfo.CountRecovered9m,
            TableData.TableInfo.PredictionOfCatch,
            TableData.TableInfo.PredictionOfCatchBrejk,
            TableData.TableInfo.PredictionOfCatchWing,
            TableData.TableInfo.PredictionOfCatch6m,
            TableData.TableInfo.PredictionOfCatch7m,
            TableData.TableInfo.PredictionOfCatch9m,
            TableData.TableInfo.PredictionOfLongPass,
            TableData.TableInfo.PredictionOfShortPass,
            TableData.TableInfo.PredictionOfCatchFaulshot,
            TableData.TableInfo.CountOfPlayedGamesGoalkeeper,
            TableData.TableInfo.CountOfInterventions,
            TableData.TableInfo.CountFaulGoalkeeper,
            TableData.TableInfo.CountYellowCardGoalkeeper,
            TableData.TableInfo.CountRedCardGoalkeeper,
            TableData.TableInfo.CountOfTimeOnPlayGroundGoalkeeper,
            TableData.TableInfo.GoalkeeperBirthDate,
            TableData.TableInfo.GoalkeeperSocialSecurityNumber,
            TableData.TableInfo.GoalkeeperGender,
            TableData.TableInfo.GoalkeeperNote,
            TableData.TableInfo.GoalkeeperValuesChange,
            TableData.TableInfo.GoalkeeperPrimaryKeyRoster,
            TableData.TableInfo.TurnamentGoalkeeperPKRoster};

    public static String GameInformations[] = {
            TableData.TableInfo.TeamName,
            TableData.TableInfo.NameOfOponent,
            TableData.TableInfo.HalftimeScore,
            TableData.TableInfo.FinalScore,
            TableData.TableInfo.MatchTime,
            TableData.TableInfo.TimeInOfensive,
            TableData.TableInfo.CountOfOfensive,
            TableData.TableInfo.TimeInDefensive,
            TableData.TableInfo.CountOfDefensive,
            TableData.TableInfo.GameReplay,
            TableData.TableInfo.GameReport,
            TableData.TableInfo.PowerPlaysCount,
            TableData.TableInfo.PowerPlaysWin,
            TableData.TableInfo.ReachedGoalWhilePowerPlay,
            TableData.TableInfo.CountOfWeakening,
            TableData.TableInfo.GoalWhileWeakening,
            TableData.TableInfo.ReachedGoalWhileWeakening,
            TableData.TableInfo.GameCountOfShots,
            TableData.TableInfo.GameCountOfMissShots,
            TableData.TableInfo.GameCountOfgoalkeeperShots,
            TableData.TableInfo.GameCountOfMissBalls,
            TableData.TableInfo.GameCountOfFaulShots,
            TableData.TableInfo.GameCountOfPlus,
            TableData.TableInfo.GameCountOfMinus,
            TableData.TableInfo.GameCountOfFauls,
            TableData.TableInfo.GameCountOfYellowCards,
            TableData.TableInfo.GameCountOfRedCards,
            TableData.TableInfo.GameCountOfReachBalls,
            TableData.TableInfo.GameCountOfFaulShotsGoal,
            TableData.TableInfo.GameCountOfFaulShotsMiss,
            TableData.TableInfo.GameCountOfGoalBrejk,
            TableData.TableInfo.GameCountOfMissBrejk,
            TableData.TableInfo.GameCountOfGoalWing,
            TableData.TableInfo.GameCountOfMissWing,
            TableData.TableInfo.GameCountOfGoal6m,
            TableData.TableInfo.GameCountOfGoal7m,
            TableData.TableInfo.GameCountOfGoal9m,
            TableData.TableInfo.GameCountOfMiss6m,
            TableData.TableInfo.GameCountOfMiss7m,
            TableData.TableInfo.GameCountOfMiss9m,
            TableData.TableInfo.GameCountOfGoalkeeperBrejk,
            TableData.TableInfo.GameCountOfGoalkeeperWing,
            TableData.TableInfo.GameCountOfGoalkeeper6m,
            TableData.TableInfo.GameCountOfGoalkeeper7m,
            TableData.TableInfo.GameCountOfGoalkeeper9m,
            TableData.TableInfo.GamePredictionOfShots,
            TableData.TableInfo.GamePredictionOfShotsBrejk,
            TableData.TableInfo.GamePredictionOfShotsWing,
            TableData.TableInfo.GamePredictionOfShots6m,
            TableData.TableInfo.GamePredictionOfShots7m,
            TableData.TableInfo.GamePredictionOfShots9m,
            TableData.TableInfo.MatchSuccessfullyCompleted,
            TableData.TableInfo.GamePlayedHome,
            TableData.TableInfo.DateTimePKGame,
            TableData.TableInfo.PKNumberGame,
            TableData.TableInfo.TurnamentGamePK};

    public String GameSummary[] = {
            TableData.TableInfo.SummaryCountWinGames,
            TableData.TableInfo.SummaryCountWinGamesHome,
            TableData.TableInfo.SummaryCountWinGamesOut,
            TableData.TableInfo.SummaryCountLoseGames,
            TableData.TableInfo.SummaryCountLoseGamesHome,
            TableData.TableInfo.SummaryCountLoseGamesOut,
            TableData.TableInfo.SummaryCountOfOfensive,
            TableData.TableInfo.SummaryCountOfDefensive,
            TableData.TableInfo.SummaryPowerPlaysCount,
            TableData.TableInfo.SummaryPowerPlaysWin,
            TableData.TableInfo.SummaryReachedGoalWhilePowerPlay,
            TableData.TableInfo.SummaryCountOfWeakening,
            TableData.TableInfo.SummaryGoalWhileWeakening,
            TableData.TableInfo.SummaryReachedGoalWhileWeakening,
            TableData.TableInfo.SummaryGameCountOfShots,
            TableData.TableInfo.SummaryGameCountOfMissShots,
            TableData.TableInfo.SummaryGameCountOfgoalkeeperShots,
            TableData.TableInfo.SummaryGameCountOfMissBalls,
            TableData.TableInfo.SummaryGameCountOfFaulShots,
            TableData.TableInfo.SummaryGameCountOfPlus,
            TableData.TableInfo.SummaryGameCountOfMinus,
            TableData.TableInfo.SummaryGameCountOfFauls,
            TableData.TableInfo.SummaryGameCountOfYellowCards,
            TableData.TableInfo.SummaryGameCountOfRedCards,
            TableData.TableInfo.SummaryGameCountOfReachBalls,
            TableData.TableInfo.SummaryGameCountOfFaulShotsGoal,
            TableData.TableInfo.SummaryGameCountOfFaulShotsMiss,
            TableData.TableInfo.SummaryGameCountOfGoalBrejk,
            TableData.TableInfo.SummaryGameCountOfMissBrejk,
            TableData.TableInfo.SummaryGameCountOfGoalWing,
            TableData.TableInfo.SummaryGameCountOfMissWing,
            TableData.TableInfo.SummaryGameCountOfGoal6m,
            TableData.TableInfo.SummaryGameCountOfGoal7m,
            TableData.TableInfo.SummaryGameCountOfGoal9m,
            TableData.TableInfo.SummaryGameCountOfMiss6m,
            TableData.TableInfo.SummaryGameCountOfMiss7m,
            TableData.TableInfo.SummaryGameCountOfMiss9m,
            TableData.TableInfo.SummaryGameCountOfGoalkeeperBrejk,
            TableData.TableInfo.SummaryGameCountOfGoalkeeperWing,
            TableData.TableInfo.SummaryGameCountOfGoalkeeper6m,
            TableData.TableInfo.SummaryGameCountOfGoalkeeper7m,
            TableData.TableInfo.SummaryGameCountOfGoalkeeper9m,
            TableData.TableInfo.SummaryGamePredictionOfShots,
            TableData.TableInfo.SummaryGamePredictionOfShotsBrejk,
            TableData.TableInfo.SummaryGamePredictionOfShotsWing,
            TableData.TableInfo.SummaryGamePredictionOfShots6m,
            TableData.TableInfo.SummaryGamePredictionOfShots7m,
            TableData.TableInfo.SummaryGamePredictionOfShots9m,
            TableData.TableInfo.SummaryGamePredictionOfFaulsShots,
            TableData.TableInfo.SummaryCountGamePlayedHome,
            TableData.TableInfo.SummaryCountGamePlayedOut,
            TableData.TableInfo.SummaryMatchTime,
            TableData.TableInfo.SummaryTimeInOfensive,
            TableData.TableInfo.SummaryTimeInDefensive,
            TableData.TableInfo.SummaryValuesChange,
            TableData.TableInfo.SummaryGameTurnamentPK};

    public CountingFunctions(Context context) {
        DB = new DatabaseOperations(context);
    }

    public void GameTeam(String TurnamentPK) {
        Cursor CR = DB.getInformationGame(DB, "");
        if(!CR.moveToLast())
            return;
        String PKGame = CR.getString(Constants.PK_NUMBER_GAME);
        CR.close();
        CR = DB.getInformationPlayers(DB, "");
        if(!CR.moveToFirst())
            return;
        Integer Count = 0;
        int j = Constants.GOAL_ON_TARGET;
        for(int i = Constants.GAME_COUNT_OF_SHOTS; i <= Constants.GAME_COUNT_OF_RED_CARDS; i++) {
            do {
                Count += CR.getInt(j);
            }while(CR.moveToNext());
            DB.updateInformationsGame(DB, PKGame, GameInformations[i], String.valueOf(Count), TurnamentPK);
            Count = 0;
            CR.moveToFirst();
            j++;
        }
        CR.close();
        CR = DB.getInformationGame(DB, "");
        if(!CR.moveToLast())
            return;
        PKGame = CR.getString(Constants.PK_NUMBER_GAME);
        Count = CR.getInt(Constants.GAME_COUNT_OF_SHOTS) + CR.getInt(Constants.GAME_COUNT_OF_MISS_SHOTS) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER_SHOTS);
        if(Count != 0)
            DB.updateInformationsGame(DB, PKGame, TableData.TableInfo.GamePredictionOfShots, String.valueOf(((CR.getInt(Constants.GAME_COUNT_OF_SHOTS) * 100) / Count)), TurnamentPK);
        Count = CR.getInt(Constants.GAME_COUNT_OF_GOAL_BREJK) + CR.getInt(Constants.GAME_COUNT_OF_MISS_BREJK) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER_BREJK);
        if(Count != 0)
            DB.updateInformationsGame(DB, PKGame, TableData.TableInfo.GamePredictionOfShotsBrejk, String.valueOf(((CR.getInt(Constants.GAME_COUNT_OF_GOAL_BREJK) * 100) / Count)), TurnamentPK);
        Count = CR.getInt(Constants.GAME_COUNT_OF_GOAL_WING) + CR.getInt(Constants.GAME_COUNT_OF_MISS_WING) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER_WING);
        if(Count != 0)
            DB.updateInformationsGame(DB, PKGame, TableData.TableInfo.GamePredictionOfShotsWing, String.valueOf(((CR.getInt(Constants.GAME_COUNT_OF_GOAL_WING) * 100) / Count)), TurnamentPK);
        Count = CR.getInt(Constants.GAME_COUNT_OF_GOAL_6M) + CR.getInt(Constants.GAME_COUNT_OF_MISS_6M) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER6_M);
        if(Count != 0)
            DB.updateInformationsGame(DB, PKGame, TableData.TableInfo.GamePredictionOfShots6m, String.valueOf(((CR.getInt(Constants.GAME_COUNT_OF_GOAL_6M) * 100) / Count)), TurnamentPK);
        Count = CR.getInt(Constants.GAME_COUNT_OF_GOAL_7M) + CR.getInt(Constants.GAME_COUNT_OF_MISS_7M) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER_7M);
        if(Count != 0)
            DB.updateInformationsGame(DB, PKGame, TableData.TableInfo.GamePredictionOfShots7m, String.valueOf(((CR.getInt(Constants.GAME_COUNT_OF_GOAL_7M) * 100) / Count)), TurnamentPK);
        Count = CR.getInt(Constants.GAME_COUNT_OF_GOAL_9M) + CR.getInt(Constants.GAME_COUNT_OF_MISS_9M) + CR.getInt(Constants.GAME_COUNT_OF_GOALKEEPER_9M);
        if(Count != 0)
            DB.updateInformationsGame(DB, PKGame, TableData.TableInfo.GamePredictionOfShots9m, String.valueOf(((CR.getInt(Constants.GAME_COUNT_OF_GOAL_9M) * 100) / Count)), TurnamentPK);
        Count = CR.getInt(Constants.GAME_COUNT_OF_FAUL_SHOTS_GOAL) + CR.getInt(Constants.GAME_COUNT_OF_FAUL_SHOTS_MISS);
        if(Count != 0)
            DB.updateInformationsGame(DB, PKGame, TableData.TableInfo.GamePredictionOfFaulsShots, String.valueOf(((CR.getInt(Constants.GAME_COUNT_OF_FAUL_SHOTS_GOAL) * 100) / Count)), TurnamentPK);
        CR.close();
    }



    public void GamePlayer(String TurnamentPK) {
        Cursor CR = DB.getInformationPlayers(DB, " WHERE " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        Integer Count = 0;
        do{
            if (CR.getString(Constants.VALUES_ARE_CONVERTED_PLAYER).equals("false")) {
                Count = CR.getInt(Constants.HISTORY_GOAL_ON_TARGET) + CR.getInt(Constants.HISTORY_SHOT_MISS) + CR.getInt(Constants.HISTORY_SHOT_GOALKEEPER);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ShotsPredictionForGame, String.valueOf(((CR.getInt(Constants.HISTORY_GOAL_ON_TARGET) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.HISTORY_BREJK_GOAL) + CR.getInt(Constants.HISTORY_BREJK_MISS) + CR.getInt(Constants.HISTORY_CATCH_BY_GOALKEEPER_BREJK);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ShotsPredictionBrejkForGame, String.valueOf(((CR.getInt(Constants.HISTORY_BREJK_GOAL) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.HISTORY_GOAL_WING) + CR.getInt(Constants.HISTORY_MISS_WING) + CR.getInt(Constants.HISTORY_CATCH_BY_GOALKEEPER_WING);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ShotsPredictionWingForGame, String.valueOf(((CR.getInt(Constants.HISTORY_GOAL_WING) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.HISTORY_GOAL_6M) + CR.getInt(Constants.HISTORY_MISS_6M) + CR.getInt(Constants.HISTORY_CATCH_BY_GOALKEEPER_6M);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ShotsPrediction6mForGame, String.valueOf(((CR.getInt(Constants.HISTORY_GOAL_6M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.HISTORY_GOAL_7M) + CR.getInt(Constants.HISTORY_MISS_7M) + CR.getInt(Constants.HISTORY_CATCH_BY_GOALKEEPER_7M);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ShotsPrediction7mForGame, String.valueOf(((CR.getInt(Constants.HISTORY_GOAL_7M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.HISTORY_GOAL_9M) + CR.getInt(Constants.HISTORY_MISS_9M) + CR.getInt(Constants.HISTORY_CATCH_BY_GOALKEEPER_9M);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ShotsPrediction9mForGame, String.valueOf(((CR.getInt(Constants.HISTORY_GOAL_9M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.HISTORY_FAUL_SHOT_GOAL) + CR.getInt(Constants.HISTORY_FAUL_SHOT_MISS);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.FaulShotsPredictionForGame, String.valueOf(((CR.getInt(Constants.HISTORY_FAUL_SHOT_GOAL) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.HISTORY_PLUS) + CR.getInt(Constants.HISTORY_MINUS) + CR.getInt(Constants.HISTORY_REACH_BALL);
                if(Count != 0)
                    DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.EfficientInDefensiveForGame, String.valueOf((((CR.getInt(Constants.HISTORY_PLUS) + CR.getInt(Constants.HISTORY_REACH_BALL)) *
                            100) / Count)), TurnamentPK);
                DB.updatePlayersInformations(DB, CR.getString(Constants.PLAYER_NUMBER), TableData.TableInfo.ValuesAreConvertedPlayer, "true", TurnamentPK);
            }
        }while(CR.moveToNext());
        CR.close();
    }

    public void GameGoalkeeper(String TurnamentPK) {
        Cursor CR = DB.getInformationGoalkeeper(DB, " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        Integer Count = 0;
        do{
            if(CR.getString(Constants.VALUES_ARE_COMVERTED_GOALKEEPER).equals("false")) {
                Count = CR.getInt(Constants.CATCH) + CR.getInt(Constants.RECOVERED_GOALS) + CR.getInt(Constants.CATCH_FAUL_SHOT) + CR.getInt(Constants.MISS_FAUL_SHOT);
                DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.CountOfInterventionsForGame, String.valueOf(Count), TurnamentPK);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfCatchForGame, String.valueOf((((CR.getInt(Constants.CATCH) + CR.getInt(Constants.CATCH_FAUL_SHOT)) *
                            100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.BREJK_CATCH) + CR.getInt(Constants.BREJK_RECOVERED);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfCatchBrejkForGame, String.valueOf(((CR.getInt(Constants.BREJK_CATCH) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.WING_CATCH) + CR.getInt(Constants.WING_RECOVERED);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfCatchWingForGame, String.valueOf(((CR.getInt(Constants.WING_CATCH) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.CATCH_6M) + CR.getInt(Constants.RECOVERED_6M);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfCatch6mForGame, String.valueOf(((CR.getInt(Constants.CATCH_6M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.CATCH_7M) + CR.getInt(Constants.RECOVERED_7M);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfCatch7mForGame, String.valueOf(((CR.getInt(Constants.CATCH_7M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.CATCH_9M) + CR.getInt(Constants.RECOVERED_9M);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfCatch9mForGame, String.valueOf(((CR.getInt(Constants.CATCH_9M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.PASS_LONG_GOOD) + CR.getInt(Constants.PASS_LONG_BAD);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfLongPassForGame, String.valueOf(((CR.getInt(Constants.PASS_LONG_GOOD) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.PASS_SHORT_GOOD) + CR.getInt(Constants.PASS_SHORT_BAD);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfShortPassForGame, String.valueOf(((CR.getInt(Constants.PASS_SHORT_GOOD) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.CATCH_FAUL_SHOT) + CR.getInt(Constants.MISS_FAUL_SHOT);
                if(Count != 0)
                    DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.PredictionOfCatchFaulshotForGame, String.valueOf(((CR.getInt(Constants.CATCH_FAUL_SHOT)*100)/Count)), TurnamentPK);
                DB.updateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER), TableData.TableInfo.ValuesAreConvertedGoalkeeper, "true", TurnamentPK);
            }
        }while(CR.moveToNext());
        CR.close();
    }

    public void HistoryGame(String DateTime) {

    }

    public void HistoryPlayer() {

    }

    public void HistoryGoalkeeper() {

    }

    public void SummaryTeam(String TurnamentPK) {

    }

    public void SummaryPlayer(String TurnamentPK) {
        Cursor CRP = DB.GetInformationPlayer(DB, " WHERE " + TableData.TableInfo.TurnamentPKRoster + " == " + TurnamentPK);
        if(!CRP.moveToFirst())
            return;
        Cursor CR;
        Integer Count = 0;
        do {
            if(CRP.getString(Constants.VALUES_CHANGE).equals("true")) {
                CR = DB.GetInformationPlayerHistory(DB, " WHERE " + TableData.TableInfo.HistoryPlayerNumber + " == " + CRP.getString(Constants.NUMBER));
                if(!CR.moveToFirst())
                    return;
                for(int i = Constants.HISTORY_GOAL_ON_TARGET; i <= Constants.HISTORY_CATCH_BY_GOALKEEPER_9M; i++) {
                    if(!CR.moveToFirst())
                        return;
                    do{
                        Count += CR.getInt(i);
                    }while(CR.moveToNext());
                    DB.UpdateInformationPlayer(DB, CRP.getString(Constants.NUMBER), PlayerInformations[i], String.valueOf(Count), TurnamentPK);
                    Count = 0;
                }
                CR.close();
            }
        }while(CRP.moveToNext());
        CRP.close();
        CR = DB.GetInformationPlayer(DB, " WHERE " + TableData.TableInfo.TurnamentPKRoster + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        Count = 0;
        do {
            if(CR.getString(Constants.VALUES_CHANGE).equals("true")) {
                Count = CR.getInt(Constants.COUNT_GOAL_ON_TARGET) + CR.getInt(Constants.COUNT_SHOT_MISS) + CR.getInt(Constants.COUNT_SHOT_GOALKEEPER);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.ShotsPrediction, String.valueOf(((CR.getInt(Constants.COUNT_GOAL_ON_TARGET) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_BREJK_GOAL) + CR.getInt(Constants.COUNT_BREJK_MISS) + CR.getInt(Constants.COUNT_CATCH_BY_GOALKEEPER_BREJK);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.ShotsPredictionBrejk, String.valueOf(((CR.getInt(Constants.COUNT_BREJK_GOAL) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_GOAL_WING) + CR.getInt(Constants.COUNT_MISS_WING) + CR.getInt(Constants.COUNT_CATCH_BY_GOALKEEPER_WING);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.ShotsPredictionWing, String.valueOf(((CR.getInt(Constants.COUNT_GOAL_WING) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_GOAL_6M) + CR.getInt(Constants.COUNT_MISS_6M) + CR.getInt(Constants.COUNT_CATCH_BY_GOALKEEPER_6M);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.ShotsPrediction6m, String.valueOf(((CR.getInt(Constants.COUNT_GOAL_6M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_GOAL_7M) + CR.getInt(Constants.COUNT_MISS_7M) + CR.getInt(Constants.COUNT_CATCH_BY_GOALKEEPER_7M);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.ShotsPrediction7m, String.valueOf(((CR.getInt(Constants.COUNT_GOAL_7M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_GOAL_9M) + CR.getInt(Constants.COUNT_MISS_9M) + CR.getInt(Constants.COUNT_CATCH_BY_GOALKEEPER_9M);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.ShotsPrediction9m, String.valueOf(((CR.getInt(Constants.COUNT_GOAL_9M) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_FAUL_SHOT_GOAL) + CR.getInt(Constants.COUNT_FAUL_SHOT_MISS);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.FaulShotsPrediction, String.valueOf(((CR.getInt(Constants.COUNT_FAUL_SHOT_GOAL) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_PLUS) + CR.getInt(Constants.COUNT_MINUS) + CR.getInt(Constants.COUNT_REACH_BALL);
                if(Count != 0)
                    DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.EfficientInDefensive, String.valueOf((((CR.getInt(Constants.COUNT_PLUS) + CR.getInt(Constants.COUNT_REACH_BALL)) *
                            100) / Count)), TurnamentPK);
                DB.UpdateInformationPlayer(DB, CR.getString(Constants.NUMBER), TableData.TableInfo.ValuesChange, "false", TurnamentPK);
            }
        }while(CR.moveToNext());
        CR.close();
    }

    public void SummaryGoalkeeper(String TurnamentPK) {
        Cursor CRP = DB.GetInformationGoalkeeper(DB, " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK);
        if(!CRP.moveToFirst())
            return;
        Cursor CR;
        Integer Count = 0;
        do {
            if(CRP.getString(Constants.GOALKEEPER_VALUES_CHANGE).equals("true")) {
                CR = DB.GetInformationGoalkeeperHistory(DB, " WHERE " + TableData.TableInfo.HistoryGoalkeeperNumber + " == " + CRP.getString(Constants.GOALKEEPER_NUMBER_ROSTER));
                if(!CR.moveToFirst())
                    return;
                for(int i = Constants.HISTORY_CATCH; i <= Constants.HISTORY_RED_CARD_GOALKEEPER; i++) {
                    if(!CR.moveToFirst())
                        return;
                    do{
                        Count += CR.getInt(i);
                    }while(CR.moveToNext());
                    DB.UpdateGoalkeeperInformations(DB, CRP.getString(Constants.GOALKEEPER_NUMBER_ROSTER), GoalkeepersInformations[i], String.valueOf(Count), TurnamentPK);
                    Count = 0;
                }
                CR.close();
            }
        }while(CRP.moveToNext());
        CRP.close();
        CR = DB.GetInformationGoalkeeper(DB, " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK);
        if(!CR.moveToFirst())
            return;
        do {
            if(CR.getString(Constants.GOALKEEPER_VALUES_CHANGE).equals("true")) {
                Count = CR.getInt(Constants.COUNT_CATCH) + CR.getInt(Constants.COUNT_RECOVERED_GOALS) + CR.getInt(Constants.COUNT_CATCH_FAUL_SHOT) + CR.getInt(Constants.COUNT_MISS_FAUL_SHOT);
                DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.CountOfInterventions, String.valueOf(Count), TurnamentPK);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfCatch, String.valueOf((((CR.getInt(Constants.COUNT_CATCH) +
                            CR.getInt(Constants.COUNT_CATCH_FAUL_SHOT)) * 100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_BREJK_CATCH) + CR.getInt(Constants.COUNT_BREJK_RECOVERED);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfCatchBrejk, String.valueOf(((CR.getInt(Constants.COUNT_BREJK_CATCH) * 100) /
                            Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_WING_CATCH) + CR.getInt(Constants.COUNT_WING_RECOVERED);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfCatchWing, String.valueOf(((CR.getInt(Constants.COUNT_WING_CATCH) * 100) /
                            Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_CATCH_6M) + CR.getInt(Constants.COUNT_RECOVERED_6M);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfCatch6m, String.valueOf(((CR.getInt(Constants.COUNT_CATCH_6M) * 100) /
                            Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_CATCH_7M) + CR.getInt(Constants.COUNT_RECOVERED_7M);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfCatch7m, String.valueOf(((CR.getInt(Constants.COUNT_CATCH_7M) * 100) /
                            Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_CATCH_9M) + CR.getInt(Constants.COUNT_RECOVERED_9M);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfCatch9m, String.valueOf(((CR.getInt(Constants.COUNT_CATCH_9M) * 100) /
                            Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_PASS_LONG_GOOD) + CR.getInt(Constants.COUNT_PASS_LONG_BAD);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfLongPass, String.valueOf(((CR.getInt(Constants.COUNT_PASS_LONG_GOOD) * 100) /
                            Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_PASS_SHORT_GOOD) + CR.getInt(Constants.COUNT_PASS_SHORT_BAD);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfShortPass, String.valueOf(((CR.getInt(Constants.COUNT_PASS_SHORT_GOOD) *
                            100) / Count)), TurnamentPK);
                Count = CR.getInt(Constants.COUNT_CATCH_FAUL_SHOT) + CR.getInt(Constants.COUNT_MISS_FAUL_SHOT);
                if(Count != 0)
                    DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.PredictionOfCatchFaulshot, String.valueOf(((CR.getInt(Constants.COUNT_CATCH_FAUL_SHOT) *
                            100) / Count)), TurnamentPK);
                DB.UpdateGoalkeeperInformations(DB, CR.getString(Constants.GOALKEEPER_NUMBER_ROSTER), TableData.TableInfo.GoalkeeperValuesChange, "false", TurnamentPK);
            }
        }while(CR.moveToNext());
        CR.close();
    }

    public void SummaryGame(String TurnamentPK) {
        Cursor CR = DB.GetInformationSummaryGame(DB, " WHERE " + TableData.TableInfo.SummaryGameTurnamentPK + " == " + TurnamentPK);
        if(!CR.moveToLast())
            return;
        if(CR.getString(Constants.SUMMARY_VALUES_CHANGE).equals("false"))
            return;
        Cursor CRG = DB.getInformationGame(DB, " WHERE " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK);
        if(!CRG.moveToFirst())
            return;
        int j = Constants.GAME_COUNT_OF_SHOTS;
        for(int i = Constants.SUMMARY_GAME_COUNT_OF_SHOTS; i <= Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_9M; i++) {
            DB.UpdateInformationsSummaryGame(DB, GameSummary[i], "0", TurnamentPK);
            CRG.moveToFirst();
            do {
                DB.UpdateInformationsSummaryGame(DB, GameSummary[i], String.valueOf(CR.getInt(i) + CRG.getInt(j)), TurnamentPK);
            }while(CRG.moveToNext());
            j++;
        }
        System.out.print(TurnamentPK+ " 000ggvu\n");
        System.out.print(CR.getString(Constants.SUMMARY_VALUES_CHANGE)+ " 000ggvu\n");
        CR = DB.GetInformationSummaryGame(DB, " WHERE " + TableData.TableInfo.SummaryGameTurnamentPK + " == " + TurnamentPK);
        if(!CR.moveToLast())
            return;
        Integer Count = 0;
        Count = CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_SHOTS) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_SHOTS) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_SHOTS);
        if(Count != 0)
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryGamePredictionOfShots, String.valueOf((CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_SHOTS) * 100) / Count), TurnamentPK);
        Count = CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_BREJK) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_BREJK) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_BREJK);
        if(Count != 0)
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryGamePredictionOfShotsBrejk, String.valueOf((CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_BREJK) * 100) / Count), TurnamentPK);
        Count = CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_WING) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_WING) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_WING);
        if(Count != 0)
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryGamePredictionOfShotsWing, String.valueOf((CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_WING) * 100) / Count), TurnamentPK);
        Count = CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_6M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_6M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_6M);
        if(Count != 0)
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryGamePredictionOfShots6m, String.valueOf((CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_6M) * 100) / Count), TurnamentPK);
        Count = CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_7M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_7M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_7M);
        if(Count != 0)
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryGamePredictionOfShots7m, String.valueOf((CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_7M) * 100) / Count), TurnamentPK);
        Count = CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_9M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_MISS_9M) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOALKEEPER_9M);
        if(Count != 0)
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryGamePredictionOfShots9m, String.valueOf((CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_GOAL_9M) * 100) / Count), TurnamentPK);
        Count = CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_FAUL_SHOTS_GOAL) + CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_FAUL_SHOTS_MISS);
        if(Count != 0)
            DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryGamePredictionOfFaulsShots, String.valueOf((CR.getInt(Constants.SUMMARY_GAME_COUNT_OF_FAUL_SHOTS_GOAL) * 100) / Count), TurnamentPK);
        DB.UpdateInformationsSummaryGame(DB, TableData.TableInfo.SummaryValuesChange, "false", TurnamentPK);
        CRG.close();
        CR.close();
    }
}

