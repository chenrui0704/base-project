export default class UserColumns {
    
    static getList(obj){
        return [
            obj,
            {
                title: '用户名',
                key: 'username'
            },
            {
                title: '登录名',
                key: 'loginName'
            },
            {
                title: '角色',
                key: 'roleName'
            },
            {
                title: '手机号',
                key: 'phone'
            },
            {
                title: '身份证',
                key: 'identityNumber'
            },
            {
                title: '邮箱',
                key: 'email'
            },
        ];
    }
    
}
