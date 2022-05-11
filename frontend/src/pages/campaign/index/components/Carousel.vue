<template>
  <n-carousel centered-slides class="w-screen h-80 pb-8 sm:( min-w-100 max-w-150)" show-arrow>
    <div class="w-full h-full" v-for="item in assetList" :key="item.id">
      <iframe
        v-if="item.assetType == 'VIDEO'"
        class="w-full h-full"
        src="//player.bilibili.com/player.html?aid=21356508&bvid=BV1wW411u7vX&cid=35117927&page=1"
        scrolling="no"
        border="0"
        frameborder="no"
        framespacing="0"
        allowfullscreen="true"
      >
      </iframe>
      <img v-if="item.assetType == 'IMAGE'" class="w-full h-full object-cover" :src="item.url" />
    </div>
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
          class="cursor-pointer inline-block w-4 h-1 rounded-md mx-1 transform duration-300 active:( scale-150 ) bg-dark-300"
        ></li>
      </ul>
    </template>
  </n-carousel>
</template>
<script setup lang="ts">
  import { AssetType } from '@/model';
  defineProps({
    assetList: {
      type: Object,
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
