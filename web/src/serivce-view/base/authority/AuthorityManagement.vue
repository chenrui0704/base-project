<style>

</style>
<template>
    <Row>
        <Row>
            <Button @click="save" type="success" style="width: 80px;margin-bottom: 10px;" >保存</Button>
        </Row>
        <Col span="12">
            <Table :data="roleData" :columns="roleColumns" @on-row-click="onRowClick"></Table>
        </Col>
        <Col span="12" style="padding-left: 10px;">
            <Table ref="menuTable" :data="menuData" :columns="menuColumns"></Table>
        </Col>
    </Row>
</template>

<script lang="ts">
    import {Vue,Component} from 'vue-property-decorator';
    import AuthorityManagementColumns from "./AuthorityManagementColumns";
    import MyResult from "../../../util/MyResult";

    @Component({})
    export default class AuthorityManagement extends Vue{

        selectRoleId: number = 0;
        roleData: any = [];
        roleColumns: any = AuthorityManagementColumns.roleList;

        menuTable: any;
        menuData: any = [];
        menuColumns: any = AuthorityManagementColumns.menuList;

        created(){
            this.queryRole();
        }

        save(){
            let that = this;
            // @ts-ignore
            let menuTableSelection = that.$refs.menuTable.getSelection();
            if(menuTableSelection.length == 0){
                that.$Message.error('请选择菜单');
                return;
            }
            let menuIds: Array<Number> = [];
            menuTableSelection.forEach(x =>{menuIds.push(x.id)});
            that.$ajax.post(that.$javaAddress + 'authority/save?roleId=' + that.selectRoleId + '&menuIds=' + menuIds).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    that.$Message.info('保存成功');
                }else {
                    that.$Message.error(myResult.description);
                }
            });
        }

        onRowClick(item){
            this.queryAuthorityByRoleId(this.selectRoleId = item.id);
        };

        queryAuthorityByRoleId(roleId){
            let that = this;
            that.$ajax.post(that.$javaAddress + 'authority/queryByRoleId?roleId=' + roleId).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    myResult.data.forEach(x =>{if(x.state == 1) x._checked = true;});
                    that.menuData = myResult.data;
                }else {
                    that.$Message.error(myResult.description);
                }
            });
        }

        queryRole(){
            let that = this;
            let parameter = {
                node: 'role',
                state: 1,
            };
            that.$ajax.post(that.$javaAddress + 'typeCode/query', parameter, that.$ajaxJsonConfig).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    that.roleData = myResult.data;
                }else {
                    that.$Message.error(myResult.description);
                }
            });
        }

    }

</script>
