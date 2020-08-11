import axios from 'axios';
import cookies from 'js-cookie';


axios.interceptors.request.use(function (config) {
    let token = cookies.get('token');
    if (token) {
        config.headers.token = token;
    }
    return config;
}, function (error) {
    return Promise.reject(error);
});


axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {

    return Promise.reject(error);
});

export default axios;
