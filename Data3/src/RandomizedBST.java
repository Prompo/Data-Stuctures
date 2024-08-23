import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

class RandomizedBST implements MafianInterface {
    private class TreeNode {
        TreeNode(Suspect s) {
            item = s;
        }

        Suspect item;
        TreeNode left;
        TreeNode right;
        int N;
    }

    Suspect removed;
    int r = 0;
    private TreeNode root;
    int counter;

    @Override 
    public void insert(Suspect item)
    {
        Suspect tempSuspect = searchByAFMNoPrint(item.key());
        if (tempSuspect != null)
        {
            System.out.println("A suspect with the AFM " + item.getAFM() + " has already been inserted.");
        }
        else
        {
            counter++;
            System.out.println("No suspect found with AFM " + item.getAFM() + ". Proceeding to insertion..");
            root = insertR(root, item);
            System.out.println("Insertion completed.");
        }
    }

    private TreeNode insertR(TreeNode t, Suspect item) {
        if (t == null) return new TreeNode(item);
        t.N=counter;
        if (Math.random() * (t.N + 1) < 1.0)
            return insertT(t, item);
        if (item.key() < t.item.key())
            t.left = insertR(t.left, item);
        else
            t.right = insertR(t.right, item);
        return t;
    }

    private TreeNode insertT(TreeNode t, Suspect item)
    {
        if (t == null) return new TreeNode(item);
        if (item.key() < t.item.key())
        {
            t.left = insertT(t.left, item);
            t = rotR(t);
        }
        else
        {
            t.right = insertT(t.right, item);
            t = rotL(t);
        }
        return t;
    }

    private TreeNode rotR(TreeNode h) { TreeNode x = h.left; h.left = x.right; x.right = h; return x; }
    private TreeNode rotL(TreeNode h) { TreeNode x = h.right; h.right = x.left; x.left = h; return x; }

    @Override
    public void load(String filename)
    {
        try {
            File file = new File("Input Data/" + filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                Suspect tempSuspect = new Suspect();

                int afm = scanner.nextInt();
                afm = checkAFMValidity(afm);
                tempSuspect.setAFM(afm);

                String fn = scanner.next();
                tempSuspect.setFirstName(fn);

                String ln = scanner.next();
                tempSuspect.setLastName(ln);

                double savings = (double)scanner.nextDouble();
                tempSuspect.setSavings(savings);

                double taxedIncome = (double)scanner.nextDouble();
                tempSuspect.setTaxedIncome(taxedIncome);

                insert(tempSuspect);
            }
            scanner.close();
        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        }
    }

    @Override
    public void updateSavings(int AFM, double savings)
    {
        Suspect tempSuspect = searchByAFMNoPrint(AFM);
        if(tempSuspect != null)
        {
            tempSuspect.setSavings(savings);
            System.out.println("Savings updated!");
        }
        else
        {
            System.out.println("No suspect found with this afm");
        }
    }

    @Override
    public Suspect searchByAFM(int AFM)
    {
        return searchByAFMR(root, AFM);
    }

    private Suspect searchByAFMR(TreeNode t, int AFM) {
        if (t == null) {
            System.out.println("No suspect found with this AFM");
            return null;
        }
        if (AFM == t.item.getAFM()) {
            System.out.println(t.item);  //print suspect's info
            return t.item;
        }
        if (AFM < t.item.getAFM()) return searchByAFMR(t.left, AFM);
        else return searchByAFMR(t.right, AFM);
    }

    private Suspect searchByAFMNoPrint(int AFM) { return searchByAFMRNoPrint(root, AFM); }
    private Suspect searchByAFMRNoPrint(TreeNode t, int AFM) {
        if (t == null) {
            return null;
        }
        if (AFM == t.item.getAFM()) {
            return t.item;
        }
        if (AFM < t.item.getAFM()) return searchByAFMRNoPrint(t.left, AFM);
        else return searchByAFMRNoPrint(t.right, AFM);
    }

