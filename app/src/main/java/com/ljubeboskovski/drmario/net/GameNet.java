//package stratil.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class GameNet
{
    public static void main(String[] args) 
    {
        if(args[0].equals("s"))
        {
            GameNet n = makegameNetServer(1337);
            GameEvent g = n.readMessage();
            System.out.println(g.type);
            for (byte number : g.data) {
                System.out.print(" " + number + ", ");
            }
        }
        else if(args[0].equals("c"))
        {
            GameNet n = makegameNetClient("127.0.0.1", 1337);
            GameEvent g = new GameEvent();
            g.type = (byte)129;
            g.data = new byte[]{1,2,3,4,5,6};
            n.writeMessage(g);
        }
    }   
   
    private static GameNet makegameNetServer(int port)
    {
        Socket con;
        try{
            ServerSocket serverSocket;
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);
            con = serverSocket.accept();
            con.setTcpNoDelay(true);
            serverSocket.close();
        }
        catch(IOException io)
        {
            return null;
        }
        
        GameNet  gamet = new GameNet (con);
        return gamet;
    }

    private static GameNet makegameNetClient(String ip, int port)
    {
        Socket con;
        try{
            con = new Socket(ip,port);
            con.setTcpNoDelay(true);
        }
        catch(IOException io)
        {
            return null;
        }
        GameNet  gamet = new GameNet (con);
        return gamet;
    }
    Socket con;
    DataInputStream in;
    DataOutputStream out;
    public GameNet(Socket con1)
    {
        this.con = con1;
        try{
            in = new DataInputStream(con.getInputStream());
            out = new DataOutputStream(con.getOutputStream());
        }
        catch(IOException io)
        {
            ///What do we do ??
        }

    }

    public static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }

    public void writeMessage(GameEvent g)
    {
        try{
            out.writeByte(g.type);
            out.writeByte(g.data.length);
            out.write(g.data, 0, g.data.length);
        }
        catch(IOException io)
        {}
    }
    
    public GameEvent readMessage()
    {
        GameEvent g = null;////////Bad
        try{
            g = new GameEvent();
            g.type = in.readByte();
            int len = unsignedToBytes(in.readByte());
            g.data = new byte[len];
            in.read(g.data);

        }
        catch(IOException io)
        {}
        return g;
    }
}