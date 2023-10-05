import java.util.Scanner;

public class ArrayCouples {

    public static void arrayChallenge(int[] intNumbersArray) {
        if (intNumbersArray == null) {
            System.out.println("The input array is null, please rerun the application and provide a valid array.");
            return;
        }

        int intNumbersArrayLength = intNumbersArray.length;

        if (intNumbersArrayLength < 4) {
            System.out.println("The array must contain at least 2 pairs of numbers, please rerun the application and provide a valid array.");
            return;
        } else if (intNumbersArrayLength % 2 != 0) {
            System.out.println("The array contains an odd quantity of numbers, please rerun the application and provide an array with even quantity of numbers.");
            return;
        } else {
            for (int number : intNumbersArray) {
                if (number < 1) {
                    System.out.println("The input array must contain only positive numbers");
                    return;
                }
            }
        }
        int validPairsCounter = 0;
        boolean[] checkedPairs = new boolean[intNumbersArrayLength];
        StringBuilder invalidPairs = new StringBuilder();

        for (int i = 0; i < intNumbersArrayLength; i += 2) {
            int currentPair = intNumbersArray[i] * 10 + intNumbersArray[i + 1];
            for (int j = i + 2; j < intNumbersArrayLength; j += 2) {
                int reversedPair = intNumbersArray[j + 1] * 10 + intNumbersArray[j];
                if (currentPair == reversedPair) {
                    validPairsCounter += 2;
                    checkedPairs[i] = true;
                    checkedPairs[i + 1] = true;
                    checkedPairs[j] = true;
                    checkedPairs[j + 1] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < intNumbersArrayLength; i++) {
            if (!checkedPairs[i]) {
                invalidPairs.append(intNumbersArray[i]).append(",");
            }
        }
        if (!invalidPairs.isEmpty()) invalidPairs.deleteCharAt(invalidPairs.length() - 1);
        if (validPairsCounter == intNumbersArrayLength / 2) {
            System.out.println("Yes.");
        } else {
            System.out.println(invalidPairs);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the array of comma-separated positive integers: ");
        String input = scanner.nextLine();
        scanner.close();

        String[] inputArray = input.split(",");
        int[] intNumbersArray = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
           try{intNumbersArray[i] = Integer.parseInt(inputArray[i].trim());}
           catch (NumberFormatException e){
               throw new RuntimeException("Invalid input, please rerun the application and enter the array of comma-separated positive integers.");
           }
        }

        arrayChallenge(intNumbersArray);
    }
}
