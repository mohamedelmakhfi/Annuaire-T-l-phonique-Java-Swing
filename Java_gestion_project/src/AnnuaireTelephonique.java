// Importer les classes nécessaires
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.List;



public class AnnuaireTelephonique extends JFrame {

    private JLabel labelNom;
    private JTextField textNameprenom;
    private JButton buttonEnregistrer;
    private DefaultListModel<String> modelclient;
    private JList<String> listclient;

    private Connection con;
    private Statement st;
    private ResultSet rs;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public AnnuaireTelephonique(String nom, String sx) {
        getContentPane().setLayout(null);
        setVisible(true);
        labelNom = new JLabel("Bonjour " + sx + " " + nom);
        labelNom.setForeground(new Color(0, 128, 128));
        labelNom.setHorizontalAlignment(SwingConstants.CENTER);
        labelNom.setBounds(179, 43, 400, 50);
        labelNom.setFont(new Font("Poppins", Font.BOLD, 17));
        getContentPane().add(labelNom);
        textNameprenom =  new  JTextField(30);
        textNameprenom.setText(nom);
        
        modelclient = new DefaultListModel<>();

        buttonEnregistrer = new JButton("Enregistrer");
        buttonEnregistrer.setForeground(new Color(0, 128, 128));
        buttonEnregistrer.setBackground(new Color(0, 128, 128));
        buttonEnregistrer.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonEnregistrer.setBounds(334, 472, 100, 30);
        
        buttonEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	saveDataToJson();
            }
        });
        getContentPane().add(buttonEnregistrer);
        
        listclient = new JList<>();
        listclient.setBounds(179, 184, 400, 253);
        getContentPane().add(listclient);
        listclient.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)));
        listclient.setFont(new Font("Poppins", Font.BOLD, 11));
        
        remplirClients();
        
        JLabel lblNewLabel_1 = new JLabel("les Num disponibles :");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Poppins", Font.BOLD, 12));
        lblNewLabel_1.setBounds(308, 133, 153, 29);
        getContentPane().add(lblNewLabel_1);
        
      
        setTitle("Annuaire Telephonique");
        setSize(756, 610);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        seConnecterBD();
    }
    
    public boolean seConnecterBD() {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtele", "root", "");
            st = con.createStatement();
            return true;  
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }

    public void remplirClients() {
        try {
            seConnecterBD();
            rs = st.executeQuery("SELECT id,nom,phone_number FROM clientname WHERE id_name = '" + textNameprenom.getText() + "'");

            while (rs.next()) {
                modelclient.addElement("      " + rs.getString(1) + "    " + rs.getString(2) + "    " + rs.getString(3));
            }

            listclient.setModel(modelclient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void saveDataToJson() {

    	 List<String> dataList = new ArrayList<>();

         for (int i = 0; i < modelclient.getSize(); i++) {
             dataList.add(modelclient.getElementAt(i));
         }

         try (FileWriter fileWriter = new FileWriter("annuaire"+textNameprenom.getText()+ "_" + System.currentTimeMillis() +".json")) {
             fileWriter.write(dataList.toString());
             JOptionPane.showMessageDialog(this, "Data saved to phone_numbers.json", "Success", JOptionPane.INFORMATION_MESSAGE);
         } catch (IOException e) {
             JOptionPane.showMessageDialog(this, "Error saving data to JSON", "Error", JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
         }

        
    }
    
    public void fermerConnexionBD() {
        try {
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fermerConnexionServeur() {
        try {
            out.close();
            in.close();

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

