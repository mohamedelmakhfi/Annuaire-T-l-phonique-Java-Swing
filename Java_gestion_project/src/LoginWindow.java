// Code pour une fenêtre d'authentification et de login en java swing
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener {

    // Déclaration des composants graphiques
    private JLabel labelUsername, labelPassword;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JButton buttonLogin, buttonCancel;
    private JPanel panel;
// je veux text ?

    // Déclaration des variables pour la connexion à la base de données
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    // Déclaration des variables pour la communication avec le serveur
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    // Constructeur de la classe
    public LoginWindow() {
        // Initialisation des composants graphiques
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        labelUsername = new JLabel("Nom d'utilisateur:");
        labelPassword = new JLabel("Mot de passe:");
        textFieldUsername = new JTextField(20);
        passwordField = new JPasswordField(20);
        buttonLogin = new JButton("Se connecter");
        buttonCancel = new JButton("Annuler");
        
        buttonLogin.setForeground(new Color(0, 128, 128));
        buttonLogin.setBackground(new Color(0, 128, 128));
        buttonLogin.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonLogin.setBounds(334, 472, 100, 30);
        
        buttonCancel.setForeground(new Color(0, 128, 128));
        buttonCancel.setBackground(new Color(0, 128, 128));
        buttonCancel.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonCancel.setBounds(334, 472, 100, 30);
        
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Modification du style
        Font font = new Font("Poppins", Font.PLAIN, 14); // Remplacez "Poppins" par la police souhaitée

        labelUsername.setFont(font);
        labelPassword.setFont(font);
        textFieldUsername.setFont(font);
        passwordField.setFont(font);
        buttonLogin.setFont(font);
        buttonCancel.setFont(font);

       
        panel = new JPanel(new GridBagLayout());
        

        // Ajout des composants avec GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelUsername, gbc);

        gbc.gridx = 1;
        panel.add(textFieldUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelPassword, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonLogin, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(buttonCancel, gbc);

        buttonLogin.addActionListener(this);
        buttonCancel.addActionListener(this);

        add(panel);

        setTitle("Fenêtre d'authentification :");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);



        connection = null;
        statement = null;
        resultSet = null;
    }

    // Méthode pour gérer les événements des boutons
    public void actionPerformed(ActionEvent e) {
        // Si le bouton "Se connecter" est cliqué
        if (e.getSource() == buttonLogin) {
            // Récupération du nom d'utilisateur et du mot de passe saisis
            String username = textFieldUsername.getText();
            String password = new String(passwordField.getPassword());

            // Vérification de la validité des données saisies
            if (username.isEmpty() || password.isEmpty()) {
                // Affichage d'un message d'erreur
                JOptionPane.showMessageDialog(this, "Veuillez saisir votre nom d'utilisateur et votre mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                // Tentative de connexion à la base de données
                try {
                    // Chargement du pilote JDBC
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Etablissement de la connexion
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtele", "root", "");

                    // Préparation de la requête SQL
                    statement = connection.prepareStatement("SELECT username,sexe,celibataire  FROM login WHERE username = ? AND password = ?");

                    // Affectation des paramètres
                    statement.setString(1, username);
                    statement.setString(2, password);

                    // Exécution de la requête
                    resultSet = statement.executeQuery();

                    // Vérification du résultat
                    if (resultSet.next()) {
                    	String name = resultSet.getString("username");
                        String sexe = resultSet.getString("sexe");
                        boolean cele = resultSet.getBoolean("celibataire");

                        
                        if (sexe.equals("M")) {
                            sexe = "Mr";
                        } else if (cele && sexe.equals("F")) {
                            sexe = "Mlle";
                        } else {
                            sexe = "Mme";
                        }
                        // Affichage d'un message de confirmation
                        JOptionPane.showMessageDialog(this, "Vous êtes connecté.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                        // Tentative de connexion au serveur
                        dispose();
                        new AnnuaireTelephonique(name , sexe);

                    } else {
                        // Affichage d'un message d'erreur
                        JOptionPane.showMessageDialog(this, "Le nom d'utilisateur ou le mot de passe est incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    // Fermeture de la connexion à la base de données
                    connection.close();

                } catch (ClassNotFoundException ex) {
                    // Affichage d'un message d'erreur
                    JOptionPane.showMessageDialog(this, "Le pilote JDBC n'est pas trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    // Affichage d'un message d'erreur
                    JOptionPane.showMessageDialog(this, "Impossible de cennecte a la base de donnees", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        // Si le bouton "Annuler" est cliqué
        if (e.getSource() == buttonCancel) {
            // Fermeture de la fenêtre
            dispose();
        }
    }



    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        // Création d'une instance de la classe LoginWindow
        new LoginWindow();
    }
}
