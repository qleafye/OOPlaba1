import com.google.gson.*;
import java.util.Scanner;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.Desktop;

public class WikiSearch{
    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        try {

            String query = ConsoleFunctions.MakeQuery();

            query = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" + query;



            String response = BrowserFuctions.MakeRequest(query);
            JsonArray searchResults = BrowserFuctions.Parsing(response);
            // Парсим JSON-ответ от API в объект JsonObject


           ConsoleFunctions.SearchResultsOut(searchResults);
            System.out.println("Введите номер запроса, который хотите выбрать:");
            int NumberOfReqeust;

            NumberOfReqeust = sc.nextInt();
            BrowserFuctions.OpenSite(NumberOfReqeust, searchResults);

        }
        catch (UnsupportedEncodingException e){
             System.out.println("Произошла ошибка при кодировании:" +  e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка при вводе значения");
        }
        finally {
            sc.close();
        }


    }
}