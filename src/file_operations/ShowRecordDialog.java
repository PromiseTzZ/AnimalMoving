package file_operations;

import object_type.Playler;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/*
    չʾ���а�
 */
public class ShowRecordDialog extends JDialog implements ActionListener {
    File gradeFile;
    JButton clear;
    JTextArea showArea;
    TreeSet<Playler> treeSet;

    public ShowRecordDialog() {
        treeSet = new TreeSet<Playler>();
        showArea = new JTextArea(6, 4);
        showArea.setFont(new Font("����", Font.BOLD, 20));
        clear = new JButton("������а�");
        clear.addActionListener(this);
        add(new JScrollPane(showArea), BorderLayout.CENTER);
        add(clear, BorderLayout.SOUTH);
        setBounds(100, 100, 320, 585);
        setModal(true);
        addWindowListener(new WindowAdapter() {
        });
    }

    public void setGradeFile(File f) {
        gradeFile = f;
        setTitle(f.getName());
    }

    public void showRecord() {
        showArea.setText(null);
        treeSet.clear();
        try {
            RandomAccessFile in = new RandomAccessFile(gradeFile, "rw");
            long fileLength = in.length();
            long readPosition = 0;
            while (readPosition < fileLength) {
                String name = in.readUTF();
                int time = in.readInt();
                readPosition = in.getFilePointer();
                Playler playler = new Playler(name, time);
                treeSet.add(playler);
            }
            in.close();
            for (Playler p : treeSet) {
                showArea.append(p.getName() + "������ʱ�䣨�룩, " + p.getTime());
                showArea.append("\n");
            }
        } catch (IOException exp) {
            System.out.println(exp);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            try {
                File f = gradeFile.getAbsoluteFile();
                gradeFile.delete();
                f.createNewFile();
                showArea.setText("���а����");
            } catch (Exception ee) {
            }
        }
    }
}
