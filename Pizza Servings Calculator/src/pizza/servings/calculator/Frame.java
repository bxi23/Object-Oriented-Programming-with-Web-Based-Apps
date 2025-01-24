/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.servings.calculator;

/**
 *
 * @author ianno
 */
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Frame extends JFrame {

    private final JLabel title;
    private final JLabel qLine;
    private final JTextField size;
    private final JButton calculate;
    private final JLabel output;
    private JPanel line2 = new JPanel();

    public Frame() {

        super("Pizza Servings Calculator");

        title = new JLabel("Pizza Servings Calculator", SwingConstants.CENTER);
        title.setForeground(Color.red);
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));

        qLine = new JLabel("Enter the size of the pizza in inches:");
        line2.add(qLine);

        size = new JTextField(4);
        size.setEditable(true);
        line2.add(size);

        calculate = new JButton("Calculate Servings");

        output = new JLabel("", SwingConstants.CENTER);

        setLayout(new GridLayout(4, 1));
        add(title);
        add(line2);
        add(calculate);
        add(output);

        PizzaSizeHandler handler = new PizzaSizeHandler();
        calculate.addActionListener(handler);
    }

    private class PizzaSizeHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String p_size = size.getText();

            double servings = (Double.parseDouble(p_size) / 8) * (Double.parseDouble(p_size) / 8);

            output.setText(String.format("%s%s%s%.2f%s",
                    "A ", p_size, " inch pizza will serve ", servings, " people"));

        }
    }
}
