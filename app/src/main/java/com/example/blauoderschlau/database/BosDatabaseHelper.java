package com.example.blauoderschlau.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.model.Answer;
import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class BosDatabaseHelper extends SQLiteOpenHelper
{


    //---------------------------------------------------------------------------------------
    //SINGLETON------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------



    private static BosDatabaseHelper instance ;

    public static synchronized BosDatabaseHelper getInstance(Context context)
    {
        if(instance == null) instance = new BosDatabaseHelper(context.getApplicationContext());
        return instance;
    }


    //----------------------------------------------------------------------------------------
    //DATABASE INFOS--------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------

    //Database Info
    private static final String DATABASE_NAME = "bosDatabase.db";
    private static final int DATABASE_VERSION = 1;

    //TABLES
    private static final String TABLE_QUESTIONS = "questions";
    private static final String TABLE_SCORE_HISTORY = "history";
    private static final String TABLE_ANSWERS= "answers";

    //QUESTION Table Columns
    private static final String KEY_QUESTION_ID = "id";
    private static final String KEY_QUESTION_TEXT = "text";

    //ANSWER Table Columns
    private static final String KEY_ANSWER_ID = "id";
    private static final String KEY_ANSWER_TEXT = "answer";
    private static final String KEY_ANSWER_CORRECT = "correct";
    private static final String KEY_ANSWER_QUESTION_ID_FK = "questionId";

    //HISTORY Table Columns
    private static final String KEY_HISTORY_ID = "id";
    private static final String KEY_HISTORY_PERMILL ="permill";
    private static final String KEY_HISTORY_DATE = "date";


    //------------------------------------------------------------------------------------------
    //INIT DATABASE-----------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------


    //constructor iz private
    private BosDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    @Override
    public void onCreate(SQLiteDatabase db) //läuft
    {

        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +  TABLE_QUESTIONS +
                "(" +
                    KEY_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_QUESTION_TEXT + " TEXT " +
                ")";

        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_SCORE_HISTORY +
                "("+
                    KEY_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    KEY_HISTORY_DATE + " TEXT, " +
                    KEY_HISTORY_PERMILL + " REAL "+
                ")";

        String CREATE_ANSWER_TABLE = "CREATE TABLE " + TABLE_ANSWERS +
                "("+
                    KEY_ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_ANSWER_QUESTION_ID_FK + " INTEGER REFERENCES " + TABLE_QUESTIONS + "," +
                    KEY_ANSWER_TEXT + " TEXT, " +
                    KEY_ANSWER_CORRECT + " INTEGER " +
                ")";

        db.execSQL(CREATE_HISTORY_TABLE);
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_ANSWER_TABLE);

        //ADDING ALL QUESTIONUNITS TO DATABASE---------------------------------------------
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }







}
