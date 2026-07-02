package Utils.ID;

import Utils.ID.Base.ID;

public class UserID extends ID {
    public UserID() {
        super("U_");
    }
    public UserID(int value){
        super("U_", value);
    }
}
