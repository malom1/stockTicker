package cloudComputing;

import java.util.Scanner;

public class urlIEXData 
{
	
	public String getIEXData(String sym)
	{
		String URLIEXString1 = "https://cloud.iexapis.com/stable/stock/";
		String URLIEXString2 = "/quote?token=pk_f9dc118fc47f4496a7a2e9e67b354e32";
	    String line = null;

	    try
        {
	       // add symbol to the end of the CNBC URL
	       java.net.URL djURL = new java.net.URL(URLIEXString1 + sym + URLIEXString2);
	       Scanner inputURL = new Scanner(djURL.openStream());
                   
	       while (inputURL.hasNext())
	       {
	          line = inputURL.nextLine();
	       }
	       
	       inputURL.close();
        }
	    
	    catch (java.net.MalformedURLException ex)
	    {
	    	ex.printStackTrace();
	    }

	    catch (java.io.IOException ex)
	    {
	    	ex.printStackTrace();
	    }
	    
	    return line;
	}
}
