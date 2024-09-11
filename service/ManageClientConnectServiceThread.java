package service;

import java.util.HashMap;

/**
 * 用于管理客户端连接到服务端的线程
 * 线程池
 */

public class ManageClientConnectServiceThread {
    public static HashMap<String, ClientConnectServiceThread> hm = new HashMap<>();

    public static void addThread(String userID,ClientConnectServiceThread thread){
        hm.put(userID, thread);
    }

    public static ClientConnectServiceThread getThread(String userID){
        return hm.get(userID);
    }
}
