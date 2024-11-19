import com.google.gson.*;

import java.io.IOException;
import java.util.Scanner;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.Desktop;

public class BrowserFuctions {
static String MakeRequest(String query) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient(); // Создаем клиента
    HttpRequest request = HttpRequest.newBuilder() // Строим запрос
            .uri(URI.create(query))
            .GET()
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Отправляем запрос
    return response.body();
}
static JsonArray Parsing(String response){
    // Парсим JSON-ответ от API в объект JsonObject
    JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
    // Извлекаем массив результатов поиска
    JsonArray searchResults = jsonObject.getAsJsonObject("query").getAsJsonArray("search");
    return searchResults;
}
static void OpenSite(int NumberOfReqeust, JsonArray searchResults) throws IOException {
    Desktop desktop = Desktop.getDesktop();

    if (NumberOfReqeust < searchResults.size() && NumberOfReqeust > 0) {

        if (desktop.isDesktopSupported()) {
            desktop.browse(URI.create("https://ru.wikipedia.org/w/index.php?curid=" + searchResults.get(NumberOfReqeust - 1).getAsJsonObject().get("pageid").getAsString())); // Открываем статью в браузере
        } else {
            System.out.println("Открытие браузера не поддерживается."); // Сообщаем о невозможности открыть браузер
        }
    }
}
}
