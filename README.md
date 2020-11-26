# JSON Web Token
기본구조
HEADER.PAYLOAD.SIGNATURE
# Header
typ : 타입
alg : 해싱 알고리즘
{
  "typ": "JWT",
  "alg": "HS256"
}

# Payload
JWT 속성 정보 (Claim)
iss: 토큰 발급자 (issuer)
sub: 토큰 제목 (subject)
aud: 토큰 대상자 (audience)
exp: 토큰의 만료시간 (expiraton), 시간은 NumericDate 형식으로 되어있어야 하며 (예: 1480849147370) 언제나 현재 시간보다 이후로 설정
nbf: Not Before 를 의미하며, 토큰의 활성 날짜와 비슷한 개념. 여기에도 NumericDate 형식으로 날짜를 지정하며, 이 날짜가 지나기 전까지는 토큰이 처리되지 않음
iat: 토큰이 발급된 시간 (issued at), 이 값을 사용하여 토큰의 age 가 얼마나 되었는지 판단
jti: JWT의 고유 식별자로서, 주로 중복적인 처리를 방지하기 위하여 사용. 일회용 토큰에 사용하면 유용

# Signature
secret key를 포함하여 암호화

# 장점
URL  파라미터와 헤더로 사용
수평 스케일이 용이
디버깅 및 관리가 용이
트래픽 대한 부담이 낮음
REST 서비스로 제공 가능
내장된 만료
독립적인 JWT

# 단점
DB상정보가 바뀌더라도 token에 적용 불가능
많은 필드를 사용하게 되면 토큰이 무거워 질수 있음
