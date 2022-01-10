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
        //�����! ���� txt �� SolidWorks ����� ��������� ANSI, ���� ������, ��������������, ����
        //������ ���� ������������ ��� �������� �������� ���������� ������
        //����� ������� � �������
        int id;
        //���������� ���� �� ����� ������
        int quantity;
        //��� ����� �� ����(50�50�1,5 � ��������...)
        String type;
        //������ �����
        double large;
        //������ ���� ����� �����
        String firstAngl;
        //������ ���� ����� �����
        String secondAngl;

        public Metal(int id, int quantity, String type, double large, String firstAngl, String secondAngl) {
            //����������� ��� ����� �������� �������� � ��������� ������� �����
            this.id = id;
            this.quantity = quantity;
            this.type = type;
            this.large = large;
            this.firstAngl = firstAngl;
            this.secondAngl = secondAngl;
        }

        public static Metal getMetal(String str) {
            //��� ��� ������� ���������� ����� ����������� � ������, ������ ��� ������� ������� �����
            int a = 9;
            //�������������� � ������
            char b = (char) a;
            //�������������� � ������ ��� ����������� ��������
            String strB = String.valueOf(b);
            //��������� ���� ��������� ������ �� ������ ��� �������� ������ ��������� ������ �����, �.�. �����
            String[] arr = str.split(strB);
            //�������� ����� ��������� ������ �� ������� � �����������
            return new Metal(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), arr[2], Double.parseDouble(arr[3]), arr[4], arr[5]);
        }

        public static String toCalculate(String string) throws IOException {
            //�������� �����, ��� ���������� ������, ������ � ��������� ����������
            BufferedReader bf = new BufferedReader(new FileReader(string));
            //��������� ����� ��� ���������� �����, ������ �������� �� ������ Gui
            ArrayList<Metal> arrMetal = new ArrayList<>();
            //������ ���� ���� �� �����
            int counter = 0;
            //������� ������, �����, ����� ������ ������ �� ����� SolidWorks �� �����������, ��������� ��� ��� ����, � ��������� ��� ���������� � ����������� �����������.
            while (bf.ready()) {
                //������ ������
                if (counter != 0) {
                    //���� ������ ������, �� �� ������
                    arrMetal.add(Metal.getMetal(bf.readLine().toLowerCase(Locale.ROOT)));
                    //������ ������, ��� ��������� ����� ������ ����������.
                } else {
                    bf.readLine();
                    counter++;
                    //���� ������, �� ������ � "��������" � ��������� � �������� �������, ����� ������� � ��������� ����, �� ���� ��� ���...
                }
            }
//            ������� �������� ����������������� ���������
//            for (Metal m:arrMetal
//            ) {
//                System.out.println(m.id+" "+m.large+" "+m.quantity+" "+m.type);
//            }

            HashSet<String> set = new HashSet<>();
            //������� ��������� � ������ ����
            for (int i = 0; i < arrMetal.size(); i++) {
                set.add(arrMetal.get(i).type);
            }

            ArrayList<String> integers = new ArrayList<>();
            //������� ������ ������������� � ������������� ����
            Iterator<String> str = set.iterator();
            //��������� �� ���������
            double large;
            //��� ������ �����
            while (str.hasNext()) {
                String next = str.next();
                //�������� �����
                large = 0.0;
                //������� ������ ���� �����, ������� ���� ���������
                for (int i = 0; i < arrMetal.size(); i++) {
                    //���� �� ������� ����
                    if (next.equals(arrMetal.get(i).type)) {
                        //���� ������� �������� ����� �� ������ ������������� �������� �������� ����� �� ���������, ��:
                        large += arrMetal.get(i).quantity * arrMetal.get(i).large;
                        //������� � ������, �������������� ������� �����������, �� �����
                    }

                }
                //����� ��������, ��� ��������� ������� while �������� ������ � ������� ���� �������, �� ��� � ������ ����� �����
                integers.add(next + " - " + large);
                //��������� � ����� �������� ������ �������� ������ ����� � � ��������
            }

            String resultOfCalc = "����� ���� ����������: \n";
            //������� ������, ������� ���������� � ��������� � ���������� ��������
            int counerForIntegers = 0;
            //������� ��� ����, ����� ��������� ������� ���� � ������
            for (String ii : integers
                //������� ������ �� ��������� ������ ����� � � ���������
            )
                if (counerForIntegers == integers.size() - 1) {
                    //���� ��������� ����� � ������, �� � ����� �����.
                    resultOfCalc += (ii + " ��." + "\n");
                    //  System.out.println(ii);
                    //��������
                } else {
                    resultOfCalc += (ii + " ��;" + "\n");

                    //System.out.println(ii);
                    //��������
                    counerForIntegers++;
                }

            bf.close();
            //��������� ����� ������
            return resultOfCalc;
            //���������� ���������
        }
    }
}
