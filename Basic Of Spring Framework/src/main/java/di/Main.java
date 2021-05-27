package di;

public class Main {
    public static void main(String[] args) {
        Outfit bikini = new Bikini();
        Girl ngocTrinh = new Girl(bikini);

        ngocTrinh.wear();
    }
}
