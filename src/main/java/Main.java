import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        System.out.println(images);
    }
}
