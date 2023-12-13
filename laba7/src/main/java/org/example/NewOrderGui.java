package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewOrderGui extends JFrame implements ActionListener {
    private Connection connection;

    public NewOrderGui() {
        JFrame frame = new JFrame("Новый заказ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 400);

        JPanel panel = new JPanel();

        JTextField productNameField = new JTextField(10);
        JTextField productDescriptionField = new JTextField(10);
        JTextField productPriceField = new JTextField(10);
        JButton createOrderButton = new JButton("Создать заказ");

        panel.add(new JLabel("Название товара:"), BorderLayout.WEST);
        panel.add(productNameField, BorderLayout.CENTER);
        panel.add(new JLabel("Описание товара:"), BorderLayout.WEST);
        panel.add(productDescriptionField, BorderLayout.CENTER);
        panel.add(new JLabel("Цена товара:"), BorderLayout.WEST);
        panel.add(productPriceField, BorderLayout.CENTER);
        panel.add(createOrderButton, BorderLayout.PAGE_END);


        createOrderButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String productDescription = productDescriptionField.getText();
            String productPrice = productPriceField.getText();
            if (createOrder(productName, productDescription, productPrice)) {
                JOptionPane.showMessageDialog(frame, "Новый заказ успешно создан.", "Успех", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Ошибка при создании нового заказа.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(createOrderButton);
        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    private boolean createOrder(String productName, String productDescription, String productPrice) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba7psp", "root", "qw123e");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при подключении к базе данных.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        String insertProductQuery = "INSERT INTO products (product_name, product_description, product_price) VALUES (?, ?, ?)";
        try (PreparedStatement insertProductStmt = connection.prepareStatement(insertProductQuery)) {
            insertProductStmt.setString(1, productName);
            insertProductStmt.setString(2, productDescription);
            insertProductStmt.setString(3, productPrice);
            insertProductStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
