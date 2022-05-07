<template>
  <!-- <Love :id="campaign.id" /> -->

  <div class="flex relative rounded-xl bg-gray-100 flex-col">
    <!-- h-144px w-256px 比例 -->
    <img
      class="mb-3 h-100% w-full rounded-t-xl cursor-pointer"
      :src="campaign.cardImg"
      @click="router.routerPush('campaign')"
    />

    <!-- <Love class="absolute right-3 top-3" /> -->
    <div class="px-3 py-2">
      <n-ellipsis
        :line-clamp="1"
        class="text-lg font-medium cursor-pointer"
        @click="router.routerPush('campaign')"
      >
        {{ campaign.title }}
      </n-ellipsis>
      <n-ellipsis
        :tooltip="false"
        :line-clamp="4"
        class="text-sm font-light tracking-wide h-91px leading-relaxed"
      >
      {{ campaign.description }}
      </n-ellipsis>

      <div class="flex items-center space-x-2 my-1">
        <n-avatar round :size="25" src="/src/assets/material/avatar.png" />
        <span class="text-sm text-true-gray-400">多凡物联网智慧团队</span>
      </div>
      <div class="mb-2">
      <span class="font-semibold text-xl text-green-600"> ￥{{ campaign.raisedMoney }} </span>
        <n-progress
          type="line"
          status="success"
          :percentage="raisedPercentage"
          :indicator-placement="'inside'"
          processing
        />
      </div>
      <div class="mt-2 flex flex-wrap justify-between">
        <div>
          <i-el-group class="align-middle mr-1 text-xl text-green-600" />
          <span class="align-middle mr-2">64 支持者</span>
        </div>

        <div>
          <i-mdi-clock-time-eight class="align-middle text-gray-400 mr-1" />
           <n-time :time="campaign.deadline" :to="Date.now()" type="relative" class="align-middle" />
        </div>
        <!-- <div v-else class="flex text-xs text-gray-400 self-center">
          <i-icomoon-free-fire class="align-middle mr-1" />
          <span class="align-middle">目标达成</span>
        </div> -->
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { Love } from '@/components';
  import { useRouterPush } from '@/router/router';

  const { campaign } = defineProps({
    campaign: {
      type: Object as () => Dto.CampaignCard,
      required: true,
      default: () => {},
    },
  });

  const router = useRouterPush();
  const raisedPercentage = (campaign?.raisedMoney / campaign.goalRaisedMoney) * 100;
</script>
<style scoped></style>
