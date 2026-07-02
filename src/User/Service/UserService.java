package User.Service;

import User.Entity.UserEntity;
import Utils.ID.UserID;

import java.util.ArrayList;

public class UserService {
    final private ArrayList<UserEntity> users = new ArrayList<>();

    public UserEntity register(String userName, String password) {
        if (userNameExists(userName)) return null;
        UserEntity user = new UserEntity(userName, password);
        users.add(user);
        return user;
    }

    public boolean userNameExists(String userName) {
        for (UserEntity u : users) {
            if (u.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public UserEntity login(String userName, String password) {
        for (UserEntity u : users) {
            if (u.getUserName().equals(userName) && u.checkPassword(password)) {
                return u;
            }
        }
        return null; // controller decides what to do with null
    }

    public UserEntity findById(UserID id) {
        for (UserEntity u : users) {
            if (u.getID().equals(id)) return u;
        }
        return null;
    }

    public UserEntity[] getAllUsers() {
        UserEntity[] ans = new UserEntity[users.size()];
        users.toArray(ans);
        return ans;
    }

    public UserEntity load(UserID id, String userName, String password) {
        users.add(new UserEntity(id, userName, password));
        return users.get(users.size() - 1);
    }
}
