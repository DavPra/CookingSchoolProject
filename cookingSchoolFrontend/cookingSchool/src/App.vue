<script setup>
import { RouterView } from 'vue-router'
import Footer from "@/components/Footer.vue";
</script>

<template>

    <v-app class="my-app">
        <RouterView/>
        <Footer/>
    </v-app>
</template>

<style scoped>
.my-app {
  color: #222222;
}
</style>