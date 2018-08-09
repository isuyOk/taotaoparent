import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class FTPTest {
    @Test
    public void test(){
        FTPClient ftpClient=new FTPClient();
        try {
            ftpClient.connect("127.0.0.1",21);
            ftpClient.login("uu","uu");
            FileInputStream inputStream=new FileInputStream("/home/uu/Downloads/2.jpg");
            ftpClient.changeWorkingDirectory("/home/uu/ftpuser/www");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile("hello3.jpg",inputStream);
            ftpClient.logout();
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
