package com.example.tomas.hadzana.AddPlayers;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomas.hadzana.Constants;
import com.example.tomas.hadzana.Database.DatabaseOperations;
import com.example.tomas.hadzana.Database.TableData;
import com.example.tomas.hadzana.R;

public class AddPlayersToDatabase extends AppCompatActivity {

    String player_first_name, player_second_name, LineUpString = "", LineUpPK = "", TurnamentPK = "0", ConditionPlayer, ConditionGoalkeeper, ConditionGameOptions, ConditionLineUp;
    Integer player_number;
    EditText PlayersFirstName, PlayersSecondName, PlayersNumber, LineUpName;
    RelativeLayout PlayersLayout, TableOfPlayersAndGoalkeepers, NewPlayerOrNewOfensiveFormation, InsertPlayers, OfensiveFormationLayout, NewOrEditLineUp, TableOfLineUps,
            TableOfPlayers,GlobalPlayers;
    Context CTX = this;
    Button InsertPlayer, InsertGoalkeeper, NewPlayer, NewLineUp, InsertLineUpButton, ImportPlayers;
    DatabaseOperations DB = new DatabaseOperations(CTX);
    LinearLayout PlayersNumbers, PlayersNames, GoalkeepersNumbers, GoalkeepersNames, ListOfPlayer, ListOfLineUp, LayoutForLineUp;
    Integer MaxNumberOfPlayersInLineUp = 6, j = 0, TextSizeForScreens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.activity_add_players_to_database);
            TextSizeForScreens = 30;
        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.activity_add_players_to_database_mobile);
