<template>

  <div @click="showDrawerRef = !showDrawerRef">
    <n-button text class="text-base">
      <p class="font-bold">探索</p>
      <i-ic-twotone-keyboard-arrow-down class="align-middle transform"/>
    </n-button>
  </div>
  <div
      :class="{ hidden: !showDrawerRef }"
      id="drawer-target"
      class="absolute w-full h-screen flex overflow-hidden z-50"
  >
    <n-drawer
        height="auto"
        v-model:show="showDrawerRef"
        placement="top"
        :trap-focus="false"
        to="#drawer-target"
    >
      <div class="w-screen-md mx-auto">
        <div class="flex py-12 justify-between">
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 浏览所有项目</p>
            <p class="w-64 h-24 bg-true-gray-100"> Clever Things For Curious Humans </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 活动排行榜 </p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 优秀团队</p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 在售 </p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 为你而造</p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
        </div>
      </div>
    </n-drawer>
  </div>
</template>

<script setup lang="ts">
import {ref} from "vue";

const showDrawerRef = ref(false);
</script>

<style scoped>

</style>