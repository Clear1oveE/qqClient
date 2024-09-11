package view;

import java.util.Scanner;

import service.ClientConnectServiceThread;
import service.UserClientService;

/**
 * 客户端的菜单界面
 */
public class QQView {
    private boolean loop = true;//是否显示菜单
    private UserClientService userClientService = new UserClientService();

    public static void main(String[] args) throws Exception {
        new QQView().mainMenu();
    }
    

    private void mainMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("===========欢迎登陆==========");
            System.out.println("\t 1 登陆系统");
            System.out.println("\t 9 退出系统");
            System.out.print("请输入：");

            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    System.out.print("请输入用户名：");
                    String userId = scanner.nextLine();
                    System.out.print("请输入密码：");
                    String userPasswd = scanner.nextLine();
                    //发给服务端验证...
                    if (userClientService.checkUser(userId, userPasswd)) {
                        System.out.println("=========欢迎用户" + userId + "=========");
                        //二级菜单
                        while (loop) {
                            System.out.println("\n==========网络通信系统二级菜单============");
                            System.out.println("\t 1 显示在线用户列表");
                            System.out.println("\t 2 群发消息");
                            System.out.println("\t 3 私聊消息");
                            System.out.println("\t 4 发送文件");
                            System.out.println("\t 9 退出系统");
                            System.out.print("请输入你的选择：");
                            int key = scanner.nextInt();
                            switch (key) {
                                case 1:
                                    System.out.println("显示在线用户列表");
                                    break;
                                case 2:
                                    System.out.println("群发消息");
                                    break;
                                case 3:
                                    System.out.println("私聊消息");
                                    break;
                                case 4:
                                    System.out.println("发送文件");
                                    break;
                                case 9:
                                    System.out.println("退出系统");
                                    loop = false;
                                    break;
                            
                                default:
                                    break;
                            }
                        }
                    } else {
                        System.out.println("用户名或密码错误");
                    }
                    break;
                case 9:
                    System.out.println("退出系统");
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }
}
