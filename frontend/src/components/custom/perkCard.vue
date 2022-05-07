<template>
  <n-card hoverable class="mb-3">
    <div class="font-bold text-base mb-2"> 花鸟云石 </div>
    <div class="text-gray-400 h-40">
      《野花》+《鸟类》+《云和天气》+《矿物和岩石》（刷边+作者亲签） “来自作者的野外科考记忆集”*1
      限量版藏书票*1（编号201-1000） 后续解锁产品 偏远地区需补运费，港澳台及海外地区暂不发货
    </div>
    <img class="rounded-xl object-contain h-auto w-auto" src="/src/assets/material/ipad.jpg" />
    <div class="flex justify-between mt-2">
      <p class="font-bold"> 支持￥50</p>
      <p class="text-gray-400">已支持 569 次</p>
    </div>
    <n-button type="primary" class="w-full mt-2">支持</n-button>
  </n-card>
</template>
<script setup lang="ts">
  defineProps({
    id: { type: Number },
  });
</script>
<style scoped></style>
