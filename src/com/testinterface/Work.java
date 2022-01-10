package com.testinterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public class Work {
    static class Metal {
        //Важно! Файл txt их SolidWorks имеет кодировку ANSI, этот проект, соответственно, тоже
        //Данный клас предназначет для удобного парсинга полученной строки
        //номер позиции в таблице
        int id;
        //количество труб из одной строки
        int quantity;
        //тип трубы по ГОСТ(50х50х1,5 и подобное...)
        String type;
        //длинна трубы
        double large;
        //первый угол резки трубы
        String firstAngl;
        //второй угол резки трубы
        String secondAngl;

        public Metal(int id, int quantity, String type, double large, String firstAngl, String secondAngl) {
            //конструктор для более удобного парсинга и получения обьекта трубы
            this.id = id;
            this.quantity = quantity;
            this.type = type;
            this.large = large;
            this.firstAngl = firstAngl;
            this.secondAngl = secondAngl;
        }

        public static Metal getMetal(String str) {
            //это бит символа разделения между параметрами в строке, данный бит получен опытным путем
            int a = 9;
            //преобразование в символ
            char b = (char) a;
            //преобразование в строку для дальнейшего парсинга
            String strB = String.valueOf(b);
            //разбиваем одну считанную строку на массив для создания нового экземляра класса Метал, т.е. трубы
            String[] arr = str.split(strB);
            //получаем новый экземпляр класса из строчки с параметрами
            return new Metal(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), arr[2], Double.parseDouble(arr[3]), arr[4], arr[5]);
        }

        public static String toCalculate(String string) throws IOException {
            //основной метод, где происходит чтение, расчет и получение результата
            BufferedReader bf = new BufferedReader(new FileReader(string));
            //открываем поток для считывания файла, стринг получаем от класса Gui
            ArrayList<Metal> arrMetal = new ArrayList<>();
            //массив всех труб из файла
            int counter = 0;
            //счетчик циклов, важно, чтобы первая строка из файла SolidWorks не считывалась, поскольку там нет труб, а заголовки для параметров с настройками отображения.
            while (bf.ready()) {
                //начало чтения
                if (counter != 0) {
                    //если первая строка, то не читаем
                    arrMetal.add(Metal.getMetal(bf.readLine().toLowerCase(Locale.ROOT)));
                    //читаем строку, все заглавные буквы делаем маленькими.
                } else {
                    bf.readLine();
                    counter++;
                    //если первая, то читаем в "холостую" и добавляем к счетчику единицу, можно сделать и булевский флаг, но пока что так...
                }
            }
//            обычная проверка работоспособности программы
//            for (Metal m:arrMetal
//            ) {
//                System.out.println(m.id+" "+m.large+" "+m.quantity+" "+m.type);
//            }

            HashSet<String> set = new HashSet<>();
            //создаем множество с типами труб
            for (int i = 0; i < arrMetal.size(); i++) {
                set.add(arrMetal.get(i).type);
            }

            ArrayList<String> integers = new ArrayList<>();
            //Создаем список наименованием с размерностями труб
            Iterator<String> str = set.iterator();
            //пройдемся по множеству
            double large;
            //это длинна трубы
            while (str.hasNext()) {
                String next = str.next();
                //название трубы
                large = 0.0;
                //текущая длинна этой трубы, которой надо посчитать
                for (int i = 0; i < arrMetal.size(); i++) {
                    //идем по массиву труб
                    if (next.equals(arrMetal.get(i).type)) {
                        //если текущее название трубы из масива соответствует текущему названию трубы из множества, то:
                        large += arrMetal.get(i).quantity * arrMetal.get(i).large;
                        //считаем её длинну, предварительно умножив колличество, на длину
                    }

                }
                //конец итерации, при следующем проходе while обнуляем длинну и обходим цикл сначала, но уже с другим типом трубы
                integers.add(next + " - " + large);
                //добавляем в ранее созданый список значение длинны трубы и её название
            }

            String resultOfCalc = "Итого труб необходимо: \n";
            //создаем строку, которая отправится в сообщение о результате рассчета
            int counerForIntegers = 0;
            //счетчик для того, чтобы последняя строчка была с точкой
            for (String ii : integers
                //обходим список со значением длинны трубы и её названием
            )
                if (counerForIntegers == integers.size() - 1) {
                    //если последняя труба в списке, то в конце точка.
                    resultOfCalc += (ii + " мм." + "\n");
                    //  System.out.println(ii);
                    //проверка
                } else {
                    resultOfCalc += (ii + " мм;" + "\n");

                    //System.out.println(ii);
                    //проверка
                    counerForIntegers++;
                }

            bf.close();
            //закрываем поток чтения
            return resultOfCalc;
            //возвращаем результат
        }
    }
}
