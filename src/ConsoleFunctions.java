import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

public class ConsoleFunctions{
    static String MakeQuery() throws UnsupportedEncodingException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ваш поисковый запрос:");
        String query = sc.nextLine();


        query = URLEncoder.encode(query, "UTF-8").replace("+", "%20");
        System.out.println("Закодированный запрос: " + query);

        return query;
    }
    static int SearchResultsOut(JsonArray searchResults){
        Scanner sc = new Scanner(System.in);
        System.out.println("Результаты поиска:");
        for (int i = 0; i < searchResults.size(); i++) { // Проходим по всем элементам массива
            JsonObject result = searchResults.get(i).getAsJsonObject(); // Получаем один результат
            // Выводим название и описание статьи
            System.out.println((i + 1) + ". " + result.get("title").getAsString());
            System.out.println("Описание: " + result.get("snippet").getAsString());
        }
        System.out.println("Введите номер запроса, который хотите выбрать:");
        int NumberOfReqeust;
        NumberOfReqeust = sc.nextInt();
        sc.close();
        return NumberOfReqeust;
    }
}
