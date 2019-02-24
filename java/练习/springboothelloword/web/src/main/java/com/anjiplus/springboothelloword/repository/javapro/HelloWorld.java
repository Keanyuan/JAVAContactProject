package com.anjiplus.springboothelloword.repository.javapro;

import java.io.IOException;

//import java.io.File;
//import java.util.Scanner;

class FreshJuice{
	//枚举方法
	/*
	 * 饮料定义 小杯、中杯、大杯
	 * 以size返回
	 * */
	enum FreshJuiceSize{SMALL, MEDUIM, LARGE}
	FreshJuiceSize size;
}

public class HelloWorld {
	
	public static void main(String[] args) throws IOException{
//		System.out.println("this is my first project");
//		FreshJuice juice = new FreshJuice();
//		juice.size = FreshJuice.FreshJuiceSize.MEDUIM;
//		System.out.println(juice.size);
//		
//		Puppy myPuppy = new Puppy("tommy");
//		myPuppy.setAge(2);
//		myPuppy.getAge();
//		System.out.println("变量值 :" + myPuppy.puppyAge);
//		
//		//使用构造器创建两个对象
//		Employee empOne = new Employee("RUNOOB1");
//		Employee empTwo = new Employee("RUNOOB2");
//		empOne.empAge(26);
//		empOne.empDesignation("高级程序员");
//		empOne.empSalary(1000);
//		empOne.printEmployee();
//		
//		empTwo.empAge(22);
//		empTwo.empDesignation(Employee.DEPARTMENT);
//		empTwo.empSalary(500);
//		empTwo.printEmployee();
		
//		
//		EmployeeTest empTest = new EmployeeTest();
//		empTest.printEmeployee();
				
//		 // 初始化 Date 对象
//        Date date = new Date();
//         
//        // 使用 toString() 函数显示日期时间
//        System.out.println(date.toString());
//        
//        Date dNow = new Date( );
//        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//   
//        System.out.println("Current Date: " + ft.format(dNow));
//        //c的使用  
//        System.out.printf("全部日期和时间信息：%tc%n",date);          
//        //f的使用  
//        System.out.printf("年-月-日格式：%tF%n",date);  
//        //d的使用  
//        System.out.printf("月/日/年格式：%tD%n",date);  
//        //r的使用  
//        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);  
//        //t的使用  
//        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);  
//        //R的使用  
//        System.out.printf("HH:MM格式（24时制）：%tR",date); 
//        
////        Logger logger = new Logger();
////        logger.printGrade(90.0);
//        Logger.printGrade(90.0);
//        
//        MyClass myClass1 = new MyClass(1);
//        MyClass myClass2 = new MyClass(2);
//        System.out.println("myclass1 : " + myClass1.x+ " myClass2: " + myClass2.x);
//       
//        MyClass.printMax(1,2,3,4,5);
//        MyClass.printMax(new double[]{10,20,30,40});

        
//        char c;
//         使用 System.in 创建 BufferedReader 
//        BufferedReader br = new BufferedReader(new 
//                           InputStreamReader(System.in));
//        System.out.println("输入字符, 按下 'q' 键退出。");
//        // 读取字符
//        do {
//           c = (char) br.read();
//           System.out.println(c);
//        } while(c != 'q');
//      
//        String str;
//        System.out.println("Enter lines of text.");
//        System.out.println("Enter 'end' to quit.");
//        do {
//           str = br.readLine();
//           System.out.println(str);
//        } while(!str.equals("end"));

//        int b; 
//        b = 'A';
//        System.out.write(b);
//        System.out.write('\n');
//
//        String dirname = "/tmp";
//        File f1 = new File(dirname);
//        if (f1.isDirectory()) {
//          System.out.println( "目录 " + dirname);
//          String s[] = f1.list();
//          for (int i=0; i < s.length; i++) {
//            File f = new File(dirname + "/" + s[i]);
//            if (f.isDirectory()) {
//              System.out.println(s[i] + " 是一个目录");
//            } else {
//              System.out.println(s[i] + " 是一个文件");
//            }
//          }
//        } else {
//          System.out.println(dirname + " 不是一个目录");
//        }
//        Scanner scan = new Scanner(System.in); 
//        // 从键盘接收数据  
//     
//        //next方式接收字符串
//            System.out.println("next方式接收：");
//            // 判断是否还有输入
//            if(scan.hasNextLine()){   
//              String str1 = scan.nextLine();
//              System.out.println("输入的数据为："+str1);  
//            }  
        
//        System.out.println();
//        SubClass sc = new SubClass();
//        SubClass sc2 = new SubClass(200);
//        SupperClass scp = new SupperClass();
//        SupperClass sccp = new SubClass();
//        scp.move();// 执行 SupperClass 类的方法
//        sccp.move();// 执行 SubClass 类的方法
//        ((SubClass) sccp).bark();
        //直接调用sccp.bark();会出错，因为b的引用类型Animal没有bark方法。
        
//        String str = "我的字符串";
//        int int1 =  10;
//        String strRes = "操作结果： ";
//        strRes = strRes + str + int1;
//        System.out.println(strRes);
//        
//        
//        int int2 = 12;
//        //^ 异或 两个条件 一个为真 一个为假才结果真
//        System.out.println((int1>int2) ^ (int1<int2));
		
		
		
        		
	}
}



