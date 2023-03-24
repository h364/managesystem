<template>
  <div class="home" style="height: 100%;">
    <el-container style="height: 100%;">
      <el-aside :width="sideWidth + 'px'"
        style="background-color: rgb(238, 241, 246); height: 100%; overflow: hidden; box-shadow: 2px 0 6px rgba(0, 21, 41, .35);">
        <el-menu :default-openeds="['1', '3']" style="height: 100%; overflow: hidden;"
          background-color="rgb(48, 65, 86)" text-color="#fff" :collapse-transition="isCollapse" :collapse="isCollapse">
          <div style="height: 60px; line-height: 60px;">
            <img src="../assets/logo.png" alt="logo"
              style="width: 32px; position: relative; top: 10px; left: 18px; margin-right: 28px;">
            <b style="color: white; ">后台管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span>导航三</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex;">
          <div style="flex: 1; font-size: 18px;">
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
          <span>王小虎</span>
        </el-header>

        <el-main>
          <div class="search">
            <el-input style="width: 200px; margin-right: 10px;" placeholder="输入名称" suffix-icon="el-icon-search"
              class="ml-5" v-model="username"></el-input>
            <el-input style="width: 200px; margin-right: 10px;" placeholder="输入邮箱" suffix-icon="el-icon-message"
              class="ml-5" v-model="email"></el-input>
            <el-input style="width: 200px; margin-right: 10px;" placeholder="输入地址" suffix-icon="el-icon-position"
              class="ml-5" v-model="address"></el-input>
            <el-button class="ml-5" type="primary" @click="search">搜索</el-button>
            <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
          </div>
          <div class="option" style="margin: 10px 0">
            <el-button class="ml-5" type="primary" @click="addClick">新增<i
                class="el-icon-circle-plus-outline"></i></el-button>
            <el-button class="ml-5" type="danger" @click="handleBatchDelete">批量删除<i class="el-icon-remove-outline"></i></el-button>
            <el-button class="ml-5" type="primary">导入<i class="el-icon-bottom"></i></el-button>
            <el-button class="ml-5" type="primary">导出<i class="el-icon-top"></i></el-button>
          </div>

          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="550px" @close="isShowWarning = false">
            <el-form :model="form">
              <el-form-item label="用户名" :label-width="formLabelWidth">
                <el-input autocomplete="off" v-model="form.username" @focus="handleFocus"></el-input>
              </el-form-item>
              <el-form-item label="昵称" :label-width="formLabelWidth">
                <el-input autocomplete="off" v-model="form.nickname" @focus="handleFocus"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" :label-width="formLabelWidth">
                <el-input autocomplete="off" v-model="form.email" @focus="handleFocus"></el-input>
              </el-form-item>
              <el-form-item label="电话" :label-width="formLabelWidth">
                <el-input autocomplete="off" v-model="form.phone" @focus="handleFocus"></el-input>
              </el-form-item>
              <el-form-item label="地址" :label-width="formLabelWidth">
                <el-input autocomplete="off" v-model="form.address" @focus="handleFocus"></el-input>
              </el-form-item>
            </el-form>

            <div style="width: 200px; color: #F56C6C; padding-left: 20px;" v-show="isShowWarning">
              <span>请输入完整的信息</span>
            </div>

            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleSave">确 定</el-button>
            </div>
          </el-dialog>

          <el-table :data="tableData" header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>></el-table-column>
            <el-table-column prop="id" label="ID" width="50"></el-table-column>
            <el-table-column prop="username" label="用户名" width="120"></el-table-column>
            <el-table-column prop="nickname" label="昵称"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="phone" label="电话"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
                <el-button type="danger" @click="handleDelete(scope.row)">删除<i
                    class="el-icon-remove-outline"></i></el-button>
              </template>
            </el-table-column>
          </el-table>

          <div class="block" style="margin-top: 10px;">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
              :page-sizes="[2, 5, 10, 20]" :page-size="currentSize" :current-page="currentPage"
              layout="total, sizes, prev, pager, next, jumper" :total="userTotal">
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

  export default {
    name: 'HomeView',
    data() {
      return {
        tableData: [],
        collapseBtnClass: 'el-icon-s-fold',
        isCollapse: false,
        sideWidth: 200,
        currentPage: 1,
        currentSize: 2,
        userTotal: 0,
        username: "",
        nickname: "",
        phone: "",
        email: "",
        address: "",
        dialogFormVisible: false,
        formLabelWidth: '60px',
        isShowWarning: false,
        form: {},
        multipleSelection: []
      }
    },

    created() {
      this.loadData()
    },
    methods: {
      loadData() {
        this.request.get("/user/page", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.currentSize,
            username: this.username,
            email: this.email,
            address: this.address
          }
        }).then(res => {
          console.log(res);
          this.tableData = res.records
          this.userTotal = res.total
        })

      },
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
      handleSizeChange(pageSize) {
        this.currentSize = pageSize
        this.loadData()
      },
      handleCurrentChange(pageNum) {
        this.currentPage = pageNum
        this.loadData()
      },
      search() {
        this.loadData()
      },
      reset() {
        this.username = ""
        this.email = ""
        this.address = ""
        this.loadData()
      },
      addClick() {
        this.dialogFormVisible = true
      },
      handleSave() {
        if (!!this.form.username && !!this.form.nickname && !!this.form.email && !!this.form.phone && !!this.form.address) {
          this.request.post("/user/save", this.form).then(res => {
            if (res) {
              this.loadData()
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.form = {}
            } else {
              this.$message.error("保存失败")
            }
          })
        } else {
          this.isShowWarning = true
        }
      },
      handleFocus() {
        this.isShowWarning = false
      },
      handleEdit(row) {
        this.form = Object.assign({}, row)
        this.dialogFormVisible = true
        this.loadData()
      },
      handleDelete(row) {
        this.request.post("/user/delete", row).then(res => {
          if (res) {
            this.loadData()
            this.$message.success("删除成功")
          } else {
            this.$message.error("删除失败")
          }
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val
      },
      handleBatchDelete() {
        let ids = this.multipleSelection.map(value => value.id)
        this.request.post("/user/delete/batch", ids).then(res => {
          if (res) {
            this.loadData()
            this.$message.success("删除成功")
          } else {
            this.$message.error("删除失败")
          }
        })
      }
    }
  }
</script>

<style>
  .headerBg {
    background: #eee !important;
  }
</style>