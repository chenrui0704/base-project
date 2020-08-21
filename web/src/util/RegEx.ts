export default class RegEx {
    
    /**
     * 身份证号表达式
     * */
    static identityNumber: string = '(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)';
    
    static phone: string = '((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))';
    
    /**
     * 表达式验证
     * */
    static isRegExp (regString, val): boolean {
        return new RegExp('^' + regString + '$').test(val);
    };
    
}
