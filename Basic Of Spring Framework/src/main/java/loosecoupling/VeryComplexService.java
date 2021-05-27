package loosecoupling;

public class VeryComplexService {

    private SortAlgorithm sortAlgorithm;

    public VeryComplexService(SortAlgorithm sortAlgorithm){
        this.sortAlgorithm = sortAlgorithm;
    }

    public void complexBusiness(int array[]){
        this.sortAlgorithm.sort(array);
    }
}
