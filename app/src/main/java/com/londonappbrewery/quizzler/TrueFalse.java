package com.londonappbrewery.quizzler;

public class TrueFalse {
    private int mQuestionID;
    private boolean answer;
    public TrueFalse(int mQuestionResourceID,boolean ans)
    {
        mQuestionID = mQuestionResourceID;
        answer = ans;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
