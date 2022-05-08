package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Caculator extends JFrame implements ActionListener {
    //北面的控件
    private JPanel jp_north = new JPanel();
    private JTextField input_text = new JTextField();

    //中间的控件
    private JPanel jp_center = new JPanel();

    public Caculator() throws HeadlessException {
        this.into();
        this.northPart();
        this.centerPart();
    }

    //初始化的方法
    public void into() {
        this.setTitle("计算器");
        this.setSize(450, 650);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocation(730, 200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //添加北面的控件
    public void northPart() {
        jp_north.add(input_text);
        this.add(jp_north, BorderLayout.NORTH);
        this.input_text.setPreferredSize(new Dimension(450, 130));
    }

    //添加中间的按钮
    public void centerPart() {
        String[] keyboard = {
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "0", ".", "^", "/",
                " "," ","AC","="
        };
        this.jp_center.setLayout(new GridLayout(5, 4));
        for (int i = 0; i < 20; i++) {
            String K = keyboard[i];
            JButton button = new JButton();
            if (button.equals(K)) {
                button.setForeground(Color.blue);
            }
            button.setText(K);
            button.setFont(new Font("粗体", Font.BOLD, 30));
            //添加监听事件
            button.addActionListener(this);
            jp_center.add(button);
        }
        this.add(jp_center, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Caculator caculator = new Caculator();
        caculator.setVisible(true);
    }

    private String firstInput = null;
    private String operator = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        String getIn = e.getActionCommand();
        //添加清空键
        if(Objects.equals(getIn, "AC")){
            this.input_text.setText("");
        } else if ("1,2,3,4,5,6,7,8,9,0,.".indexOf(getIn) != -1) {
            this.input_text.setText(input_text.getText() + getIn);
            this.input_text.setFont(new Font("粗体", Font.BOLD, 50));
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);
            //JOptionPane.showInternalMessageDialog(this,getIn);
        } else if (getIn.matches("[+,*,/,^,-]{1}")){
            operator = getIn;
            firstInput = input_text.getText();
            this.input_text.setText("");
        } else if (getIn.equals("=")){
            Double a = Double.valueOf(firstInput);
            Double b = Double.valueOf(this.input_text.getText());
            Double result = null;
            switch (operator) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                case "^":
                    result = Math.pow(a,b);
                    break;
            }
            this.input_text.setText(result.toString());
        }
    }
}