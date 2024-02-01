<script setup>
import HeaderIcon from '@/components/HeaderIcon.vue'
import Logo from "@/components/Logo.vue";
import {useAuthStore} from "@/stores/AuthStore.js";
import {useRouter} from "vue-router";

const authentication = useAuthStore();
const router = useRouter();

async function logout() {
  try {
    await authentication.logout();
    await router.push('/');
  } catch (error) {
    console.log(error);
  }

}
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der Guests -->
  <HeaderIcon/>
  <v-toolbar color="bg-transparent" elevation="3" class="  bg-transparent" rounded >
    <v-toolbar-items>
      <v-btn variant="plain" to="/admin">Dashboard</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" to="/admin/courses">Kursverwaltung</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" to="/admin/recipes">Rezeptverwaltung</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" to="/admin/users">Userverwaltung</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>

    <v-toolbar-items>
      <v-btn @click="logout()" icon="mdi-logout-variant"></v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
  </v-toolbar>
  <RouterView />
  <Logo style="position: centered;"/>
</template>

<style scoped>
</style>