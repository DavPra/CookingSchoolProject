<script setup>

import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

import { useCourseStore } from "@/stores/CourseStore.js";
import { useAuthStore } from "@/stores/AuthStore.js";
import jwtdecode from "jwt-decode";

const courseStore = useCourseStore()
const course = defineProps(['courseTitle','startDate','description','teacher','courseId'])
const authStore = useAuthStore();

onMounted(() => {
  showCourses();
  console.log('mounted');
 
});

const show = ref(false)

const err = false;
let courses = ref([]);
const courseId = course.courseId;
let decodedUserId = '';


async function showCourses() {
  await courseStore.showCourses();
  courses = courseStore.courses;
  console.log(courses);
}

showCourses();

async function bookCourse() {
  decodedUserId = jwtdecode(localStorage.getItem("accessToken")).userId;
  console.log(decodedUserId);
  await courseStore.bookCourse(decodedUserId, courseId);
  console.log(courses.courseId);
}

</script>

<template>

<div class="course-list">
      <v-card>
        <v-img
            cover
            height="250"
            src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        ></v-img>

        <v-card-title>
          {{ courseTitle }}
        </v-card-title>
        <v-card-text>{{ startDate }}</v-card-text>
        <v-card-text>{{ teacher }}</v-card-text>
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
            <v-card-text>{{description}}</v-card-text>
          <v-btn @click="bookCourse"
          variant="text" 
          color="primary">
          Buchen
          </v-btn>
          </div>
        </v-expand-transition>

      </v-card>
</div>
</template>
