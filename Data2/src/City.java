
interface CityInterface {
    int getID();

    String getName();

    int getPopulation();

    int getCovidCases();

    void setID(int ID);

    void setName(String Name);

    void setPopulation(int population);

    void setCovidCases(int CovidCases);

    String toString();

    void compareTo(double[] arr, int j, String[] arr1, int[] arr2, int[] arr3, int[] arr4,int size);
}


public class City implements CityInterface {
    protected int ID;
    protected String Name;
    protected int Population;
    protected int CovidCases;


   public City(int ID, String Name, int Population, int CovidCases ){
        this.ID=ID;
        this.Name=Name;
        this.Population=Population;
        this.CovidCases=CovidCases;
    }

    @Override
     public int getID() {
        return ID;
    }

    @Override
    public String getName(){
        return Name;
    }

    @Override
    public int getPopulation(){
        return Population;
    }

    @Override
    public int  getCovidCases(){
        return CovidCases;
    }
    @Override
    public void setID(int ID){
        this.ID=ID;
    }

    @Override
    public void setName(String Name){
        this.Name=Name;
    }

    @Override
    public void setPopulation(int Population){
        this.Population=Population;
    }

    @Override
    public void setCovidCases(int CovidCases){
        this.CovidCases=CovidCases;
    }

     @Override
     public void compareTo(double[] arr, int j, String[] arr1, int[] arr2, int[] arr3, int[] arr4,int size) {
         QuickSort ob= new QuickSort();
         for  (int c=0; c<j-1; c++) {
             for (int t = c; t < j - 1; t++) {
                 if (arr1[c]!=null && arr1[t]!=null) {
                     if (arr[c] == arr[t]) {
                         if (arr1[c].equals(arr1[t])) {
                             if (arr2[c] > arr2[t]) {
                                 ob.swap(arr, c, t);
                                 ob.swap1(arr2, c, t);
                                 ob.swap2(arr1, c, t);
                                 ob.swap1(arr3, c, t);
                                 ob.swap1(arr4, c, t);
                             }
                         } else if (ob.StrCompare(arr1[c], arr1[t]) > 0) {
                             ob.swap(arr, c, t);
                             ob.swap1(arr2, c, t);
                             ob.swap2(arr1, c, t);
                             ob.swap1(arr3, c, t);
                             ob.swap1(arr4, c, t);
                         }
                     }

                 }
             }
         }
     }
     @Override
    public String toString(){
       return this.Name ;
     }

}


