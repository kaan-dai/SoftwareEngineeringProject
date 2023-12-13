package domain.saveLoad;

public class SaveAdapter implements Saver{

    Saver save;

    

    public void setAdapter(Saver save) {
        this.save = save;
    }

    @Override
    public void save() {
        

        save.save();
        
    }

    
}
