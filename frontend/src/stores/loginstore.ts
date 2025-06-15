import { defineStore } from 'pinia';
import { reactive } from 'vue';

export const useLogin = defineStore('loginstore',() => {
    const loginstate = reactive({
        username: '',
        loggedin: false
    });
    function logout(){
        loginstate.username = '';
        loginstate.loggedin = false;
    }
    function login(username: string, losung: string){
        if(username && losung){
            loginstate.username = username;
            loginstate.loggedin = true;
        }else{
            logout();
        }
    }
    return{ logout, login, loginstate}
})