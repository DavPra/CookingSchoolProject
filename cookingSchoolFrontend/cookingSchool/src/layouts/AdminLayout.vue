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
    console.error('Fehler beim Logout:', error);
  }
}
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der Guests -->
  <HeaderIcon/>
  <v-toolbar color="grey-darken-3" class="pa-2 mb-4"   density="comfortable" elevation="1" >
    <v-toolbar-items>
      <v-btn variant="plain" rounded="xl"   to="/admin">Dashboard</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" rounded="xl"  to="/admin/courses">Kurse</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" rounded="xl" to="/admin/recipes">Rezepte</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" rounded="xl"   to="/admin/users">User</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn @click="logout()" icon="mdi-logout-variant"></v-btn>
    </v-toolbar-items>
  </v-toolbar>
  <RouterView />

  <Logo/>
</template>

<style scoped>
</style>