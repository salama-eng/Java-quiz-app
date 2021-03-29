package com.example.osama.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.osama.quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama on 1/15/2019.
 */
public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="MyQuiz.db";
    private static final int DATABASE_VERSION=1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_QUESTIONS_TABLE=" CREATE TABLE " +
                QuestionTable.TABLE_NAME+ " ( "+
                QuestionTable._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionTable.COLUMN_QUESTIONS +" TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, "+
                QuestionTable.COLUMN_OPTION2 + " TEXT, "+
                QuestionTable.COLUMN_OPTION3 + " TEXT, "+
                QuestionTable.Column_ANSWER_NR + " INTEGER" +
                " )";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionTable(){

        Question q1= new Question("A The first and most important piece of software you need to download is  ","Android SDK","jave JDK","All of them",1);
        addQuestion(q1);

        Question q2= new Question("During an Activity life-cycle, what is the first callback method invoked by the system? ","onStop()","onCreate()","onStart()",2);
        addQuestion(q2);

        Question q3= new Question("Which configuration file holds the permission to use the internet? ","Layout file","Java source file","Manifest file",3);
        addQuestion(q3);

        Question q4= new Question("Which file specifies the minimum required Android SDK version your application supports? ","build.gradle","R.java","main.xml",1);
        addQuestion(q4);

        Question q5= new Question("What is the name of the class used by Intent to store additional information?","Extra","Bundle","DataStore",2);
        addQuestion(q5);

    }
    private void addQuestion(Question question){
        ContentValues cv =new ContentValues(); // use content value to add question to DB
        cv.put(QuestionTable.COLUMN_QUESTIONS,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.Column_ANSWER_NR,question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public List<Question>getAllQuestions(){
        List<Question>questionList =new ArrayList<>();
        db= getReadableDatabase();
        Cursor c=db.rawQuery(" SELECT * FROM "+QuestionTable.TABLE_NAME,null);
        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTIONS)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.Column_ANSWER_NR)));
                questionList.add(question);

            }while (c.moveToNext());
        }

        c.close();
        return questionList;

    }
}
