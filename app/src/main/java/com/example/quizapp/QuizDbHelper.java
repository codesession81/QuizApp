package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

/*
Die Klasse QuizDbHelper generiert eine Datenbank MyAwesomeQuiz.db und
erzeugt eine Tabelle mit Spalten und füllt diese mit Fragen.
 */
public class QuizDbHelper extends SQLiteOpenHelper {//Die abstrakte Klasse SQLiteOpenHelper, welche die Methoden für die arbeit mit der Dankebank bereitstellt

    /*
    Variablen
     */
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;


    /*
    Dem Superklassenkonstruktor die für die Datenbank notwendigen Konfigurationsinformationen übergeben
     */
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    /*
    In onCreate wird die Datenbank, eine Tabelle und Spalten angelegt
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //Ein SQL-Statement in einem finalen String Speichern, der eine Tabelle anlegt und Spalten mit Ihren Attributen definiert.
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( "+
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" + ")";

        //Das SQL-Statement ausführen
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        //In die erzeugte Tabelle Informationen speichern
        fillQuestionsTable();
    }

    /*
    Wenn die Datenbank neu aufgebaut werden soll, Tabellen löschen, die bereits existieren.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    /*
    In dieser Methode werden Instanzen der Klasse Question erzeugt und der Methode addQuestion übergeben,
    die die Question-Objekte in die Datenbank speichert.
     */
    private void fillQuestionsTable(){
        Question q1 = new Question("Wie heißt der Stern, um den die Erde kreist?", "Sonne","Mond","Milchstrasse",1);
        addQuestion(q1);
        Question q2 = new Question("Wie heißt der Präsident von Amerika seit 2018?", "Ronald Nixon","Donald Trump","Arnold Schwarzenegger",2);
        addQuestion(q2);
        Question q3 = new Question("Wie heißt der Bruder von Tick und Track?", "Dagobert Duck","Gustav Gans","Trick",3);
        addQuestion(q3);
        Question q4 = new Question("Wann fliegt man Mach 1?", "343,3 m/s","1000 m/s","634,7 m/s",1);
        addQuestion(q4);
        Question q5 = new Question("Wie schnell ist Parker Solar Probe unterwegs?", "ca.17km/s","ca.100km/s","ca.400.000 km/s",2);
        addQuestion(q5);
    }

    private void addQuestion(Question question){
        //Um Questionobjekte in die Datenbank zu speichern, wird ein Objekt der Klasse ContentValues genutzt
        ContentValues cv = new ContentValues();

        /*
        Der Methode put die Argumente: Tabellennamen, Spaltennamen und Questionobjekt übergeben
         */
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR,question.getAnswerNr());

        //Mit insert die Questionobjekte in die Datenbank schreiben
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }


    /*
    Alle Questionobjekte aus der Datenbank auslesen und in eine ArrayList speichern
     */
    public ArrayList<Question> getAllQuestions(){

        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        //Mit dem Cursor-Objekt wird durch die Tabelle Tupelweise iteriert, wenn eine Tupel existiert, die Werte auslesen und in neu erzeugte Questioninstanzen speichern,
        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);//welche anschließend in die ArrayList gespeichert werden
            }while(cursor.moveToNext());//Solange eine nächste Tupel existiert, den Vorgang in der Schleife wiederholen
        }
        //Wenn alle Tupel aus der Tabelle ausgelesen sind, das Cursor-Objekt schliessen
        cursor.close();

        //Die mit Question-Objekten gefüllte ArrayList zurückgeben
        return questionList;
    }


}
