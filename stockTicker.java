package cloudComputing;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class cloudComputing extends Application
{
	String symbol  ="";
	String company = "";
	String response="";
	char   ch;
	String symbolString  = "";
	String companyString = "";
	String finalString   = "";
	String primary = "";
	String primaryString = "";
	String latestP = "";
	String latestPString = "";
	String latestV = "";
	String latestVString = "";
		
			
	
	
   @Override
   public void start(Stage primaryStage) throws Exception
   {
	  try
	  {
		// 
		// create labels and text fields
		//
	    Label     num1L      = new Label("Stock Symbol : ");
		TextField num1TF     = new TextField();
		
		//
		// text area for response
		//
	    TextArea ta = new TextArea();
	    ta.setEditable(false);
	    ta.setPrefWidth(500);
	    ta.setPrefHeight(500);
	    ta.setWrapText(true);
	    
	    
	    //
	    // button to send URL to cloud server
	    //
		Button clearButton    = new Button("CLEAR");     
	    clearButton.setOnAction(new EventHandler<ActionEvent>() 
	    {
	            @Override public void handle(ActionEvent e)
	            {
	            	Platform.runLater(new Runnable() 
					 {
					        public void run() 
					        {
				                num1TF.setText("");;
					        	ta.setText("");
					        }
					 });	
	            }
	    });
	    
	    
	    //
	    // button to send URL to cloud server
	    //
		Button submitButton    = new Button("SUBMIT");     
	    submitButton.setOnAction(new EventHandler<ActionEvent>() 
	    {
	            @Override public void handle(ActionEvent e)
	            {
	            	Platform.runLater(new Runnable() 
					 {
					        public void run() 
					        {
					        	symbol = num1TF.getText();
					        	if (symbol == "" || symbol == null || symbol == " " || (symbol.length() == 0))
					        	{
					        		Alert alert = new Alert(Alert.AlertType.ERROR);
							        alert.setTitle("--- No Stock Symbol Found Error ---");
							        alert.setHeaderText("No Stock Symbol!");
							          
							        alert.showAndWait();
					        	}
					        	else
					        	{
					        		urlIEXData iex = new urlIEXData();
					        	    response = iex.getIEXData(symbol);

					        	    
					        	    
					        	    int index = response.indexOf("symbol");
					        	    if (index != -1)
					        	    {
					        	    	symbol = "";
					        	    	index = index + 9; 
					        	    	
					        	    	while ((ch = response.charAt(index)) != '"')
					                    {
					                        symbol = symbol + ch;
					                        index++;
					                    }
					                     
					        	        symbolString = "      SYMBOL : " + symbol + "\r\n";
	      				             }
					        	 
					        	    index = response.indexOf("companyName");
					        	    if (index != -1)
					        	    {
					        	    	company = "";
					        	    	index = index + 14;
					        	    	
					        	    	while ((ch = response.charAt(index)) != '"')
					                    {
					                        company = company + ch;
					                        index++;
					                    }
					        	    	
					        	    	companyString = "COMPANY NAME : " + company + "\r\n";
					        	    }
					        	    
					        	    index = response.indexOf("primaryExchange");
					        	    if (index != -1)
					        	    {
					        	    	primary = "";
					        	    	index = index + 18;
					        	    	
					        	    	while ((ch = response.charAt(index)) != '"')
					                    {
					                        primary = primary + ch;
					                        index++;
					                    }
					        	    	
					        	    	primaryString = "PRIMARY EXCHANGE : " + primary + "\r\n";
					        	    }
					        	    
					        	    index = response.indexOf("latestPrice");
					        	    if (index != -1)
					        	    {
					        	    	company = "";
					        	    	index = index + 13;
					        	    	
					        	    	while ((ch = response.charAt(index)) != '"')
					                    {
					                        latestP = latestP + ch;
					                        index++;
					                    }
					        	    	
					        	    	latestPString = "LATEST PRICE : " + latestP + "\r\n";
					        	    }
					        	    
					        	    index = response.indexOf("latestVolume");
					        	    if (index != -1)
					        	    {
					        	    	company = "";
					        	    	index = index + 14;
					        	    	
					        	    	while ((ch = response.charAt(index)) != '"')
					                    {
					                        latestV = latestV + ch;
					                        index++;
					                    }
					        	    	
					        	    	latestVString = "LATEST VOLUME : " + latestV + "\r\n";
					        	    }
					        	    
					        	    
					        	    
					        	    finalString = symbolString + "     " + companyString + "	" + primaryString + "	" + latestPString + "	" + latestVString;
					        	    ta.setText(finalString);
					        	    
					        	    
					        	}
					        }
					 });	
	            }
	    });
		
		
		GridPane root = new GridPane();
		root.add(num1L,        0, 0);
		root.add(num1TF,       1, 0);
		
		root.add(submitButton,     0, 6);
		root.add(clearButton,      1, 6);
		root.add(ta,               0, 7);
		
		Scene scene = new Scene (root, 700, 700);
		primaryStage.setScene(scene);;
		primaryStage.show();
	   }
	
	   catch (Exception e)
	   {
		   e.printStackTrace();
	   }
   }

   public static void main(String [] args)
   {
     	launch(args);
   }
}
