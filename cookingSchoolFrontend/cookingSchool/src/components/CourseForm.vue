<script setup>

import {computed, onMounted, ref} from 'vue';
import {useCourseStore} from "@/stores/CourseStore";

const courseStore = useCourseStore()
let courseErr = ref(false)
let timeError = ref(false)

const data = ref({
  courseTitle: '',
  description: '',
  teacher: '',
  startDate: '',
  maxAttendants: '',
  price: '',

})



onMounted(() => {
  console.log('CourseForm mounted');
  console.log('Initial startDate in CourseForm:', data.value.startDate);
});


async function createCourse() {
  console.log('createCourse function called');
  console.log('Original startDate:', data.value.startDate);

  //if (data.value.startDate instanceof Date && !isNaN(data.value.startDate.getTime())) {
    //const formattedDate = data.value.startDate.toISOString();
    const requestData = {

      courseTitle: data.value.courseTitle,
      description: data.value.description,
      teacher: data.value.teacher,
      startDate: new Date(data.value.startDate).toISOString(),
      maxAttendants: data.value.maxAttendants,
      price: data.value.price,
    };

    try {
      await courseStore.createCourse(requestData);
      await courseStore.showCourses()
    } catch (err) {

      if (err.isAxiosError && err.response.status === 401) {
        console.error('Error creating course:', err);
        return courseErr = true
      } else {
        console.error('Unexpected error:', err.response.data);

      }
    }

}



</script>

<template>

    <div>
      <v-sheet width="400" :elevation="9" rounded class="mx-auto pa-5">
        <h2>Add a new Course</h2>
        <v-form @submit.prevent="createCourse">
          <v-text-field v-model="data.courseTitle" label="Titel"></v-text-field>
          <v-text-field v-model="data.teacher" label="teacher"></v-text-field>

          <v-text-field
              v-model="data.startDate"
              label="Start Date and Time"
              placeholder="yyyy-MM-dd HH:mm:ss"
          ></v-text-field>

          <v-text-field v-model.number="data.maxAttendants" label="max Attendants"></v-text-field>
          <v-text-field v-model.number="data.price" label="price"></v-text-field>

          <v-alert
              closable
              close-label="Close Alert"
              type="error"
              title="error"
              text="Kurse konnte nicht erstellt werden"
              v-model="courseErr"
          ></v-alert>
          <v-alert
              closable
              close-label="Close Alert"
              type="error"
              title="error"
              text="Ungültiges Zeitformat"
              v-model="timeError"
          ></v-alert>
          <v-btn type="submit" class="ma-2" variant="tonal">Save</v-btn>
          <v-btn type="button" @click="" variant="outlined" class="ma-2">Back</v-btn>
        </v-form>
      </v-sheet>
    </div>
</template>
