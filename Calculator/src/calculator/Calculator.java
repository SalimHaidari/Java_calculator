package calculator;

import javax.swing.*;
import javax.swing.JMenuBar;
import java.awt.event.*;
import java.awt.*;

public class Calculator extends JFrame {

    private JPanel display = new JPanel(new GridLayout(2, 1));
    private JPanel operation = new JPanel(new GridLayout(5, 1));
    private JPanel buttons = new JPanel(new GridLayout(5, 3));

    private JMenuBar menu;
    private JMenu option, setting;
    private JMenuItem about, exit, showHistory;
    private JTextField show;
    private JScrollPane history;
    private JTextArea historyText;

    //-------------Buttons------------- 
    private JButton[] numberBtns = new JButton[15];
    private String[] numberText = {"C", "CE", "P", "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "+/-"};
    private JButton[] operationBtns = new JButton[5];
    private String[] operationText = {"/", "*", "-", "+", "="};
    private double firstNumber, secondNumber, result;
    private String operationSign;

    public Calculator() {
        setFrameSettting();
        setMenu();
        setNumberButtons();
        setOperationButtons();

    }

    public void setNumberButtons() {
        for (int i = 0; i < numberBtns.length; i++) {
            numberBtns[i] = new JButton(numberText[i]);
            buttons.add(numberBtns[i]);
            numberBtns[i].setFont(new Font("arial", Font.BOLD, 22));

        }
        for (int i = 3; i < numberBtns.length; i++) {
            numberBtns[i].setBackground(Color.white);
            numberBtns[i].setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));

        }
        for (int i = 0; i < 3; i++) {
            numberBtns[i].setBackground(Color.decode("#f0f0f0"));
            numberBtns[i].setForeground(Color.DARK_GRAY);

        }
        numberBtns[numberBtns.length - 2].setBackground(Color.WHITE);
        numberBtns[numberBtns.length - 2].setForeground(Color.BLACK);
        numberBtns[numberBtns.length - 2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                show.setText(show.getText() + numberBtns[numberBtns.length - 2].getText());
            }
        });

        for (int i = 3; i < numberBtns.length - 2; i++) {
            int index = i;
            numberBtns[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    show.setText(show.getText() + numberBtns[index].getText());

                }
            });
        }
        numberBtns[0].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                show.setText("0");
            }
        });
//----------------one by one Clearing-----
        numberBtns[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txt = show.getText();
                if (txt.length() == 1) {
                    show.setText("0");
                } else {
                    txt = txt.substring(0, txt.length() - 1);
                    show.setText(txt);
                }
            }
        });

//   ---power Fanctionalty-------
        numberBtns[2].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                double d1 = Double.parseDouble(show.getText());
                show.setText(Math.pow(d1, 2) + "");
            }
        });

        numberBtns[numberBtns.length - 1].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                double d = Double.parseDouble(show.getText());
                d *= -1; //d= d*-1;
                show.setText(d + "");
            }
        });

    }





    public void setOperationButtons() {
        for (int i = 0; i < operationBtns.length; i++) {
            operationBtns[i] = new JButton(operationText[i]);
            operation.add(operationBtns[i]);
            operationBtns[i].setBackground(Color.decode("#f0f0f0"));
            operationBtns[i].setForeground(Color.darkGray);
            operationBtns[i].setFont(new Font("arial", Font.BOLD, 20));

        }
        for (int i = 0; i < operationBtns.length - 1; i++) {
            int index = i;
            operationBtns[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    operationSign = "";
                    operationSign = operationBtns[index].getText();
                    firstNumber = Double.parseDouble(show.getText());

                }
            });

        }

        operationBtns[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondNumber = Double.parseDouble(show.getText());
                switch (operationSign) {
                    case "+": {
                        result = firstNumber + secondNumber;
                        show.setText(result + "");
                        break;
                    }
                    case "-": {
                        result = firstNumber - secondNumber;
                        show.setText(result + "");
                        break;
                    }
                    case "*": {
                        result = firstNumber * secondNumber;
                        show.setText(result + "");
                        break;
                    }
                    case "/":
                        result = firstNumber / secondNumber;
                        show.setText(result + "");
                        break;

                }
                historyText.append(firstNumber + " " + operationSign + " " + secondNumber + "=" + result + "\n");

            }
        });
        //unknow for me ---------------------/////
        for (int i = 0; i < numberBtns.length - 2; i++) {
            int index = i;
            numberBtns[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Double d = Double.parseDouble(show.getText());
                    if (d == 0 || d == firstNumber || d == result) {
                        show.setText("");
                    }

                }
            });

        }

    }

     public void setMenu() {
        menu = new JMenuBar();
        setJMenuBar(menu);
        option = new JMenu("Options");
        setting = new JMenu("Setting");
        about = new JMenuItem("About");
        exit = new JMenuItem("Exit");
        showHistory = new JMenuItem("Show History");

        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new About().setVisible(true);
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        option.add(about);
        option.add(exit);
        setting.add(showHistory);

        menu.add(option);
        menu.add(setting);

    }

    public void setFrameSettting() {
        setTitle("SalimHaidari-Calculator");
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display.setBackground(Color.decode("#e6e6e6"));

        show = new JTextField("0", 9);
        show.setFont(new Font("arial", Font.PLAIN, 25));
        show.setBackground(Color.decode("#e6e6e6"));
        show.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
        show.setHorizontalAlignment(JTextField.RIGHT);
         show.setForeground(Color.black);
        show.setEnabled(false);

        historyText = new JTextArea(3, 3);
        history = new JScrollPane(historyText);
        history.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
        historyText.setBackground(Color.decode("#e6e6e6"));
        historyText.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
        historyText.setFont(new Font("arial", Font.PLAIN, 12));
        historyText.setEnabled(false);

        display.setForeground(Color.black);
        display.add(history);
        display.add(show);
        operation.setBackground(Color.decode("#e6e6e6"));
        buttons.setBackground(Color.decode("#e6e6e6"));

        add(display, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(operation, BorderLayout.EAST);
        setSize(300, 400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }

    class About extends javax.swing.JFrame {

        About() {
            setSize(300, 400);
            ImageIcon img = new ImageIcon(getClass().getResource("salim.jpg"));
            JLabel lb = new JLabel(img);
            add(lb);

        }

    }
}
