<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HeaderIcon from '@/components/HeaderIcon.vue'
import Logo from '@/components/Logo.vue'
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der Guests -->
  <HeaderIcon/>
    <v-toolbar>
      <v-toolbar-items>
        <v-btn to=""><v-icon>home</v-icon></v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn to="/courses">Kurse</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn to="/about">About Us</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn to="/login">Login</v-btn>
      </v-toolbar-items>
    </v-toolbar>
  <RouterView />
  <Logo/>
</template>


<style scoped>
</style>
