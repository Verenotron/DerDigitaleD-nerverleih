import { ref } from 'vue';
var info = ref("");

export function useInfo(){ //funktionen selbst sind Objekte und können funktionen enthalten.
    function setzeInfo(value : string){
        if(value == ""){
            info.value = "";
        }else{
            info.value = value;
        }

    }
    function loescheInfo(){
        info.value = "";
    }

    return{
        setzeInfo,
        loescheInfo,
        info
    }
}