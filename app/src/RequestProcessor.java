import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 16.03.2016.
 */
public class RequestProcessor {

    public RequestProcessor() {
    }

    public void processRequest(InputStream inputStream, OutputStream outputStream) {
        System.out.println("Processing request...");
        try {
            List<String> data = parseRequest(inputStream);
            for (String line : data) {
                System.out.println(line);
            }
            writeResponse("Hello World!", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finished processing");
        }
    }

    private void writeResponse(String s, OutputStream os) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\n";
        String result = response + s;
        os.write(result.getBytes());
        os.flush();
    }

    private List<String> parseRequest(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> result = new ArrayList<>();
        String temp;
        while (true) {
            temp = reader.readLine();
            if (temp == null || temp.trim().isEmpty()) {
                break;
            }
            result.add(temp);
        }
        return result;
    }
}
