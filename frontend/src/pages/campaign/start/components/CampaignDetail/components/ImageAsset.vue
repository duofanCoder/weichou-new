<template>
  <n-upload
    :default-file-list="previewFileList"
    :custom-request="customRequest"
    list-type="image-card"
    :max="10"
    @remove="imageRemove"
    v-model:file-list="fileList"
  />
</template>
<script setup lang="ts">
  import { AssetType } from '@/model';
  import { Dto } from '@/model/object/dto';
  import { fetchFileUpload, fetchRemoveAsset, fetchSaveAsset } from '@/service';
  import { UploadCustomRequestOptions, UploadFileInfo } from 'naive-ui/lib/upload';
  import { ref } from 'vue';
  const props = defineProps({
    campaignDetailId: {
      type: Number,
    },
  });
  const showModalRef = ref(false);
  const previewFileList = ref<UploadFileInfo[]>([]);
  const fileList = ref<UploadFileInfo[]>([]);
  const imageAssetList = ref<Array<Partial<Dto.Asset>>>(Array(0));

  const customRequest = async ({
    file,
    data,
    headers,
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
    if (res.data != null) {
      res.data.forEach((item) => {
        console.log(item.fullFilePath);
        const d: Partial<Dto.Asset> = {
          url: item.fullFilePath,
          assetType: AssetType.IMAGE,
          campaignDetailId: props.campaignDetailId,
        };
        fetchSaveAsset(d).then((res) => {
          if (res.data != null) {
            imageAssetList.value.push(res.data);
          }
        });
      });
    }
    onFinish();
  };
  const imageRemove = (options: { file: UploadFileInfo; fileList: Array<UploadFileInfo> }) => {
    const index = options.fileList.findIndex((item) => item.id == options.file.id);
    const aid = imageAssetList.value.at(index)?.id;
    console.log(aid);
    if (aid !== undefined) {
      fetchRemoveAsset([aid]);
    }
    delete imageAssetList.value[index];
  };
</script>
<style scoped></style>
