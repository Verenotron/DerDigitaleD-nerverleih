import type{ IZutatDTD } from '@/stores/IZutatDTD';
import type{ IDoenerDTD } from '@/stores/IDoenerDTD';
import {defineStore} from 'pinia';
import { ref } from 'vue'
import { useInfo } from '@/composables/useInfo';
const{ setzeInfo } = useInfo();

//const da reaktives Objekt zurückgegeben werden soll, keine funktion
export const useDoenerStore = defineStore('doenerstore', {
    state: () => ({
        doenerdata: {
            ok: false,
            doenerliste: [] as IDoenerDTD[],
        }
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

          
            

            // this.doenerdata.doenerliste = 
            //     JSON.parse(`
            //   [
            //     {
            //       "id": 2802,
            //       "bezeichnung": "Bronzongdön",
            //       "preis": 13,
            //       "vegetarizitaet": 0,
            //       "zutaten": [
            //         {
            //           "ean": "1101049047855",
            //           "name": "Eisbergsalat",
            //           "vegetarizitaet": 2
            //         },
            //         {
            //           "ean": "4474445326792",
            //           "name": "Fladenbrot",
            //           "vegetarizitaet": 1
            //         },
            //         {
            //           "ean": "7806470514874",
            //           "name": "Kalbsschnipsel",
            //           "vegetarizitaet": 0
            //         },
            //         {
            //           "ean": "3398697207454",
            //           "name": "Knoblauch",
            //           "vegetarizitaet": 2
            //         }
            //       ]
            //     },
            //     {
            //       "id": 2,
            //       "bezeichnung": "Fleischdön",
            //       "preis": 5,
            //       "vegetarizitaet": 0,
            //       "zutaten": [
            //         {
            //           "ean": "4474445326792",
            //           "name": "Fladenbrot",
            //           "vegetarizitaet": 1
            //         },
            //         {
            //           "ean": "8645075438735",
            //           "name": "Frikadelle",
            //           "vegetarizitaet": 0
            //         },
            //         {
            //           "ean": "7806470514874",
            //           "name": "Kalbsschnipsel",
            //           "vegetarizitaet": 0
            //         },
            //         {
            //           "ean": "8709274658213",
            //           "name": "Lamm da",
            //           "vegetarizitaet": 0
            //         },
            //         {
            //           "ean": "7103802900732",
            //           "name": "Putenschnipsel",
            //           "vegetarizitaet": 0
            //         }
            //       ]
            //     },
            //     {
            //       "id": 1,
            //       "bezeichnung": "Gesundöner",
            //       "preis": 10,
            //       "vegetarizitaet": 2,
            //       "zutaten": [
            //         {
            //           "ean": "1101049047855",
            //           "name": "Eisbergsalat",
            //           "vegetarizitaet": 2
            //         },
            //         {
            //           "ean": "1715334440614",
            //           "name": "Grapefruit",
            //           "vegetarizitaet": 2
            //         },
            //         {
            //           "ean": "5013842346499",
            //           "name": "Gurke",
            //           "vegetarizitaet": 2
            //         }
            //       ]
            //     },
            //     {
            //       "id": 2803,
            //       "bezeichnung": "Wiglettdön",
            //       "preis": 17,
            //       "vegetarizitaet": 0,
            //       "zutaten": [
            //         {
            //           "ean": "8645075438735",
            //           "name": "Frikadelle",
            //           "vegetarizitaet": 0
            //         },
            //         {
            //           "ean": "1715334440614",
            //           "name": "Grapefruit",
            //           "vegetarizitaet": 2
            //         },
            //         {
            //           "ean": "9150715186721",
            //           "name": "Rösti",
            //           "vegetarizitaet": 1
            //         },
            //         {
            //           "ean": "7763273447981",
            //           "name": "Weißkohl",
            //           "vegetarizitaet": 2
            //         }
            //       ]
            //     }
            //   ]
            //   `);
              // this.doenerdata.ok = true;
        }
    }

})
    
