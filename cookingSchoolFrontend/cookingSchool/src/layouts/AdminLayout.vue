<script setup>
import HeaderIcon from '@/components/HeaderIcon.vue'
import Logo from "@/components/Logo.vue";
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
      <v-btn to="/admin">Profil</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>

    <v-toolbar-items>
      <v-btn> <v-icon> icon="mid-logout"> </v-icon> </v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
  </v-toolbar>
  <RouterView />

  <Logo/>
</template>

<style scoped>
</style>