import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String keyWord = new String();

        System.out.println("Enter a keyword:");
        String keyWordFromInput = input.next();
        keyWord = transformKeyWord(keyWordFromInput);
        System.out.println(keyWord);
        
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
}
