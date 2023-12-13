package domain.controllers;

import java.util.HashMap;
import java.util.Map;

import domain.saveLoad.*;

public class LoginController {

    private Map<String, String> userPas;

    FileSave filesave;
    DataBaseSave databasesave;
   // SaveAdapter adapter = new SaveAdapter(filesave);
    
        
    public LoginController() {
       userPas = new HashMap<String, String>();
       userPas.put("tonal19", "123");
       userPas.put("yhizir19", "1234");
       userPas.put("dganioglu19", "12345");
       userPas.put("bkoken19", "123456");
       userPas.put("kdai19", "1234567");
    }

    public boolean loginCheck(String username, String password) {
        //@Effects checks if the given username and password match 
        //Used for login

        if (password.equals(userPas.get(username)) ) {
           
            return true;

        }

        return false;

    }

    
}
