import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class Reel {

    ArrayList<Symbol> image = new ArrayList();
//Inserting images and values of images
    public Reel() {
        try {
            Symbol s1 = new Symbol(new ImageIcon(getClass().getResource("redseven.png")), 7);
            image.add(s1);
        } catch (NullPointerException e) {
            System.out.println("Image one not found");
        }

        try {
            Symbol s2 = new Symbol(new ImageIcon(getClass().getResource("bell.png")), 6);
            image.add(s2);
        } catch (NullPointerException e) {
            System.out.println("Image one not found");
        }
        try {
            Symbol s3 = new Symbol(new ImageIcon(getClass().getResource("watermelon.png")), 5);
            image.add(s3);
        } catch (NullPointerException e) {
            System.out.println("Image one not found");
        }
        try {
            Symbol s4 = new Symbol(new ImageIcon(getClass().getResource("plum.png")), 4);
            image.add(s4);
        } catch (NullPointerException e) {
            System.out.println("Image one not found");
        }
        try {
            Symbol s5 = new Symbol(new ImageIcon(getClass().getResource("lemon.png")), 3);
            image.add(s5);
        } catch (NullPointerException e) {
            System.out.println("Image one not found");
        }
        try {
            Symbol s6 = new Symbol(new ImageIcon(getClass().getResource("cherry.png")), 2);
            image.add(s6);
        } catch (NullPointerException e) {
            System.out.println("Image one not found");
        }
    }
//Random method 
    public ArrayList<Symbol> spin() {
        ArrayList<Symbol> symbolList = new ArrayList();
        Random randomNo = new Random();
        int selection = randomNo.nextInt(6);
        Symbol s = image.get(selection);
        symbolList.add(s);
        symbolList.add(image.get((selection + 1) % 6));
        symbolList.add(image.get((selection + 2) % 6));
        symbolList.add(image.get((selection + 3) % 6));
        symbolList.add(image.get((selection + 4) % 6));
        symbolList.add(image.get((selection + 5) % 6));
        return symbolList;
    }
    
    
        
    

}
