package com.anjiplus.mybatis.practice;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/21 14:09
 * @Description:
 */
public class RegexMatches {
    public static void regexTest(){
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (m.find()){
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
        }else {
            System.out.println("NO MATCH");
        }

        String regex = "\\bcat\\b";
        String input = "cat cat cat cattie cat";
        Pattern p = Pattern.compile(regex);
        Matcher m1 = p.matcher(input);
        int count = 0;
        
        while (m1.find()){
            count++;
            System.out.println("------");
            System.out.println("Match number " + count);
            System.out.println("start(): " + m1.start());
            System.out.println("emd(): " + m1.end());
        }
        
        
    }
    public static void regexTest1() {
        String rex = "foo";
        String input = "foooooooooooooooooo";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rex);
        matcher = pattern.matcher(input);
        
        System.out.println("current rex is: " + rex);
        System.out.println("current input is: " +input);
        System.out.println("lookingat(): " + matcher.lookingAt());
        System.out.println("matches(): " + matcher.matches());
    }

    public static void regexReplace() {
        String rex = "dog";
        String input = "The dog says meow. " +
                "All dogs say meow.";
        String rep = "cat";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(input);
        //替换
        input = matcher.replaceAll(rep);
        System.out.println(input);
    }
    public static void regexAppendRep(){
        String rex = "a*b";
        String input = "aabfooaabfooabfoob";
        String rep = "-";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        //appendReplacement 和appendTail 方法用于文本替换
        while (matcher.find()){

            matcher.appendReplacement(sb, rep);
            System.out.println(sb.toString());

        }

        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

    public  static  void  printMax(double... numbers){
        if (numbers.length == 0){
            System.out.println("No argument passed");
            return;
        }

        double result = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > result){
                result = numbers[i];
            }
        }
        System.out.println("The max value is " + result);
    }





    public static void main(String[] args) throws Exception {
//        RegexMatches.regexAppendRep();
//        for(int i=0; i<args.length; i++){
//            System.out.println("args[" + i + "]: " +
//                    args[i]);
//        }

//        printMax(34, 33, 22, 32, 23);
//        printMax(new double[]{1, 2, 3, 4, 5});

//        Cake c1 = new Cake(1);
//        Cake c2 = new Cake(2);
//        Cake c3 = new Cake(3);
//        c2 = c3 = null;
//        System.gc();


//        brRead();

        checkException();

    }
    
    public static void checkException(){
        CheckingAccount c = new CheckingAccount(101);
        System.out.println("Depositing $500...");
        c.deposit(500.0);
        try {
            System.out.println("\nWithdrawing $100...");
            c.withdraw(100.0);
            System.out.println("\nWithdrawing $600...");
            c.withdraw(600.0);

        }catch (InsufficientFundsException e){
            System.out.println("Sorry, but you are short $" + e.getAmount());
            e.printStackTrace();
        }
    }

    public static void exceotTest(){
        try{
            int a[] = new int[2];
            System.out.println("Access element three :" + a[3]);
            System.out.println(9/0);
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException thrown  :" + e);
        } catch (ArithmeticException e){
            System.out.println("ArithmeticException thrown  :" + e);
        } catch (Exception e){
            System.out.println("Exception thrown  :" + e);
        } finally {
            System.out.println("finally");
        }
        System.out.println("Out of the block");
    }

    public static void  scannerTest(){
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据
        //next方式接收字符串
        //对输入有效字符之前遇到的空白，next()方法会自动将其去掉
//        System.out.println("next方式接收： ");
//        if (scan.hasNext()){
//            String str = scan.next();
//            System.out.println("输入的数据为： " + str);
//        }

        //nextLine方式接收字符串
        System.out.println("nextLine方式接收： ");
        if (scan.hasNextLine()){
            String str = scan.nextLine();
            System.out.println("输入的数据为： " + str);
        }
        int i = 0;
        float f = 0.0f;
        System.out.println("输入整数：");
        if (scan.hasNextInt()){
            i = scan.nextInt();
            System.out.println("整数数据是：" + i);
        }else  {
            System.out.println("输入不是整数！");
        }
        System.out.println("输入小数：");
        if (scan.hasNextFloat()){
            f = scan.nextFloat();
            System.out.println("小数数据： " + f);
        }else {
            System.out.println("输入的不是小数");
        }

        System.out.println("输入小数：");

        double sum = 0;
        int m = 0;
        while (scan.hasNextDouble()){
            double x = scan.nextDouble();
            m += 1;
            sum += x;
        }
        System.out.println(m+"个数的和为"+sum);
        System.out.println(m+"个数的平均值是"+(sum/m));
    }

    public static void fileReddir() throws IOException{
        String dirName = "/Users/kean_qi/Desktop/accet";
        File f1 = new File(dirName);
        if (f1.isDirectory()) {
            System.out.println("Directory of " + dirName);
            String s[] = f1.list();
            for (int i = 0; i < s.length; i++) {
                File f = new File(dirName + "/" + s[i]);
                if (f.isDirectory()){
                    System.out.println(s[i] + " is a director");
                }else  {
                    System.out.println(s[i] + " is a file");
                }
            }
        } else {
            System.out.println(dirName + " is not a directory");
        }
    }

    public static void fileMakedir() throws IOException{
        String dirname = "/Users/kean_qi/Desktop/accet";
        File d = new File(dirname);
        d.mkdir();
    }

    public static void fileOutput() throws IOException {
        //读取文件
//        System.out.println(fs.read());


        OutputStream os = new FileOutputStream("test.txt");
        byte bWrite[] = {11, 21, 3, 43, 5};
        for (int x = 0; x < bWrite.length; x++) {
            os.write(bWrite[x]);
        }
        os.close();
        InputStream fs = new FileInputStream("test.txt");
        int size = fs.available();
        for (int i = 0; i < size; i++) {
            System.out.print((char)fs.read() + ' ');
        }
        fs.close();

    }
    public static void fileinput() throws IOException {
        File f = new File("a.txt");
        FileOutputStream fop = new FileOutputStream(f);
        // 构建FileOutputStream对象,文件不存在会自动新建

        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk

        Map map = new HashMap();
        map.put("cy", "cyycac");
        map.put("kk", "ckkyack");


        writer.append(map.toString());
        // 写入到缓冲区

        writer.append("\r\n");
        //换行

        writer.append("English");
        // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入

        writer.close();
        //关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉

        fop.close();
        // 关闭输出流,释放系统资源

        FileInputStream fip = new FileInputStream(f);
        // 构建FileInputStream对象

        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        // 构建InputStreamReader对象,编码与写入相同

        StringBuffer sb = new StringBuffer();

        int count = 0;
        while (reader.ready()) {
            count++;

            sb.append((char) reader.read());
            // 转成char加到StringBuffer对象中
        }
        System.out.println(sb.toString());
        System.out.println(count);
        reader.close();
        // 关闭读取流

        fip.close();
        // 关闭输入流,释放系统资源
    }


        public static void brRead() throws IOException {
        int b;
        b = 'a';
        System.out.write(b);
        System.out.write('\n');
    }

    public static void brRead2() throws IOException {
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter lines of text.");
        System.out.println("enter 'end' to quit");
        //当输入'end'结束
        do {
            str = br.readLine();
            System.out.println(str);
        }while(!str.equals("end"));
    }

    public static void brRead1() throws IOException {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter characters, 'q' to quit\n");
        do {
            c = (char) br.read();
            System.out.println(c);
        }while(c != 'q');
    }
}

class Cake extends  Object {
    private  int id;
    public  Cake(int id){
        this.id = id;
        System.out.println("Cake Object " + id + "is created");

    }

    // inalize( )，它用来清除回收对象。
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Cake Object " + id + "is disposed");
    }
}




class InsufficientFundsException extends Exception
{
    private double amount;
    public InsufficientFundsException(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }
}


class CheckingAccount {
    private double balance;
    private int number;

    public CheckingAccount(int number) {
        this.number = number;
    }
    public void  deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        } else {
            double needs = amount - balance;
            throw new InsufficientFundsException(needs);
        }
    }


    public double getBalance() {
        return balance;
    }

    public int getNumber() {
        return number;
    }


}
