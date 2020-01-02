package file_operations;

import object_type.Playler;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/*
    记录保存
 */
public class Record extends JDialog implements ActionListener {
    int time = 0;
    JTextField yourName;
    JLabel label;
    JButton enter, cancel;
    File gradeFile = null;

    public Record() {
        setBounds(900, 400, 330, 160);
        setResizable(false);
        setModal(true);
        setVisible(false);
        enter = new JButton("确定");
        cancel = new JButton("取消");
        yourName = new JTextField(8);
        yourName.setText("匿名");
        enter.addActionListener(this);
        cancel.addActionListener(this);
        setLayout(new GridLayout(2, 1));
        label = new JLabel();
        add(label);
        JPanel p = new JPanel();
        p.add(yourName);
        p.add(enter);
        p.add(cancel);
        add(p);
    }

    public void setGradeFile(File f) {
        gradeFile = f;
        setTitle("保存记录到" + gradeFile.getName());
        label.setText("恭喜您完成了游戏，保存记录到" + gradeFile.getName());
        validate();
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            LinkedList<Playler> list = new LinkedList<Playler>();
            try {
                RandomAccessFile out = new RandomAccessFile(gradeFile, "rw");
                out.seek(out.length());
                out.writeUTF(yourName.getText());
                out.writeInt(time);
                out.close();
            } catch (Exception ignored) {
            }
            setVisible(false);
        }
        if (e.getSource() == cancel) {
            setVisible(false);
        }
    }
}
