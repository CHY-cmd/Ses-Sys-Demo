<template>
  <v-app style="background-color: rgb(242, 242, 242);">
   <v-content>
     <router-view></router-view>
   </v-content>
  </v-app>
</template>

<script>
import CustInfoList from './views/CustInfoList'

export default {
 name: 'App',
 components: {
   CustInfoList,
 }
 
}
</script>
<style>
#app {
  background-color:rgb(242, 242, 242) ;
  min-height: 100vh;
}
</style>
