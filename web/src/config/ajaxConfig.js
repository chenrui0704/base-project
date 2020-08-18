export default function (Vue) {
    // json格式
    Vue.prototype.$ajaxJsonConfig = {
        headers: {'Content-Type': 'application/json;charset=UTF-8'}
    };
    // form-data多文件上传请求头
    Vue.prototype.$ajaxFormDataConfig = {
        headers: {'Content-Type': 'multipart/form-data;charset=UTF-8'}
    };

}