    @Override
    public SingleLinkedList<Suspect> searchByLastName(String last_name)
    {
        SingleLinkedList<Suspect> suspects = new SingleLinkedList<Suspect>();   //level order search
        SingleLinkedList<TreeNode> nodes = new SingleLinkedList<TreeNode>();    
        nodes.add(root);
        TreeNode tempNode = root;
        while (!nodes.isEmpty())
        {
            tempNode = nodes.pop();
            if (tempNode.item.getLastName().equals(last_name))
            {
                suspects.add(tempNode.item);//add suspect to the list
            }
            if (tempNode.left != null) nodes.add(tempNode.left);
            if (tempNode.right != null) nodes.add(tempNode.right);
        }
        if (suspects.isEmpty())
        {
            System.out.println("No suspects found with the specific last name.");
            return null;
        }
        else if (suspects.size() < 5)
        {
            suspects.printList(System.out);
            return null;
        }
        else
        {
            return suspects;
        }
    }


    public void remove(int AFM) {
        // find node to delete and its parent
        TreeNode current = root;
        TreeNode parent = null;

        while (true) {
            if (current == null)
            {
                System.out.println("No suspect found with this AFM");
                return;
            }

            if (current.item.getAFM() == AFM) {
                if (r == 0) removed = current.item; r++;
                break;
            }
            parent = current;

            if (current.item.getAFM() < AFM) {
                current = current.right;
            }
            else {
                current = current.left;
            }
        }
        // node to replace with
        TreeNode replace = null;
        
        // only right
        if (current.right == null)
            replace = current.right;
        else if (current.right == null)
            replace = current.left;
        else {
            // find left most child of current right subtree!
            TreeNode findCurrent = current.right;
            
            while (true) {
                if (findCurrent.left != null)
                    findCurrent = findCurrent.left;
                else
                    break;
            }
            // only has zero or one child (there is no left child!!!)
            remove(findCurrent.item.getAFM());
            
            findCurrent.left = current.left;
            findCurrent.right = current.right;
            
            replace = findCurrent;

        }
        // replace parents reference

        if (parent == null) { //root
            root = replace;
        } else {
            if (parent.left == current)
                parent.left = replace;

            if (parent.right == current)
                parent.right = replace;
        }
    }

    double sum=0.0 ;
    int count=0;
    @Override
    public double getMeanSavings() { //ok
        TreeNode temp = root;
        if (temp == null) return 0;
        else {

            double MeanSavings;
            MeanSavings = inOrder(root);
            return MeanSavings;
        }

    }

    double inOrder(TreeNode root) {
        sum = inorder_Recursive(root);
        return sum;
    }
    double inorder_Recursive(TreeNode root){//;, double sum,int count) {
        if (root != null) {
            count++;
            sum += root.item.getTaxedIncome();
            inorder_Recursive(root.left);
            inorder_Recursive(root.right);
            return sum/count;
        }else
            return sum;
    }

    int visit;
    Suspect[] help = new Suspect[counter];
    double[] cheat = new double[counter];
    @Override
    public void printΤopSuspects(int k) {
        if (k<=counter){
            help=Arrays.copyOf(help,counter+10);
            cheat=Arrays.copyOf(cheat,counter+10);
            preorder(root,cheat,help);
        for (int i=0; i<counter-1; i++){
            for (int j=0; j<i-counter-1;j++) {
                if (cheat[j] < cheat[j + 1]) {
                    double t = cheat[j];
                    cheat[j] = cheat[j + 1];
                    cheat[j + 1] = t;
                    Suspect t2 = help[j];
                    help[j] = help[j + 1];
                    help[j + 1] = t2;
                }
            }
        }

        for (int l=0; l<k; l++){
            System.out.println(help[l]);
        }
        }else
            System.out.println("The number given is greater than the number of suspects inserted.");

    }

    int local;
   void preorder(TreeNode h, double[] arr, Suspect [] help2){
    if (h!=null) {
        if (local>=counter){
            arr= Arrays.copyOf(arr,arr.length+10);
            help2=Arrays.copyOf(help2,help2.length+10);}
        arr[local] = h.item.getSavings() - h.item.getTaxedIncome();
        help2[local]=h.item;
        local++;
        preorder(h.left, arr,help2);
        preorder(h.right, arr,help2);
    }
    }


