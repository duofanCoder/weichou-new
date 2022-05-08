<template>
  <template v-for="item in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]">
    <n-card class=" " hoverable>
      <div class="flex items-center gap-3">
        <n-avatar
          round
          :size="48"
          src="https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg"
        />
        <div>
          <p class="text-xl hover:( underline underline-offset-1 cursor-pointer)"> 多凡</p>
          <p class="text-gray-400"> <n-time :time="0" type="datetime" /></p>
        </div>
      </div>
      <div class="border-t my-2"></div>

      <router-link to="/project/journal" class="hover:( cursor-pointer)">
        <p class="font-semibold text-2xl">第一弹众筹管理栏开放</p>
        <img class="h-60 object-cover my-2" src="/src/assets/material/ipad.jpg" />
        <p>
          感谢卡友的热情支持，999档位和699档位已经卖完，为了答谢广大卡友，我们决定，增加更多福利！999加入桃园三结义镀银金属卡*3张
          699加入粗闪面卡*10张
        </p>
      </router-link>
      <div class="border-t my-2"></div>
      <div class="flex space-x-4">
        <div>
          <span><i-mdi-message-outline class="self-center align-middle" /></span>
          <span class="ml-2">500</span>
        </div>

        <div>
          <span class="cursor-pointer">
            <i-fa-thumbs-o-up v-if="true" class="self-center align-middle" />
            <i-fa-thumbs-up v-else class="self-center align-middle" />
          </span>
          <span class="ml-2 align-bottom">290</span>
        </div>
      </div>
    </n-card>
  </template>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import MdEditor from 'md-editor-v3';
  import 'md-editor-v3/lib/style.css';
  import { PropType } from 'vue';
</script>
<style scoped></style>
