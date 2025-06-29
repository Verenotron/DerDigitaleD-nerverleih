<template>
    <tr @click="expanded = !expanded" class="doener-table-zeile">
        <td>
            {{ doener.id }}
        </td>
        <td>
            {{ doener.bezeichnung }}
        </td>
        <td>
            {{ doener.preis }}
        </td>
        <td>
            {{ doener.vegetarizitaet }}
        </td>
        <td>
            {{ doener.verfuegbar}}
        </td>
        <td>
            ?
        </td>
    </tr>
    <tr v-if="expanded" v-for="zutat in doener.zutaten" :key="zutat.ean">
        <ZutatListeZeile :zutat="zutat" />
    </tr>
</template>

<script setup lang="ts">

import { ref, defineProps } from 'vue';
import type { IDoenerDTD } from '@/views/DoenerListView.vue'
import ZutatListeZeile from '@/components/zutat/ZutatListeZeile.vue';

var expanded = ref(false);

    const props = defineProps<{
        doener: IDoenerDTD
    }>()
</script>

<style>
@import '@/assets/main.css';
</style>