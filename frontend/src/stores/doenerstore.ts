import type{ IZutatDTD } from '@/stores/IZutatDTD';
import type{ IDoenerDTD } from '@/stores/IDoenerDTD';
import {defineStore} from 'pinia';
import { Client, type Message } from '@stomp/stompjs';
import { ref } from 'vue'
import { useInfo } from '@/composables/useInfo';
const{ setzeInfo } = useInfo();

//const da reaktives Objekt zurückgegeben werden soll, keine funktion
export const useDoenerStore = defineStore('doenerstore', {
    state: () => ({
        doenerdata: {
            ok: false,
            doenerliste: [] as IDoenerDTD[],
        },
        stompClient: null as Client | null
    }),
    actions:{
        async updateDoenerListe(){
          try{
            const response = await fetch('http://localhost:8080/api/doener');
            if(!response.ok){
              throw new Error("Fehler beim Laden der DoenerDaten");
            }
            const data: IDoenerDTD[] = await response.json(); //await bedeutet asynchron und dass man hier erst auf Daten wartet, befor der code fortfährt.
            this.doenerdata.doenerliste = data;
            this.doenerdata.ok = true;


          }catch(error){
            console.error("Fehler beim Laden der Tourdaten.");
            this.doenerdata.ok = false;
            setzeInfo("Fehler beim Laden der DoenerDaten.");
          }
        },
        startDoenerLiveUpdate(){

          if(this.stompClient){
            console.log('STOMP-Client existiert bereits');
            return;
          }

          this.stompClient = new Client({
            brokerURL: 'ws://localhost:8080/stompbroker',
          })

          this.stompClient.activate();
          this.stompClient.onWebSocketError = (event) => {console.error("WebSocket Fehler:", event)};
          this.stompClient.onStompError = (frame) => {console.error("STOMP Fehler: ", frame)};
          this.stompClient.onConnect = (frame) => {
            console.log('Verbunden mit STOMP-Server');
            this.stompClient?.subscribe("/topic/doener", (message: Message) => {
              this.updateDoenerListe();
            })
          }
          this.stompClient.onDisconnect = () => { console.log('Verbindung getrennt')}
        }
    }

})
    
