<template>
  <p class="text-3xl text-green-500 mb-2">内容</p>
  <p class="text-gray-400 text-base font-thin mb-4">
    添加视频或图像以显示在活动页面的顶部。有视频的活动比没有视频的活动提高2000%。将视频保留2-3分钟。
    你的活动被感兴趣的人发现了，他希望通过下面的内容详细的了解你的活动，千万不要遗漏你的活动最出彩的地方。
  </p>
  <n-form>
    <n-form-item path="sources">
      <n-tabs type="line" default-value="video" size="large">
        <n-tab-pane name="video" tab="视频" display-directive="show">
          <p class="text-gray-400 font-thin mb-4">
            输入Bilibili或Video
            URL以显示在您的活动页面顶部。确保你的视频在Bilibili或Vimeo上启用了闭路字幕。
          </p>
          <VideoAsset :campaignDetailId="props.campaignDetailId" ref="videoAssetRef" />
        </n-tab-pane>
        <n-tab-pane name="image" tab="图片" display-directive="show">
          <ImageAsset :campaignDetailId="props.campaignDetailId" ref="imageAssetRef" />
        </n-tab-pane>
      </n-tabs>
    </n-form-item>
    <n-form-item label="活动故事" class="mb-12">
      <div class="w-full h-xl">
        <Journal :campaignDetailId="props.campaignDetailId" ref="journalRef" />
      </div>
    </n-form-item>
    <n-form-item label="常见问题 & FQA" path="faq">
      <Faq :campaignDetailId="props.campaignDetailId" ref="faqRef" />
    </n-form-item>
  </n-form>
</template>

<script setup lang="ts">
  import { ref, watch } from 'vue';
  import { Dto, JournalType } from '@/model';
  import { Faq, VideoAsset, ImageAsset, Journal } from './components';

  const videoAssetRef = ref(null);
  const imageAssetRef = ref(null);
  const journalRef = ref(null);
  const faqRef = ref(null);

  const props = defineProps({
    campaignDetailId: {
      type: Number,
    },
  });
  defineExpose({
    videoAssetRef,
    imageAssetRef,
    journalRef,
    faqRef,
  });
</script>

<style scoped></style>
