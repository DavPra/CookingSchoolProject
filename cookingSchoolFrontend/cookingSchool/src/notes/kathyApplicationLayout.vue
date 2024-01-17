<script setup>
import {useRouter} from 'vue-router'
import {useAuthenticationStore} from '../stores/authenticationStore.js'
import {ref} from 'vue'

const authentication = useAuthenticationStore()
const router = useRouter()

async function logout() {
  try {
    await authentication.logout()
    router.push('/')
  }catch(error) {
    console.log(error)
  }
}

const toggleLogout = ref(false)
</script>

<template>
  <v-app-bar>
    <v-app-bar-title class="text-h4"><v-icon>mdi-rabbit-variant-outline</v-icon> Fun-TASK-isch</v-app-bar-title>
    <v-toolbar-items>
      <v-btn to="/dashboard">Home</v-btn>
      <v-btn @click="logout()" icon="mdi-logout-variant"></v-btn>
    </v-toolbar-items>
  </v-app-bar>
  <v-navigation-drawer class="bg-deep-orange-lighten-2 text-deep-orange-lighten-5" permanent>
    <v-list-item>
      <v-btn to="/alltasks" prepend-icon="mdi-inbox-outline">Alle Aufgaben</v-btn>
    </v-list-item>
    <v-list-item>
      <v-btn to="/priorities" prepend-icon="mdi-exclamation-thick">Prioritäten</v-btn>
    </v-list-item>
  </v-navigation-drawer>
  <v-main>
    <RouterView/>
  </v-main>
  <v-bottom-navigation>
    <v-btn to="/entry/" prepend-icon="mdi-plus-circle">Neue Aufgabe</v-btn>
  </v-bottom-navigation>
</template>

<style scoped></style>