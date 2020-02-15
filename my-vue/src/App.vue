<template>
  <div id="app">
    <div class="menu-div">
      <el-menu :default-active="activeIndex" class="el-menu-demo" :router="true" mode="horizontal">
        <el-menu-item index="1" :route="'/departmentList'">部门管理</el-menu-item>
        <el-submenu index="2">
          <template slot="title">用户管理</template>
          <el-menu-item index="2-1" :route="'/userList'">用户列表</el-menu-item>
          <el-menu-item index="2-2" :route="'/userAdd'">添加用户</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
    <transition name="component" mode="out-in">
      <router-view />
    </transition>
  </div>
</template> 
<script>
export default {
  data() {
    return {
      activeIndex: "1"
    };
  }
};
</script>
<style scoped>
.menu-div {
  margin-bottom: 15px;
}
.component-enter,
.component-leave-to {
  opacity: 0;
  transform: translateX(50px);
}
/* v-enter-active : 入场动画的时间段 */
/* v-leave-active : 离场动画的时间段 */
.component-enter-active,
.component-leave-active {
  transition: all 0.5s ease;
}
</style>