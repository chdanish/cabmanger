package com.RestSecureOath.client.service;

import com.RestSecureOath.client.StockPrice;
import com.google.gwt.user.client.rpc.AsyncCallback;

@SuppressWarnings("unchecked")
public interface StockPriceServiceAsync {

   void getPrices(String[] symbols, AsyncCallback<StockPrice> callback);

}
