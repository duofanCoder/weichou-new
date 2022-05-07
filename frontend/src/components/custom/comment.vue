<template>
  <div class="flex flex-col space-y-4">
    <div class="flex items-center">
      <p class="text-2xl font-medium mr-3">评论 </p>
      771
    </div>
    <!--  评论块 -->
    <div class="flex gap-3">
      <div>
        <n-avatar round :size="48" src="/src/assets/material/avatar.png" />
      </div>
      <div class="w-full flex gap-3 h-full">
        <n-input
          type="textarea"
          round
          :autosize="{
            minRows: 1,
            maxRows: 3,
          }"
          placeholder="发一条建议或评论"
          class="w-full bg-gray-100"
        >
        </n-input>
        <n-button type="primary">发布</n-button>
      </div>
    </div>

    <div class="flex gap-3">
      <div>
        <n-avatar round :size="48" src="/src/assets/material/avatar.png" />
      </div>
      <div class="w-full flex flex-col self-center">
        <p class="text-red-500 text-xs">我是老大 </p>
        <p class="text-sm">就这个up我不敢催更，我怕他顺着网线过来打我[嫌弃]</p>
        <p class="text-gray-400 text-xs space-x-4 mb-3">
          <n-time />
          <span class="cursor-pointer hover:(text-green-600)">
            <i-fa-thumbs-o-up v-if="false" class="self-center align-middle w-3 h-3" />
            <i-fa-thumbs-up v-else class="self-center align-middle text-green-600 w-3 h-3" />
            12222
          </span>
          <span class="hover:( cursor-pointer text-green-600)"> 回复 </span>
        </p>

        <!-- 回复块 -->
        <div class="flex flex-col gap-2">
          <div class="flex gap-2 mb-3">
            <div>
              <n-avatar round :size="32" src="/src/assets/material/avatar.png" />
            </div>
            <div class="w-full flex flex-col self-center space-y-1">
              <p class="text-red-500 text-xs">母猪的产后护理砖家 </p>
              <p class="text-sm">就这个up我不敢催更，我怕他顺着网线过来打我[嫌弃]</p>
              <div class="text-gray-400 text-xs space-x-4">
                <n-time />
                <span class="cursor-pointer hover:(text-green-600)">
                  <i-fa-thumbs-o-up v-if="false" class="self-center align-middle w-3 h-3" />
                  <i-fa-thumbs-up v-else class="self-center align-middle text-green-600 w-3 h-3" />
                  12222
                </span>
                <span class="hover:( cursor-pointer text-green-600)"> 回复 </span>
              </div>
            </div>
          </div>

          <div class="flex gap-2 mb-3">
            <div>
              <n-avatar round :size="32" src="/src/assets/material/avatar.png" />
            </div>
            <div class="w-full flex flex-col self-center space-y-1">
              <p class="text-red-500 text-xs">母猪的产后护理砖家 </p>
              <p class="text-sm">就这个up我不敢催更，我怕他顺着网线过来打我[嫌弃]</p>
              <p class="text-gray-400 text-xs space-x-4">
                <n-time />
                <span class="cursor-pointer hover:(text-green-600)">
                  <i-fa-thumbs-o-up v-if="false" class="self-center align-middle w-3 h-3" />
                  <i-fa-thumbs-up v-else class="self-center align-middle text-green-600 w-3 h-3" />
                  12225
                </span>
                <span class="hover:( cursor-pointer text-green-600)"> 回复 </span>
              </p>
            </div>
          </div>
        </div>

        <!-- 隐藏回复块 -->
        <div class="flex gap-3">
          <div>
            <n-avatar round :size="48" src="/src/assets/material/avatar.png" />
          </div>
          <div class="w-full flex gap-3 h-full">
            <n-input
              type="textarea"
              round
              :autosize="{
                minRows: 1,
                maxRows: 3,
              }"
              placeholder="发一条建议或评论"
              class="w-full bg-gray-100"
            >
            </n-input>
            <n-button type="primary">发布</n-button>
          </div>
        </div>
        <n-divider />
      </div>
    </div>
  </div>
</template>
<script setup lang="ts"></script>
<style scoped></style>
