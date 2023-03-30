<template>
    <div id='regist'>
        <div class="wrapper">
            <div
                style="margin: 150px auto; background-color: #fff; width: 300px; height: 350px; padding: 20px; border-radius: 10px;">
                <div style="margin: 20px 0; text-align: center; font-size: 24px;"><b>注 册</b></div>
                <el-form :model="user" :rules="rules" ref="ruleForm">
                    <el-form-item prop="username">
                        <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-user"
                            v-model="user.username"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-lock" show-password
                            v-model="user.password"></el-input>
                    </el-form-item>
                    <el-form-item prop="checkPass">
                        <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-lock" show-password
                            v-model="user.checkPass"></el-input>
                    </el-form-item>
                </el-form>
                <div style="margin: 10px 0; text-align: right;">
                    <el-button type="primary" size="small" autocomplete="off"
                        @click="handleLogin">注册</el-button></el-button>
                    <el-button type="warning" size="small" autocomplete="off" @click="backToLogin">返回登录</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Regist',
        data() {
            return {
                user: {
                    username: '',
                    password: '',
                    checkPass: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    checkPass: [
                        { required: true, message: '请确认密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            handleLogin() {
                this.$refs['ruleForm'].validate((valid) => {
                    if (valid) {
                        if (this.user.password != this.user.checkPass) {
                            this.$message.error("密码不一致");
                            return false
                        }
                        this.request.post("/user/registry", this.user).then(res => {
                            if (res.code == "200") {
                                this.$message.success("注册成功")
                                localStorage.setItem("user", JSON.stringify(res.data))
                            } else {
                                this.$message.error(res.msg);
                            }
                        })
                    }
                })
            },
            backToLogin() {
                this.$router.push("/")
            }
        }
    }
</script>

<style scoped>
    .wrapper {
        height: 100vh;
        background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
        overflow: hidden;
    }
</style>