package com.anji.plus.study.file;

import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import ch.qos.logback.core.util.FileUtil;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.util.Date;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/25 10:01
 * @Description:
 */
public class FileDemo {
    public static void main(String[] args) {
//        System.out.println("文件大小： " + getFileSize("filename.txt"));
//        deleteDir(new File("src/a"));
//        createRecursiveFile();
//        fileDir();
        visitAllDirsAndFiles(new File("src"));
    }

    //使用 File 类的 dir.isDirectory() 和 dir.list() 方法来遍历目录
    public static void visitAllDirsAndFiles(File dir){
        System.out.println(dir);
        if (dir.isDirectory()){
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]));
            }
        }
    }


    //使用 File 类的 list 方法来遍历指定目录下的所有目录
    private static void fileDir(){
        File dir = new File("src/a");
        File[] files = dir.listFiles();
        String[] children = dir.list();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);
        System.out.println(files.length);
        if (files.length == 0){
            System.out.println("目录不存在或不是一个目录");
        } else {
            for (int i = 0; i < files.length; i++) {
                File fileName = files[i];
                System.out.println(fileName.toString());
            }
        }

        //使用 File 类的 list 方法来输出指定目录下的所有文件
        if (children == null){
            System.out.println( "目录不存在或它不是一个目录");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }

        //查找以字母 'b' 开头的所有文件
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("b");
            }
        };
        String[] childrens = dir.list(filenameFilter);
        if (children == null){
            System.out.println( "目录不存在或它不是一个目录");
        } else {
            for (int i = 0; i < childrens.length; i++) {
                String filename = childrens[i];
                System.out.println(filename);
            }
        }
        
        
        File[] roots = File.listRoots();
        System.out.println("系统所有根目录: ");
        for (int i = 0; i < roots.length; i++) {
            System.out.println(roots[i].toString());
        }
        
        String curDir = System.getProperty("user.dir");
        System.out.println("你当前的工作目录为 :" + curDir);

    }

    private static void fileIsExit(){
        File file = new File("src/a");
        if(file.isDirectory()){
            String[] files = file.list();
            if (file.length() > 0){
                System.out.println("目录 " + file.getPath() + " 不为空！");
            }
        }

        System.out.println( "文件是否隐藏  "+file.isHidden());

        String strParentDirectory = file.getParent();
        System.out.println("文件上级目录： " + strParentDirectory);

        System.out.println("文件最后修改时间 " + new Date(file.lastModified()));

//        file = new File("src");
        //打印目录结构
        showDir(1, file);
    }

    public static void showDir(int indent, File file){
        for (int i = 0; i < indent; i++) {
            System.out.print("-");
        }
        System.out.println(file.getName());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                showDir(indent + 4, files[i]);
            }

        }
    }

    //使用 File 类的 ofdir.isDirectory(), dir.list() 和 deleteDir() 方法在一个个删除文件后删除目录
    private static boolean deleteDir(File dir){
        if (dir.isDirectory()){
            String[] chileren = dir.list();
            for (int i = 0; i < chileren.length; i++) {
                boolean success = deleteDir(new File(dir, chileren[i]));
                if (!success){
                    return false;
                }
            }
        }
        if (dir.delete()){
            System.out.println("目录被删除");
        } else {
            System.out.println("目录删除失败！");
            return false;
        }
        return true;
    }

    //创建递归目录
    private static void createRecursiveFile(){
        String directories = "src/a/b/c/d/e";
        File file = new File(directories);
        //创建递归目录
        boolean result = file.mkdirs();
        System.out.println("state = " + result);
    }

    //判断路径是否一致
    private static void fileCompare(){
        File file = new File("newfile.txt");
        File file1 = new File("newfile.txt");
        if (file.compareTo(file1) == 0){
            System.out.println("文件路径一致");
        } else {
            System.out.println("文件路径不一致");
        }
    }

    //使用 File 类的 file.createTempFile() 方法在指定目录中创建文件
    private static void createfile(){

        try {
            File dir = new File("src");
            File file = File.createTempFile("javatemp", ".javatemp", dir);
            System.out.println(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //设置文件只读/可写
    private static void fileReadOnly(){
        File file = new File("newfile.txt");
        if (file.exists()){
            System.out.println(file.setReadOnly());
            System.out.println(file.canRead());
            System.out.println(file.canWrite());
        }else {
            System.out.println("文件不存在");
        }
    }

    //文件重命名
    private static void fileRename(){
        File oldName = new File("destfile.txt");
        File newName = new File("newfile.txt");
        if (oldName.renameTo(newName)){
            System.out.println("文件已重命名");
        }else {
            System.out.println("Error");
        }
    }
    //获取文件大小
    public static long getFileSize(String filename){
        File file = new File(filename);
        if (!file.exists() || !file.isFile()){
            System.out.println("文件不存在");
            return  -1;
        }
        return file.length();
    }
    
    //修改文件最后时间
    private static void changeFileDate(){
        try {
            File fileToChange = new File("filename.txt");
            //创建一个新的文件 如果存在不创建
            fileToChange.createNewFile();
            Date fileTime = new Date(fileToChange.lastModified());
            System.out.println(fileTime.toString());
            System.out.println(fileToChange.setLastModified(System.currentTimeMillis()));
            fileTime = new Date(fileToChange.lastModified());
            System.out.println(fileTime.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //创建临时文件
    private static void creatTempFile(){
        try {
            File temp = File.createTempFile("pattern", ".suffix");
            temp.deleteOnExit();
            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write("aString");
            System.out.println("临时文件已创建");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //文件追加
    private static void fileAdd(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("filename.txt"));
            out.write("aString1\n");
            out.close();

            out = new BufferedWriter(new FileWriter("filename.txt", true));
            out.write("aString2\n");
            out.close();

            BufferedReader in = new BufferedReader(new FileReader("filename.txt"));
            String str;
            while ((str = in.readLine()) != null){
                System.out.println(str);
            }


            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //文件复制
    //使用 BufferedWriter 类的 read 和 write 方法将文件内容复制到另一个文件
    private static void fileCopy(){
        try {
            BufferedWriter out1 = new BufferedWriter(new FileWriter("filename.txt"));
            out1.write("String to be copied\n");
            out1.close();
            InputStream in = new FileInputStream(new File("filename.txt"));

            OutputStream out = new FileOutputStream(new File("destfile.txt"));
            byte[] buf = new byte[1024];
            int len;
            //将文件写入
            while ((len = in.read(buf)) > 0){
                System.out.println(len);
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            BufferedReader in1 = new BufferedReader(new FileReader("destfile.txt"));
            String str;
            while ((str = in1.readLine()) != null){
                System.out.println(str);
            }
            in1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //删除文件
    private static void fileDelete(){
        File file = new File("putfilename.txt");
        if (file.delete()){
            System.out.println(file.getName() + "文件已被删除");
        } else  {
            System.out.println("文件删除失败");
        }
    }

    //文件读取
    private static void fileRead(){
        try {
            BufferedReader in = new BufferedReader(new FileReader("putfilename.txt"));
            String str;
            while ((str = in.readLine()) != null){
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //文件写入
    private static void fileWrite(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("putfilename.txt"));
            out.write("this is my study java");
            out.close();
            System.out.println("文件创建成功");
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
