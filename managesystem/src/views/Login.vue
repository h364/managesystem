<template>
    <div id='login'>
        <div class="wrapper">
            <div style="margin: 200px auto; background-color: #fff; width: 300px; height: 250px; padding: 20px; border-radius: 10px;">
                <div style="margin: 20px 0; text-align: center; font-size: 24px;"><b>登 录</b></div>
                <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-user" v-model="user.username"></el-input>
                <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
                <div style="margin: 10px 0; text-align: right;">
                    <el-button type="primary" size="small" autocomplete="off" @click="handleLogin">登录</el-button>
                    <el-button type="warning" size="small" autocomplete="off">注册</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Login',
    data () {
        return {
            user: {}
        }
    },
    methods: {
        handleLogin() {
            this.request.post("/user/login", this.user).then(res => {
                if(res.code == "200") {
                    this.$router.push("/manage")
                    this.$message.success("登录成功")
                    localStorage.setItem("user", JSON.stringify(res.data))
                }else{
                    this.$message.error(res.msg);
                }
            })
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