//            TextSizeForScreens = 15;
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TurnamentPK = extras.getString("TurnamentPK");
        }
        ConditionPlayer = " WHERE " + TableData.TableInfo.TurnamentPKRoster + " == " + TurnamentPK;
        ConditionGoalkeeper = " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + TurnamentPK;
        ConditionGameOptions = " WHERE " + TableData.TableInfo.TurnamentGameOptionsPK + " == " + TurnamentPK;
        ConditionLineUp = " WHERE " + TableData.TableInfo.TurnamentLineUpsPK + " == " + TurnamentPK;
        ImportPlayers = (Button) findViewById(R.id.ImportPlayers);

        PlayersLayout = (RelativeLayout) findViewById(R.id.PlayersLayout);
        TableOfPlayersAndGoalkeepers = (RelativeLayout) findViewById(R.id.TableOfPlayersAndGoalkeepers);
        NewPlayerOrNewOfensiveFormation = (RelativeLayout) findViewById(R.id.NewPlayerOrNewOfensiveFormation);
        InsertPlayers = (RelativeLayout) findViewById(R.id.InsertPlayers);


        NewPlayer = (Button) findViewById(R.id.buttonNewPlayer);
        NewPlayer.setText("Novy hrac");
        NewPlayer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertPlayers.setVisibility(View.VISIBLE);
                NewPlayerOrNewOfensiveFormation.setVisibility(View.GONE);
                AddingPlayers();
            }
        });

        NewLineUp = (Button) findViewById(R.id.NewLineUp);
        NewLineUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CreatePackForOfensive();
            }
        });
         /*TODO: NOT DEPLOYMENT CODE*/
        Cursor CRPlayer = DB.GetInformationPlayer(DB, "");
        if(!CRPlayer.moveToFirst()) {
            DB.PutInformationPlayer(DB, "Tomas", "Hreha", 1, "0", "0", "0", "0", "0", "0", "0", 0, "0");
            DB.PutInformationPlayer(DB, "Samo", "Hreha", 2, "0", "0", "0", "0", "0", "0", "0", 1, "0");
            DB.PutInformationPlayer(DB, "Alex", "Hreha", 3, "0", "0", "0", "0", "0", "0", "0", 2, "0");
            DB.PutInformationPlayer(DB, "Jozef", "Hreha", 4, "0", "0", "0", "0", "0", "0", "0", 3, "0");
            DB.PutInformationPlayer(DB, "Juraj", "Hreha", 5, "0", "0", "0", "0", "0", "0", "0", 4, "0");
            DB.PutInformationPlayer(DB, "Filip", "Hreha", 6, "0", "0", "0", "0", "0", "0", "0", 5, "0");
            DB.PutInformationPlayer(DB, "Sasa", "Hrehova", 7, "0", "0", "0", "0", "0", "0", "0", 6, "0");
            DB.PutInformationPlayer(DB, "Stella", "Hrehova", 8, "0", "0", "0", "0", "0","0", "0", 7, "0");
            DB.PutInformationPlayer(DB, "Oxana", "Hrehova", 9, "0", "0", "0", "0", "0", "0", "0", 8, "0");
            DB.PutInformationPlayer(DB, "Gretka", "Hrehova", 10, "0", "0", "0", "0", "0", "0", "0", 9, "0");
            DB.PutInformationPlayer(DB, "Zuzana", "Hrehova", 11, "0", "0", "0", "0", "0", "0", "0", 10, "0");
            DB.PutInformationPlayer(DB, "Magdalena", "Hrehova", 12, "0", "0", "0", "0", "0", "0", "0", 11, "0");
            DB.PutInformationPlayer(DB, "Lucia", "Hrehova", 13, "0", "0", "0", "0", "0", "0", "0", 12, "0");
        }
        /*TODO: END OF NOT DEPLOYMENT CODE*/

        ShowPlayers();
        ShowGoalkeepers();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                ChangeLayout();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ChangeLayout() {
        PlayersLayout = (RelativeLayout) findViewById(R.id.PlayersLayout);
        TableOfPlayersAndGoalkeepers = (RelativeLayout) findViewById(R.id.TableOfPlayersAndGoalkeepers);
        NewPlayerOrNewOfensiveFormation = (RelativeLayout) findViewById(R.id.NewPlayerOrNewOfensiveFormation);
        InsertPlayers = (RelativeLayout) findViewById(R.id.InsertPlayers);
        OfensiveFormationLayout = (RelativeLayout) findViewById(R.id.OfensiveFormationLayout);
        NewOrEditLineUp = (RelativeLayout) findViewById(R.id.NewOrEditLineUp);
        TableOfLineUps = (RelativeLayout) findViewById(R.id.TableOfLineUps);
        TableOfPlayers = (RelativeLayout) findViewById(R.id.TableOfPlayers);
        GlobalPlayers = (RelativeLayout) findViewById(R.id.GlobalPlayers);
        if(PlayersLayout.getVisibility() == View.VISIBLE) {
            if(NewPlayerOrNewOfensiveFormation.getVisibility() == View.VISIBLE)
                finish();
            else {
                if(InsertPlayers.getVisibility() == View.VISIBLE) {
                    InsertPlayers.setVisibility(View.GONE);
                    NewPlayerOrNewOfensiveFormation.setVisibility(View.VISIBLE);
                } else {
                    if(GlobalPlayers.getVisibility() == View.VISIBLE) {
                        GlobalPlayers.setVisibility(View.GONE);
                        ShowPlayers();
                        ShowGoalkeepers();
                        InsertPlayers.setVisibility(View.VISIBLE);
                    }
                }
            }
        } else {
            if(OfensiveFormationLayout.getVisibility() == View.VISIBLE) {
                OfensiveFormationLayout.setVisibility(View.GONE);
                PlayersLayout.setVisibility(View.VISIBLE);
                NewPlayerOrNewOfensiveFormation.setVisibility(View.VISIBLE);
                NewPlayer = (Button) findViewById(R.id.buttonNewPlayer);
                NewPlayer.setVisibility(View.VISIBLE);
                NewLineUp = (Button) findViewById(R.id.NewLineUp);
                NewLineUp.setVisibility(View.VISIBLE);
            }
        }
    }

    public void AddingPlayers() {
        PlayersFirstName = (EditText) findViewById(R.id.TurnamentName);
        PlayersSecondName = (EditText) findViewById(R.id.PlayersSecondName);
        PlayersNumber = (EditText) findViewById(R.id.edittextPlayersNumber);

        PlayersFirstName.setText("");
        PlayersSecondName.setText("");
        PlayersNumber.setText("");

        InsertPlayer = (Button) findViewById(R.id.buttonInsertPlayer);
        InsertPlayer.setText("Vlozit hraca");
        InsertPlayer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO: Chcek if all of iformation was inserted, chcek if is number of player uniq*/
                player_first_name = PlayersFirstName.getText().toString();
                player_second_name = PlayersSecondName.getText().toString();
                if (PlayersNumber.getText().toString().matches("^[0-9]+$"))
                    player_number = Integer.valueOf(PlayersNumber.getText().toString());
                else {
                    Toast.makeText(getBaseContext(), PlayersNumber.getText().toString() + " nie je cislo.", Toast.LENGTH_LONG).show();
                    PlayersNumber.setText("");
                    return;
                }
                Integer primary_key_player;

                Cursor CR = DB.GetInformationPlayer(DB, ConditionPlayer);
                if (CR.moveToFirst()) {
                    if (!CheckInsertingValues(CR, player_number)) {
                        Toast.makeText(getBaseContext(), "Hrac s cislom " + String.valueOf(player_number) + " uz existuje", Toast.LENGTH_LONG).show();
                        PlayersNumber.setText("");
                        return;
                    }
                    if (!CheckInsertingValues(DB.GetInformationGoalkeeper(DB, ConditionGoalkeeper), player_number)) {
                        Toast.makeText(getBaseContext(), "Brankar s cislom " + String.valueOf(player_number) + " uz existuje", Toast.LENGTH_LONG).show();
                        PlayersNumber.setText("");
                        return;
                    }
                    CR.moveToLast();
                    primary_key_player = CR.getInt(Constants.PRIMARY_KEY_FOR_PLAYER);
                    primary_key_player++;
                } else
                    primary_key_player = 1; /*TODO: Maybe is useless send every value as paramater, reconsider hard insert in function itself */
                DB.PutInformationPlayer(DB, player_first_name, player_second_name, player_number, "0", "0", "0", "0", "0", "0", "0", primary_key_player, TurnamentPK);
                Toast.makeText(getBaseContext(), "Player " + player_first_name + " " + player_second_name + " was inserted.", Toast.LENGTH_SHORT).show();
                ShowPlayers();
                PlayersFirstName.setText("");
                PlayersSecondName.setText("");
                PlayersNumber.setText("");
            }
        });
        InsertGoalkeeper = (Button) findViewById(R.id.NewGoalkeeper);
        InsertGoalkeeper.setText("Novy brankar");
        InsertGoalkeeper.setVisibility(View.VISIBLE);
        InsertGoalkeeper.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO: Chcek if all of iformation was inserted, chcek if is number of player uniq*/
                player_first_name = PlayersFirstName.getText().toString();
                player_second_name = PlayersSecondName.getText().toString();
                if (PlayersNumber.getText().toString().matches("^[0-9]+$"))
                    player_number = Integer.valueOf(PlayersNumber.getText().toString());
                else {
                    Toast.makeText(getBaseContext(), PlayersNumber.getText().toString() + " nie je cislo.", Toast.LENGTH_LONG).show();
                    PlayersNumber.setText("");
                    return;
                }
                if (!CheckInsertingValues(DB.GetInformationPlayer(DB, ConditionPlayer), player_number)) {
                    Toast.makeText(getBaseContext(), "Hrac s cislom " + String.valueOf(player_number) + " uz existuje", Toast.LENGTH_LONG).show();
                    PlayersNumber.setText("");
                    return;
                }
                if (!CheckInsertingValues(DB.GetInformationGoalkeeper(DB, ConditionGoalkeeper), player_number)) {
                    Toast.makeText(getBaseContext(), "Brankar s cislom " + String.valueOf(player_number) + " uz existuje", Toast.LENGTH_LONG).show();
                    PlayersNumber.setText("");
                    return;
                } /*TODO: Maybe is useless send every value as paramater, reconsider hard insert in function itself */
                DB.PutInformationGoalkeeper(DB, player_first_name, player_second_name, player_number, "0", "0", "0", "0", "0", 1, TurnamentPK);
                Toast.makeText(getBaseContext(), "Goalkeeper " + player_first_name + " " + player_second_name + " was inserted.", Toast.LENGTH_SHORT).show();
                ShowGoalkeepers();
                PlayersFirstName.setText("");
                PlayersSecondName.setText("");
                PlayersNumber.setText("");
            }
        });

        ImportPlayers = (Button) findViewById(R.id.ImportPlayers);
        if(!TurnamentPK.equals("0"))
            ImportPlayers.setVisibility(View.VISIBLE);
        ImportPlayers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImportPlayersFromGlobal();
            }
        });
    }

    public void ShowPlayers(){
        PlayersNumbers = (LinearLayout) findViewById(R.id.PlayersNumbers);
        PlayersNames = (LinearLayout) findViewById(R.id.PlayersNames);
        PlayersNumbers.removeAllViews();
        PlayersNames.removeAllViews();
        Cursor CR = DB.GetInformationPlayer(DB, ConditionPlayer + " GROUP BY " + TableData.TableInfo.Number);
        if(CR.moveToFirst()) {
            do {
                final TextView Number = new TextView(this);
                final TextView Name = new TextView(this);

                Number.setText(CR.getString(2));
                Number.setTextSize(TextSizeForScreens);
                PlayersNumbers.addView(Number);

                Name.setText(CR.getString(1) + " " + CR.getString(0));
                Name.setTextSize(TextSizeForScreens);
                Name.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        EditPlayerValues(Number.getText().toString());
                        return true;
                    }
                });
                PlayersNames.addView(Name);
            }while(CR.moveToNext());
            CR.close();
        }
    }

    public void ShowGoalkeepers(){
        GoalkeepersNumbers = (LinearLayout) findViewById(R.id.GoalkeepersNumbers);
        GoalkeepersNames = (LinearLayout) findViewById(R.id.GoalkeepersNames);
        GoalkeepersNumbers.removeAllViews();
        GoalkeepersNames.removeAllViews();
        Cursor CR = DB.GetInformationGoalkeeper(DB, ConditionGoalkeeper + " GROUP BY " + TableData.TableInfo.GoalkeeperNumberRoster);
        if(CR.moveToFirst()) {
            do {
                final TextView Number = new TextView(this);
                final TextView Name = new TextView(this);

                Number.setText(CR.getString(2));
                Number.setTextSize(TextSizeForScreens);
                GoalkeepersNumbers.addView(Number);

                Name.setText(CR.getString(1) + " " + CR.getString(0));
                Name.setTextSize(TextSizeForScreens);
                Name.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        EditGoalkeeperValues(Number.getText().toString());
                        return true;
                    }
                });
                GoalkeepersNames.addView(Name);
            }while(CR.moveToNext());
            CR.close();
        }
    }

    public void EditPlayerValues(final String Player) {
        ImportPlayers = (Button) findViewById(R.id.ImportPlayers);
        ImportPlayers.setVisibility(View.GONE);

        InsertPlayers = (RelativeLayout) findViewById(R.id.InsertPlayers);
        NewPlayerOrNewOfensiveFormation = (RelativeLayout) findViewById(R.id.NewPlayerOrNewOfensiveFormation);
        InsertPlayers.setVisibility(View.VISIBLE);
        NewPlayerOrNewOfensiveFormation.setVisibility(View.GONE);

        Button DeletePlayer = (Button) findViewById(R.id.NewGoalkeeper);
        DeletePlayer.setText("Vymazat");
        Button ConfirmChanges = (Button) findViewById(R.id.buttonInsertPlayer);
        ConfirmChanges.setText("Potvrd zmenu");

        PlayersFirstName = (EditText) findViewById(R.id.TurnamentName);
        PlayersSecondName = (EditText) findViewById(R.id.PlayersSecondName);
        PlayersNumber = (EditText) findViewById(R.id.edittextPlayersNumber);
        Cursor CR = DB.GetInformationPlayer(DB, " where " + TableData.TableInfo.Number + " = " + Player);
        CR.moveToLast();
        PlayersFirstName.setText(CR.getString(0));
        PlayersSecondName.setText(CR.getString(1));
        PlayersNumber.setText(Player);
        final Integer PkPlayerNumber = CR.getInt(Constants.PRIMARY_KEY_FOR_PLAYER);
        DeletePlayer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                DB.deletePlayer(DB, String.valueOf(PkPlayerNumber), TurnamentPK);
//                Toast.makeText(getBaseContext(), "Hrac s cislo " + Player + " bol vymazany.", Toast.LENGTH_LONG).show();
//                ShowPlayers();
//                ChangeLayout();
                /*TODO: change deleting players*/
            }
        });
        ConfirmChanges.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.UpdateInformationPlayer(DB, Player, TableData.TableInfo.FirstName, PlayersFirstName.getText().toString(), TurnamentPK);
                DB.UpdateInformationPlayer(DB, Player, TableData.TableInfo.SecondName, PlayersSecondName.getText().toString(), TurnamentPK);
                DB.UpdateInformationPlayer(DB, Player, TableData.TableInfo.Number, PlayersNumber.getText().toString(), TurnamentPK);
                Toast.makeText(getBaseContext(), "Zmena prevedena.", Toast.LENGTH_LONG).show();
                ShowPlayers();
                ChangeLayout();
            }
        });
    }

    public void EditGoalkeeperValues(final String Player) {
        ImportPlayers = (Button) findViewById(R.id.ImportPlayers);
        ImportPlayers.setVisibility(View.GONE);

        InsertPlayers = (RelativeLayout) findViewById(R.id.InsertPlayers);
        NewPlayerOrNewOfensiveFormation = (RelativeLayout) findViewById(R.id.NewPlayerOrNewOfensiveFormation);
        InsertPlayers.setVisibility(View.VISIBLE);
        NewPlayerOrNewOfensiveFormation.setVisibility(View.GONE);

        Button DeletePlayer = (Button) findViewById(R.id.NewGoalkeeper);
        DeletePlayer.setText("Vymazat");
        Button ConfirmChanges = (Button) findViewById(R.id.buttonInsertPlayer);
        ConfirmChanges.setText("Potvrd zmenu");

        PlayersFirstName = (EditText) findViewById(R.id.TurnamentName);
        PlayersSecondName = (EditText) findViewById(R.id.PlayersSecondName);
        PlayersNumber = (EditText) findViewById(R.id.edittextPlayersNumber);
        Cursor CR = DB.GetInformationGoalkeeper(DB, " where " + TableData.TableInfo.GoalkeeperNumberRoster + " = " + Player);
        CR.moveToFirst();
        PlayersFirstName.setText(CR.getString(0));
        PlayersSecondName.setText(CR.getString(1));
        PlayersNumber.setText(Player);
        final Integer PkPlayerNumber = CR.getInt(Constants.PRIMARY_KEY_FOR_PLAYER);
        DeletePlayer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                DB.deletePlayer(DB, String.valueOf(PkPlayerNumber), TurnamentPK);
//                Toast.makeText(getBaseContext(), "Hrac s cislo " + Player + " bol vymazany.", Toast.LENGTH_LONG).show();
//                ShowPlayers();
//                ChangeLayout();
                /*TODO: change deleting players*/
            }
        });
        ConfirmChanges.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.UpdateGoalkeeperInformations(DB, Player, TableData.TableInfo.GoalkeeperFirstNameRoster, PlayersFirstName.getText().toString(), TurnamentPK);
                DB.UpdateGoalkeeperInformations(DB, Player, TableData.TableInfo.GoalkeeperSecondNameRoster, PlayersSecondName.getText().toString(), TurnamentPK);
                DB.UpdateGoalkeeperInformations(DB, Player, TableData.TableInfo.GoalkeeperNumberRoster, PlayersNumber.getText().toString(), TurnamentPK);
                Toast.makeText(getBaseContext(), "Zmena prevedena.", Toast.LENGTH_LONG).show();
                ShowGoalkeepers();
                ChangeLayout();
            }
        });
    }

    public boolean CheckInsertingValues(Cursor CR, Integer Number) {
        if(CR.moveToFirst()) {
            do {
                if (Number == Integer.parseInt(CR.getString(2)))
                    return false;
            } while (CR.moveToNext());
        }
        return true;
    }

    public void ImportPlayersFromGlobal() {
        GlobalPlayers = (RelativeLayout) findViewById(R.id.GlobalPlayers);
        GlobalPlayers.setVisibility(View.VISIBLE);
        InsertPlayers = (RelativeLayout) findViewById(R.id.InsertPlayers);
        InsertPlayers.setVisibility(View.GONE);

        ShowPlayersFromGlobalList();
        ShowPlayersForGlobalAdding();

        ShowGoalkeepersFromGlobalList();
        ShowGoalkeepersForGlobalAdding();
    }

    public void ShowPlayersFromGlobalList() {
        LinearLayout ListOfGlobalPlayersNumbers = (LinearLayout) findViewById(R.id.ListOfGlobalPlayersNumbers);
        LinearLayout ListOfGlobalPlayersNames = (LinearLayout) findViewById(R.id.ListOfGlobalPlayersNames);
        ListOfGlobalPlayersNumbers.removeAllViews();
        ListOfGlobalPlayersNames.removeAllViews();
        Cursor CR = DB.GetInformationPlayer(DB, " WHERE " + TableData.TableInfo.TurnamentPKRoster + " == " + "0" + " GROUP BY " + TableData.TableInfo.Number);
        if(CR.moveToFirst()) {
            do {
                final TextView Number = new TextView(this);
                final TextView Name = new TextView(this);

                Number.setText(CR.getString(2));
                Number.setTextSize(TextSizeForScreens);
                ListOfGlobalPlayersNumbers.addView(Number);

                Name.setText(CR.getString(1) + " " + CR.getString(0));
                Name.setTextSize(TextSizeForScreens);
                Name.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(CheckPlayersForDuplicity(Number.getText().toString())) {
                            DB.PutInformationPlayer(DB, Name.getText().toString().split(" ")[1], Name.getText().toString().split(" ")[0], Integer.parseInt(Number.getText().toString()), "0", "0", "0", "0", "0", "0", "0",
                                    GetPlayersPrimaryKey(), TurnamentPK);
                            Number.setVisibility(View.GONE);
                            Name.setVisibility(View.GONE);
                            ShowPlayersForGlobalAdding();
                        } else
                            Toast.makeText(getBaseContext(), "Hrac s tymto cislom uz na supiske je.", Toast.LENGTH_LONG).show();
                    }
                });
                ListOfGlobalPlayersNames.addView(Name);
            }while(CR.moveToNext());
            CR.close();
        }
    }

    public void ShowGoalkeepersFromGlobalList(){
        LinearLayout ListOfGlobalGoalkeepersNumbers = (LinearLayout) findViewById(R.id.ListOfGlobalGoalkeepersNumbers);
        LinearLayout ListOfGlobalGoalkeepersNames = (LinearLayout) findViewById(R.id.ListOfGlobalGoalkeepersNames);
        ListOfGlobalGoalkeepersNumbers.removeAllViews();
        ListOfGlobalGoalkeepersNames.removeAllViews();
        Cursor CR = DB.GetInformationGoalkeeper(DB, " WHERE " + TableData.TableInfo.TurnamentGoalkeeperPKRoster + " == " + "0" + " GROUP BY " + TableData.TableInfo.GoalkeeperNumberRoster);
        if(CR.moveToFirst()) {
            do {
                final TextView Number = new TextView(this);
                final TextView Name = new TextView(this);

                Number.setText(CR.getString(2));
                Number.setTextSize(TextSizeForScreens);
                ListOfGlobalGoalkeepersNumbers.addView(Number);

                Name.setText(CR.getString(1) + " " + CR.getString(0));
                Name.setTextSize(TextSizeForScreens);
                Name.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DB.PutInformationGoalkeeper(DB, Name.getText().toString().split(" ")[1], Name.getText().toString().split(" ")[0], Integer.parseInt(Number.getText().toString()), "0", "0", "0", "0", "0",
                                1, TurnamentPK);
                        Number.setVisibility(View.GONE);
                        Name.setVisibility(View.GONE);
                        ShowGoalkeepersForGlobalAdding();
                    }
                });
                ListOfGlobalGoalkeepersNames.addView(Name);
            }while(CR.moveToNext());
            CR.close();
        }
    }

    public boolean CheckPlayersForDuplicity(String Number) {
        Cursor CR = DB.GetInformationPlayer(DB, ConditionPlayer);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(Constants.NUMBER).equals(Number))
                    return false;
            }while(CR.moveToNext());
            CR.close();
        }
        CR = DB.GetInformationGoalkeeper(DB, ConditionGoalkeeper);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(Constants.GOALKEEPER_NUMBER).equals(Number))
                    return false;
            }while(CR.moveToNext());
            CR.close();
        }
        return true;
    }

    public int GetPlayersPrimaryKey() {
        Cursor CR = DB.GetInformationPlayer(DB, ConditionPlayer);
        if(CR.moveToLast())
            return CR.getInt(Constants.PRIMARY_KEY_FOR_PLAYER);
        return 0;
    }

    public void ShowPlayersForGlobalAdding() {
        PlayersNumbers = (LinearLayout) findViewById(R.id.PlayersNumbers);
        PlayersNames = (LinearLayout) findViewById(R.id.PlayersNames);
        PlayersNumbers.removeAllViews();
        PlayersNames.removeAllViews();
        Cursor CR = DB.GetInformationPlayer(DB, ConditionPlayer + " GROUP BY " + TableData.TableInfo.Number);
        if(CR.moveToFirst()) {
            do {
                final TextView Number = new TextView(this);
                final TextView Name = new TextView(this);

                Number.setText(CR.getString(Constants.NUMBER));
                Number.setTextSize(TextSizeForScreens);
                PlayersNumbers.addView(Number);

                Name.setText(CR.getString(Constants.SECOND_NAME) + " " + CR.getString(Constants.FIRST_NAME));
                Name.setTextSize(TextSizeForScreens);
                Name.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DB.DeleteInformationPlayer(DB, Number.getText().toString(), TurnamentPK);
                        Number.setVisibility(View.GONE);
                        Name.setVisibility(View.GONE);
                        ShowPlayersFromGlobalList();
                    }
                });
                PlayersNames.addView(Name);
            }while(CR.moveToNext());
            CR.close();
        }
    }

    public void ShowGoalkeepersForGlobalAdding(){
        GoalkeepersNumbers = (LinearLayout) findViewById(R.id.GoalkeepersNumbers);
        GoalkeepersNames = (LinearLayout) findViewById(R.id.GoalkeepersNames);
        GoalkeepersNumbers.removeAllViews();
        GoalkeepersNames.removeAllViews();
        Cursor CR = DB.GetInformationGoalkeeper(DB, ConditionGoalkeeper + " GROUP BY " + TableData.TableInfo.GoalkeeperNumberRoster);
        if(CR.moveToFirst()) {
            do {
                final TextView Number = new TextView(this);
                final TextView Name = new TextView(this);

                Number.setText(CR.getString(2));
                Number.setTextSize(TextSizeForScreens);
                GoalkeepersNumbers.addView(Number);

                Name.setText(CR.getString(1) + " " + CR.getString(0));
                Name.setTextSize(TextSizeForScreens);
                Name.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DB.deleteGoalkeeper(DB, Number.getText().toString(), TurnamentPK);
                        Number.setVisibility(View.GONE);
                        Name.setVisibility(View.GONE);
                        ShowGoalkeepersFromGlobalList();
                    }
                });
                GoalkeepersNames.addView(Name);
            }while(CR.moveToNext());
            CR.close();
        }
    }

    public void CreatePackForOfensive() {
        PlayersLayout = (RelativeLayout) findViewById(R.id.PlayersLayout);
        PlayersLayout.setVisibility(View.GONE);
        OfensiveFormationLayout = (RelativeLayout) findViewById(R.id.OfensiveFormationLayout);
        OfensiveFormationLayout.setVisibility(View.VISIBLE);

        ShowLineUpLayout();
        LineUpName = (EditText) findViewById(R.id.LineUpName);
        LineUpName.setVisibility(View.GONE);
        NewPlayer = (Button) findViewById(R.id.buttonNewPlayer);
        NewPlayer.setVisibility(View.GONE);
        NewLineUp = (Button) findViewById(R.id.NewLineUp);
        NewLineUp.setVisibility(View.GONE);
        InsertLineUpButton = (Button) findViewById(R.id.InsertLineUpButton);
        InsertLineUpButton.setVisibility(View.GONE);
        Cursor CR = DB.getInformationGameOption(DB, "");
        if(CR.moveToFirst()) {
            CR.moveToLast();
            //MaxNumberOfPlayersInLineUp = Integer.parseInt(CR.getString(0));
        }
        ListOfPlayer = (LinearLayout) findViewById(R.id.ListOfPlayer);
        ListOfPlayer.removeAllViewsInLayout();
        LayoutForLineUp = (LinearLayout) findViewById(R.id.LayoutForLineUp);
        CR = DB.GetInformationPlayer(DB, " GROUP BY " + TableData.TableInfo.Number);
        if(CR.moveToFirst()) {
            do {
                final TextView PLAYER = new TextView(this);
                PLAYER.setText(CR.getString(Constants.NUMBER) + " " + CR.getString(Constants.SECOND_NAME) + " " + CR.getString(Constants.FIRST_NAME));
                PLAYER.setTextSize(TextSizeForScreens);
                PLAYER.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (CountVisible() == MaxNumberOfPlayersInLineUp) {
                            Toast.makeText(getBaseContext(), "Mate maximalne pocet hracou.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        final TextView NewPlayerInLinUp = new TextView(CTX);
                        NewPlayerInLinUp.setTextSize(TextSizeForScreens);
                        NewPlayerInLinUp.setText(PLAYER.getText().toString());
                        NewPlayerInLinUp.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PLAYER.setVisibility(View.VISIBLE);
                                NewPlayerInLinUp.setVisibility(View.GONE);
                                InsertLineUpButton.setVisibility(View.GONE);
                                LineUpName.setVisibility(View.GONE);
                            }
                        });
                        LayoutForLineUp.addView(NewPlayerInLinUp);
                        PLAYER.setVisibility(View.GONE);
                        if (CountVisible() == MaxNumberOfPlayersInLineUp) {
                            InsertLineUpButton.setVisibility(View.VISIBLE);
                            LineUpName.setVisibility(View.VISIBLE);
                        }
                    }
                });
                ListOfPlayer.addView(PLAYER);
            } while (CR.moveToNext());
        }
        InsertLineUpButton.setText("Vlozit Utok");
        InsertLineUpButton.setTextSize(TextSizeForScreens);
        InsertLineUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Output = CheckLineUpForDuplicity();
                if(!Output.equals("AllGood")) {
                    Toast.makeText(getBaseContext(), "Utok v tomto zlozeni uz existuje.", Toast.LENGTH_LONG).show();
                    ShowPlayersInLineUp(Output);
                    return;
                }
                if (LineUpName.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Nazov utoku je povinny.", Toast.LENGTH_LONG).show();
                    return;
                }
                Cursor CRLineUp = DB.getInformationLineUp(DB, ConditionLineUp);
                if (CRLineUp.moveToFirst()) {
                    do {
                        if (CRLineUp.getString(0).split("#")[0].equals(LineUpName.getText().toString())) {
                            Toast.makeText(getBaseContext(), "Meno utoku musi byt unikatne.", Toast.LENGTH_LONG).show();
                            LineUpName.setText("");
                            return;
                        }
                    } while (CRLineUp.moveToNext());
                }
                InsertLineUp();
            }
        });
    }

    public void InsertLineUp() {
        Cursor CR = DB.getInformationLineUp(DB, ConditionLineUp);
        LineUpString = LineUpString + LineUpName.getText().toString() + "#";
        for(int i=0;i<LayoutForLineUp.getChildCount();i++) {
            if(LayoutForLineUp.getChildAt(i).getVisibility() == View.VISIBLE) {
                TextView OnePlayer = (TextView)LayoutForLineUp.getChildAt(i);
                LineUpString = LineUpString + OnePlayer.getText().toString().split(" ")[0] + "#";
            }
        }
        if(CR.moveToFirst()) {
            CR.moveToLast();
            DB.putInformationLineUp(DB, LineUpString, String.valueOf(Integer.parseInt(CR.getString(1) + 1)), TurnamentPK);
        } else
            DB.putInformationLineUp(DB, LineUpString, "0", TurnamentPK);
        LayoutForLineUp.removeAllViewsInLayout();
        LineUpString = "";
        InsertLineUpButton.setVisibility(View.GONE);
        LineUpName.setVisibility(View.GONE);
        CreatePackForOfensive();
    }

    public void ShowLineUpLayout() {
        NewPlayer = (Button) findViewById(R.id.buttonNewPlayer);
        NewPlayer.setVisibility(View.GONE);
        NewLineUp = (Button) findViewById(R.id.NewLineUp);
        NewLineUp.setVisibility(View.GONE);
        ListOfLineUp = (LinearLayout) findViewById(R.id.ListOfLineUp);
        ListOfLineUp.removeAllViewsInLayout();
        final Cursor CR = DB.getInformationLineUp(DB, ConditionLineUp);
        if(CR.moveToFirst()) {
            do {
                final TextView OneLineUp = new TextView(CTX);
                OneLineUp.setText(CR.getString(0).split("#")[0]);
                OneLineUp.setTextSize(TextSizeForScreens);
                OneLineUp.setGravity(Gravity.CENTER);
                OneLineUp.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowPlayersInLineUp(OneLineUp.getText().toString());
                    }
                });
                OneLineUp.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ShowLineUpForEditing(OneLineUp.getText().toString());
                        return true;
                    }
                });
                ListOfLineUp.addView(OneLineUp);
                for (int i = 1; i <= 6; i++) {
                    final TextView OnePlayerInLineUp = new TextView(CTX);
                    Cursor CRPlayer = DB.GetInformationPlayer(DB, "");
                    if(CRPlayer.moveToFirst()) {
                        do {
                            if(CR.getString(0).split("#")[i].equals(CRPlayer.getString(Constants.NUMBER)))
                                OnePlayerInLineUp.setText(CRPlayer.getString(Constants.NUMBER) + " " + CRPlayer.getString(Constants.SECOND_NAME) + " " + CRPlayer.getString(Constants.FIRST_NAME));
                        }while(CRPlayer.moveToNext());
                    }
                    OnePlayerInLineUp.setTextSize(TextSizeForScreens);
                    OnePlayerInLineUp.setGravity(Gravity.CENTER);
                    OnePlayerInLineUp.setVisibility(View.GONE);
                    ListOfLineUp.addView(OnePlayerInLineUp);
                }
            }while(CR.moveToNext());
        }
        ShowPlayers();
    }

    public void ShowPlayersInLineUp(String NameOfLineUp) {
        ListOfLineUp = (LinearLayout) findViewById(R.id.ListOfLineUp);
        for(int i = 0; i< ListOfLineUp.getChildCount(); i++) {
            if(((TextView)ListOfLineUp.getChildAt(i)).getText().toString().equals(NameOfLineUp)) {
                if(ListOfLineUp.getChildAt(i+1).getVisibility() == View.GONE) {
                    for(int j = i; j <= i+6; j++) {
                        ListOfLineUp.getChildAt(j).setVisibility(View.VISIBLE);
                    }
                } else {
                    for(int j = i+1; j <= i+6; j++) {
                        ListOfLineUp.getChildAt(j).setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    public void ShowLineUpForEditing(final String NameOfLineUp) {
        Cursor CR = DB.getInformationGameOption(DB, "");
        if(CR.moveToFirst()) {
            CR.moveToLast();
            MaxNumberOfPlayersInLineUp = Integer.parseInt(CR.getString(0));
        }
        ListOfPlayer = (LinearLayout) findViewById(R.id.ListOfPlayer);
        LineUpName = (EditText) findViewById(R.id.LineUpName);
        LineUpName.setText(NameOfLineUp);
        CR = DB.getInformationLineUp(DB, ConditionLineUp);
        if(CR.moveToFirst()) {
            do {
                if(CR.getString(0).split("#")[0].equals(LineUpName.getText().toString()))
                    LineUpPK = CR.getString(1);
            }while(CR.moveToNext());
        }
        LineUpName.setVisibility(View.VISIBLE);
        InsertLineUpButton = (Button) findViewById(R.id.InsertLineUpButton);
        InsertLineUpButton.setText("Vloz zmeny");
        InsertLineUpButton.setVisibility(View.VISIBLE);
        final LinearLayout LayoutForLineUp = (LinearLayout) findViewById(R.id.LayoutForLineUp);
        LayoutForLineUp.removeAllViewsInLayout();
        ListOfLineUp.removeAllViewsInLayout();
        ShowLineUpLayout();
        for(int k = 0; k < ListOfLineUp.getChildCount(); k++) {
            if(((TextView)ListOfLineUp.getChildAt(k)).getText().toString().equals(NameOfLineUp))
                ListOfLineUp.getChildAt(k).setVisibility(View.GONE);
        }
        ListOfPlayer.removeAllViewsInLayout();
        CR = DB.GetInformationPlayer(DB, "");
        if(CR.moveToFirst()) {
            do {
                final TextView OnePlayer = new TextView(CTX);
                OnePlayer.setText(CR.getString(Constants.NUMBER) + " " + CR.getString(Constants.SECOND_NAME) + " " + CR.getString(Constants.FIRST_NAME));
                OnePlayer.setTextSize(TextSizeForScreens);
                OnePlayer.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int k = 0; k < LayoutForLineUp.getChildCount(); k++) {
                            if (CountVisible() == MaxNumberOfPlayersInLineUp) {
                                Toast.makeText(getBaseContext(), "Maxialny pocet hracou v utoku.", Toast.LENGTH_LONG).show();
                                break;
                            }
                            OnePlayer.setVisibility(View.GONE);
                            if (OnePlayer.getText().toString().equals(((TextView) LayoutForLineUp.getChildAt(k)).getText().toString())) {
                                LayoutForLineUp.getChildAt(k).setVisibility(View.VISIBLE);
                            } else {
                                final TextView NewPlayerInLineUp = new TextView(CTX);
                                NewPlayerInLineUp.setText(OnePlayer.getText().toString());
                                NewPlayerInLineUp.setTextSize(TextSizeForScreens);
                                NewPlayerInLineUp.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        for(int k = 0; k < ListOfPlayer.getChildCount(); k++) {
                                            if(((TextView)ListOfPlayer.getChildAt(k)).getText().toString().equals(NewPlayerInLineUp.getText().toString()))
                                                ListOfPlayer.getChildAt(k).setVisibility(View.VISIBLE);
                                        }
                                        NewPlayerInLineUp.setVisibility(View.GONE);
                                    }
                                });
                                LayoutForLineUp.addView(NewPlayerInLineUp);
                                break;
                            }
                        }
                    }
                });
                ListOfPlayer.addView(OnePlayer);
            }while(CR.moveToNext());
        }
        for(int i = 0; i< ListOfLineUp.getChildCount(); i++) {
            if(((TextView)ListOfLineUp.getChildAt(i)).getText().toString().equals(NameOfLineUp)) {
                for(int j = i+1; j <= i+6; j++) {
                    TextView OnePlayerFromLineUp = (TextView)ListOfLineUp.getChildAt(j);
                    final TextView NewChildForEdit = new TextView(CTX);
                    NewChildForEdit.setTextSize(TextSizeForScreens);
                    NewChildForEdit.setText(OnePlayerFromLineUp.getText().toString());
                    for(int k = 0; k < ListOfPlayer.getChildCount(); k++) {
                        if(ListOfPlayer.getChildAt(k).getVisibility() == View.VISIBLE && ((TextView)ListOfPlayer.getChildAt(k)).getText().toString().equals(NewChildForEdit.getText().toString()))
                            ListOfPlayer.getChildAt(k).setVisibility(View.GONE);
                    }
                    NewChildForEdit.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(int k = 0; k < ListOfPlayer.getChildCount(); k++) {
                                if(ListOfPlayer.getChildAt(k).getVisibility() == View.GONE && ((TextView)ListOfPlayer.getChildAt(k)).getText().toString().equals(NewChildForEdit.getText().toString())) {
                                    ListOfPlayer.getChildAt(k).setVisibility(View.VISIBLE);
                                    NewChildForEdit.setVisibility(View.GONE);
                                }
                            }
                        }
                    });
                    LayoutForLineUp.addView(NewChildForEdit);
                }
            }
        }
        InsertLineUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CountVisible() == MaxNumberOfPlayersInLineUp) {
                    final String Output = CheckLineUpForDuplicity();
                    if(!Output.equals("AllGood")) {
                        Toast.makeText(getBaseContext(), "Utok v tomto zlozeni uz existuje.", Toast.LENGTH_LONG).show();
                        ShowPlayersInLineUp(Output);
                        return;
                    }
                    UpdateLineUp();
                }
                else
                    Toast.makeText(getBaseContext(), "Pocet hracou musi byt: " + String.valueOf(MaxNumberOfPlayersInLineUp), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void UpdateLineUp() {
        LayoutForLineUp = (LinearLayout) findViewById(R.id.LayoutForLineUp);
        LineUpString = LineUpString + LineUpName.getText().toString() + "#";
        for(int i=0;i<LayoutForLineUp.getChildCount();i++) {
            if(LayoutForLineUp.getChildAt(i).getVisibility() == View.VISIBLE) {
                TextView OnePlayer = (TextView)LayoutForLineUp.getChildAt(i);
                LineUpString = LineUpString + OnePlayer.getText().toString().split(" ")[0] + "#";
            }
        }
        DB.updateLineUp(DB, TableData.TableInfo.LineUp, LineUpString, LineUpPK, TurnamentPK);
        LineUpString = "";
        LayoutForLineUp.removeAllViewsInLayout();
        LineUpName.setText("");
        LineUpName.setVisibility(View.GONE);
        InsertLineUpButton.setVisibility(View.GONE);
        ShowLineUpLayout();
        CreatePackForOfensive();
    }

    private int CountVisible() {
        LayoutForLineUp = (LinearLayout) findViewById(R.id.LayoutForLineUp);
        if(LayoutForLineUp == null)
            return 0;
        int Count = 0;
        for(int i=0; i<LayoutForLineUp.getChildCount(); i++) {
            if(LayoutForLineUp.getChildAt(i).getVisibility() == View.VISIBLE)
                Count++;
        }
        return Count;
    }

    public String CheckLineUpForDuplicity() {
        LayoutForLineUp = (LinearLayout) findViewById(R.id.LayoutForLineUp);
        Integer CountOfConfirm = 0;
        Cursor CR = DB.getInformationLineUp(DB, ConditionLineUp);
        if(CR.moveToFirst()) {
            do {
                CountOfConfirm = 0;
                for(int i=0;i<LayoutForLineUp.getChildCount();i++) {
                    for(int j = 1; j <= 6; j++) {
                        if (LayoutForLineUp.getChildAt(i).getVisibility() == View.VISIBLE && ((TextView)LayoutForLineUp.getChildAt(i)).getText().toString().split(" ")[0].equals(CR.getString(0).split("#")[j])) {
                            CountOfConfirm++;
                            if(CountOfConfirm == 6) {
                                return CR.getString(0).split("#")[0];
                            }
                            break;
                        }
                    }

                }
            }while(CR.moveToNext());
        }
        return "AllGood";
    }
}
