const HOST = "http://localhost:8080/api/"

const AUTH = 'auth/'
// const MEMBER = 'member/'

export default {
	accounts: {
		kakaoLogin: () => HOST + AUTH + 'login/kakao/',
	}
}
