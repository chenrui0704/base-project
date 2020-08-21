<style>
    .marginTop10{
        margin-top: 10px;
    }
</style>
<template>
    <Row>
        <Row>
            <Button @click="queryUser()" style="width: 80px;" type="success">查询</Button>
            <Button @click="openSaveModal(null)" style="width: 80px;margin-left: 5px;" type="info">新增</Button>
        </Row>
        <Row class="marginTop10">
            <Table border :data="data" :columns="columns" ></Table>
        </Row>
        <Row class="marginTop10">
            <Page :total="queryEntity.total" :page-size="queryEntity.size" :current="queryEntity.index" @on-change="changePage"></Page>
        </Row>

        <Row description="modal">
            <Modal v-model="modal" title="新增用户" :mask-closable="false" :footer-hide="true" >
                <Form ref="userEntity" :model="userEntity" :label-width="90" :rules="userEntityRules">
                    <FormItem label="用户名：" prop="username">
                        <Input v-model="userEntity.username" placeholder="请输入用户名"></Input>
                    </FormItem>
                    <FormItem label="登录名：" prop="loginName">
                        <Input v-model="userEntity.loginName" placeholder="请输入登录名" :disabled="!operationType"></Input>
                    </FormItem>
                    <FormItem label="密码：" prop="password" v-if="operationType">
                        <Input type="password" v-model="userEntity.password"  placeholder="请输入密码"></Input>
                    </FormItem>
                    <FormItem label="邮箱：" prop="email">
                        <Input  v-model="userEntity.email"  placeholder="请输入邮箱"></Input>
                    </FormItem>
                    <FormItem label="身份证：" prop="identityNumber">
                        <Input  v-model="userEntity.identityNumber"  placeholder="请输入身份证号码"></Input>
                    </FormItem>
                    <FormItem label="手机：" prop="phone">
                        <Input  v-model="userEntity.phone"  placeholder="请输入手机号码"></Input>
                    </FormItem>
                    <FormItem label="角色：" prop="roleId">
                        <Select v-model="userEntity.roleId">
                            <Option v-for="item in roleData" :value="item.id" :key="item.id">{{ item.name }}</Option>
                        </Select>
                    </FormItem>
                </Form>
                <Row>
                    <Button type="success" style="width: 70px;" @click="saveUser()">保存</Button>
                    <Button type="default" style="width: 70px;margin-left: 5px;" @click="modal = false">取消</Button>
                </Row>
            </Modal>
        </Row>
    </Row>
</template>

<script lang="ts">
    import {Vue,Component} from 'vue-property-decorator';
    import UserEntity from "./UserEntity";
    import MyResult from 'src/util/MyResult';
    import UserEntityRules from "./UserEntityRules";
    import CookiesUtil from "../../../util/CookiesUtil";
    import UserColumns from "./UserColumns";

    @Component({})
    export default class UserManage extends Vue{

        operationType: boolean = true;
        modal: boolean = false;

        userEntity: UserEntity = new UserEntity();
        queryEntity: UserEntity = new UserEntity();
        editEntity: UserEntity = new UserEntity();

        userEntityRules: any = new UserEntityRules();

        roleData: any = [];
        data: any = [];

        operation: any = {
            title: '操作',
            render: (h, p) =>{
                let that = this;
                let item: UserEntity = p.row;
                let state = item.state;
                let viewButtonStyle = {
                    props: {
                        type: state == 1 ? 'error' : 'success',
                        size: 'small',
                        ghost: true
                    },
                    style: {
                        marginRight: '5px'
                    },
                    on: {
                        click: () => {
                            that.updateUserState(item);
                        }
                    }
                };
                let viewButton = h('Button', viewButtonStyle, state == 1  ? '停用' : '启用');

                let resetPasswordStyle = {
                    props: {
                        type: 'info',
                        size: 'small',
                        ghost: true
                    },
                    on: {
                        click: () => {
                            that.resetPassword(item);
                        }
                    }
                };
                let resetPasswordButton = h('Button', resetPasswordStyle, '重置密码');

                let editButtonStyle = {
                    props: {
                        type: 'success',
                        size: 'small',
                        ghost: true
                    },
                    style: {
                        marginRight: '5px'
                    },
                    on: {
                        click: () => {
                            that.openSaveModal(item);
                        }
                    }
                };
                let editButton = h('Button', editButtonStyle, '编辑');

                return [editButton, viewButton, resetPasswordButton];
            }
        };

        columns: any = UserColumns.getList(this.operation);

        created(){
            this.queryUser();
            this.queryRole();

        }

        updateUserState(userEntity: UserEntity){
            userEntity.state = userEntity.state == 0 ? 1 : 0;
            let that = this;
            that.$ajax.post(that.$javaAddress + 'user/updateUserState', userEntity, that.$ajaxJsonConfig).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    that.queryUser();
                }
            });

        }

        resetPassword(userEntity: UserEntity){
            userEntity.password = '1234';
            let that = this;
            that.$ajax.post(that.$javaAddress + 'user/resetPassword', userEntity, that.$ajaxJsonConfig).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    that.$Message.success('重置密码成功');
                }
            });
        }


        openSaveModal(userEntity: UserEntity){
            if(this.$refs.userEntity){
                (this.$refs.userEntity as any).resetFields();
            }
            if(null == userEntity){
                this.userEntity.initialization();
                this.operationType = true;
            }else{
                this.userEntity.copy(userEntity);
                this.operationType = false;
            }
            this.modal = true;
        }

        saveUser(){
            let that = this;
            (that.$refs['userEntity'] as any).validate((valid) => {
                if(valid){
                    that.$ajax.post(that.$javaAddress + 'user/save', that.userEntity, that.$ajaxJsonConfig).then(r =>{
                        let myResult: MyResult = r.data;
                        if(myResult.code === '1000'){
                            that.queryUser();
                            that.modal = false;
                            that.$Message.info('保存成功');
                        }else{
                            that.$Message.error('保存失败, error:' + myResult.description);
                        }
                    });
                }
            });
        };

        queryUser(){
            let that = this;
            that.$ajax.post(that.$javaAddress + 'user/query', that.queryEntity, that.$ajaxJsonConfig).then(r =>{
                let myResult: MyResult = r.data;
                if(myResult.code === '1000'){
                    let data = myResult.data;
                    that.data = data.list;
                    that.queryEntity.total = data.total;
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

        changePage(value){
            this.queryEntity.index = value;
            this.queryUser();
        }

    }
</script>
