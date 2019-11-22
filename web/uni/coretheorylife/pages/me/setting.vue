<template>
	<view class="main-page">
		<my-cell title="清除缓存"></my-cell>
		<my-cell title="版本号" :showArrow="false">
			<text>1.0.3</text>
		</my-cell>

		<view class="dlbutton normal-bgColor" hover-class="dlbutton-hover" @tap="bindLogot">
			<text>退出登录</text>
		</view>
	</view>
</template>

<script>
	import myCell from "@/components/my-cell.vue";

	export default {
		components: {
			myCell,
		},
		methods:{
			bindLogot(){
				this.$util.logOut();
			}
		}
	}
</script>

<style>
</style>
