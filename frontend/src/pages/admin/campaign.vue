<template>
  <div class="flex flex-col">
    <div class="text-3xl my-4"> 用户管理 </div>
    <n-space justify="start" align="center">
      <div>用户名称</div>
      <n-input placeholder="请输入关键字搜索"></n-input>
      <n-button type="primary" @click="onSearch"> 查询 </n-button>
    </n-space>
    <n-space justify="start" align="center">
      <n-button type="success" @click="addNew">添加</n-button>
      <n-button type="warning" @click="updateCompetitionFunc">修改</n-button>
      <n-button type="error" @click="removeFunc">删除</n-button>
      <n-button type="primary" @click="reload">刷新</n-button>
    </n-space>
    <!-- 
    <n-data-table
      ref="table"
      :columns="columns"
      :data="data"
      :pagination="pagination"
      :row-key="(row) => row.id"
      @update:checked-row-keys="handleCheck"
    /> -->

    <div class="mb-20"> </div>
  </div>
</template>
<script setup lang="ts">
  const campaignIntroList = ref<Array<Dto.CampaignIntro>>();
  fetchGetMeCampaignIntro().then((res) => {
    if (res.data != null) {
      campaignIntroList.value = res.data;
    }
  });

  const removeMeCampaign = (id: number) => {
    //   没开启
    fetchRemoveCampaignIntro([id]);
    campaignIntroList.value = campaignIntroList.value?.filter((item) => item.id != id);
  };
  const reload = () => {
    console.log('reload');
  };
</script>
<style scoped></style>
