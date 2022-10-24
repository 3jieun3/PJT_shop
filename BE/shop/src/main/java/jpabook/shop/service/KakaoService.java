package jpabook.shop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jpabook.shop.domain.entity.KakaoProfile;
import jpabook.shop.domain.entity.OAuthToken;
import jpabook.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoService {

    String REST_API_KEY = "34b280aa08664b5eb86df4fd8cd9759c";
    String REDIRECT_URI = "http://localhost:8080/api/auth/kakao/callback";

    // JSON 객체 <-> java 객체 (de)serialization 하는 라이브러리
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;

    // 카카오 인가 코드 -> 엑세스 토큰
    public String getKakaoTokenByCode(String code) {
        // http header 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // http body 객체 생성
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", REST_API_KEY);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);

        // http header 와 body 를 하나의 객체에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // http 요청 편하게 할 수 있는 라이브러리
        RestTemplate rt = new RestTemplate();
        // 카카오 서버로 http 요청 for access token
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class // 응답 받을 타입
        );

        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 엑세스 토큰 리턴
        return oauthToken.getAccess_token();
    }

    // 카카오 엑세스 토큰 -> 사용자 정보
    public KakaoProfile getKakaoProfileByToken(String accessToken) {
        // http header 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer " + accessToken);

        // http header 와 body 를 하나의 객체에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        // http 요청 편하게 할 수 있는 라이브러리
        RestTemplate rt = new RestTemplate();
        // 카카오 서버로 http 요청 for 사용자 정보
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class // 응답 받을 타입
        );
        KakaoProfile kakaoProfile  = null;
        try {
            kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 사용자 정보
        return kakaoProfile;
    }
}
