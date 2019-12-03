package com.example.quizapp;

import android.provider.BaseColumns;

/*
 QuizContract hat die Aufgabe Konstanten für die Arbeit mit einer Datenbank zur Verfügung zu stellen und kann durch die final deklaration nicht überschrieben werden.
 Wenn später eine Änderung an der Tabelle Question vorgenommen werden muss, z.b eine zusätzliche Spalte muss erzeugt werden, kann dies zentral in dieser Klasse
 erfolgen
 */
public final class QuizContract {

    /*
    Zudem wird mit einem privaten Konstruktor verhindert, dass eine Instanz dieser Klasse erzeugt werden kann.
     */
    private QuizContract(){}


    /*
    Die Konstanten befinden sich in einer inneren öffentlichen Klasse QuestionTable,um von Aussen erreichbar zu sein, zusätzlich muss durch die static Deklaration
    keine Instanz von QuestionTable erzeugt werden, um auf die Konstanten zugreifen zu können.
    Die erste Konstante steht für den Tabellennamen,jede weitere Konstante repräsentiert jeweils eine Spalte einer Entity vom Typ Question,
    mit final wird jede Konstante einmalig initialisiert.

    BaseColums ist ein Interface, das zwei weitere Konstanten bereitstellt.
    Eine davon, public static final String _ID, wird in diesem Projekt für die Android-Klasse Courser benötigt.
    Die Konvention der Konstante _ID wird für eine reibungslose Arbeit mit der Datenbank gebraucht.
     */
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_question";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}
