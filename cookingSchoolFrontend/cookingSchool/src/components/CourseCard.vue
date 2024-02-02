<script setup>

import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { useCourseStore } from "@/stores/CourseStore.js";
import jwtDecode from "jwt-decode";
import BookCourseAlert from "@/components/BookCourseAlert.vue";
import ConfirmDialog from "@/components/ConfirmDialog.vue";

const courseStore = useCourseStore()
const course = defineProps(['courseTitle','startDate','description','teacher','courseId','prize'])
const router = useRouter();
const showConfirmDialog = ref(false);
const showFinConfirmDialog = ref(false);

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
    try {
    const userId = jwtDecode(window.localStorage.getItem("accessToken")).userId;
    console.log("userID= " + userId);
    console.log("courseID= " + courseId);
    await courseStore.bookCourse(userId, courseId);
    console.log(courses.courseId);
    showConfirmDialog.value = false;
    showFinConfirmDialog.value = true;
  }
  catch (error) {
    console.log(error);
    alert('Kurs konnte nicht gebucht werden kontaktieren Sie bitte den Support.');
  }
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
        <v-card-subtitle>Datum: {{ startDate }}</v-card-subtitle>
        <v-card-subtitle>Preis: {{ prize }}</v-card-subtitle>
        <v-card-text>Lehrer: {{ teacher }}</v-card-text>
        <v-btn class="ms-3 " rounded="xl" @click="showConfirmDialog = true"
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
      <v-dialog v-model="showConfirmDialog" max-width="350">
        <v-card>
          <v-card-title>Buchung bestätigen</v-card-title>
          <v-card-item class="pb-5">
            <BookCourseAlert @confirm="bookCourse" @abort="showConfirmDialog = false"/>
          </v-card-item>
        </v-card>
      </v-dialog>

      <v-dialog v-model="showFinConfirmDialog" max-width="350">
        <v-card>
          <v-card-title>Buchung bestätigt</v-card-title>
          <v-card-item class="pb-5">
            <ConfirmDialog/>
          </v-card-item>
        </v-card>
      </v-dialog>
</div>
</template>