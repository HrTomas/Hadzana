package com.example.tomas.hadzana.Summary;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class SummaryGame extends Fragment {

    Context CX;
    DatabaseOperations DB;

    TextView WinGames, WinGamesHome, WinGamesOut, LooseGames, LooseGamesHome, LooseGamesOut, DrawGames, DrawGamesHome, DrawGamesOut;


    String TurnamentPK, ConditionPlayer, ConditionGoalkeeper, ConditionGame, ConditionGameOptions;

    Integer TextSizeForScreens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View SummaryGame = inflater.inflate(R.layout.summary_game, container, false);
        TurnamentPK = getArguments().getString("TurnamentPK");

        CX = getActivity();
        DB = new DatabaseOperations(CX);

        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 600) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TextSizeForScreens = 30;
//        } else {
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            getActivity().setContentView(R.layout.summary_game_mobile);
//            TextSizeForScreens = 15;
//        }

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
        GameSummary(SummaryGame);

        return SummaryGame;
    }

    public void DeleteSurplusData() {
        boolean Match = false;
        String DateTime = "";
        Cursor CRG = DB.getInformationGame(DB, ConditionGame);
        if(CRG.moveToFirst()) {
            do {
                if (CRG.getString(Constants.MATCH_SUCCESSFULLY_COMPLETED).equals("false") || CRG.getString(Constants.TURNAMENT_GAME_PK).equals("0")) {
                    DateTime = CRG.getString(Constants.DATETIME_PK_GAME);
                    Toast.makeText(getActivity().getBaseContext(), CRG.getString(Constants.FINAL_SCORE) + " " + CRG.getString(Constants.MATCH_SUCCESSFULLY_COMPLETED), Toast.LENGTH_SHORT).show();
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

    public void GameSummary(View SummaryGame) {

        WinGames = (TextView) SummaryGame.findViewById(R.id.WinGames);
        WinGamesHome = (TextView) SummaryGame.findViewById(R.id.WinGamesHome);
        WinGamesOut = (TextView) SummaryGame.findViewById(R.id.WinGamesOut);
        LooseGames = (TextView) SummaryGame.findViewById(R.id.LooseGames);
        LooseGamesHome = (TextView) SummaryGame.findViewById(R.id.LooseGamesHome);
        LooseGamesOut = (TextView) SummaryGame.findViewById(R.id.LooseGamesOut);
        DrawGames = (TextView) SummaryGame.findViewById(R.id.DrawGames);
        DrawGamesHome = (TextView) SummaryGame.findViewById(R.id.DrawGamesHome);
        DrawGamesOut = (TextView) SummaryGame.findViewById(R.id.DrawGamesOut);

        Cursor CR = DB.getInformationGame(DB, ConditionGame);
        int i =0;
        if(CR.moveToFirst()) {
            do {
                i++;
                if(Integer.parseInt(CR.getString(Constants.FINAL_SCORE).split(":")[0]) > Integer.parseInt(CR.getString(Constants.FINAL_SCORE).split(":")[1])) {
                    WinGames.setText(String.valueOf(Integer.parseInt(WinGames.getText().toString()) + 1));
                    if (CR.getString(Constants.GAME_PLAYED_HOME).equals("true"))
                        WinGamesHome.setText(String.valueOf(Integer.parseInt(WinGamesHome.getText().toString()) + 1));
                    else
                        WinGamesOut.setText(String.valueOf(Integer.parseInt(WinGamesOut.getText().toString()) + 1));
                }
                if(Integer.parseInt(CR.getString(Constants.FINAL_SCORE).split(":")[0]) < Integer.parseInt(CR.getString(Constants.FINAL_SCORE).split(":")[1])) {
                    LooseGames.setText(String.valueOf(Integer.parseInt(LooseGames.getText().toString()) + 1));
                    if (CR.getString(Constants.GAME_PLAYED_HOME).equals("true"))
                        LooseGamesHome.setText(String.valueOf(Integer.parseInt(LooseGamesHome.getText().toString()) + 1));
                    else
                        LooseGamesOut.setText(String.valueOf(Integer.parseInt(LooseGamesOut.getText().toString()) + 1));
                }
                if(Integer.parseInt(CR.getString(Constants.FINAL_SCORE).split(":")[0]) == Integer.parseInt(CR.getString(Constants.FINAL_SCORE).split(":")[1])) {
                    DrawGames.setText(String.valueOf(Integer.parseInt(DrawGames.getText().toString()) + 1));
                    if (CR.getString(Constants.GAME_PLAYED_HOME).equals("true"))
                        DrawGamesHome.setText(String.valueOf(Integer.parseInt(DrawGamesHome.getText().toString()) + 1));
                    else
                        DrawGamesOut.setText(String.valueOf(Integer.parseInt(DrawGamesOut.getText().toString()) + 1));
                }
            }while(CR.moveToNext());
        }
    }
}