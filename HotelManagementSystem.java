// (Import Statements remain the same)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class HotelManagementSystem extends JFrame {
    JTextField nameField, roomField, checkInField, checkOutField, contactField, searchField;
    JTable bookingTable;
    DefaultTableModel tableModel;
    JButton bookButton, clearButton, exitButton, deleteButton, searchButton, exportButton;
    JComboBox<String> roomTypeBox;
    JRadioButton cashRadio, cardRadio;
    ButtonGroup paymentGroup;
    JLabel totalLabel;

    public HotelManagementSystem() {
        setTitle("Hotel Management System");
        setSize(950, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(70, 130, 180)); // steel blue

        JLabel title = new JLabel("Hotel Management System", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(270, 20, 400, 30);
        add(title);

        JLabel nameLabel = new JLabel("Guest Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 80, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 80, 200, 30);
        nameField.setToolTipText("Enter guest's full name");
        add(nameField);

        JLabel contactLabel = new JLabel("Contact No:");
        contactLabel.setForeground(Color.WHITE);
        contactLabel.setBounds(400, 80, 100, 30);
        add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(500, 80, 200, 30);
        contactField.setToolTipText("Enter contact number");
        add(contactField);

        JLabel roomLabel = new JLabel("Room Number:");
        roomLabel.setForeground(Color.WHITE);
        roomLabel.setBounds(50, 130, 100, 30);
        add(roomLabel);

        roomField = new JTextField();
        roomField.setBounds(170, 130, 200, 30);
        add(roomField);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setForeground(Color.WHITE);
        roomTypeLabel.setBounds(400, 130, 100, 30);
        add(roomTypeLabel);

        roomTypeBox = new JComboBox<>(new String[]{"Single", "Double", "Deluxe"});
        roomTypeBox.setBounds(500, 130, 200, 30);
        add(roomTypeBox);

        JLabel checkInLabel = new JLabel("Check-in Date:");
        checkInLabel.setForeground(Color.WHITE);
        checkInLabel.setBounds(50, 180, 100, 30);
        add(checkInLabel);

        checkInField = new JTextField("YYYY-MM-DD");
        checkInField.setBounds(170, 180, 200, 30);
        add(checkInField);

        JLabel checkOutLabel = new JLabel("Check-out Date:");
        checkOutLabel.setForeground(Color.WHITE);
        checkOutLabel.setBounds(400, 180, 120, 30);
        add(checkOutLabel);

        checkOutField = new JTextField("YYYY-MM-DD");
        checkOutField.setBounds(520, 180, 180, 30);
        add(checkOutField);

        JLabel paymentLabel = new JLabel("Payment:");
        paymentLabel.setForeground(Color.WHITE);
        paymentLabel.setBounds(50, 230, 100, 30);
        add(paymentLabel);

        cashRadio = new JRadioButton("Cash");
        cardRadio = new JRadioButton("Card");
        paymentGroup = new ButtonGroup();
        paymentGroup.add(cashRadio);
        paymentGroup.add(cardRadio);

        cashRadio.setBounds(170, 230, 80, 30);
        cardRadio.setBounds(260, 230, 80, 30);
        cashRadio.setBackground(new Color(70, 130, 180));
        cardRadio.setBackground(new Color(70, 130, 180));
        cashRadio.setForeground(Color.WHITE);
        cardRadio.setForeground(Color.WHITE);
        add(cashRadio);
        add(cardRadio);

        // Buttons
        bookButton = new JButton("Book Room");
        bookButton.setBounds(50, 280, 120, 30);
        add(bookButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(190, 280, 100, 30);
        add(clearButton);

        deleteButton = new JButton("Delete Booking");
        deleteButton.setBounds(310, 280, 150, 30);
        add(deleteButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(480, 280, 100, 30);
        add(exitButton);

        exportButton = new JButton("Export to File");
        exportButton.setBounds(600, 280, 150, 30);
        add(exportButton);

        // Search functionality
        searchField = new JTextField();
        searchField.setBounds(50, 330, 200, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(270, 330, 100, 30);
        add(searchButton);

        totalLabel = new JLabel("Total Bookings: 0");
        totalLabel.setBounds(600, 330, 200, 30);
        totalLabel.setForeground(Color.WHITE);
        add(totalLabel);

        // Table
        String[] columns = {"Name", "Contact", "Room No", "Room Type", "Check-in", "Check-out", "Payment"};
        tableModel = new DefaultTableModel(columns, 0);
        bookingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        scrollPane.setBounds(50, 380, 830, 280);
        add(scrollPane);

        // Listeners
        bookButton.addActionListener(e -> {
            String name = nameField.getText();
            String contact = contactField.getText();
            String room = roomField.getText();
            String roomType = roomTypeBox.getSelectedItem().toString();
            String checkIn = checkInField.getText();
            String checkOut = checkOutField.getText();
            String payment = cashRadio.isSelected() ? "Cash" : cardRadio.isSelected() ? "Card" : "";

            if (name.isEmpty() || contact.isEmpty() || room.isEmpty() || checkIn.isEmpty() || checkOut.isEmpty() || payment.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields and select payment method.");
            } else {
                tableModel.addRow(new Object[]{name, contact, room, roomType, checkIn, checkOut, payment});
                JOptionPane.showMessageDialog(null, "Room booked successfully!");
                clearFields();
                updateTotalBookings();
            }
        });

        clearButton.addActionListener(e -> clearFields());

        deleteButton.addActionListener(e -> {
            int selected = bookingTable.getSelectedRow();
            if (selected != -1) {
                tableModel.removeRow(selected);
                updateTotalBookings();
            } else {
                JOptionPane.showMessageDialog(null, "Select a booking to delete.");
            }
        });

        exportButton.addActionListener(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("hotel_bookings.txt"))) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        writer.write(tableModel.getValueAt(i, j) + "\t");
                    }
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(null, "Data exported to hotel_bookings.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error writing to file.");
            }
        });

        searchButton.addActionListener(e -> {
            String searchName = searchField.getText().toLowerCase();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String guest = tableModel.getValueAt(i, 0).toString().toLowerCase();
                if (guest.contains(searchName)) {
                    bookingTable.setRowSelectionInterval(i, i);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No matching guest found.");
        });

        exitButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
    }

    private void clearFields() {
        nameField.setText("");
        contactField.setText("");
        roomField.setText("");
        checkInField.setText("YYYY-MM-DD");
        checkOutField.setText("YYYY-MM-DD");
        paymentGroup.clearSelection();
        roomTypeBox.setSelectedIndex(0);
        searchField.setText("");
    }

    private void updateTotalBookings() {
        totalLabel.setText("Total Bookings: " + tableModel.getRowCount());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HotelManagementSystem().setVisible(true));
    }
}
