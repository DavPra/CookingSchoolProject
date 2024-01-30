import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import {router} from './router'
import './assets/main.css'
import {vuetify} from './plugins/vuetify'
import vClickOutside from "click-outside-vue3";
import Chart from 'chart.js/auto'

const pinia = createPinia()

createApp(App)
    .use(router)
    .use(pinia)
    .use(vuetify)
    .use(vClickOutside)
    .component('Chart', Chart)
    .mount('#app')


