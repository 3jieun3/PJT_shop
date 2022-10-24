<template>
	<div class="login-box">
		<login-form></login-form>
		<hr>
		<social-login-list></social-login-list>
	</div>
</template>

<script>
import LoginForm from '@/components/LoginForm'
import SocialLoginList from '@/components/SocialLoginList'
export default {
	name: 'LoginView',
	components: {
		LoginForm,
		SocialLoginList,
	}
}
</script>

<style>
.login-box {
	margin: 5rem 40rem;
	padding: 3rem;
	border: solid 2px slateblue;
	border-radius: 2rem;
}
</style>