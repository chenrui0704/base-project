export default class AuthorityManagementColumns {
    static roleList = [
        {
            title: "角色名称",
            key: "name",
        },
        {
            title: "角色代码",
            key: "code",
        },
    ];
    
    static menuList = [
        {
            type: 'selection',
            width: 80,
            align: 'center'
        },
        {
            title: "菜单名称",
            key: "name",
        },
        {
            title: "菜单代码",
            key: "code",
        },
    ];
}
