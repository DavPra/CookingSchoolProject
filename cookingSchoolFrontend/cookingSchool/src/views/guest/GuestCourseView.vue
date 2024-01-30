<script setup>

import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
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
  showCourses();
  console.log('mounted');
});


const err = false;


async function showCourses() {
  await courseStore.showGuestCourses();
}

showCourses();

</script>

<template>
  <HeaderIcon></HeaderIcon>


    <div class="container d-flex mt-5 justify-center" >
      <v-sheet class="m-6 p-4" elevation="3">
        <h1 class="font-weight-medium mb-3 ">Dein Erlebnis bei uns</h1>
        <h2 class="mb-4">Was ist alles inkludiert?</h2>

        <ul class="list-disc pl-6">
          <li>Mehrgängiges Menü, wir kochen 3 bis 4 Speisen</li>
          <li>Jeder Teilnehmer bekommt die super duper tollen Rezepte</li>
          <li>Unsere Zutaten sind biologisch, regional und saisonal</li>
          <li>ALLE Getränke</li>
        </ul>
      </v-sheet>
    </div>

  <div>
    <NextCourses/>
  </div>



  <v-sheet class="bg-brown-lighten-5">
    <div class="mx-2 my-2 px-2 py-2">
      <h2 class="text-h4 ">Alle unser Kurse auf einen Blick:</h2>
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