import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a keyword:");
        String keyWord = input.next();


        System.out.println("Enter a message to encrypt:");
        String message = input.next();
        if (message.length() % 2 == 0){

        }else{
            System.out.println("The message should contain even number of letters!");
        }
    }
}
