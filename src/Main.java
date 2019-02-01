import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] matrix = new char[5][5];

        String keyWord = new String();

        System.out.println("Enter a keyword:");
        String keyWordFromInput = input.next();
        keyWord = transformKeyWord(keyWordFromInput);
        System.out.println(keyWord);
        matrix = matrixGenerator(keyWord);
        printMatrix(matrix);

        System.out.println("Enter a message to encrypt:");
        String message = input.next();

        if (message.length() % 2 == 0) {
            transformMessage(message);
        } else {
            System.out.println("The message should contain even number of letters!");
        }
    }

    public static String transformKeyWord(String keyFromInput) {
        String keyTransformed = new String();
        boolean letterIsUsed = false;
        keyTransformed = keyTransformed + keyFromInput.charAt(0);

        for (int i = 1; i < keyFromInput.length(); i++) {
            for (int j = 0; j < keyTransformed.length(); j++) {
                if (keyFromInput.charAt(i) == keyTransformed.charAt(j)) {
                    letterIsUsed = true;
                }
            }
            if (letterIsUsed == false)
                keyTransformed = keyTransformed + keyFromInput.charAt(i);
            letterIsUsed = false;
        }
        return keyTransformed;
    }

    public static char[][] matrixGenerator(String keyWord) {
        char matrix[][] = new char[5][5];


        boolean letterIsNotUsed = true;
        char currentLetterToAdd;
        String Key = keyWord;
        for (int i = 0; i < 26; i++) {
            currentLetterToAdd = (char) (i + 97);
            if (currentLetterToAdd == 'j')
                continue;
            for (int j = 0; j < keyWord.length(); j++) {
                if (currentLetterToAdd == keyWord.charAt(j)) {
                    letterIsNotUsed = false;
                    break;
                }
            }
            if (letterIsNotUsed)
                Key = Key + currentLetterToAdd;
            letterIsNotUsed = true;
        }
        System.out.println(Key);

        int indexFromKey = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = Key.charAt(indexFromKey);
                indexFromKey++;
            }
        }
        return matrix;
    }

    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] transformMessage(String messageFromInput) {
        messageFromInput = messageFromInput + " ";
        char[][] separatedPairs = new char[messageFromInput.length()][2];
        int counter = 0;
        for (int i = 0; i < messageFromInput.length() - 1; i += 2) {
            if (messageFromInput.toCharArray()[i] == messageFromInput.toCharArray()[i + 1]) {
                separatedPairs[counter][0] = messageFromInput.toCharArray()[i];
                separatedPairs[counter][1] = 'x';
                counter++;
                i--;
            }else if (i == messageFromInput.toCharArray().length - 2){
                separatedPairs[counter][0] = messageFromInput.toCharArray()[i];
                separatedPairs[counter][1] = 'x';
                counter++;
            }else{
                separatedPairs[counter][0] = messageFromInput.toCharArray()[i];
                separatedPairs[counter][1] = messageFromInput.toCharArray()[i + 1];
                counter++;
            }

        }

        for (int i = 0; i < counter; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(separatedPairs[i][j]);
            }
            System.out.println();
        }

        return separatedPairs;
    }
}
