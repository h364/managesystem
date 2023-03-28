<template>
    <div id="header" style="flex: 1; display: flex; flex-direction: row;">
        <div style="font-size: 18px; flex: 1;">
            <span :class="collapseBtnClass" @click="collapse" style="cursor: pointer;"></span>
        </div>
        <el-dropdown>
            <i class="el-icon-arrow-down" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>查看</el-dropdown-item>
                <el-dropdown-item>新增</el-dropdown-item>
                <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        <span style="padding-right: 55px;">{{user.nickname}}</span>
    </div>
</template>

<script>
    export default {
        name: 'Header',
        props: {
            collapseBtnClass: String,
        },
        data () {
            return {
                user: {}
            }
        },
        created () {
            this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        },
        methods: {
            collapse() {
                this.$emit("collapse")
            }
        }
    }
</script>

<style scoped>

</style>