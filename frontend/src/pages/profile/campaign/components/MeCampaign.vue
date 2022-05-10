<template>
  <div class="border py-5 mt-6 rounded-xl bg-white space-y-3">
    <p class="text-2xl px-6">您发起的活动</p>
    <p class="px-6 tracking-widest mb-4">活动可不是简简单单的发起就会成功的。</p>

    <div
      v-for="item in meCampaignIntroList"
      :key="item.id"
      class="flex text-base rounded-lg mx-6 hover:(bg-true-gray-100 )"
    >
      <img
        @click="router.push({ name: 'campaign-index', query: { campaignId: item.id } })"
        :src="item.posterImg"
        class="my-auto w-60 h-160px object-cover rounded-lg cursor-pointer"
        alt=""
      />
      <div class="inline-flex flex-col flex-1 ml-3 pt-2 py-2">
        <p
          @click="router.push({ name: 'campaign-index', query: { campaignId: item.id } })"
          class="text-xl cursor-pointer"
          >{{ item.title }}</p
        >
        <n-ellipsis :tooltip="false" :line-clamp="4" class="text-base text-gray-500 mt-2">
          <p class="h-96px"> {{ item.description }} </p>
        </n-ellipsis>
        <n-space justify="space-between" align="center" class="mt-auto">
          <div class="my-auto flex inline-flex space-x-3">
            <div class="my-auto">
              <n-tag v-if="new Date(item.endTime) > new Date()" type="success"> 进行中 </n-tag>
              <n-tag v-else> 已结束 </n-tag>
            </div>
            <n-time :time="new Date(item.createTime)" class="self-center" format="yy年M月d日h时" />
          </div>
          <n-space>
            <n-popconfirm :show-icon="false" @positive-click="removeMeCampaign(item.id)">
              <template #trigger>
                <n-button>删除</n-button>
              </template>
            </n-popconfirm>
            <n-button
              @click="
                router.push({
                  name: 'profile-perk',
                  query: { campaginId: item.campaignDetailId },
                })
              "
              >回报</n-button
            >
            <n-button
              class="mr-3"
              @click="
                router.push({ name: 'journal-index', query: { campaginId: item.campaignDetailId } })
              "
              >更新</n-button
            >
          </n-space>
        </n-space>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchGetMeCampaignIntro, fetchRemoveCampaignIntro } from '@/service';
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';

  const meCampaignIntroList = ref<Array<Dto.CampaignIntro>>();
  fetchGetMeCampaignIntro().then((res) => {
    if (res.data != null) {
      meCampaignIntroList.value = res.data;
    }
  });

  const removeMeCampaign = (id: number) => {
    //   没开启
    fetchRemoveCampaignIntro([id]);
    meCampaignIntroList.value = meCampaignIntroList.value?.filter((item) => item.id != id);
  };
  const router = useRouter();
</script>
<style scoped></style>
