<template>
  <div>
    <n-card hoverable class="mb-3">
      <div class="font-bold text-base mb-2"> {{ perk.title }} </div>
      <div class="text-gray-400 h-40">
        {{ perk.description }}
      </div>
      <img class="rounded-xl object-contain h-auto w-auto" :src="perk.posterImg" />
      <div class="flex justify-between mt-2">
        <p class="font-bold"> 支持￥{{ perk.price }}</p>
        <!-- <p class="text-gray-400">已支持 569 次</p> -->
      </div>
      <n-button type="primary" class="w-full mt-2" @click="showModal = true">支持</n-button>
    </n-card>
    <n-modal
      v-model:show="showModal"
      :mask-closable="false"
      preset="dialog"
      title="支付"
      positive-text="确认"
      negative-text="算了"
      @positive-click="onPositiveClick"
      @negative-click="onNegativeClick"
    >
      <n-image src="/src/assets/img/wechatPacket.jpg" />
    </n-modal>
  </div>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchSavePayOrder } from '@/service/api/payOrder';
  import { PropType, ref } from 'vue';

  const props = defineProps({
    perk: {
      type: Object as PropType<Dto.Perk>,
    },
  });

  const showModal = ref(false);
  const onNegativeClick = () => {
    showModal.value = false;
  };
  const onPositiveClick = () => {
    fetchSavePayOrder({
      perkId: props.perk?.id,
      campaignDetailId: props.perk?.campaignDetailId,
    }).then((res) => {
      if (res.data != null) {
        window.$message.success('支付成功！');
        showModal.value = false;
      }
    });
  };
</script>
<style scoped></style>
