package org.academiadecodigo.codezillas.trade_n.currency;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeManager {

    public double getRates(CurrencyType base, CurrencyType target) {

        if (base == target) {
            return 1;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.cryptonator.com/api/ticker/" + base.getCode() + "-" + target.getCode()))
                .header("Accept", "application/json")
                .build();

        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());
            JSONObject ticker = json.getJSONObject("ticker");

            return ticker.getDouble("price");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
