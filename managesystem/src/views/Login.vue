<template>
    <div id='login'>
        <div class="wrapper">
            <div
                style="margin: 200px auto; background-color: #fff; width: 300px; height: 250px; padding: 20px; border-radius: 10px;">
                <div style="margin: 20px 0; text-align: center; font-size: 24px;"><b>登 录</b></div>
                <el-form :model="user" :rules="rules" ref="ruleForm">
                    <el-form-item prop="username">
                        <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-user"
                            v-model="user.username"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-lock" show-password
                            v-model="user.password"></el-input>
                    </el-form-item>
                </el-form>
                <div style="margin: 10px 0; text-align: right;">
                    <el-button type="primary" size="small" autocomplete="off" @click="handleLogin">登录</el-button>
                    <el-button type="warning" size="small" autocomplete="off" @click="toRegist">注册</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Login',
        data() {
            return {
                user: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            handleLogin() {
                this.$refs.ruleForm.validate((valid) => {
                    console.log(valid);
                    if (valid) {
                        this.request.post("/user/login", this.user).then(res => {
                            if (res.code == "200") {
                                this.$router.push("/manage")
                                this.$message.success("登录成功")
                                localStorage.setItem("user", JSON.stringify(res.data))
                            } else {
                                this.$message.error(res.msg);
                            }
                        })
                    }
                })
            },
            toRegist() {
                this.$router.push("/regist")
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