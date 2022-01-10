package com.testinterface;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.IOException;

public class Gui extends JFrame {
    //��������� ������� ���������:https://java-online.ru/swing-jfilechooser.xhtml
    public static final String serialVersion="0.02 Jan10 2022 by Stepanowich P.S.\n";
    public static final String contactMail="ivanov.pavel.stepanowicht@gmail.com\n";
    private JFileChooser fileChooser = null;

    private JButton btnOpenFileAndCalculator = null;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int sizeWidth = 1000;
    int sizeHeight = 500;
    int locationX = (screenSize.width - sizeWidth) / 2;
    int locationY = (screenSize.height - sizeHeight) / 2;

    //���� �������� ������ ��� ������ ������ ���������
    public Gui() {
        super("������ ����� ���� �� ���������� ������� SolidWorks");
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
            //������ ���������� ���� ������ ����� ��� Windows
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(Gui.this,
                    "��������� �������� ������ �� ������������ ������� Windows");
            //��������� ����� �������������� ������ �� Windows, �� �� ������ ������ ������� �������� ���. ���� �������������� ����������� ���...
        }

        UIManager.put("FileChooser.openButtonText", "�������");
        UIManager.put("FileChooser.cancelButtonText", "������");
        UIManager.put("FileChooser.lookInLabelText", "�������� �");
        UIManager.put("FileChooser.fileNameLabelText", "��� �����");
        UIManager.put("FileChooser.filesOfTypeLabelText", "��� �����");
        UIManager.put("FileChooser.saveButtonText", "���������");
        UIManager.put("FileChooser.saveButtonToolTipText", "���������");
        UIManager.put("FileChooser.openButtonText", "�������");
        UIManager.put("FileChooser.openButtonToolTipText", "�������");
        UIManager.put("FileChooser.cancelButtonText", "������");
        UIManager.put("FileChooser.cancelButtonToolTipText", "������");
        UIManager.put("FileChooser.lookInLabelText", "�����");
        UIManager.put("FileChooser.saveInLabelText", "�����");
        UIManager.put("FileChooser.fileNameLabelText", "��� �����");
        UIManager.put("FileChooser.filesOfTypeLabelText", "��� ������");
        UIManager.put("FileChooser.upFolderToolTipText", "�� ���� ������� �����");
        UIManager.put("FileChooser.newFolderToolTipText", "�������� ����� �����");
        UIManager.put("FileChooser.listViewButtonToolTipText", "������");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "�������");
        UIManager.put("FileChooser.fileNameHeaderText", "���");
        UIManager.put("FileChooser.fileSizeHeaderText", "������");
        UIManager.put("FileChooser.fileTypeHeaderText", "���");
        UIManager.put("FileChooser.fileDateHeaderText", "�������");
        UIManager.put("FileChooser.fileAttrHeaderText", "��������");
        UIManager.put("FileChooser.acceptAllFileFilterText", "��� �����");
        //�����������, ��������� ������� ���������: https://ilovejavaforever.wordpress.com/2015/09/22/%D0%BF%D0%BE%D0%BB%D0%BD%D0%B0%D1%8F-%D1%80%D1%83%D1%81%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%86%D0%B8%D1%8F-%D1%87%D0%BE%D1%81%D0%B5%D1%80%D0%BE%D0%B2/

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // ������ ��� �������� ���� � ������ �� ���������

        btnOpenFileAndCalculator = new JButton("���������� ����� ����");

        // ������ �������� ����������� ���� ��� ������ ����������
        fileChooser = new JFileChooser();
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        // �������� ���������� ���� ��� ������ ����� � �������� �������
        addFileChooserListeners();
        // ����������� ���������� � �������
        JPanel panelForButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // ��������� ������ � ����������
        panelForButton.setBackground(Color.WHITE);
        panelForButton.add(btnOpenFileAndCalculator);
        // ��������� ������ � ����������
        JPanel panelForTa = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // ��������� ��������� ���� � ����������
        panelForTa.setBackground(Color.WHITE);
        panelForTa.setAutoscrolls(true);

        JTextArea ta = new JTextArea(Faq.getFaq());
        // ��������� ���� � ���������� �� ������ ���������
        ta.setEditable(false);
        ta.setWrapStyleWord(true);
        ta.setCaretPosition(0);
        ta.setEditable(false);
        ta.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
        ta.setBackground(Color.WHITE);
        panelForTa.add(ta);


//        setContentPane(contents);

        Container container = getContentPane();
        container.add(panelForButton, BorderLayout.SOUTH);
        container.add(panelForTa, BorderLayout.NORTH);
        container.setBackground(Color.WHITE);
        //�����������, ������� ����� �������� ����������� �����

        setVisible(true);
        // ����� ���� �� �����
    }

    private void addFileChooserListeners() {
        btnOpenFileAndCalculator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("����� ����� .txt � �������� ���� �� SolidWorks");
                //��������� ����
                FileNameExtensionFilter filter = new FileNameExtensionFilter("����� txt (*.txt)", "txt");
                // ������������� ������ - ������ ����� � ����������� .txt
                fileChooser.setFileFilter(filter);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showOpenDialog(Gui.this);
                // ����������� ������ - ������ ������� � �������
                if (result == JFileChooser.APPROVE_OPTION) {
                    // ���� ���� ������, �� ��������� ����� � ��������� ���������
                    try {

                        String resultToOut =Work.Metal.toCalculate(String.valueOf(fileChooser.getSelectedFile()));
                        JDialog dialog = new JDialog(Gui.this, "��������� �������");
                        //������� ���������� ���� � ����������� �������

                        JPanel gridText = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        //����������� ������ � ������� �� ������
                        gridText.setBackground(Color.WHITE);
                        JPanel grid = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                        //����������� ������ � �������� �� ������� ����
                        grid.setBackground(Color.WHITE);

                        JButton copy = new JButton("����������� ������");
                        // ������� ������ ��� ����������� ����������
                        JButton close = new JButton("�������");
                        // ������� ������ ��� �������� ����
                        grid.add(copy);
                        grid.add(close);

                        copy.addActionListener(new ActionListener() {
                            //������ ��������� ��� ������ ����������� ������
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                StringSelection selection = new StringSelection(resultToOut);
                                clipboard.setContents(selection, null);
                            }
                        });
                        close.addActionListener(new ActionListener() {
                            //������ ��������� ��� ������ �������
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                                }
                        });

                        JTextArea taOut = new JTextArea(15, 15);
                        //������� ��������� ���� � �����������
                        taOut.setEditable(false);
                        taOut.setBackground(Color.WHITE);
                        taOut.setText(resultToOut);
                        taOut.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
                        gridText.add(taOut);
                        //��������� � ������ ��� �����

                        Container container = dialog.getContentPane();
                        container.add(grid, BorderLayout.SOUTH);
                        //����������� ������ � �������� �� ����
                        container.add(gridText, BorderLayout.NORTH);
                        //����������� ������ � ������� �� �����

                        dialog.setBounds(locationX+400, locationY+50, 400, 400);
                        dialog.setVisible(true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(Gui.this,
                                "������ �� ���������� ����!");
                        ex.printStackTrace();
                    }
                }
            }
        });


    }



}
