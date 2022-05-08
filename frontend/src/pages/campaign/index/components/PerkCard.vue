<template>
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
    <n-button type="primary" class="w-full mt-2">支持</n-button>
  </n-card>
</template>
<script setup lang="ts">
  defineProps({
    perk: {
      type: Object,
    },
  });
</script>
<style scoped></style>
