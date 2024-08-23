import java.io.*;
import java.util.Scanner;

public class DynamicCovidwith_k_PQ {
    public static void main(String[] args) {
       try {
            System.out.println("Give the file: ");
            Scanner scanner=new Scanner(System.in);
            String fileName=  scanner.nextLine();
            File file =new File("src/test/"+fileName); //ΠΡΕΠΕΙ ΝΑ ΒΑΛΕΤΕ ΤΟ ΑΡΧΕΙΟ ΠΟΥ ΘΕΛΕΤΕ ΝΑ ΔΟΚΙΜΑΣΕΤΕ ΣΤΟΝ ΦΑΚΕΛΟ test
            boolean genCheck=true;
            boolean f=true;
            PQ pq = new PQ(new IntegerComparator());
            BufferedReader br = new BufferedReader(
                    new FileReader(file));

            int count = 0;
            while (( br.readLine() )!= null) {
                count++;
            }
            br.close();//close the file
            String s;
            Scanner readk = new Scanner(System.in);
            System.out.print("Give the k parameter: ");
            int k = readk.nextInt();
            if (k>count)
                f=false;//ΕΝΤΟΠΙΖΕΙ ΑΝ ΤΟ Κ ΕΙΝΑΙ ΜΙΚΡΟΤΕΡΟ ΤΩΝ ΓΡΑΜΜΩΝ ΤΟΥ ΑΡΧΕΙΟΥ
            if(f) {
                BufferedReader br1 = new BufferedReader(
                        new FileReader(file));//"C:\\Users\\anton\\Desktop\\test\\inputfile.txt"));
                int sec_counter=1;
                while ((s = br1.readLine()) != null) {
                    //reading data of each line
                    boolean ch0 = true;
                    int inside = 0;
                    String i = "";
                    do {
                        char a = s.charAt(inside);
                        String b4 = String.valueOf(a);
                        if (b4.equals("1") || b4.equals("2") || b4.equals("3") || b4.equals("4") || b4.equals("5") || b4.equals("6") ||
                                b4.equals("7") || b4.equals("8") || b4.equals("9") || b4.equals("0")) {
                            i += b4;
                        } else {
                            ch0 = false;
                        }
                        inside += 1;
                    } while (ch0);
                    int a1 = Integer.parseInt(i);
                    String N = "";
                    String c5;
                    boolean ch = true;

                    do {
                        char c1 = s.charAt(inside);
                        c5 = String.valueOf(c1);
                        if (c5.equals("1") || c5.equals("2") || c5.equals("3") || c5.equals("4") || c5.equals("5") || c5.equals("6") ||
                                c5.equals("7") || c5.equals("8") || c5.equals("9") || c5.equals("0")) {
                            ch = false;
                        } else {
                            N += c5;
                        }
                        inside += 1;
                    } while (ch);
                    inside -= 1;

                    String N1 = "";
                    String c6;
                    boolean ch1 = true;
                    do {
                        char c1 = s.charAt(inside);
                        c6 = String.valueOf(c1);
                        if (c6.equals("1") || c6.equals("2") || c6.equals("3") || c6.equals("4") || c6.equals("5") || c6.equals("6") ||
                                c6.equals("7") || c6.equals("8") || c6.equals("9") || c6.equals("0")) {
                            N1 += c6;
                        } else {
                            ch1 = false;
                        }
                        inside += 1;
                    } while (ch1);
                    String b1 = N;

                    String N2 = "";
                    String c7;
                    boolean ch2 = true;
                    do {
                        try {
                            char c1 = s.charAt(inside);
                            c7 = String.valueOf(c1);
                            N2 += c7;
                            inside += 1;
                        } catch (Exception ex) {
                            ch2 = false;
                        }

                    } while (ch2);
                    N2=N2.replaceAll("\\s","");
                    int c2 = Integer.parseInt(N1);
                    int d1 = Integer.parseInt(N2);
                    if (a1 >= 1000) {
                        genCheck = false;
                        break;
                    } else if (c2 < d1) {
                        genCheck = false;
                        break;
                    }
                    if (sec_counter<=k)
                        pq.insert(new City(a1, b1, c2, d1));
                    else
                        pq.removeLast(new City(a1, b1, c2, d1));
                    sec_counter++;
                }
                br1.close();
                if(genCheck) {
                    System.out.println("The top " + k + " cities are:");
                    for (int i = 0; i <k; i++)
                            System.out.println(pq.getmax());
                }
                else
                    System.out.println("The data of your file are not valid");
            }else
                System.out.println("Error!!!\nThe parameter k that you gave is bigger than the number of " +
                        "the cities which are included in the file");
       } catch (Exception ex) {
            System.out.println("I can't find your file");

      }
    }
}
