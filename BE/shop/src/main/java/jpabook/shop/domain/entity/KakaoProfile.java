package jpabook.shop.domain.entity;

import lombok.Data;

@Data
public class KakaoProfile {
    public String connected_at;
    public KaKaoAccount kakao_account;

    @Data
    public class KaKaoAccount {
        public Boolean has_email;
        public String email;
        public Profile profile;

        @Data
        public class Profile {
            public String nickname;
        }
    }
}
