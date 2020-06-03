package g76.Model;

import java.io.IOException;

public interface Observer {
    void gameChanged() throws IOException, InterruptedException;
}