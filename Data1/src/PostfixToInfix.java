import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PostfixToInfix {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        System.out.print("Postfix expression is: ");
        /*
        Example:
        Postfix expression is: 12*34*+
         */
        String postfix = s.next();
        StringDoubleEndedQueuelmpl list = new StringDoubleEndedQueuelmpl();
        PrintStream f = new PrintStream("StringDoubleEndedQueuelmpl.java");
        boolean detective = true;
        int numbers=0; //numbers' counter
        int operators=0; //operators' counter
        try{
                for (int i = 0; i < postfix.length(); i++) {
                    char given = postfix.charAt(i);
                    if (given == '+' || given == '-' || given == '*' || given == '/' || given == '^') {
                        String first = list.getLast();
                        list.removeLast();
                        String last = list.getLast();
                        list.removeLast();
                        String temp = "(" + last + given + first + ")";
                        list.addLast(temp);
                        operators+=1;
                    } else if (given == '0' || given == '1' || given == '2' || given == '3' || given == '4'
                            || given == '5' || given == '6' || given == '7' || given == '8' || given == '9') {
                        String temp = given + "";
                        list.addLast(temp);
                        numbers+=1;
                    } else {
                        detective = false;
                        break;
                    }
                }
        }catch(NullPointerException  ex){
            detective=false;
        }
        if (numbers!=(operators+1)){
            detective=false; /*Αν το πλήθος των τελεστέων δεν είναι ίσος απο αυτό των τελεστών +1,
            τότε σίγουρα έχει δοθεί λάθος τιμή*/
        }
        if (detective) {
            System.out.print("Infix expression is: ");
            list.printQueue(f);
        }
        else
            System.out.println("Invalid input\n" + "Try again");
    }
}


