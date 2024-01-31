<script setup>

import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { useCourseStore } from "@/stores/CourseStore.js";
import jwtdecode from "jwt-decode";

const courseStore = useCourseStore()
const course = defineProps(['courseTitle','startDate','description','teacher','courseId'])
const router = useRouter();

onMounted(() => {
  ShowCourses();
  console.log('mounted');
 
});

const show = ref(false)

const err = false;
let courses = ref([]);
const courseId = course.courseId;
let decodedUserId = '';


async function ShowCourses() {
  await courseStore.showCourses();
  courses = courseStore.courses;
  console.log(courses);
}

ShowCourses();

async function bookCourse() {
  if(!window.localStorage.getItem('accessToken')) {
    await router.push('/login');
  } else {
    decodedUserId = jwtdecode(localStorage.getItem("accessToken")).userId;
    console.log(decodedUserId);
    await courseStore.bookCourse(decodedUserId, courseId);
    console.log(courses.courseId);
  }
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
        <v-card-subtitle>{{ startDate }}</v-card-subtitle>
        <v-card-text>{{ teacher }}</v-card-text>
        <v-btn class="ms-3 " rounded="xl" @click="bookCourse"
          color="primary">
          Buchen
          </v-btn>
        <v-card-actions>
         
          <v-btn color="primary" @click="show = !show">Mehr Info</v-btn>

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
          </div>
        </v-expand-transition>

      </v-card>
</div>
</template>