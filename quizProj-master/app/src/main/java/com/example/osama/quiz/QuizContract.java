package com.example.osama.quiz;

import android.provider.BaseColumns;

/**
 * Created by Osama on 1/15/2019.
 */
public final class  QuizContract {
    private QuizContract(){}
    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME ="quiz_questions";
        public static final String COLUMN_QUESTIONS="quesions";
        public static final String COLUMN_OPTION1="option1";
        public static final String COLUMN_OPTION2="option2";
        public static final String COLUMN_OPTION3="option3";
        public static final String Column_ANSWER_NR="answer_nr";



    }
}
