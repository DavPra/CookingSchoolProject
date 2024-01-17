<script setup>

import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

import { useCourseStore } from "@/stores/CourseStore.js";

const courseStore = useCourseStore()

onMounted(() => {
  showCourses();
  console.log('mounted');
});

const show = ref(false)

const err = false;


async function showCourses() {
  await courseStore.showCourses();
}

showCourses();

</script>

<template>

<div class="course-list">
  <h1>Course List</h1>
  <v-row>
    <v-col cols="12" sm="6" md="4" lg="3">
      <v-card>
        <v-img
            cover
            height="250"
            src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        ></v-img>

        <v-card-title></v-card-title>
        <v-card-text></v-card-text>
        <v-card-actions>
          <v-btn color="primary">Kursbeschreibung</v-btn>

          <v-spacer></v-spacer>

          <v-btn 
          :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
          @click="show = !show">
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show">
            <v-divider></v-divider>
            <v-card-text></v-card-text>
          <v-btn
          variant="text" 
          color="primary">
          Details
          </v-btn>
          </div>
        </v-expand-transition>

      </v-card>
    </v-col>
  </v-row>
</div>
</template>
