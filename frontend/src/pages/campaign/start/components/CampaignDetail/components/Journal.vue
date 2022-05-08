<script setup lang="ts">
  import Editor, { ToolbarTips } from 'md-editor-v3';
  import 'md-editor-v3/lib/style.css';
  import { fetchFileUpload, fetchSaveJournal } from '@/service';
  import { ref } from 'vue';
  import { Dto, JournalType } from '@/model';
  const toolbars: (keyof ToolbarTips)[] = [
    'bold',
    'underline',
    'italic',
    'strikeThrough',
    'title',
    'quote',
    'unorderedList',
    'orderedList',
    '-',
    'link',
    'image',
    '-',
    'revoke',
    'next',
    'save',
    'pageFullscreen',
    'fullscreen',
    'preview',
  ];

  const { campaignDetailId } = defineProps({
    campaignDetailId: {
      type: Number,
    },
  });
  const journalRef = ref<Partial<Dto.Journal>>({
    content: '',
    campaignDetailId: campaignDetailId,
    journalType: JournalType.STORY,
  });
  defineExpose({
    journalRef,
  });
  const onUploadImg = async (files: Array<File>, callback: (urls: string[]) => void) => {
    const res = await Promise.all(
      files.map((file) => {
        return new Promise((rev, rej) => {
          const form = new FormData();
          form.append('files', file);
          fetchFileUpload(form).then((res) => {
            rev(res.data?.at(0));
          });
        });
      })
    );
    callback(
      res.map((item: any) => {
        return item.fullFilePath;
      })
    );
  };
  const onSave = () => {
    fetchSaveJournal(journalRef.value).then((res) => {
      if (res.data != null) {
        journalRef.value.id = res.data.id;
        window.$message.success('内容已保存!');
      }
    });
  };
</script>
<style scoped></style>
<template>
  <editor
    v-model="journalRef.content"
    v-on:save="onSave"
    :toolbars="toolbars"
    v-on:upload-img="onUploadImg"
  ></editor>
</template>
