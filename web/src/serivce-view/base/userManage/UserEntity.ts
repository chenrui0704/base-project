import PageModel from "../../../model/PageModel";
import CookiesUtil from "../../../util/CookiesUtil";

export default class UserEntity extends PageModel{
    
    id: number = 0;
    username: string = '';
    loginName: string = '';
    password: string = '';
    phone: string = '';
    email: string = '';
    identityNumber: string = '';
    
    createId: number = 0;
    updateId: number = 0;
    roleId: number = 0;
    state: number = 0;
    
    
    // 初始化
    initialization(){
        this.id = 0;
        this.username = '';
        this.loginName = '';
        this.password = '';
        this.phone = '';
        this.email = '';
        this.identityNumber = '';
        this.roleId = 0;
        this.createId = CookiesUtil.getUserId();
    }
    
    copy(obj: UserEntity){
        for(let key in obj)
            this[key] = obj[key];
        this.updateId = CookiesUtil.getUserId();
    }
    
}
