package com.example.blauoderschlau.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BosDatabaseHelper extends SQLiteOpenHelper implements DatabaseManagerContract.Model
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

    //---------------------------------------------------------------------------------------
    // QUESTIONS ----------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------

    private static List<QuestionUnit> questionUnits = new ArrayList<>();

    static {
        String q0 = "Was ist 1+1?";
        Answer[] ao0 = new Answer[]{
                new Answer("0", false),
                new Answer("1", false),
                new Answer("2", true),
                new Answer("3", false)};
        QuestionUnit questionUnit0 = new QuestionUnit(q0, ao0);

        String q1 = "Wie heißt die Mutter von Niki Lauda?";
        Answer[] ao1 = new Answer[]{
                new Answer("Sebastian Vettel", false),
                new Answer("Mama Lauda", true),
                new Answer("Toto Wolff", false),
                new Answer("Steve Jobs", false)};
        QuestionUnit questionUnit1 = new QuestionUnit(q1, ao1);

        String q2 = "Klicke auf Deutschland";
        Answer[] ao2 = new Answer[]{
                new Answer("Deutschland", true),
                new Answer("Klick", false),
                new Answer("Ich bin nicht betrunken", false),
                new Answer("Bayern", false)};
        QuestionUnit questionUnit2 = new QuestionUnit(q2, ao2);

        String q3 = "Klicke auf Deutschland";
        Answer[] ao3 = new Answer[]{
                new Answer("Duetschland", false),
                new Answer("Deustchland", false),
                new Answer("Deutschalnd", false),
                new Answer("Deutschland", true)};
        QuestionUnit questionUnit3 = new QuestionUnit(q3, ao3);

        String q4 = "Wähle die Schaltfläche mit dem Text \"oben rechts\"";
        Answer[] ao4 = new Answer[]{
                new Answer("oben rechts", true),
                new Answer("unten links", false),
                new Answer("oben links", false),
                new Answer("unten rechts", false)};
        QuestionUnit questionUnit4 = new QuestionUnit(q4, ao4);

        String q5 = "Wie viele Wörter beinhaltet diese Frage?";
        Answer[] ao5 = new Answer[]{
                new Answer("4", false),
                new Answer("5", false),
                new Answer("6", true),
                new Answer("7", false)};
        QuestionUnit questionUnit5 = new QuestionUnit(q5, ao5);

        String q6 = "Wie viele Plus-Zeichen siehst du?\n++++++";
        Answer[] ao6 = new Answer[]{
                new Answer("6", true),
                new Answer("7", false),
                new Answer("8", false),
                new Answer("9", false)};
        QuestionUnit questionUnit6 = new QuestionUnit(q6, ao6);

        String q7 = "Wo zeigt die Mehrheit der Pfeile nach rechts?";
        Answer[] ao7 = new Answer[]{
                new Answer(">><", true),
                new Answer("<><", false),
                new Answer("><<", false),
                new Answer("AfD", false)};
        QuestionUnit questionUnit7 = new QuestionUnit(q7, ao7);

        String q8 = "Wie lustig fandest du die letzte Frage?";
        Answer[] ao8 = new Answer[]{
                new Answer("Allerhöchstens Schenkelklopfer", false),
                new Answer("Ziemlich Arm", false),
                new Answer("Sehr Bein", false),
                new Answer("Höchst amüsant", true)};
        QuestionUnit questionUnit8 = new QuestionUnit(q8, ao8);

        String q9 = "Wie viel Sterne würdest du dieser App geben?";
        Answer[] ao9 = new Answer[]{
                new Answer("Einen", false),
                new Answer("Zwei", false),
                new Answer("Drei", false),
                new Answer("Fünf", true)};
        QuestionUnit questionUnit9 = new QuestionUnit(q9, ao9);

        String q10 = "Was ist aus medizinischer Sicht kein Affe?";
        Answer[] ao10 = new Answer[]{
                new Answer("Orang-Utan", false),
                new Answer("Schimpanse", false),
                new Answer("Kapuzineräffchen", false),
                new Answer("Oliver Kahn", true)};
        QuestionUnit questionUnit10 = new QuestionUnit(q10, ao10);

        String q11 = "Wer sollte sich noch weitere Fragen ausdenken?";
        Answer[] ao11 = new Answer[]{
                new Answer("Jan und Jonas", false),
                new Answer("Jonathan und Jonas", false),
                new Answer("nur Jonas", true),
                new Answer("Jan und Jonathan", false)};
        QuestionUnit questionUnit11 = new QuestionUnit(q11, ao11);


        questionUnits.add(questionUnit0);
        questionUnits.add(questionUnit1);
        questionUnits.add(questionUnit2);
        questionUnits.add(questionUnit3);
        questionUnits.add(questionUnit4);
        questionUnits.add(questionUnit5);
        questionUnits.add(questionUnit6);
        questionUnits.add(questionUnit7);
        questionUnits.add(questionUnit8);
        questionUnits.add(questionUnit9);
        questionUnits.add(questionUnit10);
        questionUnits.add(questionUnit11);
    }


    //----------------------------------------------------------------------------------------
    //DATABASE INFOS--------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------

    //Database Info
    private static final String DATABASE_NAME = "busDatabase";
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
    public void onCreate(SQLiteDatabase db)
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
        addQuestionUnits(questionUnits);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    //-----------------------------------------------------------------------------------------
    // CRUD OPERATIONS ------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------

    private void addQuestionUnits(List<QuestionUnit> questions)
    {
        for(QuestionUnit q : questions)
        {
            addQuestionUnit(q);
        }
    }


    //TO ADD A QUESTION UNIT TO DB
    private void  addQuestionUnit(QuestionUnit questionUnit)
    {
        int questionId = addQuestion(questionUnit.getQ());

        addAnswers(questionUnit.getAnswers(),questionId);
    }


    //answers may only have 4 elements
    private void addAnswers(Answer[] answers , int questionId)
    {
        if(answers.length == 4)
        {
            for (int i = 0; i < 4; i++) {
                addAnswer(answers[i], questionId);
            }
        }
    }


    //more than 4 answers can be assigned to a question
    //updating answer is not not really necessary
    private void addAnswer(Answer answer, int questionId)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try
        {

            ContentValues values = new ContentValues();
            values.put(KEY_ANSWER_TEXT, answer.getText());
            values.put(KEY_ANSWER_CORRECT,answer.isCorrect());
            values.put(KEY_ANSWER_QUESTION_ID_FK, questionId);


            db.insertOrThrow(TABLE_ANSWERS, null,  values);
            db.setTransactionSuccessful();
        }catch(Exception e)
        {
            Log.d(TAG, "ERROR WHILE INSERTING QUESTION");
        }finally
        {
            db.endTransaction();
        }

    }



    private int addQuestion(String question) //returns question ID
    {

        SQLiteDatabase db = getWritableDatabase();
        int questionId = -1;
        db.beginTransaction();

        try
        {
            ContentValues values = new ContentValues();
            values.put(KEY_QUESTION_TEXT, question);

            db.insertOrThrow(TABLE_QUESTIONS, null, values);
            db.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            Log.d(TAG, "ERROR WHILE INSERTING QUESTION");
        }
        finally
        {
            db.endTransaction();
        }

        String sql = String.format("SELECT %s FROM %s WHERE %s = ?", KEY_QUESTION_ID, TABLE_QUESTIONS, KEY_QUESTION_TEXT);
        Cursor cursor = db.rawQuery(sql, new String[]{question});

        try
        {
            if(cursor.moveToFirst())
            {
                int index = cursor.getColumnIndex(KEY_QUESTION_ID);
                questionId = cursor.getInt(index);
                db.setTransactionSuccessful();
            }
        }finally
        {
            if(cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }
        return questionId;
    }



    private void addScore(Game game)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try
        {
            ContentValues values = new ContentValues();
            values.put(KEY_HISTORY_DATE, game.getDateAsString());
            values.put(KEY_HISTORY_PERMILL, game.getPerMill());

            db.insertOrThrow(TABLE_SCORE_HISTORY, null, values);
            db.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            Log.d(TAG, "ERROR WHILE INSERTING SCORE");
        }
        finally
        {
            db.endTransaction();
        }

    }

    private List<Game> getAllScores()
    {
        List<Game> games = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = String.format("SELECT %s,%s FROM %s", KEY_HISTORY_DATE, KEY_HISTORY_PERMILL, TABLE_SCORE_HISTORY);
        Cursor cursor = db.rawQuery(sql, null);
        try
        {
            if(cursor.moveToFirst())
            {
                int indexPerMill = cursor.getColumnIndex(KEY_HISTORY_PERMILL);
                int indexDate = cursor.getColumnIndex(KEY_HISTORY_DATE);
                Game game = new Game();
                game.setDateFromDB(cursor.getString(indexDate));
                game.setPerMill(cursor.getDouble(indexPerMill));

                games.add(game);
            }
        }catch (Exception e)
        {
            Log.d(TAG, "ERROR WHILE GETTING ALL SCORES");
        }finally {
            if(cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }

        return games;
    }


    private List<QuestionUnit> getAllQuestionUnits()
    {
        List<QuestionUnit> questionUnits = new ArrayList<>();

      return questionUnits;
    }

    //--------------------------------------------------------------------------------------
    // CONTRACT FUNCTIONS ------------------------------------------------------------------
    //-.------------------------------------------------------------------------------------

    @Override
    public Game loadGameFromHistory(int pos) {
        return null;
    }

    @Override
    public List<Game> loadAllGamesFromHistory() {
        return getAllScores();
    }

    @Override
    public QuestionUnit loadQuestion(long seed) {
        return null;
    }

    @Override
    public List<QuestionUnit> loadQuestionBundle(long seed, int bundleSize) {
        return null;
    }

    @Override
    public void saveGame(Game game) {
        addScore(game);
    }

    //get all scores

    //get all questions

    //get 6 random questions






}
