import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
            try {
                download(link, filePath);
            } catch (Exception e) {
                System.out.println("Не могу загрузить " + link);
            }
        }
    }

    public static void download(String url, String path) throws IOException {
        URI uri = URI.create(url);
        URLConnection connection = uri.toURL().openConnection();
        InputStream inputStream = connection.getInputStream();
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            int b;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
        }
    }
}
