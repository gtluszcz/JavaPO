package Lab11;

public class Main {
    public static void main(String[] args){
        DownloadExample dow = new DownloadExample();
        //dow.sequentialDownload();
        dow.concurrentDownload();
        dow.concurrentDownload2();

    }
}
