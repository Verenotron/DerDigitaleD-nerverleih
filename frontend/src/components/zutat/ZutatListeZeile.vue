<template>
    <div>
        
    </div>
    <td>

    </td>
    <td>
        {{ zutat.name }}
    </td>
    <td>
        {{ zutat.ean }}
    </td>
    <td>
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