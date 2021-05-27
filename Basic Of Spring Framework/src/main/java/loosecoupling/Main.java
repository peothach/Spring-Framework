package loosecoupling;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        VeryComplexService veryComplexService1 = new VeryComplexService(new BubbleSortAlgorithm());
        VeryComplexService veryComplexService2 = new VeryComplexService(new QuickSortAlgorithm());

        veryComplexService1.complexBusiness(array);
        veryComplexService2.complexBusiness(array);
    }
}
