/*
 * 字符串扩展去除首尾空格
 */
String.prototype.trim = function trim(){
	var start,end;
	start = 0;
	end = this.length -1;
	while (start<=end && this.charAt(start)==' '){
		start++;
	}
	while (start<=end && this.charAt(end)==" "){
		end --;
	}
	return this.substring(start,end+1);
}

/*
 * 字符串转数组
 */
String.prototype.toCharArray = function(){
	var chs = [];
	for (var x=0; x<this.length; x++) {
		chs[x] = this.charAt(x);
	}
	return chs;
}
/*
 * 字符串反转
 */
String.prototype.reverse = function(){
	var arr = this.toCharArray();
	for (var x=0,y=arr.length-1;x<y;x++,y-- ) {
		swap(arr,x,y);
	}
	return arr.join("");
}

function swap(arr,x,y){
	var temp = arr[x];
	arr[x] = arr[y];
	arr[y] = temp;
}
