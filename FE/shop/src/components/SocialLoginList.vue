<template>
	<div>
		<p>소셜 로그인</p>
		<img class="button" :src="require('@/assets/kakao_login.png')" @click="kakaoLogin()" style="height: 2.5rem;">
	</div>
</template>

<script>
import axios from 'axios'
import shop from '@/api/shop'

export default {
	name: 'SocialLoginList',
	methods: {
		
		kakaoLogin() {
			axios({
				url: shop.accounts.kakaoLogin(),
				method: 'get',
			})
			.then((res) => {
				// window.open( url주소, 이름, 창 형태 )
				window.open(res.data, "KAKAO LOGIN", "popup");
			})
		},
	},
}
</script>

<style>

</style>