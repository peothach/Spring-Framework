package tighcoupling.level2;

public class VeryComplexService {

    private SortAlgorithm sortAlgorithm;

    public VeryComplexService(){
        sortAlgorithm = new BubbleSortAlgorithm();
    }

    public void complexBusiness(int array[]){
        sortAlgorithm.sort(array);
    }
}
