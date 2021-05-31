package tighcoupling.level1;

public class Main {
    public static void main(String[] args) {
        VeryComplexService veryComplexService = new VeryComplexService();
        int[] array = {1, 2, 3, 4, 5};

        veryComplexService.complexBusiness(array);
    }
}
