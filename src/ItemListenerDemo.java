
import java.awt.*;
import java.awt.event.*;



public class ItemListenerDemo extends Frame implements ItemListener {
    Checkbox checkbox1, checkbox2;
    Label label;

    public ItemListenerDemo() {
        setTitle("hi");
        setLayout(null);
        setVisible(true);
        setSize(400, 400);
        label = new Label();
        label.setAlignment(Label.CENTER);
        label.setSize(400, 100);
        add(label);
        checkbox1 = new Checkbox("C++");
        checkbox1.setBounds(100, 100, 100, 50);
        checkbox1.setBackground(Color.BLACK);
        checkbox1.setForeground(Color.WHITE);

        checkbox2 = new Checkbox("Java");
        checkbox2.setBounds(100, 150, 50, 50);
        add(checkbox2);
        add(checkbox1);
        checkbox1.addItemListener(this);
        checkbox2.addItemListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == checkbox1) {
            Boolean isChecked = checkbox1.getState();
            if (isChecked) {
                label.setText("c++ checked");
            } else {
                label.setText("C++ unchecked");
            }
        } else {
            Boolean isChecked = checkbox1.getState();
            if (isChecked) {
                label.setText("java checked");
            } else {
                label.setText("java unchecked");
            }
        }

    }
    public static void main(String[] args) {
        new ItemListenerDemo();
    }
}



