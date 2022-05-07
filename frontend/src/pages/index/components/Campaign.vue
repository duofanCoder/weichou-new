<template>
  <div class="sm:(px-x)">
    <!-- 标题 -->
    <div class="flex flex-row mt-4 w-auto mb-10 text-2xl font-light font-light"> 热门项目 </div>
    <!-- 内容 -->
    <n-grid x-gap="10 s:25 m:30 l:42 xl:42 2xl:42" responsive="screen" cols="1 s:3" y-gap="42">
      <template v-for="item in 6" :key="item">
        <n-gi>
          <CampaignCard :campaign="campaignCardData" />
        </n-gi>
      </template>
    </n-grid>
    <div class="flex mt-12">
      <n-button class="rounded-xl mx-auto w-80 py-6 font-extrabold text-base">发现更多</n-button>
    </div>
  </div>
</template>
<script setup lang="ts">

  const campaignCardData: Dto.CampaignCard = {
    id: 1,
    title: '智能显示充屏充电宝-万能',
    description:
      '这是一个不仅仅可以充电的充电宝，他还带有一块超清1080P显示屏，当你想要看电影的时候，又想要打游戏，那么你就可以分屏显示，让笔记本显示器显示游戏画面，充电宝这时就可以看电影了，两不误。',
    categaryName: '电子产品',
    cardImg: 'src/assets/material/ipad.jpg',
    raisedMoney: 8560,
    goalRaisedMoney: 10000,
    deadline: new Date(2022, 4, 2),
    isAttention: false,
    isInProgress: true,
  };
</script>
<style scoped></style>
