import { ref, readonly } from 'vue';
var info = ref(""); // reactive<>() wäre für ganze Objekte z.B. Doener

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
        info : readonly(info) //verhindert schreibenden zugriff über info.value.
    }
}