package service;

import java.io.ObjectInputStream;
import java.net.Socket;

import common.Message;

/**
 * 携带socket的一个线程
 */

public class ClientConnectServiceThread extends Thread{
    private Socket socket;

    public  ClientConnectServiceThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("等待服务器端发送信息...");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器端没有发送Message对象，线程会阻塞
                Message message = (Message)ois.readObject();
            } catch (Exception e) {
            }
           
        }
    }
}
