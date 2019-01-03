package com.example.blauoderschlau.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BusDatabaseHelper extends SQLiteOpenHelper {

    //Database Info
    private static final String DATABASE_NAME = "busDatabase";
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String TABLE_QUESTIONS = "questions";
    private static final String TABLE_SCORE_HISTORY = "history";

    //Question Table Columns
    private static final String KEY_QUESTION_ID = "id";
    private static final String KEY_QUESTION_TEXT = "text";
    private static final String KEY_QUESTION_ANSWER_A = "A";
    private static final String KEY_QUESTION_ANSWER_B = "B";
    private static final String KEY_QUESTION_ANSWER_C = "C";
    private static final String KEY_QUESTION_ANSWER_D = "D";
    private static final String KEY_QUESTION_CORRECT_ANSWER = "correct";

    //Question History Table




    public BusDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
