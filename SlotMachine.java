import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

public class SlotMachine extends JFrame implements Runnable {
    //GUI graphics components variables
    private JButton btnAddCoin, btnBetOne, btnBetMax, btnReset, btnSpin, btnShowStatistics, btnSaveStatistics;
    private JTextField tfCreditArea, tfBetArea;
    private JLabel lblImage1, lblImage2, lblImage3;
    private int credit = 10;
    private int countBet, countWin, countLose, spinNumber, winCredit;
    private double average;
    private boolean stop;

    Reel reel1, reel2, reel3;
//Declaration of symbol list
    ArrayList<Symbol> symbolList1;    ArrayList<Symbol> symbolList2;
    ArrayList<Symbol> symbolList3;
    //Declaration of threads
    Thread t1, t2, t3;

    public SlotMachine() {

        //Container cp = getContentPane();
        //JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setPreferredSize(new Dimension(600, 800));
        panel2.setPreferredSize(new Dimension(200, 200));
        panel1.setBackground(Color.red);

        //mainPanel.setLayout(new BorderLayout.CENTER));
        panel1.setLayout(new GridLayout(1, 3));
        panel2.setLayout(new GridLayout(0, 3));
        lblImage1 = new JLabel("");

        panel1.add(lblImage1);

        lblImage2 = new JLabel();
        panel1.add(lblImage2);

        lblImage3 = new JLabel();
        panel1.add(lblImage3);

        btnAddCoin = new JButton("Add coin");
        panel2.add(btnAddCoin);

        tfCreditArea = new JTextField("10");
        panel2.add(tfCreditArea);

        tfBetArea = new JTextField("Bet area");
        panel2.add(tfBetArea);

        btnBetOne = new JButton("Bet one");
        panel2.add(btnBetOne);

        btnBetMax = new JButton("Bet max");
        panel2.add(btnBetMax);

        btnReset = new JButton("Reset");
        panel2.add(btnReset);

        btnSpin = new JButton("Spin");
        panel2.add(btnSpin);

        btnShowStatistics = new JButton("Show statistics");
        panel2.add(btnShowStatistics);

        btnSaveStatistics = new JButton("Save statistics");
        panel2.add(btnSaveStatistics);

        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.SOUTH);
        //add(mainPanel);

        MyListener myListener = new MyListener();
        btnSpin.addActionListener(myListener);
        btnAddCoin.addActionListener(myListener);
        btnBetOne.addActionListener(myListener);
        btnBetMax.addActionListener(myListener);
        btnReset.addActionListener(myListener);
        btnShowStatistics.addActionListener(myListener);
        btnSaveStatistics.addActionListener(myListener);

