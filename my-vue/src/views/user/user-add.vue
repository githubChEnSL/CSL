<template>
  <div>
    <div>
      <el-form :model="user" label-width="80px">
        <el-form-item label="用户姓名" style="width:300px">
          <el-input v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="user.userSex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" style="width:300px">
          <el-input v-model="user.userAge"></el-input>
        </el-form-item>
        <el-form-item label="生日" style="width:300px">
          <el-date-picker  v-model="user.userBirthday"
            type="datetime"
            value-format="yyyy-MM-dd HH-mm-ss"
            placeholder="选择生日"
            style="width:100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="部门" style="width:300px">
          <el-select v-model="user.userDepartment" placeholder="选择部门">
            <el-option
              v-for="item in departmentList"
              :key="item.departId"
              :label="item.departName"
              :value="item.departId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveUser">保存</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import userApi from "../../api/user.js";
import departmentApi from "../../api/department.js";
export default {
  data() {
    return {
      departmentList: [],
      user: {}
    };
  },
  methods: {
    getDepartmentList() {
      departmentApi.departmentList().then(res => {
        this.departmentList = res.data
      });
    },
    saveUser() {
      userApi.save(this.user).then(res=>{
        // this.$message.success(res.msg)
        this.$router.push("userList")
      })
    }
  },
  created() {
    this.getDepartmentList();
  }
};
</script>