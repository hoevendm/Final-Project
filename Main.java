/**
Final Project
@author Group #5: Daniel Hoevener, Noah Hornback, Myron Rankins, Jillian Sizemore
@version 4/20/20

This class runs the Trivia GUI
*/


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
*This class runs the Trivia GUI
*/
class Main {
public static void main(String args[]) {
  SwingUtilities.invokeLater(new Runnable() {
    public void run() {
       new Game();
    }
  });
 }
}