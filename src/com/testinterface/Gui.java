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
    //Отдельное спасибо источнику:https://java-online.ru/swing-jfilechooser.xhtml
    public static final String serialVersion="0.02 Jan10 2022 by Stepanowich P.S.\n";
    public static final String contactMail="ivanov.pavel.stepanowicht@gmail.com\n";
    private JFileChooser fileChooser = null;

    private JButton btnOpenFileAndCalculator = null;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int sizeWidth = 1000;
    int sizeHeight = 500;
    int locationX = (screenSize.width - sizeWidth) / 2;
    int locationY = (screenSize.height - sizeHeight) / 2;

    //наша основная кнопка для старта работы программы
    public Gui() {
        super("Расчёт длины труб из сохранённой таблицы SolidWorks");
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
            //делаем стилизацию окна поиска файла под Windows
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(Gui.this,
                    "Программа работает только на операционной системе Windows");
            //Программа будет использоваться только на Windows, но на всякий случай подумал добавить это. Хотя протестировать возможности нет...
        }

        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.lookInLabelText", "Смотреть в");
        UIManager.put("FileChooser.fileNameLabelText", "Имя файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Тип файла");
        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.saveButtonToolTipText", "Сохранить");
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.openButtonToolTipText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Отмена");
        UIManager.put("FileChooser.lookInLabelText", "Папка");
        UIManager.put("FileChooser.saveInLabelText", "Папка");
        UIManager.put("FileChooser.fileNameLabelText", "Имя файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Тип файлов");
        UIManager.put("FileChooser.upFolderToolTipText", "На один уровень вверх");
        UIManager.put("FileChooser.newFolderToolTipText", "Создание новой папки");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Список");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Таблица");
        UIManager.put("FileChooser.fileNameHeaderText", "Имя");
        UIManager.put("FileChooser.fileSizeHeaderText", "Размер");
        UIManager.put("FileChooser.fileTypeHeaderText", "Тип");
        UIManager.put("FileChooser.fileDateHeaderText", "Изменен");
        UIManager.put("FileChooser.fileAttrHeaderText", "Атрибуты");
        UIManager.put("FileChooser.acceptAllFileFilterText", "Все файлы");
        //Локализация, отдельное спасибо источнику: https://ilovejavaforever.wordpress.com/2015/09/22/%D0%BF%D0%BE%D0%BB%D0%BD%D0%B0%D1%8F-%D1%80%D1%83%D1%81%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%86%D0%B8%D1%8F-%D1%87%D0%BE%D1%81%D0%B5%D1%80%D0%BE%D0%B2/

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Кнопка для закрытия окна и выхода из программы

        btnOpenFileAndCalculator = new JButton("Рассчитать длину труб");

        // Кнопка создания диалогового окна для выбора директории
        fileChooser = new JFileChooser();
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        // Создание экземпляра поля для поиска файла в файловой системе
        addFileChooserListeners();
        // Подключение слушателей к кнопкам
        JPanel panelForButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Размещаем кнопку в интерфейсе
        panelForButton.setBackground(Color.WHITE);
        panelForButton.add(btnOpenFileAndCalculator);
        // Размещаем кнопку в интерфейсе
        JPanel panelForTa = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Размещаем текстовое поле в интерфейсе
        panelForTa.setBackground(Color.WHITE);
        panelForTa.setAutoscrolls(true);

        JTextArea ta = new JTextArea(Faq.getFaq());
        // Текстовое поле с подсказкой по работе программы
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
        //выравниваем, пробелы между панелями закрашиваем белым

        setVisible(true);
        // Вывод окна на экран
    }

    private void addFileChooserListeners() {
        btnOpenFileAndCalculator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор файла .txt с таблицей труб из SolidWorks");
                //Заголовок окна
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы txt (*.txt)", "txt");
                // Устанавливаем фильтр - только файлы с расширением .txt
                fileChooser.setFileFilter(filter);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showOpenDialog(Gui.this);
                // Определение режима - только каталог с файлами
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Если файл выбран, то посчитаем трубы и отобразим результат
                    try {

                        String resultToOut =Work.Metal.toCalculate(String.valueOf(fileChooser.getSelectedFile()));
                        JDialog dialog = new JDialog(Gui.this, "Результат расчёта");
                        //создаем диалоговое окно с результатом расчета

                        JPanel gridText = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        //выравниваем панель с текстом по центру
                        gridText.setBackground(Color.WHITE);
                        JPanel grid = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                        //выравниваем панель с кнопками по правому краю
                        grid.setBackground(Color.WHITE);

                        JButton copy = new JButton("Скопировать расчет");
                        // Создаем кнопку для копирования результата
                        JButton close = new JButton("Закрыть");
                        // Создаем кнопку для закрытия окна
                        grid.add(copy);
                        grid.add(close);

                        copy.addActionListener(new ActionListener() {
                            //делаем слушателя для кнопки Скопировать расчет
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                StringSelection selection = new StringSelection(resultToOut);
                                clipboard.setContents(selection, null);
                            }
                        });
                        close.addActionListener(new ActionListener() {
                            //делаем слушателя для кнопки Закрыть
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                                }
                        });

                        JTextArea taOut = new JTextArea(15, 15);
                        //Создаем текстовое поле с результатом
                        taOut.setEditable(false);
                        taOut.setBackground(Color.WHITE);
                        taOut.setText(resultToOut);
                        taOut.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
                        gridText.add(taOut);
                        //добавляем в панель наш текст

                        Container container = dialog.getContentPane();
                        container.add(grid, BorderLayout.SOUTH);
                        //выравниваем панель с кнопками по низу
                        container.add(gridText, BorderLayout.NORTH);
                        //выравниваем панель с текстом по верху

                        dialog.setBounds(locationX+400, locationY+50, 400, 400);
                        dialog.setVisible(true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(Gui.this,
                                "Выбран не подходящий файл!");
                        ex.printStackTrace();
                    }
                }
            }
        });


    }



}
