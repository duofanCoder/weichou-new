<template>
  <n-upload
    :default-file-list="previewFileList"
    :custom-request="customRequest"
    list-type="image-card"
    :max="max"
    v-model:file-list="fileList"
  />
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchFileUpload } from '@/service';
  import { UploadCustomRequestOptions, UploadFileInfo } from 'naive-ui';
  import { ref, watch } from 'vue';

  const { max } = defineProps({
    filePath: {
      type: String,
      default: '',
    },
    max: {
      type: Number,
      default: 1,
    },
    filePathArr: {
      type: Array,
      default: new Array<string>(),
    },
  });

  const fileMap = new Map();
  const tmpFileArr = ref<UploadFileInfo[]>([]);
  const emit = defineEmits(['update:filePath', 'update:filePathArr']);
  const fileList = ref<UploadFileInfo[]>([]);
  const previewFileList = ref<UploadFileInfo[]>([]);
  const customRequest = async ({
    file,
    data,
    headers,
    withCredentials,
    action,
    onFinish,
    onError,
    onProgress,
  }: UploadCustomRequestOptions) => {
    const formData = new FormData();
    if (data) {
      Object.keys(data).forEach((key) => {
        formData.append(key, data[key as keyof UploadCustomRequestOptions['data']]);
      });
    }
    formData.append('files', file.file as File);
    const res = await fetchFileUpload(formData);
    if (max === 1 && res.data != null) {
      const file: Dto.File = res.data[0];
      emit('update:filePath', file.fullFilePath);
    } else {
      if (res.data != null) {
        res.data.forEach((item) => {
          fileMap.set(file.id, item.fullFilePath);
        });
      }
    }
    onFinish();
  };
  const previewImageUrlRef = ref('');
  const showModalRef = ref(false);
  const handlePreview = (file: UploadFileInfo) => {
    const { url } = file;
    previewImageUrlRef.value = url as string;
    showModalRef.value = true;
  };
  const updateFileList = (list: UploadFileInfo[]) => {
    tmpFileArr.value = new Array();
    list.forEach((file) => {
      tmpFileArr.value.push(fileMap.get(file.id));
    });
    emit('update:filePathArr', tmpFileArr);
  };

  watch(fileList, (newList) => {
    updateFileList(newList);
  });
</script>
<style scoped></style>
