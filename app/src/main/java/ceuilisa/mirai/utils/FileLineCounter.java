package ceuilisa.mirai.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FileLineCounter {

    private String mFolderPath;
    private int mLineCount;

    public FileLineCounter(String mFolderPath) {
        this.mFolderPath = mFolderPath;
    }

    public void run() {
        System.out.println("开始文件检索：");
        traverseFolder(mFolderPath);
        System.out.println("一共有：" + mLineCount + "行代码");
    }


    private void traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        System.out.println("********************************");
                        System.out.println("文件名:" + file2.getAbsolutePath());
                        countLine(file2);
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    private void countLine(File file) {
        try {
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);
                int linenumber = 0;
                while (lnr.readLine() != null) {
                    linenumber++;
                }
                System.out.println("单个文件行数 " + linenumber);
                System.out.println();
                mLineCount = mLineCount + linenumber;
                lnr.close();
            } else {
                System.out.println("File does not exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
