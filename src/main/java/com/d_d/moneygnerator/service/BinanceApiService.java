package com.d_d.moneygnerator.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
public class BinanceApiService {

    private static final String API_BINANCE_URL = "https://api.binance.com/api/v3/ticker/price?symbol=";

    public static String getCurrentPrice(String symbol) {
        try {
            URL url = new URL(API_BINANCE_URL + symbol);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    informationString.append(line);
                }
                br.close();

                JSONObject json = new JSONObject(informationString.toString());
                return json.getString("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
