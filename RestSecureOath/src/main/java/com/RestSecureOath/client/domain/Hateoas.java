package com.RestSecureOath.client.domain;

import com.google.gwt.core.client.JavaScriptObject;

public class Hateoas extends JavaScriptObject {
	
	Object _embedded;
	
	
	Object _links;
	
	
	Object page;


	/**
	 * @return the _embedded
	 */
	public final native Object get_embedded() /*-{
		return this._embedded;
	}-*/;


	/**
	 * @return the _links
	 */
	public final native Object get_links() /*-{
		return this._links;
	}-*/;


	/**
	 * @return the page
	 */
	public final native Object getPage() /*-{
		return this.page;
	}-*/;	

}
