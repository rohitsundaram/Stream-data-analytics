import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server_X_Client {
	static String buffer[]=new String[100000000];//read it from config file.
	static int first;
	static int last;
	public static void main(String args[]){


    Socket s=null;
    ServerSocket ss2=null;
    System.out.println("Server Listening......");
    try{
        ss2 = new ServerSocket(4445); // can also use static final PORT_NUM , when defined

    }
    catch(IOException e){
    e.printStackTrace();
    System.out.println("Server error");

    }

    while(true){
        try{
            s= ss2.accept();
            System.out.println("connection Established ");
            ServerThread st=new ServerThread(s);
            st.start();

        }

    catch(Exception e){
        e.printStackTrace();
        System.out.println("Connection Error");
    }
    }


}

}

class ServerThread extends Thread{  

    String line=null;
    BufferedReader  is = null;
    PrintWriter os=null;
    Socket s=null;
    
    Server_X_Client ob1=new Server_X_Client();

    public ServerThread(Socket s){
        this.s=s;
    }

    public void run() {
    try{
        is= new BufferedReader(new InputStreamReader(s.getInputStream()));
        os=new PrintWriter(s.getOutputStream());

    }catch(IOException e){
        System.out.println("IO error in server thread");
    }

    try {
        line=is.readLine();
        while(line.compareTo("QUIT")!=0){

            os.println(line);
            os.flush();
	    if(line.equals("C"))
	    {
		    System.out.println("It is Consumer");
		    line=is.readLine();
		    System.out.println(line);
		    int id=Integer.parseInt(line);
		    consumer(id);
	    }
	    if(line.equals("P"))
	    {
		    System.out.println("It is Producer");
        		line=is.readLine();
		    System.out.println(line);
		    int id=Integer.parseInt(line);
		    producer(id);
	    }
            System.out.println("Response roravish to Client  :  "+line);
            line=is.readLine();
        }   
    } catch (IOException e) {

        line=this.getName(); //reused String line for getting thread name
        System.out.println("IO Error/ Client "+line+" terminated abruptly");
    }
    catch(NullPointerException e){
        line=this.getName(); //reused String line for getting thread name
        System.out.println("Client "+line+" Closed");
    }

    finally{    
    try{
        System.out.println("Connection Closing..");
        if (is!=null){
            is.close(); 
            System.out.println(" Socket Input Stream Closed");
        }

        if(os!=null){
            os.close();
            System.out.println("Socket Out Closed");
        }
        if (s!=null){
        s.close();
        System.out.println("Socket Closed");
        }

        }
    catch(IOException ie){
        System.out.println("Socket Close Error");
    }
    }//end finally
    }

public void consumer(int id)
{
	Server_X_Client ob2=new Server_X_Client();
	try
	{
		Thread.sleep(2000);
	}catch(Exception e)
	{}
                os.flush();
	BufferedReader br=null; String response=null;
br= new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Hey, You are in Consumer function");
try{
	System.out.println("Waiting");
        //line=br.readLine(); 
	System.out.println("Done");
        while(line.compareTo("QUIT:w")!=0){
			if(ob1.first==0||ob1.first==ob1.last);
			
			//System.out.println("Waiting for producer to put");
		else
		{
		line=ob1.buffer[ob1.last++];
		line=line+" client id= "+Integer.toString(id);
                os.println(line);
                os.flush();
	                //response=is.readLine();
                System.out.println(line);//Printing produced by then id.
                try
        	{
                Thread.sleep(2000);
        	}catch(Exception e)
     		{}
//line=br.readLine();
		}
            }
               
    }
            
    catch(Exception e){ 
    System.out.println("Socket read Error");
    }

}
synchronized public void producer(int id)
{

	try
	{
		Thread.sleep(2000);
	}catch(Exception e)
	{}
	System.out.println("Hey, You are in Producer function with id "+id);
try{
        is= new BufferedReader(new InputStreamReader(s.getInputStream()));
        os=new PrintWriter(s.getOutputStream());

    }catch(IOException e){
        System.out.println("IO error in server thread");
    }

		for(int i=0;i<100000000;i++)
		{  
		     try
		       {	
			line=is.readLine();
	ob1.buffer[ob1.first++]="Produced by"+(Integer.toString(id))+" "+line;
		}catch(IOException e){}
		}

}

synchronized static void put(int line)
{
    Server_X_Client ob1=new Server_X_Client();
}
}
