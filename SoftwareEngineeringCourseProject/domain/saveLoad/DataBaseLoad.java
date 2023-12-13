package domain.saveLoad;

public class DataBaseLoad implements Loader{

    public DataBaseLoad databaseLoadDataBaseLoad() {
        return new DataBaseLoad();
    }
    @Override
    public void load() {
        

        System.out.println("Database load");
        
    }
    
}
