<template>
  <n-card v-if="isLoginRef" class="w-370px rounded-3xl">
    <n-space vertical>
      <Logo class="justify-center" />
      <n-form :model="userModel" ref="loginFormRef" label-placement="left" :rules="rules">
        <n-form-item path="username">
          <n-input v-model:value="userModel.username" placeholder="请输入用户名">
            <template #prefix>
              <n-icon size="18" color="#808695"> <i-uil-user /> </n-icon>
            </template>
          </n-input>
        </n-form-item>

        <n-form-item path="password">
          <n-input
            v-model:value="userModel.password"
            type="password"
            showPasswordOn="click"
            placeholder="请输入密码"
          >
            <template #prefix>
              <n-icon size="18" color="#808695"> <i-ic-outline-lock /> </n-icon>
            </template>
          </n-input>
        </n-form-item>
        <n-form-item path="rememberMe">
          <div class="flex w-full justify-between">
            <div class="flex-initial">
              <n-checkbox v-model:checked="userModel.rememberMe">自动登录</n-checkbox>
            </div>
            <div class="flex-initial order-last">
              <a href="javascript:">忘记密码</a>
            </div>
          </div>
        </n-form-item>
        <n-form-item>
          <n-button round block type="primary" class="w-full" @click="handleSubmit" size="large">
            <!-- :loading="loading" -->
            登录
          </n-button>
        </n-form-item>
        <n-el> 没有账户？ <n-button text @click="isLoginRef = false"> 注册</n-button></n-el>
      </n-form>
    </n-space>
  </n-card>
  <n-card v-else class="w-370px rounded-3xl">
    <n-space vertical>
      <Logo class="justify-center" />
      <n-form :model="userModel" ref="registerFormRef" label-placement="left" :rules="rules">
        <n-form-item path="username">
          <n-input v-model:value="userModel.username" placeholder="请输入用户名">
            <template #prefix>
              <n-icon size="18" color="#808695"> <i-uil-user /> </n-icon>
            </template>
          </n-input>
        </n-form-item>
        <n-form-item path="email">
          <n-input v-model:value="userModel.email" placeholder="请输入用邮箱">
            <template #prefix>
              <n-icon size="18" color="#808695"> <i-mdi-email-outline /> </n-icon>
            </template>
          </n-input>
        </n-form-item>

        <n-form-item path="password">
          <n-input
            v-model:value="userModel.password"
            type="password"
            showPasswordOn="click"
            placeholder="请输入密码"
          >
            <template #prefix>
              <n-icon size="18" color="#808695"> <i-ic-outline-lock /> </n-icon>
            </template>
          </n-input>
        </n-form-item>
        <n-form-item path="rePassword">
          <n-input
            v-model:value="userModel.rePassword"
            type="password"
            showPasswordOn="click"
            placeholder="请再次输入密码"
          >
            <template #prefix>
              <n-icon size="18" color="#808695"> <i-ic-outline-lock /> </n-icon>
            </template>
          </n-input>
        </n-form-item>
        <n-form-item>
          <n-button round block type="primary" class="w-full" @click="handleSubmit" size="large">
            <!-- :loading="loading" -->
            注册
          </n-button>
        </n-form-item>
        <n-el> <n-button text @click="isLoginRef = true"> 去登陆</n-button></n-el>
      </n-form>
    </n-space>
  </n-card>
</template>
<script setup lang="ts">
  import logo from '@/assets/img/logo.png';
  import { Logo } from '@/components';
  import { useUserStore } from '@/store';
  import { reactive, ref, toRaw, unref, watch } from 'vue';

  const userModel = reactive({
    username: 'duofan@weichou.com',
    password: '123456',
    email: '2441051071@qq.com',
    rememberMe: false,
    rePassword: '',
  });
  const loginFormRef = ref();
  const registerFormRef = ref();
  const isLoginRef = ref(true);
  const userStore = useUserStore();
  const { login, isLogin } = userStore;
  const rules = {
    username: { required: true, message: '请输入用户名', trigger: 'input' },
    password: { required: true, message: '请输入密码', trigger: 'input' },
    email: { required: true, message: '请输入邮箱', trigger: 'input' },
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    loginFormRef.value.validate(async (errors: any) => {
      if (!errors) {
        console.log();
        login(userModel.username, userModel.password);
      } else {
        window.$message.error('请填写完整信息，并且进行验证码校验');
      }
    });
  };
</script>
<style scoped>
  .hidden {
    display: none;
  }
</style>
