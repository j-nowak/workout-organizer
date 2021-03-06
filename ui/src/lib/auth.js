import axios from 'axios';

const COOKIE_NAME = 'session_token';

// export function setCookie(value, expiresIn=(1000 * 60 * 60 * 24 * 365)) {
//   const expires = new Date(Date.now() + expiresIn);
//   document.cookie =`${COOKIE_NAME}=${value}; expires=${expires.toUTCString()};`;
// }

export function deleteSession() {
  axios.post('http://localhost:9000/logout');
  document.cookie =`${COOKIE_NAME}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

function getCookie() {
  const cookies = document.cookie.split('; ')
    .map(x => x.split('='))
    .filter(x => x[0] === COOKIE_NAME)

  if (cookies.length > 0) {
    return cookies[0][1];
  }
  return null;
}

export function getCurrentUser() {
  const token = getCookie();

  if (token) {
    return axios.get('http://localhost:9000/react/users/current', { withCredentials: true })
      .then(response => response.data)
  }
  return Promise.reject();
}
