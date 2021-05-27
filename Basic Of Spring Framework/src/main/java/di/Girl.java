package di;

public class Girl {

    private Outfit outfit;

    public Girl(Outfit outfit){
        this.outfit = outfit;
    }

    public void wear(){
        outfit.wear();
    }
}
