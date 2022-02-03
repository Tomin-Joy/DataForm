import java.awt.*;
import java.awt.event.*;

public class DataForm {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

}

class App extends Frame implements ActionListener, WindowListener, FocusListener {

    TextField tName;
    TextField tAge;
    TextField tEmail;
    TextField tPhone;
    Button submit;
    Font f1;
    Font f2;

    void run() {
        setSize(450, 600);
        // setResizable(false);
        setLayout(new GridLayout(6, 1));
        setVisible(true);
        setTitle("Data Form V-1.0.2");
        addWindowListener(this);

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

        submit = new Button("Submit");

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

        submit.addActionListener(this);
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

        add(pTitle);
        add(pName);
        add(pAge);
        add(pEmail);
        add(pPhone);
        add(pSubmit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("\nDetails :  \n\n");
        System.out.println("Name         : " + tName.getText());
        System.out.println("Age          : " + tAge.getText());
        System.out.println("Email        : " + tEmail.getText());
        System.out.println("Phone number : " + tPhone.getText());
        System.out.print("\n");
        tAge.setText("");
        tEmail.setText("");
        tName.setText("");
        tPhone.setText("");
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
            dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

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