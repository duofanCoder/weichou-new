<template>
  <div class="flex flex-col justify-center mt-0 items-center md:(flex-row mt-6 ) gap-8 mx-auto">
    <!-- md:(w-120 h-80) xl:(w-160 h-90) -->
    <n-carousel centered-slides class="w-screen h-80 pb-8 sm:(min-w-100 max-w-150)" show-arrow>
      <div class="w-full h-full">
        <iframe
          class="w-full h-full"
          src="//player.bilibili.com/player.html?aid=809858252&bvid=BV1834y1t7x7&cid=552284811&page=1"
          scrolling="no"
          border="0"
          frameborder="no"
          framespacing="0"
          allowfullscreen="true"
        >
        </iframe>
      </div>
      <img class="w-full h-full object-cover" src="/src/assets/material/carousel_d_1.jfif" />
      <img class="w-full h-full object-cover" src="/src/assets/material/carousel_d_2.jfif" />
      <img class="w-full h-full object-cover" src="/src/assets/material/carousel_d_3.jfif" />
      <img class="w-full h-full object-cover" src="/src/assets/material/carousel_d_4.jfif" />
      <template #arrow="{ prev, next }">
        <div class="custom-arrow">
          <button type="button" class="absolute left-0 bottom-0" @click="prev">
            <n-icon><i-grommet-icons-form-previous @click="prev" /></n-icon>
          </button>
          <button type="button" class="absolute right-0 bottom-0" @click="next">
            <n-icon> <i-grommet-icons-form-next /></n-icon>
          </button>
        </div>
      </template>
      <template #dots="{ total, currentIndex, to }">
        <ul class="flex mb-1 mx-auto w-full justify-center absolute bottom-2">
          <li
            v-for="index of total"
            :class="{ ['is-active']: currentIndex === index - 1 }"
            :key="index"
            @click="to(index - 1)"
            class="inline-block w-4 h-1 rounded-md mx-1 transform duration-300 active:( scale-150 ) bg-dark-300"
          ></li>
        </ul>
      </template>
    </n-carousel>
    <!-- md:(w-120 ) xl:(w-150 ) -->
    <div class="flex flex-col space-y-2 px-2 sm:( max-w-150)">
      <p class="font-bold text-2xl md:(text-3xl) my-3">奥丁：终极掌上游戏机 </p>
      <n-ellipsis
        class="text-sm md:(text-base)"
        :line-clamp="5"
        expand-trigger="click"
        :tooltip="false"
      >
        旗舰游戏手持设备。FHD 1080p 6英寸触摸屏，6600毫安时电池。FHD 1080p
        6英寸触摸屏，6600毫安时电池。FHD 1080p 6英寸触摸屏，6600毫安时电池。FHD 1080p
        6英寸触摸屏，6600毫安时电池。FHD 108.0p 6英寸触摸屏，6600毫安时电池。
      </n-ellipsis>
      <div class="flex flex-col">
        <p class="py-2 text-2xl md:( text-3xl) text-green-600"> 已筹￥73,058</p>
        <n-progress type="line" status="success" :percentage="58" :indicator-placement="'inside'" />
        <div class="flex py-2 text-base justify-between text-sm md:( text-base)">
          <span> 目标金额￥100,000</span>
          <div>
            <i-el-group class="align-middle mr-1 text-xl text-green-600" />
            <span class="align-middle mr-2">64 支持者</span>
          </div>
        </div>
        <div class="flex justify-between">
          <div class="flex justify-center">
            <Love :id="2" :detail="true" />
          </div>
          <div class="space-x-3 self-center">
            <i-uiw-weixin class="text-green-500 hover:( cursor-pointer) align-middle" />
            <i-mdi-link-variant class="text-green-500 hover:( cursor-pointer) align-middle" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  defineProps({
    id: {
      type: Number,
    },
  });
</script>
<style scoped>
  /* .custom-arrow {
    display: flex;
    position: absolute;
    bottom: 0;
    right: 10px;
  } */

  .custom-arrow button {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    width: 28px;
    height: 28px;
    color: rgb(255, 255, 255);
    background-color: rgba(17, 9, 9, 0.76);
    border-width: 0;
    border-radius: 8px;
    transition: background-color 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    cursor: pointer;
  }

  .custom-arrow button:hover {
    background-color: rgba(110, 88, 88, 0.2);
  }

  .custom-arrow button:active {
    transform: scale(0.95);
    transform-origin: center;
  }

  .is-active {
    width: 40px;
    background: rgb(41, 151, 78);
  }
</style>
