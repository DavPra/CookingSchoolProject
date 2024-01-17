<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HeaderIcon from '@/components/HeaderIcon.vue'
import Logo from "@/components/Logo.vue";

//TODO: Kathy - Add logout function and link to guest home view
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der Guests -->
  <HeaderIcon/>
  <v-toolbar>
    <v-toolbar-items>
      <v-btn to="/admin/courses">Kurse</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn to="/admin/recipes">Rezepte</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn to="/admin/users">User</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn to="/logout"><v-icon>logout-variant</v-icon></v-btn>
    </v-toolbar-items>
  </v-toolbar>
  <RouterView />
  <Logo/>
</template>

<style scoped>
</style>