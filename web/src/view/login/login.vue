<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip">输入任意用户名和密码即可</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from '_c/login-form';
import {Base64} from 'js-base64';


export default {
    components: {
        LoginForm
    },
    methods: {

        handleSubmit ({ userName, password }) {
            let that = this;
            that.$ajax.post(that.$javaAddress + '/user/login', {loginName: Base64.encode(userName), password: Base64.encode(password)}, that.$ajaxJsonConfig).then(re =>{
                let data = re.data;
                if(data.code == 1000){
                    that.$cookie.set('token', data.data.token);
                    that.$cookie.set('user', data.data.user);
                    that.$cookie.set('userAuthority', data.data.userAuthority);
                    console.log(data);

                    this.$router.push({
                        name: this.$config.homeName
                    });
                }else{
                    that.$Message.error(data.description);
                }
            });

        }

    }
}
</script>

<style>

</style>
