package ru.kataAkademy;

enum RomanNumeral {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10),
    L(50), C(100), D(500);

    private final int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean contains(String numeral) {
        for (RomanNumeral c : RomanNumeral.values()) {
            if (c.name().equals(Character.toString(numeral.charAt(0)))) {
                return true;
            }
        }
        return false;
    }

    public static int letterToNumber(char letter) {
        return switch (letter) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> -1;
        };
    }

    public static int convectorToArabic(String romanNumeral) {
        int value = 0;
        int last = 4000;
        for (int i = 0; i < romanNumeral.length(); i++) {
            char ch = romanNumeral.charAt(i);
            int number = letterToNumber(ch);
            if (number == -1) {
                throw new NumberFormatException("Invalid format");
            }
            if (last < number)
                number -= last - 1;
            value += number;
            last = number;
        }
        return value;
    }

    public static String convectorToRoman(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Ответ не может быть отрицательным, т.к. в римской системе нет отрицательных чисел");
        }
        StringBuilder romanValue = new StringBuilder();
        int N = number;
        while (N > 0) {
            for (int i = 0; i < RomanNumeral.values().length; i++) {
                if (N < RomanNumeral.values()[i].getValue()) {
                    N -= RomanNumeral.values()[i - 1].getValue();
                    romanValue.append(RomanNumeral.values()[i - 1].name());
                    break;
                }
            }
        }
        return romanValue.toString();
    }
}