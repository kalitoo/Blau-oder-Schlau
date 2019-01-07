package com.example.blauoderschlau.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.model.Answer;
import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class MyDB implements DatabaseManagerContract.Model {

    private static MyDB instance ;

    public static synchronized MyDB getInstance(Context context)
    {
        if(instance == null) instance = new MyDB(context.getApplicationContext());
        return instance;
    }

    private Context context;
    private static List<QuestionUnit> questionUnitsToAdd = new ArrayList<>();
    private static List<QuestionUnit> questionUnits= new ArrayList<>();
    private static List<Game> gamehistory = new ArrayList<>();

    public SQLiteDatabase databaseReadable;
    SQLiteDatabase databaseWritable;

    BosDatabaseHelper helper;

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


        questionUnitsToAdd.add(questionUnit0);
        questionUnitsToAdd.add(questionUnit1);
        questionUnitsToAdd.add(questionUnit2);
        questionUnitsToAdd.add(questionUnit3);
        questionUnitsToAdd.add(questionUnit4);
        questionUnitsToAdd.add(questionUnit5);
        questionUnitsToAdd.add(questionUnit6);
        questionUnitsToAdd.add(questionUnit7);
        questionUnitsToAdd.add(questionUnit8);
        questionUnitsToAdd.add(questionUnit9);
        questionUnitsToAdd.add(questionUnit10);
        questionUnitsToAdd.add(questionUnit11);
    }




    private MyDB(Context context )
    {
        helper = BosDatabaseHelper.getInstance(context);
        databaseReadable = helper.getReadableDatabase();
        databaseWritable = helper.getWritableDatabase();
        addQuestionUnits(questionUnitsToAdd);

    }


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
        databaseWritable.beginTransaction();

        try
        {

            ContentValues values = new ContentValues();
            values.put(KEY_ANSWER_TEXT, answer.getText());
            values.put(KEY_ANSWER_CORRECT,answer.isCorrect());
            values.put(KEY_ANSWER_QUESTION_ID_FK, questionId);


            databaseWritable.insertOrThrow(TABLE_ANSWERS, null,  values);
            databaseWritable.setTransactionSuccessful();
        }catch(Exception e)
        {
            Log.d(TAG, "ERROR WHILE INSERTING QUESTION");
        }finally
        {
            databaseWritable.endTransaction();
        }

    }


    //not sure if this really returns the id

    private int addQuestion(String question) //returns question ID
    {

        int questionId = -1;
        databaseWritable.beginTransaction();

        try
        {
            ContentValues values = new ContentValues();
            values.put(KEY_QUESTION_TEXT, question);

            databaseWritable.insertOrThrow(TABLE_QUESTIONS, null, values);
            databaseWritable.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            Log.d(TAG, "ERROR WHILE INSERTING QUESTION");
        }
        finally
        {
            databaseWritable.endTransaction();
        }

        databaseReadable.beginTransaction();
        String sql = String.format("SELECT %s FROM %s WHERE %s=?", KEY_QUESTION_ID, TABLE_QUESTIONS, KEY_QUESTION_TEXT);
        Cursor cursor = databaseReadable.rawQuery(sql, new String[]{question});

        try
        {
            if(cursor.moveToFirst())
            {
                int index = cursor.getColumnIndex(KEY_QUESTION_ID);
                questionId = cursor.getInt(index);
                databaseWritable.setTransactionSuccessful();
            }
        }finally
        {
            if(cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
            databaseReadable.endTransaction();
        }
        return questionId;
    }



    private void addScore(Game game)
    {
        databaseWritable.beginTransaction();

        try
        {
            ContentValues values = new ContentValues();
            values.put(KEY_HISTORY_DATE, game.getDateAsString());
            values.put(KEY_HISTORY_PERMILL, game.getPerMill());

            databaseWritable.insertOrThrow(TABLE_SCORE_HISTORY, null, values);
            databaseWritable.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            Log.d(TAG, "ERROR WHILE INSERTING SCORE");
        }
        finally
        {
            databaseWritable.endTransaction();
        }

    }



    private List<Game> getAllScores()
    {
        List<Game> games = new ArrayList<>();
        databaseReadable.beginTransaction();

        //this might cause failure
        String sql = String.format("SELECT %s,%s FROM %s", KEY_HISTORY_DATE, KEY_HISTORY_PERMILL, TABLE_SCORE_HISTORY);
        Cursor cursor = databaseReadable.rawQuery(sql, null);
        try
        {
            if(cursor.moveToFirst())
            {
                do {
                    int indexPerMill = cursor.getColumnIndex(KEY_HISTORY_PERMILL);
                    int indexDate = cursor.getColumnIndex(KEY_HISTORY_DATE);
                    Game game = new Game();
                    game.setDateFromDB(cursor.getString(indexDate));
                    game.setPerMill(cursor.getDouble(indexPerMill));

                    games.add(game);
                }while (cursor.moveToNext());
                databaseReadable.setTransactionSuccessful();
            }
        }catch (Exception e)
        {
            Log.d(TAG, "ERROR WHILE GETTING ALL SCORES");
        }finally {
            if(cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
            databaseReadable.endTransaction();
        }

        return games;
    }

    //GET-------------------------------------------------------------------------------------

    public Cursor getScoreCursor()
    {


        databaseReadable.beginTransaction();

        //this might cause failure
        String sql = String.format("SELECT %s,%s FROM %s", KEY_HISTORY_DATE, KEY_HISTORY_PERMILL, TABLE_SCORE_HISTORY);
        Cursor cursor = databaseReadable.rawQuery(sql, null);
        databaseReadable.setTransactionSuccessful();
        databaseReadable.endTransaction();

        return cursor;
    }

    private List<QuestionUnit> getAllQuestionUnits()
    {
        List<QuestionUnit> questionUnits = new ArrayList<>();

        databaseReadable.beginTransaction();

        String sql = String.format("SELECT %s,%s FROM %s ", KEY_QUESTION_ID,KEY_QUESTION_TEXT,TABLE_QUESTIONS);
        Cursor cursor = databaseReadable.rawQuery(sql, null);


        //Like for-schleife with cursor; goes through all questions
        try
        {
            if(cursor.moveToFirst())
            {
                do {
                    int questionsTextIndex = cursor.getColumnIndex(KEY_QUESTION_TEXT);
                    int questionIdIndex = cursor.getColumnIndex(KEY_QUESTION_ID);
                    int questionId = cursor.getInt(questionIdIndex);
                    String questionText = cursor.getString(questionsTextIndex);

                    Answer[] answers = new Answer[4];

                    //my cause problems because of to string
                    String sql2 = String.format("SELECT %s,%s FROM %s WHERE %s = ?", KEY_ANSWER_TEXT,KEY_ANSWER_CORRECT, TABLE_ANSWERS, KEY_ANSWER_QUESTION_ID_FK);
                    String[] args = {Integer.toString(questionId)};
                    Cursor cursor2 = databaseReadable.rawQuery(sql2, args);


                    //for-schleife inside for-schleife; for each question get all answers
                    try{
                        if(cursor2.moveToFirst())
                        {
                            for(int i=0 ; i < 4; i++ ) {

                                int answerTextIndex = cursor2.getColumnIndex(KEY_ANSWER_TEXT);
                                int answerCorrectIndex = cursor2.getColumnIndex(KEY_ANSWER_CORRECT);
                                String answerText = cursor2.getString(answerTextIndex);
                                Boolean answerCorrect;
                                if (cursor2.getInt(answerCorrectIndex) == 0) {
                                    answerCorrect = false;
                                } else {
                                    answerCorrect = true;
                                }

                                Answer answer = new Answer(answerText, answerCorrect);
                                answers[i] = answer;
                                cursor2.moveToNext();

                            }

                        }
                    }catch(Exception e){
                        Log.d(TAG, "ERROR WHILE GETTING ALL QUESTIONS");
                    }finally{
                        cursor2.close();
                    }

                    QuestionUnit questionUnit = new QuestionUnit(questionText,answers);
                    questionUnits.add(questionUnit);

                }while(cursor.moveToNext());
                databaseReadable.setTransactionSuccessful();
            }

        }catch(Exception e){
            Log.d(TAG, "ERROR WHILE GETTING ALL QUESTIONS");

        }finally
        {
            cursor.close();
            databaseReadable.endTransaction();
        }


        return questionUnits;
    }

    //--------------------------------------------------------------------------------------
    // CONTRACT FUNCTIONS ------------------------------------------------------------------
    //--------------------------------------------------------------------------------------

    @Override
    public Game loadGameFromHistory(int pos) {
        gamehistory = getAllScores();
        return gamehistory.get(pos);
    }

    @Override
    public List<Game> loadAllGamesFromHistory() {
        return getAllScores();
    }

    @Override
    public QuestionUnit loadQuestion(long seed) {
        questionUnits = getAllQuestionUnits();
        int indexOfRandQuestion = new Random().nextInt(questionUnits.size());
        return questionUnits.get(indexOfRandQuestion);
    }

    @Override
    public List<QuestionUnit> loadQuestionBundle(long seed, int bundleSize) {
        questionUnits = getAllQuestionUnits();
        List<QuestionUnit> questionUnitBundle = new ArrayList<>();
        List<Integer> usedIndices = new ArrayList<>();
        for (int i = 0; i < bundleSize; i++) {
            if (usedIndices.size() >= questionUnits.size()) {
                break;
            }
            int randomIndex;
            do {
                randomIndex = new Random().nextInt(questionUnits.size());
            }
            while(usedIndices.contains(randomIndex));
            usedIndices.add(randomIndex);
            questionUnitBundle.add(questionUnits.get(randomIndex));
        }
        return questionUnitBundle;
    }

    @Override
    public void saveGame(Game game) {
        addScore(game);
    }


}
