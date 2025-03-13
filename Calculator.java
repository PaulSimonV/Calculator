import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String num1 = "", num2 = "", operator = "";
    
    public Calculator() {
        setTitle("Advanced Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(50, 50, 50));
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 32));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setBackground(new Color(30, 30, 30));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(textField, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(50, 50, 50));
        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+",
            "%", "√", "x²", "1/x"
        };
        
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(new Color(70, 70, 70));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
            button.addActionListener(this);
            panel.add(button);
        }
        
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (operator.isEmpty()) {
                num1 += command;
                textField.setText(num1);
            } else {
                num2 += command;
                textField.setText(num2);
            }
        } else if (command.equals("C")) {
            num1 = num2 = operator = "";
            textField.setText("");
        } else if (command.equals("=")) {
            if (!num1.isEmpty() && !num2.isEmpty()) {
                double result = calculate(Double.parseDouble(num1), Double.parseDouble(num2), operator);
                textField.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = operator = "";
            }
        } else if (command.equals("√")) {
            if (!num1.isEmpty()) {
                double result = Math.sqrt(Double.parseDouble(num1));
                textField.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = operator = "";
            }
        } else if (command.equals("x²")) {
            if (!num1.isEmpty()) {
                double result = Math.pow(Double.parseDouble(num1), 2);
                textField.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = operator = "";
            }
        } else if (command.equals("1/x")) {
            if (!num1.isEmpty()) {
                double result = 1 / Double.parseDouble(num1);
                textField.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = operator = "";
            }
        } else if (command.equals("%")) {
            if (!num1.isEmpty()) {
                double result = Double.parseDouble(num1) / 100;
                textField.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = operator = "";
            }
        } else {
            if (!num1.isEmpty()) {
                operator = command;
            }
        }
    }
    
    private double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : 0;
            default -> 0;
        };
    }
    
    public static void main(String[] args) {
        new Calculator();
    }
}
