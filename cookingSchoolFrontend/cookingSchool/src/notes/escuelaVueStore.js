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

