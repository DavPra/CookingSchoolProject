<script setup>
import {onMounted, ref} from 'vue';
import {useCourseStore} from "@/stores/CourseStore";

const courseStore = useCourseStore()


const data = ref({
  courseTitle: '',
  description: '',
  teacher: '',
  startDate: new Date(),
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

  if (data.value.startDate instanceof Date && !isNaN(data.value.startDate.getTime())) {
    const formattedDate = data.value.startDate.toISOString();
    const requestData = {
      courseTitle: data.value.courseTitle,
      description: data.value.description,
      teacher: data.value.teacher,
      startDate: formattedDate,
      maxAttendants: data.value.maxAttendants,
      price: data.value.price,
    };

    const secondObj = {
      ...data.value,
      startDate: formattedDate
    };

    console.log(secondObj);

    try {
      await courseStore.createCourse(requestData);
      await courseStore.showCourses()
    } catch (err) {
      if (err.isAxiosError && err.response.status === 401) {
        console.error('Error creating course:', err);
      } else {
        console.error('Unexpected error:', err);
      }
    }
  } else {
    console.error('Invalid date format:', data.startDate);
  }
}

// edit course or add and updateCourse?






</script>

<template>
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
        <v-date-picker
            v-model="data.startDate"
            label="Start Date">
        </v-date-picker>
      <!--  <v-text-field
            v-model="data.startDate"
            label="start date"
        ></v-text-field> -->

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
  </div>

</template>

<style scoped>

</style>