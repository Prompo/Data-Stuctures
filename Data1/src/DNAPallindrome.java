import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class DNAPallindrome {
    public static void main(String[] args) throws FileNotFoundException {
        StringDoubleEndedQueuelmpl list = new StringDoubleEndedQueuelmpl();
        PrintStream F = new PrintStream("StringDoubleEndedQueuelmpl.java");
        String exp;
        boolean f = true;
        System.out.println("Notice:\nClick on the space\nGive 1 string at a time, then press enter and in case you want to stop, give the word 'stop'");
        System.out.println("\nInsert a DNA string");
        /*Example:
        Insert a DNA string
         A
         T
         A
         T
         stop
        */
        do {
            Scanner s1 = new Scanner(System.in);
            exp = s1.next();
            if (exp.equals("A") || exp.equals("T") || exp.equals("G") || exp.equals("C"))
                list.addLast(exp);
            else if (!exp.equals("stop") && !exp.equals("")){
                list.addLast(exp);
                f = false;
            }
        } while (!exp.equals("stop"));

        System.out.print("The given DNA strng is: ");
        list.printQueue(F);
        System.out.print("\n");

        if (f) {
            if (list.size() == 0)
                System.out.println("Congratulations\nYou gave a Watson-Crick complemented pallindrome DNA string");
            else if (list.size() % 2 == 0 ) {
                for (int i = 0; i < list.size(); i++) {
                    String c = list.getFirst();
                    String c1 = list.getLast();
                    if (c.equals("A") && c1.equals("T")) {
                        list.removeFirst();
                        list.removeLast();
                    } else if (c.equals("T") && c1.equals("A")) {
                        list.removeFirst();
                        list.removeLast();
                    } else if (c.equals("C") && c1.equals("G")) {
                        list.removeFirst();
                        list.removeLast();
                    } else if (c.equals("G") && c1.equals("C")) {
                        list.removeFirst();
                        list.removeLast();
                    } else {
                        f = false;
                        break;
                    }
                }

                if (f)
                    System.out.println("Congratulations\nYou gave a Watson-Crick complemented pallindrome DNA string");
                else
                    System.out.println("Sorry\nYou gave a DNA string that it is not a Watson-Crick complemented pallindrome ");

            } else
                System.out.println("Sorry\nYou gave a DNA string that it is not a Watson-Crick complemented pallindrome ");
        }
        else
            System.out.println("Sorry\nYou gave a DNA string that it is not a Watson-Crick complemented pallindrome ");
    }
}
