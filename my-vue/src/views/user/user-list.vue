<template>
  <div>
    <div>
      <!-- 查询模块 -->
      <el-form :inline="true" :model="page.params" class="demo-form-inline">
        <!-- 部门查询 -->
        <el-form-item label="部门">
          <el-select v-model="page.params.userDepartment" placeholder="请选择" clearable>
            <el-option
              v-for="item in departmentList"
              :key="item.departId"
              :label="item.departName"
              :value="item.departId"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- 姓名模糊查询 -->
        <el-form-item label="姓名">
          <!-- clearable：可清空 -->
          <el-input v-model="page.params.userName" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <!-- 性别查询 -->
        <el-form-item label="性别">
          <el-select v-model="page.params.userSex" placeholder="请选择" clearable>
            <el-option label="男" :value="1"></el-option>
            <el-option label="女" :value="2"></el-option>
          </el-select>
        </el-form-item>
        <br />
        <!-- 创建时间区间查询 -->
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="createdTime"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            @change="changeCreatedTime"
          ></el-date-picker>
        </el-form-item>
        <br />
        <!-- 生日区间查询 -->
        <el-form-item label="生  日">
          <el-date-picker
            v-model="birthdayTime"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            @change="changebirthdayTime"
          ></el-date-picker>
        </el-form-item>
        <!-- 查询按钮 -->
        <el-form-item>
          <el-button type="primary" @click="getUserList">查询</el-button>
        </el-form-item>
      </el-form>
      <!-- 查询模块结束 -->
    </div>
    <!-- 表格模块 -->
    <el-table :data="page.list" border style="width: 100%" @sort-change="sortChange">
      <!-- 用户编号 -->
      <el-table-column prop="userId" label="用户编号" sortable="custom"></el-table-column>
      <!-- 用户姓名 -->
      <el-table-column prop="userName" label="用户姓名" sortable="custom"></el-table-column>
      <!-- 性别 -->
      <el-table-column prop="userSex" label="性别">
        <template slot-scope="scope">
          <span>{{scope.row.userSex === 1 ?'男':'女'}}</span>
        </template>
      </el-table-column>
      <!-- 年龄 -->
      <el-table-column prop="userAge" label="年龄" sortable="custom"></el-table-column>
      <!-- 生日 -->
      <el-table-column prop="userBirthday" label="生日" sortable="custom">
        <template slot-scope="scope">
          <span>{{scope.row.userBirthday | handlerNullTime}}</span>
        </template>
      </el-table-column>
      <!-- 部门名称 -->
      <el-table-column prop="department.departName" label="部门名称" sortable="custom"></el-table-column>
      <!-- 创建时间 -->
      <el-table-column prop="createdTime" label="创建时间" sortable="custom">
        <template slot-scope="scope">
          <span>{{scope.row.createdTime | handlerNullTime}}</span>
        </template>
      </el-table-column>
      <!-- 更新时间 -->
      <el-table-column prop="updateTime" label="更新时间" sortable="custom">
        <template slot-scope="scope">
          <span>{{scope.row.updateTime | handlerNullTime}}</span>
        </template>
      </el-table-column>
      <!-- 操作 -->
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            icon="el-icon-edit"
            circle
            @click="toUpdate(scope.row.userId)"
          ></el-button>
          <el-button
            size="mini"
            icon="el-icon-delete"
            type="danger"
            circle
            @click="deleteById(scope.row.userId)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改会话框 -->
    <el-dialog title="修改用户" :visible.sync="dialogFormVisible">
      <user-edit :user="user" @closeDialog="closeDialog" @getUserList="getUserList"></user-edit>
    </el-dialog>
    <!-- 表格模块结束 -->
    <!-- 分页模块 -->
    <div class="page-component">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page.pageNumber"
        :page-sizes="[1,2,5,10]"
        :page-size="page.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount"
      ></el-pagination>
      <!-- 分页模块结束 -->
    </div>
  </div>

</template>
<script>
import userApi from "@/api/user";
// import departmentApi from "../../api/department.js";
import UserEdit from "./user-edit";
export default {
  data() {
    return {
      // 分页查询相关
      page: {
        pageNumber: 1,
        pageSize: 5,
        totalPage: 0,
        totalCount: 0,
        list: [],
        params: {},
        sortColumn: "",
        sortMethod: ""
      },
      createdTime: null,
      birthdayTime: null,
      // departmentList: [],
      departmentList:this.$store.getters.getDepartmentList,
      dialogFormVisible:false,
      user: {}
    };
  },
  components: {
    UserEdit
  },
  methods: {
    getUserList() {
      userApi.userList(this.page).then(res => {
        this.page = res.data;
      });
    },
    changeCreatedTime(val) {
      if (val) {
        // 创建时间不为空，处理一下创建时间区间
        this.page.params.userCreatedStart = val[0];
        this.page.params.userCreatedEnd = val[1];
      } else {
        this.page.params.userCreatedStart = null;
        this.page.params.userCreatedEnd = null;
      }
    },
    changebirthdayTime(val) {
      if (val) {
        // 创建时间不为空，处理一下创建时间区间
        this.page.params.userBirthdayStart = val[0];
        this.page.params.userBirthdayEnd = val[1];
      } else {
        this.page.params.userBirthdayStart = null;
        this.page.params.userBirthdayEnd = null;
      }
    },
    handleSizeChange(val) {
      // PageSize改变时触发
      // console.log(`每页 ${val} 条`);
      this.page.pageSize = val;
      this.getUserList();
    },
    handleCurrentChange(val) {
      // CurrentPage改变时触发（进行翻页）
      // console.log(`当前页: ${val}`);
      this.page.pageNumber = val;
      this.getUserList();
    },
    // getDepartmentList() {
    //   departmentApi.departmentList().then(res => {
    //     this.departmentList = res.data;
    //   });
    // },
    sortChange(column, prop, order) {
      // console.log(column,prop,order)
      this.page.sortColumn = column.prop;
      this.page.sortMethod = column.order;
      this.getUserList();
    },
    // 会话框
    toUpdate(userId) {
      userApi.getbyId(userId).then(res => {
        this.user = res.data;
        this.dialogFormVisible = true;
      });
    },
    deleteById(userId) {
      this.$confirm("是否删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          userApi.deleteById(userId).then(res=>{
            this.getUserList()
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    // 关闭会话框
    closeDialog() {
      this.dialogFormVisible = false;
    }
  },
  filters: {
    handlerNullTime(time) {
      //  if(time === '' || time === null || time === undefined)
      if (!time) {
        return "无";
      }
      return time;
    }
  },
  created() {
    // this.getDepartmentList();
    this.getUserList();
  }
};
</script>
<style scoped>
.page-component {
  margin-top: 15px;
}
</style>