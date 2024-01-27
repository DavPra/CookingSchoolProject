<script setup>
import HeaderIcon from '@/components/HeaderIcon.vue'
import Logo from "@/components/Logo.vue";
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der Guests -->
  <HeaderIcon/>
  <v-toolbar color="grey-lighten-1">
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
      <v-btn to="/admin">Home</v-btn>
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