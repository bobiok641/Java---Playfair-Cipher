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
        matrixGenerator(keyWord);

        System.out.println("Enter a message to encrypt:");
        String message = input.next();
        if (message.length() % 2 == 0){

        }else{
            System.out.println("The message should contain even number of letters!");
        }
    }

    public static String transformKeyWord(String keyFromInput){
        String keyTransformed = new String();
        boolean letterIsUsed = false;
        keyTransformed = keyTransformed + keyFromInput.charAt(0);

        for (int i = 1; i < keyFromInput.length(); i++)
        {
            for (int j = 0; j < keyTransformed.length(); j++)
            {
                if (keyFromInput.charAt(i) == keyTransformed.charAt(j))
                {
                    letterIsUsed = true;
                }
            }
            if (letterIsUsed == false)
                keyTransformed = keyTransformed + keyFromInput.charAt(i);
            letterIsUsed = false;
        }
        return keyTransformed;
    }

    public static char[][] matrixGenerator(String KeyWord) {
        char matrix[][] = new char[5][5];


        boolean letterIsNotUsed = true;
        char currentLetterToAdd;
        String Key = KeyWord;
        for (int i = 0; i < 26; i++)
        {
            currentLetterToAdd = (char) (i + 97);
            if (currentLetterToAdd == 'j')
                continue;
            for (int j = 0; j < KeyWord.length(); j++)
            {
                if (currentLetterToAdd == KeyWord.charAt(j))
                {
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
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix[i][j] = Key.charAt(indexFromKey);
                indexFromKey++;
            }
        }
        return matrix;
    }

    public static void printMatrix(char[][] matrix){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
