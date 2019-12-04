<template>
	<view class="loader-16">
	</view>
</template>

<script>
</script>

<style>
	.loader-16 {
		-webkit-transform: rotateZ(45deg);
		transform: rotateZ(45deg);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		border-radius: 50%;
	}

	.loader-16:before,
	.loader-16:after {
		content: '';
		display: block;
		position: absolute;
		top: 0;
		left: 0;
		width: inherit;
		height: inherit;
		border-radius: 50%;
		-webkit-animation: 1s spin linear infinite;
		animation: 1s spin linear infinite;
	}

	.loader-16:before {
		-webkit-transform: rotateX(70deg);
		transform: rotateX(70deg);
	}

	.loader-16:after {
		-webkit-transform: rotateY(70deg);
		transform: rotateY(70deg);
		-webkit-animation-delay: .4s;
		animation-delay: .4s;
	}

	@-webkit-keyframes rotate {
		0% {
			-webkit-transform: translate(-50%, -50%) rotateZ(0deg);
			transform: translate(-50%, -50%) rotateZ(0deg);
		}

		100% {
			-webkit-transform: translate(-50%, -50%) rotateZ(360deg);
			transform: translate(-50%, -50%) rotateZ(360deg);
		}
	}

	@keyframes rotate {
		0% {
			-webkit-transform: translate(-50%, -50%) rotateZ(0deg);
			transform: translate(-50%, -50%) rotateZ(0deg);
		}

		100% {
			-webkit-transform: translate(-50%, -50%) rotateZ(360deg);
			transform: translate(-50%, -50%) rotateZ(360deg);
		}
	}

	@-webkit-keyframes rotateccw {
		0% {
			-webkit-transform: translate(-50%, -50%) rotate(0deg);
			transform: translate(-50%, -50%) rotate(0deg);
		}

		100% {
			-webkit-transform: translate(-50%, -50%) rotate(-360deg);
			transform: translate(-50%, -50%) rotate(-360deg);
		}
	}

	@keyframes rotateccw {
		0% {
			-webkit-transform: translate(-50%, -50%) rotate(0deg);
			transform: translate(-50%, -50%) rotate(0deg);
		}

		100% {
			-webkit-transform: translate(-50%, -50%) rotate(-360deg);
			transform: translate(-50%, -50%) rotate(-360deg);
		}
	}

	@-webkit-keyframes spin {

		0%,
		100% {
			box-shadow: .2em 0px 0 0px currentcolor;
		}

		12% {
			box-shadow: .2em .2em 0 0 currentcolor;
		}

		25% {
			box-shadow: 0 .2em 0 0px currentcolor;
		}

		37% {
			box-shadow: -.2em .2em 0 0 currentcolor;
		}

		50% {
			box-shadow: -.2em 0 0 0 currentcolor;
		}

		62% {
			box-shadow: -.2em -.2em 0 0 currentcolor;
		}

		75% {
			box-shadow: 0px -.2em 0 0 currentcolor;
		}

		87% {
			box-shadow: .2em -.2em 0 0 currentcolor;
		}
	}

	@keyframes spin {

		0%,
		100% {
			box-shadow: .2em 0px 0 0px currentcolor;
		}

		12% {
			box-shadow: .2em .2em 0 0 currentcolor;
		}

		25% {
			box-shadow: 0 .2em 0 0px currentcolor;
		}

		37% {
			box-shadow: -.2em .2em 0 0 currentcolor;
		}

		50% {
			box-shadow: -.2em 0 0 0 currentcolor;
		}

		62% {
			box-shadow: -.2em -.2em 0 0 currentcolor;
		}

		75% {
			box-shadow: 0px -.2em 0 0 currentcolor;
		}

		87% {
			box-shadow: .2em -.2em 0 0 currentcolor;
		}
	}
</style>
