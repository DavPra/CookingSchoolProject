<script setup>

import { useCourseStore } from "@/stores/CourseStore.js";
import { onMounted, ref, computed } from "vue";
import { useRouter } from "vue-router";
import CourseCard from "@/components/CourseCard.vue";
import { useAuthStore } from "@/stores/AuthStore.js";
import HeaderIcon from "@/components/HeaderIcon.vue";
import NextCourses from "@/components/NextCourses.vue";

const courseStore = useCourseStore()
const router = useRouter(); // router
const isVisible = ref(false);
const authStore = useAuthStore();


const courses = computed(() => courseStore.courses);


onMounted(() => {
  ShowCourses();
  console.log('mounted');
});


const err = false;


async function ShowCourses() {
  await courseStore.showCourses();
}

ShowCourses();

</script>

<template>
  <HeaderIcon></HeaderIcon>




  <div>
    <NextCourses/>
  </div>



  <div>
    <v-sheet class="m-6 p-4 justify-center text-center elevation-0" elevation="3" width="100%" >
      <h1 class="font-weight-medium mb-3 ">Dein Erlebnis bei uns</h1>
      <h2 class="mb-4">Was ist alles inkludiert?</h2>
    </v-sheet>

    <div class="d-flex mx-4 mb-6 justify-lg-space-evenly align-center flex-wrap">
      <div class="my-3 mx-2">
        <v-card class="py-3 bg-brown-lighten-5 " variant="outlined" style="flex: 1; min-width: 200px;">
          <v-card-title>Mehrgängiges Menü</v-card-title>
          <v-card-subtitle>Wir kochen 3 bis 4 Speisen</v-card-subtitle>
        </v-card>
      </div>
      <div class="my-3 mx-2">
        <v-card class="py-3 bg-brown-lighten-5" variant="outlined" style="flex: 1; min-width: 200px;">
          <v-card-title>Persönliche Rezepte</v-card-title>
          <v-card-subtitle>Jeder Teilnehmer bekommt die super duper Rezepte</v-card-subtitle>
        </v-card>
      </div>
      <div class="my-3 mx-2">
        <v-card class="py-3 bg-brown-lighten-5" variant="outlined" style="flex: 1; min-width: 200px;">
          <v-card-title>Beste Qualität</v-card-title>
          <v-card-subtitle>Unsere Zutaten sind biologisch, regional und saisonal</v-card-subtitle>
        </v-card>
      </div>
      <div class="my-3 mx-2">
        <v-card class="py-3 bg-brown-lighten-5" variant="outlined" style="flex: 1; min-width: 200px;">
          <v-card-title>All Inklusiv</v-card-title>
          <v-card-subtitle>Alle Getränke sind inkludiert</v-card-subtitle>
        </v-card>
      </div>
    </div>
  </div>




  <v-sheet class="">
    <div class="mx-2 my-2 px-2 py-2">
      <h2 class="text-h4 ">Alle unsere Kurse auf einen Blick:</h2>
    </div>

    <v-row class="d-flex ma-2 ">
      <v-col v-for="course in courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
        <CourseCard
                
                    :courseTitle="course.courseTitle"
                    :startDate="course.startDate"
                    :description="course.description"
                    :courseId="course.courseId"
                    :teacher="course.teacher"
                    
        />
      </v-col>
    </v-row>
  </v-sheet>
</template>