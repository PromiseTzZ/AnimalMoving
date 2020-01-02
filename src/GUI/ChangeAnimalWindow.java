package GUI;

import file_operations.ShowRecordDialog;
import object_type.Animal;
import object_type.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.filechooser.*;

/*
    ��JFrame������
 */

public class ChangeAnimalWindow extends JFrame implements ActionListener {
    int amountOfAnimal = 6;
    int distance = 80;
    Animal[] animal;
    object_type.Point[] point;
    HandleMouse handleMouse;
    File leftImageFile, rightImageFile;
    File fileOneGrade, fileTwoGrade, fileThreeGrade;
    JButton renew, quit;
    JMenuBar bar;
    JMenu menuGrade, menuImage, menuHero, menuabout;
    JMenuItem oneGradeResult, twoGradeResult, threeGradeResult;
    JMenuItem oneGradeItem, twoGradeItem, threeGradeItem;
    JMenuItem leftIamge, rightIamge, defaultImage;
    JMenuItem about, rules;

    JPanel pCenter;
    ShowRecordDialog showDiolag;

    ChangeAnimalWindow() {

        //����Ӣ�۰����ݴ����ļ�����
        fileOneGrade = new File("resource/����Ӣ�����а�.txt");
        fileTwoGrade = new File("resource/�м�Ӣ�����а�.txt");
        fileThreeGrade = new File("resource/�߼�Ӣ�����а�.txt");

        //�м�����
        pCenter = new JPanel();
        pCenter.setBackground(Color.white);
        pCenter.setLayout(null);

        handleMouse = new HandleMouse();

        //todo ָ��Ĭ�϶���ͼƬĿ¼
        leftImageFile = new File("resource/cat.jpg");
        rightImageFile = new File("resource/dog.jpg");
        this.init();

        //�˵���
        bar = new JMenuBar();
        menuGrade = new JMenu("ѡ����Ϸ�Ѷ�");
        menuImage = new JMenu("�Զ�������ͼ��");
        menuHero = new JMenu("Ӣ�۰�(���а�)");
        menuabout = new JMenu("����");

        oneGradeItem = new JMenuItem("��    ��");
        twoGradeItem = new JMenuItem("��    ��");
        threeGradeItem = new JMenuItem("��    ��");

        leftIamge = new JMenuItem("���涯���ͼ��");
        rightIamge = new JMenuItem("���涯���ͼ��");
        defaultImage = new JMenuItem("�ػָ�Ĭ��ͼ��");

        oneGradeResult = new JMenuItem("����Ӣ�۰�");
        twoGradeResult = new JMenuItem("�м�Ӣ�۰�");
        threeGradeResult = new JMenuItem("�߼�Ӣ�۰�");

        rules = new JMenuItem("��Ϸ����");
        about = new JMenuItem("����˵��");

        menuGrade.add(oneGradeItem);
        menuGrade.add(twoGradeItem);
        menuGrade.add(threeGradeItem);

        menuImage.add(leftIamge);
        menuImage.add(rightIamge);
        menuImage.add(defaultImage);
        menuHero.add(oneGradeResult);
        menuHero.add(twoGradeResult);
        menuHero.add(threeGradeResult);

        menuabout.add(rules);
        menuabout.add(about);

        bar.add(menuGrade);
        bar.add(menuImage);
        bar.add(menuHero);
        bar.add(menuabout);

        this.setJMenuBar(bar);

        menuabout.addActionListener(this);

        oneGradeItem.addActionListener(this);
        twoGradeItem.addActionListener(this);
        threeGradeItem.addActionListener(this);

        leftIamge.addActionListener(this);
        rightIamge.addActionListener(this);
        defaultImage.addActionListener(this);

        oneGradeResult.addActionListener(this);
        twoGradeResult.addActionListener(this);
        threeGradeResult.addActionListener(this);

        rules.addActionListener(this);
        about.addActionListener(this);

        //JPanel�����а�ť
        renew = new JButton("���¿�ʼ");
        renew.addActionListener(this);
        quit = new JButton("����");
        quit.addActionListener(this);

        JPanel north = new JPanel();
        north.add(renew);
        north.add(quit);

        //��JFrame���ڵײ���Ӱ�ť
        this.add(north, BorderLayout.AFTER_LAST_LINE);
        //����ͼƬ
        this.add(pCenter, BorderLayout.CENTER);

        //�����ɼ�ʱ���Ĵ�����ӵ�����
        JPanel south = new JPanel();
        south.add(handleMouse);
        this.add(south, BorderLayout.NORTH);

        //todo Frame������������
        this.setVisible(true);
        this.setTitle("AnimalJump");
        this.setBackground(Color.white);
        this.setBounds(600, 400, 710, 300);
        this.validate();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        if (!fileOneGrade.exists()) {
            try {
                fileOneGrade.createNewFile();
            } catch (IOException ignored) {
            }
        }
        if (!fileTwoGrade.exists()) {
            try {
                fileTwoGrade.createNewFile();
            } catch (IOException ignored) {
            }
        }
        if (!fileThreeGrade.exists()) {
            try {
                fileThreeGrade.createNewFile();
            } catch (IOException ignored) {
            }
        }
        handleMouse.gradeFile = fileOneGrade;
        showDiolag = new ShowRecordDialog();
    }


