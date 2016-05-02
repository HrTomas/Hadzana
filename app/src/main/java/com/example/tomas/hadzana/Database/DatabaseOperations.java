package com.example.tomas.hadzana.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 4;


    String TableOfPlayersRoster = "CREATE TABLE " + TableData.TableInfo.TableRoster + "(" +
            TableData.TableInfo.FirstName + " TEXT, " +
            TableData.TableInfo.SecondName + " TEXT, " +
            TableData.TableInfo.Number + " INTEGER, " +
            TableData.TableInfo.CountGoalOnTarget + " INTEGER, " +
            TableData.TableInfo.CountShotMiss + " INTEGER, " +
            TableData.TableInfo.CountShotGoalkeeper + " INTEGER, " +
            TableData.TableInfo.CountMissBall + " INTEGER, " +
            TableData.TableInfo.CountReachFaulShot + " INTEGER, " +
            TableData.TableInfo.CountPlus + " INTEGER, " +
            TableData.TableInfo.CountMinus + " INTEGER, " +
            TableData.TableInfo.CountFaul + " INTEGER, " +
            TableData.TableInfo.CountYellowCard + " INTEGER, " +
            TableData.TableInfo.CountRedCard + " INTEGER, " +
            TableData.TableInfo.CountReachBall + " INTEGER, " +
            TableData.TableInfo.CountFaulShotGoal + " INTEGER, " +
            TableData.TableInfo.CountFaulShotMiss + " INTEGER, " +
            TableData.TableInfo.CountBrejkGoal + " INTEGER, " +
            TableData.TableInfo.CountBrejkMiss + " INTEGER, " +
            TableData.TableInfo.CountGoalWing + " INTEGER, " +
            TableData.TableInfo.CountMissWing + " INTEGER, " +
            TableData.TableInfo.CountGoal6m + " INTEGER, " +
            TableData.TableInfo.CountGoal7m + " INTEGER, " +
            TableData.TableInfo.CountGoal9m + " INTEGER, " +
            TableData.TableInfo.CountMiss6m + " INTEGER, " +
            TableData.TableInfo.CountMiss7m + " INTEGER, " +
            TableData.TableInfo.CountMiss9m + " INTEGER, " +
            TableData.TableInfo.CountCatchByGoalkeeperBrejk + " INTEGER, " +
            TableData.TableInfo.CountCatchByGoalkeeperWing + " INTEGER, " +
            TableData.TableInfo.CountCatchByGoalkeeper6m + " INTEGER, " +
            TableData.TableInfo.CountCatchByGoalkeeper7m + " INTEGER, " +
            TableData.TableInfo.CountCatchByGoalkeeper9m + " INTEGER, " +
            TableData.TableInfo.CountPlayersTimeOnPlayground + " TEXT, " +
            TableData.TableInfo.ShotsPrediction + " INTEGER, " +
            TableData.TableInfo.ShotsPredictionBrejk + " INTEGER, " +
            TableData.TableInfo.ShotsPredictionWing + " INTEGER, " +
            TableData.TableInfo.ShotsPrediction6m + " INTEGER, " +
            TableData.TableInfo.ShotsPrediction7m + " INTEGER, " +
            TableData.TableInfo.ShotsPrediction9m + " INTEGER, " +
            TableData.TableInfo.FaulShotsPrediction + " INTEGER, " +
            TableData.TableInfo.EfficientInDefensive + " INTEGER, " +
            TableData.TableInfo.CountOfPlayedGames + " INTEGER, " +
            TableData.TableInfo.Exploitation + " INTEGER, " +
            TableData.TableInfo.PrimaryPosition + " TEXT, " +
            TableData.TableInfo.SecondaryPosition + " TEXT, " +
            TableData.TableInfo.BirthDate + " TEXT, " +
            TableData.TableInfo.SocialSecurityNumber + " TEXT, " +
            TableData.TableInfo.Gender + " TEXT, " +
            TableData.TableInfo.Note + " TEXT, " +
            TableData.TableInfo.ValuesChange + " TEXT, " +
            TableData.TableInfo.PrimaryKeyForPlayer + " INTEGER, " +
            TableData.TableInfo.TurnamentPKRoster + " TEXT);";

    String TableOfPlayersGame = "CREATE TABLE " + TableData.TableInfo.TableNamePlayer + "(" +
            TableData.TableInfo.PlayerFirstName + " TEXT, " +
            TableData.TableInfo.PlayerSecondName + " TEXT, " +
            TableData.TableInfo.PlayerNumber + " INTEGER, " +
            TableData.TableInfo.GoalOnTarget + " INTEGER, " +
            TableData.TableInfo.ShotMiss + " INTEGER, " +
            TableData.TableInfo.ShotGoalkeeper + " INTEGER, " +
            TableData.TableInfo.MissBall + " INTEGER, " +
            TableData.TableInfo.ReachFaulShot + " INTEGER, " +
            TableData.TableInfo.Plus + " INTEGER, " +
            TableData.TableInfo.Minus + " INTEGER, " +
            TableData.TableInfo.Faul + " INTEGER, " +
            TableData.TableInfo.YellowCard + " INTEGER, " +
            TableData.TableInfo.RedCard + " INTEGER, " +
            TableData.TableInfo.ReachBall + " INTEGER, " +
            TableData.TableInfo.FaulShotGoal + " INTEGER, " +
            TableData.TableInfo.FaulShotMiss + " INTEGER, " +
            TableData.TableInfo.BrejkGoal + " INTEGER, " +
            TableData.TableInfo.BrejkMiss + " INTEGER, " +
            TableData.TableInfo.GoalWing + " INTEGER, " +
            TableData.TableInfo.MissWing + " INTEGER, " +
            TableData.TableInfo.Goal6m + " INTEGER, " +
            TableData.TableInfo.Goal7m + " INTEGER, " +
            TableData.TableInfo.Goal9m + " INTEGER, " +
            TableData.TableInfo.Miss6m + " INTEGER, " +
            TableData.TableInfo.Miss7m + " INTEGER, " +
            TableData.TableInfo.Miss9m + " INTEGER, " +
            TableData.TableInfo.CatchByGoalkeeperBrejk + " INTEGER, " +
            TableData.TableInfo.CatchByGoalkeeperWing + " INTEGER, " +
            TableData.TableInfo.CatchByGoalkeeper6m + " INTEGER, " +
            TableData.TableInfo.CatchByGoalkeeper7m + " INTEGER, " +
            TableData.TableInfo.CatchByGoalkeeper9m + " INTEGER, " +
            TableData.TableInfo.PlayersTimeOnPlayground + " TEXT, " +
            TableData.TableInfo.ShotsPredictionForGame + " INTEGER, " +
            TableData.TableInfo.ShotsPredictionBrejkForGame + " INTEGER, " +
            TableData.TableInfo.ShotsPredictionWingForGame + " INTEGER, " +
            TableData.TableInfo.ShotsPrediction6mForGame + " INTEGER, " +
            TableData.TableInfo.ShotsPrediction7mForGame + " INTEGER, " +
            TableData.TableInfo.ShotsPrediction9mForGame + " INTEGER, " +
            TableData.TableInfo.FaulShotsPredictionForGame + " INTEGER, " +
            TableData.TableInfo.EfficientInDefensiveForGame + " INTEGER, " +
            TableData.TableInfo.ValuesAreConvertedPlayer + " TEXT, " +
            TableData.TableInfo.TurnamentPlayerPK + " TEXT);";

    String TableOfPlayersHistory = "CREATE TABLE " + TableData.TableInfo.HistoryPlayer + "(" +
            TableData.TableInfo.HistoryPlayerFirstName + " TEXT, " +
            TableData.TableInfo.HistoryPlayerSecondName + " TEXT, " +
            TableData.TableInfo.HistoryPlayerNumber + " INTEGER, " +
            TableData.TableInfo.HistoryGoalOnTarget + " INTEGER, " +
            TableData.TableInfo.HistoryShotMiss + " INTEGER, " +
            TableData.TableInfo.HistoryShotGoalkeeper + " INTEGER, " +
            TableData.TableInfo.HistoryMissBall + " INTEGER, " +
            TableData.TableInfo.HistoryReachFaulShot + " INTEGER, " +
            TableData.TableInfo.HistoryPlus + " INTEGER, " +
            TableData.TableInfo.HistoryMinus + " INTEGER, " +
            TableData.TableInfo.HistoryFaul + " INTEGER, " +
            TableData.TableInfo.HistoryYellowCard + " INTEGER, " +
            TableData.TableInfo.HistoryRedCard + " INTEGER, " +
            TableData.TableInfo.HistoryReachBall + " INTEGER, " +
            TableData.TableInfo.HistoryFaulShotGoal + " INTEGER, " +
            TableData.TableInfo.HistoryFaulShotMiss + " INTEGER, " +
            TableData.TableInfo.HistoryBrejkGoal + " INTEGER, " +
            TableData.TableInfo.HistoryBrejkMiss + " INTEGER, " +
            TableData.TableInfo.HistoryGoalWing + " INTEGER, " +
            TableData.TableInfo.HistoryMissWing + " INTEGER, " +
            TableData.TableInfo.HistoryGoal6m + " INTEGER, " +
            TableData.TableInfo.HistoryGoal7m + " INTEGER, " +
            TableData.TableInfo.HistoryGoal9m + " INTEGER, " +
            TableData.TableInfo.HistoryMiss6m + " INTEGER, " +
            TableData.TableInfo.HistoryMiss7m + " INTEGER, " +
            TableData.TableInfo.HistoryMiss9m + " INTEGER, " +
            TableData.TableInfo.HistoryCatchByGoalkeeperBrejk + " INTEGER, " +
            TableData.TableInfo.HistoryCatchByGoalkeeperWing + " INTEGER, " +
            TableData.TableInfo.HistoryCatchByGoalkeeper6m + " INTEGER, " +
            TableData.TableInfo.HistoryCatchByGoalkeeper7m + " INTEGER, " +
            TableData.TableInfo.HistoryCatchByGoalkeeper9m + " INTEGER, " +
            TableData.TableInfo.HistoryPlayersTimeOnPlayground + " TEXT, " +
            TableData.TableInfo.HistoryShotsPrediction + " INTEGER, " +
            TableData.TableInfo.HistoryShotsPredictionBrejk + " INTEGER, " +
            TableData.TableInfo.HistoryShotsPredictionWing + " INTEGER, " +
            TableData.TableInfo.HistoryShotsPrediction6m + " INTEGER, " +
            TableData.TableInfo.HistoryShotsPrediction7m + " INTEGER, " +
            TableData.TableInfo.HistoryShotsPrediction9m + " INTEGER, " +
            TableData.TableInfo.HistoryFaulShotsPrediction + " INTEGER, " +
            TableData.TableInfo.HistoryEfficientInDefensive + " INTEGER, " +
            TableData.TableInfo.HistoryDatetimaPlayer + " TEXT, " +
            TableData.TableInfo.TurnamentHistoryPKPlayer + " TEXT);";

    String TableOfGoalkeepersRoster = "CREATE TABLE " + TableData.TableInfo.TableGoalkeeperRoster + "(" +
            TableData.TableInfo.GoalkeeperFirstNameRoster + " TEXT, " +
            TableData.TableInfo.GoalkeeperSecondNameRoster + " TEXT, " +
            TableData.TableInfo.GoalkeeperNumberRoster + " INTEGER, " +
            TableData.TableInfo.CountCatch + " INTEGER, " +
            TableData.TableInfo.CountPassLongGood + " INTEGER, " +
            TableData.TableInfo.CountPassLongBad + " INTEGER, " +
            TableData.TableInfo.CountPassShortGood + " INTEGER, " +
            TableData.TableInfo.CountPassShortBad + " INTEGER, " +
            TableData.TableInfo.CountCatchFaulShot + " INTEGER, " +
            TableData.TableInfo.CountMissFaulShot + " INTEGER, " +
            TableData.TableInfo.CountRecoveredGoals + " INTEGER, " +
            TableData.TableInfo.CountBrejkCatch + " INTEGER, " +
            TableData.TableInfo.CountBrejkRecovered + " INTEGER, " +
            TableData.TableInfo.CountWingCatch + " INTEGER, " +
            TableData.TableInfo.CountWingRecovered + " INTEGER, " +
            TableData.TableInfo.CountCatch6m + " INTEGER, " +
            TableData.TableInfo.CountCatch7m + " INTEGER, " +
            TableData.TableInfo.CountCatch9m + " INTEGER, " +
            TableData.TableInfo.CountRecovered6m + " INTEGER, " +
            TableData.TableInfo.CountRecovered7m + " INTEGER, " +
            TableData.TableInfo.CountRecovered9m + " INTEGER, " +
            TableData.TableInfo.CountFaulGoalkeeper + " INTEGER, " +
            TableData.TableInfo.CountYellowCardGoalkeeper + " INTEGER, " +
            TableData.TableInfo.CountRedCardGoalkeeper + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatch + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatchBrejk + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatchWing + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatch6m + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatch7m + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatch9m + " INTEGER, " +
            TableData.TableInfo.PredictionOfLongPass + " INTEGER, " +
            TableData.TableInfo.PredictionOfShortPass + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatchFaulshot + " INTEGER, " +
            TableData.TableInfo.CountOfPlayedGamesGoalkeeper + " INTEGER, " +
            TableData.TableInfo.CountOfInterventions + " INTEGER, " +
            TableData.TableInfo.CountOfTimeOnPlayGroundGoalkeeper + " TEXT, " +
            TableData.TableInfo.GoalkeeperBirthDate + " TEXT, " +
            TableData.TableInfo.GoalkeeperSocialSecurityNumber + " TEXT, " +
            TableData.TableInfo.GoalkeeperGender + " TEXT, " +
            TableData.TableInfo.GoalkeeperNote + " TEXT, " +
            TableData.TableInfo.GoalkeeperValuesChange + " TEXT, " +
            TableData.TableInfo.GoalkeeperPrimaryKeyRoster + " INTEGER, " +
            TableData.TableInfo.TurnamentGoalkeeperPKRoster + " TEXT);";

    String TableOfGoalkeepersGame = "CREATE TABLE " + TableData.TableInfo.TableGoalkeeper + "(" +
            TableData.TableInfo.GoalkeeperFirstName + " TEXT, " +
            TableData.TableInfo.GoalkeeperSecondName + " TEXT, " +
            TableData.TableInfo.GoalkeeperNumber + " INTEGER, " +
            TableData.TableInfo.Catch + " INTEGER, " +
            TableData.TableInfo.PassLongGood + " INTEGER, " +
            TableData.TableInfo.PassLongBad + " INTEGER, " +
            TableData.TableInfo.PassShortGood + " INTEGER, " +
            TableData.TableInfo.PassShortBad + " INTEGER, " +
            TableData.TableInfo.CatchFaulShot + " INTEGER, " +
            TableData.TableInfo.MissFaulShot + " INTEGER, " +
            TableData.TableInfo.RecoveredGoals + " INTEGER, " +
            TableData.TableInfo.BrejkCatch + " INTEGER, " +
            TableData.TableInfo.BrejkRecovered + " INTEGER, " +
            TableData.TableInfo.WingCatch + " INTEGER, " +
            TableData.TableInfo.WingRecovered + " INTEGER, " +
            TableData.TableInfo.Catch6m + " INTEGER, " +
            TableData.TableInfo.Catch7m + " INTEGER, " +
            TableData.TableInfo.Catch9m + " INTEGER, " +
            TableData.TableInfo.Recovered6m + " INTEGER, " +
            TableData.TableInfo.Recovered7m + " INTEGER, " +
            TableData.TableInfo.Recovered9m + " INTEGER, " +
            TableData.TableInfo.FaulGoalkeeper + " INTEGER, " +
            TableData.TableInfo.YellowCardGoalkeeper + " INTEGER, " +
            TableData.TableInfo.RedCardGoalkeeper + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatchForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatchBrejkForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatchWingForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatch6mForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatch7mForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatch9mForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfLongPassForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfShortPassForGame + " INTEGER, " +
            TableData.TableInfo.PredictionOfCatchFaulshotForGame + " INTEGER, " +
            TableData.TableInfo.CountOfInterventionsForGame + " INTEGER, " +
            TableData.TableInfo.ValuesAreConvertedGoalkeeper + " TEXT, " +
            TableData.TableInfo.TimeOnPlaygroundGoalkeeper + " TEXT, " +
            TableData.TableInfo.TurnamentGoalkeeperPK + " TEXT);";

    String TableOfGoalkeepersHistory = "CREATE TABLE " + TableData.TableInfo.HistoryGoalkeeper + "(" +
            TableData.TableInfo.HistoryGoalkeeperFirstName + " TEXT, " +
            TableData.TableInfo.HistoryGoalkeeperSecondName + " TEXT, " +
            TableData.TableInfo.HistoryGoalkeeperNumber + " INTEGER, " +
            TableData.TableInfo.HistoryCatch + " INTEGER, " +
            TableData.TableInfo.HistoryPassLongGood + " INTEGER, " +
            TableData.TableInfo.HistoryPassLongBad + " INTEGER, " +
            TableData.TableInfo.HistoryPassShortGood + " INTEGER, " +
            TableData.TableInfo.HistoryPassShortBad + " INTEGER, " +
            TableData.TableInfo.HistoryCatchFaulShot + " INTEGER, " +
            TableData.TableInfo.HistoryMissFaulShot + " INTEGER, " +
            TableData.TableInfo.HistoryRecoveredGoals + " INTEGER, " +
            TableData.TableInfo.HistoryBrejkCatch + " INTEGER, " +
            TableData.TableInfo.HistoryBrejkRecovered + " INTEGER, " +
            TableData.TableInfo.HistoryWingCatch + " INTEGER, " +
            TableData.TableInfo.HistoryWingRecovered + " INTEGER, " +
            TableData.TableInfo.HistoryCatch6m + " INTEGER, " +
            TableData.TableInfo.HistoryCatch7m + " INTEGER, " +
            TableData.TableInfo.HistoryCatch9m + " INTEGER, " +
            TableData.TableInfo.HistoryRecovered6m + " INTEGER, " +
            TableData.TableInfo.HistoryRecovered7m + " INTEGER, " +
            TableData.TableInfo.HistoryRecovered9m + " INTEGER, " +
            TableData.TableInfo.HistoryFaulGoalkeeper + " INTEGER, " +
            TableData.TableInfo.HistoryYellowCardGoalkeeper + " INTEGER, " +
            TableData.TableInfo.HistoryRedCardGoalkeeper + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfCatch + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfCatchBrejk + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfCatchWing + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfCatch6m + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfCatch7m + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfCatch9m + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfLongPass + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfShortPass + " INTEGER, " +
            TableData.TableInfo.HistoryPredictionOfCatchFaulshot + " INTEGER, " +
            TableData.TableInfo.HistoryCountOfInterventions + " INTEGER, " +
            TableData.TableInfo.HistoryTimeOnPlaygroundGoalkeeper + " TEXT, " +
            TableData.TableInfo.HistoryDatetimeGoalkeeper + " TEXT, " +
            TableData.TableInfo.TurnamentHistoryPKGoalkeeper + " TEXT);";

    public String TABLE_GAME = "CREATE TABLE " + TableData.TableInfo.TableNameGame + "(" +
            TableData.TableInfo.TeamName + " TEXT, " +
            TableData.TableInfo.NameOfOponent + " TEXT, " +
            TableData.TableInfo.HalftimeScore + " TEXT, " +
            TableData.TableInfo.FinalScore + " TEXT, " +
            TableData.TableInfo.MatchTime + " TEXT, " +
            TableData.TableInfo.TimeInOfensive + " TEXT, " +
            TableData.TableInfo.CountOfOfensive + " TEXT, " +
            TableData.TableInfo.TimeInDefensive + " TEXT," +
            TableData.TableInfo.CountOfDefensive + " TEXT, " +
            TableData.TableInfo.GameReplay + " TEXT, " +
            TableData.TableInfo.GameReport + " TEXT, " +
            TableData.TableInfo.PowerPlaysCount + " TEXT, " +
            TableData.TableInfo.PowerPlaysWin + " TEXT, " +
            TableData.TableInfo.ReachedGoalWhilePowerPlay + " TEXT, " +
            TableData.TableInfo.CountOfWeakening + " TEXT, " +
            TableData.TableInfo.GoalWhileWeakening + " TEXT, " +
            TableData.TableInfo.ReachedGoalWhileWeakening + " TEXT, " +
            TableData.TableInfo.GameCountOfShots + " TEXT, " +
            TableData.TableInfo.GameCountOfMissShots + " TEXT, " +
            TableData.TableInfo.GameCountOfgoalkeeperShots + " TEXT, " +
            TableData.TableInfo.GameCountOfMissBalls + " TEXT, " +
            TableData.TableInfo.GameCountOfFaulShots + " TEXT, " +
            TableData.TableInfo.GameCountOfPlus + " TEXT, " +
            TableData.TableInfo.GameCountOfMinus + " TEXT, " +
            TableData.TableInfo.GameCountOfFauls + " TEXT, " +
            TableData.TableInfo.GameCountOfYellowCards + " TEXT, " +
            TableData.TableInfo.GameCountOfRedCards + " TEXT, " +
            TableData.TableInfo.GameCountOfReachBalls + " TEXT, " +
            TableData.TableInfo.GameCountOfFaulShotsGoal + " TEXT, " +
            TableData.TableInfo.GameCountOfFaulShotsMiss + " TEXT, " +
            TableData.TableInfo.GameCountOfGoalBrejk + " TEXT, " +
            TableData.TableInfo.GameCountOfMissBrejk + " TEXT, " +
            TableData.TableInfo.GameCountOfGoalWing + " TEXT, " +
            TableData.TableInfo.GameCountOfMissWing + " TEXT, " +
            TableData.TableInfo.GameCountOfGoal6m + " TEXT, " +
            TableData.TableInfo.GameCountOfGoal7m + " TEXT, " +
            TableData.TableInfo.GameCountOfGoal9m + " TEXT, " +
            TableData.TableInfo.GameCountOfMiss6m + " TEXT, " +
            TableData.TableInfo.GameCountOfMiss7m + " TEXT, " +
            TableData.TableInfo.GameCountOfMiss9m + " TEXT, " +
            TableData.TableInfo.GameCountOfGoalkeeperBrejk + " TEXT, " +
            TableData.TableInfo.GameCountOfGoalkeeperWing + " TEXT, " +
            TableData.TableInfo.GameCountOfGoalkeeper6m + " TEXT, " +
            TableData.TableInfo.GameCountOfGoalkeeper7m + " TEXT, " +
            TableData.TableInfo.GameCountOfGoalkeeper9m + " TEXT, " +
            TableData.TableInfo.GamePredictionOfShots + " TEXT, " +
            TableData.TableInfo.GamePredictionOfShotsBrejk + " TEXT, " +
            TableData.TableInfo.GamePredictionOfShotsWing + " TEXT, " +
            TableData.TableInfo.GamePredictionOfShots6m + " TEXT, " +
            TableData.TableInfo.GamePredictionOfShots7m + " TEXT, " +
            TableData.TableInfo.GamePredictionOfShots9m + " TEXT, " +
            TableData.TableInfo.GamePredictionOfFaulsShots + " TEXT, " +
            TableData.TableInfo.MatchSuccessfullyCompleted + " TEXT, " +
            TableData.TableInfo.GamePlayedHome + " TEXT, " +
            TableData.TableInfo.DateTimePKGame + " TEXT, " +
            TableData.TableInfo.PKNumberGame + " INTEGER, " +
            TableData.TableInfo.TurnamentGamePK + " TEXT);";

    public String SummaryGame = "CREATE TABLE " +
            TableData.TableInfo.TableNameSummaryGame + "(" +
            TableData.TableInfo.SummaryCountWinGames + " INTEGER, " +
            TableData.TableInfo.SummaryCountWinGamesHome + " INTEGER, " +
            TableData.TableInfo.SummaryCountWinGamesOut + " INTEGER, " +
            TableData.TableInfo.SummaryCountLoseGames + " INTEGER, " +
            TableData.TableInfo.SummaryCountLoseGamesHome + " INTEGER, " +
            TableData.TableInfo.SummaryCountLoseGamesOut + " INTEGER, " +
            TableData.TableInfo.SummaryCountOfOfensive + " INTEGER, " +
            TableData.TableInfo.SummaryCountOfDefensive + " INTEGER, " +
            TableData.TableInfo.SummaryPowerPlaysCount + " INTEGER, " +
            TableData.TableInfo.SummaryPowerPlaysWin + " INTEGER, " +
            TableData.TableInfo.SummaryReachedGoalWhilePowerPlay + " INTEGER, " +
            TableData.TableInfo.SummaryCountOfWeakening + " INTEGER, " +
            TableData.TableInfo.SummaryGoalWhileWeakening + " INTEGER, " +
            TableData.TableInfo.SummaryReachedGoalWhileWeakening + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfShots + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMissShots + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfgoalkeeperShots + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMissBalls  + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfFaulShots + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfPlus + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMinus + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfFauls + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfYellowCards + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfRedCards + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfReachBalls + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfFaulShotsGoal + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfFaulShotsMiss + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoalBrejk + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMissBrejk + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoalWing + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMissWing + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoal6m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoal7m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoal9m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMiss6m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMiss7m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfMiss9m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoalkeeperBrejk + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoalkeeperWing + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoalkeeper6m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoalkeeper7m + " INTEGER, " +
            TableData.TableInfo.SummaryGameCountOfGoalkeeper9m + " INTEGER, " +
            TableData.TableInfo.SummaryGamePredictionOfShots + " INTEGER, " +
            TableData.TableInfo.SummaryGamePredictionOfShotsBrejk + " INTEGER, " +
            TableData.TableInfo.SummaryGamePredictionOfShotsWing + " INTEGER, " +
            TableData.TableInfo.SummaryGamePredictionOfShots6m + " INTEGER, " +
            TableData.TableInfo.SummaryGamePredictionOfShots7m + " INTEGER, " +
            TableData.TableInfo.SummaryGamePredictionOfShots9m + " INTEGER, " +
            TableData.TableInfo.SummaryGamePredictionOfFaulsShots + " INTEGER, " +
            TableData.TableInfo.SummaryCountGamePlayedHome + " INTEGER, " +
            TableData.TableInfo.SummaryCountGamePlayedOut + " INTEGER, " +
            TableData.TableInfo.SummaryMatchTime + " TEXT, " +
            TableData.TableInfo.SummaryTimeInOfensive + " TEXT, " +
            TableData.TableInfo.SummaryTimeInDefensive  + " TEXT, " +
            TableData.TableInfo.SummaryValuesChange + " TEXT, " +
            TableData.TableInfo.SummaryGameTurnamentPK  + " TEXT);";


    public String TABLE_GAME_OPTIONS = "CREATE TABLE " + TableData.TableInfo.TABLE_GAME_OPTIONS + "(" + TableData.TableInfo.COUNT_OF_MONITORING_PLAYERS + " TEXT, " + TableData.TableInfo.HowOftenRecordGame + " TEXT, " + TableData.TableInfo.NAME_OF_MONITORING_TEAM + " TEXT, " +
            TableData.TableInfo.GAME_TIME + " TEXT, " + TableData.TableInfo.COUNT_OF_PERIODS + " TEXT, " + TableData.TableInfo.PLAYER_ONE + " TEXT, " + TableData.TableInfo.PLAYER_TWO + " TEXT, " + TableData.TableInfo.PLAYER_THREE + " TEXT, " +
            TableData.TableInfo.PLAYER_FOUR + " TEXT, " + TableData.TableInfo.PLAYER_FIVE + " TEXT, " + TableData.TableInfo.PLAYER_SIX + " TEXT, " + TableData.TableInfo.GOALKEEPER + " TEXT, " + TableData.TableInfo.OPTION_GOAL_ON_TARGET + " TEXT, " +
            TableData.TableInfo.OPTION_SHOT_MISS + " TEXT, " + TableData.TableInfo.OPTION_SHOT_GOALKEEPER + " TEXT, " + TableData.TableInfo.OPTION_MISS_BALL + " TEXT, " + TableData.TableInfo.OPTION_REACH_FAUL_SHOT + " TEXT, " +
            TableData.TableInfo.OPTION_PLUS + " TEXT, " + TableData.TableInfo.OPTION_MINUS + " TEXT, " + TableData.TableInfo.OPTION_FAUL + " TEXT, " + TableData.TableInfo.OPTION_YELLOW_CARD + " TEXT, " +
            TableData.TableInfo.OPTION_RED_CARD + " TEXT, " + TableData.TableInfo.OPTION_REACH_BALL + " TEXT, " + TableData.TableInfo.OPTION_PLAYERS_TIME_ON_PLAYGROUND + " TEXT, " + TableData.TableInfo.ORDER_PK + " TEXT, " +
            TableData.TableInfo.TurnamentGameOptionsPK + " TEXT);";

    public String Table_LineUp = "CREATE TABLE " + TableData.TableInfo.Table_Name_LineUp + "(" + TableData.TableInfo.LineUp + " TEXT, " + TableData.TableInfo.LineUpPK + " TEXT, " + TableData.TableInfo.TurnamentLineUpsPK + " TEXT);";

    public String Table_Opponents_Values = "CREATE TABLE " + TableData.TableInfo.Table_Opponents_Values + "(" + TableData.TableInfo.MissShotsOpponent + " TEXT, " +
            TableData.TableInfo.FaulOpponent + " TEXT, " + TableData.TableInfo.Date + " TEXT, " + TableData.TableInfo.Opponent_PK + " TEXT, " + TableData.TableInfo.TurnamentOpponentsPK + " TEXT);";

    public String Table_Turnaments = "CREATE TABLE " + TableData.TableInfo.TableOfTurnaments + "(" + TableData.TableInfo.TurnamentName + " TEXT, " + TableData.TableInfo.TurnamentPK + " TurnamentPK);";

    public DatabaseOperations(Context context) {

        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(TableOfPlayersRoster);
        sdb.execSQL(TableOfPlayersHistory);
        sdb.execSQL(TableOfGoalkeepersRoster);
        sdb.execSQL(TableOfGoalkeepersHistory);
        sdb.execSQL(TABLE_GAME);
        sdb.execSQL(TABLE_GAME_OPTIONS);
        sdb.execSQL(Table_LineUp);
        sdb.execSQL(Table_Opponents_Values);
        sdb.execSQL(Table_Turnaments);
        sdb.execSQL(SummaryGame);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public void PutInformationPlayer(DatabaseOperations DB, String FirstName, String SecondName, Integer Number, String PrimaryPosition, String SecondaryPosition, String BirthDate, String SocialSecurityNumber,
                                     String Gender, String Note, String ValuesChange, Integer PrimaryKeyForPlayer, String TurnamentPKRoster) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.FirstName, FirstName);
        CV.put(TableData.TableInfo.SecondName, SecondName);
        CV.put(TableData.TableInfo.Number, Number);
        CV.put(TableData.TableInfo.CountGoalOnTarget, 0);
        CV.put(TableData.TableInfo.CountShotMiss, 0);
        CV.put(TableData.TableInfo.CountShotGoalkeeper, 0);
        CV.put(TableData.TableInfo.CountMissBall, 0);
        CV.put(TableData.TableInfo.CountReachFaulShot, 0);
        CV.put(TableData.TableInfo.CountPlus, 0);
        CV.put(TableData.TableInfo.CountMinus, 0);
        CV.put(TableData.TableInfo.CountFaul, 0);
        CV.put(TableData.TableInfo.CountYellowCard, 0);
        CV.put(TableData.TableInfo.CountRedCard, 0);
        CV.put(TableData.TableInfo.CountReachBall, 0);
        CV.put(TableData.TableInfo.CountPlayersTimeOnPlayground, "00:00");
        CV.put(TableData.TableInfo.CountFaulShotGoal, 0);
        CV.put(TableData.TableInfo.CountFaulShotMiss, 0);
        CV.put(TableData.TableInfo.CountBrejkGoal, 0);
        CV.put(TableData.TableInfo.CountBrejkMiss, 0);
        CV.put(TableData.TableInfo.CountGoalWing, 0);
        CV.put(TableData.TableInfo.CountMissWing, 0);
        CV.put(TableData.TableInfo.CountGoal6m, 0);
        CV.put(TableData.TableInfo.CountGoal7m, 0);
        CV.put(TableData.TableInfo.CountGoal9m, 0);
        CV.put(TableData.TableInfo.CountMiss6m, 0);
        CV.put(TableData.TableInfo.CountMiss7m, 0);
        CV.put(TableData.TableInfo.CountMiss9m, 0);
        CV.put(TableData.TableInfo.CountCatchByGoalkeeperBrejk, 0);
        CV.put(TableData.TableInfo.CountCatchByGoalkeeperWing, 0);
        CV.put(TableData.TableInfo.CountCatchByGoalkeeper6m, 0);
        CV.put(TableData.TableInfo.CountCatchByGoalkeeper7m, 0);
        CV.put(TableData.TableInfo.CountCatchByGoalkeeper9m, 0);
        CV.put(TableData.TableInfo.ShotsPrediction, 0);
        CV.put(TableData.TableInfo.ShotsPredictionBrejk, 0);
        CV.put(TableData.TableInfo.ShotsPredictionWing, 0);
        CV.put(TableData.TableInfo.ShotsPrediction6m, 0);
        CV.put(TableData.TableInfo.ShotsPrediction7m, 0);
        CV.put(TableData.TableInfo.ShotsPrediction9m, 0);
        CV.put(TableData.TableInfo.FaulShotsPrediction, 0);
        CV.put(TableData.TableInfo.EfficientInDefensive, 0);
        CV.put(TableData.TableInfo.CountOfPlayedGames, 0);
        CV.put(TableData.TableInfo.Exploitation, 0);
        CV.put(TableData.TableInfo.PrimaryPosition, PrimaryPosition);
        CV.put(TableData.TableInfo.SecondaryPosition, SecondaryPosition);
        CV.put(TableData.TableInfo.BirthDate, BirthDate);
        CV.put(TableData.TableInfo.SocialSecurityNumber, SocialSecurityNumber);
        CV.put(TableData.TableInfo.Gender, Gender);
        CV.put(TableData.TableInfo.Note, Note);
        CV.put(TableData.TableInfo.ValuesChange, "false");
        CV.put(TableData.TableInfo.PrimaryKeyForPlayer, PrimaryKeyForPlayer);
        CV.put(TableData.TableInfo.TurnamentPKRoster, TurnamentPKRoster);

        SQ.insert(TableData.TableInfo.TableRoster, null, CV);
    }

    public Cursor GetInformationPlayer(DatabaseOperations DB, String Condition) {
        SQLiteDatabase SQ = DB.getReadableDatabase();
        return SQ.rawQuery("SELECT * FROM " + TableData.TableInfo.TableRoster + Condition, null);
    }

    public void UpdateInformationPlayer(DatabaseOperations DB, String Number, String ColumnForChange, String NewValue, String TurnamentPK) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(ColumnForChange, NewValue);
        SQ.update(TableData.TableInfo.TableRoster, Values, TableData.TableInfo.Number + " == " + Number + " AND " + TableData.TableInfo.TurnamentPKRoster + " == " + TurnamentPK, null);
    }

    public void DeleteInformationPlayer(DatabaseOperations DB, String Number, String TurnamentPK) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TableRoster, TableData.TableInfo.Number + " == " + Number + " AND " + TableData.TableInfo.TurnamentPKRoster + " == " + TurnamentPK, null);
    }

    public void ResetTableOfPlayersForGame(DatabaseOperations DB) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        SQ.execSQL("DROP TABLE IF EXISTS " + TableData.TableInfo.TableNamePlayer);
        SQ.execSQL(TableOfPlayersGame);
    }

    public void putInformationPlayers(DatabaseOperations DOP, String player_first_name, String player_second_name, Integer player_number, String TurnamentPlayerPK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.PlayerFirstName, player_first_name);
        CV.put(TableData.TableInfo.PlayerSecondName, player_second_name);
        CV.put(TableData.TableInfo.PlayerNumber, player_number);
        CV.put(TableData.TableInfo.GoalOnTarget, 0);
        CV.put(TableData.TableInfo.ShotMiss, 0);
        CV.put(TableData.TableInfo.ShotGoalkeeper, 0);
        CV.put(TableData.TableInfo.MissBall, 0);
        CV.put(TableData.TableInfo.ReachFaulShot, 0);
        CV.put(TableData.TableInfo.Plus, 0);
        CV.put(TableData.TableInfo.Minus, 0);
        CV.put(TableData.TableInfo.Faul, 0);
        CV.put(TableData.TableInfo.YellowCard, 0);
        CV.put(TableData.TableInfo.RedCard, 0);
        CV.put(TableData.TableInfo.ReachBall, 0);
        CV.put(TableData.TableInfo.PlayersTimeOnPlayground, "00:00");
        CV.put(TableData.TableInfo.FaulShotGoal, 0);
        CV.put(TableData.TableInfo.FaulShotMiss, 0);
        CV.put(TableData.TableInfo.BrejkGoal, 0);
        CV.put(TableData.TableInfo.BrejkMiss, 0);
        CV.put(TableData.TableInfo.GoalWing, 0);
        CV.put(TableData.TableInfo.MissWing, 0);
        CV.put(TableData.TableInfo.Goal6m, 0);
        CV.put(TableData.TableInfo.Goal7m, 0);
        CV.put(TableData.TableInfo.Goal9m, 0);
        CV.put(TableData.TableInfo.Miss6m, 0);
        CV.put(TableData.TableInfo.Miss7m, 0);
        CV.put(TableData.TableInfo.Miss9m, 0);
        CV.put(TableData.TableInfo.CatchByGoalkeeperBrejk, 0);
        CV.put(TableData.TableInfo.CatchByGoalkeeperWing, 0);
        CV.put(TableData.TableInfo.CatchByGoalkeeper6m, 0);
        CV.put(TableData.TableInfo.CatchByGoalkeeper7m, 0);
        CV.put(TableData.TableInfo.CatchByGoalkeeper9m, 0);
        CV.put(TableData.TableInfo.ShotsPredictionForGame, 0);
        CV.put(TableData.TableInfo.ShotsPredictionBrejkForGame, 0);
        CV.put(TableData.TableInfo.ShotsPredictionWingForGame, 0);
        CV.put(TableData.TableInfo.ShotsPrediction6mForGame, 0);
        CV.put(TableData.TableInfo.ShotsPrediction7mForGame, 0);
        CV.put(TableData.TableInfo.ShotsPrediction9mForGame, 0);
        CV.put(TableData.TableInfo.FaulShotsPredictionForGame, 0);
        CV.put(TableData.TableInfo.EfficientInDefensiveForGame, 0);
        CV.put(TableData.TableInfo.ValuesAreConvertedPlayer, "false");
        CV.put(TableData.TableInfo.TurnamentPlayerPK, TurnamentPlayerPK);

        SQ.insert(TableData.TableInfo.TableNamePlayer, null, CV);
    }

    public Cursor getInformationPlayers(DatabaseOperations DOP, String CONDITION) {

        SQLiteDatabase SQ = DOP.getReadableDatabase();
        Cursor CR = SQ.rawQuery("select * from " + TableData.TableInfo.TableNamePlayer + CONDITION, null);
        return CR;
    }

    public void updatePlayersInformations(DatabaseOperations DOP, String NUMBER, String COLUMN_FOR_CHANGE, String NEW_VALUE, String TurnamentPK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOR_CHANGE, NEW_VALUE);
        SQ.update(TableData.TableInfo.TableNamePlayer, values, TableData.TableInfo.PlayerNumber + " == " + NUMBER + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK, null);
    }

    public void deletePlayer(DatabaseOperations DOP, String NUMBER, String TurnamentPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TableNamePlayer, TableData.TableInfo.PlayerNumber + " == " + NUMBER + " AND " + TableData.TableInfo.TurnamentPlayerPK + " == " + TurnamentPK, null);
    }

    public void PutInformationPlayerHistory(DatabaseOperations DB, String HistoryPlayerFirstName, String HistoryPlayerSecondName, Integer HistoryPlayerNumber, Integer HistoryGoalOnTarget,
                                            Integer HistoryShotMiss, Integer HistoryShotGoalkeeper, Integer HistoryMissBall, Integer HistoryReachFaulShot, Integer HistoryPlus, Integer HistoryMinus, Integer HistoryFaul,
                                            Integer HistoryYellowCard, Integer HistoryRedCard, Integer HistoryReachBall, String HistoryPlayersTimeOnPlayground, Integer HistoryFaulShotGoal, Integer HistoryFaulShotMiss,
                                            Integer HistoryBrejkGoal, Integer HistoryBrejkMiss, Integer HistoryGoalWing, Integer HistoryMissWing, Integer HistoryGoal6m, Integer HistoryGoal7m, Integer HistoryGoal9m,
                                            Integer HistoryMiss6m, Integer HistoryMiss7m, Integer HistoryMiss9m, Integer HistoryCatchByGoalkeeperBrejk, Integer HistoryCatchByGoalkeeperWing, Integer HistoryCatchByGoalkeeper6m,
                                            Integer HistoryCatchByGoalkeeper7m, Integer HistoryCatchByGoalkeeper9m, Integer HistoryShotsPrediction, Integer HistoryShotsPredictionBrejk, Integer HistoryShotsPredictionWing,
                                            Integer HistoryShotsPrediction6m, Integer HistoryShotsPrediction7m, Integer HistoryShotsPrediction9m, Integer HistoryFaulShotsPrediction, Integer HistoryEfficientInDefensive,
                                            String HistoryDatetimaPlayer, String TurnamentHistoryPKPlayer) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.HistoryPlayerFirstName, HistoryPlayerFirstName);
        CV.put(TableData.TableInfo.HistoryPlayerSecondName, HistoryPlayerSecondName);
        CV.put(TableData.TableInfo.HistoryPlayerNumber, HistoryPlayerNumber);
        CV.put(TableData.TableInfo.HistoryGoalOnTarget, HistoryGoalOnTarget);
        CV.put(TableData.TableInfo.HistoryShotMiss, HistoryShotMiss);
        CV.put(TableData.TableInfo.HistoryShotGoalkeeper, HistoryShotGoalkeeper);
        CV.put(TableData.TableInfo.HistoryMissBall, HistoryMissBall);
        CV.put(TableData.TableInfo.HistoryReachFaulShot, HistoryReachFaulShot);
        CV.put(TableData.TableInfo.HistoryPlus, HistoryPlus);
        CV.put(TableData.TableInfo.HistoryMinus, HistoryMinus);
        CV.put(TableData.TableInfo.HistoryFaul, HistoryFaul);
        CV.put(TableData.TableInfo.HistoryYellowCard, HistoryYellowCard);
        CV.put(TableData.TableInfo.HistoryRedCard, HistoryRedCard);
        CV.put(TableData.TableInfo.HistoryReachBall, HistoryReachBall);
        CV.put(TableData.TableInfo.HistoryPlayersTimeOnPlayground, HistoryPlayersTimeOnPlayground);
        CV.put(TableData.TableInfo.HistoryFaulShotGoal, HistoryFaulShotGoal);
        CV.put(TableData.TableInfo.HistoryFaulShotMiss, HistoryFaulShotMiss);
        CV.put(TableData.TableInfo.HistoryBrejkGoal, HistoryBrejkGoal);
        CV.put(TableData.TableInfo.HistoryBrejkMiss, HistoryBrejkMiss);
        CV.put(TableData.TableInfo.HistoryGoalWing, HistoryGoalWing);
        CV.put(TableData.TableInfo.HistoryMissWing, HistoryMissWing);
        CV.put(TableData.TableInfo.HistoryGoal6m, HistoryGoal6m);
        CV.put(TableData.TableInfo.HistoryGoal7m, HistoryGoal7m);
        CV.put(TableData.TableInfo.HistoryGoal9m, HistoryGoal9m);
        CV.put(TableData.TableInfo.HistoryMiss6m, HistoryMiss6m);
        CV.put(TableData.TableInfo.HistoryMiss7m, HistoryMiss7m);
        CV.put(TableData.TableInfo.HistoryMiss9m, HistoryMiss9m);
        CV.put(TableData.TableInfo.HistoryCatchByGoalkeeperBrejk, HistoryCatchByGoalkeeperBrejk);
        CV.put(TableData.TableInfo.HistoryCatchByGoalkeeperWing, HistoryCatchByGoalkeeperWing);
        CV.put(TableData.TableInfo.HistoryCatchByGoalkeeper6m, HistoryCatchByGoalkeeper6m);
        CV.put(TableData.TableInfo.HistoryCatchByGoalkeeper7m, HistoryCatchByGoalkeeper7m);
        CV.put(TableData.TableInfo.HistoryCatchByGoalkeeper9m, HistoryCatchByGoalkeeper9m);
        CV.put(TableData.TableInfo.HistoryShotsPrediction, HistoryShotsPrediction);
        CV.put(TableData.TableInfo.HistoryShotsPredictionBrejk, HistoryShotsPredictionBrejk);
        CV.put(TableData.TableInfo.HistoryShotsPredictionWing, HistoryShotsPredictionWing);
        CV.put(TableData.TableInfo.HistoryShotsPrediction6m, HistoryShotsPrediction6m);
        CV.put(TableData.TableInfo.HistoryShotsPrediction7m, HistoryShotsPrediction7m);
        CV.put(TableData.TableInfo.HistoryShotsPrediction9m, HistoryShotsPrediction9m);
        CV.put(TableData.TableInfo.HistoryFaulShotsPrediction, HistoryFaulShotsPrediction);
        CV.put(TableData.TableInfo.HistoryEfficientInDefensive, HistoryEfficientInDefensive);
        CV.put(TableData.TableInfo.HistoryDatetimaPlayer, HistoryDatetimaPlayer);
        CV.put(TableData.TableInfo.TurnamentHistoryPKPlayer, TurnamentHistoryPKPlayer);

        SQ.insert(TableData.TableInfo.HistoryPlayer, null, CV);
    }

    public Cursor GetInformationPlayerHistory(DatabaseOperations DB, String Condition) {
        SQLiteDatabase SQ = DB.getReadableDatabase();
        return SQ.rawQuery("SELECT * FROM " + TableData.TableInfo.HistoryPlayer + Condition, null);
    }

    public void UpdatePlayerInformationsHistory(DatabaseOperations DOP, String PlayerNumber, String DateTime, String ColumnForChange, String NewValue) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnForChange, NewValue);
        SQ.update(TableData.TableInfo.HistoryPlayer, values, TableData.TableInfo.HistoryPlayerNumber + " == " + PlayerNumber + " AND " + TableData.TableInfo.HistoryDatetimaPlayer + " == " + "\"" + DateTime + "\"", null);
    }

    public void DeleteInformationPlayerHistory(DatabaseOperations DB, String Number, String DateTime) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        SQ.delete(TableData.TableInfo.HistoryPlayer, TableData.TableInfo.HistoryPlayerNumber + " == " + Number + " AND " + TableData.TableInfo.HistoryDatetimaPlayer + " == " + DateTime, null);
    }

    public void PutInformationGoalkeeper(DatabaseOperations DB, String GoalkeeperFirstNameRoster, String GoalkeeperSecondNameRoster, Integer GoalkeeperNumberRoster, String GoalkeeperBirthDate,
                                         String GoalkeeperSocialSecurityNumber, String GoalkeeperGender, String GoalkeeperNote, String GoalkeeperValuesChange, Integer GoalkeeperPrimaryKeyRoster,
                                         String TurnamentGoalkeeperPKRoster) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.GoalkeeperFirstNameRoster, GoalkeeperFirstNameRoster);
        CV.put(TableData.TableInfo.GoalkeeperSecondNameRoster, GoalkeeperSecondNameRoster);
        CV.put(TableData.TableInfo.GoalkeeperNumberRoster, GoalkeeperNumberRoster);
        CV.put(TableData.TableInfo.CountCatch, 0);
        CV.put(TableData.TableInfo.CountPassLongGood, 0);
        CV.put(TableData.TableInfo.CountPassLongBad, 0);
        CV.put(TableData.TableInfo.CountPassShortGood, 0);
        CV.put(TableData.TableInfo.CountPassShortBad, 0);
        CV.put(TableData.TableInfo.CountCatchFaulShot, 0);
        CV.put(TableData.TableInfo.CountMissFaulShot, 0);
        CV.put(TableData.TableInfo.CountRecoveredGoals, 0);
        CV.put(TableData.TableInfo.CountBrejkCatch, 0);
        CV.put(TableData.TableInfo.CountBrejkRecovered, 0);
        CV.put(TableData.TableInfo.CountWingCatch, 0);
        CV.put(TableData.TableInfo.CountWingRecovered, 0);
        CV.put(TableData.TableInfo.CountCatch6m, 0);
        CV.put(TableData.TableInfo.CountCatch7m, 0);
        CV.put(TableData.TableInfo.CountCatch9m, 0);
        CV.put(TableData.TableInfo.CountRecovered6m, 0);
        CV.put(TableData.TableInfo.CountRecovered7m, 0);
        CV.put(TableData.TableInfo.CountRecovered9m, 0);
        CV.put(TableData.TableInfo.PredictionOfCatch, 0);
        CV.put(TableData.TableInfo.PredictionOfCatchBrejk, 0);
        CV.put(TableData.TableInfo.PredictionOfCatchWing, 0);
        CV.put(TableData.TableInfo.PredictionOfCatch6m, 0);
        CV.put(TableData.TableInfo.PredictionOfCatch7m, 0);
        CV.put(TableData.TableInfo.PredictionOfCatch9m, 0);
        CV.put(TableData.TableInfo.PredictionOfLongPass, 0);
        CV.put(TableData.TableInfo.PredictionOfShortPass, 0);
        CV.put(TableData.TableInfo.PredictionOfCatchFaulshot, 0);
        CV.put(TableData.TableInfo.CountOfPlayedGamesGoalkeeper, 0);
        CV.put(TableData.TableInfo.CountOfInterventions, 0);
        CV.put(TableData.TableInfo.CountFaulGoalkeeper, 0);
        CV.put(TableData.TableInfo.CountYellowCardGoalkeeper, 0);
        CV.put(TableData.TableInfo.CountRedCardGoalkeeper, 0);
        CV.put(TableData.TableInfo.CountOfTimeOnPlayGroundGoalkeeper, "00:00");
        CV.put(TableData.TableInfo.GoalkeeperBirthDate, GoalkeeperBirthDate);
        CV.put(TableData.TableInfo.GoalkeeperSocialSecurityNumber, GoalkeeperSocialSecurityNumber);
        CV.put(TableData.TableInfo.GoalkeeperGender, GoalkeeperGender);
        CV.put(TableData.TableInfo.GoalkeeperNote, GoalkeeperNote);
        CV.put(TableData.TableInfo.GoalkeeperValuesChange, "false");
        CV.put(TableData.TableInfo.GoalkeeperPrimaryKeyRoster, GoalkeeperPrimaryKeyRoster);
        CV.put(TableData.TableInfo.TurnamentGoalkeeperPKRoster, TurnamentGoalkeeperPKRoster);
        SQ.insert(TableData.TableInfo.TableGoalkeeperRoster, null, CV);
    }

    public Cursor GetInformationGoalkeeper(DatabaseOperations DOP, String CONDITION) {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        return SQ.rawQuery("SELECT * FROM " + TableData.TableInfo.TableGoalkeeperRoster + CONDITION, null);
    }

    public void UpdateGoalkeeperInformations(DatabaseOperations DOP, String NUMBER, String COLUMN_FOR_CHANGE, String NEW_VALUE, String TurnamentPK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOR_CHANGE, NEW_VALUE);
        SQ.update(TableData.TableInfo.TableGoalkeeperRoster, values, TableData.TableInfo.GoalkeeperNumberRoster + " == " + NUMBER + " AND " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK, null);
    }

    public void DeleteGoalkeeper(DatabaseOperations DOP, String NUMBER, String TurnamentPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TableGoalkeeperRoster, TableData.TableInfo.GoalkeeperNumberRoster + " == " + NUMBER + " AND " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK, null);
    }

    public void ResetTableOfGoalkeepersForGame(DatabaseOperations DB) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        SQ.execSQL("DROP TABLE IF EXISTS " + TableData.TableInfo.TableGoalkeeper);
        SQ.execSQL(TableOfGoalkeepersGame);
    }

    public void putInformationGoalkeeper(DatabaseOperations DOP, String GoalkeeperFirstName, String GoalkeeperSecondName, Integer GoalkeeperNumber, String TurnamentGoalkeeperPK,
                                         String GoalkeeperValuesWereReset) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.GoalkeeperFirstName, GoalkeeperFirstName);
        CV.put(TableData.TableInfo.GoalkeeperSecondName, GoalkeeperSecondName);
        CV.put(TableData.TableInfo.GoalkeeperNumber, GoalkeeperNumber);
        CV.put(TableData.TableInfo.Catch, 0);
        CV.put(TableData.TableInfo.PassLongGood, 0);
        CV.put(TableData.TableInfo.PassLongBad, 0);
        CV.put(TableData.TableInfo.PassShortGood, 0);
        CV.put(TableData.TableInfo.PassShortBad, 0);
        CV.put(TableData.TableInfo.CatchFaulShot, 0);
        CV.put(TableData.TableInfo.MissFaulShot, 0);
        CV.put(TableData.TableInfo.RecoveredGoals, 0);
        CV.put(TableData.TableInfo.TimeOnPlaygroundGoalkeeper, "00:00");
        CV.put(TableData.TableInfo.BrejkCatch, 0);
        CV.put(TableData.TableInfo.BrejkRecovered, 0);
        CV.put(TableData.TableInfo.WingCatch, 0);
        CV.put(TableData.TableInfo.WingRecovered, 0);
        CV.put(TableData.TableInfo.Catch6m, 0);
        CV.put(TableData.TableInfo.Catch7m, 0);
        CV.put(TableData.TableInfo.Catch9m, 0);
        CV.put(TableData.TableInfo.Recovered6m,0 );
        CV.put(TableData.TableInfo.Recovered7m, 0);
        CV.put(TableData.TableInfo.Recovered9m, 0);
        CV.put(TableData.TableInfo.FaulGoalkeeper,0 );
        CV.put(TableData.TableInfo.YellowCardGoalkeeper, 0);
        CV.put(TableData.TableInfo.RedCardGoalkeeper, 0);
        CV.put(TableData.TableInfo.PredictionOfCatchForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfCatchBrejkForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfCatchWingForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfCatch6mForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfCatch7mForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfCatch9mForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfLongPassForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfShortPassForGame, 0);
        CV.put(TableData.TableInfo.PredictionOfCatchFaulshotForGame, 0);
        CV.put(TableData.TableInfo.CountOfInterventionsForGame, 0);
        CV.put(TableData.TableInfo.ValuesAreConvertedGoalkeeper, "false");
        CV.put(TableData.TableInfo.TurnamentGoalkeeperPK, TurnamentGoalkeeperPK);

        SQ.insert(TableData.TableInfo.TableGoalkeeper, null, CV);
    }

    public Cursor getInformationGoalkeeper(DatabaseOperations DOP, String CONDITION) {

        SQLiteDatabase SQ = DOP.getReadableDatabase();
        Cursor CR = SQ.rawQuery("select * from " + TableData.TableInfo.TableGoalkeeper + CONDITION, null);
        return CR;
    }

    public void updateGoalkeeperInformations(DatabaseOperations DOP, String NUMBER, String COLUMN_FOR_CHANGE, String NEW_VALUE, String TurnamentPK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOR_CHANGE, NEW_VALUE);
        SQ.update(TableData.TableInfo.TableGoalkeeper, values, TableData.TableInfo.GoalkeeperNumber + " == " + NUMBER + " AND " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK, null);
    }

    public void deleteGoalkeeper(DatabaseOperations DOP, String NUMBER, String TurnamentPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TableGoalkeeper, TableData.TableInfo.GoalkeeperNumber + " == " + NUMBER + " AND " + TableData.TableInfo.TurnamentGoalkeeperPK + " == " + TurnamentPK, null);
    }

    public void PutInformationGoalkeeperHistory(DatabaseOperations DB, String HistoryGoalkeeperFirstName, String HistoryGoalkeeperSecondName, Integer HistoryGoalkeeperNumber, Integer HistoryCatch, Integer HistoryPassLongGood,
                                                Integer HistoryPassLongBad, Integer HistoryPassShortGood, Integer HistoryPassShortBad, Integer HistoryCatchFaulShot, Integer HistoryMissFaulShot, Integer HistoryRecoveredGoals,
                                                String HistoryTimeOnPlaygroundGoalkeeper, Integer HistoryBrejkCatch, Integer HistoryBrejkRecovered, Integer HistoryWingCatch, Integer HistoryWingRecovered, Integer HistoryCatch6m,
                                                Integer HistoryCatch7m, Integer HistoryCatch9m, Integer HistoryRecovered6m, Integer HistoryRecovered7m, Integer HistoryRecovered9m, Integer HistoryFaulGoalkeeper,
                                                Integer HistoryYellowCardGoalkeeper, Integer HistoryRedCardGoalkeeper, Integer HistoryPredictionOfCatch, Integer HistoryPredictionOfCatchBrejk, Integer HistoryPredictionOfCatchWing,
                                                Integer HistoryPredictionOfCatch6m, Integer HistoryPredictionOfCatch7m, Integer HistoryPredictionOfCatch9m, Integer HistoryPredictionOfLongPass, Integer HistoryPredictionOfShortPass,
                                                Integer HistoryPredictionOfCatchFaulshot, Integer HistoryCountOfInterventions, String HistoryDatetimeGoalkeeper, String TurnamentHistoryPKGoalkeeper) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.HistoryGoalkeeperFirstName, HistoryGoalkeeperFirstName);
        CV.put(TableData.TableInfo.HistoryGoalkeeperSecondName, HistoryGoalkeeperSecondName);
        CV.put(TableData.TableInfo.HistoryGoalkeeperNumber, HistoryGoalkeeperNumber);
        CV.put(TableData.TableInfo.HistoryCatch, HistoryCatch);
        CV.put(TableData.TableInfo.HistoryPassLongGood, HistoryPassLongGood);
        CV.put(TableData.TableInfo.HistoryPassLongBad, HistoryPassLongBad);
        CV.put(TableData.TableInfo.HistoryPassShortGood, HistoryPassShortGood);
        CV.put(TableData.TableInfo.HistoryPassShortBad, HistoryPassShortBad);
        CV.put(TableData.TableInfo.HistoryCatchFaulShot, HistoryCatchFaulShot);
        CV.put(TableData.TableInfo.HistoryMissFaulShot, HistoryMissFaulShot);
        CV.put(TableData.TableInfo.HistoryRecoveredGoals, HistoryRecoveredGoals);
        CV.put(TableData.TableInfo.HistoryTimeOnPlaygroundGoalkeeper, HistoryTimeOnPlaygroundGoalkeeper);
        CV.put(TableData.TableInfo.HistoryBrejkCatch, HistoryBrejkCatch);
        CV.put(TableData.TableInfo.HistoryBrejkRecovered, HistoryBrejkRecovered);
        CV.put(TableData.TableInfo.HistoryWingCatch, HistoryWingCatch);
        CV.put(TableData.TableInfo.HistoryWingRecovered, HistoryWingRecovered);
        CV.put(TableData.TableInfo.HistoryCatch6m, HistoryCatch6m);
        CV.put(TableData.TableInfo.HistoryCatch7m, HistoryCatch7m);
        CV.put(TableData.TableInfo.HistoryCatch9m, HistoryCatch9m);
        CV.put(TableData.TableInfo.HistoryRecovered6m, HistoryRecovered6m);
        CV.put(TableData.TableInfo.HistoryRecovered7m, HistoryRecovered7m);
        CV.put(TableData.TableInfo.HistoryRecovered9m, HistoryRecovered9m);
        CV.put(TableData.TableInfo.HistoryPredictionOfCatch, HistoryPredictionOfCatch);
        CV.put(TableData.TableInfo.HistoryPredictionOfCatchBrejk, HistoryPredictionOfCatchBrejk);
        CV.put(TableData.TableInfo.HistoryPredictionOfCatchWing, HistoryPredictionOfCatchWing);
        CV.put(TableData.TableInfo.HistoryPredictionOfCatch6m, HistoryPredictionOfCatch6m);
        CV.put(TableData.TableInfo.HistoryPredictionOfCatch7m, HistoryPredictionOfCatch7m);
        CV.put(TableData.TableInfo.HistoryPredictionOfCatch9m, HistoryPredictionOfCatch9m);
        CV.put(TableData.TableInfo.HistoryPredictionOfLongPass, HistoryPredictionOfLongPass);
        CV.put(TableData.TableInfo.HistoryPredictionOfShortPass, HistoryPredictionOfShortPass);
        CV.put(TableData.TableInfo.HistoryPredictionOfCatchFaulshot, HistoryPredictionOfCatchFaulshot);
        CV.put(TableData.TableInfo.HistoryCountOfInterventions, HistoryCountOfInterventions);
        CV.put(TableData.TableInfo.HistoryFaulGoalkeeper, HistoryFaulGoalkeeper);
        CV.put(TableData.TableInfo.HistoryYellowCardGoalkeeper, HistoryYellowCardGoalkeeper);
        CV.put(TableData.TableInfo.HistoryRedCardGoalkeeper, HistoryRedCardGoalkeeper);
        CV.put(TableData.TableInfo.HistoryDatetimeGoalkeeper, HistoryDatetimeGoalkeeper);
        CV.put(TableData.TableInfo.TurnamentHistoryPKGoalkeeper, TurnamentHistoryPKGoalkeeper);

        SQ.insert(TableData.TableInfo.HistoryGoalkeeper, null, CV);
    }

    public Cursor GetInformationGoalkeeperHistory(DatabaseOperations DB, String Condition) {
        SQLiteDatabase SQ = DB.getReadableDatabase();
        return SQ.rawQuery("SELECT * FROM " + TableData.TableInfo.HistoryGoalkeeper + Condition, null);
    }

    public void UpdateGoalkeeperInformationsHistory(DatabaseOperations DOP, String PlayerNumber, String DateTime, String ColumnForChange, String NewValue) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnForChange, NewValue);
        SQ.update(TableData.TableInfo.HistoryGoalkeeper, values, TableData.TableInfo.HistoryGoalkeeperNumber + " == " + PlayerNumber + " AND " + TableData.TableInfo.HistoryDatetimeGoalkeeper + " == " + "\"" + DateTime + "\"", null);
    }

    public void DeleteInformationGoalkeeperHistory(DatabaseOperations DB, String Number, String DateTime) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        SQ.delete(TableData.TableInfo.HistoryGoalkeeper, TableData.TableInfo.HistoryGoalkeeperNumber + " == " + Number + " AND " + TableData.TableInfo.HistoryDatetimeGoalkeeper + " == " + DateTime, null);
    }

    public void putInformationGame(DatabaseOperations DOP, String TeamName, String NameOfOponent, String MatchTime, Integer PKGame, String TurnamentGamePK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.TeamName, TeamName);
        CV.put(TableData.TableInfo.NameOfOponent, NameOfOponent);
        CV.put(TableData.TableInfo.HalftimeScore, "0");
        CV.put(TableData.TableInfo.FinalScore, "0");
        CV.put(TableData.TableInfo.MatchTime, MatchTime);
        CV.put(TableData.TableInfo.TimeInOfensive, "0");
        CV.put(TableData.TableInfo.CountOfOfensive, "0");
        CV.put(TableData.TableInfo.TimeInDefensive, "0");
        CV.put(TableData.TableInfo.CountOfDefensive, "0");
        CV.put(TableData.TableInfo.GameReplay, "0");
        CV.put(TableData.TableInfo.GameReport, "0");
        CV.put(TableData.TableInfo.PowerPlaysCount, "0");
        CV.put(TableData.TableInfo.PowerPlaysWin, "0");
        CV.put(TableData.TableInfo.ReachedGoalWhilePowerPlay, "0");
        CV.put(TableData.TableInfo.CountOfWeakening, "0");
        CV.put(TableData.TableInfo.GoalWhileWeakening, "0");
        CV.put(TableData.TableInfo.ReachedGoalWhileWeakening, "0");
        CV.put(TableData.TableInfo.GameCountOfShots, "0");
        CV.put(TableData.TableInfo.GameCountOfMissShots, "0");
        CV.put(TableData.TableInfo.GameCountOfgoalkeeperShots, "0");
        CV.put(TableData.TableInfo.GamePredictionOfShots, "0");
        CV.put(TableData.TableInfo.GameCountOfGoalBrejk, "0");
        CV.put(TableData.TableInfo.GameCountOfMissBrejk, "0");
        CV.put(TableData.TableInfo.GameCountOfGoalkeeperBrejk, "0");
        CV.put(TableData.TableInfo.GamePredictionOfShotsBrejk, "0");
        CV.put(TableData.TableInfo.GameCountOfGoalWing, "0");
        CV.put(TableData.TableInfo.GameCountOfMissWing, "0");
        CV.put(TableData.TableInfo.GameCountOfGoalkeeperWing, "0");
        CV.put(TableData.TableInfo.GamePredictionOfShotsWing, "0");
        CV.put(TableData.TableInfo.GameCountOfGoal6m, "0");
        CV.put(TableData.TableInfo.GameCountOfMiss6m, "0");
        CV.put(TableData.TableInfo.GameCountOfGoalkeeper6m, "0");
        CV.put(TableData.TableInfo.GamePredictionOfShots6m, "0");
        CV.put(TableData.TableInfo.GameCountOfGoal7m, "0");
        CV.put(TableData.TableInfo.GameCountOfMiss7m, "0");
        CV.put(TableData.TableInfo.GameCountOfGoalkeeper7m, "0");
        CV.put(TableData.TableInfo.GamePredictionOfShots7m, "0");
        CV.put(TableData.TableInfo.GameCountOfGoal9m, "0");
        CV.put(TableData.TableInfo.GameCountOfMiss9m, "0");
        CV.put(TableData.TableInfo.GameCountOfGoalkeeper9m, "0");
        CV.put(TableData.TableInfo.GamePredictionOfShots9m, "0");
        CV.put(TableData.TableInfo.GameCountOfFaulShots, "0");
        CV.put(TableData.TableInfo.GameCountOfFaulShotsGoal, "0");
        CV.put(TableData.TableInfo.GameCountOfFaulShotsMiss, "0");
        CV.put(TableData.TableInfo.GameCountOfReachBalls, "0");
        CV.put(TableData.TableInfo.GameCountOfMissBalls, "0");
        CV.put(TableData.TableInfo.GameCountOfPlus, "0");
        CV.put(TableData.TableInfo.GameCountOfMinus, "0");
        CV.put(TableData.TableInfo.GameCountOfFauls, "0");
        CV.put(TableData.TableInfo.GameCountOfYellowCards, "0");
        CV.put(TableData.TableInfo.GameCountOfRedCards, "0");
        CV.put(TableData.TableInfo.GamePredictionOfFaulsShots, "0");
        CV.put(TableData.TableInfo.MatchSuccessfullyCompleted, "false");
        CV.put(TableData.TableInfo.GamePlayedHome, "");
        CV.put(TableData.TableInfo.DateTimePKGame, "");
        CV.put(TableData.TableInfo.PKNumberGame, PKGame);
        CV.put(TableData.TableInfo.TurnamentGamePK, TurnamentGamePK);

        SQ.insert(TableData.TableInfo.TableNameGame, null, CV);
    }

    public void updateInformationsGame(DatabaseOperations DOP, String PK, String COLUMN_FOR_CHANGE, String NEW_VALUE, String TurnamentPK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOR_CHANGE, NEW_VALUE);
        SQ.update(TableData.TableInfo.TableNameGame, values, TableData.TableInfo.PKNumberGame + " == " + PK + " AND " + TableData.TableInfo.TurnamentGamePK + " == " + TurnamentPK, null);
    }

    public void UpdateInformationsGameByDateTime(DatabaseOperations DOP, String DateTime, String COLUMN_FOR_CHANGE, String NEW_VALUE) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOR_CHANGE, NEW_VALUE);
        SQ.update(TableData.TableInfo.TableNameGame, values, TableData.TableInfo.DateTimePKGame + " == " + "\"" + DateTime + "\"", null);
    }

    public Cursor getInformationGame(DatabaseOperations DOP, String Condition) {

        SQLiteDatabase SQ = DOP.getReadableDatabase();
        Cursor CR = SQ.rawQuery("select * from " + TableData.TableInfo.TableNameGame + Condition, null);
        return CR;
    }

    public void deleteGame(DatabaseOperations DOP, String PK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TableNameGame, TableData.TableInfo.PKNumberGame + " == " + PK, null);
    }

    public void deleteGameByDateTime(DatabaseOperations DOP, String DateTime){
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TableNameGame, TableData.TableInfo.DateTimePKGame + " == " + "\"" + DateTime + "\"", null);
    }

    public void PutInformationSummaryGame(DatabaseOperations DOP, String TurnamentPK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.SummaryCountWinGames, 0);
        CV.put(TableData.TableInfo.SummaryCountWinGamesHome, 0);
        CV.put(TableData.TableInfo.SummaryCountWinGamesOut, 0);
        CV.put(TableData.TableInfo.SummaryCountLoseGames, 0);
        CV.put(TableData.TableInfo.SummaryCountLoseGamesHome, 0);
        CV.put(TableData.TableInfo.SummaryCountLoseGamesOut, 0);
        CV.put(TableData.TableInfo.SummaryCountOfOfensive, 0);
        CV.put(TableData.TableInfo.SummaryCountOfDefensive, 0);
        CV.put(TableData.TableInfo.SummaryPowerPlaysCount, 0);
        CV.put(TableData.TableInfo.SummaryPowerPlaysWin, 0);
        CV.put(TableData.TableInfo.SummaryReachedGoalWhilePowerPlay, 0);
        CV.put(TableData.TableInfo.SummaryCountOfWeakening, 0);
        CV.put(TableData.TableInfo.SummaryGoalWhileWeakening, 0);
        CV.put(TableData.TableInfo.SummaryReachedGoalWhileWeakening, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfShots, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMissShots, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfgoalkeeperShots, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMissBalls, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfFaulShots, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfPlus, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMinus, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfFauls, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfYellowCards, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfRedCards, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfReachBalls, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfFaulShotsGoal, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfFaulShotsMiss, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoalBrejk, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMissBrejk, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoalWing, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMissWing, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoal6m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoal7m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoal9m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMiss6m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMiss7m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfMiss9m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoalkeeperBrejk, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoalkeeperWing, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoalkeeper6m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoalkeeper7m, 0);
        CV.put(TableData.TableInfo.SummaryGameCountOfGoalkeeper9m, 0);
        CV.put(TableData.TableInfo.SummaryGamePredictionOfShots, 0);
        CV.put(TableData.TableInfo.SummaryGamePredictionOfShotsBrejk, 0);
        CV.put(TableData.TableInfo.SummaryGamePredictionOfShotsWing, 0);
        CV.put(TableData.TableInfo.SummaryGamePredictionOfShots6m, 0);
        CV.put(TableData.TableInfo.SummaryGamePredictionOfShots7m, 0);
        CV.put(TableData.TableInfo.SummaryGamePredictionOfShots9m, 0);
        CV.put(TableData.TableInfo.SummaryGamePredictionOfFaulsShots, 0);
        CV.put(TableData.TableInfo.SummaryCountGamePlayedHome, 0);
        CV.put(TableData.TableInfo.SummaryCountGamePlayedOut, 0);
        CV.put(TableData.TableInfo.SummaryMatchTime, "00:00");
        CV.put(TableData.TableInfo.SummaryTimeInOfensive, "00:00");
        CV.put(TableData.TableInfo.SummaryTimeInDefensive, "00:00");
        CV.put(TableData.TableInfo.SummaryValuesChange, "false");
        CV.put(TableData.TableInfo.SummaryGameTurnamentPK, TurnamentPK);;

        SQ.insert(TableData.TableInfo.TableNameSummaryGame, null, CV);
    }

    public void UpdateInformationsSummaryGame(DatabaseOperations DOP, String COLUMN_FOR_CHANGE, String NEW_VALUE, String TurnamentPK) {

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOR_CHANGE, NEW_VALUE);
        System.out.print(COLUMN_FOR_CHANGE + " " + NEW_VALUE + " " + "\n");
        SQ.update(TableData.TableInfo.TableNameSummaryGame, values, TableData.TableInfo.SummaryGameTurnamentPK + " == " + TurnamentPK, null);
    }

    public Cursor GetInformationSummaryGame(DatabaseOperations DOP, String Condition) {

        SQLiteDatabase SQ = DOP.getReadableDatabase();
        Cursor CR = SQ.rawQuery("select * from " + TableData.TableInfo.TableNameSummaryGame + Condition, null);
        return CR;
    }


    public void putInformationGameOption(DatabaseOperations DOP, String count_of_monitoring_players, String HowOftenRecordGame, String name_of_monitoring_team, String game_time, String count_of_periods, String player_one, String player_two, String player_three, String player_four,
                                         String player_five, String player_six, String goalkeeper, String option_goal_on_target, String option_shot_miss, String option_shot_goalkeeper, String option_miss_ball, String option_reach_faul_shot,
                                         String option_plus, String option_minus, String option_faul, String option_yellow_card, String option_red_card, String reach_ball, String option_players_time_on_playground, String order_pk, String TurnamentGameOptionsPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.COUNT_OF_MONITORING_PLAYERS, count_of_monitoring_players);
        CV.put(TableData.TableInfo.HowOftenRecordGame, HowOftenRecordGame);
        CV.put(TableData.TableInfo.NAME_OF_MONITORING_TEAM, name_of_monitoring_team);
        CV.put(TableData.TableInfo.GAME_TIME, game_time);
        CV.put(TableData.TableInfo.COUNT_OF_PERIODS, count_of_periods);
        CV.put(TableData.TableInfo.PLAYER_ONE, player_one);
        CV.put(TableData.TableInfo.PLAYER_TWO, player_two);
        CV.put(TableData.TableInfo.PLAYER_THREE, player_three);
        CV.put(TableData.TableInfo.PLAYER_FOUR, player_four);
        CV.put(TableData.TableInfo.PLAYER_FIVE, player_five);
        CV.put(TableData.TableInfo.PLAYER_SIX, player_six);
        CV.put(TableData.TableInfo.GOALKEEPER, goalkeeper);
        CV.put(TableData.TableInfo.OPTION_GOAL_ON_TARGET, option_goal_on_target);
        CV.put(TableData.TableInfo.OPTION_SHOT_MISS, option_shot_miss);
        CV.put(TableData.TableInfo.OPTION_SHOT_GOALKEEPER, option_shot_goalkeeper);
        CV.put(TableData.TableInfo.OPTION_MISS_BALL, option_miss_ball);
        CV.put(TableData.TableInfo.OPTION_REACH_FAUL_SHOT, option_reach_faul_shot);
        CV.put(TableData.TableInfo.OPTION_PLUS, option_plus);
        CV.put(TableData.TableInfo.OPTION_MINUS, option_minus);
        CV.put(TableData.TableInfo.OPTION_FAUL, option_faul);
        CV.put(TableData.TableInfo.OPTION_YELLOW_CARD, option_yellow_card);
        CV.put(TableData.TableInfo.OPTION_RED_CARD, option_red_card);
        CV.put(TableData.TableInfo.OPTION_REACH_BALL, reach_ball);
        CV.put(TableData.TableInfo.OPTION_PLAYERS_TIME_ON_PLAYGROUND, option_players_time_on_playground);
        CV.put(TableData.TableInfo.ORDER_PK, order_pk);
        CV.put(TableData.TableInfo.TurnamentGameOptionsPK, TurnamentGameOptionsPK);
        SQ.insert(TableData.TableInfo.TABLE_GAME_OPTIONS, null, CV);
    }

    public Cursor getInformationGameOption(DatabaseOperations DOP, String CONDITION) {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        Cursor CR = SQ.rawQuery("select * from " + TableData.TableInfo.TABLE_GAME_OPTIONS + CONDITION, null);
        return CR;
    }

    public void updateGameOption(DatabaseOperations DOP, String COLUMN_FOR_CHANGE, String NEW_VALUE, String TurnamentPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOR_CHANGE, NEW_VALUE);
        SQ.update(TableData.TableInfo.TABLE_GAME_OPTIONS, values, TableData.TableInfo.ORDER_PK  + " == " + "2" + " AND " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK, null);
    }

    public void putInformationLineUp(DatabaseOperations DOP, String LineUp, String LineUpPK, String TurnamentLineUpsPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.LineUp, LineUp);
        CV.put(TableData.TableInfo.LineUpPK, LineUpPK);
        CV.put(TableData.TableInfo.TurnamentLineUpsPK, TurnamentLineUpsPK);
        SQ.insert(TableData.TableInfo.Table_Name_LineUp, null, CV);
    }

    public Cursor getInformationLineUp(DatabaseOperations DOP, String Condition) {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        return SQ.rawQuery("select * from " + TableData.TableInfo.Table_Name_LineUp + Condition, null);
    }

    public void updateLineUp (DatabaseOperations DOP, String ColumnForChange, String NewValue, String LineUpPK, String TurnamentPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnForChange, NewValue);
        SQ.update(TableData.TableInfo.Table_Name_LineUp, values, TableData.TableInfo.LineUpPK + " == " + LineUpPK + " AND " + TableData.TableInfo.TurnamentLineUpsPK + " == " + TurnamentPK, null);
    }

    public void putInformationOpponent(DatabaseOperations DOP, String MissShotsOpponent, String FaulsOpponent, String Date, String OpponentPK, String TurnamentOpponentsPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.MissShotsOpponent, MissShotsOpponent);
        CV.put(TableData.TableInfo.FaulOpponent, FaulsOpponent);
        CV.put(TableData.TableInfo.Date, Date);
        CV.put(TableData.TableInfo.Opponent_PK, OpponentPK);
        CV.put(TableData.TableInfo.TurnamentOpponentsPK, TurnamentOpponentsPK);
        SQ.insert(TableData.TableInfo.Table_Opponents_Values, null, CV);
    }

    public Cursor getInformationOpponent(DatabaseOperations DOP, String Condition) {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        return SQ.rawQuery("select * from " + TableData.TableInfo.Table_Opponents_Values + Condition, null);
    }

    public void putInformationTurnament(DatabaseOperations DOP, String TurnamentName, String TurnamentPK) {
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.TurnamentName, TurnamentName);
        CV.put(TableData.TableInfo.TurnamentPK, TurnamentPK);
        SQ.insert(TableData.TableInfo.TableOfTurnaments, null, CV);
    }

    public Cursor getInformationTurnament(DatabaseOperations DOP, String Condition) {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        return SQ.rawQuery("select * from " + TableData.TableInfo.TableOfTurnaments + Condition, null);
    }
}
