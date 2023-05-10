import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://skillbox.ru/";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("img");

        HashSet<String> images = new HashSet<>();
        for (Element image : elements) {
            images.add(image.attr("abs:src"));
        }

        int number = 1;
        for (String link : images) {
            String extension = link
                    .replaceAll("^.+\\.", "")
                    .replace("?.+$", "");

            String filePath = "data/" + number++ + "." + extension;
        }

    }
    public static void download(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        InputStream inputStream = connection.getInputStream();
    }
}
