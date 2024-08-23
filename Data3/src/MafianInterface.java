public interface MafianInterface
{
    void insert(Suspect item);
    void load(String filename);
    void updateSavings(int AFM, double savings);
    Suspect searchByAFM(int AFM);
    SingleLinkedList<Suspect> searchByLastName(String last_name);
    void remove(int AFM);
    double getMeanSavings();
    void printΤopSuspects(int k);
    void printByAFM();
}