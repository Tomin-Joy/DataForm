import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataForm {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

}

class App extends Frame implements ActionListener {

    TextField tName;
    TextField tAge;
    TextField tEmail;
    TextField tPhone;

    void run() {
        setSize(800, 600);
        // setResizable(false);
        setLayout(new GridLayout(6, 1));
        setVisible(true);

        Panel pTitle = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pName = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pAge = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pEmail = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pPhone = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        Panel pSubmit = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 20));

        Font f1 = new Font(Font.DIALOG_INPUT, Font.BOLD, 34);
        Font f2 = new Font(Font.DIALOG, Font.PLAIN, 18);
        Font f3 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 14);

        Color c1 = new Color(201, 201, 201);

        Label title = new Label("Enter Details", 1);
        Label name = new Label("Name   : ", 2);
        Label age = new Label("Age    : ", 2);
        Label email = new Label("Email  : ", 2);
        Label phone = new Label("Phone  : ", 2);

        Button submit = new Button("Submit");

        tName = new TextField("Enter name here..", 20);
        tAge = new TextField("Enter age here..", 20);
        tEmail = new TextField("Enter email here..", 20);
        tPhone = new TextField("Enter phone number here..", 20);

        tName.setBackground(c1);
        tAge.setBackground(c1);
        tEmail.setBackground(c1);
        tPhone.setBackground(c1);

        submit.setFont(f2);
        submit.setBackground(Color.GREEN);
        submit.addActionListener(this);

        title.setFont(f1);
        name.setFont(f2);
        age.setFont(f2);
        email.setFont(f2);
        phone.setFont(f2);
        tName.setFont(f3);
        tAge.setFont(f3);
        tEmail.setFont(f3);
        tPhone.setFont(f3);

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
        System.out.println("Name         : " + tName.getText());
        System.out.println("Age          : " + tAge.getText());
        System.out.println("Email        : " + tEmail.getText());
        System.out.println("Phone number : " + tPhone.getText());
        tAge.setText("");
        tEmail.setText("");
        tName.setText("");
        tPhone.setText("");

    }
}