        MouseHandler handler = new MouseHandler();
        lblImage1.addMouseListener(handler);
        lblImage2.addMouseListener(handler);
        lblImage3.addMouseListener(handler);

    }
  //writes statistics in file
    public void inputStatistics(int countWin, int countLose, double average) {
        try {

            //open
            FileWriter fw = new FileWriter("List.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            //write
            bw.write("Win: " + countWin + "\n");
            bw.write("Lost:" + countLose + "\n");
            bw.write("Average: " + average + "\n");
            bw.write("-----------------" + "\n");
            bw.flush();

            //close
            bw.close();
            fw.close();
        } catch (Exception e) {

        }

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//Methods belonging for each button in program
    private class MyListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            String btnLabel = evt.getActionCommand();
            Reel reel1 = new Reel();
            Reel reel2 = new Reel();
            Reel reel3 = new Reel();

            if (credit == 0) {

                JOptionPane.showMessageDialog(null, "You have zero credit and cannot play more");
             if (countBet == 0) {

                JOptionPane.showMessageDialog(null, "You have zero credit and cannot play more");   }
            } else if (btnLabel.equals("Spin")) {
                   
                stop = false;
                spinNumber++;
//Call spin method of reels and return list of 6 simbols. First is randomly selected and the rest 5 symbols follow
                symbolList1 = reel1.spin();
                symbolList2 = reel2.spin();
                symbolList3 = reel3.spin();

                t1 = new Thread(new Runnable() {
                    public void run() {

                        while (!stop) {
                            for (int i = 0; i < 6; i++) {
                                ImageIcon randomImage1 = symbolList1.get(i).getImage();
                                Image image = randomImage1.getImage();
                                lblImage1.setIcon(randomImage1);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(SlotMachine.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }
                    }
                });

                t2 = new Thread(new Runnable() {
                    public void run() {
                        while (!stop) {
                            for (int i = 0; i < 6; i++) {
                                ImageIcon randomImage2 = symbolList2.get(i).getImage();
                                Image image = randomImage2.getImage();
                                lblImage2.setIcon(randomImage2);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(SlotMachine.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }
                    }
                });

                t3 = new Thread(new Runnable() {
                    public void run() {

                        while (!stop) {
                            for (int i = 0; i < 6; i++) {
                                ImageIcon randomImage3 = symbolList3.get(i).getImage();
                                Image image = randomImage3.getImage();
                                lblImage3.setIcon(randomImage3);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(SlotMachine.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                });

                t1.start();
                t2.start();
                t3.start();

            } else if (btnLabel.equals(
                    "Add coin")) {
                ++credit;
                tfCreditArea.setText(credit + "");
                if (credit == 0) {
                    JOptionPane.showMessageDialog(null, "You cannot play because dont have enough credits");
                }

            } else if (btnLabel.equals(
                    "Bet one")) {
                if (credit > 0) {
                    ++countBet;
                    --credit;
                    tfBetArea.setText(countBet + "");
                    tfCreditArea.setText(credit + "");
                } else {
                    JOptionPane.showMessageDialog(null, "You cannot bet because dont have enough credits");
                }
            } else if (btnLabel.equals(
                    "Bet max")) {
                if (credit >= 3) {
                    countBet += 3;
                    credit -= 3;
                    tfBetArea.setText(countBet + "");
                    tfCreditArea.setText(credit + "");
                } else {
                    JOptionPane.showMessageDialog(null, "You cannot bet because dont have enough credits");
                }
            } else if (btnLabel.equals(
                    "Reset")) {
                credit = credit + countBet;
                countBet = 0;
                tfCreditArea.setText(credit + "");
                tfBetArea.setText(countBet + "");

            } else if (btnLabel.equals(
                    "Show statistics")) {
                average = (double) winCredit / spinNumber;
                //I did chose JOptionPane because it shows required information on message window
                JOptionPane.showMessageDialog(null, "You won " + countWin + " You lost " + countLose + " average " + average);
            } else if (btnLabel.equals(
                    "Save statistics")) {
                average = (double) winCredit / spinNumber;
                inputStatistics(countWin, countLose, average);
            }
        }
    }

    private class MouseHandler implements MouseListener {

        public void mouseClicked(MouseEvent event) {

        }

        public void mouseExited(MouseEvent event) {
        }

        public void mouseEntered(MouseEvent event) {

        }
        //if mouse pressed this metods called
        @Override
        public void mousePressed(MouseEvent e) {
            int sameSymbols = 0;
            //comparing objects
            if (symbolList1.get(0).compareTo(symbolList2.get(0)) == 0) {
                ++sameSymbols;
            }
            if (symbolList1.get(0).compareTo(symbolList3.get(0)) == 0) {
                ++sameSymbols;
            }
            if (symbolList2.get(0).compareTo(symbolList3.get(0)) == 0) {
                ++sameSymbols;
            }
            stop = true;
            if (sameSymbols >= 1) {
                JOptionPane.showMessageDialog(null, "You win");
                credit += countBet;
                winCredit += countBet;
                tfCreditArea.setText(credit + "");
                countWin++;

            } else {
                JOptionPane.showMessageDialog(null, "You lose");
                countBet = 0;
                tfBetArea.setText(countBet + "");
                countLose++;
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SlotMachine ISymbol = new SlotMachine();
        ISymbol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ISymbol.setVisible(true);

        // ISymbol.setSize(2000, 2000);
        ISymbol.pack();
    }

}
