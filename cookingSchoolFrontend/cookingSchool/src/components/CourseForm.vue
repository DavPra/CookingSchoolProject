<script setup>

import {onMounted, ref} from 'vue';
import {useCourseStore} from "@/stores/CourseStore";

const courseStore = useCourseStore()
const courseId= defineProps(['courseId'])

const selectedTag = ref([])
console.log("Tags in CourseForm:", courseStore.tags);



//TODO zurück btn
const isPickerVisible = ref(false)
const data = ref({
  courseTitle: '',
  description: '',
  teacher: '',
  startDate: new Date(),
  maxAttendants: '',
  price: ''

})


/*async function loadCourseData() {
  try {
    const course = await courseStore.getCourseById();
    console.log('course data loaded from getCourseById')
    Object.assign(data, course);
  } catch (error) {
    console.error('Error loading course data:', error);
  }
}*/


onMounted(() => {
  console.log(selectedTag.value)
  console.log('CourseForm mounted');
  console.log('Initial startDate in CourseForm:', data.value.startDate);
});

/*
async function saveOrUpdateCourse() {
  try {
    //await loadCourseData()

    if (courseId) {
      await courseStore.updateCourse(courseId, data.value);
    } else {
      // Wenn courseId nicht vorhanden ist, handelt es sich um das Speichern eines neuen Kurses
      console.log('createCourse function called');

      const formattedDate = data.value.startDate instanceof Date && !isNaN(data.value.startDate.getTime())
          ? data.value.startDate.toISOString()
          : null;

      const requestData = {
        courseTitle: data.value.courseTitle,
        description: data.value.description,
        teacher: data.value.teacher,
        startDate: formattedDate,
        maxAttendants: data.value.maxAttendants,
        price: data.value.price,
      };

      try {
        await courseStore.createCourse(requestData);
        await courseStore.showCourses();
      } catch (err) {
        if (err.isAxiosError && err.response.status === 401) {
          console.error('Error creating course:', err);
        } else {
          console.error('Unexpected error:', err);
        }
      }
    }
  } catch (error) {
    console.error('Error saving/updating course:', error);
  }
}*/


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
         tags: [selectedTag.value]
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
  <div >

    <v-sheet width="400"   class="mx-auto elevation-2 pa-4 ma-2">
      <h2 class="pe-2">Add a new Course</h2>
      <v-form @submit.prevent = "createCourse">
        <v-text-field
            v-model="data.courseTitle"
            label="title"
        ></v-text-field>
      <v-text-field
          v-model="data.teacher"
          label="teacher"
      ></v-text-field>
        <v-text-field
             v-model="data.startDate"
             label="start date"
         ></v-text-field>
        <v-date-picker
            v-model="data.startDate"
            label="Start Date">
        </v-date-picker>


        <v-text-field
          v-model.number="data.maxAttendants"
          label="max Attendants"
      ></v-text-field>
        <v-text-field
          v-model.number="data.price"
          label="price"
      ></v-text-field>
        <!-- items: courseStore.tags, courseStore.tags.value"-->
        <v-select
            v-model="selectedTag"
            :items="tags"
            item-text="courseTagTitle"
            item-value="courseTagId"
            label="Select Tag"
            @change="onTagChange"
        ></v-select>

        <v-btn type="submit"  class="ma-2" variant="outlined">Save</v-btn>
        <v-btn type="submit"  @click = "" variant="tonal"  class="ma-2">Back</v-btn>

      </v-form>
    </v-sheet>
  </div>

</template>

