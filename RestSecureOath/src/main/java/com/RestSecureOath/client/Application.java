package com.RestSecureOath.client;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fusesource.restygwt.client.Resource;

import com.RestSecureOath.client.domain.Hateoas;
import com.RestSecureOath.client.service.StockPriceService;
import com.RestSecureOath.client.service.StockPriceServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * This is the application entry point. It just bootstraps Angular...
 */

public class Application implements EntryPoint{
	
	// A simple data type that represents a contact.
	  private static class Contact {
	    private final String address;
	    private final String name;
	    private final String cellnumber;

	    public Contact(String name, String address,String cellnumber) {
	      this.name = name;
	      this.address = address;
	      this.cellnumber=cellnumber;
	    }
	  }

	  // The list of data to display.
	  private static List<Contact> CONTACTS = new LinkedList<Contact>(Arrays.asList(new Contact("John",
	      "123 Fourth Road","+92-345-5522123"), new Contact("Mary", "222 Lancer Lane","+91-445-2522123")));
	//https://github.com/apazzolini/blc-docs/blob/3b00f6c89e5bd4311eec16a190aaf0d896afc11d/Tutorials/Admin-Tutorials/Invoking-a-Remote-Service.md
	
	private static final StockPriceServiceAsync stockPriceSvc = (StockPriceServiceAsync) GWT.create(StockPriceService.class);
	private static final Resource resource = new Resource(  "http://localhost:8080/check");
	static VerticalPanel customLogArea = new VerticalPanel();
	private static Logger logger = Logger.getLogger("");
	static{
		logger.addHandler(new HasWidgetsLogHandler(customLogArea));
	}
	
	private static class Myclick implements ClickHandler{
		String[] symbol = {"nasdek", "mello"} ;
		
		String url = "http://localhost:8080/a_drivers/pageable/list";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

		public static native void console(Object msg) /*-{
		  console.log(msg);
		}-*/;
		
		
		@Override
		public void onClick(ClickEvent event) {
			
			logger.severe(Integer.toString(CONTACTS.size()));
			Console.log("button pressed");
			CONTACTS.remove(CONTACTS.size()-1);
			logger.severe(Integer.toString(CONTACTS.size()));
			try {
				  Request request = builder.sendRequest(null, new RequestCallback() {
				    public void onError(Request request, Throwable exception) {
				       // Couldn't connect to server (could be timeout, SOP violation, etc.)
				    	logger.log(Level.OFF, "error");
				    }

				    public void onResponseReceived(Request request, Response response) {
				    	
				    	Console.log("hello");
				    	logger.severe(response.toString());
				    	Console.log("hello");
				    	Console.log(response);
				    	Console<Hateoas> clbytype = new Console<Hateoas>();
						/*List<Hateoas> lst =*/  clbytype.deserializablelist(response);
				      if (200 == response.getStatusCode()) {
				    	  logger.log(Level.WARNING, "done");
				          // Process the response in response.getText()
				      } else {
				    	  logger.log(Level.SEVERE, "fail");
				        // Handle the error.  Can get the status text from response.getStatusText()
				      }
				    }
				  });
				} catch (RequestException e) {
					logger.log(Level.SEVERE, e.toString());
				  // Couldn't connect to server
				}
		}
		
	}
	
    static class StockData extends JavaScriptObject {                              // <span style="color:black;">**(1)**</span>
  // Overlay types always have protected, zero argument constructors.
  protected StockData() {}                                              // <span style="color:black;">**(2)**</span>

      // JSNI methods to get stock data.
  public final native String getSymbol() /*-{ return this.symbol; }-*/; // <span style="color:black;">**(3)**</span>
  public final native double getPrice() /*-{ return this.price; }-*/;
  public final native double getChange() /*-{ return this.change; }-*/;

      // Non-JSNI method to return change percentage.                       // <span style="color:black;">**(4)**</span>
  public final double getChangePercent() {
    return 100.0 * getChange() / getPrice();
  }
}
	
	
	 public void onModuleLoad() {
		 
		 

		    // An example of adding our own custom logging area.  Since VerticalPanel extends HasWidgets,
		    // and handles multiple calls to add(widget) gracefully we simply create a new HasWidgetsLogHandler
		    //  with it, and add that handler to a logger. In this case, we add it to a particular logger in order
		    //  to demonstrate how the logger hierarchy works, but adding it to the root logger would be fine.
		    logger.addHandler(new HasWidgetsLogHandler(customLogArea));
		 

		 String[] symbol = {"nasdek", "mello"} ;
		 
		 //((RestServiceProxy)stockPriceSvc).setResource(resource);
		
		 Button b = new Button("Jump!", new Myclick());

		    // Create a CellTable.
		    CellTable<Contact> table = new CellTable<Contact>();

		    // Create name column.
		    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
		      @Override
		      public String getValue(Contact contact) {
		        return contact.name;
		      }
		    };

		    // Create address column.
		    TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
		      @Override
		      public String getValue(Contact contact) {
		        return contact.address;
		      }
		    };
		    
		 // Create address column.
		    TextColumn<Contact> cellnumberColumn = new TextColumn<Contact>() {
		      @Override
		      public String getValue(Contact contact) {
		        return contact.cellnumber;
		      }
		    };

		    // Add the columns.
		    table.addColumn(nameColumn, "Name");
		    table.addColumn(addressColumn, "Address");
		    table.addColumn(cellnumberColumn, "Cell#");

		    // Set the width of the table and put the table in fixed width mode.
		    table.setWidth("100%", true);

		    // Set the width of each column.
		    table.setColumnWidth(nameColumn, 33.33, Unit.PCT);
		    table.setColumnWidth(addressColumn, 33.33, Unit.PCT);
		    table.setColumnWidth(cellnumberColumn, 33.33, Unit.PCT);
		    
		    

		    // Set the total row count. This isn't strictly necessary, but it affects
		    // paging calculations, so its good habit to keep the row count up to date.
		    table.setRowCount(CONTACTS.size(), true);

		    // Push the data into the widget.
		    table.setRowData(0, CONTACTS);

		    // Add it to the root panel.
		    RootPanel.get().add(table);
		    
		    // Add button to test root panel.
		    RootPanel.get().add(b);
		    
		    // D3 version test
		    RootPanel.get().add(customLogArea);
		    
		  }
		}