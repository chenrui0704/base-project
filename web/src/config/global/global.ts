// 引用Vue 点击时可以跳转到定义的位置
import Vue from 'vue';

// 声明全局变量，如果不进行声明的话。也是可以用的，但是ts会识别不出来。
declare module 'Vue/types/vue' {
    interface Vue {
        $ajax: any;
        $javaAddress: any;
        $ajaxJsonConfig: any;
        $cookie: any;
        $menuList: any;
    }
}
export default {}
