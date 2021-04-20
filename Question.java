/**
Final Project
@author Group #5: Daniel Hoevener, Noah Hornback, Myron Rankins, Jillian Sizemore
@version 4/20/20

This class sets up the question formatting and get functions
*/


class Question {
  private String questionText;
  private String answer1Text;
  private String answer2Text;
  private String answer3Text;
  private String answer4Text;
  private int rightAnswer;
  private int pointValue;

/**
This is a constructor method for the question
@param aQuestionText, anAnswer1Text, anAnswer2Text,anAnswer3Text, anAnswer4Text, aRightAnswer, aPointValue
*/
  public Question(String aQuestionText, String anAnswer1Text, String anAnswer2Text,  String anAnswer3Text,  String anAnswer4Text, int aRightAnswer, int aPointValue){
    questionText = aQuestionText;
    answer1Text = anAnswer1Text;
    answer2Text = anAnswer2Text;
    answer3Text = anAnswer3Text;
    answer4Text = anAnswer4Text;
    rightAnswer = aRightAnswer;
    pointValue = aPointValue;
    }
/**
This is an accessor method to get the question text
@return questionText
*/  
  public String getQuestionText() {
    return questionText;
/**
This is an accessor method to get the answer 1 text
@return answer1Text
*/  
  }
  public String getAnswer1Text() {
    return answer1Text;
/**
This is an accessor method to get the answer 2 text
@return answer2Text
*/    
  }
  public String getAnswer2Text() {
    return answer2Text;
  }
/**
This is an accessor method to get the answer 3 text
@return answer3Text
*/    
  public String getAnswer3Text() {
    return answer3Text;
  }
/**
This is an accessor method to get the answer 4 text
@return answer4Text
*/  
  public String getAnswer4Text() {
    return answer4Text;
  }
/**
This is an accessor method to get the right answer number
@return rightAnswer
*/  
  public int getRightAnswer() {
    return rightAnswer;
  }
/**
This is an accessor method to get the point value of the question
@return pointValue
*/  
  public int getPointValue() {
    return pointValue;
  }
}
