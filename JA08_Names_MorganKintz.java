/*
  Title:    
  Author:  Morgan Kintz
  Date:    
  Course:  APCS Period 4
  
  Description:
    Searches a txt file using a scanner to compare a name that the user inputs
    to a database and displays the names popularity to decade using a drawPanel
    graph.
*/

import java.util.*;
import java.awt.*;
import java.io.*;

public class JA08_Names_MorganKintz {
    
    // DrawPanel Sizes
    public static int yPx = 550;
    public static int xWidth = 70;
    public static int decades = 14;
    public static int xPx = xWidth * decades;

    // DrawPanel Settings
    public static DrawingPanel panel = new DrawingPanel(70*14, 550);
    public static Graphics g = panel.getGraphics();

    // Names
    public static File f = new File("names.txt");
    public static String foundName;
    public static String foundData;
    public static int[] rank = new int[14];

    // Checks
    public static boolean flag = true;

    //
    // Executes the program upon run
    // Scanne must be decleared in a try case
    //
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(f);
            //detail(scan);
            panel.setBackground(Color.WHITE);
            labels();
            flag = search(scan);
            if (flag == false) {
                parse(foundData);
                graph();
            } else {
                System.out.println("Not Found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //
    // CALL BEFORE parse()
    // Calls to compare to the input file, if found, it sets the string 
    // foundData to the string of data following the name and gender, and then 
    // it sets foundName to the name plus gender
    // It's return is to trigger flag to proceed with graphing
    //
    public static boolean search(Scanner scan) {
        String promptedInput = prompt(scan);
        int cut = promptedInput.length();
        while (scan.hasNextLine() && flag) {
            foundName = scan.nextLine();
            if (promptedInput.equals(foundName.substring(0, cut).toLowerCase())) {
                foundData = foundName.substring(cut + 1, foundName.length());
                foundName = foundName.substring(0, cut);
                flag = false;
            }
        }
        return flag;
    }

    //
    // Gives the user an input and returns the combined string
    // Returns the formatted string to compare to the search()
    //
    public static String prompt(Scanner scan) {
        System.out.print("names? ");
        String name = (new Scanner(System.in)).nextLine();
        name = name.toLowerCase().trim() + " ";
        System.out.print("gender (M or F)? ");
        String gender = (new Scanner(System.in)).nextLine();
        gender = gender.toLowerCase().trim();
        return name + gender;
    }

    //
    // Parses the data into the interger array - rank 
    //
    public static void parse(String dataIn) {
        int tempS = 0;
        dataIn = dataIn + " ";
        int tempE = dataIn.length();
        for (int i = 0; i < 14; i++) {
            tempE = dataIn.length();
            tempE = dataIn.substring(tempS, tempE).indexOf(' ') + tempS;
            rank[i] = Integer.valueOf(dataIn.substring(tempS, tempE));
            tempS =+ tempE+1;
        }
    } 

    //
    // Plots the data
    //
    public static void graph() {
        g.setColor(Color.RED);
        int e, s;
        for (int i = 0; i < 14; i++) {
            s = Math.round(rank[i]/2) + 25;
            e = Math.round(rank[i]/2) + 25;
            if (i != 13) {
                e = Math.round(rank[i+1]/2) + 25;
                if (!(rank[i+1] == 1 || rank[i+1] == 2) && rank[i+1] == 0 && i != 13) {
                    e = 525;
                }
            }
            if (rank[i] == 0) {
                s = 525;
            }
            //}
            g.drawString(foundName + " " + rank[i], xWidth*i, s);
            if (i != 13) {
                g.drawLine(xWidth*i, s, xWidth*(i+1), e);
            }
        }
    }
    
    //
    // Draws all the visual elements to begin with
    //
    public static void labels() {
        // Horizontal lines
        g.drawLine(0, 25, xPx, 25);
        g.drawLine(0, 525, xPx, 525);
        // Vertical lines, then text
        for (int i = 1; i <= decades; i++) {
            g.drawLine(i*xWidth, 0, i*xWidth, yPx);
            g.drawString(String.valueOf(1870+i*10), xWidth*(i-1), 550);
        }
    }
}