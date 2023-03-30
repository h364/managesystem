<template>
    <div id="header" style="flex: 1; display: flex; flex-direction: row;">
        <div style="font-size: 18px; flex: 1;">
            <span :class="collapseBtnClass" @click="collapse" style="cursor: pointer;"></span>
        </div>
        <div class="avatar">
            <img :src="user.avatar" alt="头像">
        </div>
        <span style="padding: 4px 8px;">{{user.nickname}}</span>
        <el-dropdown @command="handleCommand">
            <i class="el-icon-arrow-down" style="margin: 28px 5px 5px"></i>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="personality">个人信息</el-dropdown-item>
                <el-dropdown-item command="exit">退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        name: 'Header',
        props: {
            collapseBtnClass: String,
        },
        data() {
            return {
                user: {}
            }
        },
        created() {
            this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        },
        methods: {
            collapse() {
                this.$emit("collapse")
            },
            handleCommand(command) {
                if (command == 'exit')
                    this.$router.push("/")
            }
        }
    }
</script>

<style scoped>
    .avatar img {
        width: 38px;
        vertical-align: middle;
        border-radius: 5px;
    }
</style>