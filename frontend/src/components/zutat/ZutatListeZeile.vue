<template>
    <td>
        
    </td>
    <td>
        <img :src="`/images/zutaten/${zutat.ean}.png`" :alt="zutat.name" />
    </td>
    <td class="zutat-zeile">
        {{ zutat.name }}
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