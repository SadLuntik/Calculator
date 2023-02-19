package ru.kataAkademy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String exp = scanner.nextLine();
        System.out.println(calc(exp));
    }
    public static String calc(String exp) {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        exp = exp.replace(" ", "");
        int actionIndex = -1;
        int countAction = 0;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                countAction++;
            }
        }
        if (countAction == 0) {
            throw new IllegalArgumentException("Не является математической операцией");
        }
        if (countAction > 1) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String[] data = exp.split(regexActions[actionIndex]);

        if (RomanNumeral.contains(data[0]) == RomanNumeral.contains(data[1])) {
            int a;
            int b;
            int result;
            boolean isRoman = RomanNumeral.contains(data[0]);
            if (isRoman) {
                a = RomanNumeral.convectorToArabic(data[0]);
                b = RomanNumeral.convectorToArabic(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if (a > 10 || b > 10) {
                throw new IllegalArgumentException("Введеные числа не могут быть больше 10");
            }
            switch (actions[actionIndex]) {
                case "+" -> result = a + b;
                case "-" -> result = a - b;
                case "/" -> result = a / b;
                default -> result = a * b;
            }
            if (isRoman) {
                return (RomanNumeral.convectorToRoman(result));
            } else {
                return Integer.toString(result);
            }
        } else {
            throw new IllegalArgumentException("Используются одновременно разные системы счисления");
        }
    }
}