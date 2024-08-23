public class Suspect {

    private int AFM;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;

    public Suspect() {} //empty constructor
    public Suspect(Suspect suspect)//copy constructor!!!
    {
        this.AFM = suspect.getAFM();
        this.firstName = suspect.getFirstName();
        this.lastName = suspect.getLastName();
        this.savings = suspect.getSavings();
        this.taxedIncome = suspect.getTaxedIncome();
    }
    public Suspect(int AFM, String firstName, String lastName, double savings, double taxedIncome)
    {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    int key(){return AFM;}

    void setAFM(int afm) { AFM=afm; }
    int getAFM() { return AFM; }

    void setFirstName(String FirstName) { firstName=FirstName; }
    String getFirstName() { return firstName; }

    void setLastName(String LastName) { lastName=LastName; }
    String getLastName() { return lastName; }

    void setSavings(double Savings) { savings=Savings; }
    double getSavings() { return savings; }

    void setTaxedIncome(double TaxedIncome) { taxedIncome=TaxedIncome; }
    double getTaxedIncome() { return taxedIncome; }

    public String toString()
    {
        return firstName + " " + lastName + "\n" +
        "AFM: " + AFM + "\n" +
         "Non-taxed income: "+ taxedIncome + "\n" +
                "Declared income: " + savings+"\n";
    }
}