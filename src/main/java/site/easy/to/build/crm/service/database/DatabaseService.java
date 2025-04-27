package site.easy.to.build.crm.service.database;


public interface DatabaseService {
    public void truncate() throws Exception ;
    public void generateData(int nbLine) throws Exception ;    
} 