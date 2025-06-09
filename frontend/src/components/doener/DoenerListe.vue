<template>
    <table class="doener-table">
        <thead>
            <tr>
            <th>
                ID
            </th>
            <th>
                Bezeichnung 
            </th>
            <th>
                Preis
            </th>
            <th>
                Vegetarizitaet
            </th>
        </tr>
        </thead>
        <tbody>
            <DoenerListeZeile v-for="doener in doenerliste" :doener="doener" :key="doener.id"/>
        </tbody>
    </table>
</template>

<script setup lang="ts">
    import { defineProps } from 'vue';
    import DoenerListeZeile from '@/components/doener/DoenerListeZeile.vue';
    import ZutatListeZeile from '@/components/zutat/ZutatListeZeile.vue';
    import type { IDoenerDTD } from "@/views/DoenerListView.vue";
    import type { IZutatDTD } from '@/views/DoenerListView.vue';
    // import doenerliste, { type IDoenerDTD } from "@/views/DoenerListView.vue";

    defineProps<{
        doenerliste: IDoenerDTD[]; // ! warum typeof?? 
    }>();

</script>

<style>
@import '@/assets/main.css';
</style>