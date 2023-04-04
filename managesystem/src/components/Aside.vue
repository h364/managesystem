<template>
  <div id="aside">
    <el-aside :width="sideWidth + 'px'"
      style="background-color: rgb(238, 241, 246); height: 100%; overflow: hidden; box-shadow: 2px 0 6px rgba(0, 21, 41, .35);">
      <el-menu :default-openeds="['1', '3']" style="height: 100%; overflow: hidden;" background-color="rgb(48, 65, 86)"
        text-color="#fff" :collapse-transition="isCollapse" :collapse="isCollapse" router>
        <div style="height: 60px; line-height: 60px;">
          <img src="../assets/logo.png" alt="logo"
            style="width: 32px; position: relative; top: 10px; left: 18px; margin-right: 28px;">
          <b style="color: white; ">后台管理系统</b>
        </div>
        <el-menu-item index="/manage/home">
          <template slot="title">
            <i class="el-icon-house"></i>
            <span slot="title">主页</span>
          </template>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>系统管理</span>
          </template>
          <el-menu-item index="/manage/user">
            <i class="el-icon-s-custom"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="/manage/file">
            <i class="el-icon-document"></i>
            <span slot="title">文件管理</span>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
  </div>
</template>

<script>
  export default {
    name: 'Aside',
    props: {
      isCollapse: Boolean,
      sideWidth: Number
    }
  }
</script>

<style scoped>

</style>