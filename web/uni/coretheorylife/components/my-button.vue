<template>
	<view class="dlbutton normal-bgColor" hover-class="dlbutton-hover" @click="click">
		<text>{{title}}</text>
	</view>
</template>

<script>
	export default {
		props: {
			title: {
				type: String,
				default: ''
			},
		},
		data(){
			let n = new Date().getTime();
			return {
				nowTime: n,
			}
		},
		methods: {
			// 详情
			click() {
				let now = new Date().getTime();
				
				// console.log(now);
				// console.log(this.nowTime);
				// console.log(now - this.nowTime);
				if(now - this.nowTime > 1000){
					this.nowTime = now;
					// console.log("点击成功");
					this.$emit('click');
				}
			},
		}
	}
</script>

<style>
</style>
