package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderManagementApp extends JFrame implements ActionListener {
    private JTextArea resultTextArea;
    private Connection connection;

    public OrderManagementApp() {
        super("Приложение");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel newOrderPanel = new JPanel();
        JButton createNewOrderButton = new JButton("Сформировать новый заказ");
        createNewOrderButton.addActionListener(this);
        newOrderPanel.add(createNewOrderButton);


        // Создание кнопки "Все заказы"
        JButton allOrdersButton = new JButton("Все заказы");
        allOrdersButton.addActionListener(this);
        // Добавление кнопки на панель содержимого окна
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(allOrdersButton);


        resultTextArea = new JTextArea(25, 100);
        resultTextArea.setEditable(false);
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.add(resultTextArea);

        add(newOrderPanel);
        add(allOrdersButton);
        add(textAreaPanel);
        setVisible(true);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba7psp", "root", "qw123e");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при подключении к базе данных.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new OrderManagementApp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Сформировать новый заказ")) {
            NewOrderGui newOrderGui = new NewOrderGui();
        } else if (e.getActionCommand().equals("Все заказы")){
            getAllOrders();
        }
    }

    private void getAllOrders() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("Все заказы:\n");

            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                resultBuilder.append(productName).append(" ");
                String productDescription = resultSet.getString("product_description");
                resultBuilder.append(productDescription).append(" ");
                String productPrice = resultSet.getString("product_price");
                resultBuilder.append(productPrice).append('p').append("\n");
            }

            resultTextArea.setText(resultBuilder.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при получении заказов.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
