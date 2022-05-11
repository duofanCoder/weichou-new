<template>
  <div>
    <n-card hoverable class="mb-3" v-if="props.perk != undefined">
      <div class="font-bold text-base mb-2"> {{ props.perk.title }} </div>
      <div class="text-gray-400 h-35">
        {{ props.perk.description }}
      </div>
      <img class="rounded-xl object-cover h-115px w-204px" :src="props.perk.posterImg" />
      <div class="flex justify-between mt-2">
        <p class="font-bold"> 支持￥{{ props.perk.price }}</p>
        <!-- <p class="text-gray-400">已支持 569 次</p> -->
      </div>
      <n-button type="primary" class="w-full mt-2" @click="clickSupportEvent">支持</n-button>
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
  import { useRouterPush } from '@/router/router';
  import { fetchSavePayOrder } from '@/service/api/payOrder';
  import { useUserStore } from '@/store';
  import { PropType, ref } from 'vue';
  import { useRoute } from 'vue-router';

  const props = defineProps({
    perk: {
      type: Object as PropType<Dto.Perk>,
    },
  });

  const userStore = useUserStore();
  const route = useRoute();
  const routerPush = useRouterPush();

  const showModal = ref(false);

  const clickSupportEvent = () => {
    if (!userStore.isLogin) {
      window.$message.info('请先登录!');
      routerPush.toLogin(route.fullPath);
      return;
    }
    showModal.value = true;
  };
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
