package uz.micros.jstore.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService  {

    @Autowired
    private HttpServletRequest request;

    public String getPath(){
        return request.getSession().getServletContext().getRealPath("/resources/images");
    }

    public void saveBookImage(int bookId, byte[] content) {
        String s = getPath();
        Path path = Paths.get(s, bookId + ".jpg");
        try {
            File file = new File(path.toString());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
            stream.write(content);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
