package Models;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

//1571020257 - Nguyễn Ngọc Đan Trường
public class AuthenciationServiceImp extends UnicastRemoteObject implements AuthenciationService{
    public List<User> users = new ArrayList<>();
    public List<Session> threads = new ArrayList<>();
    public static ClientSocket clientSocket;
    //1571020257 - Nguyễn Ngọc Đan Trường
    public AuthenciationServiceImp() throws RemoteException{
        super();
    }
    //1571020257 - Nguyễn Ngọc Đan Trường
    @Override
    public String login(String username, String password) {
        for(User u: users){
            if(u.username.equals(username) && u.password.equals(password)) return u.getHashKey();
        }
        return null;
    }
//1571020257 - Nguyễn Ngọc Đan Trường
    @Override
    public boolean register(String username, String password, String fullName) {
        for(var u: users) if(u.username.equals(username)) return false;
        users.add(new User(username, password, fullName));
        System.out.println("[Register] " + username + " - " + password);
        return true;
    }
//1571020257 - Nguyễn Ngọc Đan Trường
    @Override
    public String getFullname(String hashKey) {
        for(var u: users) if(u.getHashKey().equals(hashKey)){
            Session session = new Session(hashKey);
            session.start();
            threads.add(session);
            return u.fullName;
        }
        return "Unknown";
    }
}
