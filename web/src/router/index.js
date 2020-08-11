import Vue from 'vue'
import Router from 'vue-router'
import routes from './routers'

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
};

Vue.use(Router);


const router = new Router({
    mode: 'history',
    routes: routes
});


router.beforeEach((to, from, next) => {
  next();
  //  next({ replace: true, name: 'error_401' }) // 无权限，重定向到401页面
});

export default router;
