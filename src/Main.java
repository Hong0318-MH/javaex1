import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    String operation = "";
    String[] calc;
    boolean pt = true;
    String mark;
    double result;
    double num1, num2;

    Main(){

        setTitle("계산기");

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        Font font1 = new Font("맑은고딕", Font.PLAIN, 50);

        JTextField tf = new JTextField();
        tf.setBackground(Color.WHITE);
        tf.setForeground(Color.BLACK);
        tf.setPreferredSize(new Dimension(2, 140));
        p1.setBorder(BorderFactory.createEmptyBorder(7, 7, 0, 7));
        tf.setHorizontalAlignment(SwingConstants.RIGHT);
        tf.setFont(font1);
        tf.setEnabled(false);

        p1.add(tf, BorderLayout.NORTH);

        JTextField tf2 = new JTextField();
        tf2.setBackground(Color.DARK_GRAY);
        tf2.setForeground(Color.WHITE);
        tf2.setPreferredSize(new Dimension(2, 70));
        tf2.setHorizontalAlignment(SwingConstants.RIGHT);
        tf2.setFont(font1);
        tf2.setEnabled(false);

        p1.add(tf2, BorderLayout.SOUTH);


        //계산기 버튼부분
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(5,4,5,5));
        p2.setBackground(Color.WHITE);
        p2.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));//gpt참고 간격을 이격

        //버튼에 들어갈 문자열
        String[] calc = {"+" , "-", "x","/", "7", "8","9", "()", "4","5", "6", "%","1","2","3","π",".","0","=","C"};

        //문자열 인덱스의 위치로 버튼을 생성하는 for문
        JButton buttons[] = new JButton[calc.length];


        for (int i = 0; i < calc.length; i++) {
            if (calc[i] == "C") {
                buttons[i] = new JButton(calc[i]);
                buttons[i].setBackground(Color.ORANGE);
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setFont(font1);
            } else if (calc[i] == "=") {
                buttons[i] = new JButton(calc[i]);
                buttons[i].setBackground(Color.BLUE);
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setFont(font1);
            } else {
                buttons[i] = new JButton(calc[i]);
                buttons[i].setBackground(Color.GRAY);
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setFont(font1);
            }
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            p2.add(buttons[i]);

            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e1) {

                    String buttonValue = e1.getActionCommand();

                    if (buttonValue.equals("C")) {
                        tf.setText("");
                        tf2.setText("");
                        result = 0;
                        operation = "";
                        pt = true;
                    } else if (buttonValue.equals("()")) {
                        if (pt == true) {
                            tf.setText(tf.getText() + "(");
                        } else {
                            tf.setText(tf.getText() + ")");
                        }
                        pt = !pt;
                    } else if (buttonValue.equals("+") || buttonValue.equals("-") || buttonValue.equals("x") || buttonValue.equals("/")) {
                        try {
                            num1 = Double.parseDouble(tf.getText());
                            operation = buttonValue;
                            tf.setText(tf.getText() + buttonValue);
                        } catch (NumberFormatException ex) {
                            tf2.setText("숫자나 괄호 입력");
                        }
                    } else if (buttonValue.equals("=")) {
                        if (!operation.isEmpty()) {
                            try {
                                num2 = Double.parseDouble(tf.getText().split("\\" + operation)[1]);
                                switch (operation) {
                                    case "+":
                                        result = num1 + num2;
                                        break;
                                    case "-":
                                        result = num1 - num2;
                                        break;
                                    case "x":
                                        result = num1 * num2;
                                        break;
                                    case "/":
                                        result = num1 / num2;
                                        break;
                                }
                                tf2.setText("" + result);
                                operation = "";
                            } catch (NumberFormatException ex) {
                                tf2.setText("Error: Invalid Number");
                            }
                        }
                    }  else {
                        tf.setText(tf.getText() + buttonValue);
                    }
                }
            });
        }

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
        }
    }
