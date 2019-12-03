package Server.Handlers;

import Server.Server;
import com.google.gson.JsonObject;

import java.io.IOException;

public interface Handler {
    public Object handle (String data) throws IOException;

}
