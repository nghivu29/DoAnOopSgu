package test.data;

public class Imprint extends LibObject{
    public static Imprint create(String name){
        Imprint imprint = new Imprint();
        imprint.name = name;
        if (!imprint.init()){
            return null;
        }
        return imprint;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        return true;
    }
}
