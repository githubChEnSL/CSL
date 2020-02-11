<template>
  <div>
    <el-table :data="departmentList" border style="width: 100%">
      <el-table-column prop="departId" label="部门编号"></el-table-column>
      <el-table-column prop="departName" label="部门名称"></el-table-column>
    </el-table>
  </div>
</template>
<script>
import departmentApi from '../../api/department.js'
export default {
  data() {
    return {
      departmentList : []
    };
  },
  methods: {
    getDepartmentList(){
      departmentApi.departmentList().then(res=>{
        // 调用department.js的departmentList的方法.then接收回调回来的结果放到res
        // console.log(res.data)
        this.departmentList = res.data
      })
    }
  },
  created(){
    this.getDepartmentList()
  }
};
</script>