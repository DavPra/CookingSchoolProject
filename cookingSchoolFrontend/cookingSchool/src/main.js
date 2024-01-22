import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import {router} from './router'
import './assets/main.css'
import {vuetify} from './plugins/vuetify'
import vClickOutside from "click-outside-vue3";

const pinia = createPinia()

createApp(App)
    .use(router)
    .use(pinia)
    .use(vuetify)
    .use(vClickOutside)
    .mount('#app')
