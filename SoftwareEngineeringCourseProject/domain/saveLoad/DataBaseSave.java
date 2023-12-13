package domain.saveLoad;

public class DataBaseSave implements Saver{


    public DataBaseSave databaseSave() {
        return new DataBaseSave();
    }
    @Override
    public void save() {
        

        System.out.println("Database save");
        
    }
    
}
