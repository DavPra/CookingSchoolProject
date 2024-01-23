// stores/counter.js
import {defineStore} from 'pinia';
export const useCounterStore = defineStore('counter', {
    state: () => ({
        options: {
            a: 0,
            b: 0,
            c: 0,
        },
    }),
    actions: {
        increment(option) {
            this.options[option]++;
        },
    },
    getters: {
        totalClicks() {
            return Object.values(this.options).reduce((total, current) => {
                return total + current;
            }, 0);
        },
    },
});

<!-- Component.vue -->
<script setup>
    import {useCounterStore} from '../stores/counter';
    const store = useCounterStore();
</script>

<!-- Component.vue -->
<template>
    <h2>Total Clics: {{ store.totalClicks }}</h2>
    <div class="card">
        <button type="button" @click="store.increment('a')">
        Option A clicked {{ store.options['a'] }} times
    </button>
    <button type="button" @click="store.delayedIncrement('b')">
    Option B clicked {{ store.options['b'] }} times
</button>
<button type="button" @click="store.increment('c')">
    Option C clicked {{ store.options['c'] }} times
</button>
</div>
</template>
