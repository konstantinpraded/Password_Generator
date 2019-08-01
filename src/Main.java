import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String input = scanner.nextLine();
        String[] inputString = input.split(" ");
        int[] inputInt = new int[inputString.length];

        for (int i = 0; i < inputString.length; i++){
            inputInt[i] = Integer.parseInt(inputString[i]);
        }

        int upperCaseAmount = inputInt[0];
        int digitAmount = inputInt[2];
        int symbolAmount = inputInt[3];

        char[] passLetters = new char[symbolAmount];
        char[] passDigits = new char[digitAmount];
        char[] password;

        for (int i = 0; i < symbolAmount; i++) {
            passLetters[i] = (char) (97 + random.nextInt(26));
        }

        boolean duplicatesExist = true;
        if (symbolAmount > 1 && symbolAmount != digitAmount) {
            while (duplicatesExist) {
                duplicatesExist = false;
                for (int i = 1; i < symbolAmount; i++) {
                    if (passLetters[i] == passLetters[i - 1]) {
                        duplicatesExist = true;
                        passLetters[i] = (char) (97 + random.nextInt(26));
                    }
                }
            }
        }

        password = passLetters;
        if (digitAmount > 0) {
            for (int i = 0; i < digitAmount; i++) {
                passDigits[i] = (char) (random.nextInt(10) + '0');
            }

            int digitsInserted = 0;
            while (digitsInserted != digitAmount) {
                int positionForDigit = random.nextInt(symbolAmount);
                if (!Character.isDigit(password[positionForDigit])) {
                    password[positionForDigit] = passDigits[digitsInserted];
                    digitsInserted++;
                }

                if (symbolAmount > 1) {
                    duplicatesExist = true;
                    while (duplicatesExist) {
                        duplicatesExist = false;
                        for (int i = 1; i < symbolAmount; i++) {
                            if (Character.isDigit(password[i])) {
                                if (passLetters[i] == passLetters[i - 1]) {
                                    duplicatesExist = true;
                                    passLetters[i] = (char) (random.nextInt(10) + '0');
                                }
                            }
                        }
                    }
                }
            }
        }
        if (upperCaseAmount > 0) {
            boolean upperCased = false;
            int upperCasedCounter = 0;
            while (!upperCased) {
                int randomSymbol = random.nextInt(symbolAmount);
                if (Character.isDigit(password[randomSymbol])) {
                    continue;
                } else if (Character.isUpperCase(password[randomSymbol])) {
                    continue;
                } else {
                    password[randomSymbol] = Character.toUpperCase(password[randomSymbol]);
                    upperCasedCounter++;
                }
                if (upperCasedCounter == upperCaseAmount) {
                    upperCased = true;
                }
            }
        }
        for (char ch : password) {
            System.out.print(ch);
        }
    }
}

