<script setup lang="ts">

import { useLogin } from '@/stores/loginstore';
const { logout, login, loginstate } = useLogin();
import { useInfo } from '@/composables/useInfo';
const { setzeInfo } = useInfo();

import { ref } from 'vue';
const username = ref("");
const losung = ref("");

function loginFunction(){
    if(losung && username){
        login(username.value, losung.value);
        if(loginstate.loggedin == true){
           // router.push('/doenerliste'); 
           setzeInfo("Hat wohl geklappt!! :D");
        }else{
            setzeInfo("Bitte Usernamen und Losung korrekt eingeben");
        }
    }else{
        setzeInfo("Bitte Usernamen und Losung eingeben");
    }
    
}

</script>

<template>
    <h1>Login</h1>
    <form @submit.prevent="loginFunction"> <!-- submit.prevent statt v-on:click im button, submit# funktioniert scheinbar nicht-->
        <label for="name">Name</label>
        <input type="text" v-model="username">
        <label for="losung">Losung</label>
        <input type="password" v-model="losung">

        <button>Login!</button>

    </form>
    
</template>