import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import iView from 'iview';
// @ts-ignore
import i18n from '@/locale';
import config from './config/index';
import 'iview/dist/styles/iview.css';
import './index.less';
import '@/assets/icons/iconfont.css';
import axios from './config/axios';
import address from './config/global/address';
import ajaxConfig from './config/global/ajax';
import cookie from 'js-cookie';

Vue.use(iView, {
    i18n: (key, value) => i18n.t(key, value)
});


Vue.use(address);
Vue.use(ajaxConfig);

Vue.config.productionTip = false;
Vue.prototype.$config = config;
Vue.prototype.$ajax = axios;
Vue.prototype.$cookie = cookie;

window.document.title = config.title;

new Vue({
  el: '#app',
  router,
  i18n,
  store,
  render: h => h(App)
});
