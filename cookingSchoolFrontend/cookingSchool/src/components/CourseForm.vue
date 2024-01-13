<script setup>
import { ref } from 'vue';
import {useCourseStore} from "@/stores/CourseStore";

const courseStore = useCourseStore()


const data = ref({
  courseTitle: '',
  description: '',
  teacher: '',
  startDate: '',
  maxAttendants: '',
  price: '',
})




//TODO Zeit stimmt nicht
  async function createCourse() {
    console.log('createCourse function called');
    console.log('Original startDate:', data.startDate)
    const formattedDate = new Date(data.startDate).toISOString();

    const requestData = {
      ...data,
      startDate: formattedDate
    };
    try {
      await courseStore.createCourse(requestData); //value entfernt
      //await router.push('/admin');
      // await showCourses();
    } catch (err) {
      if (err.isAxiosError && err.status === 401) {
        console.log(err);
        return (err = true);
      }
    }
  }

</script>

<template>

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

</template>

<style scoped>

</style>