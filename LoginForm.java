import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, signupButton;

    // These will store credentials for login check
    private static String savedUsername = "sineha";
    private static String savedPassword = "1234";

    public LoginForm() {
        setTitle("Login - Hotel Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(100, 149, 237)); // Cornflower Blue

        JLabel titleLabel = new JLabel("Login Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(100, 20, 200, 30);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 80, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 180, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 130, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 180, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(80, 190, 100, 30);
        add(loginButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(200, 190, 100, 30);
        add(signupButton);

        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals(savedUsername) && pass.equals(savedPassword)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                dispose();
                new HotelManagementSystem().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        });

        signupButton.addActionListener(e -> {
            dispose();
            new SignupForm().setVisible(true);
        });
    }

    // Setter methods to update the saved username and password
    public static void setSavedUsername(String username) {
        savedUsername = username;
    }

    public static void setSavedPassword(String password) {
        savedPassword = password;
    }
}
