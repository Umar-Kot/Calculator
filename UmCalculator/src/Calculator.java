import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        String number = s.nextLine();
        System.out.println(calc(number));

    }

    public static String calc(String number) throws Exception {
        String[] operators = {"+", "-", "/", "*"};
        String[] regex = {"\\+", "-", "/", "\\*"};
        int index = index(operators, number);
        String[] numbers = number.split(regex[index]);
        if (numbers.length != 2) {
            throw new Exception("Неверный формат");
        }
        if (Roman.isRoman(numbers[0]) && Roman.isRoman(numbers[1])) {
            int[] array = RomanToArabiana(numbers);
            return Roman.ArabianToRoman(calculation(array, operators, index));
        } else {
            int[] array = StringToArabian(numbers);
            return String.valueOf(calculation(array, operators, index));
        }

    }

    public static int index(String[] operators, String number) {
        int index;
        for (int i = 0; i < operators.length; i++) {
            if (number.contains(operators[i])) {
                index = i;
                return index;
            }
        }
        return -1;
    }

    public static int[] StringToArabian(String[] input) throws Exception {
        int[] array = new int[2];
        array[0] = Integer.parseInt(input[0]);
        array[1] = Integer.parseInt(input[1]);

        return array;
    }

    public static int[] RomanToArabiana(String[] input) throws Exception {
        int[] array = new int[2];
        array[0] = Roman.RomanToArabian(input[0]);
        array[1] = Roman.RomanToArabian(input[1]);
        if (array[0] == 0 || array[1] == 0) throw new Exception("Неверное условие");
        return array;
    }


    public static int calculation(int[] array, String[] operators, int index) throws Exception {
        int a = array[0];
        int b = array[1];
        if (a > 10 || b > 10) throw new Exception("Неверный формат");
        int result = switch (operators[index]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "/" -> a / b;
            default -> a * b;
        };

        return result;
    }
}