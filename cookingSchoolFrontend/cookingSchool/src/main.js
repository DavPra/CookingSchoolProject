import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import {router} from './router'
import './assets/main.css'
import {vuetify} from './plugins/vuetify'
import 'bootstrap/dist/css/bootstrap.css'

const pinia = createPinia()

createApp(App)
    .use(router)
    .use(pinia)
    .use(vuetify)
    .mount('#app')