export default class TypeCodeRules {
    
    name: any = [
        {required: true, max: 20, message: '请输入不超过20位的类型名称', trigger: 'blur'},
        {validator: this.validateName, trigger: 'change'}
    ];
    
    code: any = [
        {required: true, max: 20, message: '请输入不超过20位的类型代码', trigger: 'blur'},
    ];
    
    description: any = [
        {required: true, max: 20, message: '请输入不超过20位的类型描述', trigger: 'blur'},
    ];
    
    
    validateName(rule, value, callback){
        callback();
    }
    
}
