class IntegerComparator {
    public double compare(City t1, City t2) {
        if (((t1.CovidCases * 50000) / t1.Population - (t2.CovidCases * 50000) / t2.Population) >= 0)
            return 1;
     else
            return -1;

    }
}

