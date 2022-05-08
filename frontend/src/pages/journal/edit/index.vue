<script setup lang="ts">
  import Editor, { ToolbarTips } from 'md-editor-v3';
  import 'md-editor-v3/lib/style.css';
  import { fetchFileUpload, fetchGetJournal, fetchSaveJournal } from '@/service';
  import { ref, watch } from 'vue';
  import { Dto, JournalType } from '@/model';
  import Upload from '@/components/common/Upload.vue';
  import { showErrorMsg } from '@/utils/service/msg';
  import { useRoute, useRouter } from 'vue-router';
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

  const router = useRouter();
  const route = useRoute();
  const campaginId = Number(route.query.campaginId);

  const journalId = ref(Number(route.query.journalId));

  const journalRef = ref<Partial<Dto.Journal>>({
    content: '',
    campaignDetailId: campaginId,
    journalType: JournalType.JOURNAL,
  });
  fetchGetJournal(journalId.value).then((res) => {
    Object.assign(journalRef.value, res.data);
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
  const showModal = ref(false);
  const onSave = () => {
    if (journalRef.value.id == undefined) {
      showModal.value = true;
    } else {
      fetchSaveJournal(journalRef.value).then((res) => {
        if (res.data != null) {
          journalRef.value.id = res.data.id;
          window.$message.success('内容已保存!');
        }
      });
    }
  };

  const saveJournal = () => {
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
  <div class="flex">
    <div class="mx-auto max-w-180 h-full">
      <n-space @click="showModal = true">
        <n-button type="primary">发布</n-button>
      </n-space>
      <editor
        v-model="journalRef.content"
        v-on:save="onSave"
        :toolbars="toolbars"
        v-on:upload-img="onUploadImg"
      ></editor>
    </div>

    <n-Modal v-model:show="showModal">
      <n-card
        style="width: 600px"
        title="更新动态"
        :bordered="false"
        size="huge"
        role="dialog"
        aria-modal="true"
      >
        <n-form
          :model="journalRef"
          label-placement="left"
          label-width="auto"
          require-mark-placement="right-hanging"
          :style="{
            maxWidth: '640px',
          }"
        >
          <n-form-item label="标题" path="title">
            <n-input v-model:value="journalRef.title" placeholder="请输入" />
          </n-form-item>
          <n-form-item label="描述" path="description">
            <n-input
              v-model:value="journalRef.description"
              placeholder="请输入"
              type="textarea"
              :autosize="{
                minRows: 3,
                maxRows: 5,
              }"
            />
          </n-form-item>
          <n-form-item label="封面" path="posterImg">
            <Upload v-model:filePath="journalRef.posterImg" :max="1" />
          </n-form-item>
          <div>
            <n-button @click="saveJournal">保存</n-button>
          </div>
        </n-form>
      </n-card>
    </n-Modal>
  </div>
</template>
