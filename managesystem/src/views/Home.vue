<template>
    <div id='home'>
        <el-row :gutter="10" style="margin-bottom: 80px;">
            <el-col :span="6">
                <el-card>
                    <div style="color: #409EFF; padding-bottom: 10px;"><i class="el-icon-user-solid"></i>用户总数</div>
                    <div style="text-align: center;">1000</div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div style="color: #67C23A; padding-bottom: 10px;"><i class="el-icon-circle-plus"></i>昨日注册</div>
                    <div style="text-align: center;">500</div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div style="color: #F56C6C; padding-bottom: 10px;"><i class="el-icon-circle-plus"></i>收益总额</div>
                    <div style="text-align: center;">￥10000</div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div style="color: #E6A23C; padding-bottom: 10px;"><i class="el-icon-s-shop"></i>门店总量</div>
                    <div style="text-align: center;">200</div>
                </el-card>
            </el-col>

        </el-row>

        <el-row>
            <el-col :span="12">
                <div id="main" style="width: 500px; height: 400px;"></div>
            </el-col>
            <el-col :span="12">
                <div id="pie" style="width: 500px; height: 400px;"></div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import * as echarts from 'echarts';

    export default {
        name: 'Home',
        data() {
            return {

            }
        },
        mounted() {
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            var option;
            var pieChartDom = document.getElementById('pie');
            var pieMyChart = echarts.init(pieChartDom);
            var pieOption;

            option = {
                title: {
                    text: '各季度注册用户统计',
                    subtext: '趋势图',
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: ['第一季度', '第二季度', '第三季度', '第四季度',]
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [],
                        type: 'bar'
                    }
                ]
            };


            pieOption = {
                title: {
                    text: '各季度注册用户统计',
                    subtext: '比例图',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: '50%',
                        data: [],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            this.request.get("/echarts/get").then(res => {
                if (res.code == "200") {
                    console.log(res);
                    option.series[0].data = res.data
                    myChart.setOption(option);

                    pieOption.series[0].data = [
                        { name: '第一季度', value: res.data[0] },
                        { name: '第二季度', value: res.data[1] },
                        { name: '第三季度', value: res.data[2] },
                        { name: '第四季度', value: res.data[3] }
                    ]
                    pieMyChart.setOption(pieOption);
                }
            })

        }

    }
</script>

<style scoped>

</style>