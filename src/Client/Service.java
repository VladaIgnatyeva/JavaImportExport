package Client;

import Models.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class Service {

    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket socket;

    private static Service instance;

    private Service () {
        try {
            socket = new Socket("localhost", 8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully connected to " + socket.getInetAddress().toString() + ":" + socket.getPort());

        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Service getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new Service();
        return instance;
    }

    public <TIn, Tout> Tout request (String route, TIn data, Class<Tout> outClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request<TIn>(route, data);
        String requestJson = objectMapper.writeValueAsString(request);
        System.out.println("Send: " + requestJson);
        writer.write(requestJson);
        writer.newLine();
        writer.flush();

        String answer = "";
        do {
            answer += reader.readLine();
        } while (reader.ready());
        System.out.println("Received: " + answer);
        Tout out = objectMapper.readValue(answer, outClass);
        return out;
    }

    public <Tout> Tout request (String route, Class<Tout> outClass) throws IOException {
        return request(route, null, outClass);
    }

//    public String request (String request) {
//        while (true) {
//            try {
//                writer.write(request);
//                writer.newLine();
//                writer.flush();
//                String answer = "";
//                if (request.equals("Quit")) return "break";
//                do {
//                    answer += reader.readLine();
//                    //System.out.println(answer);
//                } while (reader.ready());
//                return answer;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    public void closeSocket (){
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
