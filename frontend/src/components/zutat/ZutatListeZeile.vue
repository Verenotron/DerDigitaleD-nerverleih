<template>
    <td>
        
    </td>
    <td>
        <img :src="`/images/zutaten/${zutat.ean}.png`" :alt="zutat.name" />
    </td>
    <td class="zutat-zeile">
        <a :href="'https://de.wikipedia.org/wiki/Special:Search/' + zutat.name">{{ zutat.name }}</a>
    </td>
    <td class="zutat-zeile">
        {{ zutat.vegetarizitaet }}
    </td>
</template>

<script setup lang="ts">

import { defineProps } from 'vue';
import type { IZutatDTD } from '@/views/DoenerListView.vue';
import type { IDoenerDTD } from '@/views/DoenerListView.vue';

    defineProps<{
        zutat: IZutatDTD;
    }>()
    
</script>