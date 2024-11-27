
import Cookies from 'js-cookie';

const TOKEN_KEY = 'authToken';

export function setToken(token) {
  Cookies.set(TOKEN_KEY, token, { expires: 0.5 }); // 0.5 小时即 30 分钟
}

export function getToken() {
  return Cookies.get(TOKEN_KEY);
}

export function removeToken() {
  Cookies.remove(TOKEN_KEY);
}