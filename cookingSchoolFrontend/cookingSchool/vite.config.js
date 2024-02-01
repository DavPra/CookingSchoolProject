import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vuetify from "vite-plugin-vuetify";

const colors = {
  primary: '#b13f13',
  secondary: '#e09B40'

  // ... weitere Farbdefinitionen
};
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(), vuetify()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
    define:{
      __colors: JSON.stringify(colors),
    }
  }
})
