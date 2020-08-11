<style>
    .marginLeft-10{
        margin-left: 10px;
    }
    .marginLeft-5{
        margin-left: 5px;
    }
</style>
<template>
    <Row>
        <Col span="10" style="padding-right: 1%;">
            <Row style="margin-bottom: 20px;">
                <Button @click="openModal('root')" type="info">新增类型</Button>
                <Input v-model="nameOrCode" class="marginLeft-5" search placeholder="根据名称或代码进行搜索" style="width: 200px;" @on-search="search('root', nameOrCode)"/>
            </Row>
            <canEditTable :height="550" :columns-list="columns" v-model="data"  v-on:on-row-click="onRowClick" @on-change="nodeEdit" @on-delete="nodeDelete"></canEditTable>
        </Col>
        <Col span="14">
            <Row style="margin-bottom: 20px;">
                <Button :disabled="selectNode.length <= 0" type="info" @click="openModal(selectNode)">新增类型</Button>
                <Input :disabled="selectNode.length <= 0" v-model="nodeNameOrCode" class="marginLeft-5" search placeholder="根据名称或代码进行搜索" style="width: 200px;" @on-search="search(selectNode, nodeNameOrCode)"/>
            </Row>
            <canEditTable :height="550" :columns-list="childNodeColumns" v-model="childNodeData" @on-change="nodeEdit" @on-delete="nodeDelete"></canEditTable>
        </Col>

        <Modal v-model="modal" title="新增类型" :mask-closable="false" :footer-hide="true" >
            <Form ref="saveTypeCodeEntity" :model="saveTypeCodeEntity" :label-width="90" :rules="saveTypeCodeEntityRules">
                <FormItem label="类型名称：" prop="name">
                    <Input v-model="saveTypeCodeEntity.name" placeholder="请输入类型名称"></Input>
                </FormItem>
                <FormItem label="类型代码：" prop="code">
                    <Input v-model="saveTypeCodeEntity.code" placeholder="请输入类型代码"></Input>
                </FormItem>
                <FormItem label="类型描述：" prop="description">
                    <Input v-model="saveTypeCodeEntity.description"  placeholder="请输入类型描述..."></Input>
                </FormItem>
                <FormItem label="类型节点：">
                    <Input :disabled="true" v-model="saveTypeCodeEntity.node"></Input>
                </FormItem>
            </Form>
            <Row>
                <Button type="success" style="width: 70px;" @click="save()">保存</Button>
                <Button type="default" style="width: 70px;margin-left: 5px;" @click="modal = false">取消</Button>
            </Row>
        </Modal>
    </Row>
</template>

<script lang="ts">
    import {Vue,Component} from 'vue-property-decorator';
    import canEditTable from '../../../components/table/canEditTable.vue';
    import TypeCodeEntity from './TypeCodeEntity';
    import CookiesUtil from "../../../util/CookiesUtil";
    import MyResult from "../../../util/MyResult";
    import TypeCodeRules from "./TypeCodeRules";
    import TypeCodeColumns from "./TypeCodeColumns";

    @Component({
        components: {
            canEditTable: canEditTable
        }
    })
    export default class TypeCode extends Vue{
        modal: boolean = false;
        saveTypeCodeEntity: TypeCodeEntity = new TypeCodeEntity();
        saveTypeCodeEntityRules: TypeCodeRules = new TypeCodeRules();

        nameOrCode: string = '';
        nodeNameOrCode: string = '';
        selectNode: string = '';
        data: any = [];
        columns: any = TypeCodeColumns.list;
        childNodeData: any = [];
        childNodeColumns: any = TypeCodeColumns.list;

        private created() {
            let that = this;
            that.search('root');
        };

        openModal(node){
            this.saveTypeCodeEntity.initialization(node);
            this.modal = true;
        };

        onRowClick(item){
            this.search(this.selectNode = item.code);
        };

        search(node, nameOrCode?){
            let that = this;
            let parameter = {
                node: node,
                nameOrCode: nameOrCode,
                state: 1,
            };
            that.$ajax.post(that.$javaAddress + 'typeCode/query', parameter, that.$ajaxJsonConfig).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    if(node === 'root'){
                        that.data = myResult.data;
                    }else{
                        that.childNodeData = myResult.data;
                    }
                }else{
                    that.childNodeData = [];
                }
            });
        };

        // 修改
        nodeEdit(val, index, oldData){
            let item: TypeCodeEntity = val[index];
            item.oldNode = oldData[index].code;
            item.updateId = CookiesUtil.getUserId();
            this.saveRequest(item);
        };

        // 删除
        nodeDelete(val, index){
            let that = this;
            let item: TypeCodeEntity = val[index];
            that.$ajax.post(that.$javaAddress + 'typeCode/invalid', item, that.$ajaxJsonConfig).then(r =>{
                let myResult: MyResult = r.data;
                that.childNodeData = [];
                that.search(item.node);
                if(myResult.code === '1000'){
                    that.modal = false;
                    that.$Message.info('保存成功');
                }else{
                    that.$Message.error('保存失败, error:' + myResult.description);
                }
            });
        };

        save(){
            let that = this;
            // @ts-ignore
            that.$refs['saveTypeCodeEntity'].validate((valid) => {
                if(valid){
                    that.saveTypeCodeEntity.createId = CookiesUtil.getUserId();
                    that.saveRequest(that.saveTypeCodeEntity);
                }
            });
        }

        saveRequest(saveTypeCodeEntity: TypeCodeEntity){
            let that = this;
            that.$ajax.post(that.$javaAddress + 'typeCode/save', saveTypeCodeEntity, that.$ajaxJsonConfig).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    that.search(saveTypeCodeEntity.node);
                    that.modal = false;
                    that.$Message.info('保存成功');
                }else{
                    that.$Message.error('保存失败, error:' + myResult.description);
                    that.search(saveTypeCodeEntity.node);
                }
            });
        };

    }
</script>
