<template>
  <div>
    <md-editor v-model="content" :previewOnly="true" />
  </div>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import MdEditor from 'md-editor-v3';
  import 'md-editor-v3/lib/style.css';
  import { PropType, ref, watch } from 'vue';
  const props = defineProps({
    story: {
      type: Object as PropType<Dto.Journal>,
    },
  });

  const content = ref(props.story?.content);
  watch(props, (n) => {
    if (n.story?.content != undefined) {
      content.value = n.story?.content;
    }
  });
</script>
<style scoped></style>
