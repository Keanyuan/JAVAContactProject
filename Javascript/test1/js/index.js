
/*
 var a=6;
//a="abc";
var b=2.5;
//alert("a的密码"+(a+b));
// alert(a>10?1:2);
//alert(typeof(b));//类型
if(a>5){
	alert("100");
}else {
	alert("101");
}

switch (a) {
	case 1:
	alert("a");break;
	case 2:
	alert("b");break;
	case 6:
	alert("c");break;
	default:
	alert("d")
}
//循环语句
while (a<9){
	alert(a++);
	document.writeln(a+"</br>");
	
}
*/

for (var x = 1; x <= 9; x++) {
	for (var y = 1; y <= x; y++) {
		document.write(y+"*"+x+'='+y*x+"&nbsp;&nbsp;");
	}
		document.write("</br>");
}
//var arr=[1,2,3,4,5];
//for (var i = 0; i < arr.length; i++) {
//document.write(arr[i]);
//}

//var arr1 = new Array();

//---------------------------
// function demo() {
// 	alert("demo01");
// }
//// demo();
function add(x,y) {
	return x+y;
}
//var sum = add(4,5);
//alert(sum)

//------------------------
function show(x,y){
	alert(x+":"+y);
}
//------------------------
//var ss = show();
//alert(ss);
//------------------------
//动态函数
var adda = Function("x,y","var sum; sum=x+y; return sum;");
//alert(adda(1,2));
//alert(add(1,2));
//------------------------
//匿名函数
//var xd = function(a,b){
//	return a+b;
//}
//alert(xd(1,6));
//------------------------
/*
var arr = [66,13,37,21,89,17];
//取最值
function getMax(arr){
	var max = 0;
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] > arr[max]) {
			max = x;
		}
		return arr[max];
	}
}

var maxValue = getMax(arr);
println("maxValue:" + maxValue);

//排序
function sortArray(arr){
	for (var x = 0; x < arr.length - 1; x++) {
		for (var y = x+1; y < arr.length; y++) {
			if (arr[x]>arr[y]) {
				swap(arr,x,y);   
			}
		}
	}
}



println("排序前：" + arr);
//sortArray(arr);
println("排序后：" + arr);

//二分法查找
function searchElement (arr,key) {
	for (var x = 0; x < arr.length; x++) {
		if (arr[x] == key) {
			return x;
		}
	}
	return -1;
}
//折半查找
function binarySearch(arr,key){
	var max,min,mid;
	min = 0;
	max = arr.length -1;
	
	while (min <= max){
		mid = (max + min)>>1;
		if (key > arr[mid]) {
			min = mid + 1;
		} else if (key < arr[mid]) {
			max = mid -1;
		} else {
			return mid;
		}
	}
	return -1;
}
//对数组反转
function reverseArray(arr){
	for (var start = 0 ,end = arr.length - 1; start < end; start++,end--) {
		swap(arr,start,end);
	}
}
reverseArray(arr);
println("反转后：" + arr);
*/

//----------------------------
//for (var x = 0; x < 3; x++) {
//	println("x=" + x);
//}
//----------------------------
var arr = [66,13,37,21,89,17];
println(arr.valueOf()); 


var str = "  abcdefghijklmn      ";
println("str.len="+str.length);
println(str.bold());
println(str.fixed());
println(str.fontcolor("red"));
println(str.link("http://www.baidu.com"));
println(str.substr(2,4));//截取从下标第2位开始往后四位
println(str.substring(2,4));//截取下边2到4的字符串
println(str);
println("--"+str.trim()+"--");//去掉首尾空格
str = str.trim();
//字符串变成数组类型
println(str.toCharArray());
//字符串反转
println(str.reverse())

