export default class TypeCodeColumns {
    
    static list: any = [
        {
            title: "类型名称",
            key: "name",
            editable: true
        },
        {
            title: "类型代码",
            key: "code",
            editable: true
        },
        {
            title: "描述",
            key: "description",
            editable: true
        },
        {
            title: "操作",
            align: "center",
            key: 'handle',
            width:180,
            handle: ['edit', 'delete']
        }
    ];
    
}
