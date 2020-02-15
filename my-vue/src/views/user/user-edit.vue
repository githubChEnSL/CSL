<template>
  <div class="dialog">
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
          <el-date-picker
            v-model="user.userBirthday"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="date"
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateUser">修 改</el-button>
      </div>
  </div>
</template>
<script>
import userApi from "@/api/user";
// import departmentApi from "../../api/department.js";
export default {
  data() {
    return {
      departmentList: this.$store.getters.getDepartmentList,
    };
  },
  props:{
    user:{
      type:Object,
      default:{}
    }
  },
  methods: {
    // getDepartmentList() {
    //   departmentApi.departmentList().then(res => {
    //     this.departmentList = res.data;
    //   });
    // },
    
    // 操作
    updateUser() {
      userApi.update(this.user).then(res => {
        this.$message.success(res.msg)
        this.$emit('closeDialog')
        this.$emit('getUserList')
      });
    }
  },
  created() {
    // this.getDepartmentList();
  }
};
</script>