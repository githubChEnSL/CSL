<template>
  <div>
    <h1>我是登录组件,在.vue文件中  {{msg}} </h1>
  </div>
</template>

<script>
// 
 export default{
   data(){
     return {
        msg:'123'
     }
   },
   methods:{

   },
   filters:{

   },
   computed:{

   }
 }
</script>

<style scoped>
/* style要加scoped  不加表示只在本组件使用,否则会产生样式污染 */
</style>