    @Override
    public void printByAFM() {
        printInOrder(root); //inorder traversal
    }

    void printInOrder(TreeNode h) {
        if (h != null) {
            printInOrder(h.left);
            System.out.println(h.item.toString());
            printInOrder(h.right);
        }
    }

    static int checkAFMValidity(int afm)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            if(afm >= 10000 && afm <= 99999)
            {
                return afm;
            }
            else
            {
                System.out.print("Re-enter afm (5 digits): ");
                afm = sc.nextInt();
            }
        }
    }

    public static void main(String[] args)
    {
        int number;
        boolean run = true;
        
        Scanner scanner = new Scanner(System.in);
        RandomizedBST tree = new RandomizedBST();
        
        while (run)
        {
            System.out.println("Menu:");
            System.out.println("1. Insert the data of suspect");
            System.out.println("2. Load data into the tree from external file");
            System.out.println("3. Update the savings(non-taxed income) of a particular suspect");
            System.out.println("4. Print the data of  suspect with a particular AFM");
            System.out.println("5. Print the suspects with a particular last name");
            System.out.println("6. Remove a suspect from the tree");
            System.out.println("7. Print the average of deposits ");
            System.out.println("8. Print the k most suspicious depositors");
            System.out.println("9. Print suspects's in ascending order by afm");
            System.out.println("10. Exit");
            System.out.print("\nChoose operation: ");
            number = scanner.nextInt();
            System.out.println();

            if (number == 1)            //insert
            {
                System.out.println("Enter suspects's information");
                System.out.print("First name: ");
                String fn = scanner.next();

                System.out.print("Last name: ");
                String ln = scanner.next();

                System.out.print("AFM: ");
                int afm = scanner.nextInt();
                afm = checkAFMValidity(afm);

                System.out.print("Taxed Income: ");
                double ti = (double)scanner.nextDouble();

                System.out.print("Non-taxed income: ");
                double nti = (double)scanner.nextDouble();

                tree.insert(new Suspect(afm, fn, ln, ti, nti));
            } 
            else if (number == 2)       //load
            {
                System.out.print("Enter file name: ");
                String file_name = scanner.next();
                tree.load(file_name);
            }
            else if (number == 3)      //updateSavings
            {
                System.out.print("Enter afm: ");
                int afm = scanner.nextInt();
                afm = checkAFMValidity(afm);

                System.out.print("Enter savings: ");
                double savings = (double)scanner.nextDouble();
                
                tree.updateSavings(afm, savings);
            }
            else if (number == 4)       //searchByAFM
            {
                System.out.print("Enter AFM: ");
                int afm = scanner.nextInt();
                afm = checkAFMValidity(afm);
                tree.searchByAFM(afm);
            }
            else if (number == 5)       //searchByLastName
            {
                System.out.print("Enter the last name: ");
                String ln = scanner.next();
                SingleLinkedList<Suspect> list = tree.searchByLastName(ln);
                if(list != null) { list.printList(System.out); }
            }
            else if (number == 6)       //remove
            {
                tree.r = 0;
                System.out.print("Enter suspect's AFM to be removed: ");
                int afm = scanner.nextInt();
                afm = checkAFMValidity(afm);
                tree.remove(afm);
                System.out.println(tree.removed.getFirstName() + " " + tree.removed.getLastName() + " got removed.");
            }
            else if (number == 7)
            {
                double mean_savings = tree.getMeanSavings();
                System.out.println("Average mean savings is: "+mean_savings);
            }
            else if (number == 8)       //printTopSuspects
            {
                System.out.print("How many suspects to print? ");
                int k = scanner.nextInt();
                tree.printΤopSuspects(k);
            }
            else if (number == 9)       //printByAFM
            {
                tree.printByAFM();
            }
            else if (number == 10)      //exit
            {      
                run = false;
                System.out.println("\nEnd of the program\nThank you very much");
            }
            else
            {
                System.out.print("Invalid operation. Returning to main menu.");   
            }
            System.out.println();
        }//while(run) end
        scanner.close();
    }
}