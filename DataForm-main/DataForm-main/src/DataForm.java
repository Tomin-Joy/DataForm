import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DataForm {
    public static void main(String[] args) throws  Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/","cipherguys","ATM");
        Statement smt = con.createStatement();
        try{
            smt.execute("use Data");
            System.out.println("found");

        }catch (Exception ex){
            System.out.println("not found");
            smt.execute("Create database Data");
            smt.execute("use Data");
            smt.execute("create table Data(Name varchar(50),Age varchar(50),Email varchar(50),Phone varchar(20))");

        }
        App app = new App();
        app.run(con,smt);

    }

}

class App extends JFrame implements ActionListener, FocusListener {

    TextField tName,tAge,tEmail,tPhone;
    JButton submit,showAll,del;
    Font f1,f2;
    Statement smt;
    Connection con;
    JFrame delBox = new JFrame();


    void run(Connection con,Statement smt) {
        this.smt=smt;
        this.con=con;
        
        Panel pTitle = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pName = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pAge = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pEmail = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pPhone = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pSubmit = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));

        f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 14);
        f2 = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
        Font fTitle = new Font(Font.DIALOG_INPUT, Font.BOLD, 34);
        Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 18);


        Color c1 = new Color(201, 201, 201);

        Label title = new Label("Enter Details", 1);
        Label name = new Label("Name   : ", 2);
        Label age = new Label("Age    : ", 2);
        Label email = new Label("Email  : ", 2);
        Label phone = new Label("Phone  : ", 2);

        submit = new JButton("Submit");
        showAll = new JButton("Show all");
        del = new JButton("Delete");

        tName = new TextField("Enter name here..", 20);
        tAge = new TextField("Enter age here..", 20);
        tEmail = new TextField("Enter email here..", 20);
        tPhone = new TextField("Enter phone number here..", 20);

        tName.setBackground(c1);
        tAge.setBackground(c1);
        tEmail.setBackground(c1);
        tPhone.setBackground(c1);

        submit.setFont(fLabel);
        submit.setBackground(Color.GREEN);
        submit.setForeground(Color.WHITE);
        showAll.setFont(fLabel);
        showAll.setBackground(Color.BLUE);
        showAll.setForeground(Color.WHITE);
        del.setFont(fLabel);
        del.setBackground(Color.RED);
        del.setForeground(Color.WHITE);

        submit.addActionListener(this);
        showAll.addActionListener(this);
        del.addActionListener(this);
        tName.addFocusListener(this);
        tAge.addFocusListener(this);
        tEmail.addFocusListener(this);
        tPhone.addFocusListener(this);

        title.setFont(fTitle);
        name.setFont(fLabel);
        age.setFont(fLabel);
        email.setFont(fLabel);
        phone.setFont(fLabel);
        tName.setFont(f1);
        tAge.setFont(f1);
        tEmail.setFont(f1);
        tPhone.setFont(f1);

        pTitle.add(title);
        pName.add(name);
        pName.add(tName);
        pAge.add(age);
        pAge.add(tAge);
        pEmail.add(email);
        pEmail.add(tEmail);
        pPhone.add(phone);
        pPhone.add(tPhone);
        pSubmit.add(submit);
        pSubmit.add(del);
        pSubmit.add(showAll);

        add(pTitle);
        add(pName);
        add(pAge);
        add(pEmail);
        add(pPhone);
        add(pSubmit);

        setSize(500, 650);
        // setResizable(false);
        setLayout(new GridLayout(6, 1));
        setVisible(true);
        setTitle("Data Form V-1.0.2");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if(b==submit){
            System.out.print("\nDetails :  \n\n");
            System.out.println("Name         : " + tName.getText());
            System.out.println("Age          : " + tAge.getText());
            System.out.println("Email        : " + tEmail.getText());
            System.out.println("Phone number : " + tPhone.getText());
            System.out.print("\n");
            try {
                String query = String.format("insert into Data values(\"%s\",\"%s\",\"%s\",\"%s\")",tName.getText(),tAge.getText(),tEmail.getText(),tPhone.getText());
                smt.execute(query);
            } catch (MysqlDataTruncation ex){
                JOptionPane.showMessageDialog(this,"Data too long");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            tAge.setText("Enter age here..");
            tEmail.setText("Enter email here..");
            tName.setText("Enter name here..");
            tPhone.setText("Enter phone number here..");
        }else if(b==showAll){
            try {
                ResultSet d = smt.executeQuery("select * from data;");
                String[] columnNames = {"Name", "Age", "Email", "Phone"};
                JFrame detailsBox = new JFrame();
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNames);
                JTable details = new JTable();
                details.setModel(model);
                detailsBox.setLayout(new FlowLayout());
                detailsBox.setSize(500,400);
                details.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                details.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(details);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                detailsBox.setVisible(true);
                detailsBox.add(scroll);

                while (d.next()){
                    String str = d.getString("Name")+" "+d.getString("Age");
                    String roll = d.getString("Name");
                    String name = d.getString("Age");
                    String cl = d.getString("Email");
                    String sec = d.getString("Phone");
                    model.addRow(new Object[]{roll, name, cl, sec});

                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }else if(b==del){
            delBox.setSize(400,400);
            delBox.setVisible(true);
            delBox.setLayout(new BorderLayout());
            JLabel tittle = new JLabel();
        }
    }


    @Override
    public void focusGained(FocusEvent e) {
        TextField t= (TextField) e.getSource();
        t.selectAll();
        t.setFont(f2);
    }

    @Override
    public void focusLost(FocusEvent e) {
        TextField t= (TextField) e.getSource();
        t.setFont(f1);
    }
}