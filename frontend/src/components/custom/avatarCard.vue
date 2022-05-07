<template>
  <n-card hoverable>
    <div class="flex items-center mb-2 hover:(cursor-pointer)">
      <n-avatar round :size="45" src="/src/assets/material/avatar.png" />
      <div class="flex flex-col">
        <p class="font-extrabold hover:(cursor-pointer)">多凡</p>
        <div><span>3个活动 </span>| <span>中国,上海</span></div>
      </div>
    </div>
    <n-ellipsis class="w-auto" expand-trigger="click" line-clamp="4" :tooltip="false">
      本教学团队的教师在教学与科研上，力求让学生将理论与实践结合，紧跟网络技术的发展步伐，让学生学有所用，为社会培养真正有用的人才。教师们积极参于教学改革工作，在多年的教学工作中，注重素质教育，将考试与实际操作相结合，采取“无纸”考试和实践动手操作考试，取得了较好的效果。并对课程实行统一细化管理，统一教学内容，统一课程标准，统一命题考试。
    </n-ellipsis>
  </n-card>
</template>
<script setup lang="ts">
  defineProps({
    id: {
      type: Number,
    },
  });
</script>
<style scoped></style>
