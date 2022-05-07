<template>
  <div>
    <div class="w-full flex mt-4 flex-col-reverse sm:( flex-row ) px-16 justify-between py-12">
      <div class="flex flex-col-reverse sm:(space-y-0 flex-row basis-2/4 ) justify-between">
        <div class="flex flex-col text-gray-400">
          <p class="text-base text-gray-900 my-4">关于</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">We Do</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">联系我们</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">品牌历程</p>
        </div>
        <div class="flex flex-col text-gray-400">
          <p class="text-base text-gray-900 my-4">帮助 </p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">常见问题</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">活动规范</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">支持指南</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">活动开展指南</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">活动开展资质要求</p>
        </div>
        <div class="flex flex-col text-gray-400">
          <p class="text-base text-gray-900 my-4">条款</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">用户注册协议</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">发起者协议</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">支持者协议</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">隐私政策</p>
          <p class="hover:( underline cursor-pointer underline-offset-1)">结算及退款流程</p>
        </div>
      </div>
      <div class="my-5 border-t"> </div>
      <div class="flex flex-col items-center sm:(mr-12)">
        <n-el style="color: var(--primary-color)">
          <n-icon size="70"> <i-mdi-email-fast /></n-icon>
        </n-el>
        <div class="font-semibold">通过邮件接收活动进展</div>
        <n-input placeholder="你的邮件地址" class="mt-4"></n-input>
        <div class="flex mt-3 space-x-3">
          <n-checkbox></n-checkbox>
          <p>我同意使用条款，并已阅读并理解隐私政策</p>
        </div>
        <n-button type="success" class="w-full mt-2"> 发送给我 </n-button>
      </div>
    </div>
    <div class="mx-10 border-t my-5"> </div>
    <div class="text-center pb-5"> Copyright © 2022 WeiChou All Rights Reserved 多凡 </div>
  </div>
</template>
<script setup lang="ts"></script>
<style></style>
