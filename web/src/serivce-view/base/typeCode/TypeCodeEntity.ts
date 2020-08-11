export default class TypeCodeEntity {
    id: number = 0;
    name: string = '';
    code: string = '';
    description: string = '';
    node: string = '';
    createId: number = 0;
    updateId: number = 0;
    state: number = 1;
    oldNode: string = '';
    
    // 初始化
    initialization(node: string){
        this.id = 0;
        this.name = '';
        this.code = '';
        this.description = '';
        this.node = node;
    }
    
}
