<template>
  <div class="flex flex-col flex-1 mx-auto max-w-235">
    <div class="text-center">
      <p class="font-medium text-3xl mt-12"> 支持 </p>
      <p class="mt-3 text-lg">查看您支持过的活动记录。</p>
    </div>

    <div class="border py-5 mt-6 rounded-xl bg-white">
      <p class="text-2xl px-6">记录</p>
      <p class="mt-2 px-6 tracking-widest mb-4">使用微筹的其他用户可能会看到部分信息。</p>
      <!-- w-50 -->
      <div class="flex flex-col w-full pb-17 font-semibold gap-4 md:(gap-7)">
        <img class="mx-auto w-max-600px h-auto" src="@/assets/img/surr-404.png" />
      </div>
      <n-table
        v-if="payOrderList?.length != 0"
        class="px-6"
        :bordered="false"
        :single-line="false"
        size="large"
      >
        <thead>
          <tr>
            <th>日期</th>
            <th>活动</th>
            <th>金额</th>
            <th>可见</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in payOrderList" :key="item.id">
            <td>
              <n-time :time="new Date(item.createTime)" format="yyyy年M月d日 hh:mm" />
            </td>
            <td class="hover:( underline underline-offset-2 cursor-pointer)">{{
              item.campaignIntro.title
            }}</td>
            <td>￥ {{ item.perk.price }}</td>

            <td>
              <n-switch v-model:value="active" size="large">
                <template #checked>
                  <i-ph-eye-bold />
                </template>
                <template #unchecked>
                  <i-ph-eye-closed-bold />
                </template>
              </n-switch>
            </td>
          </tr>
        </tbody>
      </n-table>

      <div
        v-if="payOrderList?.length == 0"
        class="flex flex-col w-full pb-17 font-semibold gap-4 md:(gap-7)"
      >
        <img class="mx-auto w-max-600px h-auto" src="@/assets/img/surr-404.png" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchQueryPayOrder } from '@/service/api/payOrder';
  import { ref } from 'vue';
  import { useRoute } from 'vue-router';
  const active = ref(false);

  const payOrderList = ref<Array<Dto.PayOrder>>();

  const route = useRoute();
  const detailId = Number(route.query.detailId);
  fetchQueryPayOrder({ pageSize: 999, detailId: detailId }).then((res) => {
    if (res.data != null) {
      payOrderList.value = res.data?.data;
    }
  });
</script>

<style scoped></style>
