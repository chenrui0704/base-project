import RegEx from "../../../util/RegEx";

export default class UserEntityRules {
    
    username: any = [
        {required: true, max: 20, message: '请输入不超过20位的用户名', trigger: 'blur'},
    ];
    
    loginName: any = [
        {required: true, max: 20, message: '请输入不超过20位的登录名', trigger: 'blur'},
    ];
    
    password: any = [
        {required: true, max: 20, message: '请输入不超过20位的密码', trigger: 'blur'},
    ];
    
    roleId: any = [
        {required: true, type: 'number', message: '请选择角色', trigger: 'blur'},
    ];
    
    email: any = [
        {required: true,type: 'email', message: '请输入正确的邮箱', trigger: 'blur'},
    ];
    
    phone: any = [
        {required: true, message: '请输入正确的手机号', trigger: 'blur'},
        {validator: this.phoneValidator, trigger: 'blur'}
    ];
    
    identityNumber: any = [
        {required: true, message: '请输入正确的身份证号', trigger: 'blur'},
        {validator: this.identityNumberValidator, trigger: 'blur'}
    ];
    
    
    phoneValidator(rule, value, callback){
        if(RegEx.isRegExp(RegEx.phone, value)){
            callback();
        }else{
            callback(new Error('请输入正确的手机号'));
        }
    }
    
    identityNumberValidator(rule, value, callback){
        if(RegEx.isRegExp(RegEx.identityNumber, value)){
            callback();
        }else{
            callback(new Error('请输入正确的身份证号'));
        }
    }
    
}
