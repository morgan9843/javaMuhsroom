import java.util.*;
import java.awt.*;
import java.io.*;

public class compute {

    public static File f = new File("shrooms.txt");
    public static int species = 0;
    public static ArrayList<String> names = new ArrayList<String>();
    
    public static void main(String[] args) {
        try {
            list(f);
            System.out.println(names);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Scanner reset(Scanner input, File f) {
        try {
            return input = new Scanner(f);
        } catch (Exception ex) {
            ex.printStackTrace();        
            return null;
        }
    }

    public static void list(File f) { 
        try {
            Scanner scan = null;
            scan = reset(scan, f);

            while (scan.hasNextLine()) {
                String temp;
                species++;
                temp = scan.nextLine();
                int cut = temp.indexOf(" ");
                names.add(temp.substring(0, cut));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}