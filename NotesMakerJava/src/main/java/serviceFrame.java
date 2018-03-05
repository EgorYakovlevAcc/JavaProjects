import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class serviceFrame extends JFrame {
    String msg;
    public serviceFrame() {
        setTitle("Add new note");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(400, 400));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JLabel labelDate = new JLabel ("Today: " + (new Date()).toString());
        JLabel labelLength = new JLabel ("Max number of symbols is 100. Now you have: ");

        JButton btnAccept = new JButton("Accept");
        btnAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton btnCancel = new JButton("Cancel");

        add(textArea);
        add(labelDate);
        add(labelLength);
        add(btnAccept);
        add(btnCancel);

        setVisible(true);
    }

    String getMsg() {
        return msg;
    }
}
