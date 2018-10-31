package com.example.keren.multiplechoicequiz;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_MODULE = "module";
    }
    public static class FlashCardsTable implements BaseColumns{
        public static final String TABLE_NAME2 = "flashcards";
        public static final String QUESTION = "flashcardquestion";
        public static final String A_TITLE = "answer_title";
        public static final String ANSWER = "answer";
    }
}
