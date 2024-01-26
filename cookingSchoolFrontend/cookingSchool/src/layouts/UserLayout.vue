<script setup>
import HeaderIcon from '@/components/HeaderIcon.vue'
import Logo from "@/components/Logo.vue";
import {useRouter} from 'vue-router';

//TODO: Kathy - Add logout function and link to guest home view
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
      <v-btn><v-icon>logout-variant</v-icon></v-btn>
    </v-toolbar-items>
  </v-toolbar>
  <RouterView />
  <Logo/>
</template>

<style scoped>
</style>
