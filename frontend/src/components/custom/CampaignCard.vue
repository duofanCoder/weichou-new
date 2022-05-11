<template>
  <!-- <Love :id="campaign.id" /> -->

  <div
    ref="cardRef"
    class="flex relative rounded-xl h-full bg-gray-100 flex-col hover:(animate-pulse )"
  >
    <!-- 40% -->
    <div>
      <img
        class="mb-3 h-100% w-full rounded-t-xl cursor-pointer"
        :src="campaign.posterImg"
        :style="{ height: cardSize.height.value * 0.4 + 'px' }"
        @click="router.push({ name: 'campaign-index', query: { campaignId: campaign.id } })"
      />
    </div>

    <div class="px-3 py-2">
      <n-ellipsis
        :line-clamp="1"
        class="text-lg font-medium cursor-pointer"
        @click="router.push({ name: 'campaign-index', query: { campaignId: campaign.id } })"
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
        <n-avatar round :size="25" :src="campaign.owner.avatar" />
        <span class="text-sm text-true-gray-400">{{ campaign.owner.nickname }}</span>
      </div>
      <div class="mb-2">
        <span class="font-semibold text-xl text-green-600"> ￥{{ campaign.currentMoney }} </span>
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
          <n-time
            :time="new Date(campaign.endTime)"
            :to="Date.now()"
            type="relative"
            class="align-middle"
          />
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
  import { Dto } from '@/model';
  import { useElementSize } from '@vueuse/core';
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';

  const cardRef = ref(null);
  const cardSize = useElementSize(cardRef);

  const { campaign } = defineProps({
    campaign: {
      type: Object as () => Dto.CampaignIntro,
      required: true,
      default: () => {},
    },
  });

  const router = useRouter();
  const raisedPercentage = Number(
    ((campaign?.currentMoney / campaign.totalMoney) * 100).toFixed(2)
  );
</script>
<style scoped></style>
