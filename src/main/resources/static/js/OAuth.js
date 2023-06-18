const CLIENT_ID = "2fc0d9cd08765d70a34b95ee921e8e9a";
const REDIRECT_URI = "https://localhost:8080/oauth/callback/kakao";

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
