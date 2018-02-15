import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
public class NetworkClient {

public static void main(String args[]) throws IOException{


    InetAddress address=InetAddress.getLocalHost();
    Socket s1=null;
    String line=null;
    BufferedReader br=null;
    BufferedReader is=null;
    PrintWriter os=null;

    try {
        s1=new Socket(address, 4445); // You can use static final constant PORT_NUM
        br= new BufferedReader(new InputStreamReader(System.in));
        is=new BufferedReader(new InputStreamReader(s1.getInputStream()));
        os= new PrintWriter(s1.getOutputStream());
    }
    catch (IOException e){
        e.printStackTrace();
        System.err.print("IO Exception");
    }

    System.out.println("Client Address : "+address);
    System.out.println("Enter Data to echo Server ( Enter QUIT to end):");

    String response=null;
                os.println("C");
			Random rand = new Random();
		int  n = rand.nextInt(10000) + 1;
		os.println(Integer.toString(n));	
		os.flush();
    try{

        //line=br.readLine();
	
        line=is.readLine();
	System.out.println("Waiting for server response");
        while(line.compareTo("QUIT")!=0){
	        os.println(line);
                os.flush();
                response=is.readLine();
                System.out.println("Consuming : "+response);
        //        line=br.readLine();
	try
        	{
                Thread.sleep(2000);
        	}catch(Exception e)
        {}

            }



    }
    catch(IOException e)
    {
        e.printStackTrace();
    System.out.println("Socket read Error");
    }
    finally{

        is.close();os.close();br.close();s1.close();
                System.out.println("Connection Closed");

    }

}
}
