import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.*;
import java.util.Random;;
public class NetworkProducer {

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
		os.println("P");
		Random rand = new Random();
		int  n = rand.nextInt(10000) + 1;
		os.println(Integer.toString(n));
                os.flush();
 // The name of the file to open.
        String fileName = "temp.txt";

        // This will reference one line at a time
    //    String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println("Read data from file:  "+line);   
		    os.println(line);
		try
        	{
                Thread.sleep(2000);
        	}catch(Exception e)
        {}
                os.flush();
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileName + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }


    try{
        line=br.readLine(); 
        while(line.compareTo("QUIT")!=0){
                os.println(line);
                os.flush();
	long threadId = Thread.currentThread().getId();
        	System.out.println("Thread # " + threadId + " is doing this task");

                response=is.readLine();
                System.out.println("Server Response : "+response);
		try
        	{
                Thread.sleep(2000);
        	}catch(Exception e)
        {}
                 
		line=br.readLine();

            }



    }
    catch(IOException e){
        e.printStackTrace();
    System.out.println("Socket read Error");
    }
    finally{

        is.close();os.close();br.close();s1.close();
                System.out.println("Connection Closed");

    }

}
}
