<template>
  <div v-if="props.campaignIntro != undefined" class="flex flex-col space-y-2 px-2 sm:( max-w-150)">
    <p class="font-bold text-2xl md:(text-3xl) my-3">{{ props.campaignIntro.title }} </p>
    <n-ellipsis
      class="text-sm md:(text-base)"
      :line-clamp="5"
      expand-trigger="click"
      :tooltip="false"
    >
      {{ props.campaignIntro.description }}
    </n-ellipsis>
    <div class="flex flex-col">
      <p class="py-2 text-2xl md:( text-3xl) text-green-600">
        已筹￥{{ props.campaignIntro.currentMoney }}</p
      >
      <n-progress
        type="line"
        status="success"
        :percentage="
          Number(
            ((props.campaignIntro.currentMoney / props.campaignIntro.totalMoney) * 100).toFixed(2)
          )
        "
        :indicator-placement="'inside'"
      />
      <div class="flex py-2 text-base justify-between text-sm md:( text-base)">
        <span> 目标金额￥{{ props.campaignIntro.totalMoney }}</span>
        <div>
          <i-el-group class="align-middle mr-1 text-xl text-green-600" />
          <span class="align-middle mr-2">{{ props.campaignIntro.supportCount }} 支持者</span>
        </div>
      </div>
      <div class="flex justify-between">
        <div class="flex justify-center">
          <!-- <Love :id="props.campaignIntro.id" :detail="true" /> -->
        </div>
        <div class="space-x-3 self-center">
          <i-uiw-weixin class="text-green-500 hover:( cursor-pointer) align-middle" />
          <i-mdi-link-variant class="text-green-500 hover:( cursor-pointer) align-middle" />
        </div>
      </div>
    </div> </div
></template>

<script setup lang="ts">
  import { Dto } from '@/model';
  import { PropType } from 'vue';

  // 添加默认值
  const props = defineProps({
    campaignIntro: Object as PropType<Dto.CampaignIntro>,
  });
</script>
<style scoped></style>
