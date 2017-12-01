
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