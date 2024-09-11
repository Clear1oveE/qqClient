package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import common.Message;
import common.MessageType;
import common.User;

/**
 * 用于处理用户登录/注册
 */

public class UserClientService {
    private User user = new User();
    private Socket socket;

    /**
     * 处理用户登录，发送给客户端，如果正确新开一个线程，返回true 
     * @param userName
     * @param passwd
     * @return 用户名和密码是否正确
     * @throws Exception
     */
    public boolean checkUser(String userName, String passwd) throws Exception {
        user.setUserId(userName);
        user.setPasswd(passwd);
        boolean ifSign = false;

        //将用户信息发送给服务端
        socket = new Socket(InetAddress.getLocalHost(), 9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(user);

        //接收服务端的验证信息
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message message = (Message) ois.readObject();
        
        //验证用户信息是否正确
        if (message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESSED)) {
            //登陆成功，新开一个线程存放socket
            ClientConnectServiceThread ccst = new ClientConnectServiceThread(socket);
            ccst.start();
            ManageClientConnectServiceThread.addThread(userName, ccst);
            ifSign = true;
        } else {
            //登录失败，不启动线程，关闭socket
            socket.close();
        }
        return ifSign;
    }
}