//---------------Array------------
var arr1=["avd","sdf","asf","23","acd"];
var newArr1 = arr1.concat("shzu", arr);//在数组上连接一个元素和数组
println(arr1);
println(newArr1);
println(arr.join("-"));//元素之间以某一个字符隔开
//pop移除数组中的元素，并返回该元素 push将新元素添加到数组，并返回数组新长度
println(newArr1.pop()); //删除最后一位元素，并返回对应删除的元素
println(newArr1.push("nie"));
println(newArr1.shift());//删除第一位元素，并返回删除的元素
println(newArr1);
println(newArr1.slice(0,-1));
println(newArr1.slice(0,2));//截取第0到第二位
println(newArr1.sort());//升序排列
println(newArr1.splice(1,3,8080,98989,2334,"少废话"));//删除下标第1到第3，并从第1下边添加元素
println(newArr1);
println(newArr1.unshift("abd","cdds"));//将元素从第一位插入并返回对应个数
println(newArr1);
//println(newArr1.getMax());
//println(newArr1.toString());
println("<hr/>");
var date = new Date();
println(date);
println(date.toLocaleDateString());//只有日期
println(date.toDateString());//日期和星期
var year = date.getFullYear();//年
var month = date.getMonth();//月
var day = date.getDate();
var week = date.getDay();//星期几

println(year + "-" + month + "-" + day + "   " + getWeek(week));
function getWeek(num) {
	var weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']; 
	return weeks[num];
}

var time = date.getTime();
println(time);
var date1 = new Date(time);
println(date1);
var dateStr = "2017/12/23";
var timSt = Date.parse(dateStr);
var date2 = new Date(timSt);
println(date2.toLocaleDateString());
println("<hr/>");
with (date){
	var year = getFullYear();//年
	var month = getMonth();//月
	var day = getDate();
	var week = getDay();//星期几
	println(year + "-" + month + "-" + day + "   " + getWeek(week));
}
println("<hr/>");
var num1 = Math.random()* 10 + 1;//0--1随机数
var num2 = Math.round(12.34);//四舍五入
var num3 = Math.ceil(12.34);//大于等于指定数最小整数
var num4 = Math.floor(num1);//返回小于等于指定参数的最大证整数
var num5 = Math.pow(10,2);//10的2次幂
var num6 = parseInt(num1);//取整

println(num1 + "," + num2 + "," + num3 + "," + num4 +"," + num5 + "," + num6);
println("<hr/>");
var val = parseInt("12abc12");//12abc val = 12 、 12abc12 val = NaN
println(val);//通过isNaN判断结果是否有效
var val1 = parseInt("110", 2);//将指定的进制格式的字符串转成对应的进制（将110转换成2进制）
println(val1);
//将十进制转成其他进制
var val2 = new Number(6);
println(val2.toString(2));
var val3 = 40;//对象
println(val3.toString(2));
println(arr);
for (i in arr) {
	print(arr[i]);
}
println("<hr/>");
function Person(){ //相当于构造器
//	alert("this is a objc")；
	
}
var p = new Person();
//动态给p对象添加属性， 直接用p.属性名即可
p.name = "张三";
p.age = 21;
p.show = function (){
	println("show name:" + this.name + " age:" + this.age);
}
p.show();

var objc = new Object();
objc.name = "god father";
objc.age = 2017;
//alert(objc.name + "：" + objc.age);
println(objc.name + "：" + objc.age);


function Parsent(name,age){
	this.name = name;
	this.age = age;
	this.setName = function(name) {
		this.name = name;
	}
	this.setAge = function(age) {
		this.age = age;
	}
	
}
var p1 =new Parsent("lisi",2019);
p1.setName("小强");
println(p1.name);

var p2 = {
	"name":"小明",
	"age":21,
	"getName":function(){
		return this.name;
	},
	myname:"艾弗森",
	names: ["zhangsan1","zhangsan2","zhangsan3"],
	nums: [34,12,23],
	mus : [
	{
		"name":"wanglaowu",
		"age":23
	},
	{
		"name":"liuf",
		"age":32}
	]
}

println(p2["name"] + " - " + p2.getName() + ":" + p2.age +"---"+ p2.myname + "--"+ p2.names[2] + "---" + p2.mus[1].name);
for (x in p1) {
	println(x + ":" + p1[x]);
}

var oMap = {
	8 :"小强",
	3 : "旺财",
	7 : "小明"
	
}
println(oMap[8]);
//o+xxx  对象
//b+xxx boolean
//i+xxx int
//s+xxx string

println("<hr/>");
println("------DOM(document object model文档对象模型)----------");
//用来将标记型文档封装成对象，并将标记型文档中的所有内容（标签，文本，属性等）封装成对象，可以被调用
//DOM解析按照标签的层级关系体现出标签的所属，形成一个树状结构成为DOM树，而树中的标签以及文本甚至属性称为节点，这个节点也称为对象，标签通常也称为页面中的元素