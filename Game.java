/**
Final Project
@author Group #5: Daniel Hoevener, Noah Hornback, Myron Rankins, Jillian Sizemore
@version 4/20/20

This class sets up the GUI and the formatting and runs the logic for the trivia game
*/


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Game implements ActionListener {
  JTextField playerName; 
  JButton firstBtn, secondBtn, thirdBtn, fourthBtn, nextBtn;
  JLabel welcomeLbl, questionLbl, pointsWorthLbl, scoreLbl, resultLbl;
  static ArrayList<Question> questions;
  static String filename = "trivia.txt"; 
  static FileReader myFile;
  int score = 0;
  int activeQuestion = 0;
  String player = "";
  JButton[] qButtons = new JButton[4];
/**
This method adds everything to the GUI and reads in the info from the trivia.txt file
*/
  Game(){ 
    
    JFrame frame = new JFrame("Group 5's UC Trivia");
    frame.setLayout(new FlowLayout());
    frame.setSize(700, 550);
    playerName = new JTextField(20);
    playerName.setActionCommand("myTF");
    firstBtn = new JButton("1"); 
    firstBtn.setFont(new Font("Serif", Font.BOLD, 12));
    secondBtn = new JButton("2");
    secondBtn.setFont(new Font("Serif", Font.BOLD, 12));
    thirdBtn = new JButton("3");
    thirdBtn.setFont(new Font("Serif", Font.BOLD, 12));
    fourthBtn = new JButton("4");
    fourthBtn.setFont(new Font("Serif", Font.BOLD, 12));
    nextBtn = new JButton("Next");
    welcomeLbl = new JLabel("Welcome to UC trivia! Please enter your name in the text box and hit Enter");
    welcomeLbl.setFont(new Font("Serif", Font.BOLD, 20));
    questionLbl = new JLabel("");
    pointsWorthLbl = new JLabel("");
    scoreLbl = new JLabel("Score: " + score);
    scoreLbl.setForeground(Color.RED);
    resultLbl = new JLabel("");

    qButtons[0] = firstBtn;
    qButtons[1] = secondBtn;
    qButtons[2] = thirdBtn;
    qButtons[3] = fourthBtn;

    JPanel optionsButtonPanel = new JPanel(new FlowLayout());
    optionsButtonPanel.add(firstBtn);
    optionsButtonPanel.add(secondBtn);
    optionsButtonPanel.add(thirdBtn);
    optionsButtonPanel.add(fourthBtn);

    playerName.addActionListener(this);
    firstBtn.addActionListener(this);
    secondBtn.addActionListener(this);
    thirdBtn.addActionListener(this);
    fourthBtn.addActionListener(this);
    nextBtn.addActionListener(this);

    frame.add(welcomeLbl);
    frame.add(scoreLbl);
    frame.add(playerName);
    frame.add(questionLbl);
    frame.add(pointsWorthLbl);
    frame.add(optionsButtonPanel);
    frame.add(nextBtn);
    frame.add(resultLbl);
    
    frame.setVisible(true);
    questionLbl.setVisible(false);
    pointsWorthLbl.setVisible(false);
    scoreLbl.setVisible(false);
    firstBtn.setVisible(false);
    secondBtn.setVisible(false);
    thirdBtn.setVisible(false);
    fourthBtn.setVisible(false);
    nextBtn.setVisible(false);
    resultLbl.setVisible(false);
    optionsButtonPanel.setVisible(true);
    
    questions = new ArrayList<Question>();
    filename = "trivia.txt";
    String questionText = "",  answer1Text = "", answer2Text = "", answer3Text = "", answer4Text = "", rightAnswer = "", pointValue  = "";
  /**
  This is the try and catch function to identify possible erros with file reading
  */  
    try{
      myFile  = new FileReader(filename);
      BufferedReader reader = new BufferedReader(myFile);
      
      while(reader.ready()) {
        questionText = reader.readLine();
        answer1Text = reader.readLine();
        answer2Text = reader.readLine();
        answer3Text = reader.readLine();
        answer4Text = reader.readLine();
        rightAnswer = reader.readLine();        
        pointValue = reader.readLine();
        /**
        This is a constructor to read in info from the trivia.txt file
        */
        Question aQuestion = new Question(questionText, answer1Text, answer2Text, answer3Text, answer4Text, Integer.parseInt(rightAnswer), Integer.parseInt(pointValue));
        questions.add(aQuestion);
      } 
      reader.close(); 
    } catch (IOException exception) {
      System.out.println("An error occured: " + exception);
    }
  }
  /**
  Logic to use the action listener from the text box to welcome the player and start the game
  */
  public void actionPerformed(ActionEvent ae) {
    if(ae.getActionCommand().equals("myTF")){
      player = playerName.getText();
      welcomeLbl.setText("Welcome, " + player + "!");
      getQuestion(activeQuestion);
      startGame();
    }
    String correctAns = qButtons[questions.get(activeQuestion).getRightAnswer()-1].getText();

    if(!ae.getActionCommand().equals(correctAns)) {
      resultLbl.setText("That's incorrect, please click Next to continue playing.");
      resultLbl.setVisible(true);
    }
    if(ae.getActionCommand().equals("Next")){
      activeQuestion++;
      if(activeQuestion >= questions.size()){
      welcomeLbl.setText("That's all the questions, " + player + ". Thanks for playing UC Trivia!");
      scoreLbl.setText("You scored: " + score  + " points!");
      endGame();
      /**
      Try and catch method to identify errors in file writing
      */
      try {
        File scores = new File("scores.txt");
        if(!scores.exists()){
          scores.createNewFile();
        } 
        FileWriter toScores = new FileWriter(scores.getName(),true);
        BufferedWriter output = new BufferedWriter(toScores);
        output.write(player);
        output.newLine();
        output.write(String.valueOf(score));
        output.newLine();
        output.close();
      }
  catch(IOException exception){
    System.out.println("An error occured: " + exception);
  }     
  } else {
      getQuestion(activeQuestion);
      resultLbl.setVisible(false);
      }
    }
    if(ae.getActionCommand().equals("myTF")){
    resultLbl.setText(""); 
    } 
    else if(ae.getActionCommand().equals(correctAns)){
      resultLbl.setText("That's correct! Please click Next to continue playing");
      score = score + questions.get(activeQuestion).getPointValue();
      scoreLbl.setText("Score: "+ score);
      resultLbl.setVisible(true);
    }
    
  }
  /**
  This method starts the game and makes everything visible that should be
  */
  public void startGame(){
      scoreLbl.setVisible(true);
      playerName.setVisible(false);
      questionLbl.setVisible(true);
      pointsWorthLbl.setVisible(true);
      firstBtn.setVisible(true);
      secondBtn.setVisible(true);
      thirdBtn.setVisible(true);
      fourthBtn.setVisible(true);
      nextBtn.setVisible(true);
      resultLbl.setVisible(false);
  }
  /**
  This method ends the game and makes everything invisible that should be
  */
  public void endGame() {
      scoreLbl.setVisible(true);
      playerName.setVisible(false);
      questionLbl.setVisible(false);
      pointsWorthLbl.setVisible(false);
      firstBtn.setVisible(false);
      secondBtn.setVisible(false);
      thirdBtn.setVisible(false);
      fourthBtn.setVisible(false);
      nextBtn.setVisible(false);
      resultLbl.setVisible(false); 
  }
  /**
  This method brings in the text and point value for each question from the trivia.txt file and uses the accessor methods from the question.java file
  @param question
  */
  public void getQuestion(int question) {
    questionLbl.setText(questions.get(question).getQuestionText());
    pointsWorthLbl.setText("Value: " + Integer.toString(questions.get(question).getPointValue()));
    firstBtn.setText(questions.get(question).getAnswer1Text());
    secondBtn.setText(questions.get(question).getAnswer2Text());
    thirdBtn.setText(questions.get(question).getAnswer3Text());
    fourthBtn.setText(questions.get(question).getAnswer4Text());
  }

}

