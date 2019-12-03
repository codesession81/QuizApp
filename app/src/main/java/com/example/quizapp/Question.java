package com.example.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

/*
Diese Klasse repräsentiert eine Objekt vom Typ Question.
 */
public class Question implements Parcelable { //Parcelable ist die Androidimplementierung, was in Java Serializable repräsentiert. Beides realisiert die Speicherung und Wiederherstellung von Instanzen

    /*
    Attribute einer Question-Instanz definieren und diese private deklarieren,
    damit nur ein kontrollierter Zugriff über Getter und Setter erfolgen kann.
     */
    private String question, option1, option2, option3;
    private int answerNr;


    /*
    Konstruktoren für die Objekterzeugung definieren
     */
    public Question() {

    }

    public Question(String question, String option1, String option2, String option3, int answerNr) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
    }


    /*
     Eine durch parcelable gespeicherte Questioninstanz wiederherstellen
      */
    protected Question(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answerNr = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    /*
    Getter und Setter Methoden für den Attributzugriff bereitstellen
     */

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getOption1() {
        return option1;
    }


    public void setOption1(String option1) {
        this.option1 = option1;
    }


    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }


    /*
    ?
     */
    @Override
    public int describeContents() {
        return 0;
    }

     /*
    Eine Questioninstanz an das Parameter in vom Typ Parcel zum speichern übergeben
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(answerNr);
    }
}
