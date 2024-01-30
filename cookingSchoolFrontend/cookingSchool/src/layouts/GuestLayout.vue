<script setup>
import HeaderIcon from '@/components/HeaderIcon.vue'
import Logo from '@/components/Logo.vue'
import {globalColors as __colors} from "@/assets/colors";
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der Guests -->
    <v-toolbar color="bg-transparent" elevation="3" class="  bg-transparent" rounded >
      <v-toolbar-title>

     <v-btn to="/" variant="plain" class="text-h5">Cooking Academy</v-btn>
      </v-toolbar-title>
      <v-spacer></v-spacer>
        <v-btn variant="plain"  to="/courses" >Kurse</v-btn>
        <v-btn variant="plain" to="/about">About Us</v-btn>
    <!--  <v-btn to="/"><v-icon size="xl" te>mdi-home</v-icon></v-btn> -->
        <v-btn variant="plain" to="/login" class="ma-1">Login</v-btn>
    </v-toolbar>
  <RouterView />
  <Logo/>


</template>

<style scoped>
</style>
