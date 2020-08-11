import Vue from 'vue';
let cookie = Vue.prototype.$cookie;

export default class CookiesUtil {
    
    public static getUser(){
        return JSON.parse(cookie.get('user'));
    }
    
    public static getUserId(){
        return this.getUser().id;
    }
    
}