    //todo ��Ϸ���Ĵ���
    public void init() {
        animal = new Animal[amountOfAnimal];
        point = new object_type.Point[amountOfAnimal + 1];

        int space = distance;
        for (int i = 0; i < point.length; i++) {
            point[i] = new object_type.Point(space, 100);
            space = space + distance;
        }
        for (int i = 0; i < animal.length; i++) {
            animal[i] = new Animal();
            animal[i].addMouseListener(handleMouse);
            if (i < animal.length / 2) {
                animal[i].setIsLeft(true);
            } else {
                animal[i].setIsLeft(false);
            }
        }
        for (int i = 0; i < animal.length; i++) {
            animal[i].setSize(distance * 6 / 7, distance * 3 / 4);
            int w = animal[i].getBounds().width;
            int h = animal[i].getBounds().height;
            pCenter.add(animal[i]);
            if (i < animal.length / 2) {
                animal[i].setIsLeft(true);
                animal[i].setLeftImage(leftImageFile);
                animal[i].repaint();
                animal[i].setLocation(point[i].getX() - w / 2, point[i].getY() - h);
                animal[i].setAtPoint(point[i]);
                point[i].setThisAnimal(animal[i]);
                point[i].setIsHaveAnimal(true);
            } else {
                animal[i].setIsLeft(false);
                animal[i].setRightImage(rightImageFile);
                animal[i].repaint();
                animal[i].setLocation(point[i + 1].getX() - w / 2, point[i + 1].getY() - h);
                animal[i].setAtPoint(point[i + 1]);
                point[i + 1].setThisAnimal(animal[i]);
                point[i + 1].setIsHaveAnimal(true);
            }
        }
        handleMouse.setPoint(point);
        handleMouse.setCountTime(true);
    }

    public void setAmountOfAnimal(int m) {
        if (m >= 2 && m % 2 == 0)
            amountOfAnimal = m;
    }

    public void removeAnimal() {
        for (Point value : point) {
            if (value.getThisAnimal() != null)
                pCenter.remove(value.getThisAnimal());
        }
        pCenter.validate();
        pCenter.repaint();
    }

    //�ؿ�
    public void needDoing() {
        init();
        handleMouse.initStep();
        handleMouse.initSpendTime();
        handleMouse.setCountTime(true);
    }

    //todo �������¼�
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == oneGradeItem) {
            handleMouse.gradeFile = fileOneGrade;
            distance = 80;
            removeAnimal();
            setAmountOfAnimal(6);
            needDoing();
        } else if (e.getSource() == twoGradeItem) {
            handleMouse.gradeFile = fileTwoGrade;
            distance = 70;
            removeAnimal();
            setAmountOfAnimal(8);
            needDoing();
        } else if (e.getSource() == threeGradeItem) {
            handleMouse.gradeFile = fileThreeGrade;
            distance = 60;
            removeAnimal();
            setAmountOfAnimal(10);
            needDoing();
        } else if (e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "��������ƶ�С��Ϸ�������ƻ�����������");
        } else if (e.getSource() == renew) {//���¿�ʼ
            removeAnimal();
            needDoing();
        } else if (e.getSource() == rules) {//��Ϸ����
            JOptionPane.showMessageDialog(null, "1.��ϷĿ�꣺��������ߵĶ�����ұߵĶ��ｻ��λ��\n" +
                    "2.ÿ��ÿ������ֻ���ƶ�һ���������һ����������ƶ�������ӻ��������ո�Ͷ������\n" +
                    "3.�������ͼƬ��ʼ��Ϸ���Զ���ʱ");
        } else if (e.getSource() == quit) {//����
            ArrayList<Integer> step = handleMouse.getStep();
            int length = step.size();
            int start = -1, end = -1;
            if (length >= 2) {
                end = step.get(length - 1);
                start = step.get(length - 2);
                step.remove(length - 1);
                step.remove(length - 2);
                Animal ani = point[end].getThisAnimal();
                int w = ani.getBounds().width;
                int h = ani.getBounds().height;
                ani.setLocation(point[start].getX() - w / 2, point[start].getY() - h);
                ani.setAtPoint(point[start]);
                point[start].setThisAnimal(ani);
                point[start].setIsHaveAnimal(true);
                point[end].setIsHaveAnimal(false);
            }
        } else if (e.getSource() == leftIamge) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                leftImageFile = file;
                for (Animal value : animal) {
                    if (value.getIsLeft()) {
                        value.setLeftImage(leftImageFile);
                        value.repaint();
                    }
                }
            }
        } else if (e.getSource() == rightIamge) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                rightImageFile = file;
                for (Animal value : animal) {
                    if (!value.getIsLeft()) {
                        value.setRightImage(rightImageFile);
                        value.repaint();
                    }
                }
            }
        } else if (e.getSource() == defaultImage) {
            leftImageFile = new File("resource/dog.jpg");
            rightImageFile = new File("resource/cat.jpg");
            for (Animal value : animal) {
                if (value.getIsLeft())
                    value.setLeftImage(leftImageFile);
                else
                    value.setRightImage(rightImageFile);
                value.repaint();
            }
        } else if (e.getSource() == oneGradeResult) {
            showDiolag.setGradeFile(fileOneGrade);
            showDiolag.showRecord();
            showDiolag.setVisible(true);
        } else if (e.getSource() == twoGradeResult) {
            showDiolag.setGradeFile(fileTwoGrade);
            showDiolag.showRecord();
            showDiolag.setVisible(true);
        } else if (e.getSource() == threeGradeResult) {
            showDiolag.setGradeFile(fileThreeGrade);
            showDiolag.showRecord();
            showDiolag.setVisible(true);
        }
        this.validate();
    }
}