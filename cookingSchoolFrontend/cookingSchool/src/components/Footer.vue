<script setup>
import {ref} from "vue";

const icons = [
  'mdi-facebook',
  'mdi-twitter',
  'mdi-linkedin',
  'mdi-instagram',
]
</script>

<template>
  <v-footer
      class="bg-brown-lighten-5 text-center d-flex flex-column"
  >
    <div>
      <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4"
          :icon="icon"
          variant="text"
      ></v-btn>
    </div>

    <div class="pt-0">
       Lirum Larum Löffelstiel
    </div>
    <div class="pt-0">
    cookingacademy@contact.com
  </div>


    <v-divider></v-divider>

    <div>
      {{ new Date().getFullYear() }} — <strong>Cooking Academy</strong>
    </div>
  </v-footer>

</template>

<style scoped>

</style>