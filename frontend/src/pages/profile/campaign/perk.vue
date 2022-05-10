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

      <n-table class="px-6" :bordered="false" :single-line="false" size="large">
        <thead>
          <tr>
            <th>日期</th>
            <th>活动</th>
            <th>金额</th>
            <th>可见</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <n-time :time="4444221122122" format="yyyy年M月d日" />
            </td>
            <td class="hover:( underline underline-offset-2 cursor-pointer)">无敌陆游器-海盐版</td>
            <td>￥ 99999</td>

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
    </div>
  </div>
</template>

<script setup lang="ts">
  import { ref } from 'vue';
  const active = ref(false);
</script>

<style scoped></style>
