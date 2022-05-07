<template>
  <div class="flex flex-col">
    <div class="flex flex-col justify-center items-center h-60 w-full bg-dark-50 text-white">
      <p class="text-3xl font-bold"> 创造性的作品 </p>
      <p class="mt-3 text-sm px-5 md:(px-0)"
        >对于各地的创意寻求者,欢迎选择你感兴趣的项目进行,他们正期待着你的支持。</p
      >
    </div>
    <div class="flex gap-24 mt-16 w-full justify justify-center">
      <div class="flex-col font-semibold hidden sm:(flex)">
        <div class="flex flex-col space-y-3 text-base p-2">
          <p class="mb-4 text-lg">分类</p>
          <n-button text class="inline-block">所有</n-button>
          <n-button text class="inline-block">家</n-button>
          <n-button text class="inline-block">旅行 & 户外</n-button>
          <n-button text class="inline-block">科技</n-button>
          <n-button text class="inline-block">健康</n-button>
          <n-button text class="inline-block">出版物</n-button>
        </div>

        <div class="mt-5 p-2">
          <p class="mb-6 text-xl font-semibold">活动状态</p>

          <n-radio-group name="campaignState">
            <n-space vertical>
              <n-radio :value="0" name="1"> 所有 </n-radio>
              <n-radio :value="1" name="2"> 进行中 </n-radio>
              <n-radio :value="2" name="3"> 已结束 </n-radio>
            </n-space>
          </n-radio-group>
        </div>
      </div>

      <div class="max-w-140 px-2 lg:(max-w-210)">
        <div class="w-full flex gap-4 sm:(gap-8 px-0)">
          <n-input
            size="large"
            class="text-xl py-1 sm:(w-max-50)"
            type="text"
            placeholder="搜索你感兴趣的活动"
          >
            <template #prefix>
              <i-ic-baseline-search class="w-6 h-6" />
            </template>
          </n-input>
          <n-button class="h-auto w-28">搜索</n-button>
        </div>
        <div class="sm:( hidden )">
          <div class="flex flex-wrap mb-2 gap-3 text-base py-2">
            <span class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)">所有</span>
            <span class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)">家</span>
            <span class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)">旅行 & 户外</span>
            <span class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)">科技</span>
            <span class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)">健康</span>
            <span class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)">出版物</span>
          </div>

          <n-radio-group name="campaignState">
            <n-space>
              <n-radio :value="0" name="1"> 所有 </n-radio>
              <n-radio :value="1" name="2"> 进行中 </n-radio>
              <n-radio :value="2" name="3"> 已结束 </n-radio>
            </n-space>
          </n-radio-group>
        </div>
        <n-grid cols="1 s:2 m:3 " responsive="screen" :x-gap="12" :y-gap="8">
          <template v-for="item in [0, 1, 2, 3, 4, 5, 6, 7, 8]" :key="item">
            <n-grid-item>
              <CampaignCard :campaign="campaignCardData" class="mt-4 xs:mx-auto" />
            </n-grid-item>
          </template>
        </n-grid>
        <div
          class="flex text-base h-30 items-center justify-center py-17 font-semibold gap-4 md:(gap-7)"
        >
          <div class="flex pl-2 py-1 rounded-xl hover:(bg-gray-200 cursor-pointer)">
            <i-ic-baseline-arrow-back-ios />
          </div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">1</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">2</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">3</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">4</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">5</div>
          <div class="flex pl-2 py-1 rounded-xl hover:( bg-gray-200 cursor-pointer)">
            <i-ic-baseline-arrow-forward-ios />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { CampaignCard } from '@/components';

  const campaignCardData: Dto.CampaignCard = {
    id: 1,
    title: '智能显示充屏充电宝-万能',
    description:
      '这是一个不仅仅可以充电的充电宝，他还带有一块超清1080P显示屏，当你想要看电影的时候，又想要打游戏，那么你就可以分屏显示，让笔记本显示器显示游戏画面，充电宝这时就可以看电影了，两不误。',
    categaryName: '电子产品',
    cardImg: 'src/assets/material/project-card-1.jpg',
    raisedMoney: 8560,
    goalRaisedMoney: 10000,
    deadline: new Date(2022, 3),
    isAttention: false,
    isInProgress: true,
  };
</script>
<style scoped></style>
