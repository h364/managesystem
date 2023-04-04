<template>
    <div id='person'>
        <el-card style="width: 500px;">
            <el-upload class="avatar-uploader" action="http://localhost:9090/manage/file/upload" :show-file-list="false"
                style="flex: 1;" :on-success="handleAvatarSuccess">
                <img v-if="form.avatar" :src="form.avatar" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>

            <el-form label-width="80px" label-position="left" style="width: 400px;" :model="form">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" disabled></el-input>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="form.nickname"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="updateUserInfo">确定</el-button>
                </el-form-item>
            </el-form>
        </el-card>

    </div>
</template>

<script>
    export default {
        name: 'Person',
        data() {
            return {
                user: {},
                form: {},
                imageUrl: ""
            }
        },
        created() {
            this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
            this.request.post("/user/getUser", this.user).then(res => {
                if (res.code == "200") {
                    this.form = res.data
                    console.log(res.data);
                } else {
                    this.$message.error(res.msg)
                }
            })
        },
        methods: {
            async getUser() {
                return (await this.request.post("/user/getUser", this.user)).data
            },
            updateUserInfo() {
                this.request.post("/user/save", this.form).then(res => {
                    if (res.code == "200") {
                        this.getUser().then(res => {
                            this.user.avatar = res.avatar
                            localStorage.setItem("user", JSON.stringify(this.user))
                        })
                        this.$message.success(res.msg)
                    } else {
                        this.$message.error(res.msg)
                    }
                })
            },
            handleAvatarSuccess(res) {
                this.form.avatar = res
            }
        }
    }
</script>

<style scoped>
    .avatar-uploader{
        text-align: center;
        padding-bottom: 10px;
    }

    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 138px;
        height: 138px;
        line-height: 138px;
        text-align: center;
    }

    .avatar {
        width: 138px;
        height: 138px;
        display: block;
        border-radius: 10px;
    }
</style>