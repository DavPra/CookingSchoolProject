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
      <v-btn variant="plain" to="/admin/courses">Kursplanung</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" to="/admin/recipes">Rezepte organisieren</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn variant="plain" to="/admin/users">Benutzerverwaltung</v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>

    <v-toolbar-items>
      <v-btn @click="logout()" icon="mdi-logout-variant"></v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
  </v-toolbar>
  <div class="background">
    <RouterView />
  </div>

  <Logo style="position: centered;"/>
</template>

<style scoped>
  .background{
    background: rgb(241,205,165);
    background: linear-gradient(148deg, rgba(241,205,165,1) 0%, rgba(129,19,3,1) 100%);
    /*background-image: url("https://img.freepik.com/free-photo/orange-bokeh-patterned-wallpaper_53876-95993.jpg?size=626&ext=jpg&ga=GA1.1.1112527860.1705089653&semt=ais");
    background-size: cover; */

}
</style>