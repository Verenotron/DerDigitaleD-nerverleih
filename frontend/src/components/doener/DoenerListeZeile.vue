<template>
    <tr>
        <td>
            {{ doener.id }}
        </td>
        <td>
            {{  doener.bezeichnung }}
        </td>
        <td>
            {{ doener.preis }}
        </td>
        <td>
            {{  doener.vegetarizitaet }}
        </td>
        <td>
            ?
        </td>
    </tr>
</template>

<script setup lang="ts">

import { defineProps } from 'vue';
import type { IDoenerDTD } from '@/views/DoenerListView.vue'

    const props = defineProps<{
        doener: IDoenerDTD
    }>()
</script>