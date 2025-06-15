<script setup lang="ts">
import { ref } from 'vue';
import './assets/main.css';
import DoenerListe from '@/views/DoenerListView.vue'
import LoginView from "@/views/LoginView.vue"

import { useLogin } from '@/stores/loginstore';
const { loginstate } = useLogin();

// const info = ref("");

// function fuelleInfo(){
//   if(info.value == ""){
//     info .value = "test";
//   }else{
//     info.value = "";
//   }
// }

import { useInfo } from '@/composables/useInfo';
import { RouterView } from 'vue-router';
const { setzeInfo, loescheInfo, info} = useInfo();

</script>

<template>
  <!-- <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView /> -->

  <header>
    <div class="header--container">
      <div class="header--content">
        <a href="#">Doenerverleih UdE</a>
        <a v-if="loginstate.loggedin"  href="/doenerliste">Katalog</a>
      </div>
      <div>
        <a href="#">DE</a>
      </div>
      <div>
        <a href="/login">Login</a>
      </div>
    </div>
  </header>

  <button v-on:click="setzeInfo('info')">OK</button>

  <div v-if="info" class="info-box--wrapper">
    <div class="info-box">
      <div class="info-box--headline">
        <div>
          Information
        </div>
        <button v-on:click="loescheInfo()">
          X
        </button>
      </div>
      <div class="info-box--body">
        {{ info }}
      </div>
    </div>
  </div>

  <div>
    <!-- <LoginView/> -->
    <!-- <DoenerListe/> -->
     <RouterView/>
  </div>

</template>

<style>
@import './assets/main.css';
</style>