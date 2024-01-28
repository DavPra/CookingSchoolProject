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
  <v-toolbar color="grey-lighten-1">
    <v-toolbar-items>
      <v-btn to="/user/profile">Profil</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn to="/user/courses">Kurse</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn to="/user/recipes">Rezepte</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn to="/user/about">About Us</v-btn>
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
