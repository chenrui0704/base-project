let env = process.env.NODE_ENV;

let javaAddress;
if (env === 'development') {
  javaAddress = 'http://127.0.0.1:9999/base/frame/main/';

}else if (env === 'production') {
  javaAddress = 'http://9.23.69.55:8020/api/cr/';
}else if (env === 'testenv') {
    javaAddress = 'http://9.23.69.55:8020/api/cr/';
}

export default {
  install (Vue) {

    Vue.prototype.$env = env;
    Vue.prototype.$javaAddress = javaAddress;

  }
}

