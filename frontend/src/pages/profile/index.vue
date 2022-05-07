<template>
  <div class="flex flex-col flex-1 w-full mx-auto justify-center max-w-235">
    <div class="text-center">
      <p class="font-medium text-3xl mt-12"> 个人信息 </p>
      <p class="mt-3 text-lg">设置您在微筹里的个人信息和偏好设置</p>
    </div>

    <div class="border py-5 mt-6 rounded-xl bg-white">
      <p class="text-2xl px-6">基本信息</p>
      <p class="mt-2 px-6 tracking-widest mb-4">使用微筹的其他用户可能会看到部分信息。</p>
      <!-- w-50 -->

      <div
          @click="showChangeAvatar = true"
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer )"
      >
        <div class="inline-flex self-center text-gray-500 w-50">照片</div>
        <div class="inline-flex self-center align-middle">更改照片可帮助您个性化您的账号</div>
        <div class="ml-auto items-center inline-flex">
          <n-avatar :size="56" round :src="userInfo.avatar"></n-avatar>
        </div>
      </div>

      <!-- <router-link to=""></router-link> -->
      <router-link
          :to="{ name: 'profile-modify', query: { type: 'name' } }"
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer )"
      >
        <div class="inline-flex self-center text-gray-500 w-50">昵称</div>
        <div class="inline-flex self-center align-middle">{{ userInfo.nickname }}</div>
        <div class="ml-auto items-center inline-flex"
        >
          <i-grommet-icons-form-next class="w-7 h-7"/>
        </div>
      </router-link>

      <router-link
          :to="{ name: 'profile-modify', query: { type: 'birth' } }"
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer)"
      >
        <div class="inline-flex self-center text-gray-500 w-50">生日</div>
        <div class="inline-flex self-center align-middle">{{ userInfo.birth }}</div>
        <div class="ml-auto items-center inline-flex">
          <i-grommet-icons-form-next class="w-7 h-7"/>
        </div>
      </router-link>

      <router-link
          :to="{ name: 'profile-modify', query: { type: 'gender' } }"
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer)"
      >
        <div class="inline-flex self-center text-gray-500 w-50">性别</div>
        <div class="inline-flex self-center align-middle">{{ userInfo.gender ? '男' : '女' }}</div>
        <div class="ml-auto items-center inline-flex">
          <i-grommet-icons-form-next class="w-7 h-7"/>
        </div>
      </router-link>
    </div>

    <div class="border py-5 mt-6 rounded-xl bg-white">
      <p class="text-2xl px-6">联系信息</p>
      <p class="mt-2 px-6 tracking-widest mb-4">使用微筹的其他用户可能会看到部分信息。</p>
      <!-- w-50 -->
      <div
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer )"
      >
        <div class="inline-flex self-center text-gray-500 w-50">电子邮件</div>
        <div class="inline-flex self-center align-middle">{{ userInfo.email }}</div>
        <div class="ml-auto items-center inline-flex">
          <!-- <i-grommet-icons-form-next class="w-7 h-7" /> -->
        </div>
      </div>

      <router-link
          :to="{ name: 'profile-modify', query: { type: 'mobileNumber' } }"
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer )"
      >
        <div class="inline-flex self-center text-gray-500 w-50">电话</div>
        <div class="inline-flex self-center align-middle">{{ userInfo.mobile }}</div>
        <div class="ml-auto items-center inline-flex"
        >
          <i-grommet-icons-form-next class="w-7 h-7"/>
        </div>
      </router-link>

      <router-link
          :to="{ name: 'profile-modify', query: { type: 'wechat' } }"
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer)"
      >
        <div class="inline-flex self-center text-gray-500 w-50">微信</div>
        <div class="inline-flex self-center align-middle">{{ userInfo.wechat }}</div>
        <div class="ml-auto items-center inline-flex">
          <i-grommet-icons-form-next class="w-7 h-7"/>
        </div>
      </router-link>


      <router-link
          :to="{ name: 'profile-modify', query: { type: 'bilibili' } }"
          class="flex text-base mx-6 border-b py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer)"
      >
        <div class="inline-flex self-center text-gray-500 w-50">哔哩哔哩</div>
        <div class="inline-flex self-center align-middle">{{ userInfo.bilibili }}</div>
        <div class="ml-auto items-center inline-flex">
          <i-grommet-icons-form-next class="w-7 h-7"/>
        </div>
      </router-link>
    </div>

    <div class="border max-w-110 pb-20 py-5 mt-6 rounded-xl bg-white">
      <p class="text-2xl px-6">密码</p>
      <p class="mt-2 px-6 tracking-widest mb-4">安全的密码可以更好地保护您的帐号。</p>
      <!-- w-50 -->
      <div
          @click="showPassswordModalRef = true"
          class="flex text-base mx-6 py-6 hover:(bg-true-gray-100 mx-0 px-6 cursor-pointer )"
      >
        <div class="inline-flex self-center text-gray-500 w-50">密码</div>
        <div class="inline-flex self-center align-middle">**********</div>
        <div class="ml-auto items-center inline-flex">
          <i-grommet-icons-form-next class="w-7 h-7"/>
        </div>
      </div>
    </div>
  </div>
  <n-modal
      v-model:show="showPassswordModalRef"
      preset="card"
      title="修改密码"
      class="w-100 mt-50"
      :bordered="true"
  >
    <n-space vertical justify="center">
      <n-input
          type="password"
          v-model:value="changePasswordRef"
          placeholder="密码"
          :minlength="6"
      />
      <n-input
          type="password"
          v-model:value="confirmChangePasswordRef"
          placeholder="确认密码"
          :minlength="6"
      />

      <n-button type="primary" @click="updatePassword">确定</n-button>
    </n-space>
  </n-modal>

  <n-modal
      v-model:show="showChangeAvatar"
      preset="card"
      title="上传头像"
      class="w-100 mt-50"
      :bordered="true"
  >
    <Upload v-model:filePath="newAvatarUrl"/>
  </n-modal>
</template>

<script setup lang="ts">
import {fetchPasswordUser, fetchUpdateProfile} from '@/service';
import {useUserStore} from '@/store';
import {computed, watch, ref, toRaw, unref} from 'vue';

const userStore = useUserStore();
const userInfo = computed(() => {
  return userStore.userInfo;
});
const showPassswordModalRef = ref(false);
const showChangeAvatar = ref(false);
const confirmChangePasswordRef = ref('');
const changePasswordRef = ref('');
const updatePassword = () => {
  if (changePasswordRef.value != confirmChangePasswordRef.value) {
    window.$message.error('两次输入不一致！');
    return;
  } else {
    fetchPasswordUser(changePasswordRef.value).then((res) => {
      if (res.error == null) {
        userStore.resetAuthStore();
      }
    });
  }
};
const newAvatarUrl = ref('');
const saveUserInfo = async () => {
  userStore.userInfo.avatar = newAvatarUrl.value;
  fetchUpdateProfile(toRaw(unref(userInfo))).then((res) => {
    userStore.updateUserInfo(toRaw(unref(userInfo)));
    window.$message.success('信息已更新');
  });
};
watch(newAvatarUrl, (n) => {
  console.log(n);
  saveUserInfo();
});
</script>

<style scoped></style>
