import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RailwayReservationSystem {

    private JFrame frame;
    private JPanel loginPanel;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Map<String, String> userCredentials;
    private BufferedImage backgroundImage;
    private BufferedImage mainBackgroundImage;
    private JToggleButton showPasswordButton;

    public RailwayReservationSystem() {
        userCredentials = new HashMap<>();
        userCredentials.put("Admin", "Admin@123");
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\tharu\\OneDrive\\Documents\\Railway\\bg.jpg"));
            mainBackgroundImage = ImageIO.read(new File("C:\\Users\\tharu\\Downloads\\fk.jpg"));
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            backgroundImage = null;
            mainBackgroundImage = null;
        }
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Railway Reservation System");
        frame.setBounds(100, 100, 500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        frame.getContentPane().add(loginPanel, "loginPanel");
        loginPanel.setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        usernameField = new JTextField(25);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));

        passwordField = new JPasswordField(25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));

        showPasswordButton = new JToggleButton("\uD83D\uDC41"); // Eye symbol
        showPasswordButton.setFont(new Font("Arial", Font.PLAIN, 12));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        loginPanel.add(usernameLabel, gbc);

        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        loginPanel.add(showPasswordButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    showMainPanel();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                }
            }
        });

        showPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordButton.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('•'); // Hide password
                }
            }
        });

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (mainBackgroundImage != null) {
                    g.drawImage(mainBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        frame.getContentPane().add(mainPanel, "mainPanel");
        mainPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Railway Reservation System!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

    }

    private void showMainPanel() {
        CardLayout cl = (CardLayout) (frame.getContentPane().getLayout());
        cl.show(frame.getContentPane(), "mainPanel");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RailwayReservationSystem window = new RailwayReservationSystem();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}