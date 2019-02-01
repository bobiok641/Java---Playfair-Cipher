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

        char[][] messageAsSeparatedPairs = transformMessage(message);
        messageAsSeparatedPairs = encryptMessage(messageAsSeparatedPairs, matrix);

        for (int i = 0; i < messageAsSeparatedPairs.length; i++) {
            for (int j = 0; j < messageAsSeparatedPairs[0].length; j++) {
                System.out.print(messageAsSeparatedPairs[i][j]);
            }
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
        //System.out.println(Key);

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
        char[][] separatedPairs = new char[messageFromInput.length()/2][2];
        int counter = 0;
        for (int i = 0; i < messageFromInput.length() - 1; i += 2) {
            if (messageFromInput.toCharArray()[i] == messageFromInput.toCharArray()[i + 1]) {
                separatedPairs[counter][0] = messageFromInput.toCharArray()[i];
                separatedPairs[counter][1] = 'x';
                counter++;
                i--;
            } else if (i == messageFromInput.toCharArray().length - 2) {
                separatedPairs[counter][0] = messageFromInput.toCharArray()[i];
                separatedPairs[counter][1] = 'x';
                counter++;
            } else {
                separatedPairs[counter][0] = messageFromInput.toCharArray()[i];
                separatedPairs[counter][1] = messageFromInput.toCharArray()[i + 1];
                counter++;
            }
        }

        for (int i = 0; i < counter; i++) {
            for (int j = 0; j < 2; j++) {
                //System.out.print(separatedPairs[i][j]);
            }
            //System.out.println();
        }

        return separatedPairs;
    }

    public static char[][] encryptMessage(char[][] separatedPairs, char[][] matrix) {
        byte[][] pairsCoordinates = new byte[2][2];
        byte[] swapCoordinates = new byte[2];
        boolean isLastX;
        boolean isLastY;
        for (int i = 0; i < separatedPairs.length; i++) {
            for (byte j = 0; j < separatedPairs[0].length; j++) {
                for (byte x = 0; x < matrix.length; x++) {
                    for (byte y = 0; y < matrix[0].length; y++) {
                        if (separatedPairs[i][j] == matrix[x][y]) {
                            pairsCoordinates[j][0] = x;
                            pairsCoordinates[j][1] = y;
                            //System.out.println(matrix[x][y]);
                        }
                    }
                }
            }
            isLastX = false;
            isLastY = false;
            if (pairsCoordinates[0][0] == pairsCoordinates[1][0]) {
                for (int j = 0; j < 2; j++) {
                    if (pairsCoordinates[j][1] == 4) {
                        pairsCoordinates[j][1] = 0;
                        pairsCoordinates[Math.abs(j - 1)][1]++;
                        isLastX = true;
                    }
                }
                if (!isLastX) {
                    pairsCoordinates[0][1]++;
                    pairsCoordinates[1][1]++;
                }

            } else if (pairsCoordinates[0][1] == pairsCoordinates[1][1]) {
                for (int j = 0; j < 2; j++) {
                    if (pairsCoordinates[j][0] == 4) {
                        pairsCoordinates[j][0] = 0;
                        pairsCoordinates[Math.abs(j - 1)][0]++;
                        isLastY = true;
                    }
                }
                if (!isLastY) {
                    pairsCoordinates[0][0]++;
                    pairsCoordinates[1][0]++;
                }
            } else {
                swapCoordinates[1] = pairsCoordinates[0][1];
                pairsCoordinates[0][1] = pairsCoordinates[1][1];
                pairsCoordinates[1][1] = swapCoordinates[1];
            }

            for (int j = 0; j < 2; j++) {
                separatedPairs[i][j] = matrix[pairsCoordinates[j][0]][pairsCoordinates[j][1]];
                //System.out.print(separatedPairs[i][j]);
            }

        }
        return separatedPairs;
    }
}
