package jpabook.shop.api;

import jpabook.shop.domain.entity.KakaoProfile;
import jpabook.shop.domain.entity.Member;
import jpabook.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import jpabook.shop.service.KakaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@CrossOrigin(origins = "*", allowedHeaders = "X-AUTH-TOKEN", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

    String REST_API_KEY = "34b280aa08664b5eb86df4fd8cd9759c";
    String REDIRECT_URI = "http://localhost:8080/api/auth/kakao/callback";

    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/login/kakao")
    public String kakaoLoginURL() {
        String kakaoURL = String.format("https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code", REST_API_KEY, REDIRECT_URI);
        return kakaoURL;
    }

    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code) {
        // 카카오 인가 코드 -> 엑세스 토큰
        String kakaoAccessToken = kakaoService.getKakaoTokenByCode(code);
        System.out.println("카카오 엑세스 토큰 : " + kakaoAccessToken);

        // 카카오 엑세스 토큰 -> 사용자 정보
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfileByToken(kakaoAccessToken);
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        // 카카오 사용자 정보 -> member 객체
        Member member = Member.builder()
                .nickname(kakaoProfile.getKakao_account().getProfile().getNickname())
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();

        // 가입되어있지 않은 경우 회원가입 진행
        Long savedId = memberService.joinMember(member);
        System.out.println(savedId);

        return kakaoProfile.getKakao_account().toString();
    }
}
