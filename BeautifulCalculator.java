import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BeautifulCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    private double num1, num2, result;
    private char operator;

    public BeautifulCalculator() {
        setTitle("Beautiful Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        currentInput = new StringBuilder();

        // ===== Display =====
        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(240, 240, 240));
        display.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(display, BorderLayout.NORTH);

        // ===== Buttons Panel =====
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(30, 30, 30));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(60, 63, 65));
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]") || cmd.equals(".")) {
            currentInput.append(cmd);
            display.setText(currentInput.toString());
        } else if (cmd.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(currentInput.toString());
                operator = cmd.charAt(0);
                currentInput.setLength(0);
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(currentInput.toString());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error: /0");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (cmd.equals("C")) {
            currentInput.setLength(0);
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BeautifulCalculator::new);
    }
}
