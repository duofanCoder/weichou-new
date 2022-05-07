<script setup lang="ts">
  import { useDialog, useLoadingBar, useMessage, useNotification } from 'naive-ui';
  window.$message = useMessage();
  window.$dialog = useDialog();
  window.$notification = useNotification();
  window.$loadingBar = useLoadingBar();
</script>

<template>
  <router-view></router-view>
</template>

<style>
  #app * {
    font-family: Cambria;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    box-sizing: border-box;
  }

  ::-webkit-scrollbar {
    /*滚动条整体样式*/
    width: 4px; /*高宽分别对应横竖滚动条的尺寸*/
    height: 4px;
  }
  ::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    border-radius: 4px;
    box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: #a7a4a4;
  }
  ::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
    background: #ededed;
  }

  .n-space {
    margin-top: 0 !important;
    margin-bottom: 0 !important;
  }
</style>
