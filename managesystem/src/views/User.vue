<template>
    <div id='user'>
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
            <el-button class="ml-5" type="danger" @click="handleBatchDelete">批量删除<i
                    class="el-icon-remove-outline"></i></el-button>
            <el-upload action="http://localhost:9090/manage/user/import" style="display: inline-block;">
                <el-button class="ml-5" type="primary" style="margin-left: 10px;">导入<i
                        class="el-icon-bottom"></i></el-button></el-upload>
            <el-button class="ml-5" type="primary" style="margin-left: 10px;" @click="handleExport">导出<i
                    class="el-icon-top"></i></el-button>
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
    </div>
</template>

<script>
    export default {
        name: 'User',
        data() {
            return {
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
                multipleSelection: [],
                tableData: [],
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
                    this.tableData = res.data.records
                    this.userTotal = res.data.total
                })

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
                        if (res.code == "200") {
                            this.loadData()
                            this.$message.success(res.msg)
                            this.dialogFormVisible = false
                            this.form = {}
                        } else {
                            this.$message.error(res.msg)
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
                    if (res.code == "200") {
                        this.loadData()
                        this.$message.success(res.msg)
                    } else {
                        this.$message.error(res.msg)
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
            },
            handleExport() {
                window.open("http://localhost:9090/manage/user/export")
            }
        }

    }
</script>

<style>
    .headerBg {
        background: #eee !important;
    }
</style>