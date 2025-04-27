import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupForm extends JFrame 
{
    JTextField usernameField, firstNameField, lastNameField, emailField, ageField;
    JPasswordField passwordField, confirmPasswordField;
    JRadioButton maleRadioButton, femaleRadioButton;
    JButton signupButton, loginButton;

    public SignupForm()
     {
        setTitle("Signup - Hotel Management System");
        setSize(420, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(70, 130, 180)); // Steel Blue

        // Title label
        JLabel titleLabel = new JLabel("Signup Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(100, 30, 200, 30);
        titleLabel.setForeground(Color.RED);
        add(titleLabel);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 60, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 60, 180, 30);
        add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 180, 30);
        add(passwordField);

        // Confirm Password
        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setForeground(Color.WHITE);
        confirmPassLabel.setBounds(50, 140, 120, 30);
        add(confirmPassLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 140, 180, 30);
        add(confirmPasswordField);

        // First Name
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setBounds(50, 180, 100, 30);
        add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(150, 180, 180, 30);
        add(firstNameField);

        // Last Name
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setBounds(50, 220, 100, 30);
        add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(150, 220, 180, 30);
        add(lastNameField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 260, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 260, 180, 30);
        add(emailField);

        // Gender selection
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(50, 300, 100, 30);
        add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setForeground(Color.WHITE);
        maleRadioButton.setBounds(150, 300, 80, 30);
        add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setForeground(Color.WHITE);
        femaleRadioButton.setBounds(230, 300, 100, 30);
        add(femaleRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Signup Button
        signupButton = new JButton("Signup");
        signupButton.setBounds(80, 340, 100, 30);
        add(signupButton);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(200, 340, 100, 30);
        add(loginButton);

        // Action listeners for buttons
        signupButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            String confirmPass = new String(confirmPasswordField.getPassword());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();

            // Validate the inputs
            if (user.isEmpty() || pass.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
            } else if (!pass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
            } else {
                // Save the credentials in LoginForm
                LoginForm.setSavedUsername(user);
                LoginForm.setSavedPassword(pass);

                // Optional: Save the first name, last name, email, and gender if necessary
                JOptionPane.showMessageDialog(null, "Account created successfully! Please login.");

                // Close the signup form and open the login form
                dispose();
                new LoginForm().setVisible(true);
            }
        });

        loginButton.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupForm().setVisible(true));
    }
}
