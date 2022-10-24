<template>
	<div>
		<h3>LOG IN</h3>
		<b-row class="my-4">
			<label for="userid" class="text-start">아이디</label>
			<b-form-input id="userid"></b-form-input>
		</b-row>

		<b-row class="my-4">
			<label for="password" class="text-start">비밀번호</label>
			<b-form-input id="password"></b-form-input>
		</b-row>

    <b-button type="submit" pill variant="outline-dark">Submit</b-button>
	</div>
</template>

<script>
export default {
	name: 'LoginForm',

}
</script>

<style>
</style>