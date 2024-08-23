import java.io.*;
import java.util.Scanner;



public class Covid_k {

    public static void main(String[] args)  {
        QuickSort sortTime = new QuickSort();
        System.out.println("Give the file that you want to be QuickSorted: ");
      try {
           Scanner scanner=new Scanner(System.in);
           String fileName=  scanner.nextLine();
           File file =new File("src/test/"+fileName); //ΠΡΕΠΕΙ ΝΑ ΒΑΛΕΤΕ ΤΟ ΑΡΧΕΙΟ ΠΟΥ ΘΕΛΕΤΕ ΝΑ ΔΟΚΙΜΑΣΕΤΕ ΣΤΟΝ ΦΑΚΕΛΟ test
        BufferedReader br = new BufferedReader(
                new FileReader(file));
        boolean genCheck=true;
        int[] id = new int[100];
        String[] name = new String[100];
        int[] population = new int[100];
        int[] covidcases = new int[100];
        double[] density = new double[(int) 100.0];
        int c = 0; //index of array
        String s;
        while ((s= br.readLine() )!= null) {
            //reading data of each line
            boolean ch0=true;
            int inside=0;
            String i = "";
            do {
                char a = s.charAt(inside);
                String b4 = String.valueOf(a);
                if(b4.equals("1") || b4.equals("2") || b4.equals("3") || b4.equals("4") || b4.equals("5") || b4.equals("6") ||
                        b4.equals("7") || b4.equals("8") || b4.equals("9") || b4.equals("0")) {
                    i += b4;
                }else {
                    ch0 = false;
                }
                inside+=1;
            }while(ch0);
                int a1 = Integer.parseInt(i);
                String N = "";
                String c5;
                boolean ch = true;

            do{
                char c1 = s.charAt(inside);
                c5 = String.valueOf(c1);
                if (c5.equals("1") || c5.equals("2") || c5.equals("3") || c5.equals("4") || c5.equals("5") || c5.equals("6") ||
                        c5.equals("7") || c5.equals("8") || c5.equals("9") || c5.equals("0")) {
                   ch=false;
                }
                else {
                    N += c5;
                }
                inside += 1;
            }while(ch);
            inside-=1;

            String N1="";
            String c6;
            boolean ch1=true;
            do{
                char c1 = s.charAt(inside);
                c6 = String.valueOf(c1);
                if (c6.equals("1") || c6.equals("2") || c6.equals("3") || c6.equals("4") || c6.equals("5") || c6.equals("6") ||
                        c6.equals("7") || c6.equals("8") || c6.equals("9") || c6.equals("0")) {
                    N1 += c6;
                }
                else {
                    ch1=false;
                }
                inside += 1;
            }while(ch1);
            String b1 = N;

            String N2="";
            String c7;
            boolean ch2=true;
            do{
                try {
                    char c1 = s.charAt(inside);
                    c7 = String.valueOf(c1);
                    N2 += c7;
                    inside += 1;
                }catch(Exception ex) {
                    ch2 = false;
                }
            }while(ch2);
            N2=N2.replaceAll("\\s","");
            int c2 = Integer.parseInt(N1);
            int d1 = Integer.parseInt(N2);

            if (a1>=1000){
                genCheck=false;
                break;
            }
            else if(c2<d1){
                genCheck=false;
                break;
            }
            City ob = new City(a1, b1, c2, d1);

            //using getters and setters
            ob.setID(a1);
            id[c] = ob.getID();
            ob.setName(b1);
            name[c] = ob.getName();
            ob.setPopulation(c2);
            population[c] = ob.getPopulation();
            ob.setCovidCases(d1);
            covidcases[c] = ob.getCovidCases();
            density[c] = sortTime.calculateDensity(population[c], covidcases[c]);
            c++;
            int n = id.length;
            sortTime.sort(density, 0, n - 1, id, name, population, covidcases);
            ob.compareTo(density, n, name, id, population, covidcases,c);
        }
        br.close();//close the file
        if (genCheck) {
            //read the parameter k
            Scanner readk = new Scanner(System.in);
            System.out.print("Give the k parameter: ");
            int k = readk.nextInt();

            boolean check = true;
            if (k > c)
                check = false;
            if (check) {
                System.out.println("The top " + k + " cities are:");
                for (int i = 0; i < k; i++) {
                    System.out.println(name[i]);
                }

            } else
                System.out.println("Error!!!\nThe parameter k that you gave is bigger than the number of " +
                        "the cities which are included in the file");
        }
        else
            System.out.println("The data of your file are not valid");
        }catch(Exception ex){
            System.out.println("I can't find your file");
        }
    }
}
