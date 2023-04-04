<template>
    <div id='file'>
        <div class="search">
            <el-input style="width: 200px; margin-right: 10px;" placeholder="输入名称" suffix-icon="el-icon-search"
                class="ml-5" v-model="name"></el-input>
            <el-input style="width: 200px; margin-right: 10px;" placeholder="输入类型" suffix-icon="el-icon-message"
                class="ml-5" v-model="type"></el-input>
            <el-button class="ml-5" type="primary" @click="search">搜索</el-button>
            <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
        </div>
        <div class="option" style="margin: 10px 0">
            <el-upload action="http://localhost:9090/manage/file/upload" style="display: inline-block;">
                <el-button class="ml-5" type="primary" style="margin-right: 10px;">上传<i
                        class="el-icon-bottom"></i></el-button></el-button></el-upload>
            <el-button class="ml-5" type="danger" @click="handleBatchDelete">批量删除<i
                    class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>></el-table-column>
            <el-table-column prop="id" label="ID" width="50"></el-table-column>
            <el-table-column prop="name" label="文件名称" width="120"></el-table-column>
            <el-table-column prop="type" label="文件类型"></el-table-column>
            <el-table-column prop="size" label="文件大小(kb)"></el-table-column>
            <el-table-column label="下载">
                <el-button type="primary">下载<i class="el-icon-download"></i></el-button>
            </el-table-column>
            <el-table-column label="启用">
                <el-switch v-model="value" active-color="#13ce66" inactive-color="#ff4949">
                </el-switch>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button type="danger" @click="handleDelete(scope.row)">删除<i
                            class="el-icon-remove-outline"></i></el-button>
                </template>
            </el-table-column>
        </el-table>

        <div class="block" style="margin-top: 10px;">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :page-sizes="[2, 5, 10, 20]" :page-size="currentSize" :current-page="currentPage"
                layout="total, sizes, prev, pager, next, jumper" :total="fileTotal">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'File',
        data() {
            return {
                currentPage: 1,
                currentSize: 2,
                fileTotal: 0,
                value: true,
                name: "",
                type: "",
                formLabelWidth: '60px',
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
                this.request.get("/file/page", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.currentSize,
                        name: this.name,
                        type: this.type
                    }
                }).then(res => {
                    console.log(res);
                    this.tableData = res.data.records
                    this.fileTotal = res.data.total
                })

            },
            upload() {

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
                this.name = ""
                this.type = ""
                this.loadData()
            },
            handleDelete(row) {
                this.request.post("/file/delete", row).then(res => {
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
                this.request.post("/file/delete/batch", ids).then(res => {
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