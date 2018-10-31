package com.example.keren.multiplechoicequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.keren.multiplechoicequiz.QuizContract.*;


import java.util.ArrayList;
import java.util.List;


public class QuizDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "space_ed_quiz.db";
    private static final int DATABASE_VERSION = 1;
    // Version needs to be updated after changes need to be made on a table that is already created
    // If the app is not yet in production however, the app can be simply deleted reinstalled in order to make changes
    private SQLiteDatabase db;

    //Opening Database
    public QuizDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //Creating the table using SQL using QuizContract class to reduce changes in code if column tables need to be changed
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                QuestionsTable.COLUMN_MODULE + " INTEGER" +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE_TWO = "CREATE TABLE " +
                FlashCardsTable.TABLE_NAME2+ " ( " +
                FlashCardsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FlashCardsTable.QUESTION + " TEXT, " +
                FlashCardsTable.A_TITLE + " TEXT" +
                FlashCardsTable.ANSWER +  "TEXT" +
                ")";
        // executing the query
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE_TWO);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Dropping the table if it has already been created so that changes can be created.
        //Changing the version number is manually done
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + FlashCardsTable.TABLE_NAME2);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Which of these statements are false?", "All networks need to have a server", "A cluster is a type of server", "Analog data is coverted to digital data for data transmission", 1);
        addQuestion(q1);
        Question q2 = new Question("What is an advantage of Virtualisation?", "Lower upfront costs", "Better disaster recovery and back up", "Little training is required", 2);
        addQuestion(q2);
        Question q3 = new Question("What is the most reffered to networking model called", "TCP/IP Model", "API Model", "OSI Model", 3);
        addQuestion(q3);
        Question q4 = new Question("Which of these statements are false about the Physical Layer?", "It combines packets into bytes", "Deals with transmitting bits over a communication circuit", "It is the first layer in the OSI Model", 1);
        addQuestion(q4);
        Question q5 = new Question( "Which of the following is NOT a part of the OSI Model", "Transport Layer", "Internet Layer", "Datalink Layer", 2);
        addQuestion(q5);
        Flashcards f1 = new Flashcards("SAMPLE FLASHCARD QUESTION", "SAMPLE FLASHCARD ANSWER");
        addQuestion(f1);
        Flashcards f2 = new Flashcards("sample flashcard question", "sample flash card answer" +        "CHAR(10)" + "This is a new line" );
        addQuestion(f2);
        Flashcards f3 = new Flashcards("hello", "my name is elder price");
        addQuestion(f3);
        /*
        Flashcards f1 = new Flashcards("What are the pros and cons of Peer-to-Peer?", "No need for a network opertating system.");
        addQuestion(f1);
        Flashcards f2 = new Flashcards(" Why is analog to digital transmission needed?", "Produces fewer errors and permits higher transmission rates.");
        addQuestion(f2);
        Flashcards f3 = new Flashcards("Describe the difference between Digital and Analog Data", "Digital data has two binary levels, 0 or 1.");
        addQuestion(f3);
        Flashcards f4 = new Flashcards("Describe the difference between Digital and Analog Data", "Establishing, maintaining and terminating connections accross intervening networks.");
        addQuestion(f4);
        Flashcards f5 = new Flashcards("Types of noise that can intefere with Data transmission", "There is atmospheric noise such as lighting, microwaves, and solar noise.");
        addQuestion(f5);
        */


    }

    private void addQuestion(Question question) {
        //Filling in the table contents with questions
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    private void addQuestion(Flashcards flashcard) {
        //Filling in the questions table for quiz; contents with questions
        ContentValues cv2 = new ContentValues();
        cv2.put(FlashCardsTable.QUESTION, flashcard.getQuestionF());
        cv2.put(FlashCardsTable.ANSWER, flashcard.getAnswerF());

        db.insert(FlashCardsTable.TABLE_NAME2, null, cv2);

    }


    public List<Question> getAllQuestions() {
        //Method used to retrieve all questions
        //Accessed in Quiz Activity
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
    //returns all flashcards
    public List<Flashcards> getAllFlashcards() {
        //Method used to retrieve all flashcards
        //Accessed in Flashcard Activity
        List<Flashcards> flashcardList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + FlashCardsTable.TABLE_NAME2, null);

        if (c.moveToFirst()) {
            do {
                Flashcards flashcards = new Flashcards();
                flashcards.setQuestionF(c.getString(c.getColumnIndex(FlashCardsTable.QUESTION)));
                flashcards.setAnswerF(c.getString(c.getColumnIndex(FlashCardsTable.ANSWER)));
                flashcardList.add(flashcards);
            } while (c.moveToNext());
        }

        c.close();
        return flashcardList;
    }
}
