<script setup>

import { ref, onMounted } from 'vue';

import { useRoute } from 'vue-router';
import {useCourseStore} from "@/stores/CourseStore";

const courseStore = useCourseStore();
const route = useRoute();

const courses = ref([]);
const dialog = ref(false);
const editMode = ref(false);
const editedCourse = ref({
  courseTitle: '',
  teacher:'',
  startDate: '',
  description: '',
  image: '',
  maxAttendants: '',
  price: '',
});
const valid = ref(true);


onMounted(() => {
 courseStore.showCourses()
  fetchCourses();
});

const fetchCourses = async () => {
  await courseStore.showCourses();
  courses.value = courseStore.courses;
};

const openDialog = () => {
  editMode.value = false;
  editedCourse.value = { courseTitle: '',teacher:'', startDate: '', description: '', image: '', maxAttendants:'',price:'' };
  dialog.value = true;
};

const editCourse = (course) => {
  editMode.value = true;
  editedCourse.value = { ...course };
  dialog.value = true;
};

const saveCourse = async () => {
  try {
    console.log('Before update/create Course');
    if (editMode.value) {
      console.log('update Course called');
      console.log(editedCourse.value.courseId);
      await courseStore.updateCourse(editedCourse.value.courseId, editedCourse.value);
    } else {
      console.log('create Course called');
      console.log(editedCourse.value.courseId);
      await courseStore.createCourse(editedCourse.value);
    }

    console.log('Before showCourses');
    await courseStore.showCourses();
    console.log('After showCourses');
  } catch (error) {
    console.error('Error saving course:', error);
  } finally {
    closeDialog();
  }
};


async function deleteCourse(courseId){
  console.log('courseId delete' ,courseId)
  await courseStore.deleteCourse(courseId)
  await courseStore.showCourses()
}

const closeDialog = () => {
  dialog.value = false;
  valid.value = true;
  editedCourse.value = {
    courseTitle: '',
    teacher: '',
    startDate: '',
    description: '',
    image: '',
    maxAttendants: '',
    price: '',
  };
};



</script>

<template>
  <v-container>
    <v-row>
      <v-col v-for="course in courses" :key="course.id" cols="12" md="4">



        <v-card>
          <v-img :src="course.image" height="200"></v-img>
          <v-card-title>{{ course.courseTitle }}</v-card-title>
          <v-card-subtitle>{{ course.startDate }}</v-card-subtitle>
          <v-card-text>{{ course.description }}</v-card-text>

          <v-card-actions>
            <v-btn @click="editCourse(course)">Edit</v-btn>
            <v-btn @click="deleteCourse(course.courseId)">Delete</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
  <v-dialog v-model="dialog" max-width="500">
    <v-card>
      <v-card-title>{{ editMode ? 'Edit Course' : 'Add New Course' }}</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field v-model="editedCourse.courseTitle" label="Title" required></v-text-field>
          <v-text-field v-model="editedCourse.teacher" label="teacher" required></v-text-field>
          <v-text-field v-model="editedCourse.startDate" label="Start Date" required></v-text-field>
          <v-text-field v-model="editedCourse.description" label="Description" required></v-text-field>
          <v-text-field v-model="editedCourse.image" label="Image URL" ></v-text-field>
          <v-text-field v-model.number="editedCourse.maxAttendants" label="max Attendants"></v-text-field>
          <v-text-field v-model.number="editedCourse.price" label="price"></v-text-field>

        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn @click="saveCourse" :disabled="!valid">Save</v-btn>
        <v-btn @click="closeDialog">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn @click="openDialog">Add New Course</v-btn>

</template>
<style scoped>

</style>



