<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

const courseStore = useCourseStore()
const router = useRouter();

onMounted(() => {
    showCourses();
    console.log('mounted');
});


const err = false;
const data = { //ref entfernt
    courseTitle: '',
    description: '',
    teacher: '',
    startDate: '',
    maxAttendants: '',
    price: '',
};

async function createCourse() {
    console.log('createCourse function called');
    try {
        await courseStore.createCourse(data); //value entfernt
        await router.push('/admin');
    } catch (err) {
        if (err.isAxiosError && err.status === 401) {
            console.log(err);
            return (err = true);
        }
    }
}

async function showCourses() {
    await courseStore.showCourses();
}

showCourses();
</script>

<template>
  <!-- Übersicht aller Kurse zum Bearbeiten für den Admin -->

<div>
  <v-sheet width="300" class="mx-auto">
    <h2>Add a new Course</h2>
    <v-form @submit.prevent = "createCourse">
      <v-text-field
          v-model="data.courseTitle"
          label="title"
      ></v-text-field>
      <v-text-field
          v-model="data.description"
          label="description"
      ></v-text-field>
      <v-text-field
          v-model="data.teacher"
          label="teacher"
      ></v-text-field>
      <v-text-field
          v-model="data.startDate"
          label="start Date"
      ></v-text-field>
      <v-text-field
          v-model.number="data.maxAttendants"
          label="max Attendants"
      ></v-text-field>
      <v-text-field
          v-model.number="data.price"
          label="price"
      ></v-text-field>

      <v-btn type="submit" block class="mt-2">Save</v-btn>
    </v-form>

  </v-sheet>
  <div class="course-list">
    <h1>Course List</h1>
    <v-row>
      <v-col v-for="course in courseStore.courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
        <v-card>
          <v-img
              cover
              height="250"
              src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
          ></v-img>

          <v-card-title>{{ course.courseTitle }}</v-card-title>
          <v-card-subtitle>{{ course.startDate }}</v-card-subtitle>
          <v-card-text>{{ course.description }}</v-card-text>
          <v-card-actions>


            <v-btn @click="editCourse(course)" icon>
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn @click="deleteCourse(course.courseId)" icon>
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</div>





    <div>
        <h1>Course List</h1>
        <ul>
            <li v-for="course in courseStore.courses" :key="course.courseId">
                {{ course.courseId }}
                {{ course.courseTitle }}
                {{ course.description }}
                {{course.maxAttendants}}
                {{course.price}}
                {{course.startDate}}
                {{course.teacher}}

            </li>
        </ul>
    </div>



</template>
<style scoped>
.input {
border: 1px solid black;
}
</style>
