package domain.saveLoad;

public class LoadAdapter implements Loader {

    Loader load;

    public void setAdapter(Loader load) {
        this.load = load;
    }

    @Override
    public void load() {
        
        load.load();
        
    }
    
}
