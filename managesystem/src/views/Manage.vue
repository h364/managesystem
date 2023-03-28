<template>
  <div class="manage" style="height: 100%;">
    <el-container style="height: 100%;">
      <Aside :isCollapse="isCollapse" :sideWidth="sideWidth"></Aside>
      <el-container>
        <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex;">
          <Header :collapseBtnClass="collapseBtnClass" @collapse="collapse"></Header>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import Aside from '@/components/Aside'
  import Header from '@/components/Header'

  export default {
    name: 'Manage',
    data() {
      return {
        collapseBtnClass: 'el-icon-s-fold',
        isCollapse: false,
        sideWidth: 200,
      }
    },
    components: {
      Aside,
      Header,
    },
    methods: {
      collapse() {
        this.isCollapse = !this.isCollapse
        if (this.isCollapse) {
          this.sideWidth = 64
          this.collapseBtnClass = 'el-icon-s-unfold'
        } else {
          this.sideWidth = 200
          this.collapseBtnClass = 'el-icon-s-fold'
        }
      },
    }
  }
</script>

<style>

</style>