import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import javax.swing.*;
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
    JButton submit,showAll;
    Font f1,f2;
    Statement smt;
    Connection con;

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

        submit.addActionListener(this);
        showAll.addActionListener(this);
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                while (d.next()){
                    String str = d.getString("Name")+" "+d.getString("Age");
                    System.out.println(str);